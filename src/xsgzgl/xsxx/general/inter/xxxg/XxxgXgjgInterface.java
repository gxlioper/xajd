package xsgzgl.xsxx.general.inter.xxxg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.xxxg.xgjg.XgjgModel;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改結果_Interface类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public interface XxxgXgjgInterface {

	// 初始化参数
	public void initParameter();

	// 获得表头文件【修改結果】
	public List<HashMap<String, String>> getXgjgTop(XgjgModel model, User user);

	// 获得结果集【修改結果】
	public ArrayList<String[]> getXgjgList(XsxxGeneralForm myForm,
			XgjgModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【修改結果】
	public String createXgjgHTML(SearchRsModel rsModel, XgjgModel model,
			ArrayList<String[]> rsArrList, User user);
}
