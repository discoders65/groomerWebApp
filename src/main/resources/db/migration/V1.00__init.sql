BEGIN;

CREATE SCHEMA IF NOT EXISTS groomer

    AUTHORIZATION postgres;

COMMENT ON SCHEMA groomer
    IS 'standard public schema';

GRANT ALL ON SCHEMA groomer TO PUBLIC;

GRANT ALL ON SCHEMA groomer TO postgres;
DROP EXTENSION IF EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS groomer."groomer_user"
(
    id uuid DEFAULT uuid_generate_v4() NOT NULL,
    name character varying(32) NOT NULL,
    user_name character varying(32) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    mobile integer NOT NULL,
    picture bytea,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS groomer."appointment"
(
    id uuid DEFAULT uuid_generate_v4() NOT NULL,
    date_start timestamp NOT NULL,
    date_end timestamp NOT NULL,
    comment character varying,
    user_id uuid NOT NULL,
    pricing numeric(4)
);

CREATE TABLE IF NOT EXISTS groomer."message"
(
    id uuid DEFAULT uuid_generate_v4() NOT NULL,
    id_sender uuid NOT NULL,
    id_recipent uuid NOT NULL,
    send_date timestamp without time zone NOT NULL,
    content text NOT NULL,
    is_read boolean NOT NULL,
    PRIMARY KEY (id_sender)
);


ALTER TABLE IF EXISTS groomer."appointment"
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
    REFERENCES groomer."groomer_user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS groomer."message"
    ADD CONSTRAINT fk_id_sender FOREIGN KEY (id_sender)
    REFERENCES groomer."groomer_user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS groomer."message"
    ADD CONSTRAINT fk_id_recipent FOREIGN KEY (id_recipent)
    REFERENCES groomer."groomer_user" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;