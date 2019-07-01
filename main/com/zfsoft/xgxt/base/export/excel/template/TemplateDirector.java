package com.zfsoft.xgxt.base.export.excel.template;

/**
 * ģ�彨����
 * 
 * @author JiangDong.Yi
 * 
 */
public class TemplateDirector {
	private ATemplateBuilder templateBuilder;

	/**
	 * ע�뽨�칤����
	 * 
	 * @param templateBuilder
	 */
	public TemplateDirector(ATemplateBuilder templateBuilder) {
		this.templateBuilder = templateBuilder;
	}

	/**
	 * ����ģ��
	 */
	public void constructTemplate() throws Exception {
		templateBuilder.getExcelTemplate();
	}
}
