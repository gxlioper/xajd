/**
 * @部门:学工产品事业部
 * @日期：2017-7-13 上午09:20:47 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxzhcp.zcwh;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zfsoft.xgxt.comm.task.TaskHandler;
import com.zfsoft.xgxt.xpjpy.wdpj.CheckConditionsTask;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优
 * @类功能描述: 计算综测排名
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2017-7-13 上午09:20:47 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ComputeZcpm implements Runnable {
	
	private String xn;
	private String xydm;

	private Log logger = LogFactory.getLog(ComputeZcpm.class);
	
	public ComputeZcpm(String xn,String xydm){
		this.xn = xn;
		this.xydm = xydm;
	}
	
	public void run() {
		ZcwhDao zcwhDao = new ZcwhDao();
		CsszDao csszDao = new CsszDao();
		try {
			CsszForm csszForm = csszDao.getModel();
			/*计算排名*/
			zcwhDao.computeZcpm(xn,xydm);
			
			logger.debug("综测分及排名计算完成（班级代码"+xydm+"）：......");
			
			/*检测评奖条件*/
			String[] xydmArr = xydm.split(",");
			List<HashMap<String,String>> cpmdList = zcwhDao.getCpmdList(xydmArr, csszForm.getXn());
			if (cpmdList != null && !cpmdList.isEmpty()){
				CheckConditionsTask task = new CheckConditionsTask(cpmdList);
				TaskHandler handler = TaskHandler.getInstance();
				handler.startup("pjpy");
				handler.addTask("pjpy", xydm, task);
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.debug("计算综测分及排名失败（班级代码"+xydm+"）：......");
		}
	}
}
