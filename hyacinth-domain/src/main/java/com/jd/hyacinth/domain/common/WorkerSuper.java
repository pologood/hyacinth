package com.jd.hyacinth.domain.common;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jd.hyacinth.common.tools.DataCacheUtils;

public abstract class WorkerSuper {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	private DataCacheUtils dataCacheUtils;
	
	/** 得到该work的基本名称 不含实例号 */
	protected String workBaseName() {
		return getClass().getSimpleName(); 
	}
	
	/** 该work的名称 */
	public final String workName = workBaseName();
	
	protected abstract String workRunKeyName();
	/** 标识该任务是否运行key */
	public final String isRunKey = workRunKeyName() + ".isRun";
	
	public final void doWorker() {
		String isRun = dataCacheUtils.get(isRunKey);
//		String isRun = "true";
		if (!"true".equalsIgnoreCase(isRun)){
			logger.info("sync_work_isRun 该任务　" + workName +" 未设为运行状态  {} = {}，停止运行。 ", isRunKey, isRun);
			return;
		}
		try {
			dispose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected abstract void dispose() throws Exception;
}
