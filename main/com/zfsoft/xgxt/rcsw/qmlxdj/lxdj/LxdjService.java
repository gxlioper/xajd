/**
 * @部门:学工产品事业部
 * @日期：2017-1-11 上午08:57:27 
 */  
package com.zfsoft.xgxt.rcsw.qmlxdj.lxdj;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.rcsw.qmlxdj.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-1-11 上午08:57:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public final class LxdjService extends SuperServiceImpl<LxdjForm, LxdjDao> {
	
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午02:28:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkNotExist(String xh,String xn,String xq,String flag){
		return dao.checkNotExist(xh, xn, xq, flag);
	}
	
	/**
	 * 
	 * @描述: dmList
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:15:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDmList(){
		return dao.getDmList();
	}
	public List<HashMap<String, String>> getLxlxList(){
		return dao.getLxlxList();
	}
	/**
	 * 
	 * @描述: 获取查看信息map
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午03:45:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getCkxx(String id){
		return dao.getCkxx(id);
	}
	
	/**
	 * 
	 * @描述: 保存
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:20:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxdjform
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSq(LxdjForm lxdjform,User user) throws Exception {
		CsszService csszService = new CsszService();
		String splcid = csszService.getModel().getSplc();
		lxdjform.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		lxdjform.setSplcid(splcid);
		String sqid = lxdjform.getSqid();
		String type = lxdjform.getType();
		if(StringUtils.isNull(sqid)){
			sqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		}
		boolean flag = true;
		if(type.indexOf("submit") != -1){
			lxdjform.setShzt(Constants.YW_SHZ);
		}else{
			lxdjform.setShzt(Constants.YW_WTJ);
			
		}
		if(StringUtils.isNotNull(lxdjform.getSqid())){
			flag = dao.runUpdate(lxdjform);
		}else{
			lxdjform.setSqid(sqid);
			flag = dao.runInsert(lxdjform);
		}
		 
		if(type.indexOf("submit") != -1){
			if (flag) {
				flag = shlc.runSubmit(sqid, splcid, lxdjform.getXh(), "rcsw_qmlxsh.do", "rcsw_qmlxdj.do");
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:提交
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:48:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean submitBusi(LxdjForm model, User user)  throws Exception {
		if(!Constants.YW_YTH.equalsIgnoreCase(model.getShzt())){
			CsszService csszService = new CsszService();
			String splcid = csszService.getModel().getSplc();
			model.setSplcid(splcid);
		}
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplcid(), model.getXh(), "rcsw_qmlxsh.do", "rcsw_qmlxdj.do");
		}
		return flag;
	}
	
	/**
	 * 
	 * @描述:撤销
	 * @作者：yxy[工号：1206]
	 * @日期：2017-1-11 下午04:48:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @param lcid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean cancelRecord(String ywid, String lcid) throws Exception {
		return shlc.firstStepCancle(ywid, lcid);
	}
}
