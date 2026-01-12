# Journal Management Application

## Overview
The Journal Management Application is a secure, REST-based backend system developed using Spring Boot.  
It allows users to create, manage, and maintain personal journal entries with proper authentication and authorization.

This project demonstrates backend development skills including REST API design, security, database integration, and clean architectural practices.

---

## Technologies Used
- Java
- Spring Boot
- Spring Security
- MongoDB Atlas
- RESTful APIs
- Maven
- JUnit
- Postman
- Git & GitHub

---

## Key Features
- User registration and authentication
- Role-based authorization using Spring Security
- CRUD operations for journal entries
- User-specific data ownership and access control
- Input validation for data integrity
- Integration with external Weather API to fetch live weather information
- Cloud-based data storage using MongoDB Atlas
- Clean layered architecture (Controller → Service → Repository)

---
## Project Structure

src
├── controller
├── service
├── repository
├── model
├── security
└── config 



---

## API Testing
- All REST endpoints were tested using **Postman**
- Unit tests were written using **JUnit** to validate core functionalities

---

## Configuration
Sensitive configuration files such as:
- `application.yml`
- `application.properties`

are **excluded from version control** for security reasons.

A sample configuration file can be used to understand required environment variables and settings.

---

## How to Run the Project (Local Setup)
1. Clone the repository
2. Configure MongoDB Atlas connection in `application.yml`
3. Configure Weather API key
4. Build the project using Maven
5. Run the application using Spring Boot

---

## Purpose
This project was developed to strengthen backend development skills and demonstrate practical knowledge of:
- Secure API development
- Database integration
- Industry-standard coding practices
- Version control using Git

---

## Author
**Chaitanya Kadam**  
M.Sc. Computer Science (2024)  
Backend Developer – Java & Spring Boot



