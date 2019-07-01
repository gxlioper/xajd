package xgxt.xsgygl.comm;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.xsgygl.GyglTyForm;
import xgxt.xsgygl.GyglTyService;

public class GyglCommService extends GyglTyService {

	GyglCommDAO dao = new GyglCommDAO();

	/**
	 * �����������
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delSsfp(GyglTyForm model) throws Exception {

		String[] pkValue = model.getPrimarykey_checkVal();

		boolean flag = false;

		if (pkValue != null && pkValue.length > 0) {

			String pk = "xydm || nj || ssbh";
			String tableName = model.getTableName();

			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(tableName);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);

			flag = delGygl(saveForm, model);

		}

		return flag;
	}
	
	/**
	 * �����������(����)
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean delSsfpPl(GyglTyForm model) throws Exception {	
		
		return dao.delSsfpPl(model);
	}
}
