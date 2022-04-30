FROM openjdk:14-alpine

ARG JAR_FILE=Cache-0.1.1-exec.jar

COPY ${JAR_FILE} cache.jar

EXPOSE 8190
EXPOSE 8080

CMD ["java", "-jar", "cache.jar"]
# CMD ["java", "-Dspring.profiles.active=docker", "-jar", "user.jar"]