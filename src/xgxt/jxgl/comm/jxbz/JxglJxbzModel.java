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
 * Description: ��ѵ����_��ѵ����_Model��
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

public class JxglJxbzModel {

	private String[] bzdm;// ���ƴ���

	private String[] bzmc;// ��������

	private String xn;// ��������

	private String bzdj;// ���Ƶȼ�

	private String sjdm;// �ϼ�����

	public String getBzdj() {
		return bzdj;
	}

	public void setBzdj(String bzdj) {
		this.bzdj = bzdj;
	}

	public String[] getBzdm() {
		return bzdm;
	}

	public void setBzdm(String[] bzdm) {
		this.bzdm = bzdm;
	}

	public String[] getBzmc() {
		return bzmc;
	}

	public void setBzmc(String[] bzmc) {
		this.bzmc = bzmc;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getSjdm() {
		return sjdm;
	}

	public void setSjdm(String sjdm) {
		this.sjdm = sjdm;
	}

}
