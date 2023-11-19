BEGIN;


CREATE TABLE IF NOT EXISTS groomer."User"
(
    id uuid NOT NULL,
    login character varying(32) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    mobile integer NOT NULL,
    picture bytea,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS groomer."Appointment"
(
    id uuid NOT NULL,
    date_start date NOT NULL,
    date_end date NOT NULL,
    comment character varying,
    user_id uuid NOT NULL,
    pricing numeric(4)
);

CREATE TABLE IF NOT EXISTS groomer."Messages"
(
    id uuid NOT NULL,
    id_sender uuid NOT NULL,
    id_recipent uuid NOT NULL,
    send_date timestamp without time zone NOT NULL,
    content text NOT NULL,
    is_read boolean NOT NULL,
    PRIMARY KEY (id_sender)
);


ALTER TABLE IF EXISTS groomer."Appointment"
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id)
    REFERENCES groomer."User" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS groomer."Messages"
    ADD CONSTRAINT fk_id_sender FOREIGN KEY (id_sender)
    REFERENCES groomer."User" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS groomer."Messages"
    ADD CONSTRAINT fk_id_recipent FOREIGN KEY (id_recipent)
    REFERENCES groomer."User" (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;