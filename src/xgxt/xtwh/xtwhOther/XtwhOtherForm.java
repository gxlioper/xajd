package xgxt.xtwh.xtwhOther;

import java.io.File;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.comm.search.SearchModel;
import xgxt.utils.Pages;



public class XtwhOtherForm extends ActionForm {
	
	/**
	 * 系统维护其他
	 */
	private static final long serialVersionUID = 4330620005058270409L;
	private FormFile file;
    
    private FormFile filepc;
    
    private FormFile filekl;
    
    private FormFile filezy;
    
    private FormFile filecj;
    
    private FormFile fileAll;
    
    private FormFile[] yjfj;
    
    private String[] xmdm;       //项目代码
    private String[] xmkg;       //项目开关
    private String queryequals_ssmk;
    private String querylike_gnmc;
    private String queryequals_gnlb;
    private String queryequals_kgzt;
    
    Pages pages = new Pages();
    private ExportModel exportModel = new ExportModel();
	private SearchModel searchModel = new SearchModel();
    
	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public FormFile[] getYjfj() {
		return yjfj;
	}
	public void setYjfj(FormFile[] yjfj) {
		this.yjfj = yjfj;
	}
	public FormFile getFile() {
		return file;
	}
	public void setFile(FormFile file) {
		this.file = file;
	}
	public FormFile getFileAll() {
		return fileAll;
	}
	public void setFileAll(FormFile fileAll) {
		this.fileAll = fileAll;
	}
	public FormFile getFilecj() {
		return filecj;
	}
	public void setFilecj(FormFile filecj) {
		this.filecj = filecj;
	}
	public FormFile getFilekl() {
		return filekl;
	}
	public void setFilekl(FormFile filekl) {
		this.filekl = filekl;
	}
	public FormFile getFilepc() {
		return filepc;
	}
	public void setFilepc(FormFile filepc) {
		this.filepc = filepc;
	}
	public FormFile getFilezy() {
		return filezy;
	}
	public void setFilezy(FormFile filezy) {
		this.filezy = filezy;
	}
	public String[] getXmdm() {
		return xmdm;
	}
	public void setXmdm(String[] xmdm) {
		this.xmdm = xmdm;
	}
	public String[] getXmkg() {
		return xmkg;
	}
	public void setXmkg(String[] xmkg) {
		this.xmkg = xmkg;
	}
	public String getQueryequals_ssmk() {
		return queryequals_ssmk;
	}
	public void setQueryequals_ssmk(String queryequals_ssmk) {
		this.queryequals_ssmk = queryequals_ssmk;
	}
	public String getQuerylike_gnmc() {
		return querylike_gnmc;
	}
	public void setQuerylike_gnmc(String querylike_gnmc) {
		this.querylike_gnmc = querylike_gnmc;
	}
	public String getQueryequals_gnlb() {
		return queryequals_gnlb;
	}
	public void setQueryequals_gnlb(String queryequals_gnlb) {
		this.queryequals_gnlb = queryequals_gnlb;
	}
	public String getQueryequals_kgzt() {
		return queryequals_kgzt;
	}
	public void setQueryequals_kgzt(String queryequals_kgzt) {
		this.queryequals_kgzt = queryequals_kgzt;
	}
	/**
	 * @return the exportModel
	 */
	public ExportModel getExportModel() {
		return exportModel;
	}
	/**
	 * @param exportModel要设置的 exportModel
	 */
	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	/**
	 * @return the searchModel
	 */
	public SearchModel getSearchModel() {
		return searchModel;
	}
	/**
	 * @param searchModel要设置的 searchModel
	 */
	public void setSearchModel(SearchModel searchModel) {
		this.searchModel = searchModel;
	}
}
