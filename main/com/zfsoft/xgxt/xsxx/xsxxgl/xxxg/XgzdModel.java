/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-9 ����01:58:42 
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxxg;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ѧ����Ϣ
 * @�๦������:�޸��ֶ�
 * @���ߣ� ligl
 * @ʱ�䣺 2013-12-9 ����01:58:42
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */

public class XgzdModel implements java.io.Serializable {

	private static final long serialVersionUID = -4055576059215657808L;
	private String sqid;
	private String zd;
	private String zdz;
	private String xgqz;

	/**
	 * @���� ��
	 */
	public XgzdModel() {
		super();
	}

	public String getSqid() {
		return sqid;
	}

	public void setSqid(String sqid) {
		this.sqid = sqid;
	}

	public String getZd() {
		return zd;
	}

	public void setZd(String zd) {
		this.zd = zd;
	}

	public String getZdz() {
		return zdz;
	}

	public void setZdz(String zdz) {
		this.zdz = zdz;
	}

	public String getXgqz() {
		return xgqz;
	}

	public void setXgqz(String xgqz) {
		this.xgqz = xgqz;
	}

}
