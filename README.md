# 🛒 Marketna – E-commerce Backend System

A complete backend system for managing an e-commerce application, built using **Java 17**, **Spring Boot**, **JWT**, and **Role-Based Access Control**. It includes all core functionalities like product and category management, shopping cart, order handling, and admin/user separation.

---

## 📌 Features

- ✅ JWT Authentication  
- ✅ Role-Based Access (Admin / User)  
- ✅ User and Admin Control Separation  
- ✅ Product CRUD and Search  
- ✅ Shopping Cart  
- ✅ Checkout Flow with Wallet Balance  
- ✅ Category Management  
- ✅ Global Exception Handling  
- ✅ Clean DTO-based Structure  
- ✅ Input Validation  
- ✅ Composite Keys using `@Embeddable`

---

## 🔐 Authentication

| Endpoint       | Description         |
|----------------|---------------------|
| `POST /login`   | User login          |
| `POST /register`| User registration   |

---

## 📦 Product Management

| Method | Endpoint                  | Description                |
|--------|---------------------------|----------------------------|
| POST   | `/products`               | Add a product *(Admin)*    |
| PUT    | `/products/{id}`          | Update product *(Admin)*   |
| DELETE | `/products/{id}`          | Delete product *(Admin)*   |
| GET    | `/products`               | List all products          |
| GET    | `/products/{id}`          | Product details            |
| GET    | `/products/category/{name}` | Filter by category       |
| GET    | `/search`                 | Search products            |

---

## 🗂️ Category Management

| Method | Endpoint                  | Description              |
|--------|---------------------------|--------------------------|
| POST   | `/categories`             | Add a category *(Admin)* |
| GET    | `/categories`             | List categories          |
| DELETE | `/categories/{id}`        | Delete category *(Admin)*|

---

## 🛒 Cart Management

| Method | Endpoint          | Description               |
|--------|-------------------|---------------------------|
| POST   | `/cart`           | Add item to cart          |
| PUT    | `/cart`           | Update cart item quantity |
| DELETE | `/cart/{productId}` | Remove item from cart   |
| GET    | `/cart`           | View current cart         |

---

## 🧾 Order & Checkout

| Method | Endpoint           | Description              |
|--------|--------------------|--------------------------|
| GET    | `/admin/orders`    | View all orders *(Admin)*|
| GET    | `/orders`          | View user orders         |
| GET    | `/checkout`        | Checkout and pay         |

---

## ⚙️ Installation

1. **Clone the repository**

```bash
git clone https://github.com/3LOLOo/Marketna.git
cd Marketna
```

2. **Configure your DB & application properties**

Update your `application.properties` with:
- PostgreSQL connection:
  ```properties
  spring.datasource.url=jdbc:postgresql://localhost:5432/marketna
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  spring.jpa.hibernate.ddl-auto=update
  ```
- JWT secret & expiration
- Port (uses `9090`)

3. **Run the application**

```bash
mvn spring-boot:run
```

4. **Access the API**

> 🌐 `http://localhost:9090`

---

## 🛠 Technologies Used

- **Java 17**
- **Spring Boot 3**
- **Spring Security + JWT**
- **Spring Data JPA + Hibernate**
- **PostgreSQL**
- **Lombok**
- **ModelMapper**
- **DTO Pattern**
- **Swagger** (if enabled)
- **Validation API**
- **Global Exception Handling**