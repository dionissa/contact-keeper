# Estágio de compilação
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app

# Copia o código-fonte do aplicativo
COPY pom.xml .
COPY src ./src

# Compilação do backend
RUN mvn package -DskipTests

# Estágio final
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o arquivo JAR construído do estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Expor a porta 8080 (se necessário)
EXPOSE 8080

# Comando de entrada para executar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]