FROM eclipse-temurin:17

LABEL maintainer = "abhijeet1992tiwari@gmail.com"
WORKDIR /myApp
COPY target/service-registry-0.0.1-SNAPSHOT.jar /myApp/serviceregistry-docker-jar
ENTRYPOINT ["java","-jar","serviceregistry-docker-jar"]