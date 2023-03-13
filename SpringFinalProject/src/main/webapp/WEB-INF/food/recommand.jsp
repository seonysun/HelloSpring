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
<!-- jquery ui : dialog -->
<!-- <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css"> -->
<!-- <script src="https://code.jquery.com/jquery.js"></script> -->
<!-- <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script> -->
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 960px;
}
h1{
	text-align: center;
}
.details:hover{
	cursor: pointer;
}
</style>
</head>
<body>
<div class="wrapper row3 rows">
	<main class="container clear">
		<div class="row">
		  <div class="inline text-center">
			<button class="btn btn-lg btn-danger" v-on:click="change(1)">상황</button>
			<button class="btn btn-lg btn-success" v-on:click="change(2)">감성</button>
			<button class="btn btn-lg btn-info" v-on:click="change(3)">스타일</button>
			<button class="btn btn-lg btn-warning" v-on:click="change(4)">날씨/계절</button>
		  </div>
		</div>
		<div style="height: 20px"></div>
		<div class=row>
		  <div class="inline text-center">
			<span class="btn btn-lg btn-primary" v-for="r in recommand_list" v-on:click="recommandData(r)" style="margin-left:5px">{{r}}</span>
		  </div>
		</div>
		<div style="height: 20px"></div>
		
		<div class=row>
		  <template>
			<recommand_result v-bind:redata="recommand_data"></recommand_result>
		  </template>
		</div>
		
		<div class="dialog" title="맛집 상세보기" v-if="isShow">
			<div class=row>
				<table class="table">
				  <tr>
				  	<td v-for="img in vo.poster">
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
			  			<li v-for="m in vo.menu">{{m}}원</li>
			  		</ul>
			  	</td>
			  </tr>
			  <tr>
			  	<td colspan=2 class=text-right>
			  		<a href="javascript:history.back()" class="btn btn-xs btn-danger">목록</a>
			  	</td>
			  </tr>
			</table>
		   </div>
		   
		   <div class="one_third">
		   	<div id="map" style="width:300px;height:500px"></div>
		   </div>
		</div>
	</main>
</div>
<script>
	Vue.component("recommand_result",{
		props:['redata'],
		template:'<div class="text-center">'
			+'<div class=col-md-3 v-for="vo in redata">'
			+'<div class=thumbnail>'
			+'<img :src="vo.poster" style="width: 200px;height: 180px" class="details" v-on:click="recommandDetail(vo.fno)">'
			+'<div class=caption>'
			+'<p><h4 class="details" v-on:click="recommandDetail(vo.fno)">{{vo.name}}&nbsp;<span style="color: orange">{{vo.score}}</span></h4></p>'
			+'</div>'
			+'</div>'
			+'</div>'
			+'</div>',
		methods:{
			recommandDetail:function(fno){
				this.$parent.recommandDetail(fno, true)
// 				$('.dialog').dialog({
// 					autoOpen:false,
// 					modal:true,
// 					width:1200,
// 					height:800
// 				}).dialog("open")
			}
		}
	})
	new Vue({
		el:'.rows',
		data:{
			recommand_list:[],
			recommand_data:[],
			vo:{},
			isShow:false
		},
		methods:{
			change:function(no){
				let _this=this
				axios.get('http://localhost/web/food/recommand_change_vue.do',{
					params:{
						no:no
					}
				}).then(function(response){
					console.log(response.data)
					_this.recommand_list=response.data
				})
			},
			recommandData:function(r){
				let _this=this
				axios.get('http://localhost/web/food/recommand_result_vue.do',{
					params:{
						ss:r
					}
				}).then(function(response){
					console.log(response.data)
					_this.recommand_data=response.data
				})
			},
			recommandDetail:function(fno, show){
				let _this=this
				this.isShow=show
				axios.get('http://localhost/web/food/recommand_detail_vue.do',{
					params:{
						fno:fno
					}
				}).then(function(response){
					console.log(response.data)
					_this.vo=response.data
					
					if(window.kakao && window.kakao.maps){
						_this.initMap(_this.food_detail.name);
					}else{
						_this.addScript();
					}
				})
			},
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