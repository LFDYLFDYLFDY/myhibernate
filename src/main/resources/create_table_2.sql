begin;
DROP TABLE IF EXISTS manufacturers CASCADE;
CREATE TABLE manufacturers (ID SERIAL primary key , TITLE VARCHAR(255));
INSERT INTO manufacturers (TITLE) VALUES ('Coca Cola'), ('Danone');

drop table if exists products cascade ;
create table products (id serial primary key , title varchar(256), price numeric(8,2), manufacturer_id int) ;
insert into products (title, manufacturer_id,price ) VALUES ('Sprite',1,30.00), ('Fanta',1,40.00), ('Prostokvasha', 2,50.00);

drop table if exists customers cascade;
create table customers (id serial primary key ,name varchar(100));
insert into customers (name) values  ('Bob'),('John'),('Max');

drop table if exists big_items cascade ;
create table big_items (id serial primary key , val int, junkField int, version serial);
insert into big_items (val) values (10);


commit ;
