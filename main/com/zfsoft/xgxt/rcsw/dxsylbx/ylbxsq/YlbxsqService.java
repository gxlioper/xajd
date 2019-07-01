/**
 * @部门:学工产品事业部
 * @日期：2013-12-17 下午02:50:05
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生证补办申请管理模块
 * @类功能描述: (学生证补办申请) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-17 下午02:50:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxsqService extends
		SuperServiceImpl<YlbxsqForm, YlbxsqDao> {

	private YlbxsqDao dao = new YlbxsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public YlbxsqService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:(获取补办类型维护集合)
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
	 * @描述:(增加医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 下午03:46:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveYlbxsq(YlbxsqForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			model.setShzt(Constants.YW_WTJ);//未提交
		}
		// 获取审批流程
		String splc = dao.getShlcID();
		model.setSplc(splc);
		boolean insertResult = super.runInsert(model);
		if( SAVE.equalsIgnoreCase(model.getType())){
			return insertResult;
		}
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getYlsqid(),splc,model.getXh(),"rcsw_dxsylbx_ylbxsh.do","rcsw_dxsylbx_ylbxsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 上午08:35:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCzqebzryList() {
		return dao.getCzqebzryList();
	}
	
	/**
	 * 
	 * @描述:(这里用一句话描述这个方法的作用)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 上午08:35:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCbzkList() {
		return dao.getCbzkList();
	}
	
	/**
	 * 
	 * @描述:(当前学期名称)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 下午03:17:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getCurrentXqmc(YlbxsqForm model)throws Exception {
		String xqmc = dao.getCurrentXqmc(model);
		return xqmc;
	}
	
	
	/**
	 * 
	 * @描述:(修改医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-8 下午03:47:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYlbxsq(YlbxsqForm model) throws Exception {
		if(model.getType().equals(SUBMIT)&& !Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID());
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = true;
		if (insertResult && SUBMIT.equals(model.getType())) {
			shlc.deleteShjl(model.getYlsqid());
			result = shlc.runSubmit(model.getYlsqid(), model.getSplc(),model.getXh(),"rcsw_dxsylbx_ylbxsh.do","rcsw_dxsylbx_ylbxsq.do");
		}
		return insertResult && result;
	}
	
	
	
	
	/**
	 * 
	 * @描述:(删除医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 上午08:30:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] delYlbxsq(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getYlbxsq(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?ylbxsqDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	
	/**
	 * 
	 * @描述:(删除医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 上午08:30:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	private int ylbxsqDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @描述:(提交医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 上午08:30:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitYlbxsq(YlbxsqForm model) throws Exception {
		
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取新审批流程
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultYlbxsq = dao.updateYlbxsq(model);
		boolean result = false;
		if(resultYlbxsq){
		//保存审核流程
		result = shlc.runSubmit(model.getYlsqid(), model.getSplc(),model.getXh(),"rcsw_dxsylbx_ylbxsh.do","rcsw_dxsylbx_ylbxsq.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:(更新医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 上午08:31:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelYlbxsq(YlbxsqForm model) throws Exception {
			boolean resultYlbxsq = dao.updateYlbxsq(model);
			return resultYlbxsq;
	}
	
	/**
	 * 
	 * @描述:(获取医疗保险申请信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-13 下午04:36:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYlbxsqInfo(YlbxsqForm model) {
		if (StringUtil.isNull(model.getYlsqid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getYlbxsqInfo(model); 
	}
	

	/**
	 * 
	 * @描述:(只有刚提交并且第一级未审核的前提下，申请人可以撤销)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 上午08:31:53
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
	 * @描述:(更新医疗保险申请信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 上午08:32:28
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
	 * @描述:(按照学号判断该学生该学生医疗保险申请是否已经存在)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-14 上午08:35:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByXszbbsq(YlbxsqForm model)
	throws Exception {
		boolean flag = false;
		String num = dao.checkExistForSave(model);
		if (!"0".equalsIgnoreCase(num)) {
			flag = true;
		}
		return flag;
	}
  
	
}
