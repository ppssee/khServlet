package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

@WebServlet("/notice/modify")
public class NoticeModifyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strNo = request.getParameter("notice-no") != null ? request.getParameter("notice-no") : "0";
		int noticeNo = Integer.parseInt(strNo);
		NoticeService nService = new NoticeService();
		Notice notice = nService.selectOneByNo(noticeNo);
		request.setAttribute("notice", notice);
		request.getRequestDispatcher("/WEB-INF/views/notice/modify.jsp").forward(request, response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String noticeSubject = request.getParameter("notice-subject");
		String noticeContent = request.getParameter("notice-content");
		String strNo = request.getParameter("notice-no") != null ? request.getParameter("notice-no") : "0";
		int noticeNo = Integer.parseInt(strNo);
		Notice notice = new Notice(noticeNo, noticeSubject, noticeContent); 
		NoticeService nService = new NoticeService();
		int result = nService.updateNotice(notice);
		
		if(result>0) {	
			response.sendRedirect("/notice/detail?notice-no="+noticeNo);
		} else {
			request.setAttribute("title","공지사항 수정 실패");
			request.setAttribute("msg","공지사항이 수정되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		}
		
		
	}

}
