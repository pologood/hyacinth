package org.hyacinth.examples.multiThread;

import java.util.Set;
import java.util.concurrent.*;

import org.hyacinth.examples.annotation.Config;

@Config(value="test")
public class MultiThread {
	public static final Set<String> tmpCache = new ConcurrentSkipListSet<String>();
	public static final Set<String> requestCache = new CopyOnWriteArraySet<String>();
	
	private static ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
	public MultiThread() {
		scheduledExecutor.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				 if (!tmpCache.isEmpty()) {
		                synchronized (tmpCache) {
		                    if(!tmpCache.isEmpty()) {
		                        requestCache.addAll(tmpCache);
		                        try {
									Thread.sleep(10*1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                        tmpCache.clear();
		                    }
		                }
		            }
					System.out.println(requestCache);
			}
			
		}, 2, 5, TimeUnit.SECONDS);
	}

	
	public static void main(String[] args) throws InterruptedException {
		new MultiThread();
		tmpCache.add("1");
	
		Thread.sleep(6 * 1000);
//		synchronized (tmpCache) {
//			tmpCache.add("2");
//		}
		tmpCache.add("2");
		Thread.sleep(60 * 1000);
	}
}
