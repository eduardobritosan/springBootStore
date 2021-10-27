insert into product values(null, TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'Richard Garfield', '123', 1200.5
    , 'Black Lotus', 'ACTIVE' );
insert into product values(null, TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'Richard Garfield', '124', 100.5
   , 'Mana Crypt', 'ACTIVE' );

insert into supplier values(null, 'tunisia', 'abed');
insert into supplier values(null, 'algeria', 'mohammed');

insert into product_supplier values(null, 1, 1);
insert into product_supplier values(null, 1, 2);
insert into product_supplier values(null, 2, 1);