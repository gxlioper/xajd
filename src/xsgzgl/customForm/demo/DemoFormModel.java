package xsgzgl.customForm.demo;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�Զ��x���_DEMO_Model��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */

public class DemoFormModel {

	private String table_id; // TableID
	private String form_id; // FormID
	private String ssb; // ���ٱ�
	private String title; // ���}
	private String row_num; // �Д�
	private String xssx; // �@ʾ���
	private String[] td_id; // TdID

	public String[] getTd_id() {
		return td_id;
	}

	public void setTd_id(String[] tdId) {
		td_id = tdId;
	}

	public String getSsb() {
		return ssb;
	}

	public void setSsb(String ssb) {
		this.ssb = ssb;
	}

	public String getTitle() {
		return title;
	}

	public String getTable_id() {
		return table_id;
	}

	public void setTable_id(String tableId) {
		table_id = tableId;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String formId) {
		form_id = formId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public String getRow_num() {
		return row_num;
	}

	public void setRow_num(String rowNum) {
		row_num = rowNum;
	}
}
