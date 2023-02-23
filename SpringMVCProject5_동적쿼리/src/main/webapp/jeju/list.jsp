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
   width: 1200px;
}
h1{
   text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<h1>관광지 & 레저</h1>
		<div class="row">
			<c:forEach var="vo" items="${list }">
			  <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="../jeju/detail_before.do?no=${vo.no }">
			        <img src="${vo.poster }" style="width:250px;height:200px">
			        <div class="caption">
			        	<p>${vo.title }</p>
			        	<p>입장료: ${vo.price }원</p>
			        </div>
			      </a>
			    </div>
			  </div>
			</c:forEach>
		</div>
		<div style="height: 10px"></div>
		
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
				  <c:if test="${startpage>1 }">
					<li><a href="../jeju/list.do?page=${startpage-1 }">&lt;</a></li>
				  </c:if>
					<c:forEach var="i" begin="${startpage }" end="${endpage }">
						<li ${curpage==i?"class=active":"" }><a href="../jeju/list.do?page=${i }">${i }</a></li>
					</c:forEach>
				  <c:if test="${endpage<totalpage }">
					<li><a href="../jeju/list.do?page=${endpage+1 }">&gt;</a></li>
				  </c:if>
				</ul>
			</div>
		</div>
		<div style="height: 10px"></div>
		
		<h3>최근 방문 장소</h3>
		<hr>
		<div class="row">
			<c:forEach var="vo" items="${cList }">
			  <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="../jeju/detail.do?no=${vo.no }">
			        <img src="${vo.poster }" style="width:250px;height:200px">
			        <div class="caption">
			        	<p>${vo.title }</p>
			        </div>
			      </a>
			    </div>
			  </div>
			</c:forEach>
		</div>
	</div>
</body>
</html>