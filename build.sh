#!/bin/bash
export MAVEN_OPTS="-Xms256m -Xmx512m"
mvn clean package -Dmaven.test.skip=true 
