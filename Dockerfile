# 第一階段：Build (用 Maven + JDK)
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# 複製 Maven 設定
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Railway 必須：確保 mvnw 可執行
RUN chmod +x ./mvnw

# 下載依賴（利用 Docker 快取）
RUN ./mvnw dependency:go-offline -DskipTests

# 複製源碼並打包
COPY src src
RUN ./mvnw package -DskipTests

# 第二階段：Run (只放 JRE + jar)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

# 複製 jar 檔
COPY --from=build /app/target/*.jar app.jar

# Railway 會自動設定 PORT 環境變數
EXPOSE 8080

# Railway 免費方案優化（512MB 記憶體限制）
# -Xmx400m: 最大堆記憶體 400MB（留一些給系統）
# -XX:+UseSerialGC: 單執行緒 GC，適合小記憶體
# -Dserver.port=${PORT:-8080}: 使用 Railway 的 PORT 環境變數
ENTRYPOINT ["sh", "-c", "java -Xmx400m -XX:+UseSerialGC -Dserver.port=${PORT:-8080} -jar app.jar"]