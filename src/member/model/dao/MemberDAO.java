package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MemberDAO {

	
	public int selectCheckLogin(Connection conn, String memberId, String memberPw) {
		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, memberPw);
			ResultSet rset = pstmt.executeQuery();
			if(rset.next()) {
//				result = rset.getInt("M_COUNT");
				result = 1;
			}
			pstmt.close();
			rset.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
}
