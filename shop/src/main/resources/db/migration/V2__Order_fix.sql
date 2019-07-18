drop table if exists Order_tbl;
create table if not exists Order_tbl(
        ID  int not null identity primary key,
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