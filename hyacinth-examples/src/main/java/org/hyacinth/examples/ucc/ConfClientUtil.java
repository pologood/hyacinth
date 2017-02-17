package org.hyacinth.examples.ucc;

import java.util.concurrent.TimeUnit;

import com.jd.std.ucc.client.ConfClient;
import com.jd.std.ucc.client.client.ConfClientBuilder;

public class ConfClientUtil {
	
	/** UCC host */
	private static String host = "http://api.ucc.jd.com";
	
	/** UCC拉取配置线程池大小 */
	private static int pollingThreadPoolSize = 2;
	
	/** UCC长连接超时时间 */
	private static int waitTimeout = 15;
	
	/** UCC轮询间隔 */
	private static int intervalTime = 10;
	
	/** UCC时间单位 */
	private static TimeUnit timeUnit = TimeUnit.SECONDS;
	
	/** UCC path */
	private static String path = "chat";
	
	/** UCC path token */
	private static String token = "hiYjDBWq";
	
    private static ConfClient instance = null;

    private static Object lock = new Object();
    
    
    private static ConfClient getInstance(){

        if(instance == null){
            synchronized(lock){
                if(instance == null){
                    //instance = new ConfClientHandler(host, waitTimeout, timeUnit);
                	instance = ConfClientBuilder.newBuilder()
								.host(host)
								.pollingThreadPoolSize(pollingThreadPoolSize)
								.waitTimeout(waitTimeout)
								.intervalTime(intervalTime)
								.timeUnit(timeUnit)
								.build();
                }
            }
        }
        return instance;
    }
    
    public static String getConf(String key) throws Exception {
    	return getInstance().getConfValue(path, token, key);
    }
}
