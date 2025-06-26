package com.otavio.penalty_tracker.repository;

import com.otavio.penalty_tracker.model.PenaltyKick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaltyKickRepository extends JpaRepository<PenaltyKick, Long> {
    List<PenaltyKick> findByPlayerId(Long playerId);
}
