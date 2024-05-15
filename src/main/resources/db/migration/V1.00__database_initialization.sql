BEGIN;

-- Create schema
CREATE SCHEMA IF NOT EXISTS groomer AUTHORIZATION groomer;

-- Add comments to schema
COMMENT ON SCHEMA groomer IS 'standard public schema';

-- Grant privileges on schema
GRANT ALL ON SCHEMA groomer TO PUBLIC;
GRANT ALL ON SCHEMA groomer TO groomer;

-- Ensure uuid-ossp extension is available
DROP EXTENSION IF EXISTS "uuid-ossp";
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Create groomer_user table
CREATE TABLE IF NOT EXISTS groomer."groomer_user"
(
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    name VARCHAR(32) NOT NULL,
    user_name VARCHAR(32) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    mobile INTEGER NOT NULL,
    PRIMARY KEY (id)
);

-- Create appointment table
CREATE TABLE IF NOT EXISTS groomer."appointment"
(
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    date_start TIMESTAMP NOT NULL,
    date_end TIMESTAMP,
    comment VARCHAR,
    user_id UUID NOT NULL,
    pricing DECIMAL(10, 2),
    accepted BOOLEAN NOT NULL DEFAULT false,
    cancelled BOOLEAN NOT NULL DEFAULT false,
    employee_id UUID ,
    PRIMARY KEY (id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES groomer."groomer_user" (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_employee_id FOREIGN KEY (employee_id)
        REFERENCES groomer."groomer_user" (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Create image table
CREATE TABLE IF NOT EXISTS groomer."image"
(
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    name VARCHAR(255),
    type VARCHAR(255),
    user_id UUID,
    image_data OID,
    PRIMARY KEY (id),
    CONSTRAINT unique_user_id UNIQUE (user_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES groomer."groomer_user" (id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Create utility table
CREATE TABLE IF NOT EXISTS groomer."utility"
(
    id UUID DEFAULT uuid_generate_v4() NOT NULL,
    name VARCHAR(255),
    description VARCHAR(255),
    low_price NUMERIC(10, 2),
    up_price NUMERIC(10, 2),
    execution_time TIME,
    PRIMARY KEY (id)
);

-- Create appointment_utility table
CREATE TABLE IF NOT EXISTS groomer."appointment_utility"
(
    appointment_id UUID NOT NULL,
    utility_id UUID NOT NULL,
    PRIMARY KEY (appointment_id, utility_id),
    CONSTRAINT fk_appointment_id FOREIGN KEY (appointment_id)
        REFERENCES groomer."appointment" (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_utility_id FOREIGN KEY (utility_id)
        REFERENCES groomer."utility" (id)
        ON DELETE CASCADE
);

END;
