package com.sports.demo.Repository;

import com.sports.demo.Entity.Performance_stats; // Make sure the entity name matches
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Performance_statsRepository extends JpaRepository<Performance_stats, Integer> {
}
