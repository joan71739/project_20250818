# 任務管理系統 (Task Management System)

<div style="display: flex; justify-content: center; gap: 16px; flex-wrap: wrap;">
  <img src="https://img.icons8.com/?size=100&id=90519&format=png&color=000000" alt="Show Image" />
  <img src="https://img.icons8.com/?size=100&id=FBO05Dys9QCg&format=png&color=000000" alt="Show Image" />
  <img src="https://img.icons8.com/?size=100&id=21888&format=png&color=000000" alt="Show Image" />
  <img src="https://img.icons8.com/?size=100&id=cdYUlRaag9G9&format=png&color=000000" alt="Show Image" />
  <img src="https://img.icons8.com/?size=100&id=12599&format=png&color=000000" alt="Show Image" />
  <img src="https://img.icons8.com/?size=100&id=61959&format=png&color=000000" alt="Show Image" />
</div>

一個基於 **Spring Boot 3.5.4** 開發的 RESTful API 後端服務，提供任務管理功能與 JWT 身份驗證機制。

---

## 🔗 完整系統架構
- 後端：Spring Boot RESTful API
- 前端：React
- 功能：任務 CRUD 與 JWT 登入介面

---

## 🔗 相關專案
- 前端專案: **React Task Management Frontend** - 使用 React 開發的前端介面
- 線上 Demo:
    - 前端應用: [https://todo-app-production-41a9.up.railway.app](https://todo-app-production-41a9.up.railway.app)
    - 後端 API: [https://project20250818backend-production.up.railway.app/api](https://project20250818backend-production.up.railway.app/api)

---

## 📋 專案特色
- **JWT 身份驗證**：實作完整的 Token-based 認證機制
- **RESTful API 設計**：遵循 REST 架構風格的 API 端點
- **CORS 跨域支援**：支援前後端分離架構
- **Docker 容器化**：提供 Dockerfile 支援容器化部署
- **環境變數配置**：使用環境變數管理敏感資訊

---

## 🛠 技術棧
**後端框架**
- Spring Boot 3.5.4
- Spring Security
- Spring Web

**開發工具**
- Java 21
- Maven
- Lombok
- JWT
- Intellij

**容器化**
- Docker

**版控**
- github

**部屬**
- Railway

---

## 📚 API 文件

### 認證相關
**登入**
```http
POST /api/login
```
Request Body:
```json
{
  "username": "user",
  "password": "0000"
}
```
Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 任務管理
(需攜帶 `Authorization: Bearer <token>`)

**取得所有任務**
```http
GET /api/tasks
```
Response:
```json
[
  {
    "id": "1",
    "title": "學習React",
    "status": "進行中",
    "detail": "學習React的基礎知識",
    "due": "2025/08/21"
  },...
]
```

**新增任務**
```http
POST /api/tasks
```
Request:
```json
{
  "title": "新任務",
  "status": "待辦",
  "detail": "任務描述",
  "due": "2025/12/31"
}
```

**更新任務**
```http
PUT /api/tasks/{id}
```
Request:
```json
{
  "title": "更新的任務",
  "status": "已完成",
  "detail": "更新的描述",
  "due": "2025/12/31"
}
```

**刪除任務**
```http
DELETE /api/tasks?id={id}
```

---

## 🔧 環境變數配置

### 後端環境變數
| 變數名稱             | 說明                  | 預設值                | 生產環境值                                      |
| -------------------- | --------------------- | --------------------- | ----------------------------------------------- |
| JWT_SECRET           | JWT 簽名密鑰 (Base64) | 內建預設值            | 需自行生成                                      |
| JWT_EXPIRATION       | Token 過期時間        | 3600000               | 3600000                                         |
| CORS_ALLOWED_ORIGINS | 允許的前端來源        | http://localhost:3000 | https://todo-app-production-41a9.up.railway.app |
| SPRING_USER_NAME     | 登入用戶名            | user                  | user                                            |
| SPRING_USER_PASSWORD | 登入密碼              | 0000                  | 強密碼                                          |
---

## 📂 專案結構
```
src/main/java/com/example/project/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── TaskController.java
│   └── loginController.java
├── dto/
│   └── TaskDTO.java
├── exception/
│   └── UnknownStatusException.java
├── model/
│   ├── JwtResponse.java
│   └── Task.java
├── security/
│   └── JwtAuthenticationFilter.java
├── service/
│   └── TaskService.java
└── util/
    └── JwtUtil.java
```

---

## 🔒 安全性特點
- JWT Token 認證機制
- Spring Security 框架保護
- CORS 跨域請求控制
- 環境變數管理敏感資訊
- Base64 編碼密鑰

---

## 📝 開發筆記

### 任務狀態
- 待辦 (TODO)
- 進行中 (IN_PROGRESS)
- 已完成 (DONE)

### 日期格式
- 格式：`yyyy/MM/dd`

---

## 👤 作者
GitHub: [@joan71739](https://github.com/joan71739)


> 此專案為個人作品集展示用途