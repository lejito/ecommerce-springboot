# Etapa 1: Build de la aplicación
FROM maven:3.9.6-eclipse-temurin-24 AS builder

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar archivos del proyecto
COPY complete /app

# Construir el JAR con Maven
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final para ejecución
FROM openjdk:24-jdk-slim

# Directorio de trabajo en la imagen final
WORKDIR /app

# Copiar el JAR desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto 8080 (API REST)
EXPOSE 8080

# Comando para ejecutar la API
CMD ["java", "-jar", "app.jar"]
