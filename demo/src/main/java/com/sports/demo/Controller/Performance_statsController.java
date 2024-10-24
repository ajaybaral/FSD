package com.sports.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sports.demo.Entity.Performance_stats;
import com.sports.demo.Repository.Performance_statsRepository;

@RestController
@RequestMapping("/performance_stats")
public class Performance_statsController {

    private static final Logger logger = LoggerFactory.getLogger(Performance_statsController.class);

    @Autowired
    private Performance_statsRepository performance_statsRepository;

    // Get all performance stats
    @GetMapping
    public List<Performance_stats> getAllPerformanceStats() {
        return performance_statsRepository.findAll();
    }

    // Get a performance stat by ID
    @GetMapping("/{id}")
    public ResponseEntity<Performance_stats> getPerformanceStatById(@PathVariable Integer id) {
        Optional<Performance_stats> performanceStat = performance_statsRepository.findById(id);
        return performanceStat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new performance stat
    @PostMapping
    public ResponseEntity<Performance_stats> createPerformanceStat(@RequestBody Performance_stats performanceStat) {
        logger.info("Received POST request to create performance stat");
        try {
            logger.debug("Attempting to save performance stat: {}", performanceStat);
            Performance_stats savedStat = performance_statsRepository.save(performanceStat);
            logger.info("Successfully created performance stat with ID: {}", savedStat.getStatId());
            return new ResponseEntity<>(savedStat, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while creating performance stat: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update a performance stat (PUT - Full Update)
    @PutMapping("/{id}")
    public ResponseEntity<Performance_stats> updatePerformanceStat(@PathVariable Integer id,
            @RequestBody Performance_stats performanceStat) {
        if (!performance_statsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        performanceStat.setStatId(id); // Ensure the ID is set
        Performance_stats updatedStat = performance_statsRepository.save(performanceStat);
        return ResponseEntity.ok(updatedStat);
    }

    // Update a performance stat (PATCH - Partial Update)
    @PatchMapping("/{id}")
    public ResponseEntity<Performance_stats> partialUpdatePerformanceStat(@PathVariable Integer id,
            @RequestBody Performance_stats performanceStat) {
        Optional<Performance_stats> existingStatOpt = performance_statsRepository.findById(id);

        if (existingStatOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Performance_stats existingStat = existingStatOpt.get();

        // Update fields if they are not null
        if (performanceStat.getPoints() != null) {
            existingStat.setPoints(performanceStat.getPoints());
        }
        if (performanceStat.getAssists() != null) {
            existingStat.setAssists(performanceStat.getAssists());
        }
        if (performanceStat.getFouls() != null) {
            existingStat.setFouls(performanceStat.getFouls());
        }
        if (performanceStat.getParticipantId() != null) {
            existingStat.setParticipantId(performanceStat.getParticipantId());
        }
        if (performanceStat.getEventId() != null) {
            existingStat.setEventId(performanceStat.getEventId());
        }

        // Save the updated entity
        Performance_stats updatedStat = performance_statsRepository.save(existingStat);
        return ResponseEntity.ok(updatedStat);
    }

    // Delete a performance stat
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerformanceStat(@PathVariable Integer id) {
        logger.info("Deleting performance stat with ID: {}", id);
        if (!performance_statsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        performance_statsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
