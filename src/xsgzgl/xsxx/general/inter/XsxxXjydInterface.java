package xsgzgl.xsxx.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.xjyd.XsxxXjydModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_学籍异动_Interface类
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

public interface XsxxXjydInterface {

	// 获得表头文件【学籍异动】
	public List<HashMap<String, String>> getXsxxXjydTop(XsxxXjydModel model,
			User user);

	// 获得结果集【学籍异动】
	public ArrayList<String[]> getXsxxXjydList(XsxxGeneralForm myForm,
			XsxxXjydModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【学籍异动】
	public String createXsxxXjydHTML(SearchRsModel rsModel,
			XsxxXjydModel model, ArrayList<String[]> rsArrList, User user);
	
	// 提交学籍异动信息【学籍异动】
	public boolean submitXjyd(XsxxXjydModel model, User user)throws Exception ;
}
