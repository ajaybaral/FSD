package com.sports.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sports.demo.Entity.Result_Summary;

@Repository
public interface Result_SummaryRepository extends JpaRepository<Result_Summary, Integer> {

}
