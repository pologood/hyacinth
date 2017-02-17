package org.hyacinth.examples;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** 
 * @author cdbaironglin 
 * @version 2017年1月16日 上午11:26:58 
 */

public class ExecutorServiceTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ExecutorService service = Executors.newFixedThreadPool(1);
		List<Callable<Integer>> tasks = new ArrayList<Callable<Integer>>();
		tasks.add(new Callable<Integer>() {
			public Integer call() {
				System.out.println("start1 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("end1 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
				return 1;
			}
		});
		service.invokeAll(tasks);
		tasks.clear();
		tasks.add(new Callable<Integer>() {
			public Integer call() {
				System.out.println("start2 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("end2 " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()));
				return 1;
			}
		});
		service.invokeAll(tasks);
		service.shutdown();
	}

}
