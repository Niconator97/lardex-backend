# 🧾 Lardex Backend

Backend for a mobile application to manage household inventory and
shopping lists.

------------------------------------------------------------------------

## 🚀 Tech Stack

-   Kotlin
-   Spring Boot
-   PostgreSQL (Docker)
-   Flyway (database migrations)
-   Spring Security *(in progress)*
-   OpenAPI / Swagger

------------------------------------------------------------------------

## 📚 API Documentation

👉 Swagger UI\
http://localhost:8080/docs

👉 OpenAPI Schema\
http://localhost:8080/openapi\
http://localhost:8080/openapi.yaml

------------------------------------------------------------------------

## 🧱 Architecture

Monolithic backend using a **feature-first structure** with clear layer
separation.

    com.example.lardexbackend
    ├── infrastructure
    ├── inventory
    ├── shared
    ├── shoppinglist
    └── LardexBackendApplication.kt

------------------------------------------------------------------------

## 🧩 Layer Responsibilities

Layer            Responsibility
  ---------------- -------------------------------------------------
api              Controllers, DTOs
application      Business logic (use cases)
domain           Entities, enums, value objects
persistence      Database access
infrastructure   Configuration, security, cross-cutting concerns

------------------------------------------------------------------------

## 🧪 Features

### 📦 Inventory

-   Create inventory items
-   Retrieve inventory items
-   Household scope handled server-side

### 🛒 Shopping List

-   Basic structure implemented
-   Further functionality planned

------------------------------------------------------------------------

## 📡 API

### Base URL

    http://localhost:8080/api/v1

### Example Endpoints

    POST /inventory/items
    GET  /inventory/items

------------------------------------------------------------------------

## 🐳 Local Setup

### 1. Start PostgreSQL

``` bash
docker compose up -d
```

### 2. Start Backend

``` bash
./gradlew bootRun
```

------------------------------------------------------------------------

## 🔐 Authentication Concept (Current State)

-   Uses a stub: `CurrentUserContextProvider`
-   Real authentication (JWT) planned

👉 Household context is resolved server-side

------------------------------------------------------------------------

## 🧪 Testing

-   Unit tests for application layer
-   Controller tests using MockMvc
-   Feature-based test structure

------------------------------------------------------------------------

## 🔮 Roadmap

-   [ ] JWT authentication
-   [ ] User management
-   [ ] Complete shopping list feature
-   [ ] Transfer shopping list → inventory
-   [ ] Deployment (Docker / Kubernetes)

------------------------------------------------------------------------

## ⚠️ Notes

-   Swagger is currently publicly accessible
-   Security will be tightened later


------------------------------------------------------------------------

## 📦 Status

🚧 In development
