FROM openjdk:14-alpine

ARG JAR_FILE=User-0.1.1-exec.jar

COPY ${JAR_FILE} user.jar

EXPOSE 8182
EXPOSE 8080
EXPOSE 8082

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "user.jar"]
# CMD ["java", "-Dspring.profiles.active=docker", "-jar", "user.jar"]