/**
 * @部门:学工产品事业部
 * @日期：2013-10-30 下午06:39:24 
 */  
package com.zfsoft.xgxt.wjcf.cfjcsh;

import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqDao;
import com.zfsoft.xgxt.wjcf.cfjcsq.CfjcsqForm;
import xgxt.form.User;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.model.ShxxModel;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.wjcf.cfsb.CfsbglForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 违纪管理模块
 * @类功能描述: (处分解除审核) 
 * @作者： 陈敏杰[工号:913]
 * @时间： 2013-10-30 下午06:36:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CfjcshService extends SuperServiceImpl<CfjcshForm, CfjcshDao> {
	
	private CfjcshDao dao=new CfjcshDao();
	private CfjcsqDao cfjcsqDao = new CfjcsqDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	public CfjcshService(){
		this.setDao(dao);
	}

	/** 
	 * @描述:(保存审核)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-28 下午04:46:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	@TransactionControl
	public boolean jcsh(CfjcshForm form, User user) throws Exception{
		
		ShxxModel model = new ShxxModel();
		// 审核流程ID
		model.setShlc(form.getSplcid());
		// 审核人
		model.setShr(user.getUserName());
		// 审核意见
		model.setShyj(form.getShyj());
		// 审核状态
		model.setShzt(form.getShzt());
		// 审核岗位ID
		model.setGwid(form.getGwid());
		model.setThgw(form.getThgw());
		// 业务ID(多为申请ID)
		model.setYwid(form.getYwid());
		model.setSqrid(form.getXh());
		model.setTzlj("wjcf_cfjcsh.do?method=cxCfjcshList");
		model.setTzljsq("wjcf_cfjcsq.do?method=cxCfjcsqList");
		
		String zhzt = shlc.runAuditingNotCommit(model);
		CfjcshForm shForm=new CfjcshForm();
		shForm.setYwid(form.getYwid());
		shForm.setSqjg(zhzt);
		boolean result=dao.runUpdateNotCommit(shForm, shForm.getYwid());
		if(result){
			//设置代办信息
			/*BaseDbcz dbcz=new BaseDbcz();
			dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
			dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
			dbcz.setGnmkMc("处分解除审核");
			dbcz.setXmmc("解除审核");
			dbcz.shPush(form.getYwid(), form.getSplcid());*/
		}
		if(result && zhzt.equals(Constants.TG)){
			CfjcsqForm cfjcsqForm =  cfjcsqDao.getModel(form.getYwid());
			form.setFilepath(cfjcsqForm.getFilepath());
			form.setFilepath2(cfjcsqForm.getFilepath2());
			result= dao.insertWjjgk(form);
		}
		return result;
	}
	
	/** 
	 * @描述:(最后一级审核回滚)
	 * @作者：陈敏杰[工号：913]
	 * @日期：2013-10-29 上午10:41:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @param string
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean cancel(CfjcshForm model) throws Exception{
		CfjcshForm shForm=new CfjcshForm();
		shForm.setYwid(model.getYwid());
		shForm.setSqjg(Constants.SHZ);
		boolean result=dao.runUpdate(shForm, shForm.getYwid());
		if(result){
			int count=dao.udateJgk(model.getCfid());  //修改结果库
			if(count>=0){
				//设置代办信息
				/*BaseDbcz dbcz=new BaseDbcz();
				dbcz.setShPath("wjcf_cfjcsh.do?method=cxCfjcshList");
				dbcz.setSqPath("wjcf_cfjcsq.do?method=cxCfjcsqList");
				dbcz.setGnmkMc("处分解除审核");
				dbcz.setXmmc("解除审核");
				dbcz.shPush(model.getYwid(), model.getSplcid());*/
			}else{
				return false;
			}
		}
		
		return result;
	}
	
	public String getJcLsh(CfjcshForm model) throws Exception{
		return dao.getJcLsh(model);
	}
	/**
	 * 
	 * @描述:验证解除处分文号是否存在
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-4-30 上午09:49:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkExistJcwh(CfjcshForm myForm) {
		String num = dao.checkExistJcwh(myForm);
		return Integer.valueOf(num) > 0;
	}
	

}
