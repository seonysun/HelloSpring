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
	<div class="jumbotron">
		<h3 class="text-center">{{cate_info.title }}</h3>
		<h4 class="text-center">{{cate_info.subject }}</h4>
	</div>
		<table class="table">
		  <tr v-for="vo in food_list">
		  	<td>
				<table class="table">
				  <tr>
				  	<td width=30% class="text-center" rowspan="4">
				  	  <a :href="'../food/food_detail_before.do?fno='+vo.fno">
				  		<img :src="vo.poster" style="width: 200px" class="img-rounded">
				  	  </a>
				  	</td>
				  	<td width=70%>
				  		<h3><a :href="'../food/food_detail_before.do?fno='+vo.fno">{{vo.name }}</a>&nbsp;<span style="color: orange">${fvo.score }</span></h3>
				  	</td>
				  </tr>
				  <tr>
				  	<td width=70%>{{vo.address }}</td>
				  </tr>
				  <tr>
				  	<td width=70%>{{vo.tel }}</td>
				  </tr>
				  <tr>
				  	<td width=70%>{{vo.type }}</td>
				  </tr>
				</table>
		  	</td>
		  </tr>
		</table>
  
  </main>
</div>
<script>
	new Vue({
		el:'.rows',
		data:{
			cno:${cno},
			food_list:[],
			cate_info:{}
		},
		mounted:function(){
			let _this=this
			//카테고리 정보 출력
			axios.get("http://localhost/web/food/category_info_vue.do",{
				params:{
					cno:_this.cno
				}
			}).then(function(response){
				console.log(response.data)
				_this.cate_info=response.data
			})
			//카테고리별 맛집 리스트 출력
			axios.get("http://localhost/web/food/food_list_vue.do",{
				params:{
					cno:_this.cno
				}
			}).then(function(response){
				console.log(response.data)
				_this.food_list=response.data				
			})
		}
	})
</script>
</body>
</html>