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
	width: 900px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
<div class=container>
	<div class=row>
		<template>
			<div>
				<b-button id="show-btn" @click="showModal">Open</b-button>
										<!-- 골뱅이로 줄 때는 메소드에 괄호 생략 -->
				<b-button id="toggle-btn" v-on:click="toggleModal()">Toggle</b-button>
				<b-modal ref="my-modal" hide-footer title="Modal Component">
					<div class=text-center>
						<h1>Hello Modal</h1>
					</div>
					<b-button class="m1" variant="outline-danger" block @click="hideModal">Close</b-button>
					<b-button class="m2" variant="outline-warning" block @click="toggleModal">Toggle</b-button>
				</b-modal>
			</div>
		</template>
	</div>
</div>
<script>
	new Vue({
		el:'.container',
		methods:{
			showModal:function(){
				this.$refs['my-modal'].show()
			},
			hideModal:function(){
				this.$refs['my-modal'].hide()
			},
			toggleModal:function(){
				this.$refs['my-modal'].toggle('#toggle-btn')
			}
		}
	})
</script>
</body>
</html>