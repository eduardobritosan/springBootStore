insert into product values(null, TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'Richard Garfield', 1200.5, '123'
    , 'Black Lotus', 'DISCONTINUED' );
insert into product values(null, TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'Richard Garfield', 100.5, '124'
   , 'Mana Crypt', 'ACTIVE' );

insert into supplier values(null, 'tunisia', 'abed', 40);
insert into supplier values(null, 'algeria', 'mohammed', 45);

insert into product_supplier values(null, 1, 1);
insert into product_supplier values(null, 1, 2);
insert into product_supplier values(null, 2, 1);

insert into price_reduction values(null,TO_DATE('31/12/2021', 'DD/MM/YYYY'), 200, 1, TO_DATE('17/12/2021', 'DD/MM/YYYY'),  1);