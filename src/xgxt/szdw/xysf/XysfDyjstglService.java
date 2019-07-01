package xgxt.szdw.xysf;

import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;

public class XysfDyjstglService extends BasicExtendService{
	
	/**
	 * 保存单个讲师的德育课程
	 * @param model
	 * @return
	 */
	public boolean saveKc(DyjstglModel model){
		boolean flag = false;
		String pk = "zgh";
		String[] pkValue = new String[]{model.getZgh()};
		String[] arrzd = new String[]{"sksj","skdd","zt","hdgm","bz"};
		String[] onezd = new String[]{"zgh"};
		
		SaveForm saveForm = new SaveForm();
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setArrzd(arrzd);
		saveForm.setOnezd(onezd);
		saveForm.setTableName("xysf_dyjskcb");
		
		DAO dao = DAO.getInstance();
		
		try {
			flag = dao.saveData(saveForm, model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public Map<String, String> getOnekcInfo(String pkValue){
		String[] colList = new String[]{"zgh", "xm", "zw", "xb","zwmc", "bmdm", "bmmc", "xl",
				"zwmc","mzmc","csrq","zzmm","bz","sksj","skdd","zt","hdgm","kcbz"};
		return CommonQueryDAO.commonQueryOne("view_xysf_dyjskc", colList, "id", pkValue);
	}
	
	public Map<String, String> getJsInfo(String zgh){
		String[] colList = new String[]{"zgh", "xm", "zw", "xb","zwmc", "bmdm", "bmmc", "xl",
				"zwmc","mzmc","csrq","zzmm","bz"};
		return CommonQueryDAO.commonQueryOne("view_xysf_dyjs", colList, "zgh", zgh);
	}
}
