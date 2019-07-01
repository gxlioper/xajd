/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 下午04:32:16 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsq;

import java.util.HashMap;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;
import com.zfsoft.xgxt.wjcf.cfsssq.CfsssqDao;
import com.zfsoft.xgxt.wjcf.cfsssq.CfsssqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分申诉申请) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-29 下午01:46:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfjcsqService extends SuperServiceImpl<CfjcsqForm, CfjcsqDao> {
	private CfjcsqDao dao=new CfjcsqDao();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	public static final String BACK = "back";
	
	public CfjcsqService(){
		this.setDao(dao);
	}
	private ShlcInterface shlc = new CommShlcImpl();

	/**
	 * @throws Exception  
	 * @描述:(保存申诉申请)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 下午04:51:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String save(CfjcsqForm model) throws Exception {
		
		
		String jcid = UniqID.getInstance().getUniqIDHash();
		model.setJcid(jcid);
		//获取申诉解除流程
		CfsssqDao sssqDao=new CfsssqDao();
		HashMap<String, String> ssjcsplc=sssqDao.getSsjcsplc();
		String splcid=ssjcsplc.get("jcspl");
		//审批流程为空，提示显示设置审批流程
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		//设置初始审核状态值
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
			model.setSqjg(Constants.WSH);
			if(SAVE.equalsIgnoreCase(model.getType())){
				model.setSqjg(Constants.WSH);  
			}else{
				model.setSqjg(Constants.SHZ); 
			}
		}
		boolean result=super.runInsert(model);
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			result = shlc.runSubmit(jcid, splcid,model.getXh(),"wjcf_cfjcsh.do?method=cxCfjcshList","wjcf_cfjcsq.do?method=cxCfjcsqList");
			if (result) {
				//设置代办信息
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
				dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
				dbcz.setGnmkMc("处分解除审核");
				dbcz.setXmmc("解除审核");
				dbcz.sqPush(jcid, model.getXh(), splcid);*/
			}
		}
		return String.valueOf(result);
	}
	
	@Override
	public int runDelete(String[] values) throws Exception {
		String[] ids=getCancelIds(values);
		if(ids.length==0)
			return 0;
		int result=dao.runDelete(ids);
		for (String id : ids) {
			if(shlc.deleteShjl(id)){
				BaseDbcz dbcz=new BaseDbcz();
				dbcz.setGnmkMc("处分解除审核");
				dbcz.remove(new String[]{id});
			}
		}
		return result;
	}

	/**
	 * @throws Exception  
	 * @描述:(获取可以删除的id)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 下午07:01:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * String[] 返回类型 
	 * @throws 
	 */
	private String[] getCancelIds(String[] values) throws Exception {
		
		return dao.getCancelIds(values);
	}
	
	/**
	 * 
	 * @描述:TODO(取消记录的时候判断第一级是否已经审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午03:05:02
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
	 * @描述:TODO(更新记录)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午03:05:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateRecord(CfjcsqForm model) throws Exception{
		return  dao.updateBackRecord(model);
	}
	
	/**
	 * 
	 * @描述:TODO(提交功能)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午03:05:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitCfjcsq(CfjcsqForm model) throws Exception{
		boolean resultCfsq = dao.updateBackRecord(model);
		boolean result = false;
		if(resultCfsq ){
			//保存审核流程
			result = shlc.runSubmit(model.getJcid(), model.getSplcid(),model.getXh(),"wjcf_cfjcsh.do?method=cxCfjcshList","wjcf_cfjcsq.do?method=cxCfjcsqList");
			
			if (result) {
				//设置代办信息
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
				dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
				dbcz.setGnmkMc("处分解除审核");
				dbcz.setXmmc("解除审核");
				dbcz.sqPush(model.getJcid(),model.getXh(), model.getSplcid());*/
			}
		}
		//return result;
		
			return result;
		
	}
	
	/**
	 * 
	 * @描述:TODO(取消功能)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午03:05:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cfjcsqUpdateSave(CfjcsqForm model) throws Exception {
		String jcid = model.getJcid();
		//获取申诉解除流程
		CfsssqDao sssqDao=new CfsssqDao();
		
		//获取申诉解除流程
		String splcid = "";
		CfjcsqForm myModel = getModel(jcid);
		// 已退回
		if(Constants.YW_YTH.equals(myModel.getSqjg())){
			splcid = myModel.getSplcid();
		}else{
			HashMap<String, String> ssjcsplc=sssqDao.getSsjcsplc();
			splcid = ssjcsplc.get("jcspl");
		}
		
		//审批流程为空，提示显示设置审批流程
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		
		model.setSplcid(splcid);
		//设置初始审核状态值
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
				model.setSqjg(Constants.SHZ); 
		}
		boolean result=runUpdate(model);
		if(result){
			result = shlc.runSubmit(jcid, splcid,model.getXh(),"wjcf_cfjcsh.do?method=cxCfjcshList","wjcf_cfjcsq.do?method=cxCfjcsqList");
			if (result) {
				//设置代办信息
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
				dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
				dbcz.setGnmkMc("处分解除审核");
				dbcz.setXmmc("解除审核");
				dbcz.sqPush(jcid, model.getXh(), splcid);*/
			}
		}
		return String.valueOf(result);
	}
	
	
	
	/**
	 * 
	 * @描述:TODO(查询解除处分的学生信息)
	 * @作者：沈晓波[工号：1123]
	 * @日期：2014-8-14 上午08:58:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cfid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> jccfwjxx(String cfid) throws Exception{
		
		CfjcsqDao dao = new CfjcsqDao();
		
		return dao.jccfwjxx(cfid);
		
	}
	

}
