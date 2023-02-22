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
		<div class="row">
			<div class="text-center">
				<a href="../seoul/list.do?cate=1" class="btn btn-lg btn-danger">서울 명소</a>
				<a href="../seoul/list.do?cate=2" class="btn btn-lg btn-success">서울 자연</a>
				<a href="../seoul/list.do?cate=3" class="btn btn-lg btn-primary">서울 쇼핑</a>
			</div>
			<div style="height: 10px"></div>
			<div class="text-center">
			  <form method="post" action="../seoul/find.do">
				<input type="text" name="addr" size=20 class="input-sm">
				<input type="submit" value="검색">
			  </form>
			</div>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<c:forEach var="vo" items="${list }">
				<div class="col-md-3">
				    <div class="thumbnail">
				      <a href="../seoul/detail.do?no=${vo.no }">
				        <img src="${vo.poster }" title="${vo.title }" style="width:250px;height:200px">
				        <div class="caption"><p>${vo.title }</p></div>
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
					<li><a href="../seoul/list.do?cate=${cate }&page=${startpage-1 }">&lt;</a></li>
				  </c:if>
					<c:forEach var="i" begin="${startpage }" end="${endpage }">
						<li ${curpage==i?"class=active":"" }><a href="../seoul/list.do?cate=${cate }&page=${i }">${i }</a></li>
					</c:forEach>
				  <c:if test="${endpage<totalpage }">
					<li><a href="../seoul/list.do?cate=${cate }&page=${endpage+1 }">&gt;</a></li>
				  </c:if>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>