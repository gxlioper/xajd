package xsgzgl.jxgl.general.jxjzgl.jxjzgl;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ѵ���ƹ���_Model��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author �׽���
 * @version 1.0
 */

public class JxjzglFrom extends CommForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	//��ѵ���Ƶȼ���
	private String djdm;	//����
	private String djmc;	//����
	private String djdj;	//�ȼ�
	
	//��ѵ����������
	private String mdid;	//����ID
	private String jzid;	//����ID
	private String xh;	//ѧ��
	
	//��ѵ����ά����
	private String jzmc;	//��������
	private String jzjb;	//���Ƽ���
	private String sjid;	//�ϼ�ID
	private String jgmc;	//�̹�����
	private String jgdh;	//�̹ٵ绰
	private String jsmc;	//��ʦ����
	private String jsdh;	//��ʦ�绰
	
	//��ҵ���ֶ�
	private String jxid;	//��ѵID
	private ExportModel exportModel = new ExportModel();
	
	public String getDjdm() {
		return djdm;
	}
	public void setDjdm(String djdm) {
		this.djdm = djdm;
	}
	public String getDjmc() {
		return djmc;
	}
	public void setDjmc(String djmc) {
		this.djmc = djmc;
	}
	public String getDjdj() {
		return djdj;
	}
	public void setDjdj(String djdj) {
		this.djdj = djdj;
	}
	public String getMdid() {
		return mdid;
	}
	public void setMdid(String mdid) {
		this.mdid = mdid;
	}
	public String getJzid() {
		return jzid;
	}
	public void setJzid(String jzid) {
		this.jzid = jzid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getJzmc() {
		return jzmc;
	}
	public void setJzmc(String jzmc) {
		this.jzmc = jzmc;
	}
	public String getJzjb() {
		return jzjb;
	}
	public void setJzjb(String jzjb) {
		this.jzjb = jzjb;
	}
	public String getSjid() {
		return sjid;
	}
	public void setSjid(String sjid) {
		this.sjid = sjid;
	}
	public String getJgmc() {
		return jgmc;
	}
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}
	public String getJgdh() {
		return jgdh;
	}
	public void setJgdh(String jgdh) {
		this.jgdh = jgdh;
	}
	public String getJsmc() {
		return jsmc;
	}
	public void setJsmc(String jsmc) {
		this.jsmc = jsmc;
	}
	public String getJsdh() {
		return jsdh;
	}
	public void setJsdh(String jsdh) {
		this.jsdh = jsdh;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getJxid() {
		return jxid;
	}
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

}
