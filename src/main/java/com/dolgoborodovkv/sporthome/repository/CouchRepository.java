package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.users.Couch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouchRepository extends JpaRepository<Couch, Long> {

}
