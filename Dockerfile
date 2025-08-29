# 第一階段：Build (用 Maven + JDK)
FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

# 複製 Maven 設定與程式碼
COPY pom.xml mvnw ./
COPY .mvn .mvn

# Railway 部署必须添加这行
RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline -DskipTests

COPY src src

# 打包出 jar
RUN ./mvnw package -DskipTests

# 第二階段：Run (只放 JRE + jar)
FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]