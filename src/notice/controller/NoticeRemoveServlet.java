package notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;

@WebServlet("/notice/remove")
public class NoticeRemoveServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String noticeId = request.getParameter("notice-no") != null ? request.getParameter("notice-no") : "0";
		int noticeNo = Integer.parseInt(noticeId);                                                                                                                                                                                                               
		
		NoticeService nService = new NoticeService();
		int result = nService.deleteNotice(noticeNo);
		if(result>0) {
			
			response.sendRedirect("/notice/list");
		} else {
			request.setAttribute("title","공지사항 삭제 실패");
			request.setAttribute("msg","공지사항이 삭제되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(request, response);
		}
		
		
	}

}
