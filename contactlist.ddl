CREATE TABLE company
(
    id INT PRIMARY KEY NOT NULL,
    company VARCHAR(255) NOT NULL
);
CREATE TABLE country
(
    id INT PRIMARY KEY NOT NULL,
    country VARCHAR(255) NOT NULL
);
CREATE TABLE file
(
    fileHash VARCHAR(255) NOT NULL,
    fileName VARCHAR(255) NOT NULL,
    fileDate DATE NOT NULL,
    comment VARCHAR(255) NOT NULL,
    idPerson INT NOT NULL
);
CREATE TABLE maritalstatus
(
    id INT PRIMARY KEY NOT NULL,
    maritalStatus VARCHAR(20)
);
CREATE TABLE persons
(
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    surname VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    patronymic VARCHAR(255) NOT NULL,
    dateOfBirth DATE NOT NULL,
    sex VARCHAR(1) NOT NULL,
    nationality VARCHAR(255) NOT NULL,
    maritalStatus INT NOT NULL,
    webSite VARCHAR(255),
    email VARCHAR(255) NOT NULL,
    company INT NOT NULL,
    country INT NOT NULL,
    city VARCHAR(255) NOT NULL,
    street VARCHAR(255) NOT NULL,
    home INT NOT NULL,
    flat INT,
    cityIndex VARCHAR(255) NOT NULL,
    photoPath VARCHAR(300)
);
CREATE TABLE phone
(
    id INT NOT NULL AUTO_INCREMENT,
    countryCode INT NOT NULL,
    operatorCode INT NOT NULL,
    phoneNumber INT NOT NULL,
    phoneType VARCHAR(255),
    comment VARCHAR(255),
    idPerson INT NOT NULL
);
CREATE TABLE templates
(
    id INT NOT NULL,
    template VARCHAR(400) NOT NULL,
    templateName VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX unique_id ON company ( id );
CREATE UNIQUE INDEX unique_id ON country ( id );
CREATE UNIQUE INDEX unique_id ON maritalstatus ( id );
CREATE UNIQUE INDEX unique_id ON persons ( id );
CREATE UNIQUE INDEX unique_id ON phone ( id );
CREATE UNIQUE INDEX unique_id ON templates ( id );
