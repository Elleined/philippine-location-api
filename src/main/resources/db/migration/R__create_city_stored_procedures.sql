DROP PROCEDURE IF EXISTS city_find_all_by;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS city_find_all_by(
    IN region_id BIGINT,
    IN province_id BIGINT,
    IN name VARCHAR(50),
    IN page INT,
    IN size INT
)
COMMENT 'Get all cities with search, page, and size'
BEGIN
	DECLARE offset INT;
	SET offset = (page - 1) * size;

    IF region_id IS NULL OR region_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF province_id IS NULL OR province_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'province id cannot be a negative number or null';
    END IF;

    IF page IS NULL OR page <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page number cannot be a negative number or null';
    END IF;

    IF size IS NULL OR size <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page size cannot be a negative number or null';
    END IF;

    SELECT
        c.id AS id,
        c.name AS name,
        c.province_id AS province_id
    FROM city c
    JOIN province p ON p.id = c.province_id AND p.id = province_id
    JOIN region r ON r.id = p.region_id AND r.id = region_id
    WHERE c.name LIKE CONCAT('%', IFNULL(name, ""), '%')
    ORDER BY c.name ASC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;