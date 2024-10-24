package com.sports.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "result_summary")
public class Result_Summary {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "result_id", nullable = false)
    private Integer resultId;

    @Column(name = "finalscore")
    private Integer finalscore;

    @Column(name = "outcome", length = 50)
    private String outcome;

    @Column(name = "date")
    private java.sql.Date date; // or java.util.Date

    @Column(name = "ranking_id")
    private Integer rankingId;

    @Column(name = "stat_id")
    private Integer statId;

    // Getters and Setters
    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getFinalscore() {
        return finalscore;
    }

    public void setFinalscore(Integer finalscore) {
        this.finalscore = finalscore;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Integer getRankingId() {
        return rankingId;
    }

    public void setRankingId(Integer rankingId) {
        this.rankingId = rankingId;
    }

    public Integer getStatId() {
        return statId;
    }

    public void setStatId(Integer statId) {
        this.statId = statId;
    }
}
