ALTER TABLE IF EXISTS groomer."appointment"
    ADD COLUMN employee_id UUID;

ALTER TABLE IF EXISTS groomer."appointment"
    ADD CONSTRAINT fk_employee_id FOREIGN KEY (employee_id) REFERENCES groomer."groomer_user" (id);

ALTER TABLE IF EXISTS groomer."appointment"
    ADD PRIMARY KEY (id);

CREATE TABLE groomer."service" (
    id UUID PRIMARY KEY,
    type VARCHAR(255),
    description VARCHAR(255),
    low_price NUMERIC(10,2),
    up_price NUMERIC(10,2),
    execution_time TIME
);

CREATE TABLE groomer."appointment_service" (
    appointment_id UUID NOT NULL,
    service_id UUID NOT NULL,
    PRIMARY KEY (appointment_id, service_id),
    FOREIGN KEY (appointment_id) REFERENCES groomer."appointment" (id) ON DELETE CASCADE,
    FOREIGN KEY (service_id) REFERENCES groomer."service" (id) ON DELETE CASCADE
);

