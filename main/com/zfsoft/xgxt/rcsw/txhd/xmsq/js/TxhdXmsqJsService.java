/**
 * @部门:学工产品事业部
 * @日期：2014-6-25 上午10:13:35 
 */
package com.zfsoft.xgxt.rcsw.txhd.xmsq.js;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszDao;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务——团学活动
 * @类功能描述: 团学活动Service
 * @作者： cq [工号:785]
 * @时间： 2014-6-25 上午10:13:35
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class TxhdXmsqJsService extends
		SuperServiceImpl<TxhdXmsqJsForm, TxhdXmsqJsDao> implements Constants{
	
	private ShlcInterface shlc = new CommShlcImpl();
	
	
	/**
	 * 
	 * @描述:保存项目申请
	 * @作者：cq [工号：785]
	 * @日期：2014-6-25 下午02:08:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param xmdmArray
	 * @param ylzd1
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXmsq(TxhdXmsqJsForm form, String[] xmdmArray, User user) throws Exception {
		
		if (xmdmArray == null || xmdmArray.length == 0) {
			logger.error("未选择项目！");
			throw new NullPointerException();
		}

		form.setSqsj(GetTime.getTimeByFormat("YYYY-MM-DD hh24:mi:ss"));
		if (form.getType().equals("submit")) {
			form.setShzt(YW_SHZ);
		} else {
			form.setShzt(YW_WTJ);
		}

		for (int i = 0, n = xmdmArray.length; i < n; i++) {
			
			String sqid = UniqID.getInstance().getUniqIDHash();
			
			form.setXmdm(xmdmArray[i]);
			// 加载项目信息
			TxhdXmszDao txhdXmszDao = new TxhdXmszDao();
			TxhdXmszForm txhdXmszForm = new TxhdXmszForm();
			txhdXmszForm.setXmdm(xmdmArray[i]);
			TxhdXmszForm xmInfo = txhdXmszDao.getModel(txhdXmszForm);
			
			if (null != xmInfo && !StringUtil.isNull(xmInfo.getShlc())){
				String shlc = xmInfo.getShlc();
				form.setSplc(shlc);
				form.setXn(Base.currXn);
				form.setXq(Base.currXq);
				form.setSqid(sqid);
				form.setSqr(user.getUserName());
			}
			//保存申请
			boolean isSuccess = dao.runInsert(form);
			
			if (isSuccess){
				if(form.getType().equals("submit")){
					//待办工作
					isSuccess = shlc.runSubmit(sqid, form.getSplc(), form.getXh(), "rcsw_txhd_xmsh.do", "rcsw_txhd_xmsq_js.do");
				}
			}
		}
		
		return true;
	}

	/** 
	 * @描述: 选择项目申请页
	 * @作者：cq [工号：785]
	 * @日期：2014-6-25 下午02:55:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	
	public List<HashMap<String,String>> getXmsqInfoList(String xh){
		
		return dao.getXmsqInfoList(xh);
	}

	/** 
	 * @描述: 保存项目修改申请
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 上午11:25:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean bcxgXmsq(TxhdXmsqJsForm model)throws Exception{
		
		if(model.getType().equals("submit")){
			model.setShzt(YW_SHZ);
		}else{
			model.setShzt(YW_WTJ);
		}
		
		boolean isSuccess = dao.runUpdate(model);
		if (isSuccess){
			if(model.getType().equals("submit")){
				//待办工作
				isSuccess = shlc.runSubmit(model.getSqid(), model.getSplc(), model.getXh(), "rcsw_txhd_xmsq_js.do", "rcsw_txhd_xmsh.do");
			}
		}
		return isSuccess;
	}
	
	
	/**
	 * 
	 * @描述:提交
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 上午11:59:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pkValue
	 * @param lcid
	 * @param xh
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitRecord(String pkValue,String lcid,String xh) throws Exception {
		return shlc.runSubmit(pkValue, lcid, xh, "rcsw_txhd_xmsq_js.do", "rcsw_txhd_xmsh.do");
	}
	
	
	/**
	 * 
	 * @描述:更新
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 下午12:20:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateModel(TxhdXmsqJsForm model) throws Exception {
		return super.runUpdate(model);
	}

	
	
	/** 
	 * @描述:撤销
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 下午01:28:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @param lcid
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancleRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	
	/**
	 * 
	 * @描述:根据项目代码查询剩余名额
	 * @作者：cq [工号：785]
	 * @日期：2014-6-27 下午02:54:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public boolean getSymeForXmdm(String xmdmArray, String type) throws Exception{
		
		if (xmdmArray == null || xmdmArray == "") {
			logger.error("未选择项目！");
			throw new NullPointerException();
		}
		
		if(type.equalsIgnoreCase("submit")){
			
			String syme = dao.getSymeForXmdm(xmdmArray);
			if(!StringUtils.isBlank(syme)&& Integer.valueOf(syme)<= 0){
				return false;
			}
		}
		
		return true;
	}

}
