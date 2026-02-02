DROP PROCEDURE IF EXISTS province_find_all;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS province_find_all(
    IN region_id BIGINT,
    IN name VARCHAR(50),
    IN page INT,
    IN size INT
)
COMMENT 'Get all provinces with search, page, and size'
BEGIN
	DECLARE offset INT;
	SET offset = (page - 1) * size;

    IF region_id IS NULL OR region_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF page IS NULL OR page <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page number cannot be a negative number or null';
    END IF;

    IF size IS NULL OR size <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page size cannot be a negative number or null';
    END IF;

    SELECT
        p.id AS id,
        p.name AS name,
        r.id AS region_id,
        r.name AS region_name
    FROM region r
    JOIN province p ON r.id = p.region_id AND p.region_id = region_id
    WHERE p.name LIKE CONCAT(IFNULL(name, ""), '%')
    ORDER BY p.name ASC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;