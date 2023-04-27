CREATE TABLE tariff_info_tariff_info_locations
(
    tariff_info_entity_id    BIGINT NOT NULL,
    tariff_info_locations_id BIGINT NOT NULL
);

CREATE TABLE tariff_info_tariff_info_operators
(
    tariff_info_entity_id    BIGINT NOT NULL,
    tariff_info_operators_id BIGINT NOT NULL
);

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