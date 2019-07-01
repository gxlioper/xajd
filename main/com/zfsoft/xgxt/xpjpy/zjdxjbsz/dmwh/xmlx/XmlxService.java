/**
 * @部门:学工产品事业部
 * @日期：2017-3-9 上午09:40:08 
 */  
package com.zfsoft.xgxt.xpjpy.zjdxjbsz.dmwh.xmlx;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 评奖评优管理模块
 * @类功能描述: 代码维护_项目类型
 * @作者：Meng.Wei[工号:1186]
 * @时间： 2017-3-9 上午09:39:56 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XmlxService extends SuperServiceImpl<XmlxForm, XmlxDao>{
	private XmlxDao dao = new XmlxDao();
	
	public XmlxService() {
		super.setDao(dao);
	}
	
	/**
	 * @描述: 增加判断是否有项目名称存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午08:13:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean isExistLxmc(XmlxForm model) {
		boolean flag = false;
		String num = dao.isExistLxmc(model);
		if(!"0".equalsIgnoreCase(num)){
			flag = true;
		}
    	return  flag;
	}
	
	/** 
	 * @描述: 判断评奖性质在评奖结果当中是否存在
	 * @作者：Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午08:14:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkLxForPjjg(String value)throws Exception{
    	String resultLxmc = "";
    	String[] lxmc = dao.lxCheckExistForPjjg(value);
    	for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultLxmc+=lxmc[i];
			}else{
				resultLxmc+=lxmc[i]+",";
			}
		}
		return resultLxmc;
	}
	
	/**
	 * @描述: 判断评奖类型在评奖项目当中是否存在
	 * @作者： Meng.Wei[工号:1186]
	 * @日期：2017-3-9 下午08:15:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param value
	 * @return
	 * @throws Exception
	 * String 返回类型 
	 * @throws
	 */
	public String checkLxForPjxm(String value)throws Exception{
    	String resultLxmc = "";
    	String[] lxmc = dao.lxCheckExistForPjxm(value);
    	for(int i=0;i<lxmc.length;i++){
			if(i==lxmc.length-1){
				resultLxmc+=lxmc[i];
			}else{
				resultLxmc+=lxmc[i]+",";
			}
		}
		return resultLxmc;
	}
	
	/**
	 * @描述: 取项目类型
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-2-10 上午11:33:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlx() throws Exception {
		return dao.getXmlx();
	}
}
