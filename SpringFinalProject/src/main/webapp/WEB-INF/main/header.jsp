<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="wrapper row1">
  <header id="header" class="clear"> 
    <div id="logo" class="fl_left">
      <h1><a href="../main/main.do">Food & Recipe</a></h1>
    </div>
    <div class="fl_right">
     <c:if test="${sessionScope.id==null }">
      <ul class="inline">
        <li>ID <input type=text name=id size=15 class=input-sm ref="id" value="${id }"></li>
        <li>PW <input type=password name=pwd size=15 class=input-sm v-model="pwd" ref="pwd"></li>
        <c:if test="${ck==true }">
	        <li>ID 저장 <input type="checkbox" ref="ck" checked></li>
        </c:if>
        <c:if test="${ck==false }">
	        <li>ID 저장 <input type="checkbox" ref="ck"></li>
        </c:if>
        <li><input type=button value=로그인 class="btn btn-sm btn-danger" v-on:click="login()"></li>
      </ul>
     </c:if>
     <c:if test="${sessionScope.id!=null }">
      <ul class="inline">
        <li>${sessionScope.name }님(${sessionScope.admin=='y'?"관리자":"일반사용자" }) 로그인 중입니다</li>
        <li><input type=button value=로그아웃 class="btn btn-sm btn-danger" v-on:click="logout()"></li>
      </ul>
     </c:if>
    </div>
  </header>
</div>
<div class="wrapper row2">
  <nav id="mainav" class="clear"> 
    <ul class="clear">
      <li class="active"><a href="../main/main.do">Home</a></li>
      <li><a class="drop" href="#">회원</a>
        <ul>
          <li><a href="../member/join.do">회원가입</a></li>
          <li><a href="#">아이디찾기</a></li>
          <li><a href="#">비밀번호찾기</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">맛집</a>
        <ul>
          <li><a href="../food/food_find.do">지역별 검색</a></li>
          <li><a href="../food/food_recommand.do">밋집 추천</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">레시피</a>
        <ul>
          <li><a href="../recipe/recipe_list.do">레시피</a></li>
          <li><a href="../recipe/chef_list.do">쉐프</a></li>
          <li><a href="#">가격비교</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">제주</a>
        <ul>
          <li><a href="../jeju/location.do">행사&관광</a></li>
          <li><a href="../jeju/food.do">제주맛집</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">스토어</a>
        <ul>
          <li><a href="#">전체</a></li>
          <li><a href="#">특가</a></li>
          <li><a href="#">베스트</a></li>
          <li><a href="#">신상</a></li>
        </ul>
      </li>
      <li><a class="drop" href="#">커뮤니티</a>
        <ul>
          <li><a href="../board/list.do">게시판</a></li>
          <li><a href="#">공지사항</a></li>
        </ul>
      </li>
      <c:if test="${sessionScope.id!=null }">
	      <li><a href="../chat/chat.do">실시간채팅</a></li>
	      <li><a href="#">마이페이지</a></li>
      </c:if>
    </ul>
  </nav>
</div>
<script>
	new Vue({
		el:'#header',
		data:{
			id:'',
			pwd:'',
			msg:'',
			ck:false
		},
		methods:{
			chatlogin:function(){
				if(this.id.trim()==""){
					this.$refs.id.focus()
					return
				}
				if(this.pwd.trim()==""){
					this.$refs.pwd.focus()
					return
				}
				
				let _this=this
				axios.get('http://localhost/web/member/chat_login_vue.do',{
					params:{
						id:this.id,
						pwd:this.pwd
					}
				}).then(function(response){
					let res=response.data.trim()
					if(res==='NOID'){
						alert("존재하지 않는 ID입니다!")
						_this.id='';
						_this.pwd='';
						_this.$refs.id.focus()
					}else if(res==='NOPWD'){
						alert("비밀번호를 다시 확인해주세요!")
						_this.pwd='';
						_this.$refs.pwd.focus()
					}else{
						location.href="../main/main.do"
					}
				})
			},
			login:function(){
				this.id=this.$refs.id.value
				this.ck=this.$refs.ck.checked
				if(this.id.trim()==""){
					this.$refs.id.focus()
					return
				}
				if(this.pwd.trim()==""){
					this.$refs.pwd.focus()
					return
				}
				
				let _this=this
				axios.get('http://localhost/web/member/login_vue.do',{
					params:{
						id:this.id,
						pwd:this.pwd,
						ck:this.ck
					}
				}).then(function(response){
					let res=response.data.trim()
					if(res==='NOID'){
						alert("존재하지 않는 ID입니다!")
						_this.id='';
						_this.pwd='';
						_this.$refs.id.focus()
					}else if(res==='NOPWD'){
						alert("비밀번호를 다시 확인해주세요!")
						_this.pwd='';
						_this.$refs.pwd.focus()
					}else{
						location.href="../main/main.do"
					}
				})
			},
			logout:function(){
				axios.get('http://localhost/web/member/logout_vue.do').then(function(response){
						location.href="../main/main.do"
				})
			}
		}
		
	})
</script>
</body>
</html>