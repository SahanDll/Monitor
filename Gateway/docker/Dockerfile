FROM openjdk:14-alpine

ARG JAR_FILE=Gateway-0.1.1-exec.jar

COPY ${JAR_FILE} gateway.jar

EXPOSE 8181
EXPOSE 8080

CMD ["java", "-jar", "gateway.jar"]
# CMD ["java", "-Dspring.profiles.active=docker", "-jar", "user.jar"]