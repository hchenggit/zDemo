package my.study.test.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadTest {

    public static void main(String[] args) throws Exception {
        ExecutorService exc = Executors.newCachedThreadPool();
        try {
            /*String result = null;
            
            FutureTask<String> task = (FutureTask<String>) exc.submit(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++) {
                        try {
                            Thread.sleep(100L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(this.getClass() + "::�߳�ִ����.." + i);
                    }
                }
            }, result);

            System.out.println("task return value:" + task.get());*/

            FutureTask<String> callableTask = (FutureTask<String>) exc.submit(new Callable<String>() {

                @Override
                public String call() throws InterruptedException {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(100L);
                        System.out.println(this.getClass() + "::�߳�ִ����.." + i);
                    }
                    return "success";
                }

            });

            System.out.println("��ǰ������� task return value:" );

            System.out.println("callableTask return value:" + callableTask.get());//��ȡ���ʱ�����������Ĺ���
            
            System.out.println("��ǰ������� task return value:" );

        } finally {
            exc.shutdown();
        }

    }

}