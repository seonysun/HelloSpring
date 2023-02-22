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
		<h3 class="text-center" style="color: gray">${vo.title }</h3>
		<table class="table">
		  <tr>
		  	<td><img src="${vo.poster }" style="width: 1200px;height: 600px"></td>
		  </tr>
		  <tr>
		  	<td>${vo.address }</td>
		  </tr>
		  <tr>
		  	<td>${vo.msg }</td>
		  </tr>
		  <tr>
		  	<td style="align-content: center">
		  		<input type=button class="btn btn-sm btn-primary" value="목록" onclick="javascript:history.back()">
		  	</td>
		  </tr>
		</table>
		<div style="height: 10px"></div>

		<h3>인근 맛집</h3>
		<hr>
		<div class="row">
			<c:forEach var="fvo" items="${list }">
			  <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="#">
			        <img src="${fvo.poster }" style="width:250px;height:200px">
			        <div class="caption">
			        	<p>${fvo.name }</p>
			        </div>
			      </a>
			    </div>
			  </div>
			</c:forEach>
		</div>
	</div>
</body>
</html>