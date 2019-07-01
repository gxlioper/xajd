/**
 * @部门:学工产品事业部
 * @日期：2017-1-24 下午03:58:44 
 */  
package com.zfsoft.xgxt.dtjs.zzgxzc.xxjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 党团建设--信息结果模块
 * @类功能描述: 信息结果Service
 * @作者： xuwen[工号:1426]
 * @时间： 2017年2月10日 下午7:16:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class XxjgService extends SuperServiceImpl<XxjgForm,XxjgDao>{

	/** 
	 * @描述:判断信息结果中是否已有某学生记录
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月14日 下午1:20:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xxjgForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(XxjgForm xxjgForm) {
		return dao.isExist(xxjgForm);
	}

	/** 
	 * @描述:判断信息结果中介绍信标号是否已被使用
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月14日 下午1:20:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xxjgForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isJsxbhRepeat(XxjgForm xxjgForm) {
		return dao.isJsxbhRepeat(xxjgForm);
	}
	
	/**
	 * @throws Exception  
	 * @描述:重写getModel，获得所在党支部名称
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月14日 下午1:20:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xxjgForm
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public XxjgForm getModel(String jgid) throws Exception{
		return dao.getModel(jgid);
	}


	/** 
	 * @描述:根据id数组获取XxjgForm对象List
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年2月15日 下午5:06:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * List<XxjgForm> 返回类型 
	 * @throws 
	 */
	public List<XxjgForm> getXxjgFormList(String[] ids) {
		List<HashMap<String,String>> mapList = dao.getXxjgFormList(ids);
		List<XxjgForm> xxjgFormList = new ArrayList<XxjgForm>();
		for(HashMap<String,String> map:mapList){
			XxjgForm xxjgForm = new XxjgForm();
			xxjgForm.setXh(map.get("xh"));
			xxjgForm.setSzdzb(map.get("szdzb"));
			xxjgForm.setSzdzbmc(map.get("szdzbmc"));
			xxjgForm.setSfsn(map.get("sfsn"));
			xxjgForm.setJsdzz(map.get("jsdzz"));
			xxjgForm.setSqdw(map.get("sqdw"));
			xxjgForm.setDfjzrq(map.get("dfjzrq"));
			xxjgForm.setSfkjhyzm(map.get("sfkjhyzm"));
			xxjgForm.setJsxbh(map.get("jsxbh"));
			xxjgFormList.add(xxjgForm);
		}
		return xxjgFormList;
	}

	/**
	 * 
	 * @描述:党组织关系结果
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2018-1-18 下午05:37:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getDzcgxJgMap(String jgid){
		return dao.getDzcgxJgMap(jgid);
	}
}
