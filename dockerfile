# Use a imagem oficial do OpenJDK como base
FROM openjdk:11-jre-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o código fonte da sua aplicação para o contêiner
COPY . /app

# Instale o Maven (caso esteja usando Maven)
RUN apt-get update && apt-get install -y maven

# Instale as dependências da aplicação
RUN mvn install

# Construa o artefato da aplicação
RUN mvn package

# Defina o comando de inicialização da aplicação
CMD ["java", "-jar", "contact-keeper-1.0-SNAPSHOT.jar"]
