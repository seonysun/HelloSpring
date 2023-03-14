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
    <div class="content"> 
      <div id="gallery">
        <figure>
          <header class="heading">총 <span style="color: green;font-size: 35px">{{count}}</span>개의 맛있는 레시피가 있습니다.</header>
          <ul class="nospace clear">
            <li class="one_quarter first" v-for="(vo, index) in recipe_list" v-if="index%4==0">
            	<a :href="'../recipe/recipe_detail.do?no='+vo.no"><img :src="vo.poster" :title="vo.title+'('+vo.chef+')'"></a>
            </li>
            <li class="one_quarter" v-else>
            	<a :href="'../recipe/recipe_detail.do?no='+vo.no"><img :src="vo.poster" :title="vo.title+'('+vo.chef+')'"></a>
            </li>
          </ul>
        </figure>
      </div>
      <nav class="pagination">
        <ul>
          <li v-if="startpage>1"><a v-on:click="prev()">&laquo; Previous</a></li>
          <li class="current" v-for="i in range(startpage, endpage)" v-if="i===curpage"><a>{{i}}</a></li>
          <li v-else><a v-on:click="page(i)">{{i}}</a></li>
          <li v-if="totalpage>endpage"><a v-on:click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
    </div>
    <div class="clear"></div>
  </main>
</div>
<script>
	new Vue({
		el:'.rows',
		data:{
			recipe_list:[],
			curpage:1,
			totalpage:0,
			startpage:0,
			endpage:0,
			count:0
		},
		mounted:function(){
			this.getData()
		},
		methods:{
			getData:function(){
				let _this=this
				axios.get('http://localhost/web/recipe/recipe_list_vue.do',{
					params:{
						page:this.curpage
					}
				}).then(function(response){
					console.log(response.data)
					_this.recipe_list=response.data
					_this.curpage=response.data[0].curpage
					_this.totalpage=response.data[0].totalpage
					_this.startpage=response.data[0].startpage
					_this.endpage=response.data[0].endpage
					_this.count=response.data[0].count
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
			page:function(page){
				this.curpage=page,
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