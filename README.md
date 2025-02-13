# Cheese Quest

Team Syrnyky: Mykyta, Dmytro, Vitaliy, Arsenii

Demo: http://193.160.226.130:3002/ \
\
We recommend you [run locally with Docker](#run-locally-with-docker)

Example user: 
- username: 8x1@gmail.com
- password: 11111111

Frontend Repository: https://github.com/SyrnykyDev/CheeseQuest-frontend

This project was created with Spring Boot

## Available Endpoints

- /api/auth [GET]
- /api/auth/login [POST]
- /api/auth/oauth2/success [GET]
- /api/auth/registration [POST]
- /api/authors [GET]
- /api/image/profile/upload [POST]
- /api/image/taskMedia/upload [POST]
- /api/profile/{id} [GET]
- /api/profile/{id}/quests [GET]
- /api/quest/create [POST]
- /api/quest/{id} [GET]
- /api/quest/{id}/start [GET]
- /api/quests/all [GET]
- /api/review/createReview [POST]
- /api/review/{id} [GET]
- /api/task/create [POST]
- /api/user [GET]
- /api/user/edit [POST]


## Run locally with Docker

<code> docker run --rm  -it -p 8080:8080 c455/api
 </code>
<code> docker run --rm  -it -p 3000:80/tcp c455/client
 </code>

## Available Scripts

In the project directory, you can run:

<code> ./gradlew bootRun </code>

Runs the api in the development mode.\
[http://localhost:8080](http://localhost:8080) 

