# Job Portal Backend

A Spring Boot based Job Portal application that enables recruiters to post and manage jobs while allowing candidates to search and apply for job opportunities. The application provides secure authentication, role-based authorization, company management, and job application workflows.

## Features

* User Registration and Authentication
* JWT-based Security
* Role-Based Access Control (Admin, Recruiter, Candidate)
* Company Management
* Job Posting and Management
* Candidate Job Applications
* Contact Management
* Global Exception Handling
* Request and Session Scoped Beans
* Audit Logging with Spring AOP
* Performance Monitoring using Aspects
* RESTful APIs

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* Maven

### Database

* MySQL

### Security

* JWT Authentication
* Spring Security

### Other Technologies

* Spring AOP
* Lombok
* Logback

## Project Structure

```text
src
├── controller
├── service
├── serviceImpl
├── repository
├── entity
├── dto
├── security
├── aspect
├── exception
├── config
└── util
```

## Getting Started

### Prerequisites

* Java 17+
* Maven 3.8+
* MySQL 8+
* Git

### Clone Repository

```bash
git clone https://github.com/Arpitha1212/Job-Portal-Backend-.git
cd Job-Portal-Backend-
```

### Configure Database

Update `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jobportal
spring.datasource.username=root
spring.datasource.password=your_password
```

### Build Project

```bash
mvn clean install
```

### Run Application

```bash
mvn spring-boot:run
```

Application will start on:

```text
http://localhost:8080
```

## API Modules

### Authentication

* Register User
* Login User
* JWT Token Generation

### Company Management

* Create Company
* Update Company
* Delete Company
* View Company Details

### Job Management

* Create Job
* Update Job
* Delete Job
* Search Jobs

### Contact Management

* Submit Contact Requests
* View Contact Requests

## Security Features

* JWT Authentication
* Password Encryption
* Role-Based Authorization
* Protected API Endpoints

## Logging & Monitoring

* Application Logging with Logback
* Audit Logging using Spring AOP
* Exception Tracking
* Performance Monitoring

## Future Enhancements

* Resume Upload
* Job Recommendation Engine
* Email Notifications
* Interview Scheduling
* Microservices Architecture
* Docker & Kubernetes Deployment

## Author

**Arpitha**

Backend Developer | Java | Spring Boot | Microservices
