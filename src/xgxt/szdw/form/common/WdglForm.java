package xgxt.szdw.form.common;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class WdglForm extends ActionForm{

	/**
	 * 
	 */
	private static final long serialVersionUID = 952670412388074642L;
	private String wjlx;
	FormFile uploadFile;
	public FormFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(FormFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public String getWjlx() {
		return wjlx;
	}
	public void setWjlx(String wjlx) {
		this.wjlx = wjlx;
	}

}
