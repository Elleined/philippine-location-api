DELIMITER //
CREATE PROCEDURE province_get_all(
    IN p_region_id INT
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    SELECT *
    FROM province
    WHERE region_id = p_region_id
    ORDER BY name;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE province_get_all_paginated(
    IN p_region_id INT,
    IN p_page INT,
    IN p_size INT
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_page IS NULL OR p_page <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page number cannot be a negative number or null';
    END IF;

    IF p_size IS NULL OR p_size <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page size cannot be a negative number or null';
    END IF;

    SELECT *
    FROM province
    WHERE region_id = p_region_id
    ORDER BY name
    LIMIT p_size
    OFFSET p_page;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE province_get_all_total(
    IN p_region_id INT
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    SELECT COUNT(*)
    FROM province
    WHERE region_id = p_region_id;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE province_search_by_name(
    IN p_region_id INT,
    IN p_name VARCHAR(50)
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_name IS NULL OR TRIM(p_name) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be blank or null';
    END IF;

    SELECT *
    FROM province
    WHERE region_id = p_region_id
    AND name LIKE CONCAT('%', p_name, '%')
    ORDER BY name;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE province_search_by_name_paginated(
    IN p_region_id INT,
    IN p_name VARCHAR(50),
    IN p_page INT,
    IN p_size INT
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_name IS NULL OR TRIM(p_name) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be blank or null';
    END IF;

    IF p_page IS NULL OR p_page <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page number cannot be a negative number or null';
    END IF;

    IF p_size IS NULL OR p_size <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page size cannot be a negative number or null';
    END IF;

    SELECT *
    FROM province
    WHERE region_id = p_region_id
    AND name LIKE CONCAT('%', p_name, '%')
    ORDER BY name
    LIMIT p_size
    OFFSET p_page;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE province_search_by_name_total(
    IN p_region_id INT,
    IN p_name VARCHAR(50)
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_name IS NULL OR TRIM(p_name) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be blank or null';
    END IF;

    SELECT COUNT(*)
    FROM province
    WHERE region_id = p_region_id
    AND name LIKE CONCAT('%', p_name, '%');
END //
DELIMITER ;