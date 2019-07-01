/**
 * @部门:学工产品事业部
 * @日期：2013-7-26 下午02:28:09 
 */  
package com.zfsoft.xgxt.xpjpy.bfjs.fswh;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述:计算竞赛分
 * @作者： xiaxia [工号：1104]
 * @时间： 2016-4-19 下午02:28:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ComputeJspm implements Runnable {

	private String xn;
	private String xq;
	private String bjdm;
	private Log logger = LogFactory.getLog(ComputeJspm.class);
	
	
	public ComputeJspm(String xn , String xq ,String bjdm){
		this.xn = xn;
		this.xq = xq;
		this.bjdm = bjdm;
	}
	
	/*
	      描述: @see java.lang.Runnable#run()
	 */

	public void run() {
		BfjsFswhDao jsfDao = new BfjsFswhDao();
		
		try {

			logger.debug("开始计算竞赛分及排名（班级代码"+bjdm+"）：......");
			
//				//分学院计算
				jsfDao.computeJspmByXy(xn, xq, bjdm);
		
			logger.debug("竞赛分及排名计算完成（班级代码"+bjdm+"）：......");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("计算竞赛分及排名失败（班级代码"+bjdm+"）：......");
		}
	}

}
