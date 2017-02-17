/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.jd.hyacinth.service.impl;

import javax.annotation.Resource;

import org.hyacinth.examples.annotation.Config;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jd.hyacinth.domain.Seckill;
import com.jd.hyacinth.dao.base.BaseDao;
import com.jd.hyacinth.dao.SeckillDao;
import com.jd.hyacinth.service.base.BaseServiceImpl;
import com.jd.hyacinth.service.SeckillService;

/**
 * SeckillService 实现类
 * @author J-ONE
 * @since 2016-10-20
 */
@Config(value="test")
@Service("seckillService")
public class SeckillServiceImpl extends BaseServiceImpl<Seckill,Long> implements SeckillService {
	
	@Resource private SeckillDao seckillDao;
	
	public BaseDao<Seckill,Long> getDao() {
		return seckillDao;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertEntryCreateId(Seckill seckill) {
		return super.insertEntryCreateId(seckill);
	}
}