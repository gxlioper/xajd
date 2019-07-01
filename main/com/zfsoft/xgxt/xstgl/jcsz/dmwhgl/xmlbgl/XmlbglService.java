/**
 * @部门:学工产品事业部
 * @日期：2015-07-31 下午02:33:02 
 */
package com.zfsoft.xgxt.xstgl.jcsz.dmwhgl.xmlbgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-07-31 下午02:33:02
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XmlbglService extends SuperServiceImpl<XmlbglForm, XmlbglDao> {
	private XmlbglDao dao = new XmlbglDao();

	/**
	 * 
	 * @描述:获取类别列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午03:44:04
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
	public List<HashMap<String, String>> getPageList(XmlbglForm model) throws Exception {
		return super.getPageList(model);
	}
	/**
	 * 
	 * @描述:增加项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午04:00:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean addXmlb(XmlbglForm model) throws Exception{
		return dao.addXmlb(model);
		
	}
	/**
	 * 
	 * @描述:修改项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午04:10:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateXmlb(XmlbglForm model) throws Exception{
		return dao.runUpdate(model);
	}
	/**
	 * 
	 * @描述:删除项目类别
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午04:10:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public int delXmlb(String values) throws Exception{
		return dao.deleteXmlb(values);
	}
	/**
	 * 
	 * @描述:项目类别是否被占用
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-07-31 下午02:58:59
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
	 *获取项目类别
	 */
	public String getXmlbmc(String lbdm) throws Exception {
		return dao.getXmlbmc(lbdm);

	}
	/**
	 * 
	 * @描述:项目类别列表
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-8-4 上午09:45:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbList(String stlbdm) throws Exception {
		return dao.getXmlbList(stlbdm);

	}
}
