package com.zfsoft.xgxt.xszz.knsrdnew.knsrdzb;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import com.zfsoft.xgxt.comm.export.model.ExportModel;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ�Dlq[����:995]
 * @ʱ�䣺 2014-1-24 ����09:03:10 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdzbsxForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private String sxid;
	private String zbid;
	private String sxmc;
	private String qzbl;
	private String xssx;
	
	
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
	 * @return the sxid
	 */
	public String getSxid() {
		return sxid;
	}
	/**
	 * @param sxidҪ���õ� sxid
	 */
	public void setSxid(String sxid) {
		this.sxid = sxid;
	}
	/**
	 * @return the zbid
	 */
	public String getZbid() {
		return zbid;
	}
	/**
	 * @param zbidҪ���õ� zbid
	 */
	public void setZbid(String zbid) {
		this.zbid = zbid;
	}
	/**
	 * @return the sxmc
	 */
	public String getSxmc() {
		return sxmc;
	}
	/**
	 * @param sxmcҪ���õ� sxmc
	 */
	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}
	/**
	 * @return the qzbl
	 */
	public String getQzbl() {
		return qzbl;
	}
	/**
	 * @param qzblҪ���õ� qzbl
	 */
	public void setQzbl(String qzbl) {
		this.qzbl = qzbl;
	}
	/**
	 * @return the xssx
	 */
	public String getXssx() {
		return xssx;
	}
	/**
	 * @param xssxҪ���õ� xssx
	 */
	public void setXssx(String xssx) {
		this.xssx = xssx;
	}



	

}
