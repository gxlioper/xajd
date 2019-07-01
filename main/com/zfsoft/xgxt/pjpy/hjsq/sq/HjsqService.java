package com.zfsoft.xgxt.pjpy.hjsq.sq;

import net.sf.json.JSONObject;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.pjpy.hjsq.cssz.CsszService;

public class HjsqService extends SuperServiceImpl<HjsqForm,HjsqDao> {
	private static final String MESSAGE_REPEAT = "同一学年学期奖项名称不得重复！";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @描述: 保存
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-9 上午09:19:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zhcpForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean save(HjsqForm zhcpForm) throws Exception{
		boolean rs = true;
		if("submit".equals(zhcpForm.getType())){
			zhcpForm.setShzt(Constants.YW_SHZ);
		}else{
			zhcpForm.setShzt(Constants.YW_WTJ);
		}
		zhcpForm.setSplc(new CsszService().getModel().getSplc());
		if(dao.checkExistForSave(zhcpForm)){
			JSONObject json = new JSONObject();
			json.put("message", MESSAGE_REPEAT);
			throw new SystemException(json);
		}
		if(StringUtils.isNotNull(zhcpForm.getId())){
			rs = dao.runUpdate(zhcpForm);
		}else{
			zhcpForm.setXn(Base.currXn);
			zhcpForm.setXq(Base.currXq);
			
			zhcpForm.setId(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(zhcpForm);
		}
		if("submit".equals(zhcpForm.getType())){
			if (rs) {
				rs = shlc.runSubmit(zhcpForm.getId(), zhcpForm.getSplc(),zhcpForm.getXh(), "pjpy_jxsq.do", "pjpy_jxsh.do");
			}
		}
		return rs;
	}
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 撤销申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-30 上午11:24:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelSq(String ywid,String lcid) throws Exception{
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * 
	 * @描述: 提交申请
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-9 上午10:10:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBusiness(HjsqForm model, User user) throws Exception{
		String splc = new  CsszService().getModel().getSplc();
        if(Constants.YW_YTH.equals(model.getShzt())){
        	splc = model.getSplc();
		}
        model.setShzt(Constants.YW_SHZ);
		model.setSplc(splc);
		boolean flag = dao.runUpdate(model);
		if(flag){
			shlc.runSubmit(model.getId(), model.getSplc(),model.getXh(), "pjpy_jxsq.do", "pjpy_jxsh.do");
		}
		return flag;
	}
}
