package member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

@WebServlet("/member/update.kh")
public class UpdateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String memberId = request.getParameter("member-id");
		String memberPw = request.getParameter("member-pw");
		String memberEmail = request.getParameter("member-email");
		String memberPhone = request.getParameter("member-phone");
		String memberAddress = request.getParameter("member-address");
		String memberHobby = request.getParameter("member-hobby");

		Member member = new Member(memberId, memberPw, memberEmail, memberPhone, memberAddress, memberHobby);
		MemberService mService = new MemberService();
		int result = mService.updateMember(member);
		if(result>0) {
			HttpSession session = request.getSession();
			session.setAttribute("memberId", memberId);
			response.sendRedirect("/index.jsp");
//			request.setAttribute("memberId", memberId);
//			request.getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			request.setAttribute("title", "정보 수정 실패");
			request.setAttribute("msg", "회원 정보 수정을 실패했습니다.");
			request.getRequestDispatcher("/WEB-INF/views/member/error.jsp").forward(request, response);
		}
	}

}
