FROM openjdk:17-alpine

EXPOSE 8083

WORKDIR /src

COPY /target/*.jar  /src/ms-authentication.jar

ENTRYPOINT ["java", "-jar", "/src/ms-authentication.jar"]