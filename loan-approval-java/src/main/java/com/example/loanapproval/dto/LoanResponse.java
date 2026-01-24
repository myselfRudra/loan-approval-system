package com.example.loanapproval.dto;

public class LoanResponse {
    private String decision;
    private double riskScore;

    public String getDecision() { return decision; }
    public void setDecision(String decision) { this.decision = decision; }
    public double getRiskScore() { return riskScore; }
    public void setRiskScore(double riskScore) { this.riskScore = riskScore; }
}
