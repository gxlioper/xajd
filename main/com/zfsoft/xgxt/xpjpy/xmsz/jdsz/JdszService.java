/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 上午11:11:11
 */
package com.zfsoft.xgxt.xpjpy.xmsz.jdsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 项目维护-兼得设置
 * @作者： ligl
 * @日期：2013-8-5 上午11:11:11
 * @版本： V1.0
 * @修改记录:
 */
public class JdszService extends
		SuperServiceImpl<JdszModel, JdszDao> {

	private JdszDao dao = new JdszDao();

	public JdszService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:兼得设置
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param myForm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean jdsz(String xmdm,String xmdms) throws Exception{
		return dao.runJdsz(xmdm,xmdms);
	}
	
	/**
	 * 
	 * @描述:根据xmdm查询设置的记录
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm,String currXn, String currXq) throws Exception{
		return dao.getByXmdm(xmdm,currXn, currXq);		
	}
	
	
	/**
	 * 
	 * @描述:根据项目代码，获取已设置的不可兼得项目
	 * @作者：ligl
	 * @日期：2013-8-5 上午11:11:11
	 * @修改记录: 
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * XmwhJdszForm 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getBjdxm(String xmdm) throws Exception{
		return dao.getBjdxm(xmdm);
	}
	/**
	 * 
	 * @描述:获取星级登记
	 * @作者：caopei[工号：1352]
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllXj() {
		List<HashMap<String, String>> list = dao.getAllXj();
		return list;
	}
	
	
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:根据项目代码，返回不可兼得已申请的奖项。
	 * @作者：cq [工号：785]
	 * @日期：2014-3-3 上午10:36:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getJdysq(User user,String xmdm) throws Exception {
		
		return dao.getJdysq(user,xmdm);
	}
}
