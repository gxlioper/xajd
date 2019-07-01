package xsgzgl.xtwh.general.qxgl;

import java.util.HashMap;
import java.util.List;

/**
 * 功能模块Model
 */
public class GnmkModel {
	private String gnmkdm;					// 功能模块代码
	private String gnmkmc;					// 功能模块名称
	private List<GnmkModel> childList;		// 子模块
	private List<HashMap<String, String>> btnList;	// 按钮
	private String lv;						// 级别
	private String dxq;                     //读写权2013-1-9
	private String checked;					
	
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
	public String getDxq() {
		return dxq;
	}
	public void setDxq(String dxq) {
		this.dxq = dxq;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	
}
