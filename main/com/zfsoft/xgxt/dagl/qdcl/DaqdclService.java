/**
 * @部门:学工产品事业部
 * @日期：2014-2-10 下午05:25:02 
 */  
package com.zfsoft.xgxt.dagl.qdcl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：  wanghj [工号：1004]
 * @时间： 2014-2-10 下午05:25:02 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaqdclService extends SuperServiceImpl<DaqdclForm, DaqdclDao> {

	private DaqdclDao dao = new DaqdclDao();
	
	public DaqdclService(){
		super.setDao(dao);
	}

	/**
	 * 判断档案清单材料名称是否重复
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getDaqdclByName(DaqdclForm myForm) throws Exception{
		
		return dao.getDaqdclByName(myForm);
	}
	
	/**
	 * 
	 * @描述:材料列表
	 * @日期：2014-4-24 上午09:51:32
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDaqdclAllList() throws Exception {
		
		return dao.getDaqdclAllList();
	}
}
