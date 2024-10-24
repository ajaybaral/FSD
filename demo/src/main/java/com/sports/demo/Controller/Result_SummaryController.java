package com.sports.demo.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sports.demo.Entity.Result_Summary;
import com.sports.demo.Repository.Result_SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/result-summary")
public class Result_SummaryController {

    private static final Logger logger = LoggerFactory.getLogger(Result_SummaryController.class);

    @Autowired
    private Result_SummaryRepository resultSummaryRepository;

    // POST method to create a new result summary
    @PostMapping
    public ResponseEntity<Result_Summary> createResultSummary(@RequestBody Result_Summary resultSummary) {
        logger.info("Received request to create result summary: {}", resultSummary);
        try {
            Result_Summary savedSummary = resultSummaryRepository.save(resultSummary);
            logger.info("Successfully created result summary with ID: {}", savedSummary.getResultId());
            return new ResponseEntity<>(savedSummary, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error while creating result summary: {}", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET method to fetch all result summaries
    @GetMapping
    public ResponseEntity<List<Result_Summary>> getAllResultSummaries() {
        List<Result_Summary> summaries = resultSummaryRepository.findAll();
        return new ResponseEntity<>(summaries, HttpStatus.OK);
    }

    // GET method to fetch a result summary by ID
    @GetMapping("/{id}")
    public ResponseEntity<Result_Summary> getResultSummaryById(@PathVariable Integer id) {
        Optional<Result_Summary> summary = resultSummaryRepository.findById(id);
        if (summary.isPresent()) {
            return new ResponseEntity<>(summary.get(), HttpStatus.OK);
        } else {
            logger.warn("Result summary with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // PUT method to update an existing result summary
    @PutMapping("/{id}")
    public ResponseEntity<Result_Summary> updateResultSummary(@PathVariable Integer id,
            @RequestBody Result_Summary updatedSummary) {
        logger.info("Received request to update result summary with ID: {}", id);
        if (!resultSummaryRepository.existsById(id)) {
            logger.warn("Result summary with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        updatedSummary.setResultId(id); // Ensure the ID is set correctly
        Result_Summary savedSummary = resultSummaryRepository.save(updatedSummary);
        logger.info("Successfully updated result summary with ID: {}", savedSummary.getResultId());
        return new ResponseEntity<>(savedSummary, HttpStatus.OK);
    }

    // PATCH method to partially update an existing result summary
    @PatchMapping("/{id}")
    public ResponseEntity<Result_Summary> partialUpdateResultSummary(@PathVariable Integer id,
            @RequestBody Result_Summary partialUpdate) {
        logger.info("Received request to partially update result summary with ID: {}", id);
        Optional<Result_Summary> existingSummary = resultSummaryRepository.findById(id);
        if (!existingSummary.isPresent()) {
            logger.warn("Result summary with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Result_Summary summaryToUpdate = existingSummary.get();

        // Update fields if they are not null in the partial update request
        if (partialUpdate.getFinalscore() != null) {
            summaryToUpdate.setFinalscore(partialUpdate.getFinalscore());
        }
        if (partialUpdate.getOutcome() != null) {
            summaryToUpdate.setOutcome(partialUpdate.getOutcome());
        }
        if (partialUpdate.getDate() != null) {
            summaryToUpdate.setDate(partialUpdate.getDate());
        }
        if (partialUpdate.getRankingId() != null) {
            summaryToUpdate.setRankingId(partialUpdate.getRankingId());
        }
        if (partialUpdate.getStatId() != null) {
            summaryToUpdate.setStatId(partialUpdate.getStatId());
        }

        Result_Summary savedSummary = resultSummaryRepository.save(summaryToUpdate);
        logger.info("Successfully partially updated result summary with ID: {}", savedSummary.getResultId());
        return new ResponseEntity<>(savedSummary, HttpStatus.OK);
    }

    // DELETE method to delete a result summary by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultSummary(@PathVariable Integer id) {
        logger.info("Received request to delete result summary with ID: {}", id);
        if (!resultSummaryRepository.existsById(id)) {
            logger.warn("Result summary with ID {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        resultSummaryRepository.deleteById(id);
        logger.info("Successfully deleted result summary with ID: {}", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
