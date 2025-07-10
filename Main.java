package Attendancestu;


	import attendance.dao.studentDAO;
	import corejava.student;
	import attendance.service.Attendanceservice;
	import java.util.Scanner;

	public class Main {

	    public static void main(String[] args) {
	        studentDAO studentDAO = new studentDAO();
	        Attendanceservice Attendanceservice = new Attendanceservice();
	        Scanner scanner = new Scanner(System.in);

	        while (true) {
	            System.out.println("\n--- Attendance Management System ---");
	            System.out.println("1. Add Student");
	            System.out.println("2. Show All Students");
	            System.out.println("3. Mark Attendance");
	            System.out.println("4. Exit");
	            System.out.print("Enter choice: ");
	            int choice = scanner.nextInt();
	            scanner.nextLine();  // consume newline

	            switch (choice) {
	                case 1:
	                    System.out.print("Enter ID: ");
	                    int id = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("Enter Name: ");
	                    String name = scanner.nextLine();
	                    studentDAO.addStudent(new student(id, name));
	                    break;
	                case 2:
	                    studentDAO.getAllStudents().forEach(
	                        s -> System.out.println("ID: " + s.getId() + ", Name: " + s.getName())
	                    );
	                    break;
	                case 3:
	                    System.out.print("Enter Student ID to mark attendance: ");
	                    int sid = scanner.nextInt();
	                    scanner.nextLine();
	                    System.out.print("Enter status (Present/Absent): ");
	                    String status = scanner.nextLine();
	                    Attendances1ervice.markAttendanceMultiThreaded(sid, status);
	                    break;
	                case 4:
	                    System.exit(0);
	                default:
	                    System.out.println("Invalid choice.");
	            }
	        }
	    }
	}


