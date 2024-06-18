# Java API for Run Management

## Overview

This Java API provides endpoints to manage runs and users. It includes operations to create, retrieve, update,and delete run records.
Additionally, there is a REST client integrated to interact with another service (https://jsonplaceholder.typicode.com/) to get users information, enabling seamless integration between microservices.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Maven (Dependency Management)
- Postgres
- Docker Compose (for Postgres)

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 11 or higher installed
- Maven installed
- Docker Desktop
- Docker Compose
## Getting Started

To get a local copy up and running follow these simple steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/FayeAlangi/runnerz.git
2. **Navigate into the project directory**:
   ```bash
   mvn clean install
3. **Run the application**
    ```bash
    mvn spring-boot:run
4. **API Endpoints**

- `GET /api/runs`: Retrieve all runs
- `GET /api/runs/{id}`: Retrieve a specific run by ID
- `POST /api/runs`: Create a new run (requires JSON payload)
- `PUT /api/runs/{id}`: Update an existing run (requires JSON payload)
- `DELETE /api/runs/{id}`: Delete a run by ID
- `GET /api/runs/location/{location}`: Retrieve runs by location

License
This project is licensed under the MIT License - see the LICENSE file for details.