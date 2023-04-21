package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "subscriber_report")
public class SubscriberReportEntity {

    @Id
    @SequenceGenerator(
            name = "subscriber_id_seq",
            sequenceName = "subscriber_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "subscriber_id_seq"
    )
    private long id;
    private String numberPhone;
    private String tariffIndex;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PayloadEntity> payloads;
    private float totalCost;
    private String monetaryUnit;

    public SubscriberReportEntity(
            String numberPhone,
            String tariffIndex,
            List<PayloadEntity> payloads,
            float totalCost,
            String monetaryUnit
    ) {
        this.numberPhone = numberPhone;
        this.tariffIndex = tariffIndex;
        this.payloads = payloads;
        this.totalCost = totalCost;
        this.monetaryUnit = monetaryUnit;
    }

    public SubscriberReportEntity(
            String numberPhone,
            String tariffIndex,
            float totalCost,
            String monetaryUnit
    ) {
        this.numberPhone = numberPhone;
        this.tariffIndex = tariffIndex;
        this.totalCost = totalCost;
        this.monetaryUnit = monetaryUnit;
        payloads = new ArrayList<>();
    }

    public SubscriberReportEntity addPayload(PayloadEntity payload){
        payloads.add(payload);
        return this;
    }

    public SubscriberReportEntity removePayload(PayloadEntity payload){
        payloads.remove(payload);
        return this;
    }

}
