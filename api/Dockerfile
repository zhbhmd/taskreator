# Use Amazon Corretto 17 as the base image
FROM amazoncorretto:17

# Set the working directory in the container
WORKDIR /app

# Copy the files to the container
COPY . .

# for macos 
RUN chmod +x mvnw

# Build the Maven project
RUN ./mvnw clean install

EXPOSE 8080

# Specify the default command to run when the container starts
CMD ["java", "-jar", "target/zhbhmd-avow-task.jar"]