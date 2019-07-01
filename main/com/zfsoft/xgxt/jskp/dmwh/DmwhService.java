package com.zfsoft.xgxt.jskp.dmwh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class DmwhService extends SuperServiceImpl<DmwhForm,DmwhDao> {
	/**
	 * 
	 * @描述: 项目类别List
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-6 下午03:46:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbList(){
		return dao.getXmlbList();
	}
	
	/**
	 * 
	 * @描述: 验证代码是否已被使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-21 下午03:15:07
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmid
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsDmIsNotUserd(String[] xmlbdms){
		return dao.checkIsDmIsNotUserd(xmlbdms);
	}
	
	/**
	 * 
	 * @描述: 验证项目类别名称是否被使用
	 * @作者：喻鑫源[工号：1206]
	 * @日期：2017-7-21 下午04:14:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xmlbmc
	 * @param xmlbdm
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkMcIsNotUserd(String xmlbmc,String xmlbdm){
		return dao.checkMcIsNotUserd(xmlbmc, xmlbdm);
	}
	
	/**
	 * @描述: 项目类别数据-所有
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-29 下午04:00:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListByAll(){
		return dao.getXmlbListByAll();
	}
	
	/**
	 * @描述: 项目类别数据-能力素养
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-29 下午04:00:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListByNlsy(){
		return dao.getXmlbListByNlsy();
	}
	
	
	/**
	 * @描述: 项目类别数据-思想政治素质
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2018-5-29 下午04:00:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXmlbListBySzsz(){
		return dao.getXmlbListBySzsz();
	}
}
