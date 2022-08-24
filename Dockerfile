FROM openjdk:11
COPY  target/pokemon-trainer-hub-0.0.1-SNAPSHOT.jar pokemon-trainer-hub-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/pokemon-trainer-hub-0.0.1-SNAPSHOT.jar"]