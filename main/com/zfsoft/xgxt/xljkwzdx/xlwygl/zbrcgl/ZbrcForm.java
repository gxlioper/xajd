/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-5-26 ����11:51:41 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.zbrcgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �ܱ��ճ�
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-5-26 ����11:51:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZbrcForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private String zbid;
	
	private String zblx;
	
	private String zbzc;
	
	private String zbksrq;
	
	private String zbjsrq;
	
	private String xn;
	
	private String xq;
	
	private String czsj;

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
	 * @return the zblx
	 */
	public String getZblx() {
		return zblx;
	}

	/**
	 * @param zblxҪ���õ� zblx
	 */
	public void setZblx(String zblx) {
		this.zblx = zblx;
	}

	/**
	 * @return the zbzc
	 */
	public String getZbzc() {
		return zbzc;
	}

	/**
	 * @param zbzcҪ���õ� zbzc
	 */
	public void setZbzc(String zbzc) {
		this.zbzc = zbzc;
	}

	/**
	 * @return the zbksrq
	 */
	public String getZbksrq() {
		return zbksrq;
	}

	/**
	 * @param zbksrqҪ���õ� zbksrq
	 */
	public void setZbksrq(String zbksrq) {
		this.zbksrq = zbksrq;
	}

	/**
	 * @return the zbjsrq
	 */
	public String getZbjsrq() {
		return zbjsrq;
	}

	/**
	 * @param zbjsrqҪ���õ� zbjsrq
	 */
	public void setZbjsrq(String zbjsrq) {
		this.zbjsrq = zbjsrq;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getCzsj() {
		return czsj;
	}

	public void setCzsj(String czsj) {
		this.czsj = czsj;
	}
}
