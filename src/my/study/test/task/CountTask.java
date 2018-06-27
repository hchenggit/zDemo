package my.study.test.task;

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
public class CountTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = -3611254198265061729L;

    public static final int threshold = 2;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {

        int sum = 0;

        //��������㹻С�ͼ�������
        boolean canCompute = (end - start) <= threshold; //1000 - 500 <= 2
        if (canCompute) {
        	System.out.println("start"+start+",end: "+end);
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // ������������ֵ���ͷ��ѳ��������������
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);     //  �����Էָ���
            CountTask rightTask = new CountTask(middle + 1, end);

            // ִ��������
            leftTask.fork();
            rightTask.fork();

            System.out.println(Thread.currentThread().getName());
            //�ȴ�����ִ�н����ϲ�����
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            //�ϲ�������
            sum = leftResult + rightResult;

        }

        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkjoinPool = new ForkJoinPool();

        //����һ���������񣬼���1+2+3+4
        CountTask task = new CountTask(1, 10);

        //ִ��һ������
        Future<Integer> result = forkjoinPool.submit(task);

        try {
            System.out.println(result.get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}