👨‍🏫👩‍🏫 Attendance Management System – Java Console Project

A Java-based console application that simulates an Attendance System. Users can register, log in, browse books, place orders, and view purchase history. This project is built using Core Java, JDBC, PostgreSQL, and Maven.

✨ Features

a. Student Management

b.Attendance Management

c. Reporting

💻 Technologies Used

    ☕ Core Java – OOP concepts, Collections
    🔌 JDBC – Java Database Connectivity
    🗄️ PostgreSQL – Relational Database
    📦 Maven – Build & Dependency Management
    🧠 Eclipse IDE


    📦 Dependencies

    <project xmlns="http://maven.apache.org/POM/4.0.0" ...>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.attendance</groupId>
    <artifactId>attendance-system</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <!-- MySQL Connector -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <version>8.0.33</version>
        </dependency>
    </dependencies>
</project>


  🗃️ Database Tables

  CREATE TABLE students (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL
);

CREATE TABLE attendance (
  id SERIAL PRIMARY KEY,
  student_id INTEGER REFERENCES students(id),
  date DATE NOT NULL,
  status VARCHAR(10) NOT NULL
);

Student.java

package com.example;

public class Student {
    private final int id;
    private final String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
}

Databaseconnection.java

package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/attendance_db";
    private static final String USER = "postgres";
    private static final String PASS = "your_password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}

Attendance.java

package com.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceManager {
    public List<Student> fetchAllStudents() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id,name FROM students")) {
            List<Student> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("name")));
            }
            return list;
        }
    }

    public void markAttendance(int studentId, LocalDate date, String status) throws SQLException {
        String sql = "INSERT INTO attendance(student_id, date, status) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentId);
            ps.setDate(2, Date.valueOf(date));
            ps.setString(3, status);
            ps.executeUpdate();
        }
    }
}

AttendaceTask.java multi threading 


package com.example;

import java.time.LocalDate;

public class AttendanceTask implements Runnable {
    private final Student student;
    private final LocalDate date;
    private final String status;
    private final AttendanceManager manager;

    public AttendanceTask(Student student, LocalDate date, String status, AttendanceManager manager) {
        this.student = student;
        this.date = date;
        this.status = status;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            manager.markAttendance(student.getId(), date, status);
            System.out.println("Marked " + student.getName() + " as " + status);
        } catch (Exception e) {
            System.err.println("Failed to mark " + student.getName() + ": " + e.getMessage());
        }
    }
}

Main.java


package com.example;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        AttendanceManager manager = new AttendanceManager();
        List<Student> students = manager.fetchAllStudents();
        LocalDate today = LocalDate.now();

        System.out.println("Fetched " + students.size() + " students.");
        for (Student s : students) {
            System.out.println(" - " + s.getId() + ": " + s.getName());
        }

        // Mark attendance concurrently: present if ID is even else absent
        for (Student s : students) {
            String status = (s.getId() % 2 == 0) ? "Present" : "Absent";
            Thread t = new Thread(new AttendanceTask(s, today, status, manager));
            t.start();
        }
    }
}

📸 Final Output 

==============================
 Attendance Management System
==============================

Fetching student records...
Found 3 students:

 -> ID: 1 | Name: Alice
 -> ID: 2 | Name: Bob
 -> ID: 3 | Name: Charlie

Marking attendance for date: 2025-07-10

[Thread-1] Marked Alice as Absent
[Thread-2] Marked Bob as Present
[Thread-3] Marked Charlie as Absent

✔ Attendance marked successfully!

📱Contacts

Team Members:

1.Arpita Avadhut Kulkarni

Mail:- arpitakulkarnii2212@gmail.com

2.Akanksha Laxman More

Mail:-akankshalm810@gmail.com

3.Afrin Farukh Shaikh

Mail:-shaikhafrin1607@gmail.com


    
