package com.example.loanapproval.repository;

import com.example.loanapproval.entity.LoanApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanApplicationRepository
        extends JpaRepository<LoanApplication, Long> {
}
