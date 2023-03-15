<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div class="wrapper row3 rows">
  <main class="container clear">
   <div class=row>
	<table class="table">
	  <tr>
	  	<td v-for="img in vo.poster.split('^')">
	  		<img :src="img" style="width: 100%">
	  	</td>
	  </tr>
	</table>
   </div>
   <div style="height: 20px"></div>
   
   <div class="two_third first">
	<table class="table">
	  <tr>
	 	<td colspan="2">
	  		<h3><span id=name>{{vo.name }}</span>&nbsp;<span style="color: orange">{{vo.score }}</span></h3>
	  	</td>
	  </tr>
	  <tr>
	  	<th width=20%>주소</th>
	  	<td width=80%>
	  		{{vo.addr1 }}<br>
		  	<sub>지번: {{vo.addr2 }}</sub>
	  	</td>
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
		  		<li v-for="(m, index) in vo.menu.split('원')" v-if="count>index+1">{{m }}원</li>
	  		</ul>
	  	</td>
	  </tr>
	  <tr>
	  	<td colspan=2 class=text-right>
	  		<a href="javascript:history.back()" class="btn btn-xs btn-danger">목록</a>
	  	</td>
	  </tr>
	</table>
	<div style="height:20px"></div>
	<div class="row" id="food_reply">
		<table class=table>
		  <tr>
		  	<td>
		  		<table class=table v-for="r in reply_list">
		  		  <tr>
		  		  	<td class=text-left>
		  		  		♥{{r.name}}({{r.dbday}})
		  		  	</td>
		  		  	<td class=text-right>
		  		  		<span v-if="r.id===sessionId">
		  		  			<input type=button class="btn btn-xs btn-warning" value="수정">
		  		  			<input type=button class="btn btn-xs btn-info" value="삭제">
		  		  		</span>
		  		  	</td>
		  		  </tr>
		  		  <tr>
		  		  	<td colspan=2 valign=top class=text-left><pre style="white-space: pre-wrap;background-color: white;border: none">{{r.msg}}</pre></td>
		  		  </tr>
		  		</table>
		  	</td>
		  </tr>
		</table>
		<c:if test="${sessionScope.id!=null }">
			<table class=table>
			  <tr>
			  	<td>
			  		<textarea rows=5 cols=80 style="float: left"></textarea>
			  		<input type=button value=댓글쓰기 class="btn btn-sm btn-primary" v-on:click="write()">
			  	</td>
			  </tr>
			</table>
		</c:if>
	</div>
   </div>
   
   <div class="one_third">
   	<div id="map" style="width:300px;height:500px"></div>
   </div>
  </main>
</div>
<script>
	new Vue({
		el:'#food_reply',
		data:{
			fno:${fno},
			reply_list:[],
			sessionId:'',
			type:10,
			msg:''
		},
		mounted:function(){
			let _this=this
			axios.get('http://localhost/web/reply/reply_list.do',{
				params:{
					fno:_this.fno,
					type:_this.type
				}
			}).then(function(response){
				_this.reply_list=response.data
			})
		},
		methods:{
			
		}
	})
	new Vue({
		el:'.rows',
		data:{
			fno:${fno},
			vo:{},
			count:0
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
				if(_this.vo.menu!=='no'){
					let menu=_this.vo.menu.split('원')
					_this.count=menu.length
				}
				//맵 추가
				if(window.kakao && window.kakao.maps){
					_this.initMap()
				}else{
					_this.addScript()
				}
			})
		},
		methods:{
			addScript:function(){
				const script=document.createElement("script")
				script.onload=()=>kakao.maps.load(this.initMap)
				script.src='http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=e7b9d67490320db6911ad3cb1c1e9e18&libraries=services'
				document.head.appendChild(script)
			},
			initMap:function(){
				var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
			    mapOption = {
			        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
			        level: 3 // 지도의 확대 레벨
			    };  

				// 지도를 생성합니다    
				var map = new kakao.maps.Map(mapContainer, mapOption); 
	
				// 주소-좌표 변환 객체를 생성합니다
				var geocoder = new kakao.maps.services.Geocoder();
	
				// 주소로 좌표를 검색합니다
				geocoder.addressSearch(this.vo.addr1, function(result, status) {
	
				    // 정상적으로 검색이 완료됐으면 
				     if (status === kakao.maps.services.Status.OK) {
	
				        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
				        // 결과값으로 받은 위치를 마커로 표시합니다
				        var marker = new kakao.maps.Marker({
				            map: map,
				            position: coords
				        });
	
				        // 인포윈도우로 장소에 대한 설명을 표시합니다
				        var infowindow = new kakao.maps.InfoWindow({
				            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$('#name').text()+'</div>'
				        });
				        infowindow.open(map, marker);
	
				        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
				        map.setCenter(coords);
				    } 
				});    
			}
		}
	})
</script>
</body>
</html>