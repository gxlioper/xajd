package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.PjpyPjlcForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��Ŀ�ϱ�_Form��
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

public class PjpyXmsbForm extends PjpyPjlcForm {

	private static final long serialVersionUID = 1L;

	PjpyXmszModel xmszModel;//��Ŀ����Model

	private String nj;// �꼶

	private String xydm;// Ժϵ

	private String zydm;// רҵ

	private String bjdm;// �༶

	private String xmdm;// ��Ŀ����

	private String xmmc;// ��Ŀ����

	private String szfw;// ���÷�Χ

	private String bmdm;// ���Ŵ���
	
	private String search_condition;//��������

	private String[] pjxh;// ����ѧ��

	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	public String getSzfw() {
		return szfw;
	}

	public void setSzfw(String szfw) {
		this.szfw = szfw;
	}

	public String getXmmc() {
		return xmmc;
	}

	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}

	public String getXmdm() {
		return xmdm;
	}

	public void setXmdm(String xmdm) {
		this.xmdm = xmdm;
	}

	public String getBjdm() {
		return bjdm;
	}

	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}

	public String getNj() {
		return nj;
	}

	public void setNj(String nj) {
		this.nj = nj;
	}

	public String getXydm() {
		return xydm;
	}

	public void setXydm(String xydm) {
		this.xydm = xydm;
	}

	public String getZydm() {
		return zydm;
	}

	public void setZydm(String zydm) {
		this.zydm = zydm;
	}

	public String[] getPjxh() {
		return pjxh;
	}

	public void setPjxh(String[] pjxh) {
		this.pjxh = pjxh;
	}

	public PjpyXmszModel getXmszModel() {
		return xmszModel;
	}

	public void setXmszModel(PjpyXmszModel xmszModel) {
		this.xmszModel = xmszModel;
	}
	
	public String getSearch_condition() {
		return search_condition;
	}

	public void setSearch_condition(String searchCondition) {
		search_condition = searchCondition;
	}
}
