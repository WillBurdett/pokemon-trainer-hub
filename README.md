# pokemon-trainer-hub

A small demo project using Spring Data JPA, MySQL, Docker and Swagger.

To run, make sure you have Docker Desktop installed.

After cloning the project, in the project root directory run the following commands:
- mvn clean package -DskipTests
- docker build --tag=pokemon-trainer-hub:latest .
- docker-compose up