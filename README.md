# To-Do API

## Overview
To-Do API is a **RESTful API** built with **Spring Boot** for managing tasks and categories. The project supports **user authentication**, **task categorization**, and **task lifecycle management**. It is designed to handle to-do lists efficiently with secure access through **JWT-based authentication**.

This API is built using industry-standard practices and technologies like **Spring Boot**, **Hibernate**, **PostgreSQL**, and **Docker**, ensuring scalability and maintainability.

---

## Features

- **Authentication and Authorization**:
    - User registration and login with JWT.
    - Secure endpoints requiring authenticated users.
- **Task Management**:
    - Create, update, delete, and retrieve tasks.
    - Soft-delete functionality for tasks.
    - Retrieve tasks by status or category.
- **Category Management**:
    - Create and delete categories.
    - Associate tasks with categories.
- **Error Handling**:
    - Centralized exception handling with meaningful error messages.
    - Support for custom exceptions (e.g., `ResourceNotFoundException`, `AuthenticationException`).
- **API Documentation**:
    - Integrated **OpenAPI/Swagger** for easy testing and documentation.
- **Environment Configuration**:
    - `.env` support for environment-specific variables using `dotenv`.

---

## Technologies Used

- **Spring Boot**: Framework for building Java-based REST APIs.
- **Hibernate**: ORM framework for database interaction.
- **PostgreSQL**: Relational database.
- **JWT**: JSON Web Tokens for secure authentication.
- **Docker**: Containerization for easy deployment.
- **Spring Security**: To secure endpoints and handle authentication.
- **Spring Data JPA**: Simplified data access layer.

---

## Project Structure

The project is organized as follows:

```
src/main/java/com/todoapp/todo_api
│
├── audit                   # Auditing configuration
├── auth                    # Authentication-related components
├── common                  # Shared utilities and response models
├── config                  # Application and security configurations
├── constants               # Constant definitions
├── controller              # REST API controllers
├── dto                     # Data Transfer Objects for API communication
├── entity                  # JPA entity classes
├── enums                   # Enumerations
├── exception               # Custom exception handling
├── factory                 # Response object factory
├── mapper                  # Mapper classes for entity-DTO conversion
├── repository              # Data repositories
├── service                 # Business logic services
├── utils                   # Utility classes
```

---

## API Endpoints

### **Authentication**
| Method | Endpoint                | Description                  |
|--------|--------------------------|------------------------------|
| POST   | `/api/auth/register`     | Register a new user          |
| POST   | `/api/auth/authenticate` | Authenticate user and get JWT|

### **Task Management**
| Method | Endpoint                     | Description                      |
|--------|-------------------------------|----------------------------------|
| POST   | `/api/task/create`            | Create a new task               |
| PUT    | `/api/task/update`            | Update an existing task         |
| PUT    | `/api/task/trash/{id}`        | Soft-delete a task              |
| GET    | `/api/task/{id}`              | Retrieve a task by ID           |
| GET    | `/api/task/all`               | Retrieve all tasks              |
| GET    | `/api/task/all/{status}`      | Retrieve tasks by status        |
| GET    | `/api/task/category/{name}`   | Retrieve tasks by category name |

### **Category Management**
| Method | Endpoint                     | Description                      |
|--------|-------------------------------|----------------------------------|
| POST   | `/api/category/create`        | Create a new category            |
| DELETE | `/api/category/delete/{id}`   | Delete a category                |

---

## Prerequisites

- **Java 21**
- **Maven**
- **PostgreSQL**
- **Docker** (optional, for containerized deployment)

---

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/your-username/todo-api.git
cd todo-api
```

### 2. Configure Environment Variables
Create a `.env` file in the root directory:
```
DB_URL=jdbc:postgresql://localhost:5432/todo_db
DB_USERNAME=your_postgres_user
DB_PASSWORD=your_postgres_password
JWT_SECRET_KEY=your_jwt_secret_key
```

### 3. Build and Run
Using Maven:
```bash
mvn clean install
mvn spring-boot:run
```

Using Docker:
```bash
docker-compose up --build
```

### 4. Access the API
- Base URL: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`

---

## Deployment

To deploy the application, create a production-ready Docker image and deploy it using Kubernetes or any container orchestration tool.
