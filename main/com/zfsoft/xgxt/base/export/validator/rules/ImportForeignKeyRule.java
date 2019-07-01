package com.zfsoft.xgxt.base.export.validator.rules;

import java.util.HashMap;

import com.zfsoft.xgxt.base.export.util.DateUtils;


/**
 * 导入数据外键验证规则。 <li>导入功能自定义数据验证，非通用验证</li>
 * 
 * @author Jiangdong.Yi
 * 
 */
public class ImportForeignKeyRule implements IValidateRule {
	private String validateInfo;
	private final String defaultMessage = "数据不存在！";
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
		// 验证数据中是否唯一
		if (!oracleSourceData.containsKey(value.toString())) {
			this.validateInfo = "在数据库中不存在！";
			return false;
		} else {
			// 若数据库中已存在则替换验证值
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
