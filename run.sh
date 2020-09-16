#!/bin/sh
cd fravega-challenge-backend
mvn clean package
docker-compose build --no-cache
docker-compose up
