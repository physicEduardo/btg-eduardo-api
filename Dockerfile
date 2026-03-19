# Usar una imagen base de Java 21
FROM eclipse-temurin:21-jdk-alpine

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos de construcción y dependencias
COPY build/libs/*.jar app.jar

# Exponer el puerto en el que corre la app (ajusta si es necesario)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
