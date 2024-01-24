ALTER TABLE IF EXISTS groomer."appointment" ADD COLUMN IF NOT EXISTS cancelled boolean NOT NULL DEFAULT false;
