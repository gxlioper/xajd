/**
 * @部门:学工产品事业部
 * @日期：2016-3-17 下午01:38:53 
 */  
package com.zfsoft.xgxt.rcsw.tsqktbgl.sq;

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
 * @时间： 2016-3-17 下午01:38:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TsqktbService extends SuperServiceImpl<TsqktbForm, TsqktbDao>{
	private ShlcInterface shlc = new CommShlcImpl();
	private TsqktbDao dao = new TsqktbDao();
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	
	public String getXqmc(String xqdm){
		return dao.getXqmc(xqdm);
	}
	
	/** 
	 * @描述:得到学情分类列表
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-17 下午02:49:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getXqflList(){
		return dao.getXqflList();
	}
	
	/** 
	 * @描述:是否有记录
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-17 下午03:38:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isHaveRecord(TsqktbForm form){
		return dao.isHaveRecord(form);
	}
	
	/** 
	 * @描述:保存增加结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-17 下午03:52:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveTbjg(TsqktbForm model, User user) throws Exception {
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
				flag = shlc.runSubmit(sqid, splc, model.getXh(), "rcsw_tsqktbgl_sh.do", "rcsw_tsqktbgl_tb.do");
			}
		}
		return flag;
	}
	
	/** 
	 * @描述:保存修改结果
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-17 下午03:59:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean saveTbjgUpdate(TsqktbForm model, User user) throws Exception {
		boolean result = false;
		// 如果提交，插入审核状态
		if ("updatesubmit".equalsIgnoreCase(model.getType())) {
			model.setShzt(Constants.YW_SHZ);// 审核中
			result = runUpdate(model);
			ShlcInterface shlc = new CommShlcImpl();
			if (result) {
				String splc = dao.getShlcID();
				model.setSplc(splc);
				result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "rcsw_tsqktbgl_sh.do", "rcsw_tsqktbgl_tb.do");
			}
		} else {
			result = runUpdate(model);
		}
		
		return result;
	}
	
	/** 
	 * @描述:提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-18 上午09:13:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean submitTbjg(TsqktbForm model) throws Exception {
		boolean result = false;
		model.setShzt(Constants.YW_SHZ);
		String splc = dao.getShlcID();
		model.setSplc(splc);// 审核中
		result = runUpdate(model);
		ShlcInterface shlc = new CommShlcImpl();
		if (result) {
			result = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "rcsw_tsqktbgl_sh.do", "rcsw_tsqktbgl_tb.do");
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
	
	/**
	 * 
	 * 获取通报信息
	 */
	public Map<String, String> getTbxx(TsqktbForm form){
		return dao.getTbxx(form);
	}
	
}
