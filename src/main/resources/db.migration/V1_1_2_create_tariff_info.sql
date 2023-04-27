CREATE SEQUENCE IF NOT EXISTS tariff_info_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE tariff_info
(
    id                      BIGINT  NOT NULL,
    minute_limit            INTEGER NOT NULL,
    out_bet_before_limit    FLOAT   NOT NULL,
    out_bet_after_limit     FLOAT   NOT NULL,
    in_bet_before_limit     FLOAT   NOT NULL,
    in_bet_after_limit      FLOAT   NOT NULL,
    subscriber_payment      INTEGER NOT NULL,
    tariff_info_operator_id BIGINT,
    tariff_info_location_id BIGINT,
    CONSTRAINT pk_tariff_info PRIMARY KEY (id)
);

ALTER TABLE tariff_info
    ADD CONSTRAINT FK_TARIFF_INFO_ON_TARIFFINFOLOCATION FOREIGN KEY (tariff_info_location_id) REFERENCES tariff_info_location (id);

ALTER TABLE tariff_info
    ADD CONSTRAINT FK_TARIFF_INFO_ON_TARIFFINFOOPERATOR FOREIGN KEY (tariff_info_operator_id) REFERENCES tariff_info_operator (id);