package notice.model.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import common.JDBCTemplate;
import notice.model.dao.NoticeDAO;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {
	private NoticeDAO nDao;
	
	public NoticeService() {
		nDao = new NoticeDAO();
	}
	/**
	 * 공지사항등록
	 * @param notice
	 * @return
	 */
	public int registerNotice(Notice notice) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nDao.insertNotice(conn, notice);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	/**
	 * 공지사항 목록 조회
	 * @return
	 */
	public PageData selectAllNotice(int currentPage) {
		// 값을 2개 넘기고 싶다면
		// 1. 새로운 VO 생성,
		// 2. 해쉬맵 사용. put-> 1번 키밸류 2번 키밸류 
		Connection conn = JDBCTemplate.getConnection();
		List<Notice> nList = nDao.selectAllNotice(conn, currentPage);
		String pageNavigator = nDao.generatePageNavi(conn, currentPage);
		PageData pd = new PageData();
		pd.setnList(nList);
		pd.setPageNavigator(pageNavigator);
		
		return pd;
	}
	/**
	 * 공지사항 상세 조회
	 * @param noticeNo
	 * @return
	 */
	public Notice selectOneByNo(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		Notice notice = nDao.selectOneByNo(conn, noticeNo);
		return notice;
	}
	/**
	 * 공지사항 삭제
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(int noticeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nDao.deleteNotice(conn, noticeNo);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}
	/**
	 * 공지사항 정보 수정
	 * @param notice
	 * @return
	 */
	public int updateNotice(Notice notice) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nDao.updateNotice(conn, notice);
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		return result;
	}

}
