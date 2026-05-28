# Product Order Management System

A full-stack Product Order Management System built using Spring Boot, React.js, JWT Authentication, and MySQL.

## Tech Stack

### Backend

* Java
* Spring Boot
* Spring Security
* Spring MVC
* Hibernate
* JPA
* JWT Authentication

### Frontend

* React.js
* Vite
* React Router
* Axios
* JavaScript
* CSS

### Database

* MySQL

---

## Features

### Authentication & Authorization

* User Registration
* User Login
* JWT-based Authentication
* Role-based Authorization (ADMIN / USER)

### Product Management

* Add Product
* Update Product
* Update Product Status
* Get Product By ID
* Get All Products

### Cart Management

* Add Item to Cart
* Update Cart Item Quantity
* Remove Cart Item
* Get User Cart
* Cart Total Calculation

### Order Management

* Place Order
* Get All Orders
* Get Order By ID
* Inventory Quantity Update After Order

---

## Project Structure

```text
product-order-management-system
│
├── backend   -> Spring Boot REST APIs
└── frontend  -> React.js frontend
```

---

## How to Run the Project

### Backend

1. Open backend project in IntelliJ IDEA
2. Configure MySQL database in `application.properties`
3. Run the Spring Boot application

### Frontend

```bash
cd frontend
npm install
npm run dev
```

---

## API Testing

* Postman was used for API testing
* JWT token is required for protected APIs

---

## Security

* Password encryption using BCrypt
* JWT token validation using custom JWT Filter
* Role-based API access using Spring Security
