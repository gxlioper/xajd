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
 * @ʱ�䣺 2014-1-24 ����09:01:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class KnsrdzbForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private String type;
	private Pages pages = new Pages();
	private String zbmc;
	private String qyzt;
	private String zbid;
	
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
	 * @return the zbmc
	 */
	public String getZbmc() {
		return zbmc;
	}
	/**
	 * @param zbmcҪ���õ� zbmc
	 */
	public void setZbmc(String zbmc) {
		this.zbmc = zbmc;
	}
	/**
	 * @return the qyzt
	 */
	public String getQyzt() {
		return qyzt;
	}
	/**
	 * @param qyztҪ���õ� qyzt
	 */
	public void setQyzt(String qyzt) {
		this.qyzt = qyzt;
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
	

}
