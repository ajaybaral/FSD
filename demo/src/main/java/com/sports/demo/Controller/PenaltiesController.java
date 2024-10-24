package com.sports.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sports.demo.Entity.Penalties;
import com.sports.demo.Repository.PenaltiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/penalties")
public class PenaltiesController {

    private static final Logger logger = LoggerFactory.getLogger(PenaltiesController.class);

    @Autowired
    private PenaltiesRepository penaltiesRepository;

    // POST method to create a new penalty
    @PostMapping
    public ResponseEntity<Penalties> createPenalty(@RequestBody Penalties penalty) {
        logger.info("Received request to create penalty: {}", penalty);
        try {
            Penalties savedPenalty = penaltiesRepository.save(penalty);
            logger.info("Successfully created penalty with ID: {}", savedPenalty.getPenaltyId());
            return new ResponseEntity<>(savedPenalty, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while creating penalty: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET method to fetch all penalties
    @GetMapping
    public ResponseEntity<List<Penalties>> getAllPenalties() {
        List<Penalties> penalties = penaltiesRepository.findAll();
        return new ResponseEntity<>(penalties, HttpStatus.OK);
    }

    // GET method to fetch a specific penalty by ID
    @GetMapping("/{id}")
    public ResponseEntity<Penalties> getPenaltyById(@PathVariable Integer id) {
        logger.info("Received request to fetch penalty with ID: {}", id);
        return penaltiesRepository.findById(id)
                .map(penalty -> {
                    logger.info("Penalty found: {}", penalty);
                    return new ResponseEntity<>(penalty, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.warn("Penalty not found with ID: {}", id);
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                });
    }

    // PATCH method to partially update an existing penalty
    @PatchMapping("/{id}")
    public ResponseEntity<Penalties> updatePenalty(@PathVariable Integer id, @RequestBody Penalties penalty) {
        logger.info("Received request to partially update penalty with ID: {}", id);
        return penaltiesRepository.findById(id)
                .map(existingPenalty -> {
                    // Update fields if they're not null
                    if (penalty.getParticipantId() != null) {
                        existingPenalty.setParticipantId(penalty.getParticipantId());
                    }
                    if (penalty.getEventId() != null) {
                        existingPenalty.setEventId(penalty.getEventId());
                    }
                    if (penalty.getPenaltyType() != null) {
                        existingPenalty.setPenaltyType(penalty.getPenaltyType());
                    }
                    if (penalty.getReason() != null) {
                        existingPenalty.setReason(penalty.getReason());
                    }
                    Penalties updatedPenalty = penaltiesRepository.save(existingPenalty);
                    logger.info("Successfully updated penalty with ID: {}", updatedPenalty.getPenaltyId());
                    return new ResponseEntity<>(updatedPenalty, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.warn("Penalty not found with ID: {}", id);
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                });
    }

    // PUT method to fully update an existing penalty
    @PutMapping("/{id}")
    public ResponseEntity<Penalties> updatePenaltyFully(@PathVariable Integer id, @RequestBody Penalties penalty) {
        logger.info("Received request to fully update penalty with ID: {}", id);
        return penaltiesRepository.findById(id)
                .map(existingPenalty -> {
                    penalty.setPenaltyId(id); // Ensure the ID is set for the updated penalty
                    Penalties updatedPenalty = penaltiesRepository.save(penalty);
                    logger.info("Successfully updated penalty with ID: {}", updatedPenalty.getPenaltyId());
                    return new ResponseEntity<>(updatedPenalty, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.warn("Penalty not found with ID: {}", id);
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                });
    }

    // DELETE method to remove a penalty
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePenalty(@PathVariable Integer id) {
        logger.info("Received request to delete penalty with ID: {}", id);
        if (penaltiesRepository.existsById(id)) {
            penaltiesRepository.deleteById(id);
            logger.info("Successfully deleted penalty with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            logger.warn("Penalty not found with ID: {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
