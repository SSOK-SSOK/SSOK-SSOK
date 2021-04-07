package com.ssafy.ssokcard.model.log;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardGameLogRepository extends JpaRepository<CardGameLog, Long> {
}