CREATE INDEX idx_name ON region(name);

CREATE INDEX idx_name ON province(name);
CREATE INDEX idx_region_id_name ON province(region_id, name);

CREATE INDEX idx_name ON city(name);
CREATE INDEX idx_province_id_name ON city(province_id, name);

CREATE INDEX idx_name ON baranggay(name);
CREATE INDEX idx_city_id_name ON baranggay(city_id, name);
