package xsgzgl.customForm.model;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �Զ����_table_content_model��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class TableContentModel {

	String id;// 'ID';

	String[] old_id;// 'ԭʼID'

	String[] table_id;// '��ID'

	String[] row_num;// '��';

	String[] column_num;// '��';

	String[] width;// '���';

	String[] content;// '����';

	String[] textarea_rows;// '�ı�������';

	String[] postfix;// '��׺';

	String[] source_table;// '��Դ��';

	String[] select_dm;// '�����б����';

	String[] select_mc;// '�����б�����';

	String[] checked;// '��֤';
	
	public String[] getColumn_num() {
		return column_num;
	}

	public void setColumn_num(String[] column_num) {
		this.column_num = column_num;
	}

	public String[] getContent() {
		return content;
	}

	public void setContent(String[] content) {
		this.content = content;
	}

	public String[] getPostfix() {
		return postfix;
	}

	public void setPostfix(String[] postfix) {
		this.postfix = postfix;
	}

	public String[] getRow_num() {
		return row_num;
	}

	public void setRow_num(String[] row_num) {
		this.row_num = row_num;
	}

	public String[] getSelect_dm() {
		return select_dm;
	}

	public void setSelect_dm(String[] select_dm) {
		this.select_dm = select_dm;
	}

	public String[] getSelect_mc() {
		return select_mc;
	}

	public void setSelect_mc(String[] select_mc) {
		this.select_mc = select_mc;
	}

	public String[] getSource_table() {
		return source_table;
	}

	public void setSource_table(String[] source_table) {
		this.source_table = source_table;
	}

	public String[] getTextarea_rows() {
		return textarea_rows;
	}

	public void setTextarea_rows(String[] textarea_rows) {
		this.textarea_rows = textarea_rows;
	}

	public String[] getWidth() {
		return width;
	}

	public void setWidth(String[] width) {
		this.width = width;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getTable_id() {
		return table_id;
	}

	public void setTable_id(String[] table_id) {
		this.table_id = table_id;
	}

	public String[] getOld_id() {
		return old_id;
	}

	public void setOld_id(String[] old_id) {
		this.old_id = old_id;
	}

	public String[] getChecked() {
		return checked;
	}

	public void setChecked(String[] checked) {
		this.checked = checked;
	}
}
