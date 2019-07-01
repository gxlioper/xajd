package xgxt.jygl.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class WjscForm extends ActionForm{
	
	private FormFile[] scwjb;  
	private FormFile file;
	/**
	 * 
	 */
	private static final long serialVersionUID = 3438234816878259078L;
	

	public FormFile[] getScwjb() {
		return scwjb;
	}

	public void setScwjb(FormFile[] scwjb) {
		this.scwjb = scwjb;
	}

	public FormFile getFile() {
		return file;
	}

	public void setFile(FormFile file) {
		this.file = file;
	}
	
	
	
}
