/*create table BaseEntity
(
    ID  int primary key not null identity ,
)
*/

//todo run the queries

create table Supplier(
    ID  int primary key not null identity,
    name varchar(30) not null
);

create table ProductCategory(
    ID  int primary key not null identity,
    name varchar(30) not null,
    description varchar(100)
);

create table Product(
    ID  int primary key not null identity,
    name varchar(30) not null,
    description varchar(100),
    price bigint,
    weight double,
    PCID int NOT NULL,
    FOREIGN KEY ( PCID ) REFERENCES ProductCategory ( ID ), //ON UPDATE  NO ACTION  ON DELETE  CASCADE
    SID int NOT NULL,
    FOREIGN KEY ( SID ) REFERENCES Supplier ( ID ),
    imageURL varchar(100)
);

create table Location(
    ID  int primary key not null identity,
    name varchar(30) not null,
    AddressCountry varchar(50),
    AddressCity varchar(50),
    AddressCounty varchar(50),
    AddressStreetAddress varchar(50)
);

create table Costumer(
     ID  int primary key not null identity,
     FirstName varchar(30) not null,
     LastName varchar(30) not null,
     Username varchar(30) not null,
     Password varchar(30) not null,
     EmailAddress varchar(30) not null
);

create table Order(
     ID  int primary key not null identity,
     name varchar(30) not null,
     LID int NOT NULL,
     FOREIGN KEY ( LID ) REFERENCES Location ( ID ),
     CID int NOT NULL,
     FOREIGN KEY ( CID ) REFERENCES Costumer ( ID ),
     AddressCountry varchar(50),
     AddressCity varchar(50),
     AddressCounty varchar(50),
     AddressStreetAddress varchar(50),
     CreatedAt datetime
);
//todo Stock , Order detail