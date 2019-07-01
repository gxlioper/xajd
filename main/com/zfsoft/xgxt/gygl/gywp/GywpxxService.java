/**
 * @部门:学工产品事业部
 * @日期：2013-8-6 下午03:34:28 
 */  
package com.zfsoft.xgxt.gygl.gywp;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: (公寓物品管理) 
 * @作者： cmj [工号：913]
 * @时间： 2013-8-6 下午03:34:28 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GywpxxService extends SuperServiceImpl<GywpxxForm, GywpxxDAO> {
	
	private GywpxxDAO dao=new GywpxxDAO();
	
	public GywpxxService(){
		super.setDao(dao);
	}

	/**
	 * @throws Exception  
	 * @描述:(获取所有公寓楼物品维护信息)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-6 下午03:40:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getGywplrxxList(GywpxxForm model,
			User user) throws Exception {
		// TODO 自动生成方法存根
		return dao.getGywplrxxList(model,user);
	}

	/** 
	 * @描述:(获取物品大类List)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-7 上午08:37:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWpdlList() {
		// TODO 自动生成方法存根
		return dao.getWpdlList();
	}

	/** 
	 * @描述:(获取物品类别List)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-7 上午08:42:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws 
	 */
	public List<HashMap<String, String>> getWplbList() {
		// TODO 自动生成方法存根
		return dao.getWplbList();
	}
}
