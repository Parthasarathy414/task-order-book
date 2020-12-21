package com.task.service;

import java.io.IOException;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint
public class MyClientEndpoint {
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("Connected to endpoint: " + session.getBasicRemote());
		try {
			String name = "Test";
			System.out.println("Sending message to endpoint: " + name);
			session.getBasicRemote().sendText(name);
		} catch (IOException ex) {
			ex.getStackTrace();        }
	}

	@OnMessage
	public void processMessage(String message) {
		System.out.println("Received message in client: " + message);
		SocketClient.messageLatch.countDown();
	}

	@OnError
	public void processError(Throwable t) {
		t.printStackTrace();
	}
}
