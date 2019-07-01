package xsgzgl.customForm.model;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 自定义表单_table_content_model类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class TableContentModel {

	String id;// 'ID';

	String[] old_id;// '原始ID'

	String[] table_id;// '表ID'

	String[] row_num;// '行';

	String[] column_num;// '列';

	String[] width;// '宽度';

	String[] content;// '内容';

	String[] textarea_rows;// '文本域行数';

	String[] postfix;// '后缀';

	String[] source_table;// '来源表';

	String[] select_dm;// '下拉列表代码';

	String[] select_mc;// '下拉列表名称';

	String[] checked;// '验证';
	
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
