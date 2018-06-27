package my.study.test.javaDll;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.exceptions.NativeException;

public class Test {

	/**
	 * @param args
	 * @throws NativeException 
	 */
	public static void main(String[] args) throws NativeException {
		// TODO Auto-generated method stub JNativeCpp.dll
	   System.out.println(System.getProperty("java.version"));  
	   //System.out.println(System.getProperty("java.library.path"));
	   System.load("E:\\hc\\Workspaces\\WDemo\\src\\libmatch.dll");
	   //System.setProperty("jnative.debug", "true");
		JNative.setLoggingEnabled(true);  
        try {  
            JNative getUrl = new JNative("libmatch.dll", "seemmo_pvc_match"); //创建 getUrl 方法的<span style="font-family: Arial, Helvetica, sans-serif;">JNative对象</span>  
            /*getUrl.setRetVal(Type.STRING); //设置返回值类型为：String  
            getUrl.setParameter(0, "127.0.0.1"); //按顺序设置方法需要的参数值  
            getUrl.setParameter(1, 10087);  
            getUrl.setParameter(2, 123);  */
            getUrl.invoke(); //调用方法  
            System.out.println(getUrl.getRetVal()); //输出返回值  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        }  
	}

}
