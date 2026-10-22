# Student Management System (Java + Spring Boot + Vercel)

A robust, enterprise-grade web application for managing academic student records, developed using Java 17 LTS, Spring Boot 3.2, Spring Data JPA, Hibernate, Bootstrap 5.3, and deployed on **Vercel Cloud Edge**.

---

## 🌐 Live Production Deployments
- **Official Vercel Website:** [https://student-management-system-frontend-beige.vercel.app](https://student-management-system-frontend-beige.vercel.app)
- **Backend REST API (JSON):** [https://quaky-trista-unevangelic.ngrok-free.dev/api/students](https://quaky-trista-unevangelic.ngrok-free.dev/api/students)
- **Backend Health & Stats API:** [https://quaky-trista-unevangelic.ngrok-free.dev/api/students/stats](https://quaky-trista-unevangelic.ngrok-free.dev/api/students/stats)
- **GitHub Repository:** [https://github.com/swarajpakhale/Student-Management-System-Java-Spring-Boot](https://github.com/swarajpakhale/Student-Management-System-Java-Spring-Boot)

---

## 🎓 Academic Project Credentials
- **Institution:** Yeshwantrao Chavan College of Engineering (YCCE), Nagpur
- **Department:** Department of Computer Technology
- **Student Name:** Swaraj Surendra Pakhale
- **Roll Number:** 172
- **Branch:** Computer Technology (CT-B)
- **Semester:** 5th Semester
- **Academic Session:** 2026–2027

---

## 🏗️ Architecture & Deployment Overview
The application is organized into a clean decoupled production architecture:
1. **Frontend (Vercel)**: High-performance, responsive single-page application built with HTML5, CSS3, JavaScript, Bootstrap 5.3, and Bootstrap Icons. Deployed globally on Vercel's Edge Network with automated SSL and instant caching.
2. **Backend (Spring Boot 3.2)**: Multi-tier REST service built on Java 17 LTS, Spring MVC, Spring Data JPA, and Hibernate with declarative `@Transactional` boundaries, global CORS configuration, and Jakarta Bean Validation.
3. **Database**: Relational database supporting embedded H2 for instant evaluation and external MySQL 8.0 / PostgreSQL for production scaling via environment variables.

---

## ✨ Key Features
- **Live Academic Dashboard:** Real-time KPI metric cards (Total Enrolled Students, CT Department Cohort, IT Cohort, and Average CGPA).
- **Comprehensive CRUD Operations:** Add new students, view roster, edit existing records, and safely delete records with instant database synchronization.
- **Dynamic Multi-Attribute Search:** Real-time client and server-side filtering across student name, roll number, department, and email.
- **Defensive Validation:** Strict Jakarta Bean Validation (`@NotBlank`, `@Email`, `@Pattern`, `@DecimalMin`, `@DecimalMax`) preventing malformed data entry.
- **Configurable API Endpoint:** In-app API configuration modal allows switching between local backend and cloud production APIs.

---

## 🛠️ Technology Stack
- **Frontend Hosting:** Vercel (Edge Network)
- **Frontend Stack:** Bootstrap 5.3, Bootstrap Icons, JavaScript ES6+, HTML5
- **Backend Framework:** Spring Boot 3.2.3, Spring MVC, Spring Data JPA
- **Language:** Java 17 LTS
- **ORM & Persistence:** Hibernate 6.x, H2 Database / MySQL 8.0
- **Validation Engine:** Jakarta Validation (Hibernate Validator)
- **Containerization:** Docker (Eclipse Temurin JRE 17 Alpine Multi-Stage)
- **Build Tool:** Apache Maven 3.8+

---

## 🚀 Local Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/swarajpakhale/Student-Management-System-Java-Spring-Boot.git
cd Student-Management-System-Java-Spring-Boot
```

### 2. Run the Spring Boot Backend
```bash
mvn clean package -DskipTests=true
java -jar target/student-management-system-1.0.0.jar
```
Backend runs locally at: `http://localhost:8081`

### 3. Run the Frontend Locally
```bash
cd frontend
npx serve .
```
Or open `frontend/index.html` directly in any web browser.

---

## ☁️ Deployment Instructions

### Deploy Frontend to Vercel
```bash
cd frontend
npx vercel deploy --prod --yes
```

### Deploy Backend to Render / Cloud
This repository includes a pre-configured `Dockerfile` and `render.yaml`.
1. Sign in to [Render](https://render.com) and click **New Web Service**.
2. Connect `swarajpakhale/Student-Management-System-Java-Spring-Boot`.
3. Select **Docker** environment. Render builds and runs the container automatically.
4. Set `PORT=8081` in environment variables.

---

## 📄 License
Developed for academic assessment at Yeshwantrao Chavan College of Engineering (YCCE), Nagpur.
