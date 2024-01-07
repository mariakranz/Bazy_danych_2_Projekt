DELIMITER //

CREATE PROCEDURE GetDepartmentNames()
BEGIN
    SELECT name FROM hotelsapp.departments;
END //

DELIMITER ;

#procedury z pliku "Operacje Use-case.sql"

#Przeglądanie listy pokojów
DELIMITER //
CREATE PROCEDURE GetRoomsInfo()
BEGIN
    Select * from hotelsapp.roominfo;
END //
DELIMITER ;

#Rezerwacja pokoju
DELIMITER //

CREATE PROCEDURE InsertBooking(
    IN p_ClientName VARCHAR(255),
    IN p_ClientSurname VARCHAR(255),
    IN p_PhoneNumber VARCHAR(15),
    IN p_Email VARCHAR(255),
    IN p_StartDate DATE,
    IN p_EndDate DATE,
    IN p_RoomID INT
)
BEGIN
    INSERT INTO bookings (ClientName, ClientSurname, PhoneNumber, Email, StartDate, EndDate, RoomID)
    VALUES (p_ClientName, p_ClientSurname, p_PhoneNumber, p_Email, p_StartDate, p_EndDate, p_RoomID);
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





