package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;

@WebServlet("/member/login.kh")
public class LoginServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");	
		
		String memberId = request.getParameter("member-id");
		String memberPw = request.getParameter("member-pw");
		
		MemberService mService = new MemberService();
		int result = mService.selectCheckLogin(memberId, memberPw);
		System.out.println(result);
		
	}

}
