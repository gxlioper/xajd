/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-19 ����05:21:26 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ��ѵ��Ϣ
 * @���ߣ� ligl
 * @ʱ�䣺 2014-2-18 ����02:16:06
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class PxxxModel implements java.io.Serializable {
	private static final long serialVersionUID = -7957660390800045492L;
	private String pxid;// ����id
	private String xh;// ѧ��
	private String pxkssj;// ��ѵ��ʼʱ��
	private String pxjssj;// ��ѵ����ʱ��
	private String pxdd;// ��ѵ�ص�
	private String pxnr;// ��ѵ����

	/**
	 * @���� ��
	 */
	public PxxxModel() {
		super();
	}

	public String getPxid() {
		return pxid;
	}

	public void setPxid(String pxid) {
		this.pxid = pxid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getPxkssj() {
		return pxkssj;
	}

	public void setPxkssj(String pxkssj) {
		this.pxkssj = pxkssj;
	}

	public String getPxjssj() {
		return pxjssj;
	}

	public void setPxjssj(String pxjssj) {
		this.pxjssj = pxjssj;
	}

	public String getPxdd() {
		return pxdd;
	}

	public void setPxdd(String pxdd) {
		this.pxdd = pxdd;
	}

	public String getPxnr() {
		return pxnr;
	}

	public void setPxnr(String pxnr) {
		this.pxnr = pxnr;
	}

}
