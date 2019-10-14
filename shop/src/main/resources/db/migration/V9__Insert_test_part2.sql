insert into Productcategory_tbl (name,description) values ('toy','An object to play with'),('food','Nutritious');
insert into Product_tbl (name,description,price,weight,pcid,sid,imageurl) values
('Teddy bear','Best toy',13.50,1.20,1,1,'https://upload.wikimedia.org/wikipedia/commons/d/d8/Teddy_bear_early_1900s_-_Smithsonian_Museum_of_Natural_History.jpg');
                  insert into Product_tbl (name,description,price,weight,pcid,sid,imageurl) values
    ('Seminte','Moartea in coaja',6.30,0.39,2,1,'https://s12emagst.akamaized.net/products/4916/4915947/images/res_c23258b1b1566db3772d99f6aea8d9d0_full.jpg');

insert into location_tbl (name,addresscountry,addresscity,addresscounty,addressstreetaddress) values
('Cluj','Romania','Cluj-Napoca','Cluj','Piața Avram Iancu');
insert into location_tbl (name,addresscountry,addresscity,addresscounty,addressstreetaddress) values
('Oradea','Romania','Oradea','Bihor','Nufarului 1');

insert into customer_tbl (firstname,lastname,username,password,emailaddress) values
('Mihai','Lautaru','MihaL','HalalMihal','mihailautaru@yahoo.com'),('Loredana','Miclea','Lore','Miclealore','loredanadana@gmail.com');


insert into revenue_tbl (lid,date,sum) values (2,'2019-12-05',14.50);
