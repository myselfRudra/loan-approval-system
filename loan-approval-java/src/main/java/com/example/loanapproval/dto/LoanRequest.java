package com.example.loanapproval.dto;

public class LoanRequest {
    private int noOfDependents;
    private int incomeAnnum;
    private int loanAmount;
    private int loanTerm;
    private int cibilScore;
    private int residentialAssetsValue;
    private int commercialAssetsValue;
    private int luxuryAssetsValue;
    private int bankAssetValue;
    private int educationGraduate;
    private int selfEmployedYes;

    public int getNoOfDependents() { return noOfDependents; }
    public void setNoOfDependents(int noOfDependents) { this.noOfDependents = noOfDependents; }
    public int getIncomeAnnum() { return incomeAnnum; }
    public void setIncomeAnnum(int incomeAnnum) { this.incomeAnnum = incomeAnnum; }
    public int getLoanAmount() { return loanAmount; }
    public void setLoanAmount(int loanAmount) { this.loanAmount = loanAmount; }
    public int getLoanTerm() { return loanTerm; }
    public void setLoanTerm(int loanTerm) { this.loanTerm = loanTerm; }
    public int getCibilScore() { return cibilScore; }
    public void setCibilScore(int cibilScore) { this.cibilScore = cibilScore; }
    public int getResidentialAssetsValue() { return residentialAssetsValue; }
    public void setResidentialAssetsValue(int residentialAssetsValue) { this.residentialAssetsValue = residentialAssetsValue; }
    public int getCommercialAssetsValue() { return commercialAssetsValue; }
    public void setCommercialAssetsValue(int commercialAssetsValue) { this.commercialAssetsValue = commercialAssetsValue; }
    public int getLuxuryAssetsValue() { return luxuryAssetsValue; }
    public void setLuxuryAssetsValue(int luxuryAssetsValue) { this.luxuryAssetsValue = luxuryAssetsValue; }
    public int getBankAssetValue() { return bankAssetValue; }
    public void setBankAssetValue(int bankAssetValue) { this.bankAssetValue = bankAssetValue; }
    public int getEducationGraduate() { return educationGraduate; }
    public void setEducationGraduate(int educationGraduate) { this.educationGraduate = educationGraduate; }
    public int getSelfEmployedYes() { return selfEmployedYes; }
    public void setSelfEmployedYes(int selfEmployedYes) { this.selfEmployedYes = selfEmployedYes; }
}
