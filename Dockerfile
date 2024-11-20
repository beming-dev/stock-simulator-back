# 1단계: 빌드 단계
FROM bellsoft/liberica-openjdk-alpine:17 AS builder

WORKDIR /app

COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean build --no-daemon

# 2단계: 실행 단계
FROM bellsoft/liberica-openjdk-alpine:17

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]