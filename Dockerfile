FROM eclipse-temurin:17-jdk-alpine
COPY target/*.jar groomerWebApp.jar
ENTRYPOINT ["java", "-jar", "groomerWebApp.jar"]