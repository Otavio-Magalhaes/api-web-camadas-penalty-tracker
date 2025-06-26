package com.otavio.penalty_tracker.controller;

import com.otavio.penalty_tracker.dto.PenaltyKickDTO;
import com.otavio.penalty_tracker.service.PenaltyKickService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/penalty-kicks")
@RequiredArgsConstructor
public class PenaltyKickController {

    private final PenaltyKickService penaltyKickService;

    @GetMapping
    public ResponseEntity<List<PenaltyKickDTO>> getAllPenaltyKicks() {
        return ResponseEntity.ok(penaltyKickService.findAll());
    }

    @GetMapping("/player/{playerId}")
    public ResponseEntity<List<PenaltyKickDTO>> getByPlayerId(@PathVariable Long playerId) {
        return ResponseEntity.ok(penaltyKickService.findByPlayerId(playerId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PenaltyKickDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(penaltyKickService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PenaltyKickDTO> createPenaltyKick(@RequestBody PenaltyKickDTO dto) {
        return ResponseEntity.ok(penaltyKickService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PenaltyKickDTO> updatePenaltyKick(@PathVariable Long id, @RequestBody PenaltyKickDTO dto) {
        return ResponseEntity.ok(penaltyKickService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePenaltyKick(@PathVariable Long id) {
        penaltyKickService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
