package com.example.loanapproval.client;

import com.example.loanapproval.dto.LoanRequest;
import com.example.loanapproval.dto.LoanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class MlClient {

    private static final Logger log = LoggerFactory.getLogger(MlClient.class);

    private static final String ML_URL = "http://ml-service:8000/predict-loan";

    private final RestTemplate restTemplate;

    // ---- TIMEOUT CONFIGURATION ----
    public MlClient() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();

        factory.setConnectTimeout(3000); // 3 seconds
        factory.setReadTimeout(3000); // 3 seconds

        this.restTemplate = new RestTemplate(factory);
    }

    public LoanResponse callMl(LoanRequest request) {

        try {
            log.info("Calling ML service at {}", ML_URL);

            LoanResponse response = restTemplate.postForObject(
                    ML_URL,
                    request,
                    LoanResponse.class);

            log.info("ML response received | Decision: {}, RiskScore: {}",
                    response.getDecision(),
                    response.getRiskScore());

            return response;

        } catch (RestClientException ex) {
            log.error("ML service call failed or timed out", ex);
            throw ex;
        }
    }
}
