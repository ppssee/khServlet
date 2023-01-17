package com.kh.calculator;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/operating.do")
public class CalServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  
		
		int firstNum = Integer.parseInt(request.getParameter("first-num"));
		String opCode = request.getParameter("op-code");
		int secondNum = Integer.parseInt(request.getParameter("second-num"));
		// 프론트에서 백엔드로 데이터가 넘어옴 (서버로 데이터가 넘어왔음.)
		int result = 0;

		switch(opCode) {
		case "+" : result = firstNum + secondNum; break;
		case "-" : result = firstNum - secondNum; break;
		case "*" : result = firstNum * secondNum; break;
		case "/" : result = firstNum / secondNum; break;
		case "%" : result = firstNum % secondNum; break;
		}
		// JSP는 MVC패턴에서  View역할을 할 것임.
		// 그러기 위해서는 Servlet에서 JSP로 결과값을 보내주어야 함.
		// 새로운 키값 생성 키: "result" 값: result
		// 1. JSP에서 쓸 데이터를 request에 저장.
		request.setAttribute("result", result);
		
		// 2. 어떤 JSP에다 request에 set한 데이터를 사용할지 지정해줌(JSP파일은 많이 존재할테니까)
		RequestDispatcher view = request.getRequestDispatcher("/calculator/output.jsp");
		view.forward(request, response);
		
		// request.getRequestDispatcher("asad").forward(request, response);
		
		
		
		
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		out.println("<html><head><title>계산결과</title></head>");
//		out.println("<body>result : ");
//		out.println("<strong>"+result+"</strong>");
//		out.println("</body></html>");
		
	}



}
