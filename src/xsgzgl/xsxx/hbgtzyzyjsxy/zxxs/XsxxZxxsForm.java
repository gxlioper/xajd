package xsgzgl.xsxx.hbgtzyzyjsxy.zxxs;

import org.apache.struts.upload.FormFile;

import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��Уѧ��_����������ԴְҵѧԺ_Form��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxZxxsForm extends CommForm {

	private static final long serialVersionUID = 1L;

	private FormFile stuPic;// ��Ƭ�ϴ�

	private String xh; // ѧ��

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
