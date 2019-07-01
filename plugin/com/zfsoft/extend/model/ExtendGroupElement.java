/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-3 ����09:22:52 
 */  
package com.zfsoft.extend.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.zfsoft.extend.service.ZDSource;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-3 ����09:22:52 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendGroupElement implements Serializable{

	private static final long serialVersionUID = 8277734477423712182L;
	
	private String zd;
	
	private String mc;
	
	private String lx;
	
	private String sj;
	
	private String gs;
	
	private String mrz;
	
	private String bt;
	
	private String cd;
	
	private String bz;
	
	private String xssx;
	
	private String gid;

	private String sfbj;
	
	private String elcd;
	/////////////////////////////////
	private List<HashMap<String,String>> zdData; //�ֶ�����
	////////////////////////////////
	
	public ZDSource obtainZDSourceInstance(){
		if(StringUtils.isBlank(sj)){
			return null;
		}
		String method = StringUtils.substringBefore(sj, ":");
		String source = StringUtils.substringAfter(sj, ":");
		return new ZDSource(method, source);
	}
	
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public ExtendGroupElement() {
		super();
	}

	

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param zd
	 * @param mc
	 * @param lx
	 * @param sj
	 * @param gs
	 * @param mrz
	 * @param bt
	 * @param cd
	 * @param bz
	 * @param xssx
	 * @param gid
	 * @param sfbj
	 */
	public ExtendGroupElement(String zd, String mc, String lx, String sj,
			String gs, String mrz, String bt, String cd, String bz,
			String xssx, String gid, String sfbj,String elcd) {
		super();
		this.zd = zd;
		this.mc = mc;
		this.lx = lx;
		this.sj = sj;
		this.gs = gs;
		this.mrz = mrz;
		this.bt = bt;
		this.cd = cd;
		this.bz = bz;
		this.xssx = xssx;
		this.gid = gid;
		this.sfbj = sfbj;
		this.elcd = elcd;
	}

	/**
	 * @return the zd
	 */
	public String getZd() {
		return zd;
	}

	/**
	 * @param zdҪ���õ� zd
	 */
	public void setZd(String zd) {
		this.zd = zd;
	}

	/**
	 * @return the mc
	 */
	public String getMc() {
		return mc;
	}

	/**
	 * @param mcҪ���õ� mc
	 */
	public void setMc(String mc) {
		this.mc = mc;
	}

	/**
	 * @return the lx
	 */
	public String getLx() {
		return lx;
	}

	/**
	 * @param lxҪ���õ� lx
	 */
	public void setLx(String lx) {
		this.lx = lx;
	}

	/**
	 * @return the sj
	 */
	public String getSj() {
		return sj;
	}

	/**
	 * @param sjҪ���õ� sj
	 */
	public void setSj(String sj) {
		this.sj = sj;
	}

	/**
	 * @return the gs
	 */
	public String getGs() {
		return gs;
	}

	/**
	 * @param gsҪ���õ� gs
	 */
	public void setGs(String gs) {
		this.gs = gs;
	}

	/**
	 * @return the mrz
	 */
	public String getMrz() {
		return mrz;
	}

	/**
	 * @param mrzҪ���õ� mrz
	 */
	public void setMrz(String mrz) {
		this.mrz = mrz;
	}

	/**
	 * @return the bt
	 */
	public String getBt() {
		return bt;
	}

	/**
	 * @param btҪ���õ� bt
	 */
	public void setBt(String bt) {
		this.bt = bt;
	}

	/**
	 * @return the cd
	 */
	public String getCd() {
		return cd;
	}

	/**
	 * @param cdҪ���õ� cd
	 */
	public void setCd(String cd) {
		this.cd = cd;
	}

	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}

	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
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

	/**
	 * @return the gid
	 */
	public String getGid() {
		return gid;
	}

	/**
	 * @param gidҪ���õ� gid
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * @return the zdData
	 */
	public List<HashMap<String, String>> getZdData() {
		return zdData;
	}

	/**
	 * @param zdDataҪ���õ� zdData
	 */
	public void setZdData(List<HashMap<String, String>> zdData) {
		this.zdData = zdData;
	}

	/**
	 * @return the sfbj
	 */
	public String getSfbj() {
		return sfbj;
	}

	/**
	 * @param sfbjҪ���õ� sfbj
	 */
	public void setSfbj(String sfbj) {
		this.sfbj = sfbj;
	}

	/**
	 * @return the elcd
	 */
	public String getElcd() {
		return elcd;
	}

	/**
	 * @param elcdҪ���õ� elcd
	 */
	public void setElcd(String elcd) {
		this.elcd = elcd;
	}

}
