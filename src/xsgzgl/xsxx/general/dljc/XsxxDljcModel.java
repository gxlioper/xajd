package xsgzgl.xsxx.general.dljc;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��¼���_ͨ��_Model��
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

public class XsxxDljcModel {

	private String[] pkValue;// ����
	private String checked;// ��ѡ���
	private String[] nj;// �꼶
	private String[] xy;// ѧԺ
	private String[] zy;// רҵ
	private String[] bj;// �༶
	private String[] zd;// �ֶ�

	private boolean isXxws; // �Ƿ���Ϣ����

	public boolean isXxws() {
		return isXxws;
	}

	public void setXxws(boolean isXxws) {
		this.isXxws = isXxws;
	}

	public String[] getNj() {
		return nj;
	}

	public void setNj(String[] nj) {
		this.nj = nj;
	}

	public String[] getXy() {
		return xy;
	}

	public void setXy(String[] xy) {
		this.xy = xy;
	}

	public String[] getZy() {
		return zy;
	}

	public void setZy(String[] zy) {
		this.zy = zy;
	}

	public String[] getBj() {
		return bj;
	}

	public void setBj(String[] bj) {
		this.bj = bj;
	}

	public String[] getPkValue() {
		return pkValue;
	}

	public void setPkValue(String[] pkValue) {
		this.pkValue = pkValue;
	}

	public String[] getZd() {
		return zd;
	}

	public void setZd(String[] zd) {
		this.zd = zd;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
}
