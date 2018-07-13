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
            //1,����socket�ͻ��˶���
            socket = new Socket("127.0.0.1", 8888);
            //2����ȡ����¼�롣��ȡ����̨�û��������Ϣ
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            
            //3,socket����������ȡ����˷��ص�����-------------
            //bufferedReader_Server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            InputStream inputStream = socket.getInputStream();
            
    	    
            //4,socket�������������̨��Ϣ��������������
            //bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    	    OutputStream outputStream = socket.getOutputStream();
    	    socket.getOutputStream().write("���  ,���ǿͻ��ˣ�".getBytes("UTF-8"));
    	    outputStream.flush();
    	    
            System.out.print("�����룺");
            String message = "";
            while ((message = bufferedReader.readLine()) != null) {
            	socket.getOutputStream().write(message.getBytes("UTF-8"));
            	outputStream.flush();
            	//System.out.println("�����˵��"+inputStream2String(inputStream));
                System.out.print("�����룺");
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
	 * ������ת����
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
