package com.zfsoft.xgxt.xszz.bdpz;

import org.apache.struts.action.ActionForm;

/**
 * <p>
 * 	<b>2013��ѧ������</b>
 * </p>
 * <p>
 *		���������
 * <p>
 * @author qph
 * ���ڣ�2013-4-17
 */
public class BdpzForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String type;
	private String gotoPath;
	
	private String guid ;// 
	private String gnmk ;//�����־ 
	private String zddm ;//�ֶ� 
	private String zdmc ;//�ֶ����� 
	private String kjlx ;//�ؼ�����(input,select,radio,textarea) 
	private String zdcd ;//�ֶγ��� 
	private String zdgs ;//�ֶθ�ʽ(text,number,date) 
	private String sfbt ;//�Ƿ����(1,0) 
	private String sjy ;//��������ѡ����ѡ����Դ 
	private String xssx ;//��ʾ˳�� 
	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getGuid() {
		return guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}
	public String getGnmk() {
		return gnmk;
	}
	public void setGnmk(String gnmk) {
		this.gnmk = gnmk;
	}
	public String getZddm() {
		return zddm;
	}
	public void setZddm(String zddm) {
		this.zddm = zddm;
	}
	public String getZdmc() {
		return zdmc;
	}
	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}
	public String getKjlx() {
		return kjlx;
	}
	public void setKjlx(String kjlx) {
		this.kjlx = kjlx;
	}
	public String getZdcd() {
		return zdcd;
	}
	public void setZdcd(String zdcd) {
		this.zdcd = zdcd;
	}
	public String getZdgs() {
		return zdgs;
	}
	public void setZdgs(String zdgs) {
		this.zdgs = zdgs;
	}
	public String getSfbt() {
		return sfbt;
	}
	public void setSfbt(String sfbt) {
		this.sfbt = sfbt;
	}
	public String getSjy() {
		return sjy;
	}
	public void setSjy(String sjy) {
		this.sjy = sjy;
	}
	public String getXssx() {
		return xssx;
	}
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}
	public String getGotoPath() {
		return gotoPath;
	}
	public void setGotoPath(String gotoPath) {
		this.gotoPath = gotoPath;
	}

	
}
