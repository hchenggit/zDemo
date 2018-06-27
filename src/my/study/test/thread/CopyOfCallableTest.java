package my.study.test.thread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class CopyOfCallableTest implements Callable<String>{

	/**
     * 线程私有属性，创建线程时创建
     */
    private String s;
    public volatile static int i ;
    static AtomicInteger ai=new AtomicInteger(1);  
    
	public CopyOfCallableTest(String s) {
        this.s = s;
    }
	@Override
	public String call() throws Exception {
		s = s + "ok";
		Thread.sleep(1000L);
		i ++ ;
		System.out.println(i+"thread:" + Thread.currentThread().getName() +" , ");
		return s;
	}

	public static void main(String[] args) throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(6);
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");
		//存线程返回的结果
		List<Future<String>> listf = new ArrayList<>();
		for(String s: list){
			Future<String> future = pool.submit(new CopyOfCallableTest(s));
			listf.add(future);
		}
		
        pool.shutdown();
        System.out.println(listf);
        List listq = new ArrayList();
        for (Future<String> future : listf) {
			String res = future.get();
			if (res == null )continue;
			listq.add(res);
		}
        
	}
}
