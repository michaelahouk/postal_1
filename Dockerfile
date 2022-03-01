#Build
FROM maven:3.8.4-jdk-8 AS build
COPY src /postal_1/src
COPY pom.xml /postal_1
RUN mvn -f /postal_1/pom.xml clean package

#Run
FROM openjdk:8-jdk
COPY --from=build /postal_1/target/postal_1-1.0-SNAPSHOT.jar Postal1.jar
ENTRYPOINT [ "java","-jar","Postal1.jar" ]