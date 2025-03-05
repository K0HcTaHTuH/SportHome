package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

}
