# Microservices Portfolio Project

A **Full Microservices Architecture** project built with **Spring Boot 3** and **Java 21**, demonstrating a modular, scalable, and production-ready system with multiple interconnected services.

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot 3, Java 21  
- **Database:** MySQL  
- **API Gateway:** Spring Cloud Gateway  
- **Authentication & Security:** Keycloak (OAuth2, JWT)  
- **Messaging / Event-Driven:** Apache Kafka  
- **Database Migrations:** Flyway  
- **Containerization & Deployment:** Docker, Docker Compose  
 

---

## 🏗️ Project Structure

| Service | Description |
|---------|-------------|
| `apigateway` | API Gateway routing requests to backend services |
| `bookingservice` | Handles booking requests and customer management |
| `orderservice` | Processes orders and communicates with inventory |
| `inventoryservice` | Manages event/venue inventory and ticket availability |

---

## 🚀 Features

- **Microservices Architecture:** Modular services for product, order, booking, and inventory management.  
- **Event-Driven Communication:** Uses Apache Kafka to propagate events between services.  
- **Authentication & Authorization:** Secure endpoints using Keycloak with OAuth2 and JWT.  
- **Database Migrations:** Flyway-managed schema evolution for PostgreSQL/MySQL.  
- **Containerized Deployment:** Docker & Docker Compose for easy local setup and testing.  

---

## ⚡ Getting Started

### Prerequisites

- Java 21 (JDK)
- Maven
- Docker & Docker Compose
-  MySQL


### Running the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/RaHuL-2104/microservices-project.git
   cd microservices-project
2. Build and run each microservice:

./mvnw clean install
./mvnw spring-boot:run


3. Start Docker containers (for DBs, Kafka, Keycloak):

docker-compose up


4. Access API Gateway on:

http://localhost:8080
