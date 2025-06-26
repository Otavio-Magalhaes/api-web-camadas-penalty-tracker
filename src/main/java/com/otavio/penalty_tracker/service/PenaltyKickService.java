package com.otavio.penalty_tracker.service;

import com.otavio.penalty_tracker.dto.PenaltyKickDTO;
import com.otavio.penalty_tracker.model.PenaltyKick;
import com.otavio.penalty_tracker.model.Player;
import com.otavio.penalty_tracker.repository.PenaltyKickRepository;
import com.otavio.penalty_tracker.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PenaltyKickService {

    private final PenaltyKickRepository penaltyKickRepository;
    private final PlayerRepository playerRepository;

    private PenaltyKickDTO toDTO(PenaltyKick pk) {
        PenaltyKickDTO dto = new PenaltyKickDTO();
        dto.setId(pk.getId());
        dto.setMatchDate(pk.getMatchDate());
        dto.setTournament(pk.getTournament());
        dto.setDirection(pk.getDirection());
        dto.setGoal(pk.getGoal());
        dto.setKeeperGuess(pk.getKeeperGuess());
        dto.setPlayerId(pk.getPlayer().getId());
        return dto;
    }

    private PenaltyKick toEntity(PenaltyKickDTO dto) {
        PenaltyKick pk = new PenaltyKick();
        pk.setId(dto.getId());
        pk.setMatchDate(dto.getMatchDate());
        pk.setTournament(dto.getTournament());
        pk.setDirection(dto.getDirection());
        pk.setGoal(dto.getGoal());
        pk.setKeeperGuess(dto.getKeeperGuess());

        Player player = playerRepository.findById(dto.getPlayerId())
                .orElseThrow(() -> new Error("Player not found with id: " + dto.getPlayerId()));

        pk.setPlayer(player);
        return pk;
    }

    public List<PenaltyKickDTO> findAll() {
        return penaltyKickRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PenaltyKickDTO> findByPlayerId(Long playerId) {
        return penaltyKickRepository.findByPlayerId(playerId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PenaltyKickDTO findById(Long id) {
        PenaltyKick pk = penaltyKickRepository.findById(id)
                .orElseThrow(() -> new Error("PenaltyKick not found with id: " + id));
        return toDTO(pk);
    }

    public PenaltyKickDTO create(PenaltyKickDTO dto) {
        PenaltyKick pk = toEntity(dto);
        pk = penaltyKickRepository.save(pk);
        return toDTO(pk);
    }

    public PenaltyKickDTO update(Long id, PenaltyKickDTO dto) {
        PenaltyKick pk = penaltyKickRepository.findById(id)
                .orElseThrow(() -> new Error("PenaltyKick not found with id: " + id));

        pk.setMatchDate(dto.getMatchDate());
        pk.setTournament(dto.getTournament());
        pk.setDirection(dto.getDirection());
        pk.setGoal(dto.getGoal());
        pk.setKeeperGuess(dto.getKeeperGuess());

        Player player = playerRepository.findById(dto.getPlayerId())
                .orElseThrow(() -> new Error("Player not found with id: " + dto.getPlayerId()));

        pk.setPlayer(player);

        pk = penaltyKickRepository.save(pk);
        return toDTO(pk);
    }

    public void delete(Long id) {
        PenaltyKick pk = penaltyKickRepository.findById(id)
                .orElseThrow(() -> new Error("PenaltyKick not found with id: " + id));
        penaltyKickRepository.delete(pk);
    }
}
