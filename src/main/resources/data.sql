INSERT INTO roles(description,name) values ('Admin', 'ADMIN');
INSERT INTO roles(description,name) values ('User', 'USER');

INSERT INTO user (username,password, name) values ('admin','$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'admin');
INSERT INTO user (username,password, name) values ('user','$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'user');

insert into USER_ROLES(USER_ID,ROLE_ID) values (1,1);
insert into USER_ROLES(USER_ID,ROLE_ID) values (2,2);

insert into customer(name, phone_number, nric, residency)
values ('Test User', '1234567', '1239201', 'Alaska');
insert into car ( car_name, car_type) values ( 'Persona', 'Sedan');
insert into rental_info (start_date, end_date, status, charge, car_entity_id, customer_rental_info_entities)
values ('2020-10-10', '2020-10-20', 'ACTIVE', '20.00', 1, 1);
insert into warehouse ( current_capacity, max_capacity ) values (100, 1000);
update user set customer_id = 1 where username = 'user';