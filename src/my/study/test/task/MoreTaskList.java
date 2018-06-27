package my.study.test.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;




/**
 * 多任务分集合
 * Author: huangch
 * Since:JDK 7
 * Date: 2018-6-12上午9:41:26
 * @Copyright:2018, huangch@szinfinova.com All Rights Reserved
 */
public class MoreTaskList extends RecursiveTask<List<String>>{

	    public  int threshold = 3; // 可分配的线程数量
	    private List<String> plist;   // 100   4  25  3 33  2 50
	    public  int start = 0;
	    public  int end = 0;
	    
	    public MoreTaskList(List<String> plist,int start, int end) {
	        this.plist = plist;
	        this.start = start;
	        this.end = end;
	    }

	    @Override
	    protected List<String> compute() {

	    	List<String> lists = new ArrayList<String>();

	        //如果任务足够小就计算任务
	        boolean canCompute = end >= plist.size();
	        if (canCompute) {//一个分裂的集合
	            //
	        	end = end > plist.size() ? plist.size() : end;
	        	List<String> list = plist.subList(start, end);
	        	/**
	        	 * 分了集合之后 可以做其它事了。。。
	        	 */
	        	System.out.println(start+"---"+end);
	        	lists.addAll(list);
	        } else {
	        	int min = (int) Math.ceil((float)plist.size()/threshold) ;  //最小单元  100/3  34
	            // 如果任务大于阈值，就分裂成两个子任务计算
	            MoreTaskList leftTask = new MoreTaskList(plist,end, end+min);     //   0 34，34 68，68 102
	            
	            // 执行子任务
	            leftTask.fork();
	            
	            System.out.println(Thread.currentThread().getName()+": min:"+min+",start:"+end+","+(end+min));
	            //等待任务执行结束合并其结果
	            lists.addAll((List<String>) leftTask.join());
	        }
			return lists;
	
	}
	    /**
		 * @param args
	     * @throws ExecutionException 
	     * @throws InterruptedException 
		 */
		public static void main(String[] args) throws  Exception {
			// TODO Auto-generated method stub
	        ArrayList plist = new ArrayList();
	        plist.add("1");plist.add("2");plist.add("3");plist.add("4");plist.add("5");plist.add("6");plist.add("7");plist.add("8");plist.add("9");plist.add("10");
			MoreTaskList task = new MoreTaskList(plist, 0, 0);

	        //执行一个任务
	        Future<List<String>> result = new ForkJoinPool().submit(task);
	      //取多线程运算的结果
			List<String> listResult = result.get();
	        System.out.println(listResult);
	        
		}

}
