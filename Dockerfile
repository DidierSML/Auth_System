# ---- Fase de Construcción con Maven ----
FROM maven:3.9.7-eclipse-temurin-17 AS builder
WORKDIR /app

# Copia del .env.example como referencia (documentación)
COPY .env.example .env.example

# Copia del POM y descarga dependencias (cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el código fuente y construcción del JAR
COPY src ./src
RUN mvn clean package -DskipTests

# ---- Fase de Ejecución ----
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

# Nombre del JAR (auth_system_app.jar)
ARG JAR_FILE=auth_system-0.0.1.jar
COPY --from=builder /app/target/${JAR_FILE} auth_system_app.jar

# Copia del .env.example al contenedor (solo como guía)
COPY --from=builder /app/.env.example .env.example

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "auth_system_app.jar"]
