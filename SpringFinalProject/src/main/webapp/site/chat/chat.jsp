<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	width: 800px;
	margin: 0px auto;
}
h1{
	text-align: center;
}
#chatArea{
	height: 450px;
	overflow-y: auto;
	border: 1px solid black;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.3.0/sockjs.min.js"></script>
<script type="text/javascript">
let websocket;
function connection(){
	//웹소켓 생성
	websocket=new WebSocket("ws://localhost/web/site/chat/chat-ws")
	websocket.onopen=onOpen
	websocket.onclose=onClose
	websocket.onmessage=onMessage
	name=$('#startBtn').attr("data-name")
}
function onOpen(event){
	alert("채팅 서버에 접속되었습니다")
}
function onClose(event){
	alert("채팅 서버와의 연결이 종료되었습니다")
}
function onMessage(event){
	let data=event.data //입력된 데이터 가져오기
	if(data.substring(0,4)=='msg:'){
							//다른 명령어와 채팅 메시지 입력 구분 가능하도록 설정
		appendMessage(data.substring(4))
	}
}
function disConnection(){
	websocket.close()
}
function send(){
	//유효성 검사
	let msg=$('#sendMsg').val()
	if(msg.trim()==""){
		$('#sendMsg').focus()
		return
	}
	//서버로 전송
	websocket.send("msg:["+name+"]"+msg)
	$('#sendMsg').val("")
	$('#sendMsg').focus()
}
function appendMessage(msg){
	//입력된 문자열 수신
	$('#recvMsg').append(msg+"<br>")
	//스크롤 위치 변경
	let ch=$('#chatArea').height()
	let m=$('#recvMsg').height()-ch
	$('#chatArea').scrollTop(m)
}
$(function(){
	$('#startBtn').click(function(){
		connection()
	})
	$('#endBtn').click(function(){
		disConnection()
	})
	$('#sendBtn').click(function(){
		send()
	})
	$('#sendMsg').keydown(function(key){
		if(key.keyCode==13){
					//엔터 : keyCode==13
			send()
		}
	})
})
</script>
</head>
<body>
	<div class=container>
		<h1>WebSocket</h1>
		<div class=row>
			<table class=table>
			  <tr>
			  	<td class=text-right>
			  		<input type=button id=startBtn class="btn btn-sm btn-danger" value=입장 data-name="${sessionScope.name }">
			  		<input type=button id=endBtn class="btn btn-sm btn-success" value=퇴장>
			  		<a href="../main/main.do" class="btn btn-sm btn-warning">메인</a>
			  	</td>
			  </tr>
			  <tr>
			  	<td>
			  		<div id=chatArea>
			  			<div id=recvMsg></div>
			  		</div>
			  	</td>
			  </tr>
			  <tr>
			  	<td>
			  		<input type=text id=sendMsg size=80 class=input-sm>
			  		<input type=button id=sendBtn class="btn btn-sm btn-primary" value=전송>
			  	</td>
			  </tr>
			</table>
		</div>
	</div>
</body>
</html>