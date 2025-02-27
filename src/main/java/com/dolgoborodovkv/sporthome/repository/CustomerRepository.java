package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.users.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
