
package xgxt.pjpy.ahjg;

import java.io.Serializable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս�����ҵѧԺ��������ѧϰ��������MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-19</p>
 */
public class XsjxSaveModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xh;//ѧ��
	private String xn;//ѧ��
	private String nd;//���
	private String xxjsdm;//ѧϰ��������
	private String hjsj;//��ʱ��
	private String bz;//��ע
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getHjsj() {
		return hjsj;
	}
	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getXn() {
		return xn;
	}
	public void setXn(String xn) {
		this.xn = xn;
	}
	public String getXxjsdm() {
		return xxjsdm;
	}
	public void setXxjsdm(String xxjsdm) {
		this.xxjsdm = xxjsdm;
	}
}
