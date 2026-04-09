# Breaking.Bank 🏦💎

Welcome to **Breaking.Bank**, a microservices-based banking application built with Spring Boot. This project explores modern financial architecture with a touch of "Heisenberg" wit.

## Project Overview
Breaking.Bank is designed as a scalable system composed of specialized microservices. The goal is to provide a robust API for banking operations while maintaining clean code and high observability.

---

## Service: Account Microservice (MS)

The **Account MS** is the heart of the system, responsible for managing customer accounts, balances, and core banking entities.

### 🛠 Tech Stack & Dependencies
This service leverages the following Spring Boot ecosystem components:

* **Spring Web**: Builds RESTful APIs and uses Apache Tomcat as the default embedded container.
* **Spring Data JPA**: Simplifies data access layers with Hibernate-driven persistence.
* **H2 Database**: An in-memory database used for rapid development and testing cycles.
* **Spring Boot Actuator**: Provides production-ready features like health checks and metrics monitoring.
* **Spring Boot DevTools**: Enhances developer productivity with automatic restarts and live reloads.
* **Lombok**: Reduces boilerplate code (Getters, Setters, Constructors) for cleaner Java classes.
* **Validation**: Ensures data integrity using Bean Validation (JSR-380) constraints.

### 🚀 Getting Started


Data Transfer Object: https://martinfowler.com/eaaCatalog/dataTransferObject.html

http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb

OPEN API
https://www.openapis.org/
https://springdoc.org/

Swagger api docs url 
http://localhost:8080/swagger-ui/index.html

storming ideas for microservices: lucidchart.com/blog/ddd-event-storming

# Config files github
uri: https://github.com/giorgos-grekis/java-microservices-architecture-k8s-helm-config

------------

DDS
Domain-Driven Sizing: Since many of our modifications or enhancements driven by the business
needs, we can size/define boundaries of our microservices that are closely aligned with 
Domain-Driven-Design & Business capabilities. But this process takes lot of time and
need good domain knowledge.


---------------------

Strangler Fig patten: is a software migration pattern used to gradually replace or refactor
legacy system with a new system, piece by piece, without disrupting the existing functionality.
This pattern gets its name form the way a strangler fig plant grows
around an existing tree, slowly replacing it until the original tree no longer needed.

use case Strangler Fig patten:
- When you need to modernize a large or complex legacy system.
- When you want to avoid the risk associated with a complete system rewrite or "big bang" migration.
- When the legacy system needs to remain operational during the transition to the new system.

-------------------------------------------

Run a java app via docker

java -jar target/accounts-0.0.1-SNAPSHOT.jar


--------------------------------------------

Build via docker

docker build . -t cisu2/account:002

change the version also in docker-compose.yml

docker inspect image {imageId}

# Run the docker image with detached mode
`docker run -d -p 8080:8080 {imageId}`

# Push image to docker hub

`docker image push docker.io/cisu2/account:001`

# docker-compose

## go to the right folder /docker-compose/default {prod, qa}

### create/start docker compose
`docker compose up -d`

### start docker compose
`docker compose start`

----

delete/stop docker compose
docker compose down

stop docker compose
docker compose stop


# run docker compose with force to build accounts again
`docker compose up -d --build accounts`


# Docker rabbitmq

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4-management


------------------------


# List the TCP process bound to port 
`fuser PORT/tcp`

# kill a port
`kill $(lsof -t -i:PORT)`
-------------------------

buildpacks
url: https://buildpacks.io/features/

mvn spring-boot:build-image

----------------------------------------

Google Jib
url: https://github.com/GoogleContainerTools/jib

mvn compile jib:build


-----------------------------------------------

15-Factor methodology - Logs

In a cloud-native application, log routing and storage are not the application's concern.
Instead, application should direct their logs to the standard output, treating them as sequentially
ordered events based on time. The responsibility of log storage and rotation is now shifted
to an external tool, know as log aggregator. This tool retrieves, gathers and provides access
to the logs for inspection purposes.

-----------------------------------------------

15-Factor methodology - Port binding

Cloud native application, adhering to the 15-Factor methodology, should be self-contained
and expose their services through port binding. In production environments, routing services
may be employed to translate requests from public endpoints to the internally port-bound services.


-----------------------------------------------

spring-cloud: https://docs.spring.io/spring-cloud-config/reference/index.html



service for webhook:  https://hookdeck.com/pricing

-----------------------------------------------

