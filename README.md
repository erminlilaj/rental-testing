# rental-testing
INSERT INTO users (username, name, surname, email, password, age, user_type, created_at, updated_at)
VALUES ('admin', 'admin', 'admin', 'admin@mail.com', '$2a$10$3tSIPUuEo8lmzjBnfRZZvubO/lsr6loVy6S6LRZU0bXKo1p/X04am', 40, 'ADMIN', '2024-11-21T10:15:30.123', '2024-11-21T10:15:30.123');

# API Documentation

# OpenAPI Specification Documentation

## Introduction
This document provides a comprehensive overview of the API specification for `ms-spring-rental`. It includes details on available endpoints, their functionality, and the data structures involved. Additionally, we will discuss the logic behind some of the key controllers and services used in the application.


### Security
- **Bearer Authentication**: Uses JWT tokens for secure API calls.

---

## Endpoints

### Vehicle Controller
1. **`GET /api/vehicles/{id}`**
   - Fetches details of a specific vehicle by its ID.
   - **Responses**: Returns `VehicleResponse`.

2. **`PUT /api/vehicles/{id}`**
   - Updates the details of a specific vehicle.
   - **Request Body**: `UpdateVehicleRequest`.
   - **Responses**: Returns `VehicleEntity`.

3. **`DELETE /api/vehicles/{id}`**
   - Deletes a specific vehicle by its ID.
   - **Responses**: HTTP 200 on success.

4. **`GET /api/vehicles`**
   - Fetches all vehicles.
   - **Responses**: Array of `VehicleEntity`.

5. **`POST /api/vehicles`**
   - Creates a new vehicle with optional image upload.
   - **Request Body**: `multipart/form-data` containing `CreateVehicleRequest` and an image file.

### User Controller
1. **`GET /api/users/{id}`**
   - Fetches user details by ID.
   - **Responses**: Returns `UserEntity`.

2. **`PUT /api/users/{id}`**
   - Updates user information.
   - **Request Body**: `UpdateUserRequest`.
   - **Responses**: Returns `UserEntity`.

3. **`DELETE /api/users/{id}`**
   - Deletes a user by ID.
   - **Responses**: HTTP 200 on success.

4. **`GET /api/users`**
   - Fetches all users.
   - **Responses**: Array of `UserEntity`.

5. **`POST /api/users`**
   - Creates a new user.
   - **Request Body**: `CreateUserRequest`.
   - **Responses**: Returns `UserEntity`.

### Authentication Controller
1. **`POST /auth/signup`**
   - Registers a new user.
   - **Request Body**: `RegisterUserRequest`.
   - **Responses**: Empty object.

2. **`POST /auth/login`**
   - Authenticates a user and provides a JWT token.
   - **Request Body**: `LoginUserRequest`.
   - **Responses**: JWT token.

3. **`GET /auth/userId`**
   - Fetches the logged-in user ID.

4. **`GET /auth/isAdmin`**
   - Checks if the logged-in user has admin privileges.

### Reservation Controller
1. **`POST /api/reservations/create`**
   - Creates a new reservation.
   - **Request Body**: `CreateReservationRequest`.
   - **Responses**: `ReservationResponse`.

2. **`POST /api/reservations/check-availability`**
   - Checks the availability of a reservation.
   - **Request Body**: `CreateReservationRequest`.
   - **Responses**: Boolean.

3. **`GET /api/reservations`**
   - Fetches all reservations.
   - **Responses**: Array of `ReservationResponse`.

4. **`DELETE /api/reservations/cancel/{id}`**
   - Cancels a reservation by ID.
   - **Responses**: `ReservationResponse`.

### Statistics
1. **`GET /api/reservations/statistics/{MM-yyyy}`**
   - Fetches reservation statistics for a given month and year.
   - **Responses**: Array of `ReservationStatisticsResponse`.

---

## Data Models

### Vehicle Models
- **`UpdateVehicleRequest`**
  - Fields: `model`, `color`, `dailyFee`, `vehicleStatus`.

- **`VehicleEntity`**
  - Fields: `id`, `brand`, `model`, `year`, `gearboxType`, `fuelType`, `color`, `vehicleStatus`, `dailyFee`, timestamps.

- **`VehicleResponse`**
  - Similar to `VehicleEntity` with additional formatting.

### User Models
- **`RegisterUserRequest`**, **`UpdateUserRequest`**, **`CreateUserRequest`**
  - Fields include user details like `username`, `password`, `age`, `userType`.

- **`UserEntity`**
  - Fields: `id`, `username`, `name`, `surname`, `email`, `password`, `age`, `userType`, `authorities`.

### Reservation Models
- **`CreateReservationRequest`**
  - Fields: `vehicleId`, `startDate`, `endDate`.

- **`ReservationResponse`**
  - Fields: `reservationId`, `userId`, `vehicleId`, `startDate`, `endDate`, `status`, `duration`, `price`.

- **`ReservationStatisticsResponse`**
  - Fields: `month`, `year`, `status`, `count`, `profit`.

---

## Logic and Key Details

### Vehicle Management
- **Logic**:
  - **Creation**: Validates the request data, stores vehicle information in the database, and optionally associates an uploaded image.
  - **Update**: Ensures vehicle exists and updates its details.
  - **Deletion**: Marks a vehicle as deleted (soft delete) to maintain historical records.

### User Authentication and Authorization
- **Logic**:
  - **Signup**: Validates user details, hashes passwords, and assigns default roles.
  - **Login**: Verifies credentials and generates JWT tokens.
  - **Role-Based Access**: Differentiates between `ADMIN` and `USER` privileges for accessing endpoints.

### Reservation Workflow
- **Logic**:
  - **Create Reservation**: Checks vehicle availability, calculates price, and saves reservation.
  - **Cancel Reservation**: Updates reservation status and refunds if applicable.
  - **Statistics**: Aggregates reservation data for reporting purposes.

---

 


