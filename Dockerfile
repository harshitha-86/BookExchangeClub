# Start with a base image containing java runtime
FROM openjdk:14-jdk-alpine

#Add Maintainer info
LABEL maintainer="shashank136.sk@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's jar file
ARG JAR_FILE

# Add the application's jar to the container
ADD ${JAR_FILE} /app.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]