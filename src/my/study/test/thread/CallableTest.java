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

public class CallableTest implements Callable<ArrayList<String>>{

	/**
     * 线程私有属性，创建线程时创建
     */
    private ArrayList<String> list;
  
	public CallableTest(ArrayList<String> list) {
        this.list = list;
    }
	@Override
	public ArrayList<String> call() throws Exception {
		// TODO Auto-generated method stub
		ArrayList lists = new ArrayList();
		for(String s : list){
			Thread.sleep(100L);
			s = s + "ok";
			lists.add(s);
			System.out.println("thread:" + Thread.currentThread().getName() +" , "+ s);
		}
		return lists;
	}

	public static void main(String[] args) throws Exception {
		ExecutorService pool = Executors.newFixedThreadPool(4);
		ArrayList<String> list = new ArrayList<String>();
		list.add("1");list.add("2");list.add("3");list.add("4");list.add("5");list.add("6");
		ArrayList<String> lists = new ArrayList<String>();
		lists.add("1");lists.add("2");lists.add("3");lists.add("4");lists.add("5");lists.add("6");
		//存线程返回的结果
		List<Future<List>> listf = new ArrayList<>();
		
		FutureTask<ArrayList<String>> str = (FutureTask<ArrayList<String>>) pool.submit(new CallableTest(list));
		FutureTask<ArrayList<String>> str2 = (FutureTask<ArrayList<String>>) pool.submit(new CallableTest(list));
		System.out.println(str.get());
		System.out.println(str2.get());
        pool.shutdown();

	}
}
