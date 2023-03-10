<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 1300px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
<div class=container>
	<h3>믿고보는 맛집 리스트</h3>
	<div class=row>
		<category1 :cate1="cate_list1"></category1>
	</div>
	<div style="height: 20px"></div>
	<h3>지역별 맛집 리스트</h3>
	<div class=row>
		<category2 :cate2="cate_list2"></category2>
	</div>
	<div style="height: 20px"></div>
	<h3>메뉴별 맛집 리스트</h3>
	<div class=row>
		<category3 :cate3="cate_list3"></category3>
	</div>
	
	<b-modal ref="my-modal" title="카테고리별 맛집 목록" v-if="isShow">
		<div class=text-center>
            <h4 class="text-center">{{category_info.title}}</h4>
            <h5 class="text-center">{{category_info.subject}}</h5>
            <table class="table">
	          <tbody>
	            <tr>
	              <td>
	                <table class="table" v-for="vo in food_list">
	                  <tr>
	                    <td width=30% class="text-center" rowspan="4">
	                      <img :src="vo.poster" style="width:150px">
	                    </td>
	                    <td width=70% class="text-left">
	                      <h3>{{vo.name}}&nbsp;<span style="color:orange">{{vo.score}}</span></h3>
	                    </td>
	                  </tr>
	                  <tr>
	                    <td width=70% class="text-left">{{vo.address}}</td>
	                  </tr>
	                  <tr>
	                    <td width=70% class="text-left">{{vo.tel}}</td>
	                  </tr>
	                  <tr>
	                    <td width=70% class="text-left">{{vo.type}}</td>
	                  </tr>
	                </table>
	              </td>
	            </tr>
	          </tbody>
	        </table>
		</div>
	</b-modal>
	
	<div id="dialog" title="카테고리별 맛집 목록" v-if="isShow" id="modal-lg" size="lg">
        <div class="jumbotrom">
            <h4 class="text-center">{{category_info.title}}</h4>
            <h5 class="text-center">{{category_info.subject}}</h5>
        </div>
        <table class="table">
          <tbody>
            <tr>
              <td>
                <table class="table" v-for="vo in food_list">
                  <tr>
                    <td width=30% class="text-center" rowspan="4">
                      <img :src="vo.poster" style="width:150px">
                    </td>
                    <td width=70% class="text-left">
                      <h3>{{vo.name}}&nbsp;<span style="color:orange">{{vo.score}}</span></h3>
                    </td>
                  </tr>
                  <tr>
                    <td width=70% class="text-left">{{vo.address}}</td>
                  </tr>
                  <tr>
                    <td width=70% class="text-left">{{vo.tel}}</td>
                  </tr>
                  <tr>
                    <td width=70% class="text-left">{{vo.type}}</td>
                  </tr>
                </table>
              </td>
            </tr>
          </tbody>
        </table>
    </div>
</div>
<script>
	let eventBus=new Vue()
	Vue.component('category1',{
		props:['cate1'],
		template:'<div><div class=col-md-3 v-for="vo in cate1">'
			+'<div class=thumbnail>'
			+'<img :src="vo.poster" alt="Lights" style="width:100%" class="images" v-on:click="showFoodList(vo.cno, true)">'
	  		+'<div class=caption>'
	  		+'<p>{{vo.title}}</p>'
			+'</div>'
			+'</div>'
			+'</div></div>',
		methods:{
			showFoodList:function(cno, bool){
				eventBus.$emit("showFoodListEvent", cno, bool)
			}
		}
	})
	Vue.component('category2',{
		props:['cate2'],
		template:'<div><div class=col-md-4 v-for="vo in cate2">'
			+'<div class=thumbnail>'
			+'<img :src="vo.poster" alt="Lights" style="width:100%" class="images" v-on:click="showFoodList(vo.cno, true)">'
	  		+'<div class=caption>'
	  		+'<p>{{vo.title}}</p>'
			+'</div>'
			+'</div>'
			+'</div></div>',
		methods:{
			showFoodList:function(cno, bool){
				eventBus.$emit("showFoodListEvent", cno, bool)
			}
		}
	})
	Vue.component('category3',{
		props:['cate3'],
		template:'<div><div class=col-md-3 v-for="vo in cate3">'
			+'<div class=thumbnail>'
			+'<img :src="vo.poster" alt="Lights" style="width:100%" class="images" v-on:click="showFoodList(vo.cno, true)">'
	  		+'<div class=caption>'
	  		+'<p>{{vo.title}}</p>'
			+'</div>'
			+'</div>'
			+'</div></div>',
		methods:{
			showFoodList:function(cno, bool){
				eventBus.$emit("showFoodListEvent", cno, bool)
			}
		}
	})
	new Vue({
		el:'.container',
		data:{
			category:{},
			cate_list1:[],
			cate_list2:[],
			cate_list3:[],
			category_info:{},
			food_list:[],
    		isShow:false
		},
		mounted:function(){
			let _this=this
			axios.get('http://localhost:8080/web/food/component_category_vue.do').then(function(response){
				console.log(response.data)
				_this.category=response.data
				_this.cate_list1=_this.category.cate1
				_this.cate_list2=_this.category.cate2
				_this.cate_list3=_this.category.cate3
			})
		},
		updated:function(){
			let _this=this
			eventBus.$on('showFoodListEvent', function(cno, bool){
				_this.isShow=bool
				axios.get('http://localhost:8080/web/food/category_info_vue.do',{
					params:{
						cno:cno
					}
				}).then(function(response){
					console.log(response.data)
					_this.category_info=response.data
				})
				axios.get('http://localhost:8080/web/food/food_list_vue.do',{
					params:{
						cno:cno
					}
				}).then(function(response){
					console.log(response.data)
					_this.food_list=response.data
				})
			})
			this.$refs['my-modal'].show()
		}
	})
</script>
</body>
</html>