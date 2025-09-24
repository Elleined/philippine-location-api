DROP PROCEDURE IF EXISTS baranggay_get_all_by;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS baranggay_get_all_by(
    IN region_id INT,
    IN province_id INT,
    IN city_id INT,
    IN name VARCHAR(255),
    IN page INT,
    IN size INT
)
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

    SELECT b.*
    FROM baranggay b
    JOIN city c ON c.id = b.city_id
    JOIN province p ON p.id = c.province_id
    JOIN region r ON r.id = p.region_id
    WHERE r.id = region_id
    AND p.id = province_id
    AND c.id = city_id
    AND b.name LIKE CONCAT('%', IFNULL(name, ""), '%')
    ORDER BY b.name ASC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS baranggay_find_all_by_total;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS baranggay_get_all_by(
    IN region_id INT,
    IN province_id INT,
    IN city_id INT,
    IN name VARCHAR(255)
)
BEGIN
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
    FROM baranggay b
    JOIN city c ON c.id = b.city_id
    JOIN province p ON p.id = c.province_id
    JOIN region r ON r.id = p.region_id
    WHERE r.id = region_id
    AND p.id = province_id
    AND c.id = city_id
    AND b.name LIKE CONCAT('%', IFNULL(name, ""), '%');
END //
DELIMITER ;