FROM maven:3.8.5-openjdk-17 AS build

COPY src /home/app/src
COPY pom.xml /home/app

WORKDIR /home/app

RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-focal

RUN apt-get update && apt-get install -y netcat

WORKDIR /app

COPY --from=build /home/app/target/*.jar app.jar

COPY wait-for-services.sh /app/wait-for-services.sh
RUN chmod +x /app/wait-for-services.sh

EXPOSE 8080

CMD ["./wait-for-services.sh", "rabbitmq", "mongodb", "java", "-jar", "app.jar"]
