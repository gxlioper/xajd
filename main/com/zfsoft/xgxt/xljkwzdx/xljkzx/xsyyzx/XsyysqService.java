/**
 * @部门:学工产品事业部
 * @日期：2014-4-25 上午11:30:08 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xsyyzx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询-学生预约咨询
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-25 上午11:30:08 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsyysqService extends SuperServiceImpl<XsyysqForm, XsyysqDao>{

	public XsyysqService() {
		super.setDao(new XsyysqDao());
	}
	
	/**
	 * 
	 * @描述:查询咨询预约说明
	 * @作者：王志刚
	 * @日期：2014-4-25 上午10:14:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param 
	 * @return
	 * boolean 返回类型 
	 */
	public String getZxyysm() throws Exception{
		return dao.getZxyysm();
	}
	
	/**
	 * 
	 * @描述:查出所有心理问题类型
	 * @作者：王志刚
	 * @日期：2014-4-25 下午03:17:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getXlwtAllList(){
		return dao.getXlwtAllList();
	}
	
	/**
	 * 
	 * @描述:查出所选的心理问题类型
	 * @作者：王志刚
	 * @日期：2014-4-29 下午02:17:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxdmstr   例：'23','123','213'
	 * @return
	 */
	public List<HashMap<String, String>> getXlwtAllListByLxdm(String lxdmstr){
		return dao.getXlwtAllListByLxdm(lxdmstr);
	}
	
	/**
	 * 
	 * @描述: 取消学生预约咨询申请
	 * @作者：王志刚
	 * @日期：2014-4-29 上午11:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid 
	 * @param qxyyyy 取消预约原因
	 * @param yyzt 预约状态
	 * @return
	 * boolean 返回类型 
	 */
	public boolean cancelXsyysq(String sqid, String qxyyyy, String yyzt) throws Exception{
		return dao.cancelXsyysq(sqid, qxyyyy, yyzt);
	}
	
	/**
	 * 
	 * @描述: 学生咨询评价(根据申请id)
	 * @作者：王志刚
	 * @日期：2014-5-7 上午11:46:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid 
	 * @param zxxgmydpf 咨询效果满意度评分
	 * @param xszxpj 学生咨询评价
	 * @return
	 * boolean 返回类型 
	 */
	public boolean setZxpj(String sqid, String zxxgmydpf, String xszxpj) throws Exception{
		return dao.setZxpj(sqid, zxxgmydpf, xszxpj);
	}
	
	/**
	 * 
	 * @描述: 学生咨询评价(根据咨询id)
	 * @作者：王志刚
	 * @日期：2014-5-9 下午15:00:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zxid 
	 * @param zxxgmydpf 咨询效果满意度评分
	 * @param xszxpj 学生咨询评价
	 * @return
	 * boolean 返回类型 
	 */
	public boolean setZxpjByZxid(String zxid, String zxxgmydpf, String xszxpj) throws Exception{
		return dao.setZxpjByZxid(zxid, zxxgmydpf, xszxpj);
	}
}
