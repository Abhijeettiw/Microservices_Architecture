FROM eclipse-temurin:17
LABEL maintainer = "abhijeet1992tiwari@gmail.com"
WORKDIR /employee
COPY target/Employee-0.0.1-SNAPSHOT.jar /employee/employee-docker-jar
ENTRYPOINT ["java","-jar","employee-docker-jar"]