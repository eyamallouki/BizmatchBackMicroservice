# Building the JAR file with Maven
FROM maven:3.8.3-jdk-19 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests

# Running the JAR file with OpenJDK 11
FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 9081:9081
ENTRYPOINT ["java","-jar","app.jar"]


