/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-19 ����03:02:55 
 */  
package xsgzgl.gygl.lfryxxgl;

import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������: ������Ա�Ǽǹ��� 
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2014-8-19 ����03:02:55 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class LfrydjForm extends ActionForm {
	
	
	private static final long serialVersionUID = 1L;

	private Pages pages = new Pages();
	
	private SearchModel searchModel = new SearchModel();

	private ExportModel exportModel = new ExportModel();
	
	private String type;
	
	private String lfrdjid;
	
	private String lfrxm;
	
	private String lfrxb;
	
	private String lfsj;
	
	private String lqsj;
	
	private String lfrlxdh;
	
	private String lfrsfzh;
	
	private String zbry;
	
	private String bz;
	
	private String xh;
	
	private String xm;
	
	private String xymc;
	
	private String zymc;
	
	private String bjmc;
	
	private String ldmc;
	
	private String qsh;
	
	private String lddm;	//¥������
	private String lfsydm;	//�������ɴ���
	private String lfsymc;	//������������

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public SearchModel getSearchModel() {
		return searchModel;
	}

	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String getLfrdjid() {
		return lfrdjid;
	}

	public void setLfrdjid(String lfrdjid) {
		this.lfrdjid = lfrdjid;
	}

	public String getLfrxm() {
		return lfrxm;
	}

	public void setLfrxm(String lfrxm) {
		this.lfrxm = lfrxm;
	}

	public String getLfrxb() {
		return lfrxb;
	}

	public void setLfrxb(String lfrxb) {
		this.lfrxb = lfrxb;
	}

	public String getLfsj() {
		return lfsj;
	}

	public void setLfsj(String lfsj) {
		this.lfsj = lfsj;
	}

	public String getLqsj() {
		return lqsj;
	}

	public void setLqsj(String lqsj) {
		this.lqsj = lqsj;
	}

	public String getLfrlxdh() {
		return lfrlxdh;
	}

	public void setLfrlxdh(String lfrlxdh) {
		this.lfrlxdh = lfrlxdh;
	}

	public String getLfrsfzh() {
		return lfrsfzh;
	}

	public void setLfrsfzh(String lfrsfzh) {
		this.lfrsfzh = lfrsfzh;
	}

	public String getZbry() {
		return zbry;
	}

	public void setZbry(String zbry) {
		this.zbry = zbry;
	}

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getXymc() {
		return xymc;
	}

	public void setXymc(String xymc) {
		this.xymc = xymc;
	}

	public String getZymc() {
		return zymc;
	}

	public void setZymc(String zymc) {
		this.zymc = zymc;
	}

	public String getBjmc() {
		return bjmc;
	}

	public void setBjmc(String bjmc) {
		this.bjmc = bjmc;
	}

	public String getLdmc() {
		return ldmc;
	}

	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}

	public String getQsh() {
		return qsh;
	}

	public void setQsh(String qsh) {
		this.qsh = qsh;
	}

	/**
	 * @return the lddm
	 */
	public String getLddm() {
		return lddm;
	}

	/**
	 * @param lddmҪ���õ� lddm
	 */
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}

	/**
	 * @return the lfsydm
	 */
	public String getLfsydm() {
		return lfsydm;
	}

	/**
	 * @param lfsydmҪ���õ� lfsydm
	 */
	public void setLfsydm(String lfsydm) {
		this.lfsydm = lfsydm;
	}

	/**
	 * @return the lfsymc
	 */
	public String getLfsymc() {
		return lfsymc;
	}

	/**
	 * @param lfsymcҪ���õ� lfsymc
	 */
	public void setLfsymc(String lfsymc) {
		this.lfsymc = lfsymc;
	}

	
	
	
	
}
