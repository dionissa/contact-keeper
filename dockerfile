# Estágio de compilação
FROM maven:3.8.3-openjdk-17 as build
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
ADD . /usr/src/app/

RUN mvn package

# Estágio de execução
FROM openjdk:17-jdk-alpine

RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app

COPY --from=build /usr/src/app/target/contact-keeper-1.0-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
