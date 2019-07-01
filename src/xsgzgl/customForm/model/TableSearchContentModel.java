package xsgzgl.customForm.model;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 自定义表单_查询内容_model类
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

public class TableSearchContentModel {

	String gnmk_id;// '功能模块ID';

	String[] content_id;// '内容ID';

	String[] xssx;// '显示顺序';

	public String[] getContent_id() {
		return content_id;
	}

	public void setContent_id(String[] content_id) {
		this.content_id = content_id;
	}

	public String getGnmk_id() {
		return gnmk_id;
	}

	public void setGnmk_id(String gnmk_id) {
		this.gnmk_id = gnmk_id;
	}

	public String[] getXssx() {
		return xssx;
	}

	public void setXssx(String[] xssx) {
		this.xssx = xssx;
	}

}
