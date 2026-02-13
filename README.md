## Assignment 2 

## Names: Byiringiro Urbain Bobola  27150


Packages are organized:

- `controller.book`, `model.book`   Question 1: Library
- `controller.student`, `model.student`   Question 2: Student
- `controller.menu`, `model.menu`   Question 3: Restaurant Menu
- `controller.product`, `model.product`   Question 4: E‑Commerce Products
- `controller.task`, `model.task`   Question 5: Tasks
- `controller.user`, `model.user`   Bonus: User Profile


## How to run the application

```bash
./ls
./cd Assignment2
./mvn clean install
./mvnw spring-boot:run
```

1. The app will start on `http://localhost:8080`.
2. Use **Postman** or the browser .

---

## Question 1  Library Book API

**Model:** `Book` in `model.book`  
**Controller:** `BookController` in `controller.book`  
**Base URL:** `/api/books`

- **GET** `/api/books`  
  - Get all books.
- **GET** `/api/books/{id}`  
  - Get a book by ID (e.g. `1`).
- **GET** `/api/books/search?title={title}`  
  - Search books by title .
- **POST** `/api/books`  
  - Add a new book.  
  - Example body:
  ```json
  {
    "id": 4,
    "title": "web tech",
    "author": "Patrick",
    "isbn": "123-4567890123",
    "publicationYear": 2003
  }
  ```
- **DELETE** `/api/books/{id}`  
  - Delete a book by ID.

**Testing in Postman:**

- Test `GET /api/books` .
- Test `GET /api/books/1`.
- Test `GET /api/books/search?title=clean`.
- Test `POST /api/books` .
- Test `DELETE /api/books/3` and then `GET /api/books` again.

---

## Question 2  Student Registration API

**Model:** `Student` in `model.student`  
**Controller:** `StudentController` in `controller.student`  
**Base URL:** `/api/students`

- **GET** `/api/students`  
  - Get all students.
- **GET** `/api/students/{studentId}`  
  - Get student by ID.
- **GET** `/api/students/major/{major}`  
  - Get students by major. Example: `/api/students/major/Computer%20Science`.
- **GET** `/api/students/filter?gpa={minGpa}`  
  - Get students with GPA ≥ `minGpa`. Example: `/api/students/filter?gpa=3.5`.
- **POST** `/api/students`  
  - Register a new student.  
  - Example body:
  ```json
  {
    "studentId": 6,
    "firstName": "Urban",
    "lastName": "bobola",
    "email": "urbanbobola.com",
    "major": "Computer Science",
    "gpa": 4.0
  }
  ```
- **PUT** `/api/students/{studentId}`  
  - Update student information.

**Testing**

- Test `GET /api/students`.
- Test `GET /api/students/major/Computer%20Science`.
- Test `GET /api/students/filter?gpa=3.5`.

---

## Question 3   Restaurant Menu API

**Model:** `MenuItem` in `model.menu`  
**Controller:** `MenuController` in `controller.menu`  
**Base URL:** `/api/menu`

- **GET** `/api/menu`  
  - Get all menu items .
- **GET** `/api/menu/{id}`  
  - Get one item by ID.
- **GET** `/api/menu/category/{category}`  
  - Get items by category (e.g. `Main Course`, `Dessert`, `Beverage`).
- **GET** `/api/menu/available?available=true`  
  - Get available items (or `false` for unavailable).
- **GET** `/api/menu/search?name={name}`  
  - Search items by name .
- **POST** `/api/menu`  
  - Add a new menu item.  
  - Example body:
  ```json
  {
    "id": 9,
    "name": "ibijumba",
    "description": "ibijumba biryoshye",
    "price": 3000.0,
    "category": "ibinyampeke",
    "available": true
  }
  ```
- **PUT** `/api/menu/{id}/availability`  
  - Toggle availability.
- **DELETE** `/api/menu/{id}`  
  - Remove menu item.

**Testing**

- Test `GET /api/menu` 
- Test `GET /api/menu/category/Main%20Course`.
- Test `GET /api/menu/available?available=true`.
- Test `PUT /api/menu/6/availability` and then `GET /api/menu/6` to see change.

---

## Question 4  E‑Commerce Product API

**Model:** `Product` in `model.product`  
**Controller:** `ProductController` in `controller.product`  
**Base URL:** `/api/products`

- **GET** `/api/products`  
  - Get all products .
  - Optional pagination: `/api/products?page=0&limit=5`.
- **GET** `/api/products/{productId}`  
  - Get product details.
- **GET** `/api/products/category/{category}`  
  - Get products by category .
- **GET** `/api/products/brand/{brand}`  
  - Get products by brand .
- **GET** `/api/products/search?keyword={keyword}`  
  - Search products by keyword in name or description.
- **GET** `/api/products/price-range?min={min}&max={max}`  
  - Get products with price between min and max.
- **GET** `/api/products/in-stock`  
  - Get products where `stockQuantity > 0`.
- **POST** `/api/products`  
  - Add new product.
- **PUT** `/api/products/{productId}`  
  - Update all product details.
- **PATCH** `/api/products/{productId}/stock?quantity={quantity}`  
  - Update stock quantity only.
- **DELETE** `/api/products/{productId}`  
  - Delete product.

**Testing hints:**

- Test search endpoints:
  - `/api/products/search?keyword=laptop`
  - `/api/products/price-range?min=50&max=400`
  - `/api/products/in-stock`

---

## Question 5   Task Management API

**Model:** `Task` in `model.task`  
**Controller:** `TaskController` in `controller.task`  
**Base URL:** `/api/tasks`

- **GET** `/api/tasks`  
  - Get all tasks.
- **GET** `/api/tasks/{taskId}`  
  - Get task by ID.
- **GET** `/api/tasks/status?completed=true`  
  - Get tasks by completion status.
- **GET** `/api/tasks/priority/{priority}`  
  - Get tasks by priority (`LOW`, `MEDIUM`, `HIGH`).
- **POST** `/api/tasks`  
  - Create new task.
- **PUT** `/api/tasks/{taskId}`  
  - Update task.
- **PATCH** `/api/tasks/{taskId}/complete`  
  - Mark task as completed.
- **DELETE** `/api/tasks/{taskId}`  
  - Delete task.

**Testing hints:**

- Test `GET /api/tasks/status?completed=false`.
- Test `PATCH /api/tasks/1/complete` then `GET /api/tasks/1`.

---

## Bonus   User Profile API

**Models:** `UserProfile`, `ApiResponse<T>` in `model.user`  
**Controller:** `UserProfileController` in `controller.user`  
**Base URL:** `/api/users`

All responses are in an `ApiResponse` object:

```json
{
  "success": true,
  "message": "Some message",
  "data": { }
}
```

Main endpoints:

- **POST** `/api/users`  
  - Create user profile.
- **GET** `/api/users`  
  - Get all profiles.
- **GET** `/api/users/{userId}`  
  - Get one profile.
- **PUT** `/api/users/{userId}`  
  - Update profile.
- **DELETE** `/api/users/{userId}`  
  - Delete profile.
- **GET** `/api/users/search/username?username={username}`  
  - Search by username.
- **GET** `/api/users/search/country/{country}`  
  - Search by country.
- **GET** `/api/users/search/age-range?minAge={min}&maxAge={max}`  
  - Search by age range.
- **PATCH** `/api/users/{userId}/activate`  
  - Activate profile.
- **PATCH** `/api/users/{userId}/deactivate`  
  - Deactivate profile.

**Example create request body:**

```json
{
  "userId": 4,
  "username": "urban",
  "email": "urbanbobola20@gmail.com",
  "fullName": "urban bobola",
  "age": 21,
  "country": "Rwanda",
  "bio": "student",
  "active": true
}
```

---

## Some Screenshoots images

**Question 1 – Books API**

![Screenshot 2026-02-13 125456](Screenshoots/Screenshot%202026-02-13%20125456.png)
![Screenshot 2026-02-13 125524](Screenshoots/Screenshot%202026-02-13%20125524.png)

**Question 2 – Students API**

![Screenshot 2026-02-13 125734](Screenshoots/Screenshot%202026-02-13%20125734.png)
![Screenshot 2026-02-13 125752](Screenshoots/Screenshot%202026-02-13%20125752.png)

**Question 3 – Menu API**

![Screenshot 2026-02-13 130035](Screenshoots/Screenshot%202026-02-13%20130035.png)
![Screenshot 2026-02-13 130149](Screenshoots/Screenshot%202026-02-13%20130149.png)

**Question 4 – Products API**

![Screenshot 2026-02-13 130406](Screenshoots/Screenshot%202026-02-13%20130406.png)
![Screenshot 2026-02-13 130539](Screenshoots/Screenshot%202026-02-13%20130539.png)

**Question 5 – Tasks API**

![Screenshot 2026-02-13 131059](Screenshoots/Screenshot%202026-02-13%20131059.png)
![Screenshot 2026-02-13 131116](Screenshoots/Screenshot%202026-02-13%20131116.png)

**Bonus – User Profiles API**

![Screenshot 2026-02-13 131359](Screenshoots/Screenshot%202026-02-13%20131359.png)
![Screenshot 2026-02-13 133521](Screenshoots/Screenshot%202026-02-13%20133521.png)