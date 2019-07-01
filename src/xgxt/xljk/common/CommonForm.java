package xgxt.xljk.common;

import java.util.HashMap;
import java.util.List;

import org.apache.struts.action.ActionForm;

public abstract class CommonForm extends ActionForm {
	/**
	 * get ColumnCN
	 * @return
	 */
	public abstract List<HashMap<String, String>> getColumnCN();

	/**
	 * set columnCN
	 * @param columnCN
	 */
	public abstract void setColumnCN(List<HashMap<String, String>> columnCN);
	
	public abstract String getDoType();
	
}
