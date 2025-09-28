DROP PROCEDURE IF EXISTS region_find_all_by;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS region_find_all_by(
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
    WHERE r.name LIKE CONCAT('%', IFNULL(name, ""), '%')
    ORDER BY r.name ASC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;

DROP FUNCTION IF EXISTS region_find_all_by_total;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS region_find_all_by_total(name VARCHAR(50))
RETURNS INT DETERMINISTIC
COMMENT 'Get the total elements of region_find_all_by'
BEGIN
    DECLARE total_elements INT;

    SELECT COUNT(*)
    INTO total_elements
    FROM region r
    WHERE r.name LIKE CONCAT('%', IFNULL(name, ""), '%');

    RETURN total_elements;
END //
DELIMITER ;