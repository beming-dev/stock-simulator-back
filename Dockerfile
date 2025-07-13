FROM gradle:8.10.2-jdk17 AS builder

WORKDIR /app
COPY . .
# wrapper 대신 gradle 커맨드를 바로 쓰거나, wrapper도 그대로 사용 가능
RUN gradle clean build --no-daemon

FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
