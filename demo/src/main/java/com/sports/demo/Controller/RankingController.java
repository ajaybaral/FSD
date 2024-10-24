package com.sports.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sports.demo.Entity.Ranking;
import com.sports.demo.Repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    private static final Logger logger = LoggerFactory.getLogger(RankingController.class);

    @Autowired
    private RankingRepository rankingRepository;

    @PostMapping("/test")
    public ResponseEntity<String> testPost() {
        logger.info("Test POST request received");
        return ResponseEntity.ok("POST request received!");
    }

    // POST method to create a new ranking
    @PostMapping
    public ResponseEntity<Ranking> createRanking(@RequestBody Ranking ranking) {
        logger.info("Received request to create ranking: {}", ranking);
        try {
            Ranking savedRanking = rankingRepository.save(ranking);
            logger.info("Successfully created ranking with ID: {}", savedRanking.getRankingId());
            return new ResponseEntity<>(savedRanking, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while creating ranking: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET method to fetch all rankings
    @GetMapping
    public ResponseEntity<List<Ranking>> getAllRankings() {
        List<Ranking> rankings = rankingRepository.findAll();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }

    // PUT method to fully update an existing ranking
    @PutMapping("/{id}")
    public ResponseEntity<Ranking> updateRanking(@PathVariable Integer id, @RequestBody Ranking ranking) {
        logger.info("Received request to fully update ranking with ID: {}", id);
        return rankingRepository.findById(id)
                .map(existingRanking -> {
                    // Fully update the ranking
                    ranking.setRankingId(id); // Ensure the ID is set
                    Ranking updatedRanking = rankingRepository.save(ranking);
                    logger.info("Successfully updated ranking with ID: {}", updatedRanking.getRankingId());
                    return new ResponseEntity<>(updatedRanking, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.error("Ranking with ID {} not found", id);
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                });
    }

    // PATCH method to partially update an existing ranking
    @PatchMapping("/{id}")
    public ResponseEntity<Ranking> patchRanking(@PathVariable Integer id, @RequestBody Ranking updatedRanking) {
        logger.info("Received request to update ranking with ID: {}", id);
        return rankingRepository.findById(id)
                .map(existingRanking -> {
                    // Update fields as needed
                    if (updatedRanking.getParticipantId() != null) {
                        existingRanking.setParticipantId(updatedRanking.getParticipantId());
                    }
                    if (updatedRanking.getEventId() != null) {
                        existingRanking.setEventId(updatedRanking.getEventId());
                    }
                    if (updatedRanking.getPointsEarned() != null) {
                        existingRanking.setPointsEarned(updatedRanking.getPointsEarned());
                    }
                    if (updatedRanking.getRankPosition() != null) {
                        existingRanking.setRankPosition(updatedRanking.getRankPosition());
                    }
                    Ranking savedRanking = rankingRepository.save(existingRanking);
                    logger.info("Successfully updated ranking with ID: {}", savedRanking.getRankingId());
                    return new ResponseEntity<>(savedRanking, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    logger.error("Ranking with ID {} not found", id);
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
                });
    }

    // DELETE method to remove a ranking
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRanking(@PathVariable Integer id) {
        logger.info("Received request to delete ranking with ID: {}", id);
        return rankingRepository.findById(id)
                .map(existingRanking -> {
                    rankingRepository.delete(existingRanking);
                    logger.info("Successfully deleted ranking with ID: {}", id);
                    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> {
                    logger.error("Ranking with ID {} not found", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }
}
