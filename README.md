# Bank Management System - Spring Boot Web Application

A modern web-based bank management system built with Spring Boot, replacing the legacy Java Swing desktop application.

## Features

### User Features
- User registration with multi-step form
- Secure login with account number and PIN
- Dashboard with transaction options
- Deposit money
- Withdraw money
- Fast cash withdrawal
- Balance enquiry
- PIN change
- Mini statement (last 10 transactions)

### Admin Features
- Admin login with separate credentials
- View all accounts
- Delete/deactivate accounts
- Admin dashboard

### Security Features
- PIN encryption using BCrypt
- Spring Security for authentication and authorization
- Session management
- CSRF protection
- Role-based access control (USER and ADMIN roles)

## Technology Stack

- **Backend Framework**: Spring Boot 3.2.1
- **Web Framework**: Spring MVC
- **Template Engine**: Thymeleaf
- **Database**: Spring Data JPA with MySQL
- **Security**: Spring Security
- **Build Tool**: Maven
- **Java Version**: Java 17

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 5.7+ or MySQL 8.0+
- Git (optional)

## Database Setup

1. Install MySQL and start the MySQL server

2. Create a database named `banksystem`:
```sql
CREATE DATABASE banksystem;
```

3. The application will automatically create the required tables on first run using JPA's `ddl-auto=update` setting.

### Existing Database Migration

If you have an existing database from the Swing application, you may need to adjust the schema:

```sql
-- Add missing columns if needed
ALTER TABLE login ADD COLUMN IF NOT EXISTS account_type VARCHAR(50);
ALTER TABLE login ADD COLUMN IF NOT EXISTS services VARCHAR(255);
ALTER TABLE login ADD COLUMN IF NOT EXISTS balance DOUBLE DEFAULT 0.0;

-- The PIN column will need to be updated to store BCrypt hashed values
-- Old PINs will need to be re-hashed or users will need to reset their PINs
```

## Installation and Setup

### 1. Clone the Repository

```bash
git clone https://github.com/suryavikram2003/bankmanagementsystem.git
cd bankmanagementsystem
```

### 2. Configure Database

Edit `src/main/resources/application.properties` and update database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/banksystem?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

Or use environment variables (recommended for production):

```bash
export DB_USERNAME=your_mysql_username
export DB_PASSWORD=your_mysql_password
```

### 3. Build the Application

```bash
mvn clean install
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR file:

```bash
java -jar target/bank-management-system-2.0.0.jar
```

### 5. Access the Application

Open your web browser and navigate to:

```
http://localhost:8080
```

## Default Credentials

### Admin Login
- **Admin ID**: admin
- **Password**: admin123

### User Login
Users need to register first to create an account. After registration, they will receive an account number and PIN.

## Application Structure

```
src/main/java/bank/management/system/
├── config/                     # Configuration classes
│   ├── SecurityConfig.java     # Spring Security configuration
│   └── WebConfig.java          # Web MVC configuration
├── controller/                 # Controllers (HTTP request handlers)
│   ├── AdminController.java    # Admin operations
│   ├── AuthController.java     # Authentication
│   ├── TransactionController.java  # Transactions
│   └── UserController.java     # User registration
├── service/                    # Service layer (business logic)
│   ├── AccountService.java
│   ├── TransactionService.java
│   └── UserService.java
├── repository/                 # Data access layer
│   ├── AccountRepository.java
│   ├── AdditionalDetailsRepository.java
│   ├── TransactionRepository.java
│   └── UserRepository.java
├── entity/                     # JPA entities
│   ├── Account.java
│   ├── AdditionalDetails.java
│   ├── Transaction.java
│   └── User.java
├── dto/                        # Data Transfer Objects
│   ├── LoginRequest.java
│   ├── PinChangeRequest.java
│   ├── SignupRequest.java
│   ├── TransactionRequest.java
│   └── UserResponse.java
└── BankManagementSystemApplication.java  # Main application class

src/main/resources/
├── application.properties      # Application configuration
├── templates/                  # Thymeleaf HTML templates
│   ├── admin/
│   │   ├── dashboard.html
│   │   └── login.html
│   ├── balance.html
│   ├── dashboard.html
│   ├── deposit.html
│   ├── fastcash.html
│   ├── login.html
│   ├── mini.html
│   ├── pin.html
│   ├── signup.html
│   ├── signup-success.html
│   └── withdraw.html
└── static/
    └── css/
        └── style.css           # Application styles
```

## API Endpoints

### Public Endpoints
- `GET /` - Redirect to login
- `GET /login` - Login page
- `POST /login` - Process login
- `GET /signup` - Registration page
- `POST /signup` - Process registration
- `GET /signup-success` - Registration success page

### User Endpoints (Requires Authentication)
- `GET /dashboard` - User dashboard
- `GET /deposit` - Deposit page
- `POST /deposit` - Process deposit
- `GET /withdraw` - Withdrawal page
- `POST /withdraw` - Process withdrawal
- `GET /balance` - Balance enquiry
- `GET /fastcash` - Fast cash page
- `POST /fastcash` - Process fast cash
- `GET /pin` - PIN change page
- `POST /pin` - Process PIN change
- `GET /mini` - Mini statement
- `GET /logout` - Logout

### Admin Endpoints (Requires Admin Authentication)
- `GET /admin/login` - Admin login page
- `POST /admin/login` - Process admin login
- `GET /admin/dashboard` - Admin dashboard
- `POST /admin/delete-account` - Delete account
- `GET /admin/logout` - Admin logout

## Configuration

### Server Configuration
- Port: 8080 (configurable in `application.properties`)
- Context path: `/`
- Session timeout: 30 minutes

### Database Configuration
- JPA DDL: `update` (automatically updates schema)
- Show SQL: `true` (for debugging)
- Dialect: MySQL

### Security Configuration
- Password encoding: BCrypt
- Session management: Concurrent sessions allowed
- CSRF protection: Enabled

## Development

### Running in Development Mode

```bash
mvn spring-boot:run
```

The application will automatically reload when you make changes to the code (using Spring Boot DevTools).

### Building for Production

```bash
mvn clean package -DskipTests
```

This creates a JAR file in the `target/` directory.

### Running Tests

```bash
mvn test
```

## Troubleshooting

### Database Connection Issues
- Ensure MySQL is running
- Check database credentials in `application.properties`
- Verify database exists: `SHOW DATABASES;`

### Port Already in Use
Change the port in `application.properties`:
```properties
server.port=8081
```

### Login Issues
- For new installations, register a new user first
- Admin credentials are hardcoded: admin/admin123
- PINs are hashed with BCrypt, old plaintext PINs won't work

## Migration from Swing Application

The Spring Boot application maintains compatibility with the existing database schema:
- `signup` table → User entity
- `signuptwo` table → AdditionalDetails entity
- `signupthree` + `login` tables → Account entity
- `bank` table → Transaction entity

**Important**: PINs in the old system were stored as plaintext. The new system uses BCrypt hashing. Existing users will need to re-register or have their PINs reset.

## Security Considerations

1. Change default admin credentials in production
2. Use environment variables for database credentials
3. Enable HTTPS in production
4. Configure proper session timeout
5. Regular security updates
6. Implement rate limiting for login attempts
7. Add CAPTCHA for registration

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.

## Contact

For issues and questions, please open an issue on GitHub.

## Acknowledgments

- Original Swing application by suryavikram2003
- Converted to Spring Boot web application
- Uses Spring Boot, Thymeleaf, and Spring Security
