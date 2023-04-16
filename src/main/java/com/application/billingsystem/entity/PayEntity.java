package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "pay_transactions")
public class PayEntity {

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
    private String numberPhone;
    private float money;

    public PayEntity(String numberPhone, float money) {
        this.numberPhone = numberPhone;
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayEntity payEntity)) return false;
        return id == payEntity.id && Float.compare(payEntity.money, money) == 0 && numberPhone.equals(payEntity.numberPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberPhone, money);
    }
}
