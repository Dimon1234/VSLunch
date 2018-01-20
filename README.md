# VSLunch
Lunch Voting System

###Requirements for this project
Design and implement a JSON API using Hibernate/Spring/SpringMVC without frontend.

###Packaging and deploying
#### Prerequisites
- Java 8
- Maven > 3.0

#### Main steps:
- Command to create an executable jar:

    $ mvn package

- Command to run the executable jar created on the previous step(with embedded Tomcat):

    $ java -jar target/websocketauth-0.0.1-SNAPSHOT.jar

- After step 2 the application should be accessable by 'http://localhost:8080/'

###ABOUT
####Configuration
The application configured with predefined users(username, role, password, auth):
- admin, ROLE_ADMIN, 123456, Basic YWRtaW46MTIzNDU2
- user, ROLE_USER, 123456, Basic dXNlcjoxMjM0NTY=


If you want to add new user you can change init class: src/main/com/test/task/Service/InitService 
Before saving in database all passwords are encrypted
Add role for the user. Available roles: ROLE_ADMIN, ROLE_USER.

####Used technologies
- Java 8
- Maven 3
- Spring Boot
- Spring Security
- Spring 4
	* Spring Core (Beans, Context)
	* Spring Data Access (ORM, JPA (Hibernate), Transactions)
- DBs: H2
- RESTful services
- SLF4J2
- Tomcat


### cURL commands to test the RESTful services:
The REST API of this application is secured with Spring Security and every user of the API should be logged in.

- Get Dishes command
    * curl -X GET "http://localhost:8080/dishes" -H 'Authorization: Basic dXNlcjoxMjM0NTY=' -H 'Content-Type: application/json'
- Get restaurants command.
	* curl -X GET "http://localhost:8080/restaurants" -H 'Authorization: Basic dXNlcjoxMjM0NTY=' -H 'Content-Type: application/json'
- Get menus command 
    * curl -X GET "http://localhost:8080/menus" -H 'Authorization: Basic dXNlcjoxMjM0NTY=' -H 'Content-Type: application/json'
- Get Users command (with role ADMIN)
    * curl -X GET "http://localhost:8080/users" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'

-Add new restaurant command
	* curl -X POST "http://localhost:8080/restaurants" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json' -d '{"name":"rest-Name"}'
-Add new menu to restaurant command 
    * curl -X POST "http://localhost:8080/menus" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json' -d '{"restaurant":"http://localhost:8080/restaurants/2","date":"2018-01-21"}'
-Add new Dish to menu command
    * curl -X POST "http://localhost:8080/dishes" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'
  -d '{"menu":"http://localhost:8080/menus/1","dishName":"new dish", "price":100.1}'
-Add new User command 
    * curl -X POST "http://localhost:8080/users" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'
  -d '{"name":"user1", "email":"demo_user@mail.ru", "password":"qwerty","roles" : ["ROLE_USER"]}'

-Delete Menu command
    * curl -X DELETE "http://localhost:8080/menus/1" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'
-Delete restaurant command(after deleting menu) 
    * curl -X DELETE "http://localhost:8080/restaurants/1" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'
-Delete dish command
    * curl -X DELETE "http://localhost:8080/dishes/1" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'
-Delete user command
    * curl -X DELETE "http://localhost:8080/users/2" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'

-Update Restaurant (name)
    * curl -X PUT "http://localhost:8080/restaurants/1" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'
  -d '{"name":"new rest name"}'
-Update Menu (date)
    * curl -X PUT "http://localhost:8080/menus/1" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'
  -d '{"date":"2018-01-22"}'
-Update Dish 
    * curl -X "http://localhost:8080/dishes/1" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'
  -d '{"dishName":"new dish name","price":1000}'
-Update User 
    * curl -X PUT  "http://localhost:8080/users/2" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json' -d '{"name":"new name", "email":"newemail@mail.ru", "password":"ytrewq", "roles":["ROLE_USER"]}'

-Search Dishes by menu
    * curl -X GET 'http://localhost:8080/dishes/search/by-menu?menu=http://localhost:8080/menus/1' -H 'Authorization: Basic YWRtaW46MTIzNDU2' \
  -H 'Content-Type: application/json'
-Search Menus by date
    * curl -X GET 'http://localhost:8080/menus/search/by-date?date=2018-01-19' -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'


- Vote command
   * curl -X POST "http://localhost:8080/vote/1" -H 'Authorization: Basic YWRtaW46MTIzNDU2' -H 'Content-Type: application/json'

