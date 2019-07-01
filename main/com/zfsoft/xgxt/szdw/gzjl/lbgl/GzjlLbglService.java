/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午03:53:11 
 */  
package com.zfsoft.xgxt.szdw.gzjl.lbgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午03:53:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjlLbglService extends SuperServiceImpl<GzjlLbglForm, GzjlLbglDao> {
	
	/**
	 * 
	 * @描述:获取类别列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-6-25 下午03:51:50 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             ActionForward 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getPageList(GzjlLbglForm model) throws Exception {
		return super.getPageList(model);
	}
	/**
	 * 
	 * @描述:增加工作记录类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-6-25 下午03:51:50 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addGzjllb(GzjlLbglForm model) throws Exception{
		return dao.addGzjllb(model);
		
	}
	/**
	 * 
	 * @描述:修改工作记录类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-6-25 下午03:51:50 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateGzjllb(GzjlLbglForm model) throws Exception{
		return dao.runUpdate(model);
	}
	/**
	 * 
	 * @描述:删除工作记录类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-6-25 下午03:51:50 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public int delGzjllb(String values) throws Exception{
		return dao.deleteGzjllb(values);
	}
	/**
	 * 
	 * @描述:工作记录类别是否被占用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-10 下午02:58:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isUsed(String values) throws Exception {
		boolean flag = false;
		if (values != null) {
			String[] lbdms = values.split(",");
			for (int i = 0; i < lbdms.length; i++) {
				flag = dao.isExistsXmData(lbdms[i]);
				if (flag) {
					break;
				}
			}
		}
		return flag;
	}
	
	/**
	 *获取工作记录类别
	 */
	public String getGzjllbmc(String lbdm) throws Exception {
		return dao.getGzjllbmc(lbdm);

	}
	/**
	 * 
	 * @描述:工作类别列表
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-6-29 上午11:40:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>>  getGzjllbList() throws Exception {
		return dao.getGzjllbList();

	}
	
}
