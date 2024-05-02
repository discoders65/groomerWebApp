#!/bin/bash

# Define variables
GROOMER_WEB_DIR="/home/wowo/Documents/groomerBookingApp/groomerWebApp/"
GROOMER_MSG_DIR="/home/wowo/Documents/groomerBookingApp/groomerMsg/"
IMAGE_NAME="wjadczak/groomer_booking_app"
TAG="latest"

# Ensure Docker is running
if ! command -v docker &> /dev/null; then
    echo "Docker is not installed or not accessible."
    exit 1
fi

# Package GroomerWebApp without running tests
cd "$GROOMER_WEB_DIR" || exit 1
mvn clean package -DskipTests

# Package GroomerMsg without running tests
cd "$GROOMER_MSG_DIR" || exit 1
mvn clean package -DskipTests

# Build and push the Docker images to Docker Hub
docker build -t "$IMAGE_NAME:groomer_web_app-$TAG" "$GROOMER_WEB_DIR"
docker build -t "$IMAGE_NAME:groomer_msg-$TAG" "$GROOMER_MSG_DIR"

docker push "$IMAGE_NAME:groomer_web_app-$TAG"
docker push "$IMAGE_NAME:groomer_msg-$TAG"

# Check if push was successful
if [ $? -eq 0 ]; then
    echo "Images pushed successfully to Docker Hub."
else
    echo "Failed to push images to Docker Hub."
    exit 1
fi

exit 0
