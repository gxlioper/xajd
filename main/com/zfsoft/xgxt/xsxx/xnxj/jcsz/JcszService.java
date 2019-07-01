/**
 * @部门:学工产品事业部
 * @日期：2013-12-18 下午04:17:35 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.jcsz;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-18 下午04:17:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszService extends SuperServiceImpl<JcszForm, JcszDao> {

	private JcszDao dao = new JcszDao();
	
	/**
	 * 
	 * @描述: 获取开关表信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-18 下午05:01:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> getOneKgzt(){
		return dao.getOneKgzt();
	}
	
	public JcszForm getModel() throws Exception{
		return dao.getModel(new JcszForm());
	}
	
	/**
	 * 
	 * @描述:获取审批结果
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-18 下午05:45:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> splCx() throws Exception{
		return dao.splCx();
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述:设置开关状态
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-18 下午05:03:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kgzt
	 * @param spl
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean setupKgsz(String kgzt , String spl,String xjxn) throws Exception{
		return dao.setupKgsz(kgzt, spl,xjxn);
	}
}
