package xgxt.xtwh.xtwhCriterion.yhgl;

import java.util.HashMap;
import java.util.List;

/**
 * ����ģ��Model
 */
public class GnmkModel {
	private String gnmkdm;					// ����ģ�����
	private String gnmkmc;					// ����ģ������
	private List<GnmkModel> childList;		// ��ģ��
	private List<HashMap<String, String>> btnList;	// ��ť
	private String lv;						// ����
	
	public String getGnmkdm() {
		return gnmkdm;
	}
	public void setGnmkdm(String gnmkdm) {
		this.gnmkdm = gnmkdm;
	}
	public String getGnmkmc() {
		return gnmkmc;
	}
	public void setGnmkmc(String gnmkmc) {
		this.gnmkmc = gnmkmc;
	}
	public List<GnmkModel> getChildList() {
		return childList;
	}
	public void setChildList(List<GnmkModel> childList) {
		this.childList = childList;
	}
	public List<HashMap<String, String>> getBtnList() {
		return btnList;
	}
	public void setBtnList(List<HashMap<String, String>> btnList) {
		this.btnList = btnList;
	}
	public String getLv() {
		return lv;
	}
	public void setLv(String lv) {
		this.lv = lv;
	}
	public int getChildSize(){
		return childList.size();
	}
}
