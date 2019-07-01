/**
 * @部门:学工产品事业部
 * @日期：2013-4-18 下午02:42:37 
 */
package com.zfsoft.xgxt.xszz.xmwh.shsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 学生资助
 * @类功能描述: 项目维护-审核调整奖项设置
 * @作者： ligl
 * @时间： 2013-4-18 下午02:42:37
 * @版本： V1.0
 * @修改记录:
 */
public class XmwhShszService extends
		SuperServiceImpl<XmwhShszForm, XmwhShszDao> {

	private XmwhShszDao dao = new XmwhShszDao();

	public XmwhShszService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:兼得设置
	 * @作者：ligl
	 * @日期：2013-4-22 下午05:08:13
	 * @修改记录: 
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean shsz(String xmdm,String xmdms) throws Exception{
		return dao.runShsz(xmdm,xmdms);
	}
	
	/**
	 * 
	 * @描述:根据xmdm查询设置的记录
	 * @作者：ligl
	 * @日期：2013-4-24 上午10:14:13
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm) throws Exception{
		return dao.getByXmdm(xmdm);		
	}
	
	/**
	 * 
	 * @描述:根据项目代码，获取已设置的可调整项目
	 * @作者：ligl
	 * @日期：2013-4-24 上午10:14:13
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getKshxm(String xmdm) throws Exception{
		return dao.getKshxm(xmdm);
	}


}
