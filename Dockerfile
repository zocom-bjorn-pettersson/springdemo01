FROM openjdk:17

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./

RUN ./mvnw dependency:go-offline

COPY src ./src

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
