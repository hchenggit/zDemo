package my.study.test.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client2 {
	public static void main(String[] args) {
		// 要连接的服务端IP地址和端口
	    String host = "10.22.2.27"; 
	    int port = 8888;
	    // 与服务端建立连接
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
