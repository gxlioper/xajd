package xsgzgl.xtwh.general.customform;

import org.apache.struts.upload.FormFile;

import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: �W����������ϵ�y
 * </p>
 * <p>
 * Description: ϵ�y�S�o_�Զ��x���_Form��
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

public class CustomFormForm extends CommForm {

	private static final long serialVersionUID = 1L;
	
	private FormFile stuPic;// ��Ƭ�ϴ�

	public FormFile getStuPic() {
		return stuPic;
	}

	public void setStuPic(FormFile stuPic) {
		this.stuPic = stuPic;
	}
}
