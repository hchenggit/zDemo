package my.study.test.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static ServerSocket serverSocket = null;
    public static void main(String[] args) {
        try {
            //1创建服务端对象。
            serverSocket = new ServerSocket(8888);
            //2,获取连接过来的客户端对象。
            Socket socket = serverSocket.accept();
            System.out.println("来自客户端【" + socket.getInetAddress().getHostAddress() + "】的连接");
            BufferedReader bufferedReader_SystemIN = null;
            
            //3，通过socket对象获取输入流，要读取客户端发来的数据-----------
            //bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            InputStream inputStream = socket.getInputStream();
            
            //4，通过字符输入流获取键盘输入，要读取控制台写给客户端的数据
            bufferedReader_SystemIN = new BufferedReader(new InputStreamReader(System.in));
            
            //5.使用客户端socket对象的输出流给客户端返回数据-----------------
            //bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            OutputStream outputStream = socket.getOutputStream();
    	    socket.getOutputStream().write("你好  ,欢迎接进！".getBytes("UTF-8"));
    	    outputStream.flush();
    	    
            String message = "";
            
            byte[] bytes = new byte[20480];int len;
            //不断监听客户端的输入流
            while ((len = inputStream.read(bytes)) != -1) {
    	    	//注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
    	    	System.out.println(socket.getInetAddress().getHostAddress()+"说："+new String(bytes, 0, len,"UTF-8"));
    	    	System.out.print("请输入：");
    	    	message = bufferedReader_SystemIN.readLine();//读取控制台输入的值
                socket.getOutputStream().write(message.getBytes("UTF-8"));
        	    outputStream.flush();
            }
            
           
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
