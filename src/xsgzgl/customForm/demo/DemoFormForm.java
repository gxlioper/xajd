package xsgzgl.customForm.demo;

import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: 學生工作管理系統
 * </p>
 * <p>
 * Description: 系統維護_自定義表單_DEMO_Form类
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

public class DemoFormForm extends CommForm {

	private static final long serialVersionUID = 1L;
	
	// FormID
	private String form_id;

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String formId) {
		form_id = formId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
