/**
 * @部门:学工产品事业部
 * @日期：2013-10-29 下午01:56:23 
 */  
package com.zfsoft.xgxt.wjcf.cfsssq;

import java.util.HashMap;
import xgxt.utils.String.StringUtils;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qjgl.BaseDbcz;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分申诉申请) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-29 下午01:46:50 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfsssqService extends SuperServiceImpl<CfsssqForm, CfsssqDao> {
	private CfsssqDao dao=new CfsssqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	public static final String SUBMIT = "submit";
	public static final String SAVE = "save";
	
	public CfsssqService(){
		this.setDao(dao);
	}

	/** 
	 * @描述:(获取申诉解除审批流程)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 下午04:54:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getSsjcsplc() {
		//获取流程
		HashMap<String, String> ssjcsplc=dao.getSsjcsplc();
		return ssjcsplc;
	}
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
	public String save(CfsssqForm model) throws Exception {
		
		String ssid = "";
		ssid = UniqID.getInstance().getUniqIDHash();
		model.setSsid(ssid);
		//获取申诉解除流程
		HashMap<String, String> ssjcsplc=dao.getSsjcsplc();
		String splcid=ssjcsplc.get("ssspl");
		//审批流程为空，提示显示设置审批流程
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		//设置初始审核状态值
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
			//model.setSsjg(Constants.WSH);  
			if(SAVE.equalsIgnoreCase(model.getType())){
				model.setSsjg(Constants.WSH);  
			}else{
				model.setSsjg(Constants.SHZ); 
			}
			
		}
		boolean result = false;
		if(!StringUtils.isNull(model.getSqly())){
			model.setSqly(model.getSqly().replaceAll("\\n", ""));
		}
	    result = super.runInsert(model);
		
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			result = shlc.runSubmit(ssid, splcid,model.getXh(),"wjcf_cfsssh.do?method=cxCfssshList","wjcf_cfsssq.do?method=cxCfsssqList");
		}
		return String.valueOf(result);
	}

	
	@Override
	public int runDelete(String[] values) throws Exception {
		String[] ids=getCancelIds(values);
		if(ids.length==0)
			return 0;
		int result=super.runDelete(ids);
		for (String id : ids) {
			if(shlc.deleteShjl(id)){
				BaseDbcz dbcz=new BaseDbcz();
				dbcz.setGnmkMc("处分申诉审核");
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
	 * @描述:TODO(取消时候判断是否是第一级审核)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午02:59:09
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
	 * @描述:TODO(删除记录)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午02:59:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int deleteRecord(String[] ids) throws Exception{
		return dao.runDelete(ids);
	}
	
	/**
	 * 
	 * @描述:TODO(更改记录)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午02:59:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateRecord(CfsssqForm model) throws Exception{
		return  dao.updateBackRecord(model);
	}
	
	/**
	 * 
	 * @描述:TODO(提交功能)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午02:58:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitCfsssq(CfsssqForm model) throws Exception{
		boolean resultCfss = dao.updateBackRecord(model);
		boolean result = false;
		if(resultCfss ){
			//保存审核流程
			result = shlc.runSubmit(model.getCfid(), model.getSplcid(),model.getXh(),"wjcf_cfsssh.do?method=cxCfssshList","wjcf_cfsssq.do?method=cxCfsssqList");
		}
		return result;
	}
	
	/**
	 * 
	 * @描述:TODO(取消功能)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-7 下午02:58:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String cfsssqUpdateSave(CfsssqForm model) throws Exception {
		String ssid = "";
		ssid = model.getSsid();

		//获取申诉解除流程
		String splcid = "";
		CfsssqForm myModel = getModel(ssid);
		// 已退回
		if(Constants.YW_YTH.equals(myModel.getSsjg())){
			splcid = myModel.getSplcid();
		}else{
			HashMap<String, String> ssjcsplc=dao.getSsjcsplc();
			splcid = ssjcsplc.get("ssspl");
		}		
		
		//审批流程为空，提示显示设置审批流程
		if (null == splcid||"".equalsIgnoreCase(splcid)) {
			return MessageKey.SYS_SELECT_SHLC_FAIL;
		}
		model.setSplcid(splcid);
		//设置初始审核状态值
		if(splcid!=null&&!"".equalsIgnoreCase(splcid)){
				model.setSsjg(Constants.SHZ); 
		}
		boolean result = false;
		result = super.runUpdate(model);
		if(result && SUBMIT.equalsIgnoreCase(model.getType())){
			result = shlc.runSubmit(ssid, splcid,model.getXh(),"wjcf_cfsssh.do?method=cxCfssshList","wjcf_cfsssq.do?method=cxCfsssqList");
		}
		return String.valueOf(result);
	}


	
}
