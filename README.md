# Your Java Spring Application with Dockerized Database

This repository contains a Java Spring application built using the hexagonal architecture, along with a Docker Compose configuration for setting up the database. Below, you'll find instructions on how to install and deploy the application.

## Improvement ideas
- Secure API (e.g. Bearer token)
- Document the API (e.g. swagger)
- Tests
- SMTP Configuration file
- ...


## Prerequisites

Before you proceed, ensure you have the following software installed on your system:

- Java 17
- Apache Maven
- Docker
- Docker Compose

## Installation

Follow these steps to set up and run the application:

1. Clone this repository to your local machine:

   ```shell
   git clone https://github.com/Skrys33/FreightManagerBack.git
   cd FreightManagerBack
   ```

2. Build the Spring application using Maven:

   ```shell
   mvn clean package install
   ```

3. Build FreightManagerBack docker image :
   ```shell
   docker build -t freight-manager-back:spring-docker .
   ```

4. Start the Docker Compose to initialize the database:
   ```shell
    docker-compose up -d
   ```
