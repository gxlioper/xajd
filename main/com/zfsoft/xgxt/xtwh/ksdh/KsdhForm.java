/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-10-8 ����09:28:12 
 */
package com.zfsoft.xgxt.xtwh.ksdh;

import org.apache.struts.action.ActionForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2015-10-8 ����09:28:12
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class KsdhForm extends ActionForm {
	private String id;
	private String yhm;// �û�����XG_XTWH_KSDHB��
	private String cdid;// ����ģ������������ XG_XTWH_DHTP��XG_XTWH_KSDHB��
	private String czsj;// ����ʱ�䣨XG_XTWH_KSDHB��
	private String yhlx;// �û����ͣ�XG_XTWH_KSDHB��
	private String gnmktblj;// ����ģ���ӦͼƬ·����xg_xtwh_dhtp��
	private String mkfldm;// ģ�������루XG_DHFLDMB�����XG_XTWH_DHTP��
	private String cshdhmk;// ��ʼ������ģ�飨XG_XTWH_DHTP�������ٵ������и��û�������ʱȡcshdhmkΪ1��Ĭ��ֵ��
	private String xssx;//��ʾ˳��
	private String xxqx;//ѧУȨ�޿��ƣ�Ĭ��Ϊpublic,�ǿ��ֶΣ�����Ҫ��Ϊ�� ��ֹֹ�и��Ի�����
	private String[] gnmkms;//gnmkdm����
    private String[] xssxs;//xssx����
    private String gnmkmc;//����ģ������
	/**
	 * @return the cdid
	 */
	public String getCdid() {
		return cdid;
	}

	/**
	 * @param cdidҪ���õ� cdid
	 */
	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	/**
	 * @return the gnmktblj
	 */
	public String getGnmktblj() {
		return gnmktblj;
	}

	/**
	 * @param gnmktbljҪ���õ� gnmktblj
	 */
	public void setGnmktblj(String gnmktblj) {
		this.gnmktblj = gnmktblj;
	}

	/**
	 * @return the mkfldm
	 */
	public String getMkfldm() {
		return mkfldm;
	}

	/**
	 * @param mkfldmҪ���õ� mkfldm
	 */
	public void setMkfldm(String mkfldm) {
		this.mkfldm = mkfldm;
	}

	/**
	 * @return the cshdhmk
	 */
	public String getCshdhmk() {
		return cshdhmk;
	}

	/**
	 * @param cshdhmkҪ���õ� cshdhmk
	 */
	public void setCshdhmk(String cshdhmk) {
		this.cshdhmk = cshdhmk;
	}

	/**
	 * @return the xxqx
	 */
	public String getXxqx() {
		return xxqx;
	}

	/**
	 * @param xxqxҪ���õ� xxqx
	 */
	public void setXxqx(String xxqx) {
		this.xxqx = xxqx;
	}

	/**
	 * @return the gnmkmc
	 */
	public String getGnmkmc() {
		return gnmkmc;
	}

	/**
	 * @param gnmkmcҪ���õ� gnmkmc
	 */
	public void setGnmkmc(String gnmkmc) {
		this.gnmkmc = gnmkmc;
	}

	/**
	 * @return the gnmkmbs
	 */
	public String[] getGnmkms() {
		return gnmkms;
	}

	/**
	 * @param gnmkmbsҪ���õ� gnmkmbs
	 */
	public void setGnmkms(String[] gnmkms) {
		this.gnmkms = gnmkms;
	}

	/**
	 * @return the xssxs
	 */
	public String[] getXssxs() {
		return xssxs;
	}

	/**
	 * @param xssxsҪ���õ� xssxs
	 */
	public void setXssxs(String[] xssxs) {
		this.xssxs = xssxs;
	}

	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}

	/**
	 * @param xssxҪ���õ� xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}

	private String type;// ��̨�жϱ�־


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param typeҪ���õ�
	 *            type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param idҪ���õ�
	 *            id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the yhm
	 */
	public String getYhm() {
		return yhm;
	}

	/**
	 * @param yhmҪ���õ�
	 *            yhm
	 */
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}



	/**
	 * @return the czsj
	 */
	public String getCzsj() {
		return czsj;
	}

	/**
	 * @param czsjҪ���õ�
	 *            czsj
	 */
	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}

	/**
	 * @return the yhlx
	 */
	public String getYhlx() {
		return yhlx;
	}

	/**
	 * @param yhlxҪ���õ�
	 *            yhlx
	 */
	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}
}
