Employee Management System

Build a Spring Boot REST API for managing employees in a company.

The application should allow users to create, retrieve, update, delete, and filter employees.

## Functional Requirements

### 1. Create Employee

The system must allow users to create a new employee.

Employee information should include:

* First name
* Last name
* Email
* Phone number
* Position
* Salary
* Hire date
* Status

Available employee statuses:

```text
ACTIVE
INACTIVE
ON_LEAVE
```

The employee status should be set to `ACTIVE` by default when a new employee is created.

The email address must be unique.

If an employee with the same email already exists, the system should return an appropriate error response.

---

### 2. Get Employee by ID

The system must allow users to retrieve an employee by their ID.

```text
GET /api/employees/{id}
```

If the employee does not exist, the system should return HTTP status `404 NOT FOUND`.

---

### 3. Get All Employees

The system must provide an endpoint for retrieving all employees.

```text
GET /api/employees
```

The endpoint should return all employees stored in the database.

---

### 4. Update Employee

The system must allow users to update an existing employee.

```text
PUT /api/employees/{id}
```

Before updating, the system must check whether the employee exists.

If the employee does not exist, return `404 NOT FOUND`.

The email must remain unique.

---

### 5. Delete Employee

The system must allow users to delete an employee by ID.

```text
DELETE /api/employees/{id}
```

If the employee does not exist, return `404 NOT FOUND`.

If the deletion is successful, return HTTP status `204 NO CONTENT`.

---

### 6. Filter Employees

The system should allow users to filter employees using different criteria.

Possible filters:

* First name
* Last name
* Position
* Status
* Minimum salary
* Maximum salary

Examples:

```text
GET /api/employees?firstName=John
```

```text
GET /api/employees?status=ACTIVE
```

```text
GET /api/employees?position=Developer
```

```text
GET /api/employees?minSalary=2000&maxSalary=5000
```

Multiple filters should be supported in the same request.

Example:

```text
GET /api/employees?status=ACTIVE&position=Developer&minSalary=2000
```

The filtering logic should be implemented using the Java Stream API.

Students should use Stream operations such as:

```text
filter()
collect()
```

---

### 7. Global Error Handling

The application must have centralized exception handling.

Create a global exception handler using:

```java
@RestControllerAdvice
```

The handler should process at least:

* Employee not found
* Duplicate email
* Unexpected server errors

Create custom exceptions where appropriate.

Example error response:

```json
{
  "error": "EMPLOYEE_NOT_FOUND",
  "message": "Employee with id 10 not found"
}
```

---

## Technical Requirements

The project must use:

* Java
* Spring Boot
* Spring Web
* Spring Data JPA
* PostgreSQL
* Lombok
* Liquibase

Use the following architecture:

```text
Controller
     ↓
Service
     ↓
Repository
     ↓
PostgreSQL
```

Use DTOs for request and response objects.

Do not expose JPA entities directly from the Controller layer.


Filtering must be implemented using the Java Stream API.

## Expected API

```text
POST   /api/employees
GET    /api/employees/{id}
GET    /api/employees
PUT    /api/employees/{id}
DELETE /api/employees/{id}
```

Filtering:

```text
GET /api/employees?firstName=John
GET /api/employees?status=ACTIVE
GET /api/employees?position=Developer
GET /api/employees?minSalary=2000&maxSalary=5000
```