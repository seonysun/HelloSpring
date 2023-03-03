<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- VueJS
	- 실시간 검색, 로그인 처리, 댓글창
	1. 생명주기 함수 -> 자동 호출
		- beforeCreate() : 인스턴스 초기화(생성) 전 -> 이벤트 등록
		- created() : 인스턴스 메모리 할당
		- beforeMount() : HTML 저장할 가상 메모리 확인, 서버에서 데이터 읽기(변수)
		- mounted()(*) : 가상돔에 업로드되어 브라우저에 출력된 상태 -> window.onload
		- beforeUpdate() : 수정 전 상태
		- updated() : 수정 완료
		- beforeDestroy() : 페이지 이동 전 상태
		- destroyed() : 종료 상태
		
		- new Vue({
			el: 제어하는 영역(CSS) 지정 -> #id, .class
			data:{
				멤버변수 정의
			}
		})
	2. 디렉티브 : 제어문
		- v-if, v-else, v-for, v-show, v-hide, v-bind, v-model
	3. 외부 데이터 읽어서 제어 : axios
	4. 컴포넌트 제작
	--- 이벤트 처리 : v-on:click
	5. 기타 속성
	6. 라우터
	7. 배포 : Javascript 따로 제작 -> 웹스톰(NodeJS 기반)
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 800px;
}
h1{
	text-align: center;
}
</style>
</head>
<body>
	<div class=container>
		<h1>Vue의 생명주기</h1>
		<div class=row>
			<input type=text size=30 class=input-sm v-model="message">
			<h3>{{message}}</h3>
		</div>
	</div>
	<script>
		new Vue({
			el:'.container',
			data:{
				message:''
			},
			//생명주기 함수
			beforeCreate:function(){
				console.log("beforeCreate Call : 이벤트 등록")
				//react : componentWillCreate()
			},
			created:function(){
				console.log("created Call : 메모리 할당")
				//componentDidCreate()
			},
			beforeMount:function(){
				console.log("beforeMount Call : 서버에서 데이터(변수) 읽기")
				//componentWillMount()
			},
			mounted:function(){
				console.log("mounted Call : HTML 가상돔에 저장 완료, 실제 돔과 비교 후 브라우저에 출력")
				//componentDidMount()
			},
			beforeUpdate:function(){
				console.log("beforeUpdate Call : 수정 전")
			},
			updated:function(){
				console.log("updated Call : 수정 완료")
			},
			beforeDestroy:function(){
				console.log("beforeDestroy Call : 메모리 해제 전")
			},
			destroyed:function(){
				console.log("destroyed Call : 메모리 해제")
			}
		})
	</script>
</body>
</html>