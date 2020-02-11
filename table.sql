CREATE DATABASE java;
USE java;
CREATE TABLE user (
    Name varchar(30) NOT NULL,
    Email varchar(30) NOT NULL,
    UName varchar(30) NOT NULL,
    Password varchar(30) NOT NULL,
    online boolean NOT NULL,
    IP varchar(30) NOT NULL
);
