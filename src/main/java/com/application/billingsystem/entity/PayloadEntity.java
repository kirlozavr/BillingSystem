package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "payload")
public class PayloadEntity {

    @Id
    @SequenceGenerator(
            name = "payload_id_seq",
            sequenceName = "payload_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "payload_id_seq"
    )
    private long id;
    private String callType;
    private String startTime;
    private String endTime;
    private String duration;
    private float cost;

    public PayloadEntity(String callType, String startTime, String endTime, String duration, float cost) {
        this.callType = callType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayloadEntity that)) return false;
        return id == that.id && Float.compare(that.cost, cost) == 0 && callType.equals(that.callType) && startTime.equals(that.startTime) && endTime.equals(that.endTime) && duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, callType, startTime, endTime, duration, cost);
    }
}
