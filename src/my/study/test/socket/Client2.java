package my.study.test.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
	public static void main(String[] args) {
		// Ҫ���ӵķ����IP��ַ�Ͷ˿�
	    String host = "10.22.2.27"; 
	    int port = 8888;
	    // �����˽�������
	    try {
			Socket socket = new Socket(host, port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
