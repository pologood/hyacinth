/*
 * Copyright (c) 2016 www.jd.com All rights reserved.
 * 本软件源代码版权归京东成都云平台所有,未经许可不得任意复制与传播.
 */
package com.jd.hyacinth.web.controller;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jd.hyacinth.web.CustomDateEditor;

import org.springframework.web.context.request.WebRequest;
import org.springframework.stereotype.Controller;

import com.jd.hyacinth.domain.Seckill;
import com.jd.hyacinth.domain.common.Message;
import com.jd.hyacinth.domain.common.Page;
import com.jd.hyacinth.service.SeckillService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *seckill controller层
 * @author J-ONE
 * @since 2016-10-20
 */
@Controller
@RequestMapping(value = "/seckill")
public class SeckillController{
	private static final Logger LOGGER = LoggerFactory.getLogger(SeckillController.class);
	@Resource private SeckillService seckillService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
	}
	
	/**
	 * 列表展示
	 * @param seckill 实体对象
	 * @param page 分页对象
	 * @return
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Seckill seckill,Page<Seckill> page,Model view) throws Exception{
		try {
			view.addAttribute("seckill",seckill);
			view.addAttribute("page",seckillService.selectPage(seckill,page));			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}	
		return "seckill/list";
	}
	
	/**
	 * 响应新增(修改)页面
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String edit(@PathVariable Long id,Model view) throws Exception{
		try {
			if(id != null && id > 0) {
				Seckill seckill = seckillService.selectEntry(id);
				if(seckill == null) {
//					return toJSON(Message.failure("您要修改的数据不存在或者已被删除!"));
					return null;
				}
				view.addAttribute("seckill",seckill);
			}			
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "seckill/edit";
	}
	
	/**
	 * 通过编号删除对象
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public @ResponseBody Message del(@PathVariable Long id,Model view) throws Exception{
    	Message msg = null;
    	try {
			int res = seckillService.deleteByKey(id);
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure();
		}finally{
		}

		return msg;
	}
	
	/**
	 * 通过编号查看对象
	 * @param id 对象编号
	 * @return
	 */
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String view(@PathVariable Long id,Model view) throws Exception{
		try {
			Seckill seckill = seckillService.selectEntry(id);
			if(seckill == null) {
				return null;
			}
			view.addAttribute("seckill",seckill);
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			throw e;
		}finally{
		}

		return "seckill/view";
	}
	
	/**
	 * 保存方法
	 * @param seckill 实体对象
	 * @return
	 */
	@RequestMapping(value="/save",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json")
	public @ResponseBody Message save(Seckill seckill,Model view) throws Exception{
    	Message msg= null;
    	try {
			int res = seckillService.saveOrUpdate(seckill);
			msg  = res > 0 ? Message.success() : Message.failure();
		} catch (Exception e) {
			LOGGER.error("失败:"+e.getMessage(),e);
			msg = Message.failure();
		}finally{
		}
		return msg;
	}
	
	@RequestMapping(value = "/json/{id}")
	@ResponseBody
	public Seckill json(@PathVariable Long id){
		Seckill seckill = seckillService.selectEntry(id);
		return seckill;
	}
	
}