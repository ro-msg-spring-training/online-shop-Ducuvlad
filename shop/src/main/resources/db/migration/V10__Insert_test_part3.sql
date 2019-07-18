insert into stock_tbl (pid,lid,quantity) values (1,1,12);
insert into stock_tbl (pid,lid,quantity) values (2,1,5);

insert into order_tbl (lid,cid,createdat,addresscountry,addresscity,addresscounty,addressstreetaddress) values (1,1,'2008-11-11','Romania','Cluj-Napoca','Cluj','Saturn 11');

insert into orderdetail_tbl (oid,pid,quantity) values(1,1,12)