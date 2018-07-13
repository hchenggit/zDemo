package my.study.test.ice;

import myHelloWorld.HelloWorldPrx;
import myHelloWorld.HelloWorldPrxHelper;

/**
 * ice客户端
 * @author huangch
 * Since:JDK 7
 * Date:2018-7-3下午3:10:20
 * @Copyright2018,huangch@szinfinova.com All Rights Reserved
 */
public class Client {

	/**
	 * Ice.ObjectPrx base = ic.stringToProxy("SimplePrinter:tcp -h 127.0.0.1 -p 10000");
	 * @sober , 2018-6-10下午3:26:31
	 */
	public static void main(String[] args) {
		int status = 0;
	    // Communicator实例
	    Ice.Communicator ic = null;
	    try{
	      // 调用Ice.Util.Initialize()初始化Ice run time
	      ic = Ice.Util.initialize(args);
	      //   根据servant的名称以及服务器ip、端口获取远程服务代理
	      Ice.ObjectPrx base = ic.stringToProxy("SimplePrinter:tcp -h 10.22.2.27 -p 10000");
	      // 将上面的代理向下转换成一个Printer接口的代理
	      HelloWorldPrx helloWorld = HelloWorldPrxHelper.checkedCast(base);
	      // 如果转换成功
	      if (helloWorld == null){
	        throw new Error("Invalid proxy");
	      }
	      // 调用这个代理，将字符串传给它
	      helloWorld.say("你好吗！");
	      helloWorld.say("大家好！");
	      helloWorld.say("才是真的！");
	      
	      helloWorld.s
	    } catch (Ice.LocalException e){
	      e.printStackTrace();
	      status = 1;
	    } catch (Exception e){
	      e.printStackTrace();
	      status = 1;
	    } finally{
	      if (ic != null){
	        ic.destroy();
	      }
	    }
	    System.exit(status);
	}

}
