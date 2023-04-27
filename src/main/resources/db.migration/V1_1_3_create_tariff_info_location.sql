CREATE SEQUENCE IF NOT EXISTS tariff_info_location_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE tariff_info_location
(
    id                       BIGINT NOT NULL,
    out_bet_another_location FLOAT  NOT NULL,
    in_bet_another_location  FLOAT  NOT NULL,
    CONSTRAINT pk_tariff_info_location PRIMARY KEY (id)
);