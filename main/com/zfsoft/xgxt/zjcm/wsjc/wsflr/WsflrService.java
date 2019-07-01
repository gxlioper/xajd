/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 下午01:54:34 
 */  
package com.zfsoft.xgxt.zjcm.wsjc.wsflr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-3-8 下午01:54:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsflrService extends SuperServiceImpl<WsflrForm, WsflrDao>{
	private WsflrDao dao = new WsflrDao();
	
	List<HashMap<String, String>> getPageListForLr(WsflrForm t, User user) throws Exception{
		return dao.getPageListForLr(t, user);
	}
	
	public boolean saveWsf(WsflrForm t, User user) throws Exception{
		return dao.saveWsf(t, user);
	}
	
	/** 
	 * @描述:提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-9 上午10:11:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @param user
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws 
	 */
	public String tj(WsflrForm form, User user) throws Exception{
		List<String> failXms = new ArrayList<String>();
		String[] ids = form.getIds();
		for(int i = 0;i<ids.length;i++){
			boolean result = dao.tj(ids[i], user, form);
			if (!result) {
				failXms.add(ids[i]);
			}
		}
		if (failXms.isEmpty()) {
			return MessageUtil.getText(MessageKey.SYS_SAVE_SUCCESS);
		} else {
			return MessageUtil.getText(MessageKey.SYS_SAVE_FAIL);
		}
		
	}
	
	/** 
	 * @描述:是否可提交
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-3-9 上午10:16:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean checkIsCanSubmit(WsflrForm form) throws Exception {
		String[] ids = form.getIds();
		boolean result = true;
		for(int i = 0;i<ids.length;i++){
			result = dao.checkIsCanSubmit(ids[i]);
			if (!result) {
				break;
			}
		}
		return result;
	}
	
	public List<HashMap<String, String>> getCcqsList(WsflrForm t, User user) throws Exception{
		return dao.getCcqsList(t, user);
	
	}
	
	/** 
	 * @描述:卫生分录入导出
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-4-11 上午11:11:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWsflrDc(WsflrForm t, User user) throws Exception{
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		return dao.getPageListForLr(t, user);
	}
	
}
