# Chat application <img src="/assets/chaticon.png" width="50" height="50">

Developed by [Jasmin Bajrić](https://github.com/jbajric) in february 2022.

### Description
This web application is created for the Evolt interview. Chat application gives users the ability to exchange messages without the need to refresh the web browser session. 

### Used technologies

#### [Spring Boot 2.6.3](https://github.com/spring-projects/spring-boot/releases/tag/v2.6.3)
#### [ReactJS 17.0.2](https://github.com/facebook/react/blob/main/CHANGELOG.md#1702-march-22-2021)
#### [Java 17](https://docs.oracle.com/en/java/javase/17/)

## Starting the project

### First step
In your IDE, open the project and within the root directory for backend execute:

```
maven clean
maven install
```

### Second step
Launch the following terminal commands:

```
docker compose build
docker compose up
```

#### Bonus: Heroku
This project uses a deployed Heroku Postgres database with following connection credentials:

```
 hosturl: ec2-52-73-149-159.compute-1.amazonaws.com
 hostport: 5432
 database: db6blnro637lup
 username: stgirqrhjzvobe
 password: eafc8e9b75935ca6b56600549dbd6f5b6d216cfc4d5d42495030e7ae470b1f61
```

## Implemented functionalities:

1. When opened, user has a random username.
2. View of active users in sidebar.
3. Centered chat room window.
4. Ability to send and receive messages in chat room.
5. Broadcast notification when a new user joins chat room. 
6. Broadcast notification when a user leaves chat room.
7. Ability to view all sent messages in global chat room.
