package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.users.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}
