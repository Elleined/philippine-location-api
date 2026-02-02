DROP PROCEDURE IF EXISTS baranggay_find_all;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS baranggay_find_all(
    IN region_id BIGINT,
    IN province_id BIGINT,
    IN city_id BIGINT,
    IN name VARCHAR(50),
    IN page INT,
    IN size INT
)
COMMENT 'Get all baranggays with search, page, size'
BEGIN
	DECLARE offset INT;
	SET offset = (page - 1) * size;

    IF region_id IS NULL OR region_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF province_id IS NULL OR province_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'province id cannot be a negative number or null';
    END IF;

    IF city_id IS NULL OR city_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'city id cannot be a negative number or null';
    END IF;

    IF page IS NULL OR page <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page cannot be a negative number or null';
    END IF;

    IF size IS NULL OR size <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'size cannot be a negative number or null';
    END IF;

    SELECT
        b.id AS id,
        b.name AS name,
        r.id AS region_id,
        r.name AS region_name,
        p.id AS province_id,
        p.name AS province_name,
        c.id AS city_id,
        c.name AS city_name
    FROM baranggay b
    JOIN city c ON c.id = b.city_id AND c.id = city_id
    JOIN province p ON p.id = c.province_id AND p.id = province_id
    JOIN region r ON r.id = p.region_id AND r.id = region_id
    WHERE b.name LIKE CONCAT(IFNULL(name, ""), '%')
    ORDER BY b.name ASC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;