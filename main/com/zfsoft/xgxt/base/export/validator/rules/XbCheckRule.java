/**
 * @部门:学工产品事业部
 * @日期：2013-12-25 上午10:09:01 
 */  
package com.zfsoft.xgxt.base.export.validator.rules;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 性别验证
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2013-12-25 上午10:09:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XbCheckRule implements IValidateRule{
	private final String _MAN="男";
	private final String _WOMAN="女";
	public boolean validate(Object value) {
		if (value == null || value.toString().length() == 0) {
			return false;
		}
		if(_MAN.equals(value.toString())||_WOMAN.equals(value.toString())){
			return true;
		}
		return false;
	}
	public String getValidateInfo() {
		return " 请填写正确的性别[男/女]！";
	}
}
