package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.users.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

}
