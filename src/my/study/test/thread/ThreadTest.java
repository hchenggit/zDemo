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
                        System.out.println(this.getClass() + "::线程执行中.." + i);
                    }
                }
            }, result);

            System.out.println("task return value:" + task.get());*/

            FutureTask<String> callableTask = (FutureTask<String>) exc.submit(new Callable<String>() {

                @Override
                public String call() throws InterruptedException {
                    for (int i = 0; i < 10; i++) {
                        Thread.sleep(100L);
                        System.out.println(this.getClass() + "::线程执行中.." + i);
                    }
                    return "success";
                }

            });

            System.out.println("提前出结果了 task return value:" );

            System.out.println("callableTask return value:" + callableTask.get());//获取结果时，具有阻塞的功能
            
            System.out.println("提前出结果了 task return value:" );

        } finally {
            exc.shutdown();
        }

    }

}