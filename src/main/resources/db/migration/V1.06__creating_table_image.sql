CREATE TABLE IF NOT EXISTS groomer."image"
(
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    name character varying(255),
    type character varying(255),
    user_id UUID,
    image_data BYTEA,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS groomer."image"
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
    REFERENCES groomer."groomer_user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;