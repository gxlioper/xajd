/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-8 ����02:18:49 
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
 * @ϵͳ����: ѧ����������ϵͳ
 * @�ֶθ�ʽУ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-8 ����02:18:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
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
	 * @���� ��TODO�����µ�ǰ���췽��
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
		//������в���
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
