package com.example.loanapproval.controller;

import com.example.loanapproval.dto.LoanRequest;
import com.example.loanapproval.dto.LoanResponse;
import com.example.loanapproval.entity.LoanApplication;
import com.example.loanapproval.repository.LoanApplicationRepository;
import com.example.loanapproval.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private static final Logger log = LoggerFactory.getLogger(LoanController.class);

    private final LoanService loanService;
    private final LoanApplicationRepository repository;

    public LoanController(LoanService loanService,
            LoanApplicationRepository repository) {
        this.loanService = loanService;
        this.repository = repository;
    }

    // ---------------- APPLY FOR LOAN ----------------
    @PostMapping("/apply")
    public LoanResponse applyLoan(@RequestBody LoanRequest request) {

        log.info("Loan application request received");
        return loanService.processLoan(request);
    }

    // ---------------- LOAN HISTORY ----------------
    @GetMapping("/history")
    public List<LoanApplication> getLoanHistory() {

        log.info("Fetching loan application history");
        return repository.findAll();
    }
}
