<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery.js"></script>
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
		<div class=row>
		  <div class=text-center>
			<button class="btn btn-sm btn-danger" v-on:click="movieListData(1, '일일 박스오피스')">일일 박스오피스</button>
			<button class="btn btn-sm btn-success" v-on:click="movieListData(2, '실시간 예매율')">실시간 예매율</button>
			<button class="btn btn-sm btn-info" v-on:click="movieListData(3, '좌석점유율 순위')">좌석점유율 순위</button>
			<button class="btn btn-sm btn-primary" v-on:click="movieListData(4, '온라인상영관 일일')">온라인상영관 일일</button>
		  </div>
		</div>
		<div style="height: 20px"></div>
		<div class=row>
			<h3 class=text-center>{{title}}</h3>
		</div>
		<div class=row>
			<div class=col-sm-8>
				<table class=table>
					<tr>
					  <td>
					  	<input type="text" size=20 class=input-sm id=keyword>
					  </td>
					</tr>
				</table>
				<table class="table table-hover" id=user-table>
					<thead>
					  <tr>
					  	<th class=text-center>순위</th>
					  	<th class=text-center></th>
					  	<th class=text-center>영화명</th>
					  	<th class=text-center>감독</th>
					  	<th class=text-center>장르</th>
					  </tr>
					</thead>
					<tbody><!-- react에서는 tbody 필수(화면 출력 불가) -->
					  <tr v-for="(vo, index) in movie_list" v-on:mouseover="movieDetailData(index)">
					  	<td class=text-center>{{vo.rank}}</td>
						<td class=text-center>
							<img :src="'https://www.kobis.or.kr/'+vo.thumbUrl" width=50 height=50>
						</td>
						<td>{{vo.movieNm}}</td>
						<td>{{vo.director}}</td>
						<td>{{vo.genre}}</td>
					  </tr>
					</tbody>
				</table>
			</div>
			<div class=col-sm-4>
			  <div v-show="isShow">
			  <!-- v-if도 동일한 결과 -> isShow만 주면 default true, ===로 true/false 조건 지정해도 됨 -->
				<table class="table">
					<tr>
					  <td width=30% class=text-center rowspan=6>
					  	<img :src="'https://www.kobis.or.kr/'+movie_detail.thumbUrl" style="width:100%">
					  </td>
					  <td colspan=2><b>{{movie_detail.movieNm}}</b></td>
					</tr>
					<tr>
					  <td width=20% class=text-right>감독</td>
					  <td width=50%>{{movie_detail.director}}</td>
					</tr>
					<tr>
					  <td width=20% class=text-right>장르</td>
					  <td width=50%>{{movie_detail.genre}}</td>
					</tr>
					<tr>
					  <td width=20% class=text-right>개봉일</td>
					  <td width=50%>{{movie_detail.startYearDate}}</td>
					</tr>
					<tr>
					  <td width=20% class=text-right>관람등급</td>
					  <td width=50%>{{movie_detail.watchGradeNm}}</td>
					</tr>
					<tr>
					  <td width=20% class=text-right>순위</td>
					  <td width=50%>{{movie_detail.rank}}</td>
					</tr>
				</table>
				<table class="table">
					<tr>
					  <td height=200 class=text-left valign=top>{{movie_detail.synop}}</td>
					</tr>
				</table>
			  </div>
			</div>
		</div>
	</div>
	<!-- 
		<script type="text/javascript"> ES 5
		<script type="text/babel"> ES 6
		axios.get : getMapping, axios.post : postMapping
	 -->
	<script>
		new Vue({
			el:'.container-fluid',
			data:{
				movie_list:[],
				movie_detail:{},
				title:'일일 박스오피스',
				isShow:false
			},
			mounted:function(){
				let _this=this
				axios.get("http://localhost/web/movie/movie_list_vue.do",{
					params:{
						no:1
					}
				}).then(function(response){
					console.log(response.data)
					_this.movie_list=response.data
				})
				
				$('#keyword').keyup(function(){
					let k=$(this).val()
					$('#user-table > tbody > tr').hide()
					let temp=$('#user-table > tbody > tr > td:nth-child(5n+3):contains("'+k+'")')
					$(temp).parent().show()
				})
			},
			methods:{
				movieListData:function(no, title){
					this.title=title
					let _this=this
					axios.get("http://localhost/web/movie/movie_list_vue.do",{
						params:{
							no:no
						}
					}).then(function(response){
						console.log(response.data)
						_this.movie_list=response.data
					})
				},
				movieDetailData:function(index){
					console.log("index="+index)
					this.isShow=true
					this.movie_detail=this.movie_list[index]
				}
			}
		})
	</script>
</body>
</html>