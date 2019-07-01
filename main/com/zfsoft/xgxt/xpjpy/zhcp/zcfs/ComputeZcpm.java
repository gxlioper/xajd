/**
 * @部门:学工产品事业部
 * @日期：2013-7-26 下午02:28:09 
 */  
package com.zfsoft.xgxt.xpjpy.zhcp.zcfs;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xgxt.action.Base;

import com.zfsoft.xgxt.comm.task.TaskHandler;
import com.zfsoft.xgxt.xpjpy.cpmd.CpmdDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszDao;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xpjpy.wdpj.CheckConditionsTask;
import com.zfsoft.xgxt.xpjpy.zhcp.zcxm.ZcxmService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述:计算综测分
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-7-26 下午02:28:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ComputeZcpm implements Runnable {

	private String xn;
	private String xq;
	private String bjdm;
	private Log logger = LogFactory.getLog(ComputeZcpm.class);
	
	
	public ComputeZcpm(String xn , String xq ,String bjdm){
		this.xn = xn;
		this.xq = xq;
		this.bjdm = bjdm;
	}
	
	/*
	      描述: @see java.lang.Runnable#run()
	 */

	public void run() {
		ZcfsDao zcfDao = new ZcfsDao();
		CsszDao csszDao = new CsszDao();
		
		try {
			CsszModel csszModel = csszDao.getModel();

			logger.debug("开始计算综测分及排名（班级代码"+bjdm+"）：......");
			//分年级设比例计算
			if (ZcxmService.XMJB_NJ.equals(csszModel.getZcxmjb())){
				zcfDao.computeZcpmByNj(xn, xq, bjdm);
			} else if (ZcxmService.XMJB_YX.equals(csszModel.getZcxmjb())){
				//分学院设比例计算
				zcfDao.computeZcpmByXy(xn, xq, bjdm);
			} else {
				//不分年级 、学院，全体比例一致
				zcfDao.computeZcpm(xn, xq, bjdm);
			}
			
			//计算学年综测
			if(CsszService.getZczq()){
				zcfDao.computeXnpm(xn,bjdm);
				logger.debug("学年分及排名计算完成（班级代码"+bjdm+"）：......");
			}
			
			logger.debug("综测分及排名计算完成（班级代码"+bjdm+"）：......");
			
			// 计算平均分名次（浙江机电职业技术学院）
			if("12861".equals(Base.xxdm)){
				zcfDao.computeZYPDB_ZJJDZYJSXY(xn, xq, bjdm);
				logger.debug("浙江机电职业技术学院:平均分名次计算完成（班级代码"+bjdm+"）：......");
			}
			
			//检测评奖条件
			CpmdDao cpmdDao = new CpmdDao();
			String[] bjdmArr = bjdm.split(",");
			List<HashMap<String,String>> cpmdList = cpmdDao.getCpmdList(bjdmArr, csszModel.getXn(), csszModel.getXq());
			if (cpmdList != null && !cpmdList.isEmpty()){
				CheckConditionsTask task = new CheckConditionsTask(cpmdList);
				TaskHandler handler = TaskHandler.getInstance();
				handler.startup("pjpy");
				handler.addTask("pjpy", bjdm, task);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("计算综测分及排名失败（班级代码"+bjdm+"）：......");
		}
	}

}
