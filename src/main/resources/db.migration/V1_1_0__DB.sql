CREATE SEQUENCE IF NOT EXISTS change_subscriber_tariff_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS payload_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS subscriber_id_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS tariff_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE change_subscriber_tariff
(
    id           BIGINT NOT NULL,
    number_phone VARCHAR(255),
    tariff_index VARCHAR(255),
    CONSTRAINT pk_change_subscriber_tariff PRIMARY KEY (id)
);

CREATE TABLE pay_transactions
(
    id           BIGINT NOT NULL,
    number_phone VARCHAR(255),
    money        FLOAT  NOT NULL,
    CONSTRAINT pk_pay_transactions PRIMARY KEY (id)
);

CREATE TABLE payload
(
    id         BIGINT NOT NULL,
    call_type  VARCHAR(255),
    start_time VARCHAR(255),
    end_time   VARCHAR(255),
    duration   VARCHAR(255),
    cost       FLOAT  NOT NULL,
    CONSTRAINT pk_payload PRIMARY KEY (id)
);

CREATE TABLE subscriber
(
    id           BIGINT NOT NULL,
    number_phone VARCHAR(255),
    tariff_index VARCHAR(255),
    balance      FLOAT  NOT NULL,
    CONSTRAINT pk_subscriber PRIMARY KEY (id)
);

CREATE TABLE tariff
(
    id                  BIGINT NOT NULL,
    tariff_index        VARCHAR(255),
    name_tariff         VARCHAR(255),
    minute_limit        FLOAT  NOT NULL,
    out_bet             FLOAT  NOT NULL,
    out_bet_after_limit FLOAT  NOT NULL,
    in_bet              FLOAT  NOT NULL,
    subscriber_payment  FLOAT  NOT NULL,
    monetary_unit       VARCHAR(255),
    CONSTRAINT pk_tariff PRIMARY KEY (id)
);