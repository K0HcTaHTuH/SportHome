package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
