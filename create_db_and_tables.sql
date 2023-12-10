# DROP DATABASE hotelsapp;

CREATE DATABASE hotelsapp;

CREATE TABLE `hotelsapp`.`descriptions` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Description_UNIQUE` (`Description` ASC) VISIBLE);

CREATE TABLE `hotelsapp`.`buildings` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `City` VARCHAR(255) NOT NULL,
  `Street` VARCHAR(255) NOT NULL,
  `DescriptionID` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `DescriptionID_idx` (`DescriptionID` ASC) VISIBLE,
  CONSTRAINT `DescriptionID`
    FOREIGN KEY (`DescriptionID`)
    REFERENCES `hotelsapp`.`descriptions` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION);

CREATE TABLE `hotelsapp`.`rooms` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Number` INT NOT NULL,
  `Type` VARCHAR(255) NULL,
  `BedsNumber` INT NULL,
  `BuildingID` INT NULL,
  `DescriptionID` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `BuildingID_idx` (`BuildingID` ASC) VISIBLE,
  INDEX `DescriptionID_idx` (`DescriptionID` ASC) VISIBLE,
  CONSTRAINT `BuildingID`
    FOREIGN KEY (`BuildingID`)
    REFERENCES `hotelsapp`.`buildings` (`id`)
    ON DELETE RESTRICT
    ON UPDATE NO ACTION,
  CONSTRAINT `DescriptionIDgfk`
    FOREIGN KEY (`DescriptionID`)
    REFERENCES `hotelsapp`.`descriptions` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION);

CREATE TABLE `hotelsapp`.`bookings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ClientName` varchar(255) DEFAULT NULL,
  `ClientSurname` varchar(255) NOT NULL,
  `PhoneNumber` varchar(9) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `StartDate` date NOT NULL,
  `EndDate` date NOT NULL,
  `RoomID` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `RoomID_idx` (`RoomID`),
  CONSTRAINT `RoomID` FOREIGN KEY (`RoomID`) REFERENCES `rooms` (`id`) ON DELETE RESTRICT
);

CREATE TABLE `hotelsapp`.`departments` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) NOT NULL,
  `ManagerID` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `Name_UNIQUE` (`Name`),
  KEY `ManagerID_idx` (`ManagerID`)
  #CONSTRAINT `ManagerID` FOREIGN KEY (`ManagerID`) REFERENCES `employees` (`id`) ON DELETE RESTRICT
) ;

Insert into `hotelsapp`.`departments` (Name, ManagerID) values ('Office', 1);
Insert into `hotelsapp`.`departments` (Name, ManagerID) values ('Marketing', 1);
# select * from `hotelsapp`.`departments`;

CREATE TABLE `hotelsapp`.`employees` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(255) NOT NULL,
  `Surname` VARCHAR(255) NOT NULL,
  `Phone` VARCHAR(9) NOT NULL,
  `Email` VARCHAR(255) NOT NULL,
  `Privilege` INT NOT NULL,
  `DepartmentID` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Phone_UNIQUE` (`Phone` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC) VISIBLE,
  INDEX `DepartmentID_idx` (`DepartmentID` ASC) VISIBLE,
  CONSTRAINT `DepartmentID`
    FOREIGN KEY (`DepartmentID`)
    REFERENCES `hotelsapp`.`departments` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION);
    
Insert into `hotelsapp`.`employees` (Name, Surname, Phone, Email, Privilege, DepartmentID) 
values ('Tedious', 'Tomcat', '123456789', 'tedioustomcat@mail.com', 3, 1);
# select * from `hotelsapp`.`employees`;


ALTER TABLE `hotelsapp`.`departments` 
ADD CONSTRAINT `ManagerIDfk`
  FOREIGN KEY (`ManagerID`)
  REFERENCES `hotelsapp`.`employees` (`id`)
  ON DELETE RESTRICT
  ON UPDATE NO ACTION;
  
  
  CREATE TABLE `hotelsapp`.`logindata` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `Login` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  `LastLoginDate` DATE NULL,
  `EmployeeID` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Login_UNIQUE` (`Login` ASC) VISIBLE,
  INDEX `EmployeeID_idx` (`EmployeeID` ASC) VISIBLE,
  CONSTRAINT `EmployeeID`
    FOREIGN KEY (`EmployeeID`)
    REFERENCES `hotelsapp`.`employees` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);



