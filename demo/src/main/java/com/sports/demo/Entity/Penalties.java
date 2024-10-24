package com.sports.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "penalties")
public class Penalties {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "penalties_seq")
    @SequenceGenerator(name = "penalties_seq", sequenceName = "penalties_penalty_id_seq", allocationSize = 1)
    @Column(name = "penalty_id", nullable = false)
    private Integer penaltyId;

    @Column(name = "participant_id")
    private Integer participantId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "penalty_type", length = 50)
    private String penaltyType;

    @Column(name = "reason", length = 255)
    private String reason;

    // Getters and Setters

    public Integer getPenaltyId() {
        return penaltyId;
    }

    public void setPenaltyId(Integer penaltyId) {
        this.penaltyId = penaltyId;
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

    public String getPenaltyType() {
        return penaltyType;
    }

    public void setPenaltyType(String penaltyType) {
        this.penaltyType = penaltyType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
