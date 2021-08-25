drop user if exists 'user_spring5';

create user 'user_spring5'@'localhost' identified by 'rootroot';

drop database if exists spring5;

create database spring5;

grant all privileges on spring5.* to 'user_spring5'@'localhost';


create table spring5.MEMBER ( 
ID int auto_increment primary key,
EMAIL varchar(255),
PASSWORD varchar(100),
NAME varchar(100),
REGDATE datetime,
unique key (EMAIL) );