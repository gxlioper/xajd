package xgxt.pjpy.comm.pjpy.lcsz;

import xgxt.pjpy.comm.pjpy.PjpyCommForm;

public class PjpyLcszForm extends PjpyCommForm {

	private static final long serialVersionUID = 1L;

	private String lcdj;// ���̵ȼ�

	private String lcmc;// ��������

	private String lcsm;// ����˵��

	private String tzlj;// ��ת·��
	
	private String pkValue;// ����ֵ
	
	public String getPkValue() {
		return pkValue;
	}

	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}

	public String getLcdj() {
		return lcdj;
	}

	public void setLcdj(String lcdj) {
		this.lcdj = lcdj;
	}

	public String getLcmc() {
		return lcmc;
	}

	public void setLcmc(String lcmc) {
		this.lcmc = lcmc;
	}

	public String getLcsm() {
		return lcsm;
	}

	public void setLcsm(String lcsm) {
		this.lcsm = lcsm;
	}

	public String getTzlj() {
		return tzlj;
	}

	public void setTzlj(String tzlj) {
		this.tzlj = tzlj;
	}
}
