/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-17 ����10:56:01 
 */  
package com.zfsoft.xgxt.rcsw.xscxqyb;

import org.apache.struts.action.ActionForm;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����ѧ���±�
 * @�๦������: 
 * @���ߣ� ����[����:1186]
 * @ʱ�䣺 2016-3-17 ����11:11:11 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class XscxqybForm extends ActionForm {
	Pages pages = new Pages();//��ҳ
	SearchModel searchModel = new SearchModel();// �߼���ѯ
	private ExportModel exportModel = new ExportModel();//�Զ��嵼��
	private String type; //����
	private String jgid; //���ID
	private String xn; //ѧ��
	private String xq; //ѧ��
	private String yf; //�·�
	private String bygzkzqk; //���¹�����չ���
	private String xsgzrd; //ѧ����ע�ȵ�
	private String xssxdt; //ѧ��˼�붯̬
	private String xstsjgzjy; //ѧ�����󼰹������
	private String txsj; //��дʱ��
	private String txr; //��д��
	private String zgh; //ְ����
	private String xqmc; //ѧ������	
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
	 * @return the jgid
	 */
	public String getJgid() {
		return jgid;
	}
	/**
	 * @param jgidҪ���õ� jgid
	 */
	public void setJgid(String jgid) {
		this.jgid = jgid;
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
	 * @return the yf
	 */
	public String getYf() {
		return yf;
	}
	/**
	 * @param yfҪ���õ� yf
	 */
	public void setYf(String yf) {
		this.yf = yf;
	}
	/**
	 * @return the bygzkzqk
	 */
	public String getBygzkzqk() {
		return bygzkzqk;
	}
	/**
	 * @param bygzkzqkҪ���õ� bygzkzqk
	 */
	public void setBygzkzqk(String bygzkzqk) {
		this.bygzkzqk = bygzkzqk;
	}
	/**
	 * @return the xsgzrd
	 */
	public String getXsgzrd() {
		return xsgzrd;
	}
	/**
	 * @param xsgzrdҪ���õ� xsgzrd
	 */
	public void setXsgzrd(String xsgzrd) {
		this.xsgzrd = xsgzrd;
	}
	/**
	 * @return the xssxdt
	 */
	public String getXssxdt() {
		return xssxdt;
	}
	/**
	 * @param xssxdtҪ���õ� xssxdt
	 */
	public void setXssxdt(String xssxdt) {
		this.xssxdt = xssxdt;
	}
	/**
	 * @return the xstsjgzjy
	 */
	public String getXstsjgzjy() {
		return xstsjgzjy;
	}
	/**
	 * @param xstsjgzjyҪ���õ� xstsjgzjy
	 */
	public void setXstsjgzjy(String xstsjgzjy) {
		this.xstsjgzjy = xstsjgzjy;
	}
	/**
	 * @return the txsj
	 */
	public String getTxsj() {
		return txsj;
	}
	/**
	 * @param txsjҪ���õ� txsj
	 */
	public void setTxsj(String txsj) {
		this.txsj = txsj;
	}
	/**
	 * @return the txr
	 */
	public String getTxr() {
		return txr;
	}
	/**
	 * @param txrҪ���õ� txr
	 */
	public void setTxr(String txr) {
		this.txr = txr;
	}
}
