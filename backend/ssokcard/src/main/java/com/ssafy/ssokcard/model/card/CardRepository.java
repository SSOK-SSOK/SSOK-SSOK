package com.ssafy.ssokcard.model.card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByCategoryId(long id);
    List<Card> findByCategoryIdAndLanguage(long id, String languageCode);
}