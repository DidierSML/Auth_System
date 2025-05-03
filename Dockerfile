FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/auth_system-0.0.1.jar
COPY ${JAR_FILE} auth_system_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "auth_system_app.jar"]

# ---- Fase de Construcción con Maven ----
FROM maven:3.9.7-eclipse-temurin-17 AS builder
WORKDIR /app

# 1. Copia el POM y descarga dependencias (cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2. Copia el código fuente y construye el JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Fase de Ejecución ----
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Copia el JAR desde la fase de construcción
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto (debe coincidir con tu aplicación Spring Boot)
EXPOSE 8080

# Comando de inicio
ENTRYPOINT ["java", "-jar", "auth_system_app.jar"]
