package notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.Notice;

@WebServlet("/notice/list")
public class NoticeListServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");	
		
		NoticeService nService = new NoticeService(); 
		List<Notice> nList = nService.selectAllNotice();  
		
		request.setAttribute("nList", nList);
		request.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(request, response);
		
	}

}
