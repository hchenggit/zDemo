package my.study.test.deault;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
/**
 * 一定要养成习惯，json "key":"value"
 * @author huangch
 * Since:JDK 7
 * Date:2018-7-31上午11:12:12
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class CMSHttpTest {

	public static void main( String[] args ) throws Exception{
        System.out.println( "Hello World!" );
        String url = "http://10.82.27.20:8040/DMS/action/config/getDMSConfig.do";//10.82.27.20:8040
        String parm = "{type:\"add\",param:{ip:\"127.0.0.1\",port:\"8087\",EngineServerType:\"SM\",MEMORY_SIZE:0.7,node:\"node1\"}}";
        parm = "{id:\"\"}";
        JSONObject dbsParm = JSONObject.fromObject(parm);
        //dbsParm.put("id", "");
        httpPost(url,dbsParm.toString(),"","");
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
		//按要求的json参数
		//StringEntity strentity = new StringEntity(parm,"utf-8");
		ByteArrayEntity strentity = new ByteArrayEntity(parm.getBytes("utf-8"));
		//uefEntity.setContentEncoding(contentEncoding);
		try {
			httppost.setEntity(strentity);
			
			System.out.println("executing request " + httppost.getURI());
			System.out.println("executing request " + parm);
			//模拟原始的ajax请求  LoginUtil.getSessionId() 为去sessionid
			//httppost.addHeader("Cookie",cookie);
			httppost.setHeader("Content-Type","application/json");
			//httppost.addHeader("Authorization",authorization);
			
		
			//httpost = new HttpPost(url2); httppost.setHeader(new BasicHeader("Cookie","jsessionid=66DF7E3E8B3602A1153D8721D537D163"));
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
					
					System.out.println("-----------------结果-------------------");
					System.out.println("Response content: " + result);
					//JSONObject o = JSONObject.fromObject(result);
					
					//Object[] list = o.getJSONArray("msg").toArray();
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
	public static String mapPost(String url, Map<String,Object> map, String encoding){
		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try{
		httpClient = HttpClients.createDefault();
		httpPost = new HttpPost(url);
		//设置参数
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		Iterator iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
		Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
		list.add(new BasicNameValuePair(elem.getKey(),String.valueOf(elem.getValue())));
		}
		if(list.size() > 0){
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,encoding);
		httpPost.setEntity(entity);
		}
		HttpResponse response = httpClient.execute(httpPost);
		if(response != null){
		HttpEntity resEntity = response.getEntity();
		if(resEntity != null){
		result = EntityUtils.toString(resEntity,encoding);
		}
		}
		}catch(Exception ex){
		ex.printStackTrace();
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
