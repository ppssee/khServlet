package student.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.model.service.StudentService;
import student.model.vo.Student;


@WebServlet("/student/enroll.do")
public class StudentEnrollServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/student/enroll.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String studentId = request.getParameter("id");
		String studentPw = request.getParameter("pw");
		String studentName = request.getParameter("name");
		String studentEmail = request.getParameter("email");
		String studentPhone = request.getParameter("phone");
		String studentAddr = request.getParameter("addr");
		String gender = request.getParameter("gender");

		Student student = new Student(studentId, studentPw, studentName, studentEmail, studentPhone, studentAddr, gender);
		StudentService sService = new StudentService();
		int result = sService.registerMember(student);
		
		if(result>0) {
			response.sendRedirect("/student/login.do");
		}else {
			request.setAttribute("title", "회원 가입 실패");
			request.setAttribute("msg", "회원가입이 완료되지 않았습니다.");
			request.getRequestDispatcher("/WEB-INF/views/student/error.jsp").forward(request, response);
		}
		
	}

}
