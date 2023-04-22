package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "subscriber")
public class SubscriberEntity {

    /**
     * Сущность абонента
     **/

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
    private float balance;

    public SubscriberEntity(String numberPhone, String tariffIndex, float balance) {
        this.numberPhone = numberPhone;
        this.tariffIndex = tariffIndex;
        this.balance = balance;
    }
}
