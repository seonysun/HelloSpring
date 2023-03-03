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
</style>
</head>
<body>
	<div class=container-fluid>
		<div class=col-md-5>
		  <div class="col-md-4" v-for="vo in food_list">
		    <div class="thumbnail">
		        <img :src="vo.poster" style="width: 200px;height: 200px" v-on:click="foodDetail(vo.no)">
		        <div class="caption">
		          <p style="font-size: 8px">{{vo.title }}</p>
		        </div>
		    </div>
		  </div>
		  <div class="row">
		      <div class="text-center">
		        <input type="button" class="btn btn-sm btn-warning" value="이전" v-on:click="prev()">
		        {{curpage}} page / {{totalpage}} pages
		        <input type="button" class="btn btn-sm btn-info" value="다음" v-on:click="next()">
		      </div>
		  </div>
		</div>
		<div class=col-md-7 v-show="isShow">
			<table class="table">
			  <tr>
			  	<td><img :src="food_detail.poster" style="height: 100%"></td>
			  </tr>
			</table>
			<table class="table">
			  <tr>
			  	<td colspan="2">
			  		<h3>{{food_detail.title }}&nbsp;<span style="color: orange">{{food_detail.score }}</span></h3>
			  	</td>
			  </tr>
			  <tr>
			  	<th width=20%>주소</th>
			  	<td width=80%>
			  		{{food_detail.addr }}
			  		<br>
			  		<sub>지번: {{food_detail.addr2 }}</sub>
			  	</td>
			  </tr>
			  <tr>
			  	<th width=20%>전화</th>
			  	<td width=80%>{{food_detail.tel }}</td>
			  </tr>
			  <tr>
			  	<th width=20%>음식종류</th>
			  	<td width=80%>{{food_detail.type }}</td>
			  </tr>
			  <tr>
			  	<th width=20%>주차</th>
			  	<td width=80%>{{food_detail.parking }}</td>
			  </tr>
			  <tr>
			  	<th width=20%>영업시간</th>
			  	<td width=80%>{{food_detail.time }}</td>
			  </tr>
				  <tr v-if="menus!=='no'">
				  	<th width=20%>메뉴</th>
				  	<td width=80%>
				  		<ul v-for="m in menus">
						  	<li>{{m }}</li>
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
				food_list:[],
				food_detail:{},
				curpage:1,
				totalpage:0,
				isShow:false,
				menus:[]
			},
			mounted:function(){
				let _this=this
				axios.get('http://localhost/web/jeju/food_list_vue.do',{
					params:{
						page:1
					}
				}).then(function(response){
					console.log(response.data)
					_this.food_list=response.data
					_this.curpage=response.data[0].curpage
					_this.totalpage=response.data[0].totalpage
				})
			},
			methods:{
				foodDetail:function(no){
					this.isShow=true
					let _this=this
					axios.get('http://localhost/web/jeju/food_detail_vue.do',{
						params:{
							no:no
						}
					}).then(function(response){
						console.log(response.data)
						_this.food_detail=response.data
						_this.menus=_this.food_detail.menu.split('^')
					})
				},
				prev:function(){
					this.curpage=this.curpage>1?this.curpage-1:this.curpage
					this.send()
				},
				next:function(){
					this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage
					this.send()
				},
				send:function(){
					let _this=this
					axios.get('http://localhost/web/jeju/food_list_vue.do',{
						params:{
							page:_this.curpage
						}
					}).then(function(response){
						console.log(response.data)
						_this.food_list=response.data
						_this.curpage=response.data[0].curpage
						_this.totalpage=response.data[0].totalpage
					})	
				}
			}
		})
	</script>
</body>
</html>