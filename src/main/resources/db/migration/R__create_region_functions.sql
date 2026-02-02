DROP FUNCTION IF EXISTS region_find_all_total;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS region_find_all_total(name VARCHAR(50))
RETURNS INT DETERMINISTIC
COMMENT 'Get the total elements of region_find_all'
BEGIN
    DECLARE total_elements INT;

    SELECT COUNT(*)
    INTO total_elements
    FROM region r
    WHERE r.name LIKE CONCAT(IFNULL(name, ""), '%');

    RETURN total_elements;
END //
DELIMITER ;