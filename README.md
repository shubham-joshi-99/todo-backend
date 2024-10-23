
© 2024 Shubham Joshi

# Todo API Service

This is a RESTful API for managing todo items. The service allows clients to create, retrieve, update, and delete todo items.

## Technologies Used
- **Java 17**
- **Spring Boot** for building the REST API
- **JUnit 5** for testing
- **Mockito** for mocking dependencies in tests

## API Endpoints

### 1. Get Todo List

- **Endpoint:** `GET /todos`
- **Description:** Retrieve the list of all todo items.
- **Response:**
    - **Status:** `200 OK`
    - **Body:**
      ```json
      [
        {
          "id": 1,
          "title": "Buy groceries",
          "status": "ACTIVE",
          "order": 1
        }
      ]
      ```

### 2. Save Todo Item

- **Endpoint:** `POST /todos`
- **Description:** Save a new todo item.
- **Request Body:**
  ```json
  {
    "title": "Buy groceries edit",
    "status": "ACTIVE",
    "order": 1
  }
- **Response:**
  - **Status:** `200 OK`
  - **Body:** "Saved successfully"

### 3. Update Todo Item

- **Endpoint:** `PUT /todos/{id}`
- **Description:**  Update an existing todo item by its ID.
- **Path Parameter:** `id (int): The ID of the todo item to update.`
- **Request Body:**
  ```json
  {
    "title": "Buy groceries",
    "status": "COMPLETED",
    "order": 1
  }
- **Response:**
    - **Status:** `200 OK`
    - **Body:** "Updated successfully"
### 4. Delete Todo Item

- **Endpoint:** `DELETE /todos/{id}`
- **Description:**  Delete a todo item by its ID.
- **Path Parameter:** `id (int): The ID of the todo item to delete.`
- **Request Body:**
  ```json
  {
    "title": "Buy groceries",
    "status": "ACTIVE",
    "order": 1
  }
- **Response:**
    - **Status:** `200 OK`
    - **Body:** "Deleted successfully"
## Database Configuration

I have used H2 Database as the in-memory database for development and testing purposes.
You can comment the `spring.datasource.url` property in `application.properties` if you don't want to use create the table.

### H2 Database Configuration

- **Console:** Enabled at `/h2-console`
- **Username:** sa
- **Password:** password
- **Table:** `todo`


## Running the Application

To run the application, execute:
```sh
mvn spring-boot:run
```
The application will be available at http://localhost:8080
