/**
 * @部门:学工产品事业部
 * @日期：2016-5-9 上午10:45:19 
 */  
package com.zfsoft.xgxt.rcsw.hjjygl.hjjysq;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-5-9 上午10:45:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjjySqService extends SuperServiceImpl<HjjySqForm, HjjySqDao>{
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mm:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	HjjySqDao dao = new HjjySqDao();

	

	/**
	 * 
	 * @描述:保存申请
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-5-9 上午11:44:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveHjjySq(HjjySqForm model) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		String splc = dao.getShlcID();
		model.setSplc(splc);
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
				result = shlc.runSubmit(sqid, splc, model.getXh(), "rcsw_hjjy_jysh.do", "rcsw_hjjy_jysq.do");
			}
		}
		return result;
	
	}
	

	/**
	 * 
	 * @描述:修改申请保存
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-5-9 上午11:44:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveEditHjjySq( HjjySqForm model) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("submit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			String splc = dao.getShlcID();
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "rcsw_hjjy_jysh.do", "rcsw_hjjy_jysq.do");
			}
		} else {
			result = runUpdate(model);
		}
		return result;
	
	}
    /**
     * 
     * @描述:提交申请
     * @作者：xiaxia[工号：1104]
     * @日期：2016-5-9 上午11:44:51
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param model
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
	public boolean submitHjjySq(HjjySqForm model) throws Exception {
		boolean result = false;
			model.setShzt(Constants.YW_SHZ);// 审核中
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				result = shlc.runSubmit(model.getSqid(), splc, model.getSqid(), "rcsw_hjjy_jysh.do", "rcsw_hjjy_jysq.do");
			}
			return result;
	}

	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-05-09 下午03:02:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}

	/**
	 * 判断是否有申请记录
	 */
	public boolean isHaveGhJl(HjjySqForm model) throws Exception {
		return dao.isHaveGhJl(model);
	}
	public HashMap<String, String> getJyxx(String sqid) {
		return dao.getJyxx(sqid);
	}


		
	}
