package com.otavio.penalty_tracker.service;

import com.otavio.penalty_tracker.dto.PenaltyKickDTO;
import com.otavio.penalty_tracker.dto.PlayerDTO;
import com.otavio.penalty_tracker.model.Player;
import com.otavio.penalty_tracker.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;


    private PlayerDTO toDTO(Player player) {
        PlayerDTO dto = new PlayerDTO();
        dto.setId(player.getId());
        dto.setName(player.getName());
        dto.setTeam(player.getTeam());
        dto.setPosition(player.getPosition());
        dto.setPreferredFoot(player.getPreferredFoot());
        dto.setAge(player.getAge());

        if (player.getPenaltyKicks() != null) {
            List<PenaltyKickDTO> penaltyKickDTOs = player.getPenaltyKicks().stream().map(p -> {
                PenaltyKickDTO kickDTO = new PenaltyKickDTO();
                kickDTO.setId(p.getId());
                kickDTO.setMatchDate(p.getMatchDate());
                kickDTO.setTournament(p.getTournament());
                kickDTO.setDirection(p.getDirection());
                kickDTO.setGoal(p.getGoal());
                kickDTO.setKeeperGuess(p.getKeeperGuess());
                kickDTO.setPlayerId(p.getPlayer().getId());
                return kickDTO;
            }).collect(Collectors.toList());
            dto.setPenaltyKicks(penaltyKickDTOs);
        }
        return dto;
    }


    private Player toEntity(PlayerDTO dto) {
        Player player = new Player();
        player.setId(dto.getId());
        player.setName(dto.getName());
        player.setTeam(dto.getTeam());
        player.setPosition(dto.getPosition());
        player.setPreferredFoot(dto.getPreferredFoot());
        player.setAge(dto.getAge());
        return player;
    }

    public List<PlayerDTO> findAll() {
        return playerRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PlayerDTO findById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Player not found"));
        return toDTO(player);
    }

    public PlayerDTO create(PlayerDTO dto) {
        Player player = toEntity(dto);
        player = playerRepository.save(player);
        return toDTO(player);
    }

    public PlayerDTO update(Long id, PlayerDTO dto) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new Error("Player not found with id: " + id));

        player.setName(dto.getName());
        player.setTeam(dto.getTeam());
        player.setPosition(dto.getPosition());
        player.setPreferredFoot(dto.getPreferredFoot());
        player.setAge(dto.getAge());

        player = playerRepository.save(player);
        return toDTO(player);
    }

    public void delete(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new Error("Player not found with id: " + id));
        playerRepository.delete(player);
    }
}
