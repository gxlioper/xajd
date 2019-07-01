package xsgzgl.xtwh.general.customform;

import org.apache.struts.upload.FormFile;

import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_Form类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 偉大的駱
 * @version 1.0
 */

public class CustomFormForm extends CommForm {

	private static final long serialVersionUID = 1L;
	
	private FormFile stuPic;// 照片上传

	public FormFile getStuPic() {
		return stuPic;
	}

	public void setStuPic(FormFile stuPic) {
		this.stuPic = stuPic;
	}
}
