
ALTER TABLE IF EXISTS groomer."image"
DROP COLUMN image_data;

ALTER TABLE IF EXISTS groomer."image"
ADD COLUMN image_data oid;