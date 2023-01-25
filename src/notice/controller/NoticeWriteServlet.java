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

@WebServlet("/notice/write")
public class NoticeWriteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session != null && (session.getAttribute("memberId")) != null) {			
			request.getRequestDispatcher("/WEB-INF/views/notice/write.jsp").forward(request, response);
		} else {
			request.setAttribute("title", "로그인 필요");
			request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		}
	}
	
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String noticeSubject = request.getParameter("subject");
		String noticeContent = request.getParameter("content");
		HttpSession session = request.getSession();
		
		if(session != null && (session.getAttribute("memberId")) != null) {
			String memberId = (String)session.getAttribute("memberId");
			Notice notice = new Notice(noticeSubject, noticeContent);
			notice.setNoticeWriter(memberId);
			
			NoticeService nService = new NoticeService();
			int result = nService.registerNotice(notice);
			if(result>0) {
				response.sendRedirect("/notice/list");
			} else {
				request.setAttribute("title", "공지사항 등록실패");
				request.setAttribute("msg", "공지사항 등록 완료 실패");
				request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
			}	
		} else {
			request.setAttribute("title", "로그인 필요");
			request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		}
	}
}
