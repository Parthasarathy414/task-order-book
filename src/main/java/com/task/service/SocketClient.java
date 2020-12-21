package com.task.service;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;

import org.springframework.web.socket.WebSocketHttpHeaders;

public class SocketClient {

	final static CountDownLatch messageLatch = new CountDownLatch(1);
	private static String URL = "wss://api-public.prelive.cex.tribe.sh/api/v1/ws";


	public void client() {

		String isoDatePattern = "E, dd MMM yyyy HH:mm:ss ";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(isoDatePattern);

		String dateString = simpleDateFormat.format(new Date());

		System.out.println("Date ::::: "+dateString);

		try {
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			WebSocketHttpHeaders headers = new WebSocketHttpHeaders();

			headers.add("Date", dateString);
			headers.add("ApiKey", "tNr6nfLCDOU7SKhn4S9fZ1koBYqCrUjm");
			headers.add("Authorization", "\"hmac username=\\\"tNr6nfLCDOU7SKhn4S9fZ1koBYqCrUjm\\\", algorithm=\\\"hmac-sha1\\\", headers=\\\"date\\\", signature=\\\"`/bin/echo -n \"date: "+dateString+"\" | openssl sha1 -binary -hmac \"7s7yCQw87TdUlKnTRP6SDlXfTGBpGqDP\" | base64 `\\\"\"");

			container.connectToServer(MyClientEndpoint.class, URI.create(URL));
			messageLatch.await(100, TimeUnit.SECONDS);
		} catch (Exception ex) {
			ex.getStackTrace();	}
	}
}