package my.study.test.ice;

public class Client1 {
	public static void main(String[] args)
	  {
	    int status = 0;
	    // Communicatorʵ��
	    Ice.Communicator ic = null;
	    try
	    {
	      // ����Ice.Util.Initialize()��ʼ��Ice run time
	      ic = Ice.Util.initialize(args);

	      //   ����servant�������Լ�������ip���˿ڻ�ȡԶ�̷������
	      Ice.ObjectPrx base = ic.stringToProxy("SimplePrinter:default -p 10000");

	      // ������Ĵ�������ת����һ��Printer�ӿڵĴ���
	      HelloWorldPrx helloWorld = HelloWorldPrxHelper.checkedCast(base);

	      // ���ת���ɹ�
	      if (helloWorld == null)
	      {
	        throw new Error("Invalid proxy");
	      }

	      // ��������������ַ���������
	      helloWorld.say("bar");

	    } catch (Ice.LocalException e)
	    {
	      e.printStackTrace();
	      status = 1;
	    } catch (Exception e)
	    {
	      e.printStackTrace();
	      status = 1;
	    } finally
	    {
	      if (ic != null)
	      {
	        ic.destroy();
	      }
	    }
	    System.exit(status);
	  }
}
