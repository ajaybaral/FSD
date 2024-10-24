package com.sports.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "performance_stats")
public class Performance_stats {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "performance_stats_seq")
    @GenericGenerator(name = "performance_stats_seq", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
            @Parameter(name = "increment_size", value = "1") // Increment size set to 1
    })
    @Column(name = "stat_id", nullable = false)
    private Integer statId;

    @Column(name = "participant_id")
    private Integer participantId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "points")
    private Integer points;

    @Column(name = "assists")
    private Integer assists;

    @Column(name = "fouls")
    private Integer fouls;

    public Integer getStatId() {
        return statId;
    }

    public void setStatId(Integer statId) {
        this.statId = statId;
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

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getAssists() {
        return assists;
    }

    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    public Integer getFouls() {
        return fouls;
    }

    public void setFouls(Integer fouls) {
        this.fouls = fouls;
    }
}
