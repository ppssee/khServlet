package com.kh.radiobtn;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/radioResult.do")
public class RadioServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gender = request.getParameter("gender");
		String chkMail = request.getParameter("chk-mail");
		
		request.setAttribute("gender", gender);
		request.setAttribute("chkMail", chkMail);
		request.getRequestDispatcher("/radiobtn/radioResult.jsp").forward(request, response);
		
		
//		response.setContentType("text/html; charset=utf-8");
//		PrintWriter out = response.getWriter();
//		out.println("<html><head><title>수신 결과</title></head><body>");
//		out.println("성별 : " + gender + "<br>");
//		out.println("메일 수신 여부 : " + chkMail + "<br>");
//		out.println("</body></html>");
//		
	}

}
