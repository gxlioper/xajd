package xgxt.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * ���ڴ���ĳЩ�������⣨��ͷ���б����ݽ���ͬһ��server�еõ�����һ����������ʱ���ô��ࣩ
 * Ls
 */
public class QueryReturnData {
	ArrayList<String[]> rs = null; 
	String [] xymcList = null; 
	ArrayList<HashMap<String, String>>  topTr = null;
	public ArrayList<String[]> getRs() {
		return rs;
	}
	public void setRs(ArrayList<String[]> rs) {
		this.rs = rs;
	}
	public ArrayList<HashMap<String, String>> getTopTr() {
		return topTr;
	}
	public void setTopTr(ArrayList<HashMap<String, String>> topTr) {
		this.topTr = topTr;
	}
	public String[] getXymcList() {
		return xymcList;
	}
	public void setXymcList(String[] xymcList) {
		this.xymcList = xymcList;
	}
	
	
}
