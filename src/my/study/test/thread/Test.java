package my.study.test.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Test {
	static Map<String,Integer> m = new HashMap();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService pool = Executors.newFixedThreadPool( 4 );
		m.put("1", 1);
		pool.submit(new Runnable(){
			@Override
			public void run() {
				while(true){
					m.put("4", 1);
					m.put("2", 1);
					m.put("3", 1);
					m.put("1", 1+m.get("1"));
				}
			}});
		pool.submit(new Runnable(){
			@Override
			public void run() {
				while(true){
					m.put("4", 2);
					m.put("3", 2);
					m.put("2", 2);
					m.put("1", 1+m.get("1"));
					
					//m.remove("1");
				}
			}});
		pool.submit(new Runnable(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.print(m.get("2")+"----------------------------");
					System.out.println(m.get("1"));
				}
			}});
	}

	
}
