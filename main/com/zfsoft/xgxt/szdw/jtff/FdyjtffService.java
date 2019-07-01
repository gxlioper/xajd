/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:16:48 
 */  
package com.zfsoft.xgxt.szdw.jtff;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： Penghui.Qu [工号：445]
 * @时间： 2013-8-5 上午11:16:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdyjtffService extends SuperServiceImpl<FdyjtffForm, FdyjtffDAO> {
	private FdyjtffDAO dao=new FdyjtffDAO();
	
	public FdyjtffService(){
		super.setDao(dao);
	}

	/** 
	 * @描述:TODO(判断辅导员津贴发放是否已录入)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午01:55:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(FdyjtffForm model) {
		// TODO 自动生成方法存根
		return dao.isExist(model);
	}

	/** 
	 * @描述:TODO(获取辅导员信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午02:39:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws 
	 */
	public HashMap<String, String> getFdyjbxx(String zgh) {
		return dao.getFdyjbxx(zgh);
	}

	/** 
	 * @描述:TODO(辅导员list)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午02:46:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyPageList(FdyjtffForm model,
			User user) throws Exception{
		List<HashMap<String, String>> fdylist=dao.getFdyPageList(model);
/*		String cz="<button type=\"button\" id=\"select\" onclick=\"cz();\" style=\"cursor:hand\"  class=\"btn_01\" >选择</button>";
		for(HashMap<String, String> hm:fdylist){
			hm.put("cz",cz);
		}
*/		return fdylist;
	}
	
	/** 
	 * @描述:获取辅导员【寝室导师】（不刷新父页面）
	 * @作者：江水才[工号：1150]
	 * @日期：2014-11-26 上午08:46:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getFdyQsdsPageList(FdyjtffForm model,
			User user) throws Exception{
		List<HashMap<String, String>> fdylist=dao.getFdyQsdsPageList(model);
		/*		String cz="<button type=\"button\" id=\"select\" onclick=\"cz();\" style=\"cursor:hand\"  class=\"btn_01\" >选择</button>";
		for(HashMap<String, String> hm:fdylist){
			hm.put("cz",cz);
		}
		 */		return fdylist;
	}
}
