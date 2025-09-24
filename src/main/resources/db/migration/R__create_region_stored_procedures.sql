DROP PROCEDURE IF EXISTS region_find_all_by;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS region_find_all_by(
    IN name VARCHAR(50),
    IN page INT,
    IN size INT
)
BEGIN
	DECLARE offset INT;
	SET offset = (page - 1) * size;

    IF page IS NULL OR page <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page number cannot be a negative number or null';
    END IF;

    IF size IS NULL OR size <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page size cannot be a negative number or null';
    END IF;

    SELECT *
    FROM region r
    WHERE r.name LIKE CONCAT('%', IFNULL(name, ""), '%')
    ORDER BY r.name ASC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS region_find_all_by_total;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS region_find_all_by_total(
    IN name VARCHAR(50)
)
BEGIN

    SELECT COUNT(*)
    FROM region r
    WHERE r.name LIKE CONCAT('%', IFNULL(name, ""), '%');
END //
DELIMITER ;