package xsgzgl.customForm.model;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 自定义表单_table_model类
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

public class TableModel {

	String gnmk_id;//功能模块ID
	
	String[] id;// 'ID';

	String[] title;// '标题';

	String[] row_all;// '行数'

	String[] create_time;// '创建时间'

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getRow_all() {
		return row_all;
	}

	public void setRow_all(String[] row_all) {
		this.row_all = row_all;
	}

	public String[] getTitle() {
		return title;
	}

	public void setTitle(String[] title) {
		this.title = title;
	}

	public String[] getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String[] create_time) {
		this.create_time = create_time;
	}

	public String getGnmk_id() {
		return gnmk_id;
	}

	public void setGnmk_id(String gnmk_id) {
		this.gnmk_id = gnmk_id;
	}
}
