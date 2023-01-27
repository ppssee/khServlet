package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.net.httpserver.Authenticator.Result;

import notice.model.vo.Notice;

public class NoticeDAO {

	/**
	 * 공지사항 등록
	 * @param conn
	 * @param notice
	 * @return
	 */
	public int insertNotice(Connection conn, Notice notice) {
		String query = "INSERT INTO NOTICE_TBL "
				+ "VALUES(SEQ_NOTICENO.NEXTVAL, ?, ?, ?, DEFAULT, DEFAULT)";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setString(3, notice.getNoticeWriter());
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 공지사항 목록 조회
	 * @param conn
	 * @return
	 */
	public List<Notice> selectAllNotice(Connection conn, int currentPage) {
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(ORDER BY NOTICE_NO DESC) AS NUM, NOTICE_TBL.* FROM NOTICE_TBL) WHERE NUM BETWEEN ? AND ?";
		List<Notice> nList = null;
		int recordCountPerPage = 10;
		// currentPage : 1, recordCountPerPage: 10
		// strart : 1, end: 10
		// currentPage : 2, recordCountPerPage: 10
		// strart : 11 end: 20
		// currentPage : 3, recordCountPerPage: 10
		// strart : 21 end: 30
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1 );
		int end = currentPage * recordCountPerPage;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			
			ResultSet rset = pstmt.executeQuery();
			nList = new ArrayList<Notice>();
			while(rset.next()) {
				Notice notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeWriter(rset.getString("NOTICE_WRITER"));
				notice.setNoticeDate(rset.getTimestamp("NOTICE_DATE"));
				notice.setViewCount(rset.getInt("VIEW_COUNT"));
				nList.add(notice);
			}
			rset.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nList;
	}
	
	/**
	 * 페이지 네비게이터를 만들어주는 메소드
	 */
	public String generatePageNavi(Connection conn, int currentPage) {		
		// 1 페이지당 게시물 수 : 10
		// ex) 전체게시물 수 :37
		// 네비게이터 수 : 4 (1,2,3,4)	
		// ex) 전체게시물 수 : 55
		// 네비게이터 수 : 6 (1,2,3,4,5,6)
		// 네비게이터 수 = 전체게시물수 / 페이지당 게시물 수 +1	
		
		int totalCount = getRecordTotalCount(conn);
		int recordCountPerPage = 10;
		int naviTotalCount = 0;
		if(totalCount % recordCountPerPage > 0) {        // 
			naviTotalCount = totalCount / recordCountPerPage+1;			
		} else { 	// 게시물 10,20,30 경우 +1페이지를 할필요가 업음.
			naviTotalCount = totalCount / recordCountPerPage;
		} 
		// ex) 네비게이터 수 1 2 3 4 5 6 7 8 9 10 11 12
		// 1 2 3 4 5
		// 6 7 8 9 10
		// 11 12
		int naviCountPerPage = 5;     // 5일경우 5개의 페이지를 보여주고 다음 눌러야 6~10 페이지를 보여줌 
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage -1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = startNavi; i<= endNavi; i++) {
			sb.append("<a href='/notice/list?page="+i+"'>"+i+"</a>");
		}
		return sb.toString();
	}

	/**
	 * 페이지 전체 게시물 가져오는 메소드
	 * @param conn
	 * @return
	 */
	public int getRecordTotalCount(Connection conn) {
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM NOTICE_TBL";
		int recordTotalCount = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rset = pstmt.executeQuery();
			while(rset.next()) {
				recordTotalCount = rset.getInt("TOTALCOUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recordTotalCount;
	}
	
	/**
	 * 공지사항 상세조회 
	 * @param conn
	 * @param noticeNo
	 * @return
	 */
	public Notice selectOneByNo(Connection conn, int noticeNo) {
		String query = "SELECT * FROM NOTICE_TBL WHERE NOTICE_NO = ? ";
		Notice notice = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			ResultSet rset = pstmt.executeQuery();
			while(rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("NOTICE_NO"));
				notice.setNoticeSubject(rset.getString("NOTICE_SUBJECT"));
				notice.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				notice.setNoticeWriter(rset.getString("NOTICE_WRITER"));
				notice.setNoticeDate(rset.getTimestamp("NOTICE_DATE"));
				notice.setViewCount(rset.getInt("VIEW_COUNT"));
			}
			rset.close();
			pstmt.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return notice;
	}
	public int deleteNotice(Connection conn, int noticeNo) {
		String query = "DELETE FROM NOTICE_TBL WHERE NOTICE_NO = ?";
		int result = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public int updateNotice(Connection conn, Notice notice) {
		String query="UPDATE NOTICE_TBL SET NOTICE_SUBJECT=?, NOTICE_CONTENT=? WHERE NOTICE_NO = ?";
		int result=0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, notice.getNoticeSubject());
			pstmt.setString(2, notice.getNoticeContent());
			pstmt.setInt(3, notice.getNoticeNo());
			result = pstmt.executeUpdate();
			pstmt.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
