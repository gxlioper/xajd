/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��5��4�� ����2:22:42 
 */  
package com.zfsoft.xgxt.xsxx.zyfwgl.zyfwsq;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ־Ը�������ģ��
 * @�๦������: ־Ը��������Form
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��5��4�� ����2:22:42 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ZyfwSqForm extends ActionForm{
	
	private static final long serialVersionUID = -8916494147378969804L;
	
	private String fwid;	//�������룩id
	private String xh;	//ѧ��
	private String xn;	//ѧ��
	private String xq;	//ѧ��
	private String xqmc;	//ѧ������
	private String fwkssj;	//����ʼʱ��
	private String fwjssj;	//�������ʱ��
	private String fwddssx;	//����ص�ʡ����
	private String fwdd;	//����ص�
	private String jzr;	//��֤��
	private String fwxss;	//����Сʱ��
	private String fwnr;	//��������
	private String splc;	//��������
	private String shzt;	//���״̬
	
	//��ҳ
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	//�Զ��嵼��
	private ExportModel exportModel = new ExportModel();
	
	private String type;

	/**
	 * @return the fwid
	 */
	public String getFwid() {
		return fwid;
	}

	/**
	 * @param fwidҪ���õ� fwid
	 */
	public void setFwid(String fwid) {
		this.fwid = fwid;
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
	 * @return the xq
	 */
	public String getXq() {
		return xq;
	}

	/**
	 * @param xqҪ���õ� xq
	 */
	public void setXq(String xq) {
		this.xq = xq;
	}

	/**
	 * @return the xqmc
	 */
	public String getXqmc() {
		return xqmc;
	}

	/**
	 * @param xqmcҪ���õ� xqmc
	 */
	public void setXqmc(String xqmc) {
		this.xqmc = xqmc;
	}

	/**
	 * @return the fwkssj
	 */
	public String getFwkssj() {
		return fwkssj;
	}

	/**
	 * @param fwkssjҪ���õ� fwkssj
	 */
	public void setFwkssj(String fwkssj) {
		this.fwkssj = fwkssj;
	}

	/**
	 * @return the fwjssj
	 */
	public String getFwjssj() {
		return fwjssj;
	}

	/**
	 * @param fwjssjҪ���õ� fwjssj
	 */
	public void setFwjssj(String fwjssj) {
		this.fwjssj = fwjssj;
	}

	/**
	 * @return the fwddssx
	 */
	public String getFwddssx() {
		return fwddssx;
	}

	/**
	 * @param fwddssxҪ���õ� fwddssx
	 */
	public void setFwddssx(String fwddssx) {
		this.fwddssx = fwddssx;
	}

	/**
	 * @return the fwdd
	 */
	public String getFwdd() {
		return fwdd;
	}

	/**
	 * @param fwddҪ���õ� fwdd
	 */
	public void setFwdd(String fwdd) {
		this.fwdd = fwdd;
	}

	/**
	 * @return the jzr
	 */
	public String getJzr() {
		return jzr;
	}

	/**
	 * @param jzrҪ���õ� jzr
	 */
	public void setJzr(String jzr) {
		this.jzr = jzr;
	}

	/**
	 * @return the fwxss
	 */
	public String getFwxss() {
		return fwxss;
	}

	/**
	 * @param fwxssҪ���õ� fwxss
	 */
	public void setFwxss(String fwxss) {
		this.fwxss = fwxss;
	}

	/**
	 * @return the fwnr
	 */
	public String getFwnr() {
		return fwnr;
	}

	/**
	 * @param fwnrҪ���õ� fwnr
	 */
	public void setFwnr(String fwnr) {
		this.fwnr = fwnr;
	}

	/**
	 * @return the splc
	 */
	public String getSplc() {
		return splc;
	}

	/**
	 * @param splcҪ���õ� splc
	 */
	public void setSplc(String splc) {
		this.splc = splc;
	}

	/**
	 * @return the shzt
	 */
	public String getShzt() {
		return shzt;
	}

	/**
	 * @param shztҪ���õ� shzt
	 */
	public void setShzt(String shzt) {
		this.shzt = shzt;
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
	

}
