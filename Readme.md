# Contact Manager API

The Contact Management System is a fully-featured Spring Boot REST API application designed with clean architecture and best practices to demonstrate enterprise-grade backend development. It supports complete CRUD operations on contacts, each of which contains rich information including embedded address objects and validated phone numbers. The application employs JSR-303 bean validation annotations like @NotBlank, @Email, and @Pattern to ensure data integrity at the DTO and entity levels. To secure the API, Spring Security is configured with Basic Authentication and role-based access control using annotations like @PreAuthorize. Admin users are granted exclusive access to sensitive operations such as retrieving the total number of contact records. The system uses an in-memory H2 database, easily accessible via the H2 console for testing and debugging, with configurations enabled through application.properties. All access and operations are logged using a custom logback-spring.xml file, which logs to both the console and rotating daily log files for maintainability. Furthermore, the project leverages Spring AOP to log method-level activity before and after execution within the service layer, enabling traceability and aiding in debugging without polluting business logic. Combined with a global exception handler and clean DTO-to-entity mapping, this system demonstrates a complete, robust, and professional-grade RESTful backend.

---

##  Features

- Create, Read, Update, Delete contacts
- Basic Authentication with role support
- JSR-303 validation (e.g., @NotBlank, @Email)
- Embedded Address object
- Global exception handling
- Swagger/OpenAPI documentation
- H2 in-memory database with console
- Logback logging with daily file rotation
  
---

## Tech Stack

| Layer         | Technology        |
|---------------|-------------------|
| Framework     | Spring Boot 3.x   |
| Build Tool    | Maven             |
| Security      | Spring Security   |
| Persistence   | Spring Data JPA   |
| Validation    | JSR-303 (jakarta) |
| Database      | H2 In-Memory DB   |
| Docs          | Swagger (SpringDoc) |
| Logs          | Logback           |
| Language      | Java 17           |

---

H2 URL -http://localhost:8085/h2-console
H2 username/password - admin/admin