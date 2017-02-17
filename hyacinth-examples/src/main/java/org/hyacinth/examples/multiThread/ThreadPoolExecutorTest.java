package org.hyacinth.examples.multiThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** 
 * @author cdbaironglin 
 * @version 2017年2月15日 上午11:22:06 
 */

public class ThreadPoolExecutorTest {

    public static void main(String[] args) throws Exception {
        RejectedExecutionHandler handler = new RejectedExecutionHandlerImpl();
        ThreadFactory factory = Executors.defaultThreadFactory();
        ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(2), factory);
        MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
        Thread monitorThread = new Thread(monitor);
        monitorThread.start();
        
        for (int i = 0; i < 10; i++) {
            executorPool.execute(new WorkerThread("cmd" + i));
        }
        
        Thread.sleep(30 * 1000);
        executorPool.shutdown();
        Thread.sleep(5 * 1000);
        monitor.shutdown();
    }

}
