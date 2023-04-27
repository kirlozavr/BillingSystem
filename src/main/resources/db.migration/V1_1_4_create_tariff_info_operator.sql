CREATE SEQUENCE IF NOT EXISTS tariff_info_operator_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE tariff_info_operator
(
    id                       BIGINT NOT NULL,
    out_bet_another_operator FLOAT  NOT NULL,
    in_bet_another_operator  FLOAT  NOT NULL,
    CONSTRAINT pk_tariff_info_operator PRIMARY KEY (id)
);