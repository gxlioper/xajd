package xgxt.qgzx.comm.gwsqwh;

import org.apache.struts.action.ActionForm;

import xgxt.qgzx.QgzxTyForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 勤工助学_学生岗位申请-actionform类
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
	
	private String gwdmsbsj;//岗位代码上报时间
	private String save_xh;//学号（通用保存结构）
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
