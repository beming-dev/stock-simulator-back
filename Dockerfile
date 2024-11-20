# 1단계: 빌드 단계
FROM bellsoft/liberica-openjdk-alpine:17 AS builder

WORKDIR /app

COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew clean build --no-daemon

# 2단계: 실행 단계
FROM bellsoft/liberica-openjdk-alpine:17
git remote add origin https://github.com/beming-dev/stock-simulator-back.git
git branch -M main
git push -u origin main
WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]