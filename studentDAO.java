package attendance.dao;


	import corejava.student;
	import attendance.util.DBConnection;

	import java.sql.*;
	import java.util.ArrayList;
	import java.util.List;

	public class studentDAO {

	    public void addStudent(student student) {
	        String query = "INSERT INTO students (id, name) VALUES (?, ?)";
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setInt(1, student.getId());
	            ps.setString(2, student.getName());
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public List<student> getAllStudents() {
	        List<student> list = new ArrayList<>();
	        String query = "SELECT * FROM students";
	        try (Connection conn = DBConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {

	            while (rs.next()) {
	                list.add(new student(rs.getInt("id"), rs.getString("name")));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return list;
	    }

	    public void markAttendance(int studentId, String status) {
	        String query = "INSERT INTO attendance (student_id, status, date) VALUES (?, ?, NOW())";
	        try (Connection conn = DBConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(query)) {
	            ps.setInt(1, studentId);
	            ps.setString(2, status);
	            ps.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


