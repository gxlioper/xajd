/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-18 ����10:34:16 
 */  
package com.zfsoft.xgxt.xsxx.xsxxgl.bddc;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� yxy[����:1206]
 * @ʱ�䣺 2016-8-18 ����10:34:16 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExportModel extends ActionForm {
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
	private String[] xhArr;
	private String path;//����ʱ���ļ�����
	private String xh;//ѧ�ţ�����ʱ��
	private String filepath;//�ļ�·���������ļ�ʱ��
	private String defaultimagepath;//Ĭ��ͼƬ·��
	
	/**
	 * @return the defaultimagepath
	 */
	public String getDefaultimagepath() {
		return defaultimagepath;
	}
	/**
	 * @param defaultimagepathҪ���õ� defaultimagepath
	 */
	public void setDefaultimagepath(String defaultimagepath) {
		this.defaultimagepath = defaultimagepath;
	}
	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}
	/**
	 * @param filepathҪ���õ� filepath
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	/**
	 * @return the xh
	 */
	public String getXh() {
		return xh;
	}
	/**
	 * @param xhҪ���õ� xh
	 */
	public void setXh(String xh) {
		this.xh = xh;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param pathҪ���õ� path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the xhArr
	 */
	public String[] getXhArr() {
		return xhArr;
	}
	/**
	 * @param xhArrҪ���õ� xhArr
	 */
	public void setXhArr(String[] xhArr) {
		this.xhArr = xhArr;
	}
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
	
}
