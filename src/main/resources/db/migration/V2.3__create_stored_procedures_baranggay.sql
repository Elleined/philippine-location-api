DELIMITER //
CREATE PROCEDURE baranggay_get_all(
    IN p_region_id INT,
    IN p_province_id INT,
    IN p_city_id INT
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_province_id IS NULL OR p_province_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'province id cannot be a negative number or null';
    END IF;

    IF p_city_id IS NULL OR p_city_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'city id cannot be a negative number or null';
    END IF;

    SELECT b.*
    FROM baranggay b
    JOIN city c ON c.id = b.city_id
    JOIN province p ON p.id = c.province_id
    JOIN region r ON r.id = p.region_id
    WHERE r.id = p_region_id
    AND p.id = p_province_id
    AND c.id = p_city_id
    ORDER BY b.name;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE baranggay_get_all_paginated(
    IN p_region_id INT,
    IN p_province_id INT,
    IN p_city_id INT,
    IN p_page INT,
    IN p_size INT
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_province_id IS NULL OR p_province_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'province id cannot be a negative number or null';
    END IF;

    IF p_city_id IS NULL OR p_city_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'city id cannot be a negative number or null';
    END IF;

    IF p_page IS NULL OR p_page <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page number cannot be a negative number or null';
    END IF;

    IF p_size IS NULL OR p_size <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'page size cannot be a negative number or null';
    END IF;

    SELECT b.*
    FROM baranggay b
    JOIN city c ON c.id = b.city_id
    JOIN province p ON p.id = c.province_id
    JOIN region r ON r.id = p.region_id
    WHERE r.id = p_region_id
    AND p.id = p_province_id
    AND c.id = p_city_id
    ORDER BY b.name
    LIMIT p_size
    OFFSET p_page;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE baranggay_get_all_total(
    IN p_region_id INT,
    IN p_province_id INT,
    IN p_city_id INT
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_province_id IS NULL OR p_province_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'province id cannot be a negative number or null';
    END IF;

    IF p_city_id IS NULL OR p_city_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'city id cannot be a negative number or null';
    END IF;

    SELECT COUNT(*)
    FROM baranggay b
    JOIN city c ON c.id = b.city_id
    JOIN province p ON p.id = c.province_id
    JOIN region r ON r.id = p.region_id
    WHERE r.id = p_region_id
    AND p.id = p_province_id
    AND c.id = p_city_id;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE baranggay_search_by_name(
    IN p_region_id INT,
    IN p_province_id INT,
    IN p_city_id INT,
    IN p_name VARCHAR(50)
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_province_id IS NULL OR p_province_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'province id cannot be a negative number or null';
    END IF;

    IF p_city_id IS NULL OR p_city_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'city id cannot be a negative number or null';
    END IF;

    IF p_name IS NULL OR TRIM(p_name) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be blank or null';
    END IF;

    SELECT b.*
    FROM baranggay b
    JOIN city c ON c.id = b.city_id
    JOIN province p ON p.id = c.province_id
    JOIN region r ON r.id = p.region_id
    WHERE r.id = p_region_id
    AND p.id = p_province_id
    AND c.id = p_city_id
    AND b.name LIKE CONCAT('%', p_name, '%')
    ORDER BY b.name;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE baranggay_search_by_name_paginated(
    IN p_region_id INT,
    IN p_province_id INT,
    IN p_city_id INT,
    IN p_name VARCHAR(50),
    IN p_page INT,
    IN p_size INT
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_province_id IS NULL OR p_province_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'province id cannot be a negative number or null';
    END IF;

    IF p_city_id IS NULL OR p_city_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'city id cannot be a negative number or null';
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

    SELECT b.*
    FROM baranggay b
    JOIN city c ON c.id = b.city_id
    JOIN province p ON p.id = c.province_id
    JOIN region r ON r.id = p.region_id
    WHERE r.id = p_region_id
    AND p.id = p_province_id
    AND c.id = p_city_id
    AND b.name LIKE CONCAT('%', p_name, '%')
    ORDER BY b.name
    LIMIT p_size
    OFFSET p_page;
END //
DELIMITER ;


DELIMITER //
CREATE PROCEDURE baranggay_search_by_name_total(
    IN p_region_id INT,
    IN p_province_id INT,
    IN p_city_id INT,
    IN p_name VARCHAR(50)
)
BEGIN
    IF p_region_id IS NULL OR p_region_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'region id cannot be a negative number or null';
    END IF;

    IF p_province_id IS NULL OR p_province_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'province id cannot be a negative number or null';
    END IF;

    IF p_city_id IS NULL OR p_city_id <= -1 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'city id cannot be a negative number or null';
    END IF;

    IF p_name IS NULL OR TRIM(p_name) = '' THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'name cannot be blank or null';
    END IF;

    SELECT COUNT(*)
    FROM baranggay b
    JOIN city c ON c.id = b.city_id
    JOIN province p ON p.id = c.province_id
    JOIN region r ON r.id = p.region_id
    WHERE r.id = p_region_id
    AND p.id = p_province_id
    AND c.id = p_city_id
    AND b.name LIKE CONCAT('%', p_name, '%');
END //
DELIMITER ;