package xsgzgl.xtwh.general.customform;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�Զ��x���_Model��
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

public class CustomFormModel {

	private String[] pkValue;// ����
	private String lx;// ����
	private String contextPath;//����·��

	// ===============������ begin=====================
	private String form_id;// '��ID';
	private String form_name;// '������';
	private String ssmk;// '����ģ��';
	private String source_table;// '���ݱ�';
	private String source_table_pk;// '���ݱ�������';
	private String assistant_table_one;// '������1';
	private String assistant_table_one_zd;// '������1�ֶ�';
	private String assistant_table_one_relate;// '������1����';
	private String assistant_table_two;// '������2';
	private String assistant_table_two_zd;// '������2�ֶ�';
	private String assistant_table_two_relate;// '������2����';
	private String detail_view;// '��ϸ��ͼ';
	private String search_view;// '��ѯ��ͼ';
	private String create_time;// '����ʱ��';
	// ===============������ end=====================

	// ===============������ begin=====================
	private String title;// '����';
	private String table_id;// 'TableID';
	private String row_num;// '����';
	private String column_num;// '����';
	private String xssx;// '��ʾ˳��';
	private String rowspan;// '�ϲ���';
	private String colspan;// '�ϲ���';
	// ===============������ end=====================

	// ===============�ֶ����� begin===================
	private String zd;// '�ֶ�';
	private String zdm;// '�ֶ���';
	private String ssb;// '���ٱ�';
	private String zdlx;// '�ֶ����';
	private String input_width;// '���';
	private String textarea_rows;// '�ı�������';
	private String input_postfix;// '��׺';
	private String option_dm;// '�����б����';
	private String option_mc;// '�����б�����';
	private String checked;// '��֤';
	private String onlyone;// 'Ψһ';
	private String isnull;// '����Ϊ��';
	private String edit;// '�ɷ�༭';
	// ===============�ֶ����� end=====================

	// ===============������� begin===================
	private String area_lx;// '��������';
	private String area_jb;// '��������';
	private String area_dm;// '��������';
	private String area_mc;// '��������';
	// ===============������� end===================

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String formId) {
		form_id = formId;
	}

	public String getArea_mc() {
		return area_mc;
	}

	public void setArea_mc(String areaMc) {
		area_mc = areaMc;
	}

	public String getIsnull() {
		return isnull;
	}

	public String getArea_lx() {
		return area_lx;
	}

	public void setArea_lx(String areaLx) {
		area_lx = areaLx;
	}

	public String getArea_jb() {
		return area_jb;
	}

	public void setArea_jb(String areaJb) {
		area_jb = areaJb;
	}

	public String getArea_dm() {
		return area_dm;
	}

	public void setArea_dm(String areaDm) {
		area_dm = areaDm;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public String getEdit() {
		return edit;
	}

	public void setEdit(String edit) {
		this.edit = edit;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getOnlyone() {
		return onlyone;
	}

	public String getSource_table_pk() {
		return source_table_pk;
	}

	public void setSource_table_pk(String sourceTablePk) {
		source_table_pk = sourceTablePk;
	}

	public String getAssistant_table_one() {
		return assistant_table_one;
	}

	public void setAssistant_table_one(String assistantTableOne) {
		assistant_table_one = assistantTableOne;
	}

	public String getAssistant_table_one_zd() {
		return assistant_table_one_zd;
	}

	public void setAssistant_table_one_zd(String assistantTableOneZd) {
		assistant_table_one_zd = assistantTableOneZd;
	}

	public String getAssistant_table_one_relate() {
		return assistant_table_one_relate;
	}

	public void setAssistant_table_one_relate(String assistantTableOneRelate) {
		assistant_table_one_relate = assistantTableOneRelate;
	}

	public String getAssistant_table_two() {
		return assistant_table_two;
	}

	public void setAssistant_table_two(String assistantTableTwo) {
		assistant_table_two = assistantTableTwo;
	}

	public String getAssistant_table_two_zd() {
		return assistant_table_two_zd;
	}

	public void setAssistant_table_two_zd(String assistantTableTwoZd) {
		assistant_table_two_zd = assistantTableTwoZd;
	}

	public String getAssistant_table_two_relate() {
		return assistant_table_two_relate;
	}

	public void setAssistant_table_two_relate(String assistantTableTwoRelate) {
		assistant_table_two_relate = assistantTableTwoRelate;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String createTime) {
		create_time = createTime;
	}

	public void setOnlyone(String onlyone) {
		this.onlyone = onlyone;
	}

	public String getForm_name() {
		return form_name;
	}

	public void setForm_name(String formName) {
		form_name = formName;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getZdm() {
		return zdm;
	}

	public void setZdm(String zdm) {
		this.zdm = zdm;
	}

	public String getSsb() {
		return ssb;
	}

	public void setSsb(String ssb) {
		this.ssb = ssb;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public String getInput_width() {
		return input_width;
	}

	public void setInput_width(String inputWidth) {
		input_width = inputWidth;
	}

	public String getTextarea_rows() {
		return textarea_rows;
	}

	public void setTextarea_rows(String textareaRows) {
		textarea_rows = textareaRows;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}
	
	public String getInput_postfix() {
		return input_postfix;
	}

	public void setInput_postfix(String inputPostfix) {
		input_postfix = inputPostfix;
	}

	public String getSource_table() {
		return source_table;
	}

	public void setSource_table(String sourceTable) {
		source_table = sourceTable;
	}

	public String getOption_dm() {
		return option_dm;
	}

	public void setOption_dm(String optionDm) {
		option_dm = optionDm;
	}

	public String getOption_mc() {
		return option_mc;
	}

	public void setOption_mc(String optionMc) {
		option_mc = optionMc;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getSsmk() {
		return ssmk;
	}

	public void setSsmk(String ssmk) {
		this.ssmk = ssmk;
	}

	public String getDetail_view() {
		return detail_view;
	}

	public void setDetail_view(String detailView) {
		detail_view = detailView;
	}

	public String getSearch_view() {
		return search_view;
	}

	public void setSearch_view(String searchView) {
		search_view = searchView;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTable_id() {
		return table_id;
	}

	public void setTable_id(String tableId) {
		table_id = tableId;
	}

	public String getRow_num() {
		return row_num;
	}

	public void setRow_num(String rowNum) {
		row_num = rowNum;
	}

	public String getColumn_num() {
		return column_num;
	}

	public void setColumn_num(String columnNum) {
		column_num = columnNum;
	}

	public String getXssx() {
		return xssx;
	}

	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	public String getRowspan() {
		return rowspan;
	}

	public void setRowspan(String rowspan) {
		this.rowspan = rowspan;
	}

	public String getColspan() {
		return colspan;
	}

	public void setColspan(String colspan) {
		this.colspan = colspan;
	}
}
