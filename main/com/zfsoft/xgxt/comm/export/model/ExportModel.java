package com.zfsoft.xgxt.comm.export.model;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

import xgxt.form.User;
import xsgzgl.comm.form.CommForm;


/**
 * 公用导出模型
 * @author Penghui.Qu
 *
 */
public class ExportModel extends CommForm {

	private static final long serialVersionUID = 7850562411516988844L;
	private String dcclbh;
	private List<HashMap<String, String>> dataList;
	private String zgh;
	private String[] unselectCol;
	private String[] selectCol;
	private String selectZd;
	private String unselectZd;
	private String backUrl;
	private String exportWjgs;
	private Object obj;
	private User user ;
	
	
	public String getDcclbh() {
		return dcclbh;
	}
	public void setDcclbh(String dcclbh) {
		this.dcclbh = dcclbh;
	}
	public List<HashMap<String, String>> getDataList() {
		return dataList;
	}
	public void setDataList(List<HashMap<String, String>> dataList) {
		this.dataList = dataList;
	}
	public String getZgh() {
		return zgh;
	}
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	public String getBackUrl() {
		return backUrl;
	}
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	public String[] getUnselectCol() {
		return unselectCol;
	}
	public void setUnselectCol(String[] unselectCol) {
		this.unselectCol = unselectCol;
	}
	public String[] getSelectCol() {
		return selectCol;
	}
	public void setSelectCol(String[] selectCol) {
		this.selectCol = selectCol;
	}
	public String getSelectZd() {
		return selectZd;
	}
	public void setSelectZd(String selectZd) {
		this.selectZd = selectZd;
	}
	public String getUnselectZd() {
		return unselectZd;
	}
	public void setUnselectZd(String unselectZd) {
		this.unselectZd = unselectZd;
	}
	public String getExportWjgs() {
		return exportWjgs;
	}
	public void setExportWjgs(String exportWjgs) {
		this.exportWjgs = exportWjgs;
	}
	/**
	 * @return the obj
	 */
	public Object getObj() {
		return obj;
	}
	/**
	 * @param obj要设置的 obj
	 */
	public void setObj(Object obj) {
		this.obj = obj;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user要设置的 user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
