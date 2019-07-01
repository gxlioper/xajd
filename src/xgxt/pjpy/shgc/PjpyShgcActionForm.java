
package xgxt.pjpy.shgc;

import org.apache.struts.action.ActionForm;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 上海工程技术大学ActionForm</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-03</p>
 */
public class PjpyShgcActionForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] pks;
	public String[] getPks() {
		return pks;
	}
	public void setPks(String[] pks) {
		this.pks = pks;
	}
}
