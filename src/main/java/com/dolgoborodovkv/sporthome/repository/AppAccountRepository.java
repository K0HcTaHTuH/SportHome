package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.users.AppAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppAccountRepository extends JpaRepository<AppAccount, Long> {

}
