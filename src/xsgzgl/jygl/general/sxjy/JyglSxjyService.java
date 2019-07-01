package xsgzgl.jygl.general.sxjy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicService;
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.jygl.general.inter.JyglSxjyInterface;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjJgcxInterface;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 就业管理_实习就业_通用_Service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class JyglSxjyService extends BasicService implements JyglSxjyInterface {

	JyglSxjyDAO dao = new JyglSxjyDAO();

	/**
	 * 【实习就业】获得表头信息
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getSxjyTop(JyglSxjyModel model,
			User user) {
		DAO dao = DAO.getInstance();
		String tableName = model.getSearch_table();
		String[] en = model.getSearch_zd();
		String[] cn = getTableComment(tableName, en);

		List<HashMap<String, String>> topTr = dao.arrayToList(en, cn);
		return topTr;
	}

	/**
	 * 【实习就业】查询数据
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getSxjyList(JyglGeneralForm myForm,
			JyglSxjyModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		ArrayList<String[]> list = dao.getSxjyList(myForm, model, user);
		return list;
	}

	/**
	 * 【实习就业】构建HTML
	 * 
	 * @author 伟大的骆
	 */
	public String createSxjyHTML(SearchRsModel rsModel, JyglSxjyModel model,
			ArrayList<String[]> rsArrList, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	/**
	 * 【实习就业】获得详细信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getSxjyMap(JyglSxjyModel model)
			throws Exception {
		HashMap<String, String> map = getDetail(model);
		return map;
	}

	/**
	 * 【实习就业】保存信息
	 * 
	 * @author 伟大的骆
	 */
	public boolean saveSxjy(JyglSxjyModel model, User user,
			HttpServletRequest request) {

		String[] save_string_zd = model.getSave_string_zd();
		String[] save_array_zd = model.getSave_array_zd();

		//保存Map
		HashMap<String, Object> saveMap = getValueMapByObj(request, unionArray(
				save_string_zd, save_array_zd));

		boolean flag = false;
		
		try {
			flag = saveTable(model, saveMap, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		
		return flag;
	}

	/**
	 * 【实习就业】删除信息
	 * 
	 * @author 伟大的骆
	 */
	public boolean deleteSxjy(JyglSxjyModel model, User user,
			HttpServletRequest request) {

		// 保存Map
		HashMap<String, Object> saveMap = getValueMapByObj(request,
				new String[] { "pkValue" });

		String[] pkValue = (String[]) saveMap.get("pkValue");
		model.setPkValue(pkValue);

		boolean flag = false;

		try {
			flag = deleteTable(model, user);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}

		return flag;
	}
}
