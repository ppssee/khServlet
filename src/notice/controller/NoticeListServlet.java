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
import notice.model.vo.PageData;

@WebServlet("/notice/list")
public class NoticeListServlet extends HttpServlet {

	// 페이징 처리 
	// 1. 전체 게시물의 갯수
	// 2. 쿼리문이 변경되어야 함.
	//   - 현재 페이지 넘버에 의해 변경되어야 함.
	//   - SELECT * FROM ( 
	//		SELECT ROW_NUMBER() OVER(ORDER BY NOTICE_NO DESC) AS NUM, NOTICE_TBL.* FROM NOTICE_TBL)
	//		WHERE NUM BETWEEN 11 AND 20;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 

		String strCurr = request.getParameter("page")
				         != null ? request.getParameter("page") : "1"; // 없으면 1번 페이지 
        
		int currentPage = Integer.parseInt(strCurr);
		NoticeService nService = new NoticeService(); 
		PageData pd = nService.selectAllNotice(currentPage);  
		List<Notice> nList = pd.getnList();
		String pageNavigator = pd.getPageNavigator();
		request.setAttribute("nList", nList);
		request.setAttribute("pageNavi", pageNavigator);
		request.getRequestDispatcher("/WEB-INF/views/notice/list.jsp").forward(request, response);
		
	}

}
