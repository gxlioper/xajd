/**
 * @部门:学工产品事业部
 * @日期：2015-6-8 下午02:18:49 
 */  
package com.zfsoft.extend.dataFormat;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.extend.dataFormat.formator.DateField;
import com.zfsoft.extend.dataFormat.formator.DatetimeField;
import com.zfsoft.extend.dataFormat.formator.EmailField;
import com.zfsoft.extend.dataFormat.formator.FloatField;
import com.zfsoft.extend.dataFormat.formator.IDCardField;
import com.zfsoft.extend.dataFormat.formator.IntField;
import com.zfsoft.extend.dataFormat.formator.MobileField;
import com.zfsoft.extend.dataFormat.formator.NumberField;
import com.zfsoft.extend.dataFormat.formator.PatternField;
import com.zfsoft.extend.dataFormat.formator.TelField;
import com.zfsoft.extend.dataFormat.formator.ZipField;

/** 
 * @系统名称: 学生工作管理系统
 * @字段格式校验
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-8 下午02:18:49 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FieldFormat {
	
	public String[] validate(){
		 boolean check = true;
		 String errorMag = null;
		 if(this.formator != null && StringUtils.isNotBlank(this.value)){
			check = this.formator.check(value);
			if(!check){
				errorMag = this.formator.getErrorMessage(value);
			}
		 }
		 return new String[]{check?"1":"0", errorMag};
		}
	
	
	public static FieldFormat getInstance(String value , String formator){
		return new FieldFormat(value, formator);
	}
	
	/**
	 * @描述 ：TODO描述下当前构造方法
	 * @param value
	 */
	private FieldFormat(String value,String formator) {
		super();
		this.value = value;
		if(StringUtils.isNotBlank(formator)){
			this.formator = getFieldFormator(formator);
		}
	}


	private IField getFieldFormator(String config){
		IField filed = null;
		String fieldVal = config;
		String args = null;
		//如果带有参数
		if(StringUtils.contains(fieldVal,'(')){
			args = StringUtils.substringBetween(fieldVal, "(", ")");
			fieldVal = StringUtils.substringBefore(fieldVal, "(").toUpperCase();	
		}
		FIELDTYPE fielType = FIELDTYPE.fromString(fieldVal.toUpperCase());
		switch (fielType) {
		case NUMBER:
			filed = new NumberField();
			break;
		case EMAIL:
			filed = new EmailField();
			break;
		case FLOAT:
			filed = new FloatField();
			break;
		case INT:
			filed = new IntField();
			break;
		case DATE:
			if(args != null){
				filed = new DateField(args);
			}else{
				filed = new DateField();
			}
			break;
		case DATETIME:
			if(args != null){
				filed = new DatetimeField(args);
			}else{
				filed = new DatetimeField();
			}
			break;
		case IDCARD:
			filed = new IDCardField();
			break;
		case TEL:
			filed = new TelField();
			break;
		case ZIP:
			filed = new ZipField();
			break;
		case MOBILE:
			filed = new MobileField();
			break;
		case PATTERN:
			if(args != null){
				filed = new PatternField(args);
			}else{
				filed = new PatternField();
			}
			break;
		default:
			break;
		}
		return filed;
	}
	
	private String value;
	
	private IField formator;
	
}
