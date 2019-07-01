/**
 * @部门:学工产品事业部
 * @日期：2016-7-22 上午10:49:05 
 */  
package com.zfsoft.xgxt.xsztz.tttzxm.jg;

import java.util.HashMap;

import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsztz.tttzxm.comm.CommTtxmService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-7-22 上午10:49:05 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TttzxmJgService extends SuperServiceImpl<TttzxmJgForm, TttzxmJgDao> {
	private static final String DATE_FORMAT = "yyyy-MM-dd hh24:mi:ss";
	private CommTtxmService commService = new CommTtxmService();

	/**
	 * 
	 * @描述:保存团体申请（增加）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午03:59:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveTtsq(TttzxmJgForm model, User user) throws Exception {
		String ttsqid = UniqID.getInstance().getUniqIDHash().toUpperCase();
		model.setSqsj(GetTime.getTimeByFormat(DATE_FORMAT));
		model.setSjly("0");
		model.setTtjgid(ttsqid);
		boolean flag = commService.saveTtcy(ttsqid, model.getXmdm(), model.getXhArr());
		if(flag){
			flag = dao.runInsert(model);
		}
		
		return flag;
	}
	
	/**
	 * 
	 * @描述:保存团体申请（修改）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-7-26 下午04:00:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveTtsqUpdate(TttzxmJgForm model, User user) throws Exception {
		boolean flag = commService.saveTtcy(model.getTtjgid(), model.getXmdm(), model.getXhArr());
		if(flag){
			flag = dao.runUpdate(model);
		}
		return flag;
	}
	
	/**
	  * 
	  * @描述: 获取团体成员表中对应的学生记录
	  * @作者：yxy[工号：1206]
	  * @日期：2016-8-8 上午10:13:25
	  * @修改记录: 修改者名字-修改日期-修改内容
	  * @return
	  * HashMap<String,String> 返回类型 
	  * @throws
	  */
	 public HashMap<String, String> getttcyDyRecord(String xh,String xmdm){
		 return dao.getttcyDyRecord(xh, xmdm);
	 }
}
