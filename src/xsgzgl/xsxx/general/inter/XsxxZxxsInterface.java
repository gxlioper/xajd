package xsgzgl.xsxx.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;


/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_在校学生_Interface类
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

public interface XsxxZxxsInterface {

	// 获得表头文件(在校学生)
	public List<HashMap<String, String>> getXsxxZxxsTop(XsxxZxxsModel model,
			User user);

	// 获得结果集(在校学生)
	public ArrayList<String[]> getXsxxZxxsList(XsxxGeneralForm myForm,
			XsxxZxxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(在校学生)
	public String createXsxxZxxsHTML(SearchRsModel rsModel,
			XsxxZxxsModel model, ArrayList<String[]> rsArrList, User user);
	
	// 获得学生基本信息
	public HashMap<String, String> getZxxsInfo(XsxxZxxsModel model, User user);
	
	// 保存学生信息
	public boolean saveXsxx(XsxxZxxsModel model, User user);
	
	// 保存毕业处理
	public boolean saveBycl(XsxxZxxsModel model, User user)throws Exception;
}
