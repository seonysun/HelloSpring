<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 200px;
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
			<div class="col-md-4">
			    <div class="thumbnail">
			      <a href="../jeju/list.do">
			        <img src="../image/jeju.png" style="width:380px;height:250px">
			        <div class="caption"><p>제주여행</p></div>
			      </a>
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="thumbnail">
			      <a href="../seoul/list.do">
			        <img src="../image/seoul.jpg" style="width:380px;height:250px">
			        <div class="caption"><p>서울여행</p></div>
			      </a>
			    </div>
			</div>
			<div class="col-md-4">
			    <div class="thumbnail">
			      <a href="../food/category.do">
			        <img src="../image/food.jpg" style="width:380px;height:250px">
			        <div class="caption"><p>맛집</p></div>
			      </a>
			    </div>
			</div>
		</div>
	</div>
</body>
</html>