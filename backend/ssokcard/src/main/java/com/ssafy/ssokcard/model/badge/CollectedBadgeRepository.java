package com.ssafy.ssokcard.model.badge;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectedBadgeRepository extends JpaRepository<CollectedBadge, Long> {
}
