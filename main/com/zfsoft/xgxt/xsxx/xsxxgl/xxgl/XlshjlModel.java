/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-23 ����05:29:36 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

import java.io.Serializable;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����ᾭ��Model
 * @�๦������:ѧ����ᾭ��Model
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-1-23 ����05:29:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class XlshjlModel implements Serializable{

	/** 
	 * @���� serialVersionUID : 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private String jlid; //����id
	
	private String xh; //ѧ��
	
	private String qzrq;//��ֹ����
	
	private String gzdd;//�ںε�λѧϰ����
	
	private String zmrjhc;//֤���ˡ����ںδ�
	
	private String zw;//ְ��
	
	private String jzsj; // ��ֹʱ��
	
	private String fjid; // ��ֹʱ��

	
	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}

	/**
	 * @param fjidҪ���õ� fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}

	/**
	 * @return the jzsj
	 */
	public String getJzsj() {
		return jzsj;
	}

	/**
	 * @param jzsjҪ���õ� jzsj
	 */
	public void setJzsj(String jzsj) {
		this.jzsj = jzsj;
	}

	/**
	 * @return the jlid
	 */
	public String getJlid() {
		return jlid;
	}

	/**
	 * @param jlidҪ���õ� jlid
	 */
	public void setJlid(String jlid) {
		this.jlid = jlid;
	}

	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}

	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/**
	 * @return the qzrq
	 */
	public String getQzrq() {
		return qzrq;
	}

	/**
	 * @param qzrqҪ���õ� qzrq
	 */
	public void setQzrq(String qzrq) {
		this.qzrq = qzrq;
	}

	/**
	 * @return the gzdd
	 */
	public String getGzdd() {
		return gzdd;
	}

	/**
	 * @param gzddҪ���õ� gzdd
	 */
	public void setGzdd(String gzdd) {
		this.gzdd = gzdd;
	}

	/**
	 * @return the zmrjhc
	 */
	public String getZmrjhc() {
		return zmrjhc;
	}

	/**
	 * @param zmrjhcҪ���õ� zmrjhc
	 */
	public void setZmrjhc(String zmrjhc) {
		this.zmrjhc = zmrjhc;
	}

	/**
	 * @return the zw
	 */
	public String getZw() {
		return zw;
	}

	/**
	 * @param zwҪ���õ� zw
	 */
	public void setZw(String zw) {
		this.zw = zw;
	}
	
	
}
