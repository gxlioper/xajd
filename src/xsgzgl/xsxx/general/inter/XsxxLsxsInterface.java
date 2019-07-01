package xsgzgl.xsxx.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.pjry.PjszPjryModel;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.lsxs.XsxxLsxsModel;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_历史学生_Interface类
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

public interface XsxxLsxsInterface {

	// 获得表头文件(历史学生)
	public List<HashMap<String, String>> getXsxxLsxsTop(XsxxLsxsModel model,
			User user);

	// 获得结果集(历史学生)
	public ArrayList<String[]> getXsxxLsxsList(XsxxGeneralForm myForm,
			XsxxLsxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(历史学生)
	public String createXsxxLsxsHTML(SearchRsModel rsModel,
			XsxxLsxsModel model, ArrayList<String[]> rsArrList, User user);

	// 获得学生基本信息
	public HashMap<String, String> getLsxsInfo(XsxxZxxsModel model, User user);

}
