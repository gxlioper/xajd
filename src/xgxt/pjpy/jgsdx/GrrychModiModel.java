
package xgxt.pjpy.jgsdx;

import java.io.Serializable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧ�������������ƺŵ������MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-09</p>
 */
public class GrrychModiModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pkValue;//����
	private String rychdm;//�����ƺŴ���
	private String sh;//���
	private String shyj;//������
	public String getPkValue() {
		return pkValue;
	}
	public void setPkValue(String pkValue) {
		this.pkValue = pkValue;
	}
	public String getRychdm() {
		return rychdm;
	}
	public void setRychdm(String rychdm) {
		this.rychdm = rychdm;
	}
	public String getSh() {
		return sh;
	}
	public void setSh(String sh) {
		this.sh = sh;
	}
	public String getShyj() {
		return shyj;
	}
	public void setShyj(String shyj) {
		this.shyj = shyj;
	}
}
