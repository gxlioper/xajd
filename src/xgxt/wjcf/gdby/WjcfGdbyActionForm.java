
package xgxt.wjcf.gdby;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ɳ����ѧԺΥ�ʹ���ActionForm</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-05</p>
 */
public class WjcfGdbyActionForm extends ActionForm {

	/**
	 * �Զ����ɰ汾ID
	 */
	private static final long serialVersionUID = 1L;

	private String xn;//ѧ��
	private String xq;//ѧ��
	private String nd;//���
	private String nj;//�꼶
	private String xydm;//ѧԺ����
	private String zydm;//רҵ����
	private String bjdm;//�༶����
	private String sh;//���
	private String clyj;//�������
	private String cfsj;//����ʱ��
	private String cfwh;//�����ĺ�
	private String cflb;//�������
	private String cfyy;//����ԭ��
	private String pkValue;
	private String xh;//ѧ��
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getCflb() {
		return cflb;
	}
	public void setCflb(String cflb) {
		this.cflb = cflb;
	}
	public String getCfsj() {
		return cfsj;
	}
	public void setCfsj(String cfsj) {
		this.cfsj = cfsj;
	}
	public String getCfwh() {
		return cfwh;
	}
	public void setCfwh(String cfwh) {
		this.cfwh = cfwh;
	}
	public String getCfyy() {
		return cfyy;
	}
	public void setCfyy(String cfyy) {
		this.cfyy = cfyy;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXq() {
		return xq;
	}
	public void setXq(String xq) {
		this.xq = xq;
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
	public String getClyj() {
		return clyj;
	}
	public void setClyj(String clyj) {
		this.clyj = clyj;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
	}
}
