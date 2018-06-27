package my.study.test.ice;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket = null;
		
		try {
			socket = new Socket("127.0.0.1", 1);
			
			System.out.println(socket);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
