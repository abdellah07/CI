#!/bin/sh

mvn clean package -DskipTests

docker-compose up -d