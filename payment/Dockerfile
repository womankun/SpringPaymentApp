# ビルド用ステージ
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# ネットワークとTLS対応のため証明書更新
RUN apt-get update && apt-get install -y ca-certificates && update-ca-certificates

# ソースコードコピー
COPY . .

# Gradleキャッシュを活用しつつビルド
RUN gradle build --no-daemon --refresh-dependencies

# 実行用ステージ
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 55555
CMD ["java", "-jar", "app.jar"]
