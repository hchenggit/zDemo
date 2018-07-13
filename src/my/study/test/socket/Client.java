package my.study.test.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedReader bufferedReader_Server = null;
        BufferedWriter bufferedWriter = null;
        try {
            //1,创建socket客户端对象。
            socket = new Socket("127.0.0.1", 8888);
            //2，获取键盘录入。获取控制台用户输入的信息
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            
            //3,socket输入流。读取服务端返回的数据-------------
            //bufferedReader_Server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            InputStream inputStream = socket.getInputStream();
            
    	    
            //4,socket输出流。将控制台信息数据输出给服务端
            //bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    	    OutputStream outputStream = socket.getOutputStream();
    	    socket.getOutputStream().write("你好  ,我是客户端！".getBytes("UTF-8"));
    	    outputStream.flush();
    	    
            System.out.print("请输入：");
            String message = "";
            while ((message = bufferedReader.readLine()) != null) {
            	socket.getOutputStream().write(message.getBytes("UTF-8"));
            	outputStream.flush();
            	//System.out.println("服务端说："+inputStream2String(inputStream));
                System.out.print("请输入：");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
	/**
	 * 输入流转文字
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static String inputStream2String(InputStream is) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

}
