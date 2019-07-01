/**
 * @部门:学工产品事业部
 * @日期：2013-12-17 下午02:50:05
 */
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjtx;

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
 * @模块名称: 火车乘车区间填写管理模块
 * @类功能描述: TODO(火车乘车区间填写) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-26 上午09:42:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcccqjtxService extends
		SuperServiceImpl<HcccqjtxForm, HcccqjtxDao> {

	private HcccqjtxDao dao = new HcccqjtxDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static String _BCZSCID="-1";

	public HcccqjtxService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:TODO(增加火车乘车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午04:21:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveHcccqjtx(HcccqjtxForm model) throws Exception {
		
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
			result = shlc.runSubmit(model.getCcqjtxid(),splc,model.getXh(),"rcsw_hcyhk_hcccqjsh.do","rcsw_hcyhk_hcccqjtx.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(修改火车乘车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午04:13:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateHcccqjtx(HcccqjtxForm model) throws Exception {
		
		if(model.getType().equals(SUBMIT)&&!Constants.YW_YTH.equals(model.getShzt())){
			// 获取审批流程
			model.setSplc(dao.getShlcID());
		}
		
		if(model.getType().equals(SUBMIT)){
			model.setShzt(Constants.YW_SHZ);//审核中
		}else{
			//model.setShzt(Constants.YW_WTJ);//未提交
		}
		
		boolean insertResult = super.runUpdate(model);
		boolean result = false;
		if (insertResult && SUBMIT.equalsIgnoreCase(model.getType())) {
			//保存审核流程
			result = shlc.runSubmit(model.getCcqjtxid(), model.getSplc(),model.getXh(),"rcsw_hcyhk_hcccqjsh.do","rcsw_hcyhk_hcccqjtx.do");
			return result;
		}else{
			return insertResult;
		}
		
	}
	
	/**
	 * 
	 * @描述:TODO(删除火车乘车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午04:21:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] deleteHcccqjtx(String[] ids) throws Exception{
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
				HashMap<String, String> hm=dao.getHcccqjtx(str);
				noDel.append(hm.get("xh"));
				noDel.append("&nbsp;");
				noDel.append(hm.get("xm"));
				noDel.append(",</br>");
				isHaveNoId=true;
			}
		}
		int i=delId.size()>0?hcccqjtxDelete(delId.toArray(new String[]{})):0;
		String str=noDel.toString();
		//去除最后多余逗号
		str=isHaveNoId?str:_BCZSCID;
		return new String[]{String.valueOf(i),str};
	}
	
	private int hcccqjtxDelete(String[] ids) throws Exception {
		for(String id:ids){
			shlc.deleteShjl(id);
		}
		return runDelete(ids);
	}
	
	/**
	 * 
	 * @描述:TODO(提交火车车程区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:11:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitHcccqjtx(HcccqjtxForm model) throws Exception {
		
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			// 获取审批流程
			model.setSplc(dao.getShlcID());
		}
		
		model.setShzt(Constants.YW_SHZ);
		boolean resultHcccqjtx = dao.updateHcccqjtx(model);
		boolean result = false;
		if(resultHcccqjtx){
			//保存审核流程
			result = shlc.runSubmit(model.getCcqjtxid(), model.getSplc(),model.getXh(),"rcsw_hcyhk_hcccqjsh.do","rcsw_hcyhk_hcccqjtx.do");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(更新火车乘车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-17 下午06:27:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateHcccqjtxzt(HcccqjtxForm model) throws Exception {
			boolean resultBbsq = dao.updateHcccqjtx(model);
			return resultBbsq;
	}
	
	/**
	 * 
	 * @描述:TODO(获取火车乘车区间填写详细信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 下午04:46:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getHcccqjtxInfo(HcccqjtxForm model) {
		if (StringUtil.isNull(model.getCcqjtxid())) {
			logger.error("申请ID不能为空！");
			throw new NullPointerException();
		}
		return dao.getHcccqjtxInfo(model);
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
	 * @描述:TODO(按照学号, 学年,学期判断火车乘车区间填写表中是否已经存在该学生)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-13 下午05:23:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistByHcccqjtx(HcccqjtxForm model,String type)
	throws Exception {
		boolean flag = false;
		if("save".equalsIgnoreCase(type)) {
			String num = dao.checkExistForHcccqjtxSave(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}else{
			String num = dao.checkExistForHcccqjtxUpdate(model);
			if (!"0".equalsIgnoreCase(num)) {
				flag = true;
			}
		}
	
		return flag;
	}
	
	/**
	 * 
	 * @描述:TODO(查询火车车程)
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
	 * @描述:TODO(获取基础设置默认的乘车起点站)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-2-19 上午11:29:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXxccqdz() {
		return dao.getXxccqdz();
	}
	
	
	/**
	 * 
	 * @描述:根据学号获取最新乘车区间结果
	 * @作者：cq [工号：785]
	 * @日期：2014-8-28 上午11:58:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String>getHcccqj(String xh){
		return dao.getHcccqj(xh);
	}
	
	/*
	 * 取学期名称
	 */
	public String getXqmc(String xq){
		return dao.getXqmc(xq);
	}
}
