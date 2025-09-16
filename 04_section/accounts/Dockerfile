

# docker build . -t eroskoller/accounts:s04
#FROM openjdk:21-jdk-slim
FROM eclipse-temurin:21-alpine


#MAINTAINER eazybytes.com
LABEL maintainer="eazybytes.com"

COPY target/accounts-s04.jar accounts-s04.jar

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]


#docker run --name accounts-s04 -d -p 8080:8080 eroskoller/accounts:s04


