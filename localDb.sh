#!/bin/bash
# docker pull --platform linux/amd64 mysql:8.0.22
docker run -itd --platform linux/amd64 -p 23306:3306 -e MYSQL_ROOT_PASSWORD=1q2w3e4r! mysql:8.0.22

# INSERT INTO tb_people(id, name) VALUES (1, "KIMMINSIK");

#update user set authentication_string=password('1q2w3e4r!') where user='root';
#flush privileges;
#
#UPDATE mysql.user set authentication_string=PASSWORD("MyNewPass"),  password_expired='N', plugin='mysql_native_password' where User='root';
#update user set authentication_string=password('1q2w3e4r!') where user='root';
#
#alter user 'root'@'localhost' identified with mysql_native_password by '1q2w3e4r!';
#create user 'root'@'%' identified by '1q2w3e4r!';
#
#flush privileges;
#
#grant all privileges on *.* to 'root'@'%';