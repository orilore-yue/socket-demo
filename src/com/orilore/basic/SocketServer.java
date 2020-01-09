package com.orilore.basic;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
	public static void main(String[] args) throws Exception {
		// ����ָ���Ķ˿�
		int port = 55533;
		ServerSocket server = new ServerSocket(port);
		// server��һֱ�ȴ����ӵĵ���
		System.out.println("server��һֱ�ȴ����ӵĵ���");
		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						// ���������Ӻ󣬴�socket�л�ȡ�����������������������ж�ȡ
						Socket socket = server.accept();
						InputStream inputStream = socket.getInputStream();
						byte[] bytes = new byte[1024];
						int len;
						StringBuilder sb = new StringBuilder();
						while ((len = inputStream.read(bytes)) != -1) {
							// ע��ָ�������ʽ�����ͷ��ͽ��շ�һ��Ҫͳһ������ʹ��UTF-8
							sb.append(new String(bytes, 0, len, "UTF-8"));
						}
						System.out.println("���Կͻ��˵���Ϣ�� " + sb);
						inputStream.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		th.start();
	}
}
