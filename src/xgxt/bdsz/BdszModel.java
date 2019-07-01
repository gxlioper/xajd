package xgxt.bdsz;

import java.util.ArrayList;
import java.util.HashMap;

import xgxt.utils.Pages;

public class BdszModel {
	
	/* ͨ�� */
	Pages pages = new Pages();
	
	private String tabname;
	
	private String mkdm;
	
	private String gnb;//���ܱ�
	
	private String zdcd;     //�ֶγ���
	
	private String zdid;     //�ֶ�id
	
	private String cxxspx;     //��ѯ��ʾ����
	
	private String zdpx;     //�ֶ�����
	
	private String zdmc;     //�ֶ�����
	
	private String zdlx;     //�ֶ�����
	
	private String[] opid;     //������ѡ�����
	
	private String[] opmc;     //������ѡ������
	
	private String sfbt ;//�Ƿ����
	
	private String bz;//��ע
	
	private String cxxs;     //��ѯ��ʾ
	
	private String mkmc;     //ģ������
	
	private String[] arrZdid;

	private String[] arrZdmc;
	
	private String[] arrSfbt;
	
	private String[] arrZdlx;
	
	private String[] arrZdcd;
	
	private HashMap<String, ArrayList<HashMap<String, String>>> opslist;

	public HashMap<String, ArrayList<HashMap<String, String>>> getOpslist() {
		return opslist;
	}

	public void setOpslist(
			HashMap<String, ArrayList<HashMap<String, String>>> opslist) {
		this.opslist = opslist;
	}

	public String getMkmc() {
		return mkmc;
	}

	public void setMkmc(String mkmc) {
		this.mkmc = mkmc;
	}

	public String getCxxs() {
		return cxxs;
	}

	public void setCxxs(String cxxs) {
		this.cxxs = cxxs;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getTabname() {
		return tabname;
	}

	public void setTabname(String tabname) {
		this.tabname = tabname;
	}

	public String getCxxspx() {
		return cxxspx;
	}

	public void setCxxspx(String cxxspx) {
		this.cxxspx = cxxspx;
	}

	public String getZdcd() {
		return zdcd;
	}

	public void setZdcd(String zdcd) {
		this.zdcd = zdcd;
	}

	public String getZdid() {
		return zdid;
	}

	public void setZdid(String zdid) {
		this.zdid = zdid;
	}

	public String getZdlx() {
		return zdlx;
	}

	public void setZdlx(String zdlx) {
		this.zdlx = zdlx;
	}

	public String getZdmc() {
		return zdmc;
	}

	public void setZdmc(String zdmc) {
		this.zdmc = zdmc;
	}

	public String getZdpx() {
		return zdpx;
	}

	public void setZdpx(String zdpx) {
		this.zdpx = zdpx;
	}

	public String[] getOpid() {
		return opid;
	}

	public void setOpid(String[] opid) {
		this.opid = opid;
	}

	public String[] getOpmc() {
		return opmc;
	}

	public void setOpmc(String[] opmc) {
		this.opmc = opmc;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}


	public String[] getArrZdid() {
		return arrZdid;
	}

	public void setArrZdid(String[] arrZdid) {
		this.arrZdid = arrZdid;
	}

	public String[] getArrZdmc() {
		return arrZdmc;
	}

	public void setArrZdmc(String[] arrZdmc) {
		this.arrZdmc = arrZdmc;
	}

	public String getMkdm() {
		return mkdm;
	}

	public void setMkdm(String mkdm) {
		this.mkdm = mkdm;
	}

	public String getGnb() {
		return gnb;
	}

	public void setGnb(String gnb) {
		this.gnb = gnb;
	}

	public String getSfbt() {
		return sfbt;
	}

	public void setSfbt(String sfbt) {
		this.sfbt = sfbt;
	}

	public String[] getArrSfbt() {
		return arrSfbt;
	}

	public void setArrSfbt(String[] arrSfbt) {
		this.arrSfbt = arrSfbt;
	}

	public String[] getArrZdcd() {
		return arrZdcd;
	}

	public void setArrZdcd(String[] arrZdcd) {
		this.arrZdcd = arrZdcd;
	}

	public String[] getArrZdlx() {
		return arrZdlx;
	}

	public void setArrZdlx(String[] arrZdlx) {
		this.arrZdlx = arrZdlx;
	}
}
