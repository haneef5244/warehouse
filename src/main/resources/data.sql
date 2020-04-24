INSERT INTO roles(description,name) values ('Admin', 'ADMIN');
INSERT INTO roles(description,name) values ('User', 'USER');

INSERT INTO user (username,password, name) values ('admin','$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'admin');
INSERT INTO user (username,password, name) values ('user','$2a$04$I9Q2sDc4QGGg5WNTLmsz0.fvGv3OjoZyj81PrSFyGOqMphqfS2qKu', 'user');

insert into USER_ROLES(USER_ID,ROLE_ID) values (1,1);
insert into USER_ROLES(USER_ID,ROLE_ID) values (2,2);