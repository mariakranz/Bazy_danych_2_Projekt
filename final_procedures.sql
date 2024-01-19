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

#Rezerwacja pokoju
CREATE PROCEDURE InsertBooking(
    IN p_ClientName VARCHAR(255),
    IN p_ClientSurname VARCHAR(255),
    IN p_PhoneNumber VARCHAR(15),
    IN p_Email VARCHAR(255),
    IN p_StartDate DATE,
    IN p_EndDate DATE,
    IN p_RoomID INT,
    OUT p_Success BOOLEAN
)
BEGIN
    DECLARE conflict_count INT;

    -- Sprawdzenie, czy istnieje już rezerwacja na dany pokój w podanym czasie
    SELECT COUNT(*) INTO conflict_count
    FROM bookings
    WHERE RoomID = p_RoomID
    AND ((StartDate >= p_StartDate AND StartDate < p_EndDate)
    OR (EndDate > p_StartDate AND EndDate <= p_EndDate)
    OR (StartDate <= p_StartDate AND EndDate >= p_EndDate));

    IF conflict_count > 0 THEN
        -- Jeśli istnieje konflikt, ustaw p_Success na false
        SET p_Success = FALSE;
    ELSE
        -- Wstawienie rezerwacji do bazy danych
        INSERT INTO bookings (ClientName, ClientSurname, PhoneNumber, Email, StartDate, EndDate, RoomID)
        VALUES (p_ClientName, p_ClientSurname, p_PhoneNumber, p_Email, p_StartDate, p_EndDate, p_RoomID);

        -- Jeśli nie ma konfliktu, ustaw p_Success na true
        SET p_Success = TRUE;
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


#GetEmlpoyeeInfo
DELIMITER //

CREATE PROCEDURE GetEmployeeInfoByID(
    IN p_EmployeeID INT,
    -- OUT p_EmployeeID_out INT,
    OUT p_EmployeeName VARCHAR(255),
    OUT p_EmployeeSurname VARCHAR(255),
    OUT p_EmployeePhone VARCHAR(9),
    OUT p_EmployeeEmail VARCHAR(255),
    OUT p_EmployeePrivilege INT,
    OUT p_DepartmentName VARCHAR(255)
)
BEGIN
    SELECT
        EmployeeID,
        EmployeeName,
        EmployeeSurname,
        EmployeePhone,
        EmployeeEmail,
        EmployeePrivilege,
        DepartmentName
    INTO
        p_EmployeeID,
        p_EmployeeName,
        p_EmployeeSurname,
        p_EmployeePhone,
        p_EmployeeEmail,
        p_EmployeePrivilege,
        p_DepartmentName
    FROM
        employeeinfo
    WHERE
        EmployeeId = p_EmployeeID;
END //

DELIMITER ;


#Logowanie
DELIMITER //

CREATE PROCEDURE AuthenticateUser(
    IN p_Login VARCHAR(255),
    IN p_Password VARCHAR(255),
    OUT p_EmployeeID INT
)
BEGIN
    SELECT EmployeeID INTO p_EmployeeID
    FROM logindata
    WHERE Login = p_Login AND Password = p_Password;
END //

DELIMITER ;

#Login
DELIMITER //

CREATE PROCEDURE UpdateLastLoginDate(
    IN p_EmployeeID INT
)
BEGIN
    UPDATE logindata
    SET LastLoginDate = CURRENT_DATE()
    WHERE EmployeeID = p_EmployeeID;
END //
DELIMITER ;

#filtrowanie
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `GetRoomsInfo`(
    IN filterType BINARY,
    IN bNumber INT,
    IN city VARCHAR(255),
    IN roomType VARCHAR(255)
)
BEGIN
    DECLARE filterCondition VARCHAR(255);

    SET filterCondition = '';

    -- Sprawdzanie poszczególnych bitów w filterType
    IF (filterType & b'100') = b'100' THEN
        SET filterCondition = CONCAT(filterCondition, ' AND BedsNumber = ', bNumber);
END IF;

    IF (filterType & b'010') = b'010' THEN
        SET filterCondition = CONCAT(filterCondition, ' AND City = "', city, '"');
END IF;

    IF (filterType & b'001') = b'001' THEN
        SET filterCondition = CONCAT(filterCondition, ' AND Type = "', roomType, '"');
END IF;

    -- Usuwanie pierwszego 'AND' z warunku
    IF LENGTH(filterCondition) > 0 THEN
        SET filterCondition = SUBSTRING(filterCondition, 5);

        -- Wykonanie zapytania z uwzględnieniem warunków
        SET @query = CONCAT('SELECT * FROM hotelsapp.roominfo WHERE ', filterCondition);

PREPARE stmt FROM @query;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;
ELSE
        -- Wykonanie zapytania bez warunków
SELECT * FROM hotelsapp.roominfo;
END IF;
END //

DELIMITER ;

#Dodaj pokój
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
