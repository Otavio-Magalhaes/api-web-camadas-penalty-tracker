package com.otavio.penalty_tracker.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlayerDTO {
    private Long id;
    private String name;
    private String team;
    private String position;
    private String preferredFoot;
    private Integer age;

    private List<PenaltyKickDTO> penaltyKicks;
}