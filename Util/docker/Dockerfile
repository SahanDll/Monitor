FROM openjdk:14-alpine

ARG JAR_FILE=Util-0.1.1-exec.jar

COPY ${JAR_FILE} util.jar

EXPOSE 8184
EXPOSE 8080

CMD ["java", "-jar", "util.jar"]
# CMD ["java", "-Dspring.profiles.active=docker", "-jar", "user.jar"]