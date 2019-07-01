/**
 * @部门:学工产品事业部
 * @日期：2016-5-6 下午02:13:57 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.sq;

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
 * @模块名称: 毕业还款管理
 * @类功能描述: 毕业还款申请 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-6 下午02:13:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ByhkglSqService extends SuperServiceImpl<ByhkglSqForm, ByhkglSqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private ByhkglSqDao dao = new ByhkglSqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	
	/**
	 * 
	 * @描述:保存增加结果
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-9 下午04:29:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSqjg(ByhkglSqForm model, User user) throws Exception {
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
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "zxdk_byhkgl_byhksh.do", "zxdk_byhkgl_byhksq.do");
			}
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @描述:保存修改结果
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-9 下午04:29:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSqjgUpdate(ByhkglSqForm model, User user) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "zxdk_byhkgl_byhksh.do", "zxdk_byhkgl_byhksq.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @描述: 展期原因LIST
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-9 下午03:01:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getZqyyList() {
		return dao.getZqyyList();
	}
	
	/**
	 * 
	 * @描述: 应还金额
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-9 下午03:51:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String yhjeCx(String xh) {
		return dao.yhjeCx(xh);
	}
	
	/**
	 * 
	 * @描述:提交
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-9 下午06:19:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitByhkglsq(ByhkglSqForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);// 审核中
		String splc = dao.getShlcID();
		model.setSplc(splc);
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(model.getSqid(), splc, model.getXh(), "zxdk_byhkgl_byhksh.do", "zxdk_byhkgl_byhksq.do");
		}
		
		return result;	
	}
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-9 下午06:35:42
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
	 * @描述: 获取zqyymc
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-10 下午01:44:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getZqyymc(String xh) {
		return dao.getZqyymc(xh);
	}
}
