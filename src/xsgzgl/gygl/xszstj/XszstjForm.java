package xsgzgl.gygl.xszstj;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.utils.Pages;
import xsgzgl.gygl.comm.GyglNewForm;

public class XszstjForm extends GyglNewForm{

	Pages pages = new Pages();
	
	private static final long serialVersionUID = 1L;
	
	private String nj;//�꼶
	private String xydm;//ѧԺ
	private String bjdm;//�༶
	private String xb;//�Ա�
	private String rzzt;//��ס״̬
	
	private ExportModel exportModel = new ExportModel();


	public Pages getPages() {
		return pages;
	}
	public void setPages(Pages pages) {
		this.pages = pages;
	}
	public String getNj() {
		return nj;
	}
	public void setNj(String nj) {
		this.nj = nj;
	}
	public String getXydm() {
		return xydm;
	}
	public void setXydm(String xydm) {
		this.xydm = xydm;
	}
	public String getBjdm() {
		return bjdm;
	}
	public void setBjdm(String bjdm) {
		this.bjdm = bjdm;
	}
	public String getXb() {
		return xb;
	}
	public void setXb(String xb) {
		this.xb = xb;
	}
	public String getRzzt() {
		return rzzt;
	}
	public void setRzzt(String rzzt) {
		this.rzzt = rzzt;
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

}
