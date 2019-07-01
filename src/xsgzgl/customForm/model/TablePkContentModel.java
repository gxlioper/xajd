package xsgzgl.customForm.model;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 自定义表单_主键_model类
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

public class TablePkContentModel {

	String id;// 'ID';

	String gnmk_id;// '功能模块ID';

	String[] pk_id;// '主键ID';

	String[] xssx;// '显示顺序';

	public String getGnmk_id() {
		return gnmk_id;
	}

	public void setGnmk_id(String gnmk_id) {
		this.gnmk_id = gnmk_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getPk_id() {
		return pk_id;
	}

	public void setPk_id(String[] pk_id) {
		this.pk_id = pk_id;
	}

	public String[] getXssx() {
		return xssx;
	}

	public void setXssx(String[] xssx) {
		this.xssx = xssx;
	}

}
