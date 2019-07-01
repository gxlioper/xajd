/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmlbwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目类别维护
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmlbwhService extends SuperServiceImpl<XmlbwhForm, XmlbwhDao> {

	private XmlbwhDao dao = new XmlbwhDao();

	public XmlbwhService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:将最大的id值加1,生成新id
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public int getNewId() throws Exception {
		int maxId = 0;
		maxId = dao.getMaxId();
		return maxId + 1;
	}


	/**
	 * 
	 * @描述:获取项目类别代码及名称
	 * @作者：ligl
	 * @日期：2013-4-19 下午02:19:00
	 * @修改记录:
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlb() throws Exception {
		List<HashMap<String, String>> list = dao.getXmlb();
		return list;
	}
	
	/**
	 * 
	 * @描述:判断重复数据，以名称为准
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRepeat(XmlbwhForm model) throws Exception {
		return dao.isRepeat(model);
	}
	
	
	/**
	 * 
	 * @描述:判断关联性，数据可否处理
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(XmlbwhForm model) throws Exception {
		XmlbwhForm modelOld = dao.getModel(model);
		boolean flag = false;
		if(!model.getLbmc().equals(modelOld.getLbmc())){
			flag = dao.isRalate(model);
		}
		return flag;
	}
	
	
	/**
	 * 
	 * @描述:判断关联性，数据可否处理
	 * @作者：ligl
	 * @日期：2013-4-18 下午02:42:55
	 * @修改记录:
	 * @throws
	 */
	public boolean isRalate(String values) throws Exception {
		return dao.isRalate(values);
	}
	
	
}
