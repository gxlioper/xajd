/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-3 ����01:53:07
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: �����
 * @���ߣ���ˮ��[���ţ�1150]
 * @���ڣ�2014-12-3 ����01:53:07
 * @�汾�� V1.0
 * @�޸ļ�¼:
 */
public class HjqkNewModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String hjid;// ����id
	private String xh;// ѧ��
	private String hjsj;// ��ʱ��
	private String fjdw;// ������λ
	private String hjmc;// ������
	private String hjje;// �񽱽��  �㽭������Ի��ֶ�
	private String bz;// ��ע

	/**
	 * @���� ��
	 */
	public HjqkNewModel() {
		super();
	}

	public String getHjid() {
		return hjid;
	}

	public void setHjid(String hjid) {
		this.hjid = hjid;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getHjsj() {
		return hjsj;
	}

	public void setHjsj(String hjsj) {
		this.hjsj = hjsj;
	}

	public String getFjdw() {
		return fjdw;
	}

	public void setFjdw(String fjdw) {
		this.fjdw = fjdw;
	}

	public String getHjmc() {
		return hjmc;
	}

	public void setHjmc(String hjmc) {
		this.hjmc = hjmc;
	}

	public String getHjje() {
		return hjje;
	}

	public void setHjje(String hjje) {
		this.hjje = hjje;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

}
