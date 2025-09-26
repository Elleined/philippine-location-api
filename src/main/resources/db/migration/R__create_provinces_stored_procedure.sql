DROP PROCEDURE IF EXISTS province_find_all_by;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS province_find_all_by(
    IN region_id BIGINT,
    IN name VARCHAR(50),
    IN page INT,
    IN size INT
)
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

    SELECT *
    FROM province p
    WHERE p.region_id = region_id
    AND p.name LIKE CONCAT('%', IFNULL(name, ""), '%')
    ORDER BY p.name ASC
    LIMIT size
    OFFSET offset;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS province_find_all_by_total;
DELIMITER //
CREATE PROCEDURE IF NOT EXISTS province_find_all_by_total(
    IN region_id BIGINT,
    IN name VARCHAR(50)
)
BEGIN
    IF region_id IS NULL OR region_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    SELECT COUNT(*)
    FROM province p
    WHERE p.region_id = region_id
    AND p.name LIKE CONCAT('%', IFNULL(name, ""), '%');
END //
DELIMITER ;