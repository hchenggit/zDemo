package my.study.test.task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
/**
 * ������ ����
 * Author: huangch
 * Since:JDK 7
 * Date: 2018-6-7����1:56:31
 * @Copyright:2018, huangch@szinfinova.com All Rights Reserved
 */
public class CopyOfCountTask extends RecursiveTask<List<String>> {
    private static final long serialVersionUID = -3611254198265061729L;

    public static final int threshold = 5;
    private int start;
    private int end;
    private List<String> plist;

    public CopyOfCountTask(List<String> plist,int start, int end) {
        this.start = start;
        this.end = end;
        this.plist = plist;
    }

    @Override
    protected List<String> compute() {

    	List<String> lists = new ArrayList<String>();

        //��������㹻С�ͼ�������
        boolean canCompute = (end - start) <= 4; //1000 - 500 <= 2
        if (canCompute) {
        	System.out.println("start"+start+",end: "+end);
        	end = end >= plist.size() ? plist.size() : end+1;
        	List<String> list = plist.subList(start, end);
        	/**
        	 * �������İ�
        	 */
        	lists.addAll(list);
        } else {
            // ������������ֵ���ͷ��ѳ��������������
            int middle = (start + end) / 2;
            CopyOfCountTask leftTask = new CopyOfCountTask(plist,start, middle);     //  �����Էָ���
            CopyOfCountTask rightTask = new CopyOfCountTask(plist,middle + 1, end);
            // ִ��������
            leftTask.fork();
            rightTask.fork();
            System.out.println(Thread.currentThread().getName());
            //�ϲ�������
            //�ȴ�����ִ�н����ϲ�����
            lists.addAll((List<String>) leftTask.join());
            lists.addAll((List<String>) rightTask.join());
        }

        return lists;
    }

    public static void main(String[] args) throws  Exception {
    	ArrayList plist = new ArrayList();
        plist.add("1");plist.add("2");plist.add("3");plist.add("4");plist.add("5");plist.add("6");plist.add("7");plist.add("8");plist.add("9");plist.add("10");
        plist.add("11");plist.add("12");plist.add("13");plist.add("14");plist.add("15");plist.add("16");plist.add("17");plist.add("18");plist.add("19");plist.add("20");

        CopyOfCountTask task = new CopyOfCountTask(plist, 0, plist.size());

        //ִ��һ������
        Future<List<String>> result = new ForkJoinPool().submit(task);
      //ȡ���߳�����Ľ��
		List<String> listResult = result.get();
        System.out.println(listResult);
        
      
    }

}