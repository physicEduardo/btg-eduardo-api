# BTG Java Spring Boot Project

This project is a backend application built with Java, Spring Boot, and MongoDB. It provides a RESTful API for managing clients, funds and operations, with JWT-based authentication and notification services.

## Features
- User registration and login with JWT authentication
- Fund subscription and cancellation for clients
- Notification system (email and SMS simulation)
- MongoDB integration for data persistence
- RESTful API endpoints for funds, operations, and authentication
- Unit tests for controllers, services, utils, and security
- Docker and Docker Compose support for easy deployment

## Requirements
- Java 21
- Gradle
- Docker & Docker Compose

## Running Locally
1. **Build the JAR:**
   ```sh
   ./gradlew clean build
   ```

2. **API Access:**
   - The backend will be available at `http://localhost:8080`

## Environment Variables
- JWT secret and expiration should be configured in `application.yaml`.

## Notes
- The notification system is simulated (prints to console).
- No credentials are set for MongoDB by default.
- For production, update secrets and consider enabling MongoDB authentication.

---
