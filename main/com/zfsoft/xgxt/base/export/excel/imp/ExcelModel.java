package com.zfsoft.xgxt.base.export.excel.imp;

import com.zfsoft.xgxt.base.export.excel.template.ATemplateBuilder;

/**
 * ����ҳ��Ļ�������
 * 
 * @author Administrator
 * 
 */
public class ExcelModel {
	// ��ʱ�ļ�·��
	public String tempFilePath = "";
	// ��ʱ�ļ�����
	public String tempFileName = "";
	// ����Ĭ������
	private String sheetName = "sheet1";// ����������
	// Excel��ʽģ��
	private ATemplateBuilder templateBuilder = null;

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getTempFilePath() {
		return tempFilePath;
	}

	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
	}

	public String getTempFileName() {
		return tempFileName;
	}

	public void setTempFileName(String tempFileName) {
		this.tempFileName = tempFileName;
	}

	public ATemplateBuilder getTemplateBuilder() {
		return templateBuilder;
	}

	public void setTemplateBuilder(ATemplateBuilder templateBuilder) {
		this.templateBuilder = templateBuilder;
	}
}
