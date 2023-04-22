package com.application.billingsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "change_subscriber_tariff")
public class ChangeSubscriberTariffEntity {

    /**
     * Сущность изменения тарифа
     **/

    @Id
    @SequenceGenerator(
            name = "change_subscriber_tariff_id_seq",
            sequenceName = "change_subscriber_tariff_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "change_subscriber_tariff_id_seq"
    )
    private long id;
    private String numberPhone;
    private String tariffIndex;

    public ChangeSubscriberTariffEntity(String numberPhone, String tariffIndex) {
        this.numberPhone = numberPhone;
        this.tariffIndex = tariffIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChangeSubscriberTariffEntity that)) return false;
        return id == that.id && numberPhone.equals(that.numberPhone) && tariffIndex.equals(that.tariffIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberPhone, tariffIndex);
    }
}
