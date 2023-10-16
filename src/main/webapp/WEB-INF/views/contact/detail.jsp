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
    fnModifyResult();
    fnDeleteContact();
    fnListContact();
  })
  
  function fnModifyResult(){
    var modifyResult = '${modifyResult}';
    if(modifyResult !== ''){
      if(modifyResult === '1'){
        alert('공지사항이 수정되었습니다.');
      } else {
        alert('공지사항 수정이 실패했습니다.');
      }
    }
  }
  
  function fnDeleteContact(){
  $('#btn_delete').click(function(){
    if(confirm('공지사항을 삭제할까요?')){
      $('#frm_detail').attr('action', '${contextPath}/contact/delete.do');
      $('#frm_detail').submit();
    }
  })
  }

  function fnListContact(){
  $('#btn_list').click(function(){
    location.href = '${contextPath}/contact/list.do';
  })
  }
  
</script>
</head>
<body>

  <div>
    <h2>${contact.notice_no}번 공지사항</h2>
    <form id="frm_detail" method="post" action="${contextPath}/contact/modify.do">
      <div>구분 : 일반</div>
      <div>제목 : ${contact.title}</div>
      <div>${contact.content}</div>
      
      <hr>
      
      <div>
        <input type="hidden" name="notice_no" value="${contact.notice_no}">
        <button type="submit">편집</button>
        <button type="button" id="btn_delete">삭제</button>
        <button type="button" id="btn_list">목록</button>
      </div>
    </form>
  </div>

</body>
</html>