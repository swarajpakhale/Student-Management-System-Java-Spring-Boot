# Student Management System Using Java & Spring Boot

A robust, enterprise-grade web application for managing academic student records, developed using Java 17, Spring Boot 3.2, Spring Data JPA, Hibernate, Thymeleaf, and Bootstrap 5.

## Academic Project Details
- **Institution:** Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
- **Department:** Department of Computer Technology
- **Student Name:** Swaraj Surendra Pakhale
- **Roll Number:** 172
- **Branch:** Computer Technology (CT-B)
- **Academic Session:** 2026–2027

---

## Key Features
- **Interactive Dashboard:** Live real-time statistics (total enrolled students, active enrollment count, departmental breakdown, overall average CGPA).
- **Comprehensive CRUD Operations:** Add, view, edit, update, and soft/hard delete student records.
- **Dynamic Search & Filtering:** Filter student records instantly by first name, last name, email, roll number, or department.
- **Robust Server-Side Validation:** Jakarta Bean Validation (`@NotBlank`, `@Email`, `@Pattern`, `@Min`, `@Max`, `@Past`) ensuring strict data integrity with user-friendly error banners.
- **RESTful API Support:** Complete JSON endpoints under `/api/students` for programmatic querying and external client integration.
- **Embedded & Production Database Support:** Zero-configuration local execution using embedded H2 database or enterprise deployment with MySQL 8.0.

---

## Technology Stack
- **Backend Framework:** Spring Boot 3.2.3, Spring MVC, Spring Data JPA
- **Programming Language:** Java 17 LTS
- **Persistence & ORM:** Hibernate 6.x, H2 Database (local development) / MySQL 8.0 (production profile)
- **Frontend / Templating:** Thymeleaf, Bootstrap 5.3, Bootstrap Icons
- **Validation Engine:** Jakarta Validation (Hibernate Validator)
- **Testing Frameworks:** JUnit 5, Mockito, Spring Boot Test, MockMvc
- **Build Tool:** Apache Maven 3.8+

---

## Prerequisites
- **JDK 17** or higher installed and configured in `JAVA_HOME`.
- **Apache Maven 3.8+** (or use bundled Maven wrapper).
- Modern web browser (Chrome, Edge, Firefox).

---

## Installation & Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/swarajpakhale/Student-Management-System-Java-Spring-Boot.git
cd Student-Management-System-Java-Spring-Boot
```

### 2. Build the Application
```bash
mvn clean package -DskipTests=true
```

### 3. Run the Application
```bash
mvn spring-boot:run
```
*Alternatively, run the packaged JAR file:*
```bash
java -jar target/student-management-system-1.0.0.jar
```

### 4. Access the Application
- **Main Web Application:** [http://localhost:8081](http://localhost:8081)
- **Student Directory:** [http://localhost:8081/students](http://localhost:8081/students)
- **Add Student Form:** [http://localhost:8081/students/new](http://localhost:8081/students/new)
- **RESTful API Endpoint:** [http://localhost:8081/api/students](http://localhost:8081/api/students)
- **H2 Database Console:** [http://localhost:8081/h2-console](http://localhost:8081/h2-console)
  - JDBC URL: `jdbc:h2:file:./data/studentdb`
  - Username: `sa`
  - Password: *(leave blank)*

---

## Running Unit & Integration Tests
To execute the automated test suite with full coverage reports:
```bash
mvn test
```
All 11 unit and integration test cases validate controller routing, validation error handling, service logic, and repository persistence with a 100% pass rate.

---

## License
Developed for academic assessment at Yeshwantrao Chavan College of Engineering (YCCE), Nagpur.
