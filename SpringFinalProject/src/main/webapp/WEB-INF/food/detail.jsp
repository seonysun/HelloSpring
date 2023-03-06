<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="wrapper row3 rows">
  <main class="container clear">
	<table class="table">
	  <tr>
	  	<td v-for="img in posters">
	  		<img :src="img" style="width: 100%">
	  	</td>
	  </tr>
	</table>
	<table class="table">
	  <tr>
	 	<td colspan="2">
	  		<h3>{{vo.name }}&nbsp;<span style="color: orange">{{vo.score }}</span></h3>
	  	</td>
	  </tr>
	  <tr>
	  	<th width=20%>주소</th>
	  	<td width=80%>{{vo.address }}</td>
	  </tr>
	  <tr>
	  	<th width=20%>전화</th>
	  	<td width=80%>{{vo.tel }}</td>
	  </tr>
	  <tr>
	  	<th width=20%>음식종류</th>
	  	<td width=80%>{{vo.type }}</td>
	  </tr>
	  <tr>
	  	<th width=20%>가격대</th>
	  	<td width=80%>{{vo.price }}</td>
	  </tr>
	  <tr>
	  	<th width=20%>주차</th>
	  	<td width=80%>{{vo.parking }}</td>
	  </tr>
	  <tr>
	  	<th width=20%>영업시간</th>
	  	<td width=80%>{{vo.time }}</td>
	  </tr>
	  <tr v-if="vo.menu!='no'">
	  	<th width=20%>메뉴</th>
	  	<td width=80%>
	  		<ul v-for="m in menus">
		  		<li>{{m }}원</li>
	  		</ul>
	  	</td>
	  </tr>
	</table>
  </main>
</div>
<script>
	new Vue({
		el:'.rows',
		data:{
			fno:${fno},
			vo:{},
			posters:[],
			menus:[]
		},
		mounted:function(){
			let _this=this
			axios.get("http://localhost/web/food/food_detail_vue.do",{
				params:{
					fno:_this.fno
				}
			}).then(function(response){
				console.log(response.data)
				_this.vo=response.data
				_this.posters=_this.vo.poster.split('^')
				_this.menus=_this.vo.menu.split("원")
			})
		}
	})
</script>
</body>
</html>