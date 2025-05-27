# 📘 BayzDelivery - DOC.md

This document provides all necessary steps to set up, run, and verify the BayzDelivery backend application locally.

---

## ✅ Prerequisites

Before starting, make sure the following tools are installed:

- **Java 17**
- **Gradle**
- **Docker & Docker Compose**
- **Git**
- Optional: **Postman** or **cURL** (for testing API endpoints)

---

## 📦 Clone the Repository

```bash
git clone git@github.com:SahityaGupta/bayz-delivery.git
cd backend-assignment
```

---

## 🐳 Start PostgreSQL Using Docker

The project includes a pre-configured Docker Compose setup. To start the database:

```bash
docker-compose up -d
```

This command will start a PostgreSQL container using the configuration in `docker-compose.yml`.

---

## ⚙️ Configuration

Check and configure the database settings in:

```
src/main/resources/application.yml
```

Ensure values like `spring.datasource.url`, `username`, and `password` match your Docker setup (usually `postgres:postgres`).

---

## 🛠️ Run the Application

### Option 1: Using Gradle

```bash
./gradlew bootRun
```

### Option 2: Build and Run the JAR

```bash
./gradlew build
java -jar build/libs/bayz-delivery-0.0.1-SNAPSHOT.jar
```

The application will start at:

```
http://localhost:8081/
```

---

## 📲 API Endpoints

### Submit Delivery (existing endpoint)

```http
POST /api/deliveries
```

**Request Body:**
```json
{
  "deliveryManId": 1,
  "customerId": 2,
  "orderId": 1234,
  "distance": 4.5,
  "startTime": "2024-05-01T10:00:00",
  "endTime": "2024-05-01T10:30:00"
}
```

---

### 🔥 New Feature: Top 3 Delivery Men by Commission

```http
GET /api/deliverymen/top3?start=2024-05-01T00:00:00&end=2024-05-27T23:59:59
```

**Response Example:**
```json
{
  "topDeliveryMen": [
    {
      "deliveryManId": 101,
      "totalCommission": 550.0
    },
    {
      "deliveryManId": 104,
      "totalCommission": 500.0
    },
    {
      "deliveryManId": 107,
      "totalCommission": 475.0
    }
  ],
  "averageCommission": 295.67
}
```

---

## ⏰ Scheduled Task

A scheduled job runs **every 5 minutes** to check if any delivery is taking more than 45 minutes. If found, it logs a message like:

```
[ALERT] Delivery ID 987 has exceeded 45 minutes. Notify Customer Support.
```

No real notifications are sent—just printed messages.

---

## ✅ Bug Fixes Summary

- Prevented delivery men from delivering multiple orders at the same time.
- Fixed commission calculation:  
  `Commission = OrderPrice * 0.05 + Distance * 0.5`
- Ensured user can only register as either customer or delivery man, not both.

---