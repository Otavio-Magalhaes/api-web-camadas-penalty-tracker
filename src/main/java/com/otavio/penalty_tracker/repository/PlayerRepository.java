package com.otavio.penalty_tracker.repository;

import com.otavio.penalty_tracker.model.Player;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @EntityGraph(attributePaths = "penaltyKicks")
    Optional<Player> findById(Long id);

}
