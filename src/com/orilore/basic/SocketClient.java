package com.orilore.basic;

import java.io.OutputStream;
import java.net.Socket;

public class SocketClient {
	public static void main(String args[]) throws Exception {
	    // Ҫ���ӵķ����IP��ַ�Ͷ˿�
	    String host = "127.0.0.1"; 
	    int port = 55533;
	    // �����˽�������
	    Socket socket = new Socket(host, port);
	    // �������Ӻ��������
	    OutputStream outputStream = socket.getOutputStream();
	    String message="��ã�";
	    outputStream.write(message.getBytes("UTF-8"));
	    outputStream.close();
	    socket.close();
	}
}
