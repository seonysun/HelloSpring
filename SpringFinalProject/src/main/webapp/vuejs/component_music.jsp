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
	<h1>지니뮤직 Top100</h1>
	<div class=row>
		<music :musicdata="music_list"></music>
		<b-modal ref="my-modal" :title="music_detail.title" v-if="isShow" id="modal-lg" size="lg">
			<div class=text-center>
				<h3>{{music_detail.title}}</h3>
				<iframe :src="'http://youtube.com/embed/'+music_detail.key" style="width: 600px;height: 500px"></iframe>
			</div>
		</b-modal>
	</div>
</div>
<script>
	let eventBus=new Vue()
	Vue.component('music',{
		props:['musicdata'],
		template:'<table class=table>'
			+'<tr>'
			+'<th class=text-center>순위</th>'
			+'<th class=text-center></th>'
			+'<th class=text-center>곡명</th>'
			+'<th class=text-center>가수명</th>'
			+'<th class=text-center>앨범명</th>'
			+'<th class=text-center></th>'
			+'</tr>'
			+'<tbody>'
			+'<tr v-for="m in musicdata">'
			+'<td class=text-center>{{m.no}}</td>'
			+'<td class=text-center><img :src="m.poster" style="width:30px;height:30px"></td>'
			+'<td>{{m.title}}</td>'
			+'<td class=text-center>{{m.singer}}</td>'
			+'<td class=text-center>{{m.album}}</td>'
			+'<td class=text-center><b-button id="showBtn" variant="outline-danger" @click="showMovie(m.no, true)">뮤비</b-button></td>'
			+'</tr>'
			+'</tbody>'
			+'</table>',
		methods:{
			showMovie:function(value, bool){
				eventBus.$emit('showMovieEvent', value, bool)
			}
		}
	})
	new Vue({
		el:'.container',
		data:{
			music_list:[],
			music_detail:{},
			isShow:false
		},
		mounted:function(){
			let _this=this
			axios.get('http://localhost/web/music/list.do').then(function(response){
				console.log(response.data)
				_this.music_list=response.data
			})
		},
		//컴포넌트에서 사용되는 속성들이 변경되거나 재랜더링 된 후
		updated:function(){
			let _this=this
			eventBus.$on('showMovieEvent', function(value, bool){
				 _this.isShow=bool
				axios.get('http://localhost/web/music/detail.do',{
					params:{
						no:value
					}
				}).then(function(response){
					console.log(response.data)
					_this.music_detail=response.data
				})
			})
			this.showMovie()
		},
		methods:{
			showMovie:function(){
				this.$refs['my-modal'].show()
			}
		}
	})
</script>
</body>
</html>