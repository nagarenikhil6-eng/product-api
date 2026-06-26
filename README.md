# Product API

A Spring Boot REST API built for the CodeVector Backend Take-Home Assignment.

## Features

* Browse 200,000+ products
* Cursor-based pagination for fast performance
* Category-based filtering
* CRUD operations (Create, Read, Update, Delete)
* MySQL Database
* Swagger UI for API documentation
* Bulk data generation using DataLoader

## Tech Stack

* Java 17
* Spring Boot 3
* Spring Data JPA
* MySQL
* Maven
* Swagger (OpenAPI)

## API Endpoints

### Get Products

GET `/api/products`

Optional Query Parameters:

* `category`
* `lastId`
* `limit`

Example:

GET `/api/products?category=Electronics&lastId=199848&limit=20`

### Get Product by ID

GET `/api/products/{id}`

### Create Product

POST `/api/products`

### Update Product

PUT `/api/products/{id}`

### Delete Product

DELETE `/api/products/{id}`

## Cursor Pagination

This project uses **cursor-based pagination** instead of offset pagination.

Benefits:

* Better performance on large datasets
* No duplicate records while browsing
* Faster than OFFSET for large tables

## Swagger

After running the application:

http://localhost:8081/swagger-ui/index.html

## Database

MySQL

Database Name:

productdb

Products Loaded:

200,000+

## Run Project

1. Clone repository

2. Configure MySQL

3. Run:

mvn spring-boot:run

## Author

Nikhil Nagare
