/**
 * @部门:学工产品事业部
 * @日期：2015-3-26 上午09:35:45 
 */  
package com.zfsoft.xgxt.rcsw.bxgl.bxbxsq;

import java.util.HashMap;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.bxgl.cssz.CsszDao;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-3-26 上午09:35:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BxbxSqService extends SuperServiceImpl<BxbxSqForm, BxbxSqDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private static final String SJZT = "0";// 审核状态不为1的数据状态为0
	private ShlcInterface shlc = new CommShlcImpl();
	private CsszDao csszDao = new CsszDao();
	/**
	 * 
	 * @描述:获取保险报销详细信息
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-27 下午03:15:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getBxsqxx(String sqid){
		return dao.getBxsqxx(sqid);
		
		
	}
	/**
	 * 
	 * @描述:保险报销申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-26 下午03:48:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param qqxxList
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveBxbxsq(BxbxSqForm model) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		String splc = csszDao.getModel().getSplc();
		model.setSqid(sqid);
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		// 有审批流的情况设定初始值
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		// 保存申请信息
		boolean result = dao.runInsert(model);
		// 保存审核信息
		if (!"save".equals(model.getType())) {
			if (result) {
				result = shlc.runSubmit(sqid, splc, model.getXh(), "rcsw_bxgl_bxbxsh.do", "rcsw_bxgl_bxbxsq.do");
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:保险报销申请修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-3-27 下午02:59:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBxbxsq(BxbxSqForm model) throws Exception {
		String splc = csszDao.getModel().getSplc();
		model.setSplc(splc);
		// 有审批流的情况设定初始值
		if ("submit".equals(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
		} else {
			model.setShzt(Constants.YW_WTJ);// 未提交
		}
		// 保存申请信息
		boolean result = dao.runUpdate(model);
		// 保存审核信息
		if (!"save".equals(model.getType())) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "rcsw_bxgl_bxbxsh.do", "rcsw_bxgl_bxbxsq.do");
		}
		return result;
	}
	/**
	 * 
	 * @描述:申请提交
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-30 下午04:41:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBxbxsq(BxbxSqForm model) throws Exception {
		boolean result =true;
		String splc = csszDao.getModel().getSplc();
		model.setSplc(splc);
		model.setShzt(Constants.YW_SHZ);// 审核中
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		// 保存申请信息
		result = dao.runUpdate(model);
		// 保存审核信息
		if (!"save".equals(model.getType())) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "rcsw_bxgl_bxbxsh.do", "rcsw_bxgl_bxbxsq.do");
		}
		return result;
	}
	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-30 下午04:55:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @描述：校验门诊报销金额
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年3月28日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型
	 */
	public boolean checkMzje(String bxje,String csfysj,String xh,String id){
		//在报销金额、产生费用时间非必填的情况下，该校验无意义
		if(StringUtils.isNull(bxje)||StringUtils.isNull(csfysj)){
			return true;
		}
		return dao.checkMzje(bxje,csfysj,xh,id);
	}
	
	/**
	 * 
	 * @描述: 获取总额
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-11-21 下午07:02:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZe(String xh,String csfysj){
		return dao.getZe(xh,csfysj);
	}
}
