package xsgzgl.xtwh.general;

import org.apache.struts.upload.FormFile;

import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 系統維護_通用_Form类
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

public class XtwhGeneralForm extends CommForm {

	private static final long serialVersionUID = 1L;
	
	private FormFile stuPic;// 照片上传

	public FormFile getStuPic() {
		return stuPic;
	}

	public void setStuPic(FormFile stuPic) {
		this.stuPic = stuPic;
	}
}
