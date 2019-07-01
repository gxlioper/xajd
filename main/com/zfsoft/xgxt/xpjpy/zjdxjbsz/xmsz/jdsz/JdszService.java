/**
 * @部门: 学工产品(1)部
 * @日期： 2018-7-13 上午10:25:05 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.xmsz.jdsz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xpjpy.zjdxjbsz.cssz.CsszService;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 新评奖评优管理模块
 * @类功能描述: 不可兼得设置
 * @作者： MengWei[工号:1186]
 * @时间： 2018-7-13 上午10:24:14 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JdszService extends SuperServiceImpl<JdszForm,JdszDao>{
	private JdszDao dao = new JdszDao();
	
	public JdszService(){
		super.setDao(dao);
	}
	
	/**
	 * @描述: 根据项目代码获得项目名称
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 下午02:34:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String getXmmcByXmdm(String xmdm) throws Exception {
		return dao.getXmmcByXmdm(xmdm);
	}
	
	/**
	 * @描述: 根据xmdm查询设置的记录
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 下午03:14:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xn
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getByXmdm(String xmdm, String xn) throws Exception{
		return dao.getByXmdm(xmdm, xn);
	}
	
	/**
	 * @描述: 根据xmdm查询此项目是否被申请
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 下午03:23:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistsFlowData(String xmdm) throws Exception{
		return Integer.valueOf(dao.getFlowData(xmdm)) > 0;
	}
	
	/**
	 * @描述: 获取勾选项目以外的所有项目(相同流程内的)
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-13 下午05:51:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getOthers(String xmdm) throws Exception{
		CsszService CsszService = new CsszService();
		String xn = CsszService.getCszzInfo().get("xn");
		return dao.getOthers(xmdm, xn);
	}
	
	/**
	 * @描述: 兼得设置保存
	 * @作者： MengWei[工号：1186]
	 * @日期： 2018-7-14 上午11:38:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmdm
	 * @param xmdms
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean jdsz(String xmdm,String xmdms) throws Exception{
		return dao.runJdsz(xmdm,xmdms);
	}
}
