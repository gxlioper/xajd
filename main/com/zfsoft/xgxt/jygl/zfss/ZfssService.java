/**
 * @部门:学工产品事业部
 * @日期：2013-5-27 下午02:05:22 
 */
package com.zfsoft.xgxt.jygl.zfss;

import java.util.HashMap;
import java.util.List;

import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 社区管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： huj
 * @时间： 2013-5-27 下午02:05:22
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZfssService extends SuperServiceImpl<ZfssForm, ZfssDao> {

	private ZfssDao dao = new ZfssDao();

	public ZfssService() {
		super.setDao(dao);
	}
	
	public List<HashMap<String, String>> getZfssCountList(ZfssForm t)
		throws Exception {
		return dao.getZfssCountList(t);
	}
	public List<HashMap<String, String>> getAllZfssCountList(ZfssForm t)
		throws Exception {
		Pages pages = (Pages) t.getClass().getMethod("getPages").invoke(t);
		pages.setPageSize(Integer.MAX_VALUE);
		
		t.getClass().getMethod("setPages",Pages.class).invoke(t, pages);
		
		return dao.getZfssCountList(t);
	}
	
	public ZfssForm getModel(ZfssForm t) throws Exception {
		return dao.getModel(t);
	}

}
