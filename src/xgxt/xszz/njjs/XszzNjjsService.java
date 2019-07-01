package xgxt.xszz.njjs;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import xgxt.dtjs.DtjsForm;
import xgxt.dtjs.DtjsTyService;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.xszz.XszzService;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.commonN05.XszzCommonN05ActionForm;

public class XszzNjjsService extends XszzService {

	XszzNjjsDAO dao = new XszzNjjsDAO();

	/**
	 * @describe 同步学费信息
	 * @author luo
	 */
	public boolean tbXfxx() throws Exception {
		return dao.tbXfxx();
	}

	/**
	 * @describe 获得收费相关信息
	 * @author luo
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<HashMap<String, String>> getSfxxList(XszzTyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		return dao.getSfxxList(model);
	}
	
	/**
	 * @describe 保存学费减免
	 * @author luo
	 */
	public boolean saveXfjm(XszzCommonN05ActionForm model) throws Exception {
		
		String[] arrzd = new String[] { "xfsfxm", "xfyjje", "xfsjje", "xfjmje",
				"xfsfqf" };

		String[] onezd = new String[] { "xn", "xh" };

		String tableName = "njjs_xfxxb";

		String pk = "xn||xh";
		
		String pkValue = model.getXn()+model.getXh();
		
		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setOnezd(onezd);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { pkValue});

		return saveXszz(saveForm, model);
	}
}
