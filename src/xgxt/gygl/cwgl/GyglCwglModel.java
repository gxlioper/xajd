package xgxt.gygl.cwgl;

import java.util.HashMap;
import java.util.List;

public class GyglCwglModel {
	
	private String[]lddm;//¥������
	
	private String[]cs;//����
	
	private String[]qsh;//���Һ�
	
	private String[]xh;//ѧ��
	
	private String[]cwh;//��λ��
	
	private String[]rzrq;//��ס����
	
	private String[]sjly;//������Դ
	
	private String[]ssbh;//������
	
	private List<HashMap<String,String>>wfpcwList;//δ���䴲λ�б�
	
	private List<HashMap<String,String>>khzcwList;//�ɻ�ס��λ�б�
	
	private List<HashMap<String,String>>bkhzcwList;//���ɻ�ס��λ�б�
	
	private String fpdx;

	public String getFpdx() {
		return fpdx;
	}

	public void setFpdx(String fpdx) {
		this.fpdx = fpdx;
	}

	public String[] getSsbh() {
		return ssbh;
	}

	public void setSsbh(String[] ssbh) {
		this.ssbh = ssbh;
	}

	public String[] getCs() {
		return cs;
	}

	public void setCs(String[] cs) {
		this.cs = cs;
	}

	public String[] getCwh() {
		return cwh;
	}

	public void setCwh(String[] cwh) {
		this.cwh = cwh;
	}

	public String[] getLddm() {
		return lddm;
	}

	public void setLddm(String[] lddm) {
		this.lddm = lddm;
	}

	public String[] getQsh() {
		return qsh;
	}

	public void setQsh(String[] qsh) {
		this.qsh = qsh;
	}

	public String[] getRzrq() {
		return rzrq;
	}

	public void setRzrq(String[] rzrq) {
		this.rzrq = rzrq;
	}

	public String[] getSjly() {
		return sjly;
	}

	public void setSjly(String[] sjly) {
		this.sjly = sjly;
	}

	public String[] getXh() {
		return xh;
	}

	public void setXh(String[] xh) {
		this.xh = xh;
	}

	public List<HashMap<String, String>> getBkhzcwList() {
		return bkhzcwList;
	}

	public void setBkhzcwList(List<HashMap<String, String>> bkhzcwList) {
		this.bkhzcwList = bkhzcwList;
	}

	public List<HashMap<String, String>> getKhzcwList() {
		return khzcwList;
	}

	public void setKhzcwList(List<HashMap<String, String>> khzcwList) {
		this.khzcwList = khzcwList;
	}

	public List<HashMap<String, String>> getWfpcwList() {
		return wfpcwList;
	}

	public void setWfpcwList(List<HashMap<String, String>> wfpcwList) {
		this.wfpcwList = wfpcwList;
	}
	
	
}
