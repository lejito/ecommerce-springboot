# Etapa 1: Build de la aplicación
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Crear un directorio de trabajo
WORKDIR /app

#copia el proyecto al contenedor
COPY complete /app

# generar el archivo JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecución
FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
