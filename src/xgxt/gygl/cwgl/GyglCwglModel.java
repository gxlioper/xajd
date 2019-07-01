package xgxt.gygl.cwgl;

import java.util.HashMap;
import java.util.List;

public class GyglCwglModel {
	
	private String[]lddm;//楼栋代码
	
	private String[]cs;//层数
	
	private String[]qsh;//寝室号
	
	private String[]xh;//学号
	
	private String[]cwh;//床位号
	
	private String[]rzrq;//入住日期
	
	private String[]sjly;//数据来源
	
	private String[]ssbh;//宿舍编号
	
	private List<HashMap<String,String>>wfpcwList;//未分配床位列表
	
	private List<HashMap<String,String>>khzcwList;//可混住床位列表
	
	private List<HashMap<String,String>>bkhzcwList;//不可混住床位列表
	
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
