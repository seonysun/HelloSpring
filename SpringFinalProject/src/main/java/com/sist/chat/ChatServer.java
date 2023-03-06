package com.sist.chat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ServerEndpoint("/site/chat/chat-ws")
				//tiles 형식과 다르게 설정 -> 우선순위 다음(order=1)인 viewResolver 불러옴
public class ChatServer {
	private static List<Session> users=new ArrayList<Session>();
						//브라우저마다 1개씩 생성 -> 중복 없음
	//클라이언트 접속 시
	@OnOpen 
	public void onOpen(Session session) {
//		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession se=request.getSession();
		users.add(session);
		System.out.println("클라이언트 접속.."+session.getId());
	}
	//클라이언트 접속 해제 시
	@OnClose
	public void onClose(Session session) {
//		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession se=request.getSession();
		users.remove(session);
		System.out.println("클라이언트 퇴장.."+session.getId());
	}
	//클라이언트가 메시지 전송 시
	@OnMessage
	public void onMessage(String message, Session session) throws Exception{
//		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//		HttpSession se=request.getSession();
//		String name=(String)se.getAttribute("name");
		Iterator<Session> iterator=users.iterator();
		System.out.println(users.size()+"명 접속 중");
		System.out.println("수신 메시지: "+message);
		while(iterator.hasNext()) {
			iterator.next().getBasicRemote().sendText(message);
		}
	}
}
