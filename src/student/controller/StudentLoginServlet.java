package student.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import student.model.service.StudentService;
import student.model.vo.Student;

@WebServlet("/student/login.do")
public class StudentLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/views/student/login.jsp").forward(request, response);

	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String studentId = request.getParameter("student-id");
		String studentPw = request.getParameter("student-pw");
		Student student = new Student(studentId, studentPw);
		StudentService stdService = new StudentService();
		Student sOne = stdService.selectCheckLogin(student);
		if(sOne != null) {
			request.getRequestDispatcher("/WEB-INF/views/student/loginSuccess.jsp").forward(request, response);
			
		} else {
			request.setAttribute("title", "로그인 실패");
			request.setAttribute("msg", "아이디와 비밀번호를 확인해주세요.");
			request.getRequestDispatcher("/WEB-INF/views/student/error.jsp").forward(request, response);
		}
	}

}
