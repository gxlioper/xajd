/**
 * @部门:学工产品事业部
 * @日期：2014-5-5 上午09:54:47 
 */  
package com.zfsoft.xgxt.xljkwzdx.common;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温州大学）
 * @类功能描述: 将状态代码转换为中文
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-5-5 上午09:54:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZtToCnDesc {

	/** 
	 * @描述:根据预约状态代码返回中文名称
	 * @作者：王志刚[工号：1060]
	 * @日期：2014-5-5 上午09:53:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param String
	 * @return
	 */
	static public String yyztChange(String str){
		String returnstr = "";
		if("1".equals(str)){
			returnstr = "预约中";
		}else if("2".equals(str)){
			returnstr = "预约成功";
		}else if("3".equals(str)){
			returnstr = "预约中（学生取消）";
		}else if("4".equals(str)){
			returnstr = "预约成功（学生取消）";
		}else if("5".equals(str)){
			returnstr = "预约失败";
		}
		return returnstr;
	}
	
	/** 
	 * @描述:根据咨询状态代码返回中文名称
	 * @作者：王志刚[工号：1060]
	 * @日期：2014-5-7 下午01:36:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param String
	 * @return
	 */
	static public String zxztChange(String str){
		String returnstr = "";
		if("0".equals(str)){
			returnstr = "待咨询";
		}else if("1".equals(str)){
			returnstr = "已咨询";
		}else if("2".equals(str)){
			returnstr = "未咨询";
		}
		return returnstr;
	}
	
	/** 
	 * @描述:根据接受程度代码返回中文名称
	 * @作者：王志刚[工号：1060]
	 * @日期：2014-5-9 上午10:53:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param String
	 * @return
	 */
	static public String jscdChange(String str){
		String returnstr = "";
		if("hph".equals(str)){
			returnstr = "很配合";
		}else if("ybph".equals(str)){
			returnstr = "一般配合";
		}else if("za".equals(str)){
			returnstr = "阻碍";
		}
		return returnstr;
	}
	
	/** 
	 * @描述:根据接受程度代码返回中文名称
	 * @作者：王志刚[工号：1060]
	 * @日期：2014-5-9 上午11:07:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param String
	 * @return
	 */
	static public String yzcdpgChange(String str){
		String returnstr = "";
		if("hyz".equals(str)){
			returnstr = "很严重";
		}else if("bjyz".equals(str)){
			returnstr = "比较严重";
		}else if("ybcd".equals(str)){
			returnstr = "一般程度";
		}else if("jq".equals(str)){
			returnstr = "较轻";
		}else if("bswt".equals(str)){
			returnstr = "不是问题";
		}
		return returnstr;
	}
	
	/** 
	 * @描述:是否转介代码返回中文名称
	 * @作者：王志刚[工号：1060]
	 * @日期：2014-5-9 上午11:12:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param String
	 * @return
	 */
	static public String sfxyzjChange(String str){
		String returnstr = "";
		if("1".equals(str)){
			returnstr = "否";
		}else if("2".equals(str)){
			returnstr = "是";
		}
		return returnstr;
	}
	
	/** 
	 * @描述:是否预约下次咨询代码返回中文名称
	 * @作者：王志刚[工号：1060]
	 * @日期：2014-5-9 上午11:14:35
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param String
	 * @return
	 */
	static public String sfyyxczxChange(String str){
		String returnstr = "";
		if("1".equals(str)){
			returnstr = "否";
		}else if("2".equals(str)){
			returnstr = "是";
		}
		return returnstr;
	}
}
