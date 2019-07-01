/**
 * @部门:学工产品事业部
 * @日期：2013-5-2 上午09:45:35 
 */  
package com.zfsoft.xgxt.base.check.impl;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.check.CheckCondition;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 系统级校验学生是否符合条件
 * @作者： Penghui.Qu 
 * @时间： 2013-5-2 上午09:45:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CheckStudentCondition implements CheckCondition {

	private Log logger = LogFactory.getLog(CheckStudentCondition.class);

	
	/*
	 * 
	 * 描述: @see com.zfsoft.xgxt.base.check.CheckCondition#checkCondition(java.lang.String, java.util.List)
	 * @param xh
	 * @param conditions
	 * @return
	 * @throws Exception 
	 * @see com.zfsoft.xgxt.base.check.CheckCondition#checkCondition(java.lang.String, java.util.List)
	 */
	public List<HashMap<String,String>> checkCondition(String xh, List<HashMap<String, String>> conditions) throws Exception {
	
		List<HashMap<String,String>> checkResult = new ArrayList<HashMap<String,String>>();
		
		if (conditions == null || conditions.size() == 0){
			logger.debug("无需要验证的条件，默认为符合条件！");
			return null;
		}
		
		//循环验证
		for (HashMap<String,String> condition : conditions){
			String ffm = condition.get("ffm");
			
			if (StringUtils.isNull(ffm)){
				logger.error("未配置验证方法！");
				continue;
			}
			
			String[] checkInfo = ffm.split("#");
			
			if (checkInfo == null || checkInfo.length != 2){
				logger.error("不符合规则的验证条件！");
			}
			
			Class<?> t = Class.forName(checkInfo[0]);
			Object o = t.newInstance();
			Method method = t.getMethod(checkInfo[1], new Class[]{String.class,HashMap.class});
			
			//反射调用验证方法， 获取验证值。
			String checkValue ="";
			if ("10466".equals(Base.xxdm)) {
				String[] param = xh.split("&&");
				String value = "";
				if ("jtpj_sfqsz".equals(condition.get("tjdm"))) {
					value=param[0]+"@@"+param[1];
				}else {
					value=param[0];
				}
				checkValue = (String) method.invoke(o, value,condition);//到验证方法验证
			}else {
				checkValue = (String) method.invoke(o, xh,condition);
			}
			boolean success = compare(condition.get("tjz"), checkValue, condition.get("tjgx"));
			
			condition.put("result", String.valueOf(success));
			checkResult.add(condition);
		}
		
		return checkResult;
	}
	/**
	 * 是否满足全部条件
	 */
	public boolean checkConditionBoolean(String xh, List<HashMap<String,String>> conditions) throws Exception {
		if (conditions == null || conditions.size() == 0){
			logger.debug("无需要验证的条件，默认为符合条件！");
			return true;
		}
		
		//循环验证
		for (HashMap<String,String> condition : conditions){
			String ffm = condition.get("ffm");
			
			if (StringUtils.isNull(ffm)){
				logger.error("未配置验证方法！");
				continue;
			}
			
			String[] checkInfo = ffm.split("#");
			
			if (checkInfo == null || checkInfo.length != 2){
				logger.error("不符合规则的验证条件！");
			}
			
			Class<?> t = Class.forName(checkInfo[0]);
			Object o = t.newInstance();
			Method method = t.getMethod(checkInfo[1], new Class[]{String.class,HashMap.class});
			
			//反射调用验证方法， 获取验证值。
			String checkValue = (String) method.invoke(o, xh,condition);
			boolean success = compare(condition.get("tjz"), checkValue, condition.get("tjgx"));
			if(!success){
				return false;
			}
		}
		return true;
	}

	
	//比较验证
	private boolean compare(String value ,String checkValue ,String tjgx){
		
		if (StringUtil.isNull(checkValue)){
			return false;
		}
		
		if(checkValue.contains("||")){
			String [] cv = checkValue.split("\\|\\|");

			if (StringUtil.isNull(cv[0])||StringUtil.isNull(cv[1])||"null".equals(cv[0])||"null".equals(cv[1])){
				return false;
			}
			
			//暂时仅支持传入两个条件或
			if (EQUAL_TO.equals(tjgx)){
				//等于
				return value.equals(cv[0])||value.equals(cv[1]);
			} else if (LESS_THAN.equals(tjgx)){
				//小于
				return (Double.valueOf(cv[0]) < Double.valueOf(value))||(Double.valueOf(cv[1]) < Double.valueOf(value));
			} else if (GREAT_THAN.equals(tjgx)){
				//大于
				return (Double.valueOf(cv[0]) > Double.valueOf(value))||(Double.valueOf(cv[1]) > Double.valueOf(value));
			} else if (LESS_THAN_OR_EQUAL_TO.equals(tjgx)){
				//小于等于
				return (Double.valueOf(cv[0]) <= Double.valueOf(value))||(Double.valueOf(cv[1]) <= Double.valueOf(value));
			} else if (GREAT_THAN_OR_EQUAL_TO.equals(tjgx)){
				//大于等于
				return (Double.valueOf(cv[0]) >= Double.valueOf(value))||(Double.valueOf(cv[1]) >= Double.valueOf(value));
			} else if (IN.equals(tjgx)){
				return (Integer.valueOf(cv[0]) >= 1)||(Integer.valueOf(cv[1])>= 1);
			} else if (NOT_IN.equals(tjgx)){
				return (Integer.valueOf(cv[0]) == 0)||(Integer.valueOf(cv[1]) == 0);
			} else if (LESS_THAN_OR_EQUAL_TO_IN.equals(tjgx)){
				return (Integer.valueOf(cv[0]) >= 1)||(Integer.valueOf(cv[1]) >= 1);
			} else if (EQUAL_PM.equals(tjgx)){
				//用于比较排名
				return (Double.valueOf(cv[0]) <= Double.valueOf(value))||(Double.valueOf(cv[1]) <= Double.valueOf(value));
			}
		}else{
			if (EQUAL_TO.equals(tjgx)){
				//等于
				return value.equals(checkValue);
			} else if (LESS_THAN.equals(tjgx)){
				//小于
				return Double.valueOf(checkValue) < Double.valueOf(value);
			} else if (GREAT_THAN.equals(tjgx)){
				//大于
				return Double.valueOf(checkValue) > Double.valueOf(value);
			} else if (LESS_THAN_OR_EQUAL_TO.equals(tjgx)){
				//小于等于
				return Double.valueOf(checkValue) <= Double.valueOf(value);
			} else if (GREAT_THAN_OR_EQUAL_TO.equals(tjgx)){
				//大于等于
				return Double.valueOf(checkValue) >= Double.valueOf(value);
			} else if (IN.equals(tjgx)){
				return Integer.valueOf(checkValue) >= 1;
			} else if (NOT_IN.equals(tjgx)){
				return Integer.valueOf(checkValue) == 0;
			} else if (LESS_THAN_OR_EQUAL_TO_IN.equals(tjgx)){
				return Integer.valueOf(checkValue) >= 1;
			} else if (EQUAL_PM.equals(tjgx)){
				//用于比较排名
				return Double.valueOf(checkValue) <= Double.valueOf(value);
			}
		}
		
		return false;
	}
	
}
