/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-6 ����10:26:27 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ѧ����Ϣ�޸�����
 * @���ߣ� ligl
 * @ʱ�䣺 2013-12-6 ����10:26:27
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */

public class XgsqModel implements java.io.Serializable {
	private static final long serialVersionUID = -7538118568402400940L;
	private String sqid;// ����ID
	private String xh;// ѧ��
	private String xgsj;// �޸�ʱ��
	private String shjg;// ��˽��(δ���,�����,ͨ��,�˻�)
	private String xgzd;// �޸��ֶ�

	/**
	 * @���� ��
	 */
	public XgsqModel() {
		super();
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXgsj() {
		return xgsj;
	}

	public void setXgsj(String xgsj) {
		this.xgsj = xgsj;
	}

	public String getShjg() {
		return shjg;
	}

	public void setShjg(String shjg) {
		this.shjg = shjg;
	}

	public String getXgzd() {
		return xgzd;
	}

	public void setXgzd(String xgzd) {
		this.xgzd = xgzd;
	}

}
