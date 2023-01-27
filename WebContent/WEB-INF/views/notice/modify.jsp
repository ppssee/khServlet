<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 수정</title>
<link rel="stylesheet" href="/resources/css/notice.css">
</head>
<body>
	<h1>공지사항 수정</h1>
    <form action="/notice/modify" method="post">
    	<input type="hidden" name = "notice-no" value="${notice.noticeNo }">
    	
        <fieldset>
            <legend>공지사항 수정</legend>
            <ul>
                <li>
                    <label>제목</label>
                    <input type="text" name = "notice-subject" value ="${notice.noticeSubject }">
                </li>
                <li>
                    <label>내용</label>
                    <textarea cols="45" rows="30" name = "notice-content">${notice.noticeContent }</textarea>
                </li>
            </ul>
            
            
        </fieldset>
        <div class="btn-area">
            <input type="submit" value="수정하기" >
            <input type="reset" value="초기화" >
        </div>
    </form>
</body>
</html>