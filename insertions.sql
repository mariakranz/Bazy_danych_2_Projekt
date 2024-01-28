# Insert into descriptions
Insert into `hotelsapp`.`descriptions` (Description) values ("Elegancki pokój z widokiem na morze, idealny dla par. Wyposażony w nowoczesne meble, duże łóżko i prywatną łazienkę. Odpocznij w luksusie i ciesz się malowniczym krajobrazem.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Przestronny apartament rodzinny z dwiema sypialniami i kuchnią. Doskonały wybór dla rodzin z dziećmi. Wnętrza utrzymane w nowoczesnym stylu z przytulnymi detalami.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Pokój biznesowy z ergonomicznym biurkiem i szybkim dostępem do Internetu. Idealne miejsce do pracy i relaksu po intensywnym dniu spotkań. Komfort i funkcjonalność w jednym.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Stylowy apartament typu suite z osobną sypialnią i przestronnym salonem. Luksusowe wykończenia, wygodne łóżko, a także eleganckie detale sprawią, że poczujesz się jak w domu.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Pokój dla miłośników wellness z dostępem do prywatnej sauny i jacuzzi. Relaksuj się i ciesz się chwilą błogiego odpoczynku po intensywnym dniu zwiedzania.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Klasyczny pokój z podwójnym łóżkiem i romantycznym wystrojem. Doskonały wybór dla par szukających spokoju i intymności. Przytulna atmosfera gwarantowana.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Nowoczesny apartament z panoramicznym widokiem na miasto. Wyposażony w najnowsze technologie, zapewni Ci komfortowy pobyt. Idealny dla podróżujących służbowo i turystów.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Pokój rodzinny z dwiema łazienkami i oddzielnymi sypialniami dla dzieci. Doskonały wybór dla rodzin, które cenią sobie prywatność i wygodę podczas podróży.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Przestronny loft z ekskluzywnym wystrojem i designerskimi meblami. Doskonałe miejsce dla osób poszukujących unikalnego i luksusowego doświadczenia hotelowego.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Pokój z tarasem i widokiem na ogrody hotelowe. Zrelaksuj się na prywatnym tarasie i ciesz się świeżym powietrzem. Idealny dla miłośników natury i spokoju.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Ekskluzywny hotel resort na wybrzeżu, otoczony tropikalnym ogrodem i prywatną plażą. Każdy pokój oferuje panoramiczny widok na ocean, a luksusowe udogodnienia zapewniają niezapomniane wrażenia.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Historyczny pałac przekształcony w luksusowy hotel, zachowujący swój pierwotny urok i elegancję. Zabytkowe wnętrza, finezyjne detale oraz wysokiej klasy usługi tworzą niepowtarzalny klimat dla wymagających gości.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Hotel spa zlokalizowany w malowniczym kurorcie górskim, oferujący kompleksową gamę zabiegów relaksacyjnych. Otwarte przestrzenie, baseny termalne i specjalnie zaprojektowane pokoje tworzą raj dla miłośników wellness.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Designerski hotel w centrum miasta, otoczony modnymi restauracjami i galeriami sztuki. Każdy pokój jest unikatowy, a nowoczesne udogodnienia łączą się z kreatywnym stylem, tworząc niepowtarzalne miejsce dla podróżujących.");
Insert into `hotelsapp`.`descriptions` (Description) values ("Hotel butikowy w stylu vintage, zlokalizowany w zabytkowej dzielnicy. Każdy pokój jest urządzony indywidualnie, nawiązując do historii okolicy. Intymna atmosfera i autentyczność sprawiają, że to idealne miejsce dla poszukujących unikalnych doświadczeń.");

#Insert into buildings
Insert into `hotelsapp`.`buildings` (City, Street, DescriptionID) values ('Warszawa', 'ul. Chmielna 24', 11);
Insert into `hotelsapp`.`buildings` (City, Street, DescriptionID) values ('Kraków', 'ul. Floriańska 15', 12);
Insert into `hotelsapp`.`buildings` (City, Street, DescriptionID) values ('Gdańsk', 'ul. Długa 8', 13);
Insert into `hotelsapp`.`buildings` (City, Street, DescriptionID) values ('Poznań', 'ul. Garbary 10', 14);
Insert into `hotelsapp`.`buildings` (City, Street, DescriptionID) values ('Wrocław', 'ul. Świdnicka 5', 15);

#Insert into rooms
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (101, 'Apartament', 1, 1, 1);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (202, 'Double', 2, 2, 2);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (305, 'Single', 1, 3, 3);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (401, 'Suite', 2, 4, 4);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (502, 'Family', 4, 5, 5);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (103, 'Single', 1, 1, 6);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (204, 'Apartament', 1, 2, 7);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (307, 'Family', 4, 3, 8);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (403, 'Single', 1, 4, 9);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (504, 'Suite', 2, 5, 10);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (102, 'Double', 2, 1, 1);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (203, 'Single', 1, 2, 2);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (306, 'Suite', 2, 3, 3);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (402, 'Family', 4, 4, 4);
Insert into `hotelsapp`.`rooms` (Number, Type, BedsNumber, BuildingID, DescriptionID) values (503, 'Double', 2, 5, 5);

# Insert into bookings
Insert into `hotelsapp`.`bookings` (ClientName, ClientSurname, PhoneNumber, Email, StartDate, EndDate, RoomID) values ('Jan', 'Kowalski', '123456789', 'jan.kowalski@email.com', '2023-01-01', '2023-01-05', 1);
Insert into `hotelsapp`.`bookings` (ClientName, ClientSurname, PhoneNumber, Email, StartDate, EndDate, RoomID) values ('Anna', 'Nowak', '987654321', 'anna.nowak@email.com', '2023-02-10', '2023-02-15', 5);
Insert into `hotelsapp`.`bookings` (ClientName, ClientSurname, PhoneNumber, Email, StartDate, EndDate, RoomID) values ('Piotr', 'Wójcik', '654321987', 'piotr.wojcik@email.com', '2023-03-20', '2023-03-25', 15);
Insert into `hotelsapp`.`bookings` (ClientName, ClientSurname, PhoneNumber, Email, StartDate, EndDate, RoomID) values ('Karolina', 'Dąbrowska', '123789456', 'karolina.dabrowska@email.com', '2023-04-15', '2023-04-20', 2);
Insert into `hotelsapp`.`bookings` (ClientName, ClientSurname, PhoneNumber, Email, StartDate, EndDate, RoomID) values ('Mateusz', 'Kowalczyk', '789456123', 'mateusz.kowalczyk@email.com', '2023-05-05', '2023-05-10', 1);

# Insert into departments (two departments already)
Insert into `hotelsapp`.`departments` (Name, ManagerID) values ('Client_service', 1);
Insert into `hotelsapp`.`departments` (Name, ManagerID) values ('Cleaning', 1);
Insert into `hotelsapp`.`departments` (Name, ManagerID) values ('Social_media', 1);
Insert into `hotelsapp`.`departments` (Name, ManagerID) values ('IT', 1);
Insert into `hotelsapp`.`departments` (Name, ManagerID) values ('Catering', 1);
Insert into `hotelsapp`.`departments` (Name, ManagerID) values ('Security', 1);
Insert into `hotelsapp`.`departments` (Name, ManagerID) values ('Accounting', 1);


# Insert into employees (one employee already)
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('John', 'Doe', '555123456', 'johndoe@mail.com', 2, 4);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Jane', 'Smith', '555987654', 'janesmith@mail.com', 1, 2);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Bob', 'Johnson', '555234567', 'bobjohnson@mail.com', 3, 6);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Alice', 'Williams', '555876543', 'alicewilliams@mail.com', 2, 8);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Charlie', 'Brown', '555345678', 'charliebrown@mail.com', 1, 5);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Eva', 'Davis', '555987653', 'evadavis@mail.com', 3, 3);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Frank', 'Miller', '555456789', 'frankmiller@mail.com', 2, 7);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Grace', 'Smith', '555876532', 'gracesmith@mail.com', 1, 1);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Harry', 'Jones', '555567801', 'harryjones@mail.com', 3, 9);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Ivy', 'Taylor', '555234578', 'ivytaylor@mail.com', 2, 4);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Jack', 'Moore', '555678012', 'jackmoore@mail.com', 1, 6);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Kelly', 'Hill', '555346789', 'kellyhill@mail.com', 3, 8);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Leo', 'Baker', '551234567', 'leobaker@mail.com', 2, 5);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Mia', 'Clark', '558901234', 'miaclark@mail.com', 1, 3);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Nathan', 'White', '554567890', 'nathanwhite@mail.com', 3, 7);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Olivia', 'Lee', '556789012', 'olivialee@mail.com', 2, 1);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Peter', 'Smith', '558701234', 'petersmith@mail.com', 1, 9);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Quinn', 'Jones', '552345678', 'quinnjones@mail.com', 3, 2);
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) values ('Rachel', 'Taylor', '555678901', 'racheltaylor@mail.com', 2, 5);

# Insert into logindata -- nie do wywolywanie w kodzie
DELIMITER //
CREATE PROCEDURE `hotelsapp`.InsertLoginData()
BEGIN
    DECLARE i INT DEFAULT 1;
    
    WHILE i <= 20 DO
        INSERT INTO `hotelsapp`.`logindata` (Login, Password, LastLoginDate, EmployeeID)
        VALUES (CONCAT('User', i), CONCAT('Password', i), '2010-10-10', i);
        
        SET i = i + 1;
    END WHILE;
    
END //
DELIMITER ;

-- Wywołanie procedury
CALL `hotelsapp`.InsertLoginData();
DROP PROCEDURE IF EXISTS `hotelsapp`.InsertLoginData;

