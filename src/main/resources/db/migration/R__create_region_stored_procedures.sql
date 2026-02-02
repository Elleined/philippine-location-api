DROP PROCEDURE IF EXISTS region_find_all;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS region_find_all(
    IN name VARCHAR(50),
    IN page INT,
    IN size INT
)
COMMENT 'Get all regions with search, page, and size'
BEGIN
	DECLARE offset INT;
	SET offset = (page - 1) * size;

    IF page IS NULL OR page <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page number cannot be a negative number or null';
    END IF;

    IF size IS NULL OR size <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page size cannot be a negative number or null';
    END IF;

    SELECT
        r.id AS id,
        r.name AS name,
        r.description AS description
    FROM region r
    WHERE r.name LIKE CONCAT(IFNULL(name, ""), '%')
    ORDER BY r.name ASC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;