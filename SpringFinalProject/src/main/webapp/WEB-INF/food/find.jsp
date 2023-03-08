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
<style type="text/css">
.row{
	width: 100%
}
</style>
<link rel="stylesheet" href="../layout/styles/food.css">
</head>
<body>
<div class="wrapper row3 rows">
  <main class="container clear">
  	<div class=row>
  		<table class=table>
  		  <tr>
  		  	<td class="text-right inline">
  		  		<input type=text size=25 class=input-sm ref=ss v-model="ss">
  		  		<input type=button value=검색 class="btn btn-sm btn-primary" v-on:click="find()">
  		  	</td>
  		  </tr>
  		</table>
  	</div>
  	<div style="height: 20px"></div>
  	<div class=row>
  		<div id=a>
  			<img src="../images/map/1111.png" id=seoul_1>
  			<img :src="'../images/map/gu_'+i+'_off.png'" v-for="i in range(1, 25)" :id="'gu'+i">
  		</div>
  	</div>
  	<div class=row>
  		<div class=col-md-3 v-for="vo in find_list">
  			<div class=thumbnail>
  				<a :href="'../food/food_location_detail.do?fno='+vo.fno">
  					<img :src="vo.poster">
	  				<div class=caption>
	  					<p style="font-size: 8px">{{vo.name}}&nbsp;<span style="color: orange">{{vo.score}}</span></p>
	  				</div>
  				</a>
  			</div>
  		</div>
  	</div>
  	<div class=row>
  		<div style="display: inline-block;text-align: center">
  			<ul class="pagination">
			  <li v-if="startpage>1"><span v-on:click="prev()">&lt;</span></li>
			  <li v-for="i in range(startpage, endpage)"><span v-on:click="pageChange(i)">{{i}}</span></li>
			  <li v-if="totalpage>endpage"><span v-on:click="next()">&gt;</span></li>
			</ul>
  		</div>
  	</div>
  </main>
</div>
<script>
	new Vue({
		el:'.rows',
		data:{
			find_list:[],
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0,
			ss:'역삼'
		},
		mounted:function(){
			this.getData()
		},
		methods:{
			getData:function(){
				let _this=this
				axios.get('http://localhost/web/food/food_find_vue.do',{
					params:{
						page:this.curpage,
						address:this.ss
					}
				}).then(function(response){
					console.log(response.data)
					_this.find_list=response.data
					_this.curpage=response.data[0].curpage
					_this.totalpage=response.data[0].totalpage
					_this.startpage=response.data[0].startpage
					_this.endpage=response.data[0].endpage
				})
			},
			range:function(min, max){
				let array=[],
				j=0
				for(let i=min;i<=max;i++){
					array[j]=i
					j++
				}
				return array
			},
			pageChange:function(page){
				this.curpage=page
				this.getData()
			},
			find:function(){
				this.curpage=1
				this.getData()
			},
			prev:function(){
				this.curpage=this.startpage-1
				this.getData()
			},
			next:function(){
				this.curpage=this.endpage+1
				this.getData()
			}
		}
	})
</script>
</body>
</html>