package com.zfsoft.xgxt.pjpy.xzhcp.sq;

import java.util.HashMap;
import java.util.List;

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
import com.zfsoft.xgxt.pjpy.xzhcp.ZhcpService;
import com.zfsoft.xgxt.rcsw.qjgl.xjsqsh.XjsqcsszService;
import com.zfsoft.xgxt.rcsw.qjgl.xjsqsh.XjsqshForm;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;

public class ZhcpDjService extends SuperServiceImpl<ZhcpDjForm,ZhcpDjDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	private static final String MESSAGE_REPEAT = "本学年学期已有记录，请勿重复填写！";
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存综合测评
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-30 上午10:45:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zhcpForm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean save(ZhcpDjForm zhcpForm) throws Exception{
		boolean rs = true;
		CsszModel cssz = new CsszService().getModel();
		if("submit".equals(zhcpForm.getType())){
			zhcpForm.setShzt(Constants.YW_SHZ);
		}else{
			zhcpForm.setShzt(Constants.YW_WTJ);
		}
		zhcpForm.setSplc(new ZhcpService().getModel().getSplc());
		if(StringUtils.isNotNull(zhcpForm.getSqid())){
			rs = dao.runUpdate(zhcpForm);
		}else{
			zhcpForm.setXn(cssz.getXn());
			if(!"10364".equals(Base.xxdm)){
				zhcpForm.setXq(cssz.getXq());
			}
			if(!dao.checkNotRepeat(zhcpForm)){
				JSONObject json = new JSONObject();
				json.put("message", MESSAGE_REPEAT);
				throw new SystemException(json);
			}
			zhcpForm.setSqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsert(zhcpForm);
		}
		if("submit".equals(zhcpForm.getType())){
			if (rs) {
				rs = shlc.runSubmit(zhcpForm.getSqid(), zhcpForm.getSplc(),zhcpForm.getXh(), "pjpy_xzhcp_zcdj.do", "pjpy_xzhcp_zcsh.do");
			}
		}
		return rs;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 提交
	 * @作者：喻鑫源[工号：1206]06
	 * @修改记录: 修改者名字-修改日期
	 * @日期：2018-1-30 上午10:50:-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBusiness(ZhcpDjForm model, User user) throws Exception{
		String splc = new ZhcpService().getModel().getSplc();
        if(Constants.YW_YTH.equals(model.getShzt())){
        	splc = model.getSplc();
		}
        model.setShzt(Constants.YW_SHZ);
		model.setSplc(splc);
		boolean flag = dao.runUpdate(model);
		if(flag){
			shlc.runSubmit(model.getSqid(), model.getSplc(),model.getXh(), "pjpy_xzhcp_zcdj.do", "pjpy_xzhcp_zcsh.do");
		}
		return flag;
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
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-2-28 上午10:29:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZcfsForDjb(String xh,String xn,String xq){
		return dao.getZcfsForDjb(xh, xn, xq);
		
	}
	
	/**
	 * 
	 * @描述: 获取单科最低分
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-2-28 下午02:23:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getDkZdf(String xh,String xn,String xq){
		return dao.getDkZdf(xh, xn, xq);
	}
	
	/**
	 * 
	 * @描述: 获取学生基本信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-2-28 下午02:42:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsjbxx(String xh){
		return dao.getXsjbxx(xh);
	}
	
	/**
	 * 
	 * @描述: 获取班级人数
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-2-28 下午03:17:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjrs(String bjdm){
		return dao.getBjrs(bjdm);
	}
	
	/**
	 * 
	 * @描述: 获取跑评奖结果List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-3-5 上午10:51:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getListByHjjg(String xh,String xn){
		List<HashMap<String,String>> pjjgList = dao.getListByHjjg(xh, xn);
		for(int i=pjjgList.size();i<5;i++){
			pjjgList.add(new HashMap<String,String>());
		}
		return pjjgList;
	}
}
