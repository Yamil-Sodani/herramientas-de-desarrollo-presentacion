FROM maven:3.8.1-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/rental-system-1.0.0.jar .
EXPOSE 8080
CMD ["java", "-jar", "rental-system-1.0.0.jar"]
