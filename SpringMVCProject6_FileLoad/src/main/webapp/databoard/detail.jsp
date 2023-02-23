<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row{
   margin: 0px auto;
   width:800px;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
  <div class="container">
    <h1>상세보기</h1>
    <div class="row">
      <table class="table">
        <tr>
         <th width=20% class="text-center success">번호</th>
         <td width=30% class="text-center">${vo.no }</td>
         <th width=20% class="text-center success">작성일</th>
         <td width=30% class="text-center">${vo.dbday }</td>
        </tr>
        <tr>
         <th width=20% class="text-center success">이름</th>
         <td width=30% class="text-center">${vo.name }</td>
         <th width=20% class="text-center success">조회수</th>
         <td width=30% class="text-center">${vo.hit }</td>
        </tr>
        <tr>
         <th width=20% class="text-center success">제목</th>
         <td colspan="3">${vo.subject }</td>
        </tr>
        <c:if test="${vo.filecount>0 }">
	        <tr>
	         <th width=20% class="text-center success">첨부파일</th>
	         <td colspan="3">
	           <ul>
	             <c:forEach var="fn" items="${nList }" varStatus="s">
	               <li><a href="download.do?fn=${fn }">${fn }</a>(${sList[s.index]}Bytes)</li>
	               													<!-- list는 배열이므로 인덱스 번호 이용하면 직접 호출 가능 -->
	             </c:forEach>
	           </ul>
	         </td>
	        </tr>
        </c:if>
        <tr>
          <td colspan="4" class="text-left" valign="top" height="200"><pre style="white-space: pre-wrap;border: none;background-color: white">${vo.content }</pre></td>
        </tr>
        <tr>
          <td colspan="4" class="text-right">
            <a href="#" class="btn btn-xs btn-info">수정</a>
            <a href="#" class="btn btn-xs btn-success">삭제</a>
            <a href="list.do" class="btn btn-xs btn-warning">목록</a>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>