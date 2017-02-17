package org.hyacinth.examples.multiThread;

import java.util.concurrent.ThreadPoolExecutor;

/** 
 * @author cdbaironglin 
 * @version 2017年2月15日 上午11:22:29 
 */

public class MyMonitorThread implements Runnable {

    private ThreadPoolExecutor executor;
    private boolean run = true;
    private int delay;
    
    public MyMonitorThread(ThreadPoolExecutor executor, int delay) {
        this.executor = executor;
        this.delay = delay;
    }
    
    public void shutdown() {
        this.run = false;
    }
    @Override
    public void run() {
        while (run) {
            System.out.println(
                    String.format("[monitor][%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s", 
                            executor.getPoolSize(),
                            executor.getCorePoolSize(),
                            executor.getActiveCount(),
                            executor.getCompletedTaskCount(),
                            executor.getTaskCount(),
                            executor.isShutdown(),
                            executor.isTerminated()));
            try {
                Thread.sleep(delay * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
