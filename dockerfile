# Estágio de compilação
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Instalação de dependências do frontend
RUN apt-get update && \
    apt-get install -y nodejs npm && \
    npm install -g npm@latest

# Copia o código-fonte do aplicativo
COPY pom.xml .
COPY src ./src

# Compilação do backend
RUN mvn dependency:go-offline && \
    mvn package -DskipTests

# Compilação do frontend (exemplo)
RUN cd src/main/frontend && \
    npm install && \
    npm run build

# Estágio de produção
FROM eclipse-temurin:17-jre
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
