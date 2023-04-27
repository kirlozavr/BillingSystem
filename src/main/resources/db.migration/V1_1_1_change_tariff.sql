ALTER TABLE tariff
    ADD name_operator VARCHAR(255);

ALTER TABLE tariff
    ADD target_location VARCHAR(255);

ALTER TABLE tariff
    ADD tariff_info_id BIGINT;

ALTER TABLE tariff
    ADD CONSTRAINT FK_TARIFF_ON_TARIFFINFO FOREIGN KEY (tariff_info_id) REFERENCES tariff_info (id);

ALTER TABLE tariff
DROP
COLUMN in_bet_after_limit;

ALTER TABLE tariff
DROP
COLUMN in_bet_before_limit;

ALTER TABLE tariff
DROP
COLUMN minute_limit;

ALTER TABLE tariff
DROP
COLUMN out_bet_after_limit;

ALTER TABLE tariff
DROP
COLUMN out_bet_before_limit;

ALTER TABLE tariff
DROP
COLUMN subscriber_payment;