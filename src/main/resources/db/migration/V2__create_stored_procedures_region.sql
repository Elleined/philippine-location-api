DELIMITER //
CREATE PROCEDURE region_get_all()
BEGIN
    SELECT *
    FROM region
    ORDER BY name;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE region_get_all_total()
BEGIN
    SELECT COUNT(*)
    FROM region;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE region_get_all_paginated(
    IN p_page INT,
    IN p_size INT
)
BEGIN
    IF p_page <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page number cannot be a negative number';
    END IF;

    IF p_size <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page size cannot be a negative number';
    END IF;

    SELECT *
    FROM region
    ORDER BY name
    LIMIT p_size
    OFFSET p_page;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE region_search_by_name(
    IN p_name VARCHAR(50)
)
BEGIN
    IF p_name IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be null';
    END IF;

    IF TRIM(p_name) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be blank';
    END IF;

    SELECT *
    FROM region
    WHERE name LIKE CONCAT('%', p_name, '%')
    ORDER BY name;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE region_search_by_name_paginated(
    IN p_name VARCHAR(50),
    IN p_page INT,
    IN p_size INT
)
BEGIN
    IF p_page <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page number cannot be a negative number';
    END IF;

    IF p_size <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page size cannot be a negative number';
    END IF;

    IF p_name IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be null';
    END IF;

    IF TRIM(p_name) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be blank';
    END IF;

    SELECT *
    FROM region
    WHERE name LIKE CONCAT('%', p_name, '%')
    ORDER BY name
    LIMIT p_size
    OFFSET p_page;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE region_search_by_total(
    IN p_name VARCHAR(50)
)
BEGIN
    IF p_name IS NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be null';
    END IF;

    IF TRIM(p_name) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be blank';
    END IF;

    SELECT COUNT(*)
    FROM region
    WHERE name LIKE CONCAT('%', p_name, '%');
END //
DELIMITER ;