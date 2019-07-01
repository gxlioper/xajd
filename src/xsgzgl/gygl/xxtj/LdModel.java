package xsgzgl.gygl.xxtj;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LdModel {
	private String lddm;
	private Map<Integer, List<QsModel>> csMap = new TreeMap<Integer, List<QsModel>>(); // ²ãÊýmap
	private String ldmc;
	private String ldxb;
	
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	
	public String getLdmc() {
		return ldmc;
	}
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	public Map<Integer, List<QsModel>> getCsMap() {
		return csMap;
	}
	public void setCsMap(Map<Integer, List<QsModel>> csMap) {
		this.csMap = csMap;
	}
	public String getLdxb() {
		return ldxb;
	}
	public void setLdxb(String ldxb) {
		this.ldxb = ldxb;
	}
}
