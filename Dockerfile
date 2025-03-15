FROM openjdk:21-slim AS builder
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml mvnw ./
COPY .mvn .mvn
COPY src /app/src
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

FROM openjdk:21-slim
RUN apt-get update && apt-get install -y curl jq
WORKDIR /app
COPY --from=builder /app/target/hubspot-0.0.1-SNAPSHOT.jar /app/hubspot.jar
COPY .env /app/.env
COPY ngrok-start.sh /app/ngrok-start.sh
RUN chmod +x /app/ngrok-start.sh
EXPOSE 8080

CMD ["/app/ngrok-start.sh"]
