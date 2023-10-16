<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script>

  $(function(){
    fnListContact();
  })
  
  function fnListContact(){
  $('#btn_list').click(function(){
    location.href = '${contextPath}/contact/list.do';
  })
  }
  
</script>
</head>
<body>

  <div>
    <h1>공지 작성하기</h1>
    <form method="post" action="${contextPath}/contact/add.do">
      <div>구분
        <select name="gubun">
          <option id="normal" value="1" selected="selected">일반</option>
          <option id="fast" value="2">긴급</option>
        </select>
      </div>
      <div>
        제목 <input type="text" id="title" name="title"><br>
        내용
      </div>
      <div>
        <textarea rows="5" cols="30" name="content" id="content"></textarea>
      </div>
      <div>
        <button type="submit">작성완료</button>
        <button type="button" id="btn_list">목록</button>
      </div>
    </form>
  </div>

</body>

</html>