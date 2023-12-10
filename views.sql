#widok budynku
CREATE VIEW BuildingInfo AS
SELECT 
    b.id AS BuildingID,
    b.City,
    b.Street,
    d.Description,
    COUNT(r.Number) AS RoomCount
FROM buildings b
JOIN descriptions d ON b.DescriptionID = d.id
LEFT JOIN rooms r ON b.id = r.BuildingID
GROUP BY b.id;
#widok pokoju
CREATE VIEW RoomInfo AS
SELECT
        r.id AS RoomID, 
        r.Number, 
        r.Type, 
        r.BedsNumber, 
        d.Description as `RoomDescription`, 
        bi.City, 
        bi.Street, 
        bi.Description as `BuildingDescription`,
        bi.RoomCount
FROM rooms r
JOIN descriptions d ON r.DescriptionID = d.ID
JOIN BuildingInfo bi ON r.BuildingID = bi.BuildingID;
# widok pracownika
CREATE VIEW EmployeeInfo AS
SELECT 
    e.id AS EmployeeID,
    e.name AS EmployeeName,
    e.surname AS EmployeeSurname,
    e.phone AS EmployeePhone,
    e.email AS EmployeeEmail,
    e.privilege AS EmployeePrivilege,
    d.Name AS DepartmentName
FROM 
    employees e
JOIN 
    departments d ON e.DepartmentID = d.id;

