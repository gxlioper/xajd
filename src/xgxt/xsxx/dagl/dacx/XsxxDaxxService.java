/**
 * @部门:学工产品事业部
 * @日期：2014-4-22 上午11:50:09 
 */  
package xgxt.xsxx.dagl.dacx;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案查询页面
 * @类功能描述:  
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-22 上午11:50:09 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsxxDaxxService extends SuperServiceImpl<XsxxDaxxForm, XsxxDaxxDao> {

	public XsxxDaxxService() {
		super.setDao(new XsxxDaxxDao());
	}
	
	/**
	 * 查询最新一条档案信息
	 * @param xm,sfzh
	 * @return
	 */
	public HashMap<String,String> getXsxxDaxx(String xm, String sfzh){
		return dao.getXsxxDaxx(xm, sfzh);
	}
}
