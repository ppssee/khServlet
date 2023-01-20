<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생 로그인 페이지</title>
<link rel="stylesheet" href="/resources/css/student.css">
</head>
<body>
	<h1>학생 로그인 페이지</h1>
	<div>
		<form action="/student/login.do" method="post">
			<fieldset>
				<legend>로그인 정보</legend>
				<ul>
					<li>
						<label for="id">아이디</label>
						<input type="text" id="id" name="student-id" placholder="ID를 입력해주세요">
					</li>
					<li>
						<label for="pw">비밀번호</label>
						<input type="password" id="pw" name="student-pw" placholder="PW를 입력해주세요">
					</li>
				</ul>
			</fieldset>
			<div>
				<input type="submit" value="로그인">
				<input type="reset" value="초기화">
			</div>
		</form>
	</div>

</body>
</html>