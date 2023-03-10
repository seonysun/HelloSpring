<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Vue, React -> 속도 증가(가상메모리: 가상 돔)
	- HTML : DOM 트리 형태로 저장 -> 효율적으로 관리
		- 분할 : template => component
	1. Vue
		(1) 디렉티브
			v-for / v-if / v-if ~ v-else / v-model
		(2) Component : 객체 간 데이터 전달
			- 형식
				- <template>
				- Vue.component('태그명', template:'HTML'>
			- props : 부모 컴포넌트가 자식 컴포넌트에 전달하는 값
				- readonly
				- 개별 데이터, 객체 모두 가능
			- emit : 자식 컴포넌트에서 부모 컴포넌트로 데이터 전송
			- on : 자식 컴포넌트에서 보낸 데이터 수신 
			- events
		(3) 외부 데이터 읽기 : axios
		(4) 화면 이동 : 라우터(Router)
		(5) vue3, vuex
 -->
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
</head>
<body>
<div id="app">
	<my-component1></my-component1>
	<my-component2></my-component2>
	<my-component3 v-bind:pdata="senddata"></my-component3>
</div>
<template id="app2">
	<h2>Hello Vue-Component2</h2>
</template>
<script>
	Vue.component('my-component3',{
		props:['pdata'],
		template:'<h1>Hello Vue-Component3 : {{pdata}}</h1>'
	})
	Vue.component('my-component2',{
		template:"#app2"
	})
	Vue.component('my-component1',{
		template:'<h1>Hello Vue-Component1</h1>'
	})
	new Vue({
		el:'#app',
		data:{
			senddata:'my-component3 데이터 전송',
		}
	})
</script>
</body>
</html>