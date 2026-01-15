# Step 1: Use Java 17 base image
FROM eclipse-temurin:17-jdk-alpine

# Step 2: Set working directory
WORKDIR /app

# Step 3: Copy jar file
COPY target/resume-screening-system-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose port
EXPOSE 8080

# Step 5: Run application
ENTRYPOINT ["java", "-jar", "app.jar"]
