FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/auth_system-0.0.1.jar
COPY ${JAR_FILE} auth_system_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "auth_system_app.jar"]
