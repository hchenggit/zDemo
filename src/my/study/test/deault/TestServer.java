package my.study.test.deault;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.ByteArrayEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.infinova.common.spring.JUnit4ClassRunner;
import com.infinova.communication.base.ActionResult;
import com.infinova.communication.base.HttpClientOperation;
import com.infinova.communication.base.category.ReturnCodeType;
import com.infinova.communication.base.util.Strings;
import com.infinova.communication.http.common.HttpResponseResult;
import com.infinova.communication.http.common.HttpStatusCodes;
import com.infinova.communication.vo.PlatformInfo;
import com.infinova.retrieval.service.RetrievalExeService;
import com.infinova.retrieval.vo.PicCodeContext;
import com.infinova.retrieval.vo.SMRecogPicVO;
import com.infinova.system.SystemModule;
import com.infinova.system.enums.SESServerType;
import com.infinova.system.vo.BigDataInfoVO;
import com.infinova.system.vo.ConCodeServer;
/**
 * 测试
 * Author: huangch
 * Since:JDK 7
 * Date: 2018-6-4下午6:58:26
 * @Copyright:2018, huangch@szinfinova.com All Rights Reserved
 */
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:../CMS/src/main/webapp/WEB-INF/applicationContext.xml", 
		"file:../CMS/src/main/webapp/WEB-INF/dataSource.xml",
        "file:../CMS/src/main/webapp/WEB-INF/redis.xml",
//         "file:../CMS/src/main/conf/system.properties",
        "file:../CMS/src/main/webapp/WEB-INF/security.xml", 
        "file:../CMS/src/main/webapp/WEB-INF/springmvc-servlet.xml"})
public class TestServer {
	@Autowired
	private RetrievalExeService retrievalExeService;

	/**
	 * PFS 识别图片结构�?
	 * @throws IOException
	 */
	@TestJNA
	public void testPfs() throws IOException{
		String url = "http://10.82.27.21:9005/p/1_b6PDEbgzTBmY5vZCQkMwNue_0_15_5c9hV_Vsv_c488?x=1353&y=543&width=360&height=291";
		String picType = "0";
		System.out.println("---------------------------------------------");
		ConCodeServer pfs = new ConCodeServer();//PFS
		pfs.setIp("10.82.27.32");
		pfs.setPort("8000");
		pfs.setConCode("SM_IMAGE");
		String str = sendCodeServer(pfs,url,picType);
		System.out.println(str);
	}
	
	/**
	 * PFS
	 * @param i 服务�?
	 * @param url 图片地址
	 * @param picType 图片类型
	 * @return 图片结构化信�?
	 * @throws IOException
	 */
	private String sendCodeServer(ConCodeServer i, String url,String picType)
			throws IOException {
		ByteArrayEntity entity = new ByteArrayEntity(getCodeModel(url,
				i.getConCode(),picType));
		StringBuilder urlSt = new StringBuilder();
		urlSt.append("http://").append(i.getIp()).append(":")
				.append(i.getPort()).append("/?cmd=recogPic");
		System.out.println(urlSt.toString());
		HttpResponseResult response = HttpClientOperation.doPost(
				urlSt.toString(), null, entity);
		if (response.getStatusCode() == HttpStatusCodes.Code200
				&& !com.google.common.base.Strings.isNullOrEmpty(response
						.getResponseContent())) {
			return response.getResponseContent();
		}
		return null;
	}
	
	@TestJNA
	public void testTgi() {
		System.out.println("------------------------------testTgi------------------------------------");
		PlatformInfo platformInfo = new PlatformInfo();
		platformInfo.setIp("10.82.13.166");
		platformInfo.setPort("9001");
		String style = "default";
		String fileName = "1_V_Vsv_c488_CS.jpg";
		try {
			byte[] bytes = toByteArray("C:\\Users\\chenjsj\\Downloads\\1_V_Vsv_c488_CS.jpg");
			Map str = uploadPic(platformInfo, style, bytes, fileName);
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("---------------------------执行完毕------------------------------");
			System.out.println(str);
			System.out.println("-----------------------------------------------------------------------");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Tgi
	 * 返回图片上传的地�?
	 */
	public Map uploadPic(PlatformInfo platformInfo ,String style, byte[] bytes,String fileName) throws Exception{
        long start = System.currentTimeMillis();
        Map result = new HashMap();
        try {
          
            ByteArrayEntity entity = new ByteArrayEntity(bytes);
            StringBuilder urlSt = new StringBuilder();
            //the param style must be the first param
            urlSt.append("http://").append(platformInfo.getIp()).append(":").append(platformInfo.getPort())
                    .append("/sdk.post?").append("style=").append(style).append("&filename=").append(fileName).append("&type=0&force=1");
            System.out.println("----------------------------------------"+urlSt.toString());
            HttpResponseResult response = HttpClientOperation.doPost(urlSt.toString(), null, entity);
            System.out.println(response.getResponseContent()+"-"+response.getStatusCode());
            System.out.println("~");
            if (response.getStatusCode() == HttpStatusCodes.Code200 &&
                    !com.google.common.base.Strings.isNullOrEmpty(response.getResponseContent())) {
                if(response.getResponseContent().equals("fail")){
                	result.put("errorCode", ReturnCodeType.SERVER_TGI_ERROR);
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("http://").append(platformInfo.getIp()).append(":").append(platformInfo.getPort()).append(response.getResponseContent());
                    result.put("errorCode", ReturnCodeType.SUCCESS);
                    result.put("data",sb.toString());
                }
            } else {
            	result.put("errorCode", ReturnCodeType.SERVER_TGI_ERROR);
            }
            return result;
        } finally {
            System.out.println("TGI upload image EPS:" + (System.currentTimeMillis() - start));
        }
    }
	/**
	 * �?��各服务器
	 */
	@TestJNA
	public void testServer(){
		try {
			BigDataInfoVO bd = SystemModule.getBd();
			
			System.out.println(bd.getIp());
			System.out.println(bd.getDriver());
			System.out.println(bd.getPort());
			//retrievalExeService.queryFeature(null,"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private byte[] getCodeModel(String url, String type,String picType) {
		String context = "";
		if (SESServerType.SM_IMAGE.toString().equalsIgnoreCase(type)) {
			context = SMRecogPicVO.getPfsContext(url,picType);
		} else if (SESServerType.GL_IMAGE.toString().equalsIgnoreCase(type)) {
			context = PicCodeContext.getPfsContext(url);
		}

		return context.getBytes();
	}
	
	public static byte[] GetImageByte(String imgFile){//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
	
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try   
        {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
          
        }   
        catch (IOException e)   
        {  
            e.printStackTrace();  
        }  
        return data;
    }
	public static byte[] toByteArray(String filename) throws IOException {

		File f = new File(filename);
		if (!f.exists()) {
			throw new FileNotFoundException(filename);
		}

		ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
		BufferedInputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(f));
			int buf_size = 1024;
			byte[] buffer = new byte[buf_size];
			int len = 0;
			while (-1 != (len = in.read(buffer, 0, buf_size))) {
				bos.write(buffer, 0, len);
			}
			return bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			bos.close();
		}
	}
}
