<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세조회</title>
<link rel="stylesheet" href="/resources/css/notice.css">
</head>
<body>

	<h1>공지사항 상세</h1>
	<ul>
		<li>
			<label>글번호</label>
			<input type="text" value="${notice.noticeNo }" class="border-remove" id="notice-no" readonly>
		</li>
		<li>
			<label>작성일</label>
			<input type="text" value="${notice.noticeDate }" class="border-remove" readonly>
		</li>
		<li>
			<label>글쓴이</label>
			<input type="text" value=" ${notice.noticeWriter }" class="border-remove" readonly>
		</li>
		<li>
			<label>제목</label>
			<input type="text" value=" ${notice.noticeSubject }"  class="border-remove" readonly>
		</li>
		<li>
			<label>내용</label>
			<textarea rows="30" cols="40"  class="border-remove" readlonly ">${notice.noticeContent }</textarea>
		</li>
	</ul>
	<a href="/notice/list">목록으로</a>
	<a href="#">수정하기</a>
	<a href="javascript:void(0);" onclick="deleteCheck();">삭제하기</a>
	<script>
	function deleteCheck(){
		 if(confirm("삭제하시겠습니까?")){
             var noticeNo = "${notice.noticeNo}"
             location.href ="/notice/remove?notice-no="+noticeNo;
         }
	}
	</script>	
</body>
</html>