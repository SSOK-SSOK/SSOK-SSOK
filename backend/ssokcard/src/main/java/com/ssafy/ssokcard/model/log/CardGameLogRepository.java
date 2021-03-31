package com.ssafy.ssokcard.model.log;

import com.ssafy.ssokcard.model.CardGameLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardGameLogRepository extends JpaRepository<CardGameLog, Long> {
}