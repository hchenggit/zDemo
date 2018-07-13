package my.study.test.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import my.study.test.deault.CustomHttp;
import net.sf.json.JSONObject;
//http://127.0.0.1/ISAS/action/system/event/subscribe.do?_dc=1531194796225&timeout=36000000&clientId=3499b5fd-0e8e-4205-b222-9920650c5274
public class SocketClient {
	
	public static void main(String[] args) {
		// Ҫ���ӵķ����IP��ַ�Ͷ˿� telnet 10.22.2.27 8888
	    String host = "10.22.2.27"; 
	    int port = 8888;
	    // �����˽�������
	    Socket socket = null;
		try {
			socket = new Socket(host, port);
			System.out.println("��������...");
		    // �������Ӻ��������
		    final OutputStream outputStream = socket.getOutputStream();
		    //String message="���  yiwangzhibujian";
		    //socket.getOutputStream().write(message.getBytes("UTF-8"));
	
		    //��ȡ������
		    InputStream inputStream = socket.getInputStream();
		    
		    byte[] bytes = new byte[10240];int len;
		    //���ʹ�ö��̣߳��Ǿ���Ҫ�̳߳أ���ֹ��������ʱ���������̺߳ľ���Դ
		    ExecutorService threadPool = Executors.newFixedThreadPool(20);
		    //���ϼ�������˵�������
	        while ( ((len = inputStream.read(bytes)) != -1) ) {
	        	System.out.println("����������˵���Ϣ");
	        	final String json = new String(bytes, 0, len,"UTF-8");
	        	System.out.println("��Ϣ��: "+json);
	        	Runnable runnable= new Thread(new Runnable(){
	  	    	    @Override
	  	    	    public void run() {	
	  	    	    	try{
	  		        		JSONObject object = JSONObject.fromObject(json);//{url:'http//10.22.2.15:8080/DMS/action/heartbeat.do',parm:''}
	  		  	    	    //����ᷢ���쳣������ִ���ж�
	  		  	    	    String result = CustomHttp.httpPost( ((String) object.get("url")).replace("IP_PORT", "127.0.0.1:8080"), object.getJSONObject("parm").toString(), getKey(object,"cookie"), getKey(object,"authorization"));
	  		  	    	    System.out.println("��ʼ������"+result);
	  		  	    	    //�ڰ���Ϣ���س�ȥ {uid:'',result:''}
	  		  	    	    JSONObject sendMsg = new JSONObject();
	  		  	    	    sendMsg.put("uid", object.get("uid"));
	  		  	    	    sendMsg.put("result", result);
	  		  	    	    outputStream.write(sendMsg.toString().getBytes("UTF-8"));
	  		  	    	    outputStream.flush();
	  		  	    	    System.out.println("��Ϣ������ϣ�");
	  		        	}catch(Exception e){
	  		        		e.printStackTrace();
	  		        	}
	  	    	    }
	        	});
	        	threadPool.submit(runnable);
	        }
	    
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("finally :");
		    
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
	public static String getKey(JSONObject object,String key){
		if(object.has(key)){
			return object.getString(key);
		}else{
			return "";
		}
	}
	
	
}
