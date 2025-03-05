package com.dolgoborodovkv.sporthome.repository;

import com.dolgoborodovkv.sporthome.entity.gym.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
