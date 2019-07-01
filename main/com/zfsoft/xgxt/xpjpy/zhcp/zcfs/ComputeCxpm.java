/**
 * @部门:学工产品事业部
 * @日期：2013-7-26 下午02:28:09 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述:计算综测分
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-26 下午02:28:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ComputeCxpm implements Runnable {

	private String xn;
	private String xq;
	private Log logger = LogFactory.getLog(ComputeCxpm.class);
	
	
	public ComputeCxpm(String xn , String xq){
		this.xn = xn;
		this.xq = xq;
	}
	
	/*
	      描述: @see java.lang.Runnable#run()
	 */

	public void run() {
		ZcfsDao zcfDao = new ZcfsDao();
		
		try {
			logger.debug("开始计算排名：......");
			
			zcfDao.cxJspm(xn, xq);
			
			logger.debug("计算排名完成：......");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("计算排名失败：......");
		}
	}

}
