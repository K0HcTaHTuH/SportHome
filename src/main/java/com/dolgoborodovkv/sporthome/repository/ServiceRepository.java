package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.gym.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {

}
