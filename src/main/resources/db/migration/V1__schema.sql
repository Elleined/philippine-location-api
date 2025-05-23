CREATE TABLE IF NOT EXISTS region (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
description VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS province (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
region_id BIGINT NOT NULL,
FOREIGN KEY (region_id) REFERENCES region(id)
);

CREATE TABLE IF NOT EXISTS city (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
province_id BIGINT NOT NULL,
FOREIGN KEY (province_id) REFERENCES province(id)
);

CREATE TABLE IF NOT EXISTS baranggay (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
city_id BIGINT NOT NULL,
FOREIGN KEY (city_id) REFERENCES city(id)
);
