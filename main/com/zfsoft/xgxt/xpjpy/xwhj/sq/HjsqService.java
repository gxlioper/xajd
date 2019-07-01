/**
 * @部门:学工产品事业部
 * @日期：2016-7-21 下午03:45:27 
 */  
package com.zfsoft.xgxt.xpjpy.xwhj.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优-获奖信息管理
 * @类功能描述: 获奖申请
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-7-21 下午03:45:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjsqService extends SuperServiceImpl<HjsqForm, HjsqDao> {
	
	private ShlcInterface shlc = new CommShlcImpl();
	private HjsqDao dao = new HjsqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	/**
	 * 
	 * @描述: 动态取值(奖项名次)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午02:02:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxlbdm
	 * @param jsfs
	 * @param jxdjdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJxmcList(String jxlbdm, String jsfs, String jxdjdm) {
		return dao.getJxmcList(jxlbdm, jsfs, jxdjdm);	
	}
	
	/**
	 * 
	 * @描述: 动态取值(金额)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午04:20:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jxlbdm
	 * @param jsfs
	 * @param jxdjdm
	 * @param jxmcdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getJe(String jxlbdm, String jsfs, String jxdjdm, String jxmcdm) {
		return dao.getJe(jxlbdm, jsfs, jxdjdm, jxmcdm);
	}
	
	/**
	 * 
	 * @描述: 保存(增加)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午04:40:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSqjg(HjsqForm model, User user) throws Exception {
		
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		model.setXh(model.getXh());
		String splc = dao.getShlcID();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "pjpy_hjgl_sh.do", "pjpy_hjgl_sq.do");
			}
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @描述: 保存(修改)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午04:44:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSqjgUpdate(HjsqForm model, User user) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "pjpy_hjgl_sh.do", "pjpy_hjgl_sq.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @描述: 撤销
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午05:36:37
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
	 * 
	 * @描述: 提交
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-22 下午05:50:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitHjsq(HjsqForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);// 审核中
		String splc = dao.getShlcID();
		model.setSplc(splc);
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "pjpy_hjgl_sh.do", "pjpy_hjgl_sq.do");
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @描述: 重复验证
	 * @作者：沈晓波[工号：1123]
	 * @日期：2016-7-26 上午11:23:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param type
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByHjsq(HjsqForm model,String type) throws Exception {
		
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {
			String num = dao.isExistByHjsqSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.isExistByHjsqUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}
	
		return flag;	
	}
	
}
