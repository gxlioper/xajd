package com.zfsoft.xgxt.base.export.validator.rules;

import java.util.HashMap;

import com.zfsoft.xgxt.base.export.util.DateUtils;


/**
 * �������������֤���� <li>���빦���Զ���������֤����ͨ����֤</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ImportForeignKeyRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "���ݲ����ڣ�";
	private HashMap<String, Object> oracleSourceData = null;

	public ImportForeignKeyRule(HashMap<String, Object> oracleSourceData) {
		super();
		this.oracleSourceData = oracleSourceData;
	}

	public ImportForeignKeyRule() {
		super();
	}

	public String getValidateInfo() {
		return DateUtils.defaultIfEmpty(this.validateInfo, defaultMessage);
	}

	public boolean validate(Object value) {
		if (value == null) {
			return false;
		}
		// ��֤�������Ƿ�Ψһ
		if (!oracleSourceData.containsKey(value.toString())) {
			this.validateInfo = "�����ݿ��в����ڣ�";
			return false;
		} else {
			// �����ݿ����Ѵ������滻��ֵ֤
			StringBuffer sb = (StringBuffer) value;
			sb.replace(0, sb.length(), oracleSourceData.get(sb.toString())
					.toString());
		}

		return true;

	}

	public void setValidateInfo(String validateInfo) {
		this.validateInfo = validateInfo;
	}

}
