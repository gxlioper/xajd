/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018��5��28�� ����3:32:50 
 */  
package xsgzgl.gyjc.jcjg;

import com.zfsoft.xgxt.comm.export.model.ExportModel;
import org.apache.struts.action.ActionForm;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018��5��28�� ����3:32:50 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WsjcForm extends ActionForm{
	
	private String type;
	private String lddm;
	private String ldmc;
	private String qsh;
	private String bmmc;
	private String jcsj;
	private String pjdj;
	private Pages pages = new Pages();
	private SearchModel searchModel = new SearchModel();
	private String ch;
	private String jcrq;
	private String jcr;
	private String[] ztpjs;
	private String[] grpjs;
	private ExportModel exportModel = new ExportModel();

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}

	public String[] getGrpjs() {
		return grpjs;
	}

	public void setGrpjs(String[] grpjs) {
		this.grpjs = grpjs;
	}

	public String[] getCwhs() {
		return cwhs;
	}

	public void setCwhs(String[] cwhs) {
		this.cwhs = cwhs;
	}

	private String[] cwhs;

	public String[] getZtpjs() {
		return ztpjs;
	}

	public void setZtpjs(String[] ztpjs) {
		this.ztpjs = ztpjs;
	}

	public String getJcrq() {
		return jcrq;
	}

	public void setJcrq(String jcrq) {
		this.jcrq = jcrq;
	}

	public String getJcr() {
		return jcr;
	}

	public void setJcr(String jcr) {
		this.jcr = jcr;
	}



	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public Pages getPages() {
		return pages;
	}

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
	 * @return the ldmc
	 */
	public String getLdmc() {
		return ldmc;
	}
	/**
	 * @param ldmcҪ���õ� ldmc
	 */
	public void setLdmc(String ldmc) {
		this.ldmc = ldmc;
	}
	/**
	 * @return the qsh
	 */
	public String getQsh() {
		return qsh;
	}
	/**
	 * @param qshҪ���õ� qsh
	 */
	public void setQsh(String qsh) {
		this.qsh = qsh;
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
	 * @return the jcsj
	 */
	public String getJcsj() {
		return jcsj;
	}
	/**
	 * @param jcsjҪ���õ� jcsj
	 */
	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}
	/**
	 * @return the pjdj
	 */
	public String getPjdj() {
		return pjdj;
	}
	/**
	 * @param pjdjҪ���õ� pjdj
	 */
	public void setPjdj(String pjdj) {
		this.pjdj = pjdj;
	}

}
