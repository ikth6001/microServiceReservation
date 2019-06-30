#!/bin/bash

/app/wait-for-it.sh -t 0 config-server:8760 -- echo "config boot"
java -Dspring.profiles.active=dev -jar /app/msreserve.eureka-0.0.1-SNAPSHOT.jar
