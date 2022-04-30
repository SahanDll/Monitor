FROM openjdk:14-alpine

ARG JAR_FILE=Eureka-0.1.1-exec.jar

COPY ${JAR_FILE} eureka.jar

EXPOSE 8082
EXPOSE 8080

CMD ["java", "-jar", "eureka.jar"]
# CMD ["java", "-Dspring.profiles.active=docker", "-jar", "user.jar"]