
# Blog Application - REST API

## Project Description

This is a **Blog Application** built using **Spring Boot**, **Spring Security**, and **JWT** for managing users, posts, comments, and user roles. The system handles authentication and authorization using **JSON Web Tokens (JWT)** and provides full **CRUD** functionality for users, posts, comments, and roles. The project is structured following best practices for REST API architecture and adheres to **Spring Framework** guidelines.

## Technologies Used

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Hibernate / JPA**
- **MySQL / PostgreSQL (configurable)**
- **Lombok**
- **Maven**

## Features

### Users

- **User registration** with automatic role assignment (`ROLE_USER`)
- **Login** and JWT token generation
- **CRUD operations** for users (admins can modify or delete other users)
- **Email uniqueness check** during registration
- User data update (first name, last name, email)
- Secure password storage using **BCryptPasswordEncoder**

### Posts

- **Create, update, and delete posts** (by the post owner or admin)
- Associate posts with categories
- Support for **multiple categories** per post
- **One-to-Many** relationship with comments
- Automatic entity-to-DTO mapping

### Comments

- **Add comments** to posts (for authenticated users)
- Update and delete comments (by the comment author or admin)
- **Many-to-One** relationship with users and posts

### Roles

- **Role management** for users using **Many-to-Many** relationships (e.g., `ROLE_USER`, `ROLE_ADMIN`)
- Automatic role assignment (`ROLE_USER`) during registration
- Admins can manage user roles

### Authentication & Authorization

- **JWT token** generation during login and used for authorizing subsequent API requests
- Secure CRUD operations for protected resources using **Spring Security**
- Role-based access control determines which users can access or modify certain resources (e.g., only admins can delete posts)

### Categories

- Manage post categories
- **Many-to-One** relationship with posts

### Exception Handling

- Global exception handling using **@ControllerAdvice** for errors like:
  - Resource not found (e.g., user, post)
  - Unauthorized access to resources
  - Failed authentication

## Project Architecture

The project follows a typical **Spring Boot** structure with distinct layers:

1. **Controller** – Handles HTTP requests and returns JSON responses.
2. **Service** – Contains the business logic of the application.
3. **Repository** – Data access layer using **JPA** and **Hibernate** to interact with the database.
4. **DTO (Data Transfer Object)** – Objects used to transfer data between layers, separating business logic from data representation.
5. **Entities** – JPA entities that represent database tables.


---

This description is ready for you to paste into your GitHub `README.md`. You can customize the links to your LinkedIn and GitHub profiles as well as modify the content to fit any additional features or details you'd like to include.

Let me know if you'd like any further modifications!
