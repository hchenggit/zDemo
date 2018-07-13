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
		// 要连接的服务端IP地址和端口 telnet 10.22.2.27 8888
	    String host = "10.22.2.27"; 
	    int port = 8888;
	    // 与服务端建立连接
	    Socket socket = null;
		try {
			socket = new Socket(host, port);
			System.out.println("建立连接...");
		    // 建立连接后获得输出流
		    final OutputStream outputStream = socket.getOutputStream();
		    //String message="你好  yiwangzhibujian";
		    //socket.getOutputStream().write(message.getBytes("UTF-8"));
	
		    //获取输入流
		    InputStream inputStream = socket.getInputStream();
		    
		    byte[] bytes = new byte[10240];int len;
		    //如果使用多线程，那就需要线程池，防止并发过高时创建过多线程耗尽资源
		    ExecutorService threadPool = Executors.newFixedThreadPool(20);
		    //不断监听服务端的输入流
	        while ( ((len = inputStream.read(bytes)) != -1) ) {
	        	System.out.println("监听到服务端的消息");
	        	final String json = new String(bytes, 0, len,"UTF-8");
	        	System.out.println("消息是: "+json);
	        	Runnable runnable= new Thread(new Runnable(){
	  	    	    @Override
	  	    	    public void run() {	
	  	    	    	try{
	  		        		JSONObject object = JSONObject.fromObject(json);//{url:'http//10.22.2.15:8080/DMS/action/heartbeat.do',parm:''}
	  		  	    	    //这里会发生异常，导致执行中断
	  		  	    	    String result = CustomHttp.httpPost( ((String) object.get("url")).replace("IP_PORT", "127.0.0.1:8080"), object.getJSONObject("parm").toString(), getKey(object,"cookie"), getKey(object,"authorization"));
	  		  	    	    System.out.println("开始反馈："+result);
	  		  	    	    //在把消息返回出去 {uid:'',result:''}
	  		  	    	    JSONObject sendMsg = new JSONObject();
	  		  	    	    sendMsg.put("uid", object.get("uid"));
	  		  	    	    sendMsg.put("result", result);
	  		  	    	    outputStream.write(sendMsg.toString().getBytes("UTF-8"));
	  		  	    	    outputStream.flush();
	  		  	    	    System.out.println("消息反馈完毕！");
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
	public static String getKey(JSONObject object,String key){
		if(object.has(key)){
			return object.getString(key);
		}else{
			return "";
		}
	}
	
	
}
