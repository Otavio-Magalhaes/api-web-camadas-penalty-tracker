package com.otavio.penalty_tracker.dto;

import com.otavio.penalty_tracker.model.PenaltyKick.Direction;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PenaltyKickDTO {
    private Long id;
    private LocalDate matchDate;
    private String tournament;
    private Direction direction;
    private Boolean goal;
    private String keeperGuess;

    private Long playerId; //
}
