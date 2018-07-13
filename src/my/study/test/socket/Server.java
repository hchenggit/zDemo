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
            //1��������˶���
            serverSocket = new ServerSocket(8888);
            //2,��ȡ���ӹ����Ŀͻ��˶���
            Socket socket = serverSocket.accept();
            System.out.println("���Կͻ��ˡ�" + socket.getInetAddress().getHostAddress() + "��������");
            BufferedReader bufferedReader_SystemIN = null;
            
            //3��ͨ��socket�����ȡ��������Ҫ��ȡ�ͻ��˷���������-----------
            //bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            InputStream inputStream = socket.getInputStream();
            
            //4��ͨ���ַ���������ȡ�������룬Ҫ��ȡ����̨д���ͻ��˵�����
            bufferedReader_SystemIN = new BufferedReader(new InputStreamReader(System.in));
            
            //5.ʹ�ÿͻ���socket�������������ͻ��˷�������-----------------
            //bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            OutputStream outputStream = socket.getOutputStream();
    	    socket.getOutputStream().write("���  ,��ӭ�ӽ���".getBytes("UTF-8"));
    	    outputStream.flush();
    	    
            String message = "";
            
            byte[] bytes = new byte[20480];int len;
            //���ϼ����ͻ��˵�������
            while ((len = inputStream.read(bytes)) != -1) {
    	    	//ע��ָ�������ʽ�����ͷ��ͽ��շ�һ��Ҫͳһ������ʹ��UTF-8
    	    	System.out.println(socket.getInetAddress().getHostAddress()+"˵��"+new String(bytes, 0, len,"UTF-8"));
    	    	System.out.print("�����룺");
    	    	message = bufferedReader_SystemIN.readLine();//��ȡ����̨�����ֵ
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
