CREATE TABLE tariff_info_tariff_locations
(
    tariff_info_entity_id    BIGINT NOT NULL,
    tariff_info_locations_id BIGINT NOT NULL
);

CREATE TABLE tariff_info_tariff_operators
(
    tariff_info_entity_id    BIGINT NOT NULL,
    tariff_info_operators_id BIGINT NOT NULL
);

ALTER TABLE tariff_info_location
    ADD name_location VARCHAR(255);

ALTER TABLE tariff_info_operator
    ADD name_operator VARCHAR(255);

ALTER TABLE tariff
    ADD target_operator VARCHAR(255);

ALTER TABLE tariff_info_tariff_info_locations
    ADD CONSTRAINT uc_tariff_info_tariff_info_locations_tariffinfolocations UNIQUE (tariff_info_locations_id);

ALTER TABLE tariff_info_tariff_info_operators
    ADD CONSTRAINT uc_tariff_info_tariff_info_operators_tariffinfooperators UNIQUE (tariff_info_operators_id);

ALTER TABLE tariff_info_tariff_info_locations
    ADD CONSTRAINT fk_tarinftarinfloc_on_tariff_info_entity FOREIGN KEY (tariff_info_entity_id) REFERENCES tariff_info (id);

ALTER TABLE tariff_info_tariff_info_locations
    ADD CONSTRAINT fk_tarinftarinfloc_on_tariff_info_location_entity FOREIGN KEY (tariff_info_locations_id) REFERENCES tariff_info_location (id);

ALTER TABLE tariff_info_tariff_info_operators
    ADD CONSTRAINT fk_tarinftarinfope_on_tariff_info_entity FOREIGN KEY (tariff_info_entity_id) REFERENCES tariff_info (id);

ALTER TABLE tariff_info_tariff_info_operators
    ADD CONSTRAINT fk_tarinftarinfope_on_tariff_info_operator_entity FOREIGN KEY (tariff_info_operators_id) REFERENCES tariff_info_operator (id);

ALTER TABLE tariff_info
DROP
CONSTRAINT fkhqqu317u6bvqlchjgdsv68d47;

ALTER TABLE tariff_info
DROP
CONSTRAINT fkjmdwubvjala2gnsc3fpc5uwhj;

ALTER TABLE tariff
DROP
COLUMN name_operator;

ALTER TABLE tariff_info
DROP
COLUMN tariff_info_location_id;

ALTER TABLE tariff_info
DROP
COLUMN tariff_info_operator_id;