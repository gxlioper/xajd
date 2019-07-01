/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午02:23:52 
 */  
package com.zfsoft.xgxt.xljkwzdx.common;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理健康
 * @类功能描述: 
 * @作者：王志刚[工号:1060]
 * @时间： 2014-4-29 下午02:23:52 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class StringTools {
	
	static public String strOutNull(String str){
		if(str == null){
			str = "";
		}
		return str;
	}
	/**
	 * 
	 * @描述: 将Textarea里的内容转换为html形式
	 * @作者：王志刚[工号：1060]
	 * @日期：2014-5-8 下午05:20:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * String 返回类型 
	 */
	static public String StringToText(String str){
		if(str == null){
			str = "";
		}
		return str.replaceAll("\\n", "<br/>").replaceAll(" ", "&nbsp");
	}
	/**
	 * 
	 * @描述: 将html形式的内容转换为textarea
	 * @作者：王志刚[工号：1060]
	 * @日期：2014-5-8 下午05:20:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * String 返回类型 
	 */
	static public String TextToString(String str){
		if(str == null){
			str = "";
		}
		return str.replaceAll("<br/>", "\\n").replaceAll("&nbsp", "");
	}
	/**
	 * 
	 * @描述: 去除hmtl标签（<br/>,&nbsp）
	 * @作者：王志刚[工号：1060]
	 * @日期：2014-5-8 下午05:20:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param str
	 * @return
	 * String 返回类型 
	 */
	static public String TextToString1(String str){
		if(str == null){
			str = "";
		}
		return str.replaceAll("<br/>", "").replaceAll("&nbsp", "");
	}
}
