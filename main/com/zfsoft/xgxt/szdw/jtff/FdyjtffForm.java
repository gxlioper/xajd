/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-8-5 ����11:09:05 
 */  
package com.zfsoft.xgxt.szdw.jtff;

import org.apache.struts.action.ActionForm;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(����Ա��������--ɽ��Ϋ��) 
 * @���ߣ� cmj [���ţ�913]
 * @ʱ�䣺 2013-8-5 ����11:09:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class FdyjtffForm extends ActionForm {
	
private static final long serialVersionUID = 1L;
	
	private String type;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	
	private String id;            //
	private String zgh;            //ְ����
	private String xm;            //����
	private String xb;            //�Ա�
	private String xbmc;            //�Ա�
	private String lxdh;            //��ϵ�绰
	private String bmmc;            //��������
	
	private String bzlx;            //��������
	private String bzlxmc;            //��������
	private String kpdj;            //�����ȼ�
	private String kpdjmc;            //�����ȼ�
	private String xn;            //ѧ��
	private String xq;            //ѧ��
	private String xqmc;            //ѧ��
	private String bzje;            //�������
	
	private String fdyjtfflx;            //ҵ��ģ�������Ҫ��Ӹ��Ի�����
	
	
	
	/**
	 * @return the bzlxmc
	 */
	public String getBzlxmc() {
		return bzlxmc;
	}
	/**
	 * @param bzlxmcҪ���õ� bzlxmc
	 */
	public void setBzlxmc(String bzlxmc) {
		this.bzlxmc = bzlxmc;
	}
	/**
	 * @return the kpdjmc
	 */
	public String getKpdjmc() {
		return kpdjmc;
	}
	/**
	 * @param kpdjmcҪ���õ� kpdjmc
	 */
	public void setKpdjmc(String kpdjmc) {
		this.kpdjmc = kpdjmc;
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
	 * @return the zgh
	 */
	public String getZgh() {
		return zgh;
	}
	/**
	 * @param zghҪ���õ� zgh
	 */
	public void setZgh(String zgh) {
		this.zgh = zgh;
	}
	/**
	 * @return the bzlx
	 */
	public String getBzlx() {
		return bzlx;
	}
	/**
	 * @param bzlxҪ���õ� bzlx
	 */
	public void setBzlx(String bzlx) {
		this.bzlx = bzlx;
	}
	/**
	 * @return the kpdj
	 */
	public String getKpdj() {
		return kpdj;
	}
	/**
	 * @param kpdjҪ���õ� kpdj
	 */
	public void setKpdj(String kpdj) {
		this.kpdj = kpdj;
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
	 * @return the bzje
	 */
	public String getBzje() {
		return bzje;
	}
	/**
	 * @param bzjeҪ���õ� bzje
	 */
	public void setBzje(String bzje) {
		this.bzje = bzje;
	}
	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}
	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}
	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}
	/**
	 * @param lxdhҪ���õ� lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	/**
	 * @return the bmmc
	 */
	public String getBmmc() {
		return bmmc;
	}
	/**
	 * @param bmmcҪ���õ� bmmc
	 */
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	/**
	 * @return the xb
	 */
	public String getXb() {
		return xb;
	}
	/**
	 * @param xbҪ���õ� xb
	 */
	public void setXb(String xb) {
		this.xb = xb;
	}
	/**
	 * @return the xbmc
	 */
	public String getXbmc() {
		return xbmc;
	}
	/**
	 * @param xbmcҪ���õ� xbmc
	 */
	public void setXbmc(String xbmc) {
		this.xbmc = xbmc;
	}
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	public ExportModel getExportModel() {
		return exportModel;
	}
	public String getFdyjtfflx() {
		return fdyjtfflx;
	}
	public void setFdyjtfflx(String fdyjtfflx) {
		this.fdyjtfflx = fdyjtfflx;
	}
	
}
