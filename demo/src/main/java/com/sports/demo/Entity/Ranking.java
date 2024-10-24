package com.sports.demo.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ranking")
public class Ranking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ranking_seq")
    @SequenceGenerator(name = "ranking_seq", sequenceName = "ranking_ranking_id_seq", allocationSize = 1)
    @GenericGenerator(name = "ranking_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "increment_size", value = "1") // Ensure the sequence increments by 1
    })
    @Column(name = "ranking_id", nullable = false)
    private Integer rankingId;

    @Column(name = "participant_id")
    private Integer participantId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "pointsearned")
    private Integer pointsEarned;

    @Column(name = "rankposition")
    private Integer rankPosition;

    // Getters and Setters

    public Integer getRankingId() {
        return rankingId;
    }

    public void setRankingId(Integer rankingId) {
        this.rankingId = rankingId;
    }

    public Integer getParticipantId() {
        return participantId;
    }

    public void setParticipantId(Integer participantId) {
        this.participantId = participantId;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public Integer getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(Integer pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public Integer getRankPosition() {
        return rankPosition;
    }

    public void setRankPosition(Integer rankPosition) {
        this.rankPosition = rankPosition;
    }
}
