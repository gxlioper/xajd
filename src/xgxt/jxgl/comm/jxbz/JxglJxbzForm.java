package xgxt.jxgl.comm.jxbz;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.upload.FormFile;

import xgxt.DAO.DAO;
import xgxt.jxgl.comm.JxglCommForm;
import xgxt.pjpy.comm.pjpy.PjpyCommForm;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ѵ����_Form��
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

public class JxglJxbzForm extends JxglCommForm {

	private static final long serialVersionUID = 1L;

	private String bzdm;// ���ƴ���

	private String bzmc;// ��������

	private String bzlx;// ��������

	private String bzdj;// ���Ƶȼ�

	private String sjdm;// �ϼ�����

	private String jsdm;// ��ʦ����

	private String jgbh;// �̹ٱ��

	private String menuId;// �˵����

	private String czlx;// ��������

	private String checkedBzdm;// ѡ�еı��ƴ���

	public String getCheckedBzdm() {
		return checkedBzdm;
	}

	public void setCheckedBzdm(String checkedBzdm) {
		this.checkedBzdm = checkedBzdm;
	}

	public String getBzlx() {
		return bzlx;
	}

	public void setBzlx(String bzlx) {
		this.bzlx = bzlx;
	}

	public String getBzdm() {
		return bzdm;
	}

	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}

	public String getBzmc() {
		return bzmc;
	}

	public void setBzmc(String bzmc) {
		this.bzmc = bzmc;
	}

	public String getBzdj() {
		return bzdj;
	}

	public void setBzdj(String bzdj) {
		this.bzdj = bzdj;
	}

	public String getJgbh() {
		return jgbh;
	}

	public void setJgbh(String jgbh) {
		this.jgbh = jgbh;
	}

	public String getJsdm() {
		return jsdm;
	}

	public void setJsdm(String jsdm) {
		this.jsdm = jsdm;
	}

	public String getSjdm() {
		return sjdm;
	}

	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getCzlx() {
		return czlx;
	}

	public void setCzlx(String czlx) {
		this.czlx = czlx;
	}

}
