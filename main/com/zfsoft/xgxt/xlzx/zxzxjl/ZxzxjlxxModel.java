/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-12-22 ����11:07:36 
 */  
package com.zfsoft.xgxt.xlzx.zxzxjl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-12-22 ����11:07:36 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZxzxjlxxModel extends ActionForm{
	
	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String bh;
	private String id;
	private String xh;
	private String zxsxm;
	private String zxsj;
	private String zxdd;
	private String zxpg;
	private String zxgc;
	private String zxfk;
	private String zxth;
	
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the zxsxm
	 */
	public String getZxsxm() {
		return zxsxm;
	}
	/**
	 * @param zxsxmҪ���õ� zxsxm
	 */
	public void setZxsxm(String zxsxm) {
		this.zxsxm = zxsxm;
	}
	/**
	 * @return the zxsj
	 */
	public String getZxsj() {
		return zxsj;
	}
	/**
	 * @param zxsjҪ���õ� zxsj
	 */
	public void setZxsj(String zxsj) {
		this.zxsj = zxsj;
	}
	/**
	 * @return the zxdd
	 */
	public String getZxdd() {
		return zxdd;
	}
	/**
	 * @param zxddҪ���õ� zxdd
	 */
	public void setZxdd(String zxdd) {
		this.zxdd = zxdd;
	}
	/**
	 * @return the zxpg
	 */
	public String getZxpg() {
		return zxpg;
	}
	/**
	 * @param zxpgҪ���õ� zxpg
	 */
	public void setZxpg(String zxpg) {
		this.zxpg = zxpg;
	}
	/**
	 * @return the zxgc
	 */
	public String getZxgc() {
		return zxgc;
	}
	/**
	 * @param zxgcҪ���õ� zxgc
	 */
	public void setZxgc(String zxgc) {
		this.zxgc = zxgc;
	}
	/**
	 * @return the zxfk
	 */
	public String getZxfk() {
		return zxfk;
	}
	/**
	 * @param zxfkҪ���õ� zxfk
	 */
	public void setZxfk(String zxfk) {
		this.zxfk = zxfk;
	}
	/**
	 * @return the zxth
	 */
	public String getZxth() {
		return zxth;
	}
	/**
	 * @param zxthҪ���õ� zxth
	 */
	public void setZxth(String zxth) {
		this.zxth = zxth;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModelҪ���õ� searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModelҪ���õ� exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the bh
	 */
	public String getBh() {
		return bh;
	}
	/**
	 * @param bhҪ���õ� bh
	 */
	public void setBh(String bh) {
		this.bh = bh;
	}
	
	
}
