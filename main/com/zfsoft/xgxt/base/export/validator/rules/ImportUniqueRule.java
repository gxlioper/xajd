package com.zfsoft.xgxt.base.export.validator.rules;

import java.util.HashMap;

import com.zfsoft.xgxt.base.export.util.DateUtils;


/**
 * ��������Ψһ��֤���� 
 * <li>������Ի���֤����ͨ����֤</li> 
 * <li>���ݲ���Ψһ�ķ��أ�false</li> 
 * <li>������Ψһ�ķ��أ�true
 * </li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ImportUniqueRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "�����Ѵ��ڣ�";
	private HashMap<String, Object> excelSourceData = null;
	private HashMap<String, Object> oracleSourceData = null;

	public ImportUniqueRule(HashMap<String, Object> excelSourceData,
			HashMap<String, Object> oracleSourceData) {
		super();
		this.excelSourceData = excelSourceData;
		this.oracleSourceData = oracleSourceData;
	}

	public ImportUniqueRule() {
		super();
		this.oracleSourceData = new HashMap<String, Object>();
		this.excelSourceData = new HashMap<String, Object>();
	}

	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
	}
	public boolean validate(Object value) {
		if (value == null) {
			return false;
		}

		// ��֤�������Ƿ�Ψһ
		if (oracleSourceData.containsKey(value.toString())) {
			this.validateInfo = "���ݿ��д�����ͬ���ݣ�";
			return false;
		}
		// ��֤����Excel���Ƿ�Ψһ
		if (excelSourceData.containsKey(value.toString())) {
			this.validateInfo = "���������д�����ͬ���ݣ�";
			return false;
		}

		return true;

	}

	public void setValidateInfo(String validateInfo) {
		this.validateInfo = validateInfo;
	}

}
