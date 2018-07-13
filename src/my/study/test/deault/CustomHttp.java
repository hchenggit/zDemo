package my.study.test.deault;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Hello world!
 *
 */
public class CustomHttp 
{
    public static void main( String[] args ) throws Exception{
        System.out.println( "Hello World!" );
        //http://localhost:8081/DMS/action/heartbeat.do
        //JSONObject object = JSONObject.fromObject(json);//{url:'http//10.22.2.15:8080/DMS/action/heartbeat.do',parm:''}
        //{"cookie":
        //executing request http://127.0.0.1:8080/ISAS/action/system/event/subscribe.do
        //http://IP_PORT/ISAS/action/analysistask/getTaskBySearchEntity.do?_dc=1531205676376&analyseType=0&taskName=&taskType=-1&startTime=2018-07-10 00:00:00&endTime=2018-07-10 23:59:59&order={\"property\":\"createTime\",\"direction\":\"DESC\"}&page=1&start=0&limit=100&
        String url = "http://127.0.0.1:8080/ISAS/action/analysistask/getTaskBySearchEntity.do?_dc=1531205676376&analyseType=0&taskName=&taskType=-1&startTime=2018-07-10 00:00:00&endTime=2018-07-10 23:59:59&order={\"property\":\"createTime\",\"direction\":\"DESC\"}&page=1&start=0&limit=100";
        //url = "http://127.0.0.1:8080/ISAS/action/analysistask/getTaskBySearchEntity.do?_dc=1531205676376&analyseType=0&taskName=&taskType=-1&startTime=2018-07-10%2000%3A00%3A00&endTime=2018-07-10%2023%3A59%3A59&order=%7B%22property%22%3A%22createTime%22%2C%22direction%22%3A%22DESC%22%7D&page=1&start=0&limit=100";
       /* url = url.replaceAll("\\\"", "\\%22");
        url = url.replaceAll("\\:", "\\%3A");
        url = url.replaceAll(" ", "\\%20");
        url = url.replaceAll(",", "\\%2C");
        url = url.replaceAll("\\{", "\\%7B");
        url = url.replaceAll("\\}", "\\%7D");
        url = url.replace("http%3A", "http:");// %20
        System.out.println(url);*/
        httpPost(url,"","userInfo.userName=admin; userInfo.verifyPassword=admin; userInfo.userType=0; userInfo.loginName=admin; userInfo.loginFlag=1; userInfo.password=","Basic YWRtaW46MjEyMzJGMjk3QTU3QTVBNzQzODk0QTBFNEE4MDFGQzM=");
    }
    /**
	 * 方法 保存
	 * http://10.128.5.189:7001/hrmis/ria_pojo.do?method=action
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String url,String parm,String cookie,String authorization) throws Exception{

		String result = "";
		// 创建默认的httpClient实例.  
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost 
		HttpPost httppost = new HttpPost(clUrl(url));//"http://10.128.5.189:7001/hrmis/ria_pojo.do?method=action"
		// 创建参数队列  
		List formparams = new ArrayList();
		//按要求的json参数
		StringEntity strentity = new StringEntity(parm,"utf-8");
		
		//uefEntity.setContentEncoding(contentEncoding);
		try {
			httppost.setEntity(strentity);
			//uefEntity = new URLEncoder("","");
			System.out.println("executing request " + httppost.getURI());
			//模拟原始的ajax请求  LoginUtil.getSessionId() 为去sessionid
			httppost.addHeader("Cookie",cookie);
			httppost.setHeader("Content-Type","application/json");
			httppost.addHeader("Authorization",authorization);
			
		
			//httpost = new HttpPost(url2); httppost.setHeader(new BasicHeader("Cookie","jsessionid=66DF7E3E8B3602A1153D8721D537D163"));
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					
					System.out.println("-----------------结果-------------------");
					System.out.println("Response content: " + result);
					System.out.println("--------------------------------------");
				}
			} finally {
				response.close();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			// 关闭连接,释放资源  
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		return result;
	}
	public static String clUrl(String oldUrl){
		if(oldUrl.indexOf("?") > 0 ){
			String start = oldUrl.substring(0,oldUrl.indexOf("?"));
			String url = oldUrl.substring(oldUrl.indexOf("?"));
			url = url.replaceAll("\\\"", "\\%22");
	        url = url.replaceAll("\\:", "\\%3A");
	        url = url.replaceAll(" ", "\\%20");
	        url = url.replaceAll(",", "\\%2C");
	        url = url.replaceAll("\\{", "\\%7B");
	        url = url.replaceAll("\\}", "\\%7D");
	        url = url.replace("http%3A", "http:");// %20
	        return start+url;
		}else{
			return oldUrl;
		}
		
	}
}
