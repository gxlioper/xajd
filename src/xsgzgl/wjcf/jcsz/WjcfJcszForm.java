package xsgzgl.wjcf.jcsz;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;

/**
 * 
* 
* �����ƣ�WjcfJcszForm 
* ��������Υ�ʹ��ֻ�������form
* �����ˣ�yijd 
* ����ʱ�䣺2012-6-19 ����09:20:00 
* �޸ı�ע��  
* @version 
*
 */
public class WjcfJcszForm extends CommForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cflbdm;
	private String cflbmc;
	private String spl;
	private String sfkss;
	private String sfksqjc;
	private String ssslgzr;
	private String cfyydm;
	private String cfyymc;
	//����������
	private String ssspl;
	private String jcspl;
	private String divcfyymc;
	//�Զ��嵼������
	private SearchModel searchModel = new SearchModel();
	private ExportModel exportModel = new ExportModel();
	private Pages pages = new Pages();
	
	public String getCflbdm() {
		return cflbdm;
	}
	public void setCflbdm(String cflbdm) {
		this.cflbdm = cflbdm;
	}
	public String getCflbmc() {
		return cflbmc;
	}
	public void setCflbmc(String cflbmc) {
		this.cflbmc = cflbmc;
	}
	public String getSpl() {
		return spl;
	}
	public void setSpl(String spl) {
		this.spl = spl;
	}
	public String getSfkss() {
		return sfkss;
	}
	public void setSfkss(String sfkss) {
		this.sfkss = sfkss;
	}
	public String getSfksqjc() {
		return sfksqjc;
	}
	public void setSfksqjc(String sfksqjc) {
		this.sfksqjc = sfksqjc;
	}
	public String getSsslgzr() {
		return ssslgzr;
	}
	public void setSsslgzr(String ssslgzr) {
		this.ssslgzr = ssslgzr;
	}
	public String getCfyydm() {
		return cfyydm;
	}
	public void setCfyydm(String cfyydm) {
		this.cfyydm = cfyydm;
	}
	public String getCfyymc() {
		return cfyymc;
	}
	public void setCfyymc(String cfyymc) {
		this.cfyymc = cfyymc;
	}
	public String getSsspl() {
		return ssspl;
	}
	public void setSsspl(String ssspl) {
		this.ssspl = ssspl;
	}
	public String getJcspl() {
		return jcspl;
	}
	public void setJcspl(String jcspl) {
		this.jcspl = jcspl;
	}
	public String getDivcfyymc() {
		return divcfyymc;
	}
	public void setDivcfyymc(String divcfyymc) {
		this.divcfyymc = divcfyymc;
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
	
}
