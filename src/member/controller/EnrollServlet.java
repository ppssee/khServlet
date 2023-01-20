package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/member/enrollView.kh")
public class EnrollServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/member/enroll.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("member-id");
		String memberPw = request.getParameter("member-pw");
		String memberName = request.getParameter("member-name");
		int memberAge =Integer.parseInt(request.getParameter("member-age"));
		String memberEmail = request.getParameter("member-email");
		String memberPhone = request.getParameter("member-phone");
		String memberAddress = request.getParameter("member-address");
		String memberGender = request.getParameter("member-gender");
		String memberHobby = request.getParameter("member-hobby");
		Member member = new Member(memberId, memberPw, memberName, memberAge, memberEmail, memberPhone, memberAddress, memberGender, memberHobby);
		

		MemberService mService = new MemberService();
		int result = mService.registerMember(member);
		if(result > 0) {
			//가입성공
			response.sendRedirect("/index.jsp");
		} else {
			//가입실패
			request.setAttribute("title", "회원 가입 실패");
			request.setAttribute("msg", "회원가입이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/member/error.jsp").forward(request, response);
		}
		
		
	}

}
