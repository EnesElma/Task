FROM gradle:jdk11 AS builder
WORKDIR /app
COPY ../build.gradle .
COPY src ./src
RUN gradle assemble

FROM openjdk:11-jre-slim
COPY --from=builder /app/build/libs/*.jar /app/my_app.jar
CMD java -jar /app/my_app.jar