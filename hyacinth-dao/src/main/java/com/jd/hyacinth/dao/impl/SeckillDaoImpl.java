/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.jd.hyacinth.dao.impl;

import org.springframework.stereotype.Repository;
import com.jd.hyacinth.domain.Seckill;
import com.jd.hyacinth.dao.base.BaseDaoImpl;
import com.jd.hyacinth.dao.SeckillDao;

/**
 * SeckillDao 实现类
 * @author J-ONE
 * @since 2016-10-20
 */
@Repository("seckillDao")
public class SeckillDaoImpl extends BaseDaoImpl<Seckill,Long> implements SeckillDao {
	private final static String NAMESPACE = "com.jd.hyacinth.dao.SeckillDao.";
	
	//返回本DAO命名空间,并添加statement
	public String getNameSpace(String statement) {
		return NAMESPACE + statement;
	}
		
}