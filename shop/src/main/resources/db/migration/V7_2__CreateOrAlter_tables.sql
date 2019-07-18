
create table if not exists Supplier_tbl(
                                           ID  int  identity primary key,
                                           name Varchar(100) not null
);

create table if not exists ProductCategory_tbl(
                                                  ID  int  not null identity primary key,
                                                  name varchar(100) not null,
                                                  description varchar(100)
);

create table if not exists Product_tbl(
                                          ID  int  not null identity primary key,
                                          name varchar(100) not null,
                                          description varchar(100),
                                          price DECIMAL,
                                          weight double,
                                          PCID int NOT NULL,
                                          FOREIGN KEY ( PCID ) REFERENCES ProductCategory_tbl ( ID ), //ON UPDATE  NO ACTION  ON DELETE  CASCADE
                                          SID int NOT NULL,
                                          FOREIGN KEY ( SID ) REFERENCES Supplier_tbl ( ID ),
                                          imageURL varchar(200)
);

create table if not exists Location_tbl(
                                           ID  int not null identity primary key,
                                           name varchar(100) not null,
                                           addressCountry varchar(100),
                                           addressCity varchar(100),
                                           addressCounty varchar(100),
                                           addressStreetAddress varchar(100)
);

create table if not exists Customer_tbl(
                                           ID  int not null identity  primary key,
                                           firstName varchar(100) not null,
                                           lastName varchar(100) not null,
                                           username varchar(100) not null,
                                           password varchar(100) not null,
                                           emailAddress varchar(100) not null
);

create table if not exists Order_tbl(
                                        ID  int not null identity primary key,
                                        name varchar(100) not null,
                                        LID int NOT NULL,
                                        FOREIGN KEY ( LID ) REFERENCES Location_tbl ( ID ),
                                        CID int NOT NULL,
                                        FOREIGN KEY ( CID ) REFERENCES Customer_tbl ( ID ),
                                        addressCountry varchar(100),
                                        addressCity varchar(100),
                                        addressCounty varchar(100),
                                        addressStreetAddress varchar(100),
                                        createdAt datetime
);

create table if not exists Stock_tbl (
                                         LID int NOT NULL,
                                         FOREIGN KEY ( LID ) REFERENCES Location_tbl ( ID ),
                                         PID int NOT NULL,
                                         FOREIGN KEY ( PID ) REFERENCES Product_tbl ( ID ),
                                         quantity int,
                                         PRIMARY KEY (LID,PID)
);

create table if not exists OrderDetail_tbl(
                                              OID int NOT NULL,
                                              FOREIGN KEY ( OID ) REFERENCES Order_tbl( ID ),
                                              PID int NOT NULL,
                                              FOREIGN KEY ( PID ) REFERENCES Product_tbl ( ID ),
                                              quantity int,
                                              PRIMARY KEY (OID,PID)
);

create table if not exists Revenue_tbl(
                                          ID  int not null identity primary key,
                                          LID int NOT NULL,
                                          FOREIGN KEY ( LID ) REFERENCES Location_tbl ( ID ),
                                          date DATE,
                                          sum DECIMAL
)

