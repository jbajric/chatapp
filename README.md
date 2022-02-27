# Chat application <img src="/assets/chaticon.png" width="50" height="50">

Developed by [Jasmin Bajrić](https://github.com/jbajric) in february 2022.

### Description
This web application is created for the Evolt interview. Chat application gives users the ability to exchange messages without the need to refresh the web browser session. 

### Used technologies

#### [Spring Boot 2.6.3](https://github.com/spring-projects/spring-boot/releases/tag/v2.6.3)
#### [ReactJS 17.0.2](https://github.com/facebook/react/blob/main/CHANGELOG.md#1702-march-22-2021)

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

## Required functionalities:

1. When opened, user has a random username.
2. View of active users in sidebar.
3. Centered chat room window.
4. Ability to send and receive messages in chat room.
5. Broadcast notification for a new user in chat room.

## Optional functionalities:

1. Ability for private conversations with active users.
2. Notifications for a created private conversation.
3. View of old messages in global chat room.
