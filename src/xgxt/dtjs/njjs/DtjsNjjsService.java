package xgxt.dtjs.njjs;

import javax.servlet.http.HttpServletRequest;
import xgxt.dtjs.DtjsForm;
import xgxt.dtjs.DtjsTyService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;

public class DtjsNjjsService extends DtjsTyService {

	DtjsNjjsDAO dao = new DtjsNjjsDAO();
	
	/**
	 * 保存团员信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveTyxx(DtjsForm model, String tableName,
			HttpServletRequest request) throws Exception {

		String[] onezd = new String[] { "rdsj", "rtdd", "rtsj", "tyzbh", "xh",
				"xl", "ywrtzys","bz" };

		String pk = "xh";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { model.getXh() });

		return saveDtjs(saveForm, model, request);
	}	
}
