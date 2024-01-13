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







DELIMITER //
CREATE PROCEDURE GetDepartmentNames()
BEGIN
    SELECT name FROM hotelsapp.departments;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE GetDepartments()
BEGIN
    SELECT * FROM hotelsapp.departments;
END //



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

CALL GetEmployeeInfoByID(1, @EmployeeName, @EmployeeSurname, @EmployeePhone, @EmployeeEmail, @EmployeePrivilege, @DepartmentName);
SELECT @EmployeeID, @EmployeeName, @EmployeeSurname, @EmployeePhone, @EmployeeEmail, @EmployeePrivilege, @DepartmentName;

#GetRoomInfo
DELIMITER //

CREATE PROCEDURE GetRoomInfoByID(
    IN p_RoomID INT,
    OUT p_Number INT,
    OUT p_Type VARCHAR(255),
    OUT p_BedsNumber INT,
    OUT p_RoomDescription VARCHAR(255),
    OUT p_City VARCHAR(255),
    OUT p_Street VARCHAR(255),
    OUT p_BuildingDescription VARCHAR(255),
    OUT p_RoomCount BIGINT
)
BEGIN
    SELECT
        RoomID,
        Number,
        Type,
        BedsNumber,
        RoomDescription,
        City,
        Street,
        BuildingDescription,
        RoomCount
    INTO
        p_RoomID,
        p_Number,
        p_Type,
        p_BedsNumber,
        p_RoomDescription,
        p_City,
        p_Street,
        p_BuildingDescription,
        p_RoomCount
    FROM
        RoomInfo
    WHERE
        RoomID = p_RoomID;
END //

DELIMITER ;




DELIMITER ;

CALL GetDepartments();

DELIMITER //

CREATE PROCEDURE GetDepartmentsV2()
BEGIN
    SELECT id, Name, ManagerID FROM hotelsapp.departments;
END //

DELIMITER ;
CALL GetDepartments();

DELIMITER //

CREATE PROCEDURE GetDescriptions()
BEGIN
    SELECT * FROM hotelsapp.descriptions;
END //

DELIMITER ;

call GetDescriptions();

#procedury z pliku "Operacje Use-case.sql"

#Przeglądanie listy pokojów
-- DELIMITER //
-- CREATE PROCEDURE GetRoomsInfo()
-- BEGIN
--     Select * from hotelsapp.roominfo;
-- END //
-- DELIMITER ;

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

#Modyfikacja  rezerwacji
DELIMITER //

CREATE PROCEDURE UpdateBookingEmail(
    IN p_BookingID INT,
    IN p_NewEmail VARCHAR(255)
)
BEGIN
    UPDATE bookings
    SET Email = p_NewEmail
    WHERE id = p_BookingID;
END //

DELIMITER ;

#Usuwanie rezerwacji
DELIMITER //

CREATE PROCEDURE DeleteBooking(
    IN p_BookingID INT
)
BEGIN
    DELETE FROM bookings
    WHERE id = p_BookingID;
END //

DELIMITER ;

#Dodanie opisu
DELIMITER //

CREATE PROCEDURE InsertDescription(
    IN p_DescriptionText VARCHAR(255)
)
BEGIN
    INSERT INTO descriptions (Description)
    VALUES (p_DescriptionText);
END //

DELIMITER ;

#Modyfikacja opisu
DELIMITER //

CREATE PROCEDURE UpdateDescription(
    IN p_DescriptionID INT,
    IN p_NewDescriptionText VARCHAR(255)
)
BEGIN
    UPDATE descriptions
    SET Description = p_NewDescriptionText
    WHERE id = p_DescriptionID;
END //

DELIMITER ;

#Usuwanie opisu
DELIMITER //

CREATE PROCEDURE DeleteDescription(
    IN p_DescriptionID INT
)
BEGIN
    DELETE FROM descriptions
    WHERE id = p_DescriptionID;
END //

DELIMITER ;

#Dodanie budynku
DELIMITER //

CREATE PROCEDURE InsertBuilding(
    IN p_City VARCHAR(255),
    IN p_Street VARCHAR(255),
    IN p_DescriptionID INT
)
BEGIN
    INSERT INTO buildings (City, Street, DescriptionID)
    VALUES (p_City, p_Street, p_DescriptionID);
END //

DELIMITER ;

#Modyfikacja  budynku
DELIMITER //

CREATE PROCEDURE UpdateBuildingDescription(
    IN p_BuildingID INT,
    IN p_NewDescriptionID INT
)
BEGIN
    UPDATE buildings
    SET DescriptionID = p_NewDescriptionID
    WHERE id = p_BuildingID;
END //

DELIMITER ;

#Usuwanie budynku
DELIMITER //

CREATE PROCEDURE DeleteBuilding(
    IN p_BuildingID INT
)
BEGIN
    DELETE FROM buildings
    WHERE id = p_BuildingID;
END //

DELIMITER ;

#Dodanie pokoju
DELIMITER //

CREATE PROCEDURE InsertRoom(
    IN p_RoomNumber INT,
    IN p_RoomType VARCHAR(255),
    IN p_BedsNumber INT,
    IN p_BuildingID INT,
    IN p_DescriptionID INT
)
BEGIN
    INSERT INTO rooms (Number, Type, BedsNumber, BuildingID, DescriptionID)
    VALUES (p_RoomNumber, p_RoomType, p_BedsNumber, p_BuildingID, p_DescriptionID);
END //

DELIMITER ;

#Modyfikacja  pokoju
DELIMITER //

CREATE PROCEDURE UpdateRoomDescription(
    IN p_RoomID INT,
    IN p_NewDescriptionID INT
)
BEGIN
    UPDATE rooms
    SET DescriptionID = p_NewDescriptionID
    WHERE id = p_RoomID;
END //

DELIMITER ;

#Usuwanie budynku
DELIMITER //

CREATE PROCEDURE DeleteRoom(
    IN p_RoomID INT
)
BEGIN
    DELETE FROM rooms
    WHERE id = p_RoomID;
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





