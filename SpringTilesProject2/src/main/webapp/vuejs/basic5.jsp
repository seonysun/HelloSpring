<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
h1{
	text-align: center;
}
.ddd:hover{
	cursor: pointer;
}
</style>
</head>
<body>
	<div class=container-fluid>
		<div class=col-sm-4>
		  <div class=text-center>
			<button class="btn btn-sm btn-danger" v-on:click="change(1)">믿고 보는 맛집 리스트</button>
			<button class="btn btn-sm btn-success" v-on:click="change(2)">지역별 맛집 리스트</button>
			<button class="btn btn-sm btn-primary" v-on:click="change(3)">메뉴별 맛집 리스트</button>
		  </div>
		  <div style="height: 20px"></div>
		  <div class=col-md-6 v-for="cvo in cate_list">
		  	<div class="thumbnail">
		        <img :src="cvo.poster" style="width:200px;height: 150px" v-on:click="foodList(cvo.cno)">
		        <div class="caption">
		          <p>{{cvo.title}}</p>
		        </div>
		    </div>
		  </div>
		</div>
		<div class=col-sm-3 v-show=isShow>
			<div class="jumbotron">
				<h4 class="text-center">{{title }}</h4>
				<h5 class="text-center">{{subject }}</h5>
			</div>
			<table class="table">
			  <tr>
			  	<td>
					<table class="table" v-for="fvo in food_list">
					  <tr>
					  	<td width=30% class="text-center" rowspan="4">
					  		<img :src="fvo.poster" style="width: 130px" class="img-rounded" v-on:click="foodDetail(fvo.fno)">
					  	</td>
					  	<td width=70%>
					  		<h4 v-on:click="foodDetail(fvo.fno)" class="ddd"><b>{{fvo.name }}</b>&nbsp;<span style="color: orange">{{fvo.score }}</span></h4>
					  	</td>
					  </tr>
					  <tr>
					  	<td width=70%>{{fvo.address }}</td>
					  </tr>
					  <tr>
					  	<td width=70%>{{fvo.tel }}</td>
					  </tr>
					  <tr>
					  	<td width=70%>{{fvo.type }}</td>
					  </tr>
					</table>
			  	</td>
			  </tr>
			</table>
		</div>
		<div class=col-sm-5 v-show=isShow2>
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
				  		<ul>
							<li v-for="m in menus">{{m }}원</li>
				  		</ul>
				  	</td>
				  </tr>
			</table>
		</div>
	</div>
	<script>
		new Vue({
			el:'.container-fluid',
			data:{
				cate_list:[],
				food_list:[],
				vo:{},
				title:'',
				subject:'',
				isShow:false,
				isShow2:false,
				posters:[],
				menus:[]
			},
			mounted:function(){
				let _this=this
				axios.get('http://localhost/web/food/category_vue.do',{
					params:{
						no:1
					}
				}).then(function(response){
					console.log(response.data)
					_this.cate_list=response.data
				})
			},
			methods:{
				change:function(no){
					let _this=this
					axios.get('http://localhost/web/food/category_vue.do',{
						params:{
							no:no
						}
					}).then(function(response){
						console.log(response.data)
						_this.cate_list=response.data
					})
				},
				foodList:function(cno){
					this.isShow=true
					let _this=this
					axios.get('http://localhost/web/food/food_list_vue.do',{
						params:{
							no:cno
						}
					}).then(function(response){
						console.log(response.data)
						_this.food_list=response.data
						_this.title=response.data[0].title
						_this.subject=response.data[0].subject
					})
				},
				foodDetail:function(fno){
					this.isShow2=true
					let _this=this
					axios.get('http://localhost/web/food/food_detail_vue.do',{
						params:{
							fno:fno
						}
					}).then(function(response){
						console.log(response.data)
						_this.vo=response.data
						_this.posters=_this.vo.poster.split('^')
						_this.menus=_this.vo.menu.split("원")
					})
				}
			}
		})
	</script>
</body>
</html>