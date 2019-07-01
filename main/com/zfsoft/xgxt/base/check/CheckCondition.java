/**
 * @部门:学工产品事业部
 * @日期：2013-5-2 上午09:41:16 
 */  
package com.zfsoft.xgxt.base.check;

import java.util.HashMap;
import java.util.List;


/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 学生条件验证中心
 * @作者： Penghui.Qu 
 * @时间： 2013-5-2 上午09:41:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public interface CheckCondition {
	
	 static final String LESS_THAN = "<";//小于
	 static final String GREAT_THAN = ">";//大于
	 static final String EQUAL_TO = "=";//等于
	 static final String GREAT_THAN_OR_EQUAL_TO = ">=";//大于等于
	 static final String LESS_THAN_OR_EQUAL_TO = "<=";//小于等于
	 static final String IN = "in";//包含
	 static final String NOT_IN = "notin";//不包含
	 static final String EQUAL_PM = "!<";//用于比较排名
	 static final String LESS_THAN_OR_EQUAL_TO_IN = "<=in";//小于等于(不判断条件值)
	
	
	
	/**
	 * 
	 * @描述:校验条件
	 * @作者：Penghui.Qu
	 * @日期：2013-5-2 上午09:44:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh 用户
	 * @param conditions 条件集
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> checkCondition(String xh, List<HashMap<String,String>> conditions) throws Exception;
	
	/**
	 * 是否满足全部条件
	 */
	public boolean checkConditionBoolean(String xh, List<HashMap<String,String>> conditions) throws Exception;
}
