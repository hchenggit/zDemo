package my.study.test.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * �߳�����demo������ʱ����߳�id�Լ������߳��в���
 */
public class ThreadRunner implements Runnable {

    private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

    /**
     * �߳�˽�����ԣ������߳�ʱ����
     */
    private Integer num;

    public ThreadRunner(Integer num) {
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println("thread:" + Thread.currentThread().getName() + ",time:" + format.format(new Date()) + ",num:" + num);
        try {//ʹ�߳�˯�ߣ�ģ���߳��������
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}