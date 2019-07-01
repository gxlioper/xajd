/**
 * @部门:学工产品事业部
 * @日期：2013-7-31 上午08:27:26 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jcxmwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 奖惩项目维护
 * @作者： xiaxia[工号:1104]
 * @时间： 2016-8-2 下午04:31:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class XsxwJcxmwhService extends SuperServiceImpl<XsxwJcxmwhForm, XsxwJcxmwhDao>  {

	private XsxwJcxmwhDao dao = new XsxwJcxmwhDao();
	/**
	 * 
	 * @描述:处分项目列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-8-2 下午05:01:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCfxmPageList(XsxwJcxmwhForm model) throws Exception{
		return dao.getCfxmPageList(model);
	}
	

}
