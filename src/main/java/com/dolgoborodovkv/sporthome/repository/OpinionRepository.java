package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.gym.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {

}
