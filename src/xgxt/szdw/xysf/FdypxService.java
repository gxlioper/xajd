package xgxt.szdw.xysf;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.SaveForm;

public class FdypxService extends BasicExtendService{
	
	public boolean savePx(DyjstglModel model){
		boolean flag = false;
		String pk = "zgh";
		String[] pkValue = new String[]{model.getZgh()};
		String[] arrzd = new String[]{"pxsj","pxdd","pxmc","pxnr","bz"};
		String[] onezd = new String[]{"zgh"};
		
		SaveForm saveForm = new SaveForm();
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setTableName("szdw_xysf_fdypxb");
		
		DAO dao = DAO.getInstance();
		
		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
