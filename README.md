# 📚 Library Management System

A desktop-based Library Management System built in **Java** using **NetBeans IDE** with a **MySQL** database backend. Developed as an Object-Oriented Programming (OOP) course project.

---

## 🖥️ Features

- 🔐 **Login Authentication** — Secure user login system
- 📖 **Book Inventory Management** — Add, update, delete, and search books
- 👤 **Member Management** — Register and manage library members
- 📋 **Lending System** — Issue and return books with due date tracking
- 🗄️ **Database Integration** — Full CRUD operations with MySQL

---

## 🛠️ Tech Stack

| Technology | Usage |
|------------|-------|
| Java | Core programming language |
| NetBeans IDE | Development environment |
| MySQL | Database management |
| JDBC | Database connectivity |
| Swing (JFrame) | GUI / User Interface |

---

## 📁 Project Structure
librarydb/
├── src/
│   ├── LoginForm.java
│   ├── LoginForm_1.java
│   ├── MainDashboard.java
│   ├── MainDashboard_1.java
│   ├── BookInventoryForm.java
│   ├── BookInventoryForm_1.java
│   ├── BookInventoryForm_2.java
│   ├── BookInventoryForm_3.java
│   ├── BookInventoryForm_4.java
│   ├── BookInventoryForm_5.java
│   ├── LendingForm.java
│   ├── LendingForm_1.java
│   ├── LendingForm_2.java
│   ├── LendingForm_3.java
│   ├── LendingForm_4.java
│   └── LendingForm_BookInventoryRefresher.java
├── nbproject/
│   ├── private/
│   ├── build-impl.xml
│   ├── genfiles.properties
│   ├── project.properties
│   └── project.xml
├── LibraryReport.pdf
├── build.xml
├── manifest.mf
├── .gitignore
└── README.md

---

## ⚙️ How to Run

### Prerequisites
- Java JDK 8 or higher
- NetBeans IDE
- MySQL Server

### Steps

1. **Clone the repository**
```bash
   git clone https://github.com/AWAIS-KHAN12/librarydb.git
```

2. **Import the project in NetBeans**
   - Open NetBeans → File → Open Project → Select `librarydb`

3. **Set up the database**
   - Open MySQL and create a database named `librarydb`
   - Import the provided SQL file (if included)

4. **Configure DB connection**
   - Update the database credentials in the source file where JDBC connection is defined:
```java
   String url = "jdbc:mysql://localhost:3306/librarydb";
   String user = "root";
   String password = "your_password";
```

5. **Run the project**
   - Press `F6` in NetBeans or click the Run button

---



## 👨‍💻 Author

**Awais Khan**  
📧 232397@students.au.edu.pk  
🔗 [[LinkedIn](https://linkedin.com/in/your-profile) ](https://www.linkedin.com/in/muhammad-awais-khan-583242293/) 
🐙 [GitHub](https://github.com/AWAIS-KHAN12)

---
