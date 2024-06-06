CREATE TABLE person(
    id serial NOT NULL PRIMARY KEY ,
    name text not null unique
);

CREATE TABLE product(
    id serial not null primary key ,
    name text not null unique ,
    price double precision not null
);

CREATE TABLE person_product(
    person_id int not null references person (id) on DELETE cascade ,
    product_id int not null references product(id) on DELETE cascade ,
    price double precision
);

CREATE index person_name_idx on person(name);
CREATE index product_name_idx on product (name);

select * from pg_indexes where tablename='person';
select * from pg_indexes where tablename='product';

INSERT INTO person (name) values
                                      ('Person_1'),
                                      ('Person_2'),
                                      ('Person_3'),
                                      ('Person_4'),
                                      ('Person_5'),
                                      ('Person_6');

insert into product (name, price) VALUES
                                      ('Product_1', 10000),
                                      ('Product_2', 12000),
                                      ('Product_3', 15000),
                                      ('Product_4', 5000),
                                      ('Product_5', 3000),
                                      ('Product_6', 1800);


