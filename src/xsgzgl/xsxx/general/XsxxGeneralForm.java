package xsgzgl.xsxx.general;

import org.apache.struts.upload.FormFile;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_ͨ��_Form��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxGeneralForm extends CommForm {

	private static final long serialVersionUID = 1L;

	private User user;
	
	private String guid;
	
	private FormFile stuPic;// ��Ƭ�ϴ�
	private FormFile stuGkPic;//�߿���Ƭ

	private String xh; // ѧ��
	
	private String xm;
	
	private String xb;
	
	private String nj;
	
	private String xz;
	
	private String bjmc;
	
	private String spgw;

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

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXz() {
		return xz;
	}

	public void setXz(String xz) {
		this.xz = xz;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getSpgw() {
		return spgw;
	}

	public void setSpgw(String spgw) {
		this.spgw = spgw;
	}

	/**
	 * @return the stuGkPic
	 */
	public FormFile getStuGkPic() {
		return stuGkPic;
	}

	/**
	 * @param stuGkPicҪ���õ� stuGkPic
	 */
	public void setStuGkPic(FormFile stuGkPic) {
		this.stuGkPic = stuGkPic;
	}
	
	
	
	
}
