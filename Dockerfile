# Multi-Stage Build Dockerfile for Student Management System
# Build Stage
FROM maven:3.9.6-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests=true

# Production Runtime Stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create a secure non-root user
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# Copy built JAR from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose default application port
EXPOSE 8081
ENV PORT=8081

# JVM flags optimized for container memory constraints
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
