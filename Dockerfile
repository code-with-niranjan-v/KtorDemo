# Use a base image with JDK and Gradle installed
FROM gradle:jdk11 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy only the necessary files needed for dependency resolution
COPY build.gradle.kts settings.gradle.kts /app/

# Copy the source code
COPY src/ /app/src/

# Resolve dependencies
RUN gradle resolveDependencies --no-daemon

# Build the application
RUN gradle build --no-daemon

# Create a new image with only JRE
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from the build stage to the container
COPY --from=build /app/build/libs/DemoKtorServer.jar /app/DemoKtorServer.jar

# Expose the port that your Ktor server listens on
EXPOSE 8080

# Command to run your application when the container starts
CMD ["java", "-jar", "/app/DemoKtorServer.jar"]