CREATE TABLE change_subscriber_tariff
(
    id           BIGINT NOT NULL,
    number_phone VARCHAR(255),
    tariff_index VARCHAR(255),
    CONSTRAINT pk_change_subscriber_tariff PRIMARY KEY (id)
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

CREATE TABLE payment
(
    id           BIGINT NOT NULL,
    number_phone VARCHAR(255),
    money        FLOAT  NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

CREATE TABLE subscriber
(
    id           BIGINT NOT NULL,
    number_phone VARCHAR(255),
    tariff_index VARCHAR(255),
    balance      FLOAT  NOT NULL,
    CONSTRAINT pk_subscriber PRIMARY KEY (id)
);

CREATE TABLE subscriber_report
(
    id            BIGINT NOT NULL,
    number_phone  VARCHAR(255),
    tariff_index  VARCHAR(255),
    total_cost    FLOAT  NOT NULL,
    monetary_unit VARCHAR(255),
    CONSTRAINT pk_subscriber_report PRIMARY KEY (id)
);

CREATE TABLE subscriber_report_payloads
(
    subscriber_report_entity_id BIGINT NOT NULL,
    payloads_id                 BIGINT NOT NULL
);

CREATE TABLE tariff
(
    id                   BIGINT  NOT NULL,
    tariff_index         VARCHAR(255),
    name_tariff          VARCHAR(255),
    minute_limit         INTEGER NOT NULL,
    out_bet_before_limit FLOAT   NOT NULL,
    out_bet_after_limit  FLOAT   NOT NULL,
    in_bet_before_limit  FLOAT   NOT NULL,
    in_bet_after_limit   FLOAT   NOT NULL,
    subscriber_payment   INTEGER NOT NULL,
    monetary_unit        VARCHAR(255),
    CONSTRAINT pk_tariff PRIMARY KEY (id)
);

ALTER TABLE subscriber_report_payloads
    ADD CONSTRAINT uc_subscriber_report_payloads_payloads UNIQUE (payloads_id);

ALTER TABLE subscriber_report_payloads
    ADD CONSTRAINT fk_subreppay_on_payload_entity FOREIGN KEY (payloads_id) REFERENCES payload (id);

ALTER TABLE subscriber_report_payloads
    ADD CONSTRAINT fk_subreppay_on_subscriber_report_entity FOREIGN KEY (subscriber_report_entity_id) REFERENCES subscriber_report (id);