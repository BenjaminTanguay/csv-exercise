#!/bin/bash


./mvnw clean package

# Define the path to the JAR file
JAR_PATH="target/csv-exercise-fat.jar"

# Check if the JAR file exists
if [ -f "$JAR_PATH" ]; then
    echo "Starting the JAR file: $JAR_PATH"
    # Start the JAR file
    java -jar "$JAR_PATH"
else
    echo "JAR file not found at: $JAR_PATH"
    echo "Please build the project or verify the path."
    exit 1
fi
