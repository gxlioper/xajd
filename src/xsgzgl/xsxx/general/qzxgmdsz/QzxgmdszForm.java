package xsgzgl.xsxx.general.qzxgmdsz;

import com.zfsoft.xgxt.comm.export.model.ExportModel;

import xgxt.utils.Pages;
import xsgzgl.comm.form.CommForm;

public class QzxgmdszForm extends CommForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// ·ÖÒ³
	Pages pages = new Pages();
	
	private String xh;
	
	private String xgzt;
	
	private ExportModel exportModel = new ExportModel();

	public String getXh() {
		return xh;
	}

	public void setXh(String xh) {
		this.xh = xh;
	}

	public String getXgzt() {
		return xgzt;
	}

	public void setXgzt(String xgzt) {
		this.xgzt = xgzt;
	}

	public Pages getPages() {
		return pages;
	}

	public void setPages(Pages pages) {
		this.pages = pages;
	}

	public ExportModel getExportModel() {
		return exportModel;
	}

	public void setExportModel(ExportModel exportModel) {
		this.exportModel = exportModel;
	}
	
}
