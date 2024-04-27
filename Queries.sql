CREATE DATABASE demo;


CREATE TABLE Customer (
      customer_Id INT PRIMARY KEY auto_increment,
      customer_Name VARCHAR(100)
);


CREATE TABLE Payment (
     payment_Id INT PRIMARY KEY auto_increment,
     payment_Amount DECIMAL(10, 2),
     status VARCHAR(100)
);