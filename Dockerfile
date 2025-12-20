# --- Estágio 1: A Fábrica (Construção) ---
# Usamos uma imagem com o JDK completo para compilar o projeto
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copia os arquivos do projeto para dentro do container
COPY . .

# Dá permissão de execução para o script do Maven e compila o projeto
# O flag -DskipTests agiliza o processo pulando testes unitários por enquanto
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# --- Estágio 2: A Loja (Execução) ---
# Usamos uma imagem apenas com o JRE (mais leve) só para rodar o app
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copia apenas o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# Informa que o app roda na porta 4000 (igual configuramos no application.properties)
EXPOSE 4000

# O comando que faz o app ligar
ENTRYPOINT ["java", "-jar", "app.jar"]