<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="vo" items="${jList }">
		<div class="col-md-3">
		    <div class="thumbnail">
		      <a href="../jeju/food_detail.do?no=${vo.no }">
		        <img src="${vo.poster }" style="width: 300px;height: 220px">
		        <div class="caption">
		          <p style="font-size: 8px">${vo.title }</p>
		        </div>
		      </a>
		    </div>
		</div>
	</c:forEach>
	<div style="height: 10px"></div>
	<div class="text-center">
		<ul class="pagination">
		  <c:if test="${startpage>1 }">
		  	<li><a href="../jeju/food.do?page=${startpage-1 }">&lt;</a></li>
		  </c:if>
		  <c:forEach var="i" begin="${startpage }" end="${endpage }">
			  <li ${i==curpage?"class=active":"" }><a href="../jeju/food.do?page=${i }">${i }</a></li>
		  </c:forEach>
		  <c:if test="${endpage<totalpage }">
			<li><a href="../jeju/food.do?page=${endpage+1 }">&gt;</a></li>
		  </c:if>
		</ul>
	</div>
</body>
</html>