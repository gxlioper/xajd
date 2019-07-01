package xsgzgl.xsxx.hbgtzyzyjsxy.zxxs;

import org.apache.struts.upload.FormFile;

import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_在校学生_湖北国土资源职业学院_Form类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxZxxsForm extends CommForm {

	private static final long serialVersionUID = 1L;

	private FormFile stuPic;// 照片上传

	private String xh; // 学号

	public FormFile getStuPic() {
		return stuPic;
	}

	public void setStuPic(FormFile stuPic) {
		this.stuPic = stuPic;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}
}
