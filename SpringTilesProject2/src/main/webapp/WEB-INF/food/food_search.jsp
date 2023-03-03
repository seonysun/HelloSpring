<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script src='https://cdnjs.cloudflare.com/ajax/libs/vue/1.0.11/vue.js'></script> -->
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <div class="container" id="app">
    <div class="row">
    	<input type="text" size="20" class="input-sm" v-model="addr">
    												  <!-- vue_data 내 변수 값 설정 : 양방향 통신 -->
    	<input type="button" class="btn btn-sm btn-success" value="검색" @click="search()">
    </div>
    <div style="height: 20px"></div>
    <div class="row">
      <div class="col-md-3" v-for="vo in food_list">
      						<!-- foreach -->
      						<!-- vue food_list에서 가져온 JSON 데이터 이용 -->
       <div class="thumbnail">
         <a :href="'../food/location_detail_before.do?fno='+vo.fno">
           <img :src="vo.poster" style="width: 100%">
           		<!-- 속성 값으로 변수 가져올 때 속성 앞에 : 찍기 -->
           <div class="caption">
             <p>{{vo.name}}</p>
             	<!-- 태그 안에 변수 가져올 때 중괄호 2개 -->
           </div>
         </a>
       </div>
     </div>
    </div>
    <div class="row">
      <div class="text-center">
        <input type="button" class="btn btn-sm btn-warning" value="이전" @click="prev()">
        {{curpage}} page / {{totalpage}} pages
        <input type="button" class="btn btn-sm btn-info" value="다음" @click="next()">
      </div>
    </div>
  </div>
  <%--
     Vue 동작 생명주기
     1. created => new Vue()
     2. mounted => 브라우저 띄웠을 때, onload() => jquery의 경우 $(function())
     3. updated => 수정 시
     4. destroyed => 종료 시
   --%>
  <script>
    new Vue({
       el:'#app', //제어하고자 하는 범위 지정
       data:{ //양방향 통신 -> 입력된 데이터 받아오는 것, 입력한 데이터 보내는 것 모두 가능
          curpage:1,
          totalpage:0,
          food_list:[], //list = Array(JSONArray)
          addr:''
       },
       mounted:function(){
    	  let _this=this
          axios.get("http://localhost/web/food/food_search_vue.do").then(function(response){
															        	//response=> : vue의 this와 axios의 this 혼용 가능
															        	//function 안에 들어가면 this가 바뀌므로 vue의 this 재정의 필요(let)
             console.log(response.data)
             _this.food_list=response.data //axios(url)에서 실행시킨 .do의 return값으로 얻은 데이터를 food_list에 저장
             _this.curpage=response.data[0].curpage
             _this.totalpage=response.data[0].totalpage
          })
       },
       //사용할 함수 정의
       methods:{ 
    	  prev:function(){
    		  this.curpage=this.curpage>1?this.curpage-1:this.curpage
    		  this.send()
    	  },
    	  next:function(){
    		  this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage
    		  this.send()
    	  },
    	  search:function(){
    		  this.curpage=1
    		  this.send()
    	  },
    	  send:function(){
	    	  let _this=this
	          axios.get("http://localhost/web/food/food_search_vue.do",{
	        	  params:{
	        		  page:_this.curpage,
	        		  addr:_this.addr
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