# Secure Student Management API

A RESTful API built with Spring Boot for managing student records with JWT-based authentication and role-based authorization.

## Features

- Complete CRUD operations for student management
- JWT-based stateless authentication
- Role-based access control (ADMIN and USER roles)
- Input validation and error handling
- H2 in-memory database
- Comprehensive unit tests
- Lombok integration for cleaner code

## Technologies Used

- Java 17
- Spring Boot 3.1.5
- Spring Security
- Spring Data JPA
- H2 Database
- JWT (JSON Web Tokens)
- Lombok
- Maven
- JUnit 5 & Mockito

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- Git

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/student-management-api.git
cd student-management-api

Build the project:

bashmvn clean install

Run the application:

bashmvn spring-boot:run
The application will start on http://localhost:8080
