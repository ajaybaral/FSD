package com.sports.demo.Repository;

import com.sports.demo.Entity.Penalties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenaltiesRepository extends JpaRepository<Penalties, Integer> {
}
