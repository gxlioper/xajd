/**
 * @部门:学工产品事业部
 * @日期：2017-3-9 上午09:43:12 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmxz;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 代码维护_项目性质
 * @作者：Meng.Wei[工号:1186]
 * @时间： 2017-3-9 上午09:43:12 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmxzService extends SuperServiceImpl<XmxzForm, XmxzDao>{
	private XmxzDao dao = new XmxzDao();
	
	public XmxzService() {
		super.setDao(dao);
	}
	
	/**
	 * @描述: 增加判断是否有项目名称存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-10 上午11:42:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistXzmc(XmxzForm model) {
		boolean flag = false;
		String num = dao.isExistXzmc(model);
		if(!"0".equalsIgnoreCase(num)){
			flag = true;
		}
    	return  flag;
	}
	
	/** 
	 * @描述: 判断评奖性质在评奖结果当中是否存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午08:14:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkXzForPjjg(String value)throws Exception{
    	String resultXzmc = "";
    	String[] xzmc = dao.xzCheckExistForPjjg(value);
    	for(int i=0;i<xzmc.length;i++){
			if(i==xzmc.length-1){
				resultXzmc+=xzmc[i];
			}else{
				resultXzmc+=xzmc[i]+",";
			}
		}
		return resultXzmc;
	}
	
	/**
	 * @描述: 判断评奖性质在评奖项目当中是否存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午08:15:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkXzForPjxm(String value)throws Exception{
    	String resultXzmc = "";
    	String[] xzmc = dao.xzCheckExistForPjxm(value);
    	for(int i=0;i<xzmc.length;i++){
			if(i==xzmc.length-1){
				resultXzmc+=xzmc[i];
			}else{
				resultXzmc+=xzmc[i]+",";
			}
		}
		return resultXzmc;
	}
}
