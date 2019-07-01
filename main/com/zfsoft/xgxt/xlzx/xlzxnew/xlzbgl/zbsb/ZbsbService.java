package com.zfsoft.xgxt.xlzx.xlzxnew.xlzbgl.zbsb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.jskp.cssz.CsszService;
import com.zfsoft.xgxt.jskp.lxsq.LxsqForm;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz.XlzxSbService;

public class ZbsbService extends SuperServiceImpl<ZbsbForm, ZbsbDao> {
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @描述: 学生授权验证
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-28 下午01:49:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String xssqCheck(String xh){
		return dao.xssqCheck(xh);
	}
	
	/**
	 * 
	 * @描述: 获取学生信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午03:32:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBjxx(String xh){
		return dao.getBjxx(xh);
	}
	
	/**
	 * 
	 * @描述: 获取周报问题人员信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午04:26:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsqid
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getZbWtryInfo(String zbsqid){
		return dao.getZbWtryInfo(zbsqid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-29 下午05:13:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zbsb
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveZbsb(ZbsbForm zbsb) throws Exception{
		boolean rs = true;
		String[] xhArray = zbsb.getXhArray();
		String[] zbwtmsArray = zbsb.getZbwtmsArray();
		zbsb.setSplcid(new XlzxSbService().getModel("zb").getSplc());
		zbsb.setSbsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		if("submit".equals(zbsb.getSaveFlag())){
			zbsb.setShzt(Constants.YW_SHZ);
		}else{
			zbsb.setShzt(Constants.YW_WTJ);
		}
		if(StringUtils.isNotNull(zbsb.getSbsqid())){
			rs = dao.delWtb(zbsb.getSbsqid());
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
			rs = dao.runUpdateNotCommit(zbsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}else{
			if(!this.checkIsNotExist(zbsb.getBjdm(), zbsb.getSbzbid())){
				throw new SystemException(MessageKey.XG_XLZX_SB_REPEAT);
			}
			zbsb.setSbsqid(UniqID.getInstance().getUniqIDHash().toUpperCase());
			rs = dao.runInsertNotCommit(zbsb);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		List<String[]> paraList = new ArrayList<String[]>();
		if(xhArray != null && xhArray.length > 0){
			for (int i = 0; i < zbwtmsArray.length; i++) {
				paraList.add(new String[]{"zb",zbsb.getSbsqid(),xhArray[i],zbwtmsArray[i]});
			}
			rs = dao.saveDataIntoWtb(paraList);
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		if("submit".equals(zbsb.getSaveFlag())){
			rs = shlc.runSubmit(zbsb.getSbsqid(),zbsb.getSplcid(),zbsb.getXh(), "xg_xlzxnew_zbsh.do", "xg_xlzxnew_zbsb.do");
			if(!rs){
				throw new SystemException(MessageKey.SYS_SAVE_FAIL);
			}
		}
		return rs;
	}
	
	/**
	 * 
	 * @描述: 提交
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 上午10:16:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBusi(ZbsbForm model, User user)  throws Exception {
		String splc = new XlzxSbService().getModel("zb").getSplc();
        if(Constants.YW_YTH.equals(model.getShzt())){
        	splc = model.getSplcid();
		}
        model.setShzt(Constants.YW_SHZ);
		model.setSplcid(splc);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getSbsqid(), splc, model.getXh(), "xg_xlzxnew_zbsh.do", "xg_xlzxnew_zbsb.do");
		}
		return flag;
	}
	
	//撤销
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 获取学生查询
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-8-30 下午01:45:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getStuCx(ZbsbForm t, User user,String xhs) throws Exception{
		return dao.getStuCx(t, user, xhs);
	}
	
	/**
	 * 
	 * @描述: 检测任职日期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-1 上午10:32:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkRzrq(String xh){
		return dao.checkRzrq(xh);
	}
	
	/**
	 * 
	 * @描述: 验证是否已填写周报
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-1 上午11:33:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @param sbzbid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotExist(String bjdm,String sbzbid){
		return dao.checkIsNotExist(bjdm, sbzbid);
	}
}
