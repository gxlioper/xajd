/**
 * @部门:学工产品事业部
 * @日期：2013-12-17 下午02:50:05
 */
package com.zfsoft.xgxt.rcsw.xszbb.xszbbsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh.XszbblxwhDao;
import com.zfsoft.xgxt.rcsw.xszbb.xszbblxwh.XszbblxwhForm;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办申请管理模块
 * @类功能描述: TODO(学生证补办申请) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-17 下午02:50:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XszbbsqService extends
		SuperServiceImpl<XszbbsqForm, XszbbsqDao> {

	private XszbbsqDao dao = new XszbbsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public XszbbsqService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:TODO(获取补办类型维护集合)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午02:50:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getBblxwhList() {
		return dao.getBblxwhList();
	}
	

	/**
	 * 
	 * @描述:TODO(增加学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午04:21:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveXszbbsq(XszbbsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		// 获取审批流程
		XszbblxwhForm bblx = new XszbblxwhForm();
		bblx.setXszbblxdm(model.getXszbblxdm());
		
		String splc = new XszbblxwhDao().getModel(bblx).getShlc();//dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getBbsqid(), splc,model.getXh(),"rcsw_xszbb_bbsh.do","rcsw_xszbb_bbsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(修改学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午04:21:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXszbbsq(XszbbsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取审批流程
			XszbblxwhForm bblx = new XszbblxwhForm();
			bblx.setXszbblxdm(model.getXszbblxdm());
			String splc = new XszbblxwhDao().getModel(bblx).getShlc();//dao.getShlcID();
			model.setSplc(splc);
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult) {
			shlc.deleteShjl(model.getBbsqid());
			if("update".equalsIgnoreCase(model.getType())){
				result = true;
			}else{
				result = shlc.runSubmit(model.getBbsqid(), model.getSplc(),model.getXh(),"rcsw_xszbb_bbsh.do","rcsw_xszbb_bbsq.do");
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(删除学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:11:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteXszbbsq(String[] ids) throws Exception{
		List<String> delId=new ArrayList<String>();//可删除的id集合
		StringBuffer noDel = new StringBuffer();
		boolean isHaveNoId = false;
		if(null==ids||ids.length<=0){
			return null;
		}
		for(String str:ids){
			if(dao.isCanDel(str)){
				delId.add(str);//记录删除id
			}else{
				HashMap<String, String> hm=dao.getBbsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?bbsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	/**
	 * 
	 * @描述:TODO(删除学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:32:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int bbsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @描述:TODO(提交学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:11:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBbsq(XszbbsqForm model) throws Exception {
		boolean resultBbsq = dao.updateBbsq(model);
		boolean result = false;
		if(resultBbsq){
		//保存审核流程
		result = shlc.runSubmit(model.getBbsqid(), model.getSplc(),model.getXh(),"rcsw_xszbb_bbsh.do","rcsw_xszbb_bbsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(更新补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:27:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBbsq(XszbbsqForm model) throws Exception {
			boolean resultBbsq = dao.updateBbsq(model);
			return resultBbsq;
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 下午04:46:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXszbbsqInfo(XszbbsqForm model) {
		if (StringUtil.isNull(model.getBbsqid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getXszbbsqInfo(model);
	}
	

	/**
	 * 
	 * @描述:只有刚提交并且第一级未审核的前提下，申请人可以撤销
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-3 上午09:18:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid,lcid);
	}
	
	/**
	 * 
	 * @描述:TODO(更新学生证补办申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午09:30:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean allowUpdateSetting() {

		try {
			return dao.getDshCount() == 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @描述:TODO(按照学号判断该学生证补办申请是否已经存在)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-26 上午08:39:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByXszbbsq(XszbbsqForm model)
	throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
	
	/** 
	 * @描述:获取火车乘车区间数据(这里用一句话描述这个方法的作用)
	 * @作者：柳俊[工号：1282]
	 * @日期：2017-5-23 下午05:07:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getHcccqj(String xh){
		return dao.getHcccqj(xh);
	}
  
	
}
