package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "payment")
public class PaymentEntity {

    /**
     * Сущность платежа на пополнение счета
     **/

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
    private long money;

    public PaymentEntity(String numberPhone, long money) {
        this.numberPhone = numberPhone;
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaymentEntity paymentEntity)) return false;
        return id == paymentEntity.id && Float.compare(paymentEntity.money, money) == 0 && numberPhone.equals(paymentEntity.numberPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberPhone, money);
    }
}
