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
				<b-tabs content-class="mt-3">
					<b-tab title="첫번째" active><p>First Tab</p></b-tab>
					<b-tab title="두번째" active><p>Second Tab</p></b-tab>
					<b-tab title="세번째" active><p>Third Tab</p></b-tab>
				</b-tabs>
			</div>
		</template>
	</div>
</div>
<script>
	new Vue({
		el:'.container'
	})
</script>
</body>
</html>