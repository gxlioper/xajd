/**
 * @部门:学工产品事业部
 * @日期：2016-3-28 下午05:23:06 
 */  
package com.zfsoft.xgxt.xsxx.cxdd.jg;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-28 下午05:23:06 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CxddJgService extends SuperServiceImpl<CxddJgForm, CxddJgDao> {
	public List<HashMap<String, String>> getCxdjList(String cxdj){
		return dao.getCxdjList(cxdj);
	}
	
	public String getbjdm(String xh){
		return dao.getbjdm(xh);
	}
	
	/**
	 * 
	 * @描述: 综测分数成绩Map
	 * @作者：ligl
	 * @日期：2013-11-30 下午03:07:04
	 * @修改记录: 
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getZcfsList(CxddJgForm t,String xmmc) {
		return dao.getZcfsList(t, xmmc);
	}
	
	/**
	 * @description	： 获取平均成绩（江苏省徐州医药高等职业学校）
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-10 上午09:57:29
	 * @param t
	 * @return
	 */
	public HashMap<String,String> getPjcjAndPm(CxddJgForm t) {
		return dao.getPjcjAndPm(t);
	}
	
	/**
	 * @description	： 获取综测总分（徐州医药）
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-10 下午02:30:06
	 * @param t
	 * @param xmmc
	 * @return
	 */
	public HashMap<String,String> getZcfsTotal(CxddJgForm t,String xmmc) {
		return dao.getZcfsTotal(t, xmmc);
	} 
	/**
	 * 
	 * @描述:获取学生基本信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-19 下午05:05:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXsjbxx(String xh){
		return dao.getXsjbxx(xh);
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-19 下午05:08:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getBjrs(String bjdm){
		return dao.getBjrs(bjdm);
	}
	
	/**
	 * 
	 * @描述: 获取学年学期
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-19 下午05:20:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param xq
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXnxqStr(String xn,String xq){
		String xqmc = Base.getXqmcForXqdm(xq);
		xn = xn.replace("-", "/");
		return xn+xqmc;
	}
	
	/**
	 * 
	 * @描述:获取成绩List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-19 下午05:21:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCjList(CxddJgForm t){
		return dao.getCjList(t);
	}
	
	/**
	 * 
	 * @描述: 获取评奖结果List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-18 上午10:17:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getPjjgList(CxddJgForm t){
		return dao.getPjjgList(t);
	}
	
	/**
	 * 
	 * @描述: 获取违纪处分List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-9-18 上午10:40:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getWjcfList(CxddJgForm t){
		return dao.getWjcfList(t);
	}
	
	/**
	 * @description	： 获取综合成绩排名
	 * @author 		： 柳俊（1282）
	 * @date 		：2017-11-13 下午01:52:37
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 */
	public String getZhcjPm(String xh,String xn,String xq){
		return dao.getZhcjPm(xh,xn,xq);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 保存开学信息
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-9 下午03:07:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	@TransactionControl
	public boolean saveKxxx(CxddJgForm form) throws Exception{
		dao.deleteSzb();
		boolean rs = dao.runInsertSzb(form);
		return rs;
	}
	
	/**
	 * 
	 * @描述: 获取开学参数设置表
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-9 下午04:11:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getKxCsszb(){
		return dao.getKxCsszb();
	}
	
	/**
	 * 
	 * @描述: 获取学费数据
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-9 下午05:30:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getXfsj(String xh){
		return dao.getXfsj(xh);
	}
}
