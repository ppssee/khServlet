package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/member/myInfo.kh")
public class MyInfoServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("member-id");
		if(memberId != null) {  	// id값이 존재할 때	
			MemberService mService = new MemberService();
			Member member = mService.printOneById(memberId);
			request.setAttribute("member", member);  //객체 전달
			request.getRequestDispatcher("/WEB-INF/views/member/myInfo.jsp").forward(request, response);
		} else { 	// id가 없을 때
			request.setAttribute("title", "아이디로 조회 실패");
			request.setAttribute("msg", "해당하는 데이터가 존재하지 않습니다.");
			request.getRequestDispatcher("/WEB-INF/view/member/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
