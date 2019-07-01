/**
 * @部门:学工产品事业部
 * @日期：2016-1-27 下午04:23:43 
 */  
package com.zfsoft.xgxt.xsztz.jxgl.xnjxjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-27 下午04:23:43 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnjxjgService extends SuperServiceImpl<XnjxjgForm, XnjxjgDao>{
	
	private XnjxjgDao dao = new XnjxjgDao();
	
	
	
	public boolean runUpdate(XnjxjgForm form) throws Exception {
		return dao.runUpdate(form);
	}
	
	public boolean runInsert(XnjxjgForm form)throws Exception {
		return dao.runInsert(form);		
	}
	
	public boolean runDel(XnjxjgForm form)throws Exception {
		return dao.runDel(form);
	}
	
	/** 
	 * @描述:结果模块用于删除
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-2 上午09:41:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean runDelforjg(XnjxjgForm form) throws Exception {
		return dao.runDelForJg(form);
	}
	
	/** 
	 * @描述:结果模块用于增加
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-2 上午09:42:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean runInsertForjg(XnjxjgForm form) throws Exception {
		return dao.runInsertForjg(form);
	}
	
	/** 
	 * @描述:结果模块用于获取奖项信息
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-2 上午09:42:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getjginfo(XnjxjgForm form) throws Exception {
		return dao.getJginfo(form);
	}
	
	/** 
	 * @描述:获取奖项结果信息
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-2 上午09:44:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String,String> getJg(XnjxjgForm form) throws Exception {
		return dao.getJg(form);
	}
	
	/** 
	 * @描述:获取已申请的项目信息，用于结果模块的修改
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-2-2 上午09:45:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getYiShen(String xh,String xmdm){
		return dao.getYiShen(xh,xmdm);
	}
	
	
	 
}
