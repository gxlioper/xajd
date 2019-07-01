/**
 * @部门:学工产品事业部
 * @日期：2017-2-8 上午10:08:35 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.zcsq;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.dtjs.zzgxzc.jcsz.JcszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2017-2-8 上午10:08:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZcsqSERVICE extends SuperServiceImpl<ZcsqForm, ZcsqDAO> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private ShlcInterface shlc = new CommShlcImpl();
	/**
	 * 
	 * @描述: 党支部集合List
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-8 下午05:15:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDzbList(){
		return dao.getDzbList();
	}
	
	/**
	 * 
	 * @描述: 查看转出申请
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 下午02:05:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> ckZcsq(String xh){
		return dao.ckZcsq(xh);
	}
	
	/**
	 * 
	 * @描述: 保存申请数据
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 下午03:05:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveSq(ZcsqForm model,User user) throws Exception {
		//党费交至日期处理（转为每月的最后一天）
		model.setDfjzrq(DateUtils.getLastDayOfMonth(model.getDfjzrq()));
		
		JcszService csszService = new JcszService();
		String splcid = csszService.getModel().getSplc();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSplcid(splcid);
		model.setSqr(user.getUserName());
		String sqid = model.getSqid();
		String type = model.getType();
		if(StringUtils.isNull(sqid)){
			sqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		}
		boolean flag = true;
		if(type.indexOf("submit") != -1){
			model.setShzt(Constants.YW_SHZ);
		}else{
			model.setShzt(Constants.YW_WTJ);
			
		}
		if(StringUtils.isNotNull(model.getSqid())){
			flag = dao.runUpdate(model);
		}else{
			model.setSqid(sqid);
			flag = dao.runInsert(model);
		}
		 
		if(type.indexOf("submit") != -1){
			if (flag) {
				flag = shlc.runSubmit(sqid, splcid, model.getXh(), "dtjs_dzzgxsh.do", "dtjs_dzzgxsq.do");
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
	public boolean submitBusi(ZcsqForm model, User user)  throws Exception {
		JcszService csszService = new JcszService();
		String splcid = csszService.getModel().getSplc();
		model.setSplcid(splcid);
		model.setShzt(Constants.YW_SHZ);
		boolean flag = dao.runUpdate(model);
		if(flag){
			 shlc.runSubmit(model.getSqid(), model.getSplcid(), model.getXh(), "dtjs_dzzgxsh.do", "dtjs_dzzgxsq.do");
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
	
	/**
	 * 
	 * @描述: 是否不存在申请和结果表当中
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-9 下午03:21:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean IsNotExist(ZcsqForm model){
		return dao.IsNotExist(model);
	}
	
	/**
	 * 
	 * @描述: 验证访问该页面的学生是否为党员
	 * @作者：yxy[工号：1206]
	 * @日期：2017-2-10 上午09:35:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param username
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean IsDy(String username){
		return dao.IsDy(username);
	}
}
