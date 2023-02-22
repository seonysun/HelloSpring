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
			<table class="table">
			  <tr>
			  	<c:forTokens items="${vo.poster }" delims="^" var="img">
			  		<td><img src="${img }" style="width: 100%"></td>
			  	</c:forTokens>
			  </tr>
			</table>
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<div class="col-sm-8">
				<table class="table">
				  <tr>
				  	<td colspan="2">
				  		<h3>${vo.name }&nbsp;<span style="color: orange">${vo.score }</span></h3>
				  	</td>
				  </tr>
				  <tr>
				  	<th width=20%>주소</th>
				  	<td width=80%>${vo.address }</td>
				  </tr>
				  <tr>
				  	<th width=20%>전화</th>
				  	<td width=80%>${vo.tel }</td>
				  </tr>
				  <tr>
				  	<th width=20%>음식종류</th>
				  	<td width=80%>${vo.type }</td>
				  </tr>
				  <tr>
				  	<th width=20%>가격대</th>
				  	<td width=80%>${vo.price }</td>
				  </tr>
				  <tr>
				  	<th width=20%>주차</th>
				  	<td width=80%>${vo.parking }</td>
				  </tr>
				  <tr>
				  	<th width=20%>영업시간</th>
				  	<td width=80%>${vo.time }</td>
				  </tr>
				  <tr>
				  	<th width=20%>메뉴</th>
				  	<td width=80%>${vo.menu }</td>
				  </tr>
				  <tr>
				  	<td colspan=2 class="text-right">
				  		<b>맛있다(${vo.good })&nbsp;괜찮다(${vo.soso })&nbsp;별로(${vo.bad })</b>&nbsp;
				  		<input type=button class="btn btn-xs btn-danger" value="목록" onclick="javascript:history.back()">
				  																<!-- hit 수 등 업데이트 필요한 데이터 포함 시에는 링크로 줘야 함 -->
				  	</td>
				  </tr>
				</table>
			</div>
			<div class="col-sm-4">
			
			</div>
		</div>
	</div>
</body>
</html>