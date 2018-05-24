package com.chunmi.annualconvention.controller;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

 
@ServerEndpoint(value="/websocket")
public class MyWebSocket {
	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static CopyOnWriteArraySet<MyWebSocket> websocketPools = new CopyOnWriteArraySet<MyWebSocket>();

	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) {
		this.session = session;
		websocketPools.add(this);
	}

	@OnClose
	public void onClose() {
		websocketPools.remove(this);
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		websocketPools.forEach(item -> {
			try {
				item.sendMsg(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	private void sendMsg(String message) throws IOException {
		if(message.length()>50) {
			message=message.substring(0, 50);
		}
		this.session.getAsyncRemote().sendText(message);
	}

	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
	}

}
