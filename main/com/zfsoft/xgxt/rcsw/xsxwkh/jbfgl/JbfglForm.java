/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-8-2 ����04:09:29 
 */  
package com.zfsoft.xgxt.rcsw.xsxwkh.jbfgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �����ֱ�pojo
 * @���ߣ� caopei[����:1352]
 * @ʱ�䣺 2016-8-2 ����04:09:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JbfglForm extends ActionForm{
	private String jbfid;	
	private String xn;
	private String xh;
	private String bzrcpdj;//�����λ򸨵�Ա�����ȼ�
	private String bzrcpfz;//-------------������ֵ
	private String xscpdj;//ѧ�������ȼ�
	private String xscpfz;//-------��ֵ
	private String bz;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	private String type;
	/**
	 * @return the jbfid
	 */
	public String getJbfid() {
		return jbfid;
	}
	/**
	 * @param jbfidҪ���õ� jbfid
	 */
	public void setJbfid(String jbfid) {
		this.jbfid = jbfid;
	}
	/**
	 * @return the xn
	 */
	public String getXn() {
		return xn;
	}
	/**
	 * @param xnҪ���õ� xn
	 */
	public void setXn(String xn) {
		this.xn = xn;
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
	 * @return the bzrcpdj
	 */
	public String getBzrcpdj() {
		return bzrcpdj;
	}
	/**
	 * @param bzrcpdjҪ���õ� bzrcpdj
	 */
	public void setBzrcpdj(String bzrcpdj) {
		this.bzrcpdj = bzrcpdj;
	}
	/**
	 * @return the bzrcpfz
	 */
	public String getBzrcpfz() {
		return bzrcpfz;
	}
	/**
	 * @param bzrcpfzҪ���õ� bzrcpfz
	 */
	public void setBzrcpfz(String bzrcpfz) {
		this.bzrcpfz = bzrcpfz;
	}
	/**
	 * @return the xscpdj
	 */
	public String getXscpdj() {
		return xscpdj;
	}
	/**
	 * @param xscpdjҪ���õ� xscpdj
	 */
	public void setXscpdj(String xscpdj) {
		this.xscpdj = xscpdj;
	}
	/**
	 * @return the xscpfz
	 */
	public String getXscpfz() {
		return xscpfz;
	}
	/**
	 * @param xscpfzҪ���õ� xscpfz
	 */
	public void setXscpfz(String xscpfz) {
		this.xscpfz = xscpfz;
	}
	/**
	 * @return the bz
	 */
	public String getBz() {
		return bz;
	}
	/**
	 * @param bzҪ���õ� bz
	 */
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}
	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
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
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2016-8-5 ����09:44:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param split
	 * @return
	 * int �������� 
	 * @throws 
	 */

}
