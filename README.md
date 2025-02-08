# Profile Management Service

This is a simple API application built as a homework project for Istanbul Tech API Development and Integration Bootcamp. The service provides user profile management functionality with address management capabilities.

## Technologies Used

- Spring Boot
- MongoDB
- Docker Compose
- Swagger UI
- JWT Authentication

## Prerequisites

To run this application, you need to have the following installed:
- Docker and Docker Compose
- Java 17 or higher
- Maven

## Getting Started

1. Clone the repository
2. Navigate to the project directory
3. Start the MongoDB and Mongo Express containers:
```bash
docker-compose up -d
```

4. Run the Spring Boot application:
```bash
./mvnw spring-boot:run
```

## Accessing the Applications

- **MongoDB Express (Database UI)**: http://localhost:8081/db/homework/address
- **Swagger UI (API Documentation)**: http://localhost:8080/swagger-ui/index.html#

## API Endpoints

### User Management

#### Register User
- **POST** `/api/users/register`
- Creates a new user account
- Request body example:
```json
{
  "name": "ismail",
  "surname": "kattan",
  "email": "example@email.com",
  "phone": "+905314326118",
  "password": "user123",
  "username": "sml-kttn"
}
```

#### Login
- **POST** `/api/users/login`
- Authenticates user and returns JWT token
- Request body example:
```json
{
  "username": "sml-kttn",
  "password": "user123"
}
```

#### Get Profile
- **GET** `/api/users/user/{username}`
- Retrieves user profile information
- Requires JWT Authentication

#### Update Profile
- **PUT** `/api/users/user/{username}`
- Updates user profile information
- Requires JWT Authentication
- Note: Cannot update address in this endpoint

### Address Management

#### Create Address
- **POST** `/api/addresses/create`
- Creates a new address for the user
- Requires JWT Authentication

#### Get User Addresses
- **GET** `/api/addresses/user/{username}`
- Retrieves all addresses for a specific user
- Requires JWT Authentication

#### Get Single Address
- **GET** `/api/addresses/address/{addressId}`
- Retrieves a specific address by ID
- Requires JWT Authentication

#### Update Address
- **PUT** `/api/addresses/address/{addressId}`
- Updates a specific address
- Requires JWT Authentication

#### Delete Address
- **DELETE** `/api/addresses/address/{addressId}`
- Deletes a specific address
- Requires JWT Authentication

## Authentication

The API uses JWT (JSON Web Token) for authentication. Include the JWT token in the Authorization header as follows:
```
Authorization: Bearer <your-jwt-token>
```

## Contributing

Feel free to submit issues and pull requests.

## License

This project is open-source and available under the MIT License.
