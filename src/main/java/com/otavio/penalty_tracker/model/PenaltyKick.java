package com.otavio.penalty_tracker.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "penalty_kicks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PenaltyKick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate matchDate;
    private String tournament;
    @Enumerated(EnumType.STRING)
    private Direction direction;
    private Boolean goal;
    private String keeperGuess;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;

    public enum Direction {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        CENTER
    }
}
