# rental-testing
INSERT INTO users (username, name, surname, email, password, age, user_type, created_at, updated_at)
VALUES ('admin', 'admin', 'admin', 'admin@mail.com', '$2a$10$3tSIPUuEo8lmzjBnfRZZvubO/lsr6loVy6S6LRZU0bXKo1p/X04am', 40, 'ADMIN', '2024-11-21T10:15:30.123', '2024-11-21T10:15:30.123');

# API Documentation

## Authentication
The API uses **Bearer Token (JWT)** authentication for secure access. All endpoints require authentication by default unless specified otherwise.

## Endpoints

### Authentication Endpoints
1. **User Registration**  
   - **Endpoint**: `/auth/signup`  
   - **Method**: `POST`  
   - **Request Body**:  
     ```json
     {
       "username": "string",
       "name": "string",
       "surname": "string",
       "email": "string",
       "password": "string",
       "age": "integer"
     }
     ```

2. **User Login**  
   - **Endpoint**: `/auth/login`  
   - **Method**: `POST`  
   - **Request Body**:  
     ```json
     {
       "username": "string",
       "password": "string"
     }
     ```

### Vehicle Endpoints
1. **Get All Vehicles**  
   - **Endpoint**: `/api/vehicles`  
   - **Method**: `GET`  
   - **Response**: Array of `VehicleEntity`

2. **Create Vehicle**  
   - **Endpoint**: `/api/vehicles`  
   - **Method**: `POST`  
   - **Request Body**:  
     ```json
     {
       "brand": "string",
       "model": "string",
       "year": "integer",
       "gearboxType": "AUTOMATIC | MANUAL",
       "fuelType": "DIESEL | PETROL | ELECTRIC",
       "color": "string",
       "vehicleStatus": "AVAILABLE | MAINTENANCE",
       "dailyFee": "number"
     }
     ```

3. **Get Vehicle by ID**  
   - **Endpoint**: `/api/vehicles/{id}`  
   - **Method**: `GET`  
   - **Response**: `VehicleResponse`

4. **Update Vehicle**  
   - **Endpoint**: `/api/vehicles/{id}`  
   - **Method**: `PUT`  
   - **Request Body**:  
     ```json
     {
       "model": "string",
       "color": "string",
       "dailyFee": "number",
       "vehicleStatus": "AVAILABLE | MAINTENANCE"
     }
     ```

5. **Delete Vehicle**  
   - **Endpoint**: `/api/vehicles/{id}`  
   - **Method**: `DELETE`

### User Endpoints
1. **Get All Users**  
   - **Endpoint**: `/api/users`  
   - **Method**: `GET`  
   - **Response**: Array of `UserEntity`

2. **Create User**  
   - **Endpoint**: `/api/users`  
   - **Method**: `POST`  
   - **Request Body**:  
     ```json
     {
       "username": "string",
       "name": "string",
       "surname": "string",
       "email": "string",
       "password": "string",
       "age": "integer",
       "userType": "ADMIN | USER"
     }
     ```

3. **Get User by ID**  
   - **Endpoint**: `/api/users/{id}`  
   - **Method**: `GET`  
   - **Response**: `UserEntity`

4. **Update User**  
   - **Endpoint**: `/api/users/{id}`  
   - **Method**: `PUT`  
   - **Request Body**:  
     ```json
     {
       "username": "string",
       "password": "string",
       "age": "integer"
     }
     ```

5. **Delete User**  
   - **Endpoint**: `/api/users/{id}`  
   - **Method**: `DELETE`

### Reservation Endpoints
1. **Create Reservation**  
   - **Endpoint**: `/api/reservations/create`  
   - **Method**: `POST`  
   - **Request Body**:  
     ```json
     {
       "vehicleId": "integer",
       "startDate": "date-time",
       "endDate": "date-time"
     }
     ```

2. **Check Reservation Availability**  
   - **Endpoint**: `/api/reservations/check-availability`  
   - **Method**: `POST`  
   - **Request Body**:  
     ```json
     {
       "vehicleId": "integer",
       "startDate": "date-time",
       "endDate": "date-time"
     }
     ```  
   - **Response**: `Boolean`

3. **Get All Reservations**  
   - **Endpoint**: `/api/reservations`  
   - **Method**: `GET`  
   - **Response**: Array of `ReservationResponse`

4. **Get Reservation by ID**  
   - **Endpoint**: `/api/reservations/{id}`  
   - **Method**: `GET`  
   - **Response**: `ReservationResponse`

5. **Get Reservation Statistics**  
   - **Endpoint**: `/api/reservations/statistics/{MM-yyyy}`  
   - **Method**: `GET`  
   - **Response**: Array of `ReservationStatisticsResponse`

6. **Cancel Reservation**  
   - **Endpoint**: `/api/reservations/cancel/{id}`  
   - **Method**: `DELETE`  
   - **Response**: `ReservationResponse`

## Data Models

### Vehicle
- `id`: integer  
- `brand`: string  
- `model`: string  
- `year`: integer  
- `gearboxType`: AUTOMATIC | MANUAL  
- `fuelType`: DIESEL | PETROL | ELECTRIC  
- `color`: string  
- `vehicleStatus`: AVAILABLE | MAINTENANCE  
- `dailyFee`: number  
- `createdAt`, `updatedAt`, `deletedAt`: date-time  

### User
- `id`: integer  
- `username`: string  
- `name`, `surname`: string  
- `email`: string  
- `age`: integer  
- `userType`: ADMIN | USER  
- Additional security properties like `accountNonExpired`, `enabled`, etc.

### Reservation
- `reservationId`: integer  
- `userId`: integer  
- `vehicleId`: integer  
- `vehicleName`: string  
- `startDate`, `endDate`: date-time  
- `status`: string  
- `duration`: integer  
- `price`: number  


- All endpoints require authentication by default.

