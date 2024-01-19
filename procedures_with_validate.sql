#DESCRIPTIONS
#descriptions get
DELIMITER //

CREATE PROCEDURE GetDescriptionsValidatePrivilege(IN employeeID INT)
BEGIN
    DECLARE employeePrivilege INT;

    -- Sprawdzenie uprawnień pracownika
    SELECT privilege INTO employeePrivilege FROM hotelsapp.employees WHERE id = employeeID;

    -- Wykonanie SELECT tylko jeśli employee ma privilege > 1
    IF employeePrivilege >= 1 THEN
        SELECT * FROM hotelsapp.descriptions;
    END IF;
END //

DELIMITER ;


#descriptionInsert
DELIMITER //

CREATE PROCEDURE InsertDescriptionValidatePrivilege(
	IN p_EmployeeID INT,
    IN p_DescriptionText VARCHAR(255)
)
BEGIN
    DECLARE employeePrivilege INT;

    -- Sprawdzenie uprawnień pracownika
    SELECT privilege INTO employeePrivilege FROM hotelsapp.employees WHERE id = p_EmployeeID;

    -- Wykonanie INSERT tylko jeśli employee ma privilege >= 3
    IF employeePrivilege >= 3 THEN
        INSERT INTO descriptions (Description)
        VALUES (p_DescriptionText);
    END IF;
END //

DELIMITER ;

#Modyfikacja opisu----------------------NIEZAIMPLEMENTOWANO

#Uswanie opisu
DELIMITER //

CREATE PROCEDURE DeleteDescriptionValidate(
	IN p_EmployeeID INT,
    IN p_DescriptionID INT
)
BEGIN
    DECLARE employeePrivilege INT;

    -- Sprawdzenie uprawnień pracownika
    SELECT privilege INTO employeePrivilege FROM hotelsapp.employees WHERE id = p_EmployeeID;

    -- Wykonanie DELETE tylko jeśli employee ma privilege >= 3
    IF employeePrivilege >= 3 THEN
        DELETE FROM descriptions
        WHERE id = p_DescriptionID;
    END IF;
END //

DELIMITER ;


#Buildings
#Wyswietelnie wszystkich budynków
#Dodanie budynku
DELIMITER //

CREATE PROCEDURE InsertBuildingValidate(
    IN p_City VARCHAR(255),
    IN p_Street VARCHAR(255),
    IN p_DescriptionID INT,
    IN p_EmployeeID INT
)
BEGIN
    DECLARE employeePrivilege INT;

    -- Sprawdzenie uprawnień pracownika
    SELECT privilege INTO employeePrivilege FROM hotelsapp.employees WHERE id = p_EmployeeID;

    -- Wykonanie INSERT tylko jeśli employee ma privilege >= 3
    IF employeePrivilege >= 3 THEN
        INSERT INTO buildings (City, Street, DescriptionID)
        VALUES (p_City, p_Street, p_DescriptionID);
    END IF;
END //

DELIMITER ;

#Modyfikacja budynku (opisu)
DELIMITER //

CREATE PROCEDURE UpdateBuildingDescriptionValidate(
    IN p_BuildingID INT,
    IN p_NewDescriptionID INT,
    IN p_EmployeeID INT
)
BEGIN
    DECLARE employeePrivilege INT;

    -- Sprawdzenie uprawnień pracownika
    SELECT privilege INTO employeePrivilege FROM hotelsapp.employees WHERE id = p_EmployeeID;

    -- Wykonanie UPDATE tylko jeśli employee ma privilege >= 3
    IF employeePrivilege >= 3 THEN
        UPDATE buildings
        SET DescriptionID = p_NewDescriptionID
        WHERE id = p_BuildingID;
    END IF;
END //

DELIMITER ;

#Usunięcie budynku----------------------NIEZAIMPLEMENTOWANO

#ROOMS
#dodaj pokój
DELIMITER //

CREATE PROCEDURE InsertRoomValidate(
    IN p_RoomNumber INT,
    IN p_RoomType VARCHAR(255),
    IN p_BedsNumber INT,
    IN p_BuildingID INT,
    IN p_DescriptionID INT,
    IN p_EmployeeID INT
)
BEGIN
    DECLARE employeePrivilege INT;

    -- Sprawdzenie uprawnień pracownika
    SELECT privilege INTO employeePrivilege FROM hotelsapp.employees WHERE id = p_EmployeeID;

    -- Wykonanie INSERT tylko jeśli employee ma privilege >= 3
    IF employeePrivilege >= 3 THEN
        INSERT INTO rooms (Number, Type, BedsNumber, BuildingID, DescriptionID)
        VALUES (p_RoomNumber, p_RoomType, p_BedsNumber, p_BuildingID, p_DescriptionID);
    END IF;
END //

DELIMITER ;

#Usuń pokój
DELIMITER //

CREATE PROCEDURE DeleteRoomValidate(
    IN p_RoomID INT,
    IN p_EmployeeID INT
)
BEGIN
    DECLARE employeePrivilege INT;

    -- Sprawdzenie uprawnień pracownika
    SELECT privilege INTO employeePrivilege FROM hotelsapp.employees WHERE id = p_EmployeeID;

    -- Wykonanie DELETE tylko jeśli employee ma privilege >= 3
    IF employeePrivilege >= 3 THEN
        DELETE FROM rooms
        WHERE id = p_RoomID;
    END IF;
END //

DELIMITER ;

#Zmodyfikuj pokój----------------------NIEZAIMPLEMENTOWANO

#Wyświetl pokoje----------------------NIEZAIMPLEMENTOWANO


#BOOKINGS
#wyswietl
DELIMITER //

CREATE PROCEDURE GetBookings()
BEGIN
	SELECT * FROM hotelsapp.bookings;
END //

DELIMITER ;
#modyfikuj rezerwacje (email)
DELIMITER //

CREATE PROCEDURE UpdateBookingEmailValidate(
    IN p_BookingID INT,
    IN p_NewEmail VARCHAR(255),
    IN p_EmployeeID INT
)
BEGIN
    DECLARE employeePrivilege INT;

    -- Sprawdzenie uprawnień pracownika
    SELECT privilege INTO employeePrivilege FROM hotelsapp.employees WHERE id = p_EmployeeID;

    -- Wykonanie UPDATE tylko jeśli employee ma privilege >= 2
    IF employeePrivilege >= 2 THEN
        UPDATE bookings
        SET Email = p_NewEmail
        WHERE id = p_BookingID;
    END IF;
END //

DELIMITER ;


#Usuń rezerwacje
DELIMITER //

CREATE PROCEDURE DeleteBookingValidate(
    IN p_BookingID INT,
    IN p_EmployeeID INT
)
BEGIN
    DECLARE employeePrivilege INT;

    -- Sprawdzenie uprawnień pracownika
    SELECT privilege INTO employeePrivilege FROM hotelsapp.employees WHERE id = p_EmployeeID;

    -- Wykonanie DELETE tylko jeśli employee ma privilege >= 2
    IF employeePrivilege >= 2 THEN
        DELETE FROM bookings
        WHERE id = p_BookingID;
    END IF;
END //

DELIMITER ;


#EMPLOYEES
#Dodaj pracownika

#DEPARTMENTS
#Dodaj departament

#EMPLOYEEINFO
#wyswietl employeeinfo
