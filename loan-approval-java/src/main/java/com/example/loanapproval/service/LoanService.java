package com.example.loanapproval.service;

import com.example.loanapproval.client.MlClient;
import com.example.loanapproval.dto.LoanRequest;
import com.example.loanapproval.dto.LoanResponse;
import com.example.loanapproval.entity.LoanApplication;
import com.example.loanapproval.repository.LoanApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoanService {

    private static final Logger log = LoggerFactory.getLogger(LoanService.class);

    private final MlClient mlClient;
    private final LoanApplicationRepository repository;

    public LoanService(MlClient mlClient,
            LoanApplicationRepository repository) {
        this.mlClient = mlClient;
        this.repository = repository;
    }

    public LoanResponse processLoan(LoanRequest request) {

        log.info("Received loan application | CIBIL: {}, Income: {}, Amount: {}",
                request.getCibilScore(),
                request.getIncomeAnnum(),
                request.getLoanAmount());

        // ---------- BUSINESS RULE ----------
        if (request.getCibilScore() < 500) {
            log.warn("Loan rejected due to low CIBIL score");

            LoanResponse response = new LoanResponse();
            response.setDecision("REJECTED");
            response.setRiskScore(1.0);

            saveToDatabase(request, response);
            return response;
        }

        // ---------- ML CALL WITH FALLBACK ----------
        try {
            log.info("Calling ML microservice");
            LoanResponse mlResponse = mlClient.callMl(request);

            if (mlResponse.getRiskScore() > 0.7) {
                log.warn("Loan marked HIGH_RISK by business rule");
                mlResponse.setDecision("HIGH_RISK");
            }

            saveToDatabase(request, mlResponse);
            return mlResponse;

        } catch (Exception ex) {

            log.error("ML unavailable → falling back to MANUAL_REVIEW");

            LoanResponse fallback = new LoanResponse();
            fallback.setDecision("MANUAL_REVIEW");
            fallback.setRiskScore(0.5);

            saveToDatabase(request, fallback);
            return fallback;
        }
    }

    private void saveToDatabase(LoanRequest request,
            LoanResponse response) {

        LoanApplication entity = new LoanApplication();
        entity.setCibilScore(request.getCibilScore());
        entity.setIncomeAnnum(request.getIncomeAnnum());
        entity.setLoanAmount(request.getLoanAmount());
        entity.setDecision(response.getDecision());
        entity.setRiskScore(response.getRiskScore());
        entity.setCreatedAt(LocalDateTime.now());

        repository.save(entity);

        log.info("Loan application saved | Decision: {}",
                response.getDecision());
    }
}
