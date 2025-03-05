package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.gym.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {

}
