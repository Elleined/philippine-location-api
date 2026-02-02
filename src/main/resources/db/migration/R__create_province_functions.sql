DROP FUNCTION IF EXISTS province_find_all_total;
DELIMITER //
CREATE FUNCTION IF NOT EXISTS province_find_all_total(
    region_id BIGINT,
    name VARCHAR(50)
)
RETURNS INT DETERMINISTIC
COMMENT 'Get the total elements of province_find_all'
BEGIN
    DECLARE total_elements INT;

    IF region_id IS NULL OR region_id <= 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    SELECT COUNT(*)
    INTO total_elements
    FROM region r
    JOIN province p ON r.id = p.region_id AND p.region_id = region_id
    WHERE p.name LIKE CONCAT(IFNULL(name, ""), '%');

    RETURN total_elements;
END //
DELIMITER ;