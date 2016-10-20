/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.jd.hyacinth.domain;

import com.jd.hyacinth.domain.base.BaseDomain;

/**
 * seckill
 * @author J-ONE
 * @since 2016-10-20
 */
public class Seckill extends BaseDomain {
	private static final long serialVersionUID = 1L;
	private Long seckillId;
	private String name;
	private Integer number;
	private String startTime;
	private String endTime;
	private String createTime;

	public Seckill(){
		//默认无参构造方法
	}

	/**
	 * 获取 seckillId
	 * @return
	 */
	public Long getSeckillId(){
		return seckillId;
	}
	
	/**
	 * 设置 seckillId
	 * @param seckillId
	 */
	public void setSeckillId(Long seckillId){
		this.seckillId = seckillId;
	}

	/**
	 * 获取 name
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 设置 name
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * 获取 number
	 * @return
	 */
	public Integer getNumber(){
		return number;
	}
	
	/**
	 * 设置 number
	 * @param number
	 */
	public void setNumber(Integer number){
		this.number = number;
	}

	/**
	 * 获取 startTime
	 * @return
	 */
	public String getStartTime(){
		return startTime;
	}
	
	/**
	 * 设置 startTime
	 * @param startTime
	 */
	public void setStartTime(String startTime){
		this.startTime = startTime;
	}

	/**
	 * 获取 endTime
	 * @return
	 */
	public String getEndTime(){
		return endTime;
	}
	
	/**
	 * 设置 endTime
	 * @param endTime
	 */
	public void setEndTime(String endTime){
		this.endTime = endTime;
	}

	/**
	 * 获取 createTime
	 * @return
	 */
	public String getCreateTime(){
		return createTime;
	}
	
	/**
	 * 设置 createTime
	 * @param createTime
	 */
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
}