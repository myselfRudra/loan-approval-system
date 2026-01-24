package com.example.loanapproval.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_application")
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cibilScore;
    private int incomeAnnum;
    private int loanAmount;

    private String decision;
    private double riskScore;

    private LocalDateTime createdAt;

    // -------- GETTERS & SETTERS --------

    public Long getId() {
        return id;
    }

    public int getCibilScore() {
        return cibilScore;
    }

    public void setCibilScore(int cibilScore) {
        this.cibilScore = cibilScore;
    }

    public int getIncomeAnnum() {
        return incomeAnnum;
    }

    public void setIncomeAnnum(int incomeAnnum) {
        this.incomeAnnum = incomeAnnum;
    }

    public int getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(int loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
