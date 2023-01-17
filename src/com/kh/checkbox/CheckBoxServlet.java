package com.kh.checkbox;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.View;

@WebServlet("/showResult.do")
public class CheckBoxServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");		
		
		String values[] = request.getParameterValues("place");
		request.setAttribute("places", values);
		request.getRequestDispatcher("/checkbox/checkResult.jsp").forward(request, response);
		
		
		
//		PrintWriter out = response.getWriter();
//		out.println("<html><head><title>관광지 선택 결과</title></head>");
//		out.println("<body>");
//		out.println("<h1>관광지 선택결과</h1>");
//		for(String value : values) {
//			out.println("<p>"+value+"</p>");
//		}
//		out.println("</body></html>");
	}
	
}
