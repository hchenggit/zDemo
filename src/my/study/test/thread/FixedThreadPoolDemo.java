package my.study.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(4);
        for(int i = 0 ; i < 50 ; i++){
            pool.submit(new ThreadRunner((i + 1)));
        }
        pool.shutdown();

	}

}
