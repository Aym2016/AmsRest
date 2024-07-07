FROM openjdk:17-slim

# Create app directory
WORKDIR /app

# Copy dependencies and sources
COPY mvnw .
COPY pom.xml .
COPY src ./src

# Build spring rest app
RUN ./mvnw package -DskipTests

# Copy fat jar to app directory
COPY target/app-0.0.1-SNAPSHOT.jar app.jar 

# Expose port
EXPOSE 9001

# Run jar on container startup
CMD ["java", "-jar", "app.jar"]