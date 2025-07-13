FROM gradle:8.10.2-jdk17 AS builder
WORKDIR /app

# 1) 의존성 캐시용: build.gradle과 wrapper 설정만 복사
COPY build.gradle settings.gradle gradle.* ./
# 의존성만 미리 다운로드 (테스트 제외)
RUN gradle dependencies --no-daemon --stacktrace

# 2) 실제 소스 복사 및 빌드
COPY . .
RUN gradle clean build --no-daemon --stacktrace --info

# 런타임 이미지
FROM bellsoft/liberica-openjdk-alpine:17
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
