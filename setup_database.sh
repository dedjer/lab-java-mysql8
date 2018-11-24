#!/bin/sh -e

mysql -uroot -p -e "DROP DATABASE IF EXISTS example;
create database db_example;
create user 'springuser'@'localhost' identified by 'ThePassword';
grant all on db_example.* to 'springuser'@'localhost';"
