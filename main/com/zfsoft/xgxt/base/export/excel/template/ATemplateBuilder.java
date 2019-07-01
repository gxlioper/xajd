package com.zfsoft.xgxt.base.export.excel.template;

import jxl.write.WritableSheet;

/**
 * Excelģ�彨���߽ӿ�
 * 
 * @author JiangDong.Yi
 * 
 */
public abstract class ATemplateBuilder {
	protected WritableSheet sheet;

	/**
	 * �����ʽ
	 */
	public abstract void buildFormat() throws Exception;

	/**
	 * ������ע
	 */
	public abstract void buildPostil() throws Exception;

	/**
	 * ��ȡExcelģ��
	 * 
	 * @return
	 */
	public WritableSheet getExcelTemplate() throws Exception {
		if (this.sheet == null) {
			return null;
		}
		this.buildFormat();
		this.buildPostil();
		return this.sheet;
	}

	/**
	 * ���ù�����
	 * 
	 * @param sheet
	 */
	public void setSheet(WritableSheet sheet) {
		this.sheet = sheet;
	}
}
