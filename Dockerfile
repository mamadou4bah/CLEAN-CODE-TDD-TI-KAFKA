# Étape de build
FROM maven:3.9.4-eclipse-temurin-21-alpine AS builder

# Copier d'abord le pom.xml
WORKDIR /build
COPY si-pmu-api/pom.xml .
COPY si-pmu-api/src ./src

# Package l'application
RUN mvn clean package -DskipTests

# Étape de production
FROM eclipse-temurin:21
WORKDIR /app
COPY --from=builder /build/target/*.jar app.jar
EXPOSE 8099
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","app.jar"]