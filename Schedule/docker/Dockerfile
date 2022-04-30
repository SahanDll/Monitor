FROM openjdk:14-alpine

ARG JAR_FILE=Schedule-0.1.1-exec.jar

COPY ${JAR_FILE} schedule.jar

EXPOSE 8183
EXPOSE 8080

CMD ["java", "-jar", "schedule.jar"]
# CMD ["java", "-Dspring.profiles.active=docker", "-jar", "user.jar"]