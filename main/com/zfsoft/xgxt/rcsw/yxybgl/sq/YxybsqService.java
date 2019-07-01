/**
 * @部门:学工产品事业部
 * @日期：2016-3-23 上午11:41:42 
 */  
package com.zfsoft.xgxt.rcsw.yxybgl.sq;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-23 上午11:41:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YxybsqService extends SuperServiceImpl<YxybsqForm, YxybsqDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private YxybsqDao dao = new YxybsqDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	
	/** 
	 * @描述:获取院系列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-23 下午01:49:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXylist(User user){
		return dao.getXylist(user);
	}
	
	/** 
	 * @描述:得到学期名称
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-23 下午02:01:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xqdm
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	/** 
	 * @描述:根据学年得到月份列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-23 下午03:35:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws SQLException
	 * List<String> 返回类型 
	 * @throws 
	 */
	public List<String> getYueFenByXn() throws SQLException {
		return dao.getYueFenByXn();
	}
	
	/** 
	 * @描述:判断是否有数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-23 下午03:37:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecord(YxybsqForm form){
		return dao.isHaveRecord(form);
	}
	
	/** 
	 * @描述:保存增加结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-23 下午03:56:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSqjg(YxybsqForm model, User user) throws Exception {
		String sqid = UniqID.getInstance().getUniqIDHash();
		model.setSqid(sqid);
		String splc = dao.getShlcID();
		model.setTxsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplc(splc);
		if(model.getType().equals("save")){
			model.setShzt(Constants.YW_WTJ);
		}else{
			model.setShzt(Constants.YW_SHZ);
		}
		boolean flag = dao.runInsert(model);
		if(model.getType().equals("submit")){
			if (flag) {
				flag = shlc.runSubmit(sqid, splc, model.getXydm(), "rcsw_yxybgl_sh.do", "rcsw_yxybgl_sq.do");
			}
		}
		return flag;
	}
	
	/** 
	 * @描述:保存修改结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-23 下午03:59:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveSqjgUpdate(YxybsqForm model, User user) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXydm(), "rcsw_yxybgl_sh.do", "rcsw_yxybgl_sq.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;
	}
	
	/** 
	 * @描述:获取申请信息
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-23 下午04:24:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public Map<String,String> getSqxx(YxybsqForm form){
		return dao.getSqxx(form);
	}
	
	/** 
	 * @描述:提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-23 下午05:07:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean submitSqjg(YxybsqForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);// 审核中
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			String splc = dao.getShlcID();
			model.setSplc(splc);
			result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXydm(), "rcsw_yxybgl_sh.do", "rcsw_yxybgl_sq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * 撤销
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	
}
