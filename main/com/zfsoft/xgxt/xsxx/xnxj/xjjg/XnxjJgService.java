/**
 * @部门:学工产品事业部
 * @日期：2013-12-23 下午03:04:49 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjjg;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-23 下午03:04:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnxjJgService extends SuperServiceImpl<XnxjJgForm, XnxjJgDao> {

	private XnxjJgDao dao = new XnxjJgDao();

	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param dao
	 */
	public XnxjJgService() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-24 上午11:49:38
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 * XnxjJgForm 返回类型 
	 * @throws
	 */
	public XnxjJgForm getModel(String xh , String xn) throws Exception{
		return dao.getModel(xh, xn);
	}
	
	public HashMap<String , String> getXnxjJgInfo(String id) throws Exception{
		return dao.getXnxjJgInfo(id);
	}
	
	
	public List<HashMap<String , String>> getXnxjList(String xh , String xn) throws Exception{
		return dao.getXnxjList(xh, xn);
	}
	
	/**
	 * 
	 * @描述:根据业务id删除结果
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-24 上午11:50:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ywid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public int delXnxjJg(String ywid) throws Exception{
		return dao.delXnxjg(ywid);
	}
	
	/**
	 * 
	 * @描述:获取学年小结历史记录
	 * @作者：张小彬[工号:1036]
	 * @日期：2013-12-24 上午11:55:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getAllXnxjList(String xh) throws Exception{
		return dao.getAllXnxjList(xh);
	}
}
