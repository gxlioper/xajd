
package xgxt.xszz.hzzyjsxy;

import java.io.Serializable;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ְҵ����ѧԺ��Уѧ��������Ϣ����MODEL</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-04</p>
 */
public class SaveZxxsdkModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String xn;//ѧ��
	private String xh;//ѧ��
	private String yxlxdh;//��Ч��ϵ�绰
	private String jtdh;//��ͥ�绰
	private String jtdz;//��ͥ��ַ
	private String dkje;//������
	private String fdsj;//�Ŵ�ʱ��
	private String dknx;//��������
	
	public String getDkje() {
		return dkje;
	}
	public void setDkje(String dkje) {
		this.dkje = dkje;
	}
	public String getDknx() {
		return dknx;
	}
	public void setDknx(String dknx) {
		this.dknx = dknx;
	}
	public String getFdsj() {
		return fdsj;
	}
	public void setFdsj(String fdsj) {
		this.fdsj = fdsj;
	}
	public String getJtdh() {
		return jtdh;
	}
	public void setJtdh(String jtdh) {
		this.jtdh = jtdh;
	}
	public String getJtdz() {
		return jtdz;
	}
	public void setJtdz(String jtdz) {
		this.jtdz = jtdz;
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
	public String getYxlxdh() {
		return yxlxdh;
	}
	public void setYxlxdh(String yxlxdh) {
		this.yxlxdh = yxlxdh;
	}
	
}
