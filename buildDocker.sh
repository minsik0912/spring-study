#!/bin/bash

rm -rf ./app.jar
./gradlew clean build -x test
mv ./build/libs/*.jar ./app.jar

docker build -t consumer:latest .

docker-compose down
docker-compose up -d
docker-compose logs -f --tail 200