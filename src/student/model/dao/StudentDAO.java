package student.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.PresentationDirection;

import student.model.vo.Student;

public class StudentDAO {

	public Student selectCheckLogin(Connection conn, Student student) {
		String sql = "SELECT * FROM STUDENT_TBL WHERE STUDENT_ID = ? AND STUDENT_PW = ?";
		Student sOne = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getStudentId());
			pstmt.setString(2, student.getStudentPw());
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()) {
				sOne = new Student();
				sOne.setStudentId(rset.getString("STUDENT_ID"));
				sOne.setStudentPw(rset.getString("STUDENT_PW"));
				sOne.setStudentName(rset.getString("STUDENT_NAME"));
				sOne.setStudentEmail(rset.getString("STUDENT_EMAIL"));
				sOne.setStudentPhone(rset.getString("STUDENT_PHONE"));
				sOne.setStudentAddr(rset.getString("STUDENT_ADDR"));
				sOne.setGender(rset.getString("GENDER"));
				sOne.setStudentDate(rset.getDate("STUDENT_DATE"));
			}
			rset.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sOne;
	}

	public int insertMember(Connection conn, Student student) {
		String sql ="INSERT INTO STUDENT_TBL VALUES(?,?,?,?,?,?,?,DEFAULT)";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, student.getStudentId());
			pstmt.setString(2, student.getStudentPw());
			pstmt.setString(3, student.getStudentName());
			pstmt.setString(4, student.getStudentEmail());
			pstmt.setString(5, student.getStudentPhone());
			pstmt.setString(6, student.getStudentAddr());
			pstmt.setString(7, student.getGender());
			result = pstmt.executeUpdate();
		
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
