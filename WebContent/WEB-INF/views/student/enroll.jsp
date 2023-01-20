<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>학생 마이 페이지</title>
    <link rel="stylesheet" href="/resources/css/student.css">
</head>

<body>
    <h1>학생 가입 정보</h1>
    <div>
        <form action="/student/enroll.do" method="post">
            <fieldset>
                <legend>학생 가입 정보</legend>
                <ul>
                    <li>
                        <label for="id">아이디</label>
                        <input type="text" name="id" id="id">
                    </li>
                    <li>
                        <label for="pw">비밀번호</label>
                        <input type="password" name="pw" id="pw">
                    </li>
                    <li>
                        <label for="name">이름</label>
                        <input type="text" name="name" id="name">
                    </li>
                    <li>
                        <label for="email">이메일</label>
                        <input type="text" name="email" id="email">
                    </li>
                    <li>
                        <label for="phone">전화번호</label>
                        <input type="text" name="phone" id="phone">
                    </li>
                    <li>
                        <label for="addr">주소</label>
                        <input type="text" name="addr" id="addr">
                    </li>
                    <li>
                        <label for="gender">성별</label>
                        <input type="text" name="gender" id="gender" placeholder="M 또는 F 를 입력해주세요.">
                    </li>
                </ul>
            </fieldset>
            <div>
                <input type="submit" value="가입하기">
                <input type="reset" value="초기화">
            </div>
        </form>
    </div>
</body>
</html>