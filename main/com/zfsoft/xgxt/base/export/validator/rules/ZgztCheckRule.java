/**
 * @部门:学工产品事业部
 * @日期：2013-12-25 上午10:09:01 
 */  
package com.zfsoft.xgxt.base.export.validator.rules;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 在岗状态验证
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2014-10-20 上午11:09:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZgztCheckRule implements IValidateRule{
	private final String _ZG="zg";
	private final String _TG="tg";
	public boolean validate(Object value) {
		if (value == null || value.toString().length() == 0) {
			return false;
		}
		if(_ZG.equals(value.toString())||_TG.equals(value.toString())){
			return true;
		}
		return false;
	}
	public String getValidateInfo() {
		return " 请填写正确的在岗状态[zg/tg]！";
	}
}
