#Przeglądanie listy pokojów
Select * from roomInfo;
#Rezerwacja pokoju
insert into bookings (ClientName, ClientSurname, PhoneNumber, Email, StartDate, EndDate, RoomID) values ('Jan', 'Kowalski', '123456789', 'jan.kowalski@email.com', '2023-11-01', '2023-11-05', 6);
#Modyfikacja  rezerwacji
update employees set Email = 'jan.kowalski@gmail.com' where id = 6;
#Usuwanie rezerwacji
delete from bookings where id = 5;
#Dodanie opisu
insert into descriptions (Description) values ('Testowy opis1');
#Modyfikacja  opisu
update descriptions set Description = 'Poprawiony Opis' where id = 16;
#Usuwanie opisu
delete from descriptions where id = 16;
#Dodanie budynku
insert into buildings (City, Street, DescriptionID) values ('Wrocław', "pl.Grunwaldzki 124", 2);
#Modyfikacja  budynku
update buildings set DescriptionID = 1 where id = 6;
#Usuwanie budynku
delete from buildings where id = 6;
#Dodanie pokoju
Insert into rooms (Number, Type, BedsNumber, BuildingID, DescriptionID) values (123, 'Family', 3, 3, 6);
#Modyfikacja  pokoju
update rooms set DescriptionID = 12 where id = 11;
#Usuwanie budynku
delete from rooms where id = 11;
#Logowanie
select EmployeeID from logindata where Login = 'User1' and Password = 'Password1';
update logindata set LastLoginDate = current_date() where EmployeeID = 1;