FROM openjdk:11-jdk-slim-sid
COPY target/*.jar /opt/
EXPOSE 8080
CMD java-jar /opt/spring-backend-v1.jar 