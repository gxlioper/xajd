package xgxt.studentInfo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxTjszDAO;
import xgxt.studentInfo.model.StudentInfoForm;
import xgxt.utils.CommonQueryDAO;

public class XsxxTjszService extends CommService {

	XsxxTjszDAO dao = new XsxxTjszDAO();

	/**
	 * 获得数据库配置字段信息
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, Object>> getDbPzList(StudentInfoForm model) {

		List<HashMap<String, String>> pzList = dao.getDbPzList(model);

		List<HashMap<String, String>> szList = CommonQueryDAO
				.commonQueryforList("xsxx_tjsz_szb", "", new String[] {},
						new String[] { "tjzd", "tjzdz" }, "");

		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();

		if (pzList != null && pzList.size() > 0) {

			for (int i = 0; i < pzList.size(); i++) {

				HashMap<String, String> map = pzList.get(i);
				HashMap<String, Object> ob = new HashMap<String, Object>();

				// 字段
				String zd = map.get("zd");
				// 字段名
				String zdm = map.get("zdm");
				// 字段值
				String zdz = map.get("zdz");
				// 显示名称
				String xsmc = map.get("xsmc");

				ob.put("zd", zd);
				ob.put("zdm", zdm);

				if (!Base.isNull(zdz)) {

					String[] arr_zdz = zdz.split("!!@@!!");
					String[] arr_mc = xsmc.split("!!@@!!");
					
					if (arr_zdz != null && arr_zdz.length > 0) {

						ArrayList<HashMap<String, String>> zdzList = new ArrayList<HashMap<String, String>>();

						for (int j = 0; j < arr_zdz.length; j++) {

							HashMap<String, String> zdMap = new HashMap<String, String>();

							String isCheck = "no";

							if (szList != null && szList.size() > 0) {

								for (int k = 0; k < szList.size(); k++) {

									HashMap<String, String> szMap = szList
											.get(k);

									// 条件字段
									String tjzd = szMap.get("tjzd");
									// 条件字段值
									String tjzdz = szMap.get("tjzdz");

									if (tjzd.equalsIgnoreCase(zd)
											&& tjzdz.equalsIgnoreCase(arr_zdz[j])) {
										isCheck = "yes";
										break;
									}
								}
							}
							zdMap.put("zdz", arr_zdz[j]);
							zdMap.put("xsmc", arr_mc[j]);
							zdMap.put("isCheck", isCheck);

							zdzList.add(zdMap);
						}

						ob.put("zdzList", zdzList);
						ob.put("num", zdzList.size());
					}
				}

				list.add(ob);
			}
		}

		return list;
	}

	/**
	 * 获得数据库配置字段信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public Boolean saveTjsz(StudentInfoForm model, User user) throws Exception {

		String[] arrzd = new String[] { "tjzd", "tjzdz", "xsmc" };

		String tableName = "xsxx_tjsz_szb";

		String pk = "1";

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setArrzd(arrzd);
		saveForm.setPk(pk);
		saveForm.setPkValue(new String[] { "1" });

		boolean flag = dao.saveData(saveForm, model, user);

		return flag;
	}
}
