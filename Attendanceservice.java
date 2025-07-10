package attendance.service;



	import attendance.dao.studentDAO;

	public class Attendanceservice {

	    private final studentDAO dao = new studentDAO();

	    public void markAttendanceMultiThreaded(int studentId, String status) {
	        Thread thread = new Thread(() -> {
	            dao.markAttendance(studentId, status);
	            System.out.println("Attendance marked for student ID: " + studentId);
	        });
	        thread.start();
	    }
	}


