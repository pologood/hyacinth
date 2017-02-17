package org.hyacinth.examples.multiThread;

/** 
 * @author cdbaironglin 
 * @version 2017年2月15日 下午2:17:25 
 */

public class WorkerThread implements Runnable {
    
    private String s;
    
    public WorkerThread(String s) {
        this.s = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": start command" + s);
        processCommand(s);
        System.out.println(Thread.currentThread().getName() + ": end.");
        
    }

    private void processCommand(String s2) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return this.s;
    }

}
