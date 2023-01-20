<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공!</title>
</head>
<body>
	<h1>로그인 성공했어요</h1>
    ${memberId }님 환영합니다 <br>

    <input type="hidden" id="member-id" value="${memberId }"> 
    <a href="/member/myInfo.kh?member-id=${memberId }">마이페이지</a> 
    
    <!-- javascript:void(0) a태그의 링크 기능 무효화 시킴. 클릭만 작동시키기 위해서. -->
    <a href="javascript:void(0);" onclick="removeCheck()">회원탈퇴</a>   
    
    
    <script>
        function removeCheck(){
            if(confirm("회원탈퇴를 하시겠습니까?")){
                var memberId = document.querySelector("#member-id").value;
                location.href ="/member/remove.kh?member-id="+memberId;
            }
            
        }
    </script>
</body>
</html>