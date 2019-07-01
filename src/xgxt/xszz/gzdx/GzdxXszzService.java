package xgxt.xszz.gzdx;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.SaveForm;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;

public class GzdxXszzService extends XszzService {
	
	GzdxXszzDAO dao = new GzdxXszzDAO();
	
	/**
	 * 保存条件设置
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveTjsz(XszzTyForm model, HttpServletRequest request)
			throws Exception {

		String[] onezd = new String[] { "xmdm", "tjzd", "tjlx", "tjz" };

		String tableName = "xszz_zztjb";

		String pk = "xmdm||tjzd";
		String pkValue = model.getXmdm() + model.getTjzd();

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue });

		return saveXszz(saveForm, model, request);
	}
}




	








