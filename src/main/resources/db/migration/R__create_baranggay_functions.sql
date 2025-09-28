DROP FUNCTION IF EXISTS baranggay_find_all_by_total;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS baranggay_find_all_by_total(
    region_id BIGINT,
    province_id BIGINT,
    city_id BIGINT,
    name VARCHAR(50)
)
RETURNS INT DETERMINISTIC
COMMENT 'Get total elements of baranggay_find_all_by'
BEGIN
    DECLARE total_elements INT;

    IF region_id IS NULL OR region_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF province_id IS NULL OR province_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'province id cannot be a negative number or null';
    END IF;

    IF city_id IS NULL OR city_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'city id cannot be a negative number or null';
    END IF;

    SELECT COUNT(*)
    INTO total_elements
    FROM baranggay b
    JOIN city c ON c.id = b.city_id AND c.id = city_id
    JOIN province p ON p.id = c.province_id AND p.id = province_id
    JOIN region r ON r.id = p.region_id AND r.id = region_id
    WHERE b.name LIKE CONCAT('%', IFNULL(name, ""), '%');

    RETURN total_elements;
END //
DELIMITER ;