package xgxt.qgzx.comm.gwsqwh;

import org.apache.struts.action.ActionForm;

import xgxt.qgzx.QgzxTyForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ڹ���ѧ_ѧ����λ����-actionform��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author lt
 * @version 1.0
 */
public class QgzxXsgwsqForm extends QgzxTyForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String gwxz;
	private String gzksrq;
	private String gzjsrq;
	
	private String gwdmsbsj;//��λ�����ϱ�ʱ��
	private String save_xh;//ѧ�ţ�ͨ�ñ���ṹ��
	private String[] primarykey_cbv;
	
	
	public String[] getPrimarykey_cbv() {
		return primarykey_cbv;
	}
	public void setPrimarykey_cbv(String[] primarykey_cbv) {
		this.primarykey_cbv = primarykey_cbv;
	}
	public String getGwxz() {
		return gwxz;
	}
	public void setGwxz(String gwxz) {
		this.gwxz = gwxz;
	}
	public String getGzjsrq() {
		return gzjsrq;
	}
	public void setGzjsrq(String gzjsrq) {
		this.gzjsrq = gzjsrq;
	}
	public String getGzksrq() {
		return gzksrq;
	}
	public void setGzksrq(String gzksrq) {
		this.gzksrq = gzksrq;
	}
	public String getGwdmsbsj() {
		return gwdmsbsj;
	}
	public void setGwdmsbsj(String gwdmsbsj) {
		this.gwdmsbsj = gwdmsbsj;
	}
	public String getSave_xh() {
		return save_xh;
	}
	public void setSave_xh(String saveXh) {
		save_xh = saveXh;
	}

}
