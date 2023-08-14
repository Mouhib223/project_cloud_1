# Stage 1: Build the application
FROM adoptopenjdk:11-jdk-hotspot AS builder
RUN apt-get update && \
    apt-get install -y maven
WORKDIR /app

# Copy the Maven project file(s) to the container
COPY pom.xml .

# Download the project dependencies
RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -B

# Copy the source code to the container
COPY src ./src

# Build the application
RUN mvn package 

# Stage 2: Create a minimal image to run the application
FROM adoptopenjdk:11-jre-hotspot

WORKDIR /app

# Copy the compiled application from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Set the command to run the application
CMD ["java", "-jar", "app.jar"]


