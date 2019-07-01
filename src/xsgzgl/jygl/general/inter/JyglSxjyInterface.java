package xsgzgl.jygl.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.jygl.general.sxjy.JyglSxjyModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 就业管理_实习就业_Interface类
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

public interface JyglSxjyInterface {

	// 【实习就业】获得表头文件
	public List<HashMap<String, String>> getSxjyTop(JyglSxjyModel model,
			User user);

	// 【实习就业】获得结果集
	public ArrayList<String[]> getSxjyList(JyglGeneralForm myForm,
			JyglSxjyModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 【实习就业】构建结果集
	public String createSxjyHTML(SearchRsModel rsModel, JyglSxjyModel model,
			ArrayList<String[]> rsArrList, User user);

	// 【实习就业】获得详细信息
	public HashMap<String, String> getSxjyMap(JyglSxjyModel model)
			throws Exception;

	// 【实习就业】保存
	public boolean saveSxjy(JyglSxjyModel model, User user,
			HttpServletRequest request);

	// 【实习就业】删除
	public boolean deleteSxjy(JyglSxjyModel model, User user,
			HttpServletRequest request);
}
