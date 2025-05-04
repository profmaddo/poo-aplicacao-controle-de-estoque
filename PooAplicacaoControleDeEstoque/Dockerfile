# Dockerfile para Sistema de Controle de Estoque
FROM openjdk:22

LABEL authors="marcomaddo"

# Diretório de trabalho dentro do container
WORKDIR /app

# Copia o código da aplicação para dentro do container
COPY app/ /app
COPY lib/sqlite-jdbc-3.49.1.0.jar /app/lib/

# Copia o banco de dados para um volume (se vazio)
COPY data/ /app/data/


# Comando para executar o sistema (exemplo para .class, depois mostro para .jar também)
CMD ["java", "-cp", ".:/app/lib/sqlite-jdbc-3.49.1.0.jar", "MenuSistemaDeEstoqueV1"]