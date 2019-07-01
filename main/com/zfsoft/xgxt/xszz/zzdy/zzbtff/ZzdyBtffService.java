/**
 * @部门:学工产品事业部
 * @日期：2015-11-23 上午08:40:48 
 */  
package com.zfsoft.xgxt.xszz.zzdy.zzbtff;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2015-11-23 上午08:40:48 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZzdyBtffService extends SuperServiceImpl<ZzdyBtffForm, ZzdyBtffDao> {
	private ZzdyBtffDao dao = new ZzdyBtffDao();
	
	public boolean isHave(ZzdyBtffForm model) {
		return dao.isHave(model);
	}

	/**
	 * 
	 * @描述:资助名单修改
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-23 下午05:55:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean btff(ZzdyBtffForm model) throws Exception {
			return  dao.btff(model);
	}
	/**
	 * 
	 * @描述:获取发放月份列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-11-25 上午11:55:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getFfyfList() throws Exception {
		return dao.getFfyfList();
	}
	
	public List<HashMap<String, String>> getFfjlList(String xh ,String xmdm) throws Exception {
		return dao.getFfjlList(xh,xmdm);
	}

}
