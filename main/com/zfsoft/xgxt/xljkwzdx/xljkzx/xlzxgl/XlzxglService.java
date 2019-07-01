/**
 * @部门:学工产品事业部
 * @日期：2014-5-7 下午01:56:35 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -心理咨询管理
 * @类功能描述:
 * @作者：王志刚[工号:1060]
 * @时间： 2014-5-7 下午01:56:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlzxglService extends SuperServiceImpl<XlzxglForm, XlzxglDao>{

	public XlzxglService() {
		super.setDao(new XlzxglDao());
	}
	
	/**
	 * 
	 * @描述:查询导出数据
	 * @作者：王志刚
	 * @日期：2014-5-7 下午05:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getAllXlzxglList(XlzxglForm model, User user)
			throws Exception {
		return dao.getAllXlzxglList(model, user);
	}
}
