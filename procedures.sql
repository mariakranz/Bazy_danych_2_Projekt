DELIMITER //

CREATE PROCEDURE GetDepartmentNames()
BEGIN
    SELECT name FROM hotelsapp.departments;
END //

DELIMITER ;