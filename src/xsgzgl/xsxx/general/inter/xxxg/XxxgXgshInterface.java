package xsgzgl.xsxx.general.inter.xxxg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.dwwh.DwwhModel;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 學生信息_信息修改_修改審核_Interface类
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

public interface XxxgXgshInterface {

	// 初始化参数
	public void initParameter();

	// 获得表头文件【修改審核】
	public List<HashMap<String, String>> getXgshTop(XgshModel model, User user);

	// 获得结果集【修改審核】
	public ArrayList<String[]> getXgshList(XsxxGeneralForm myForm,
			XgshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【修改審核】
	public String createXgshHTML(SearchRsModel rsModel, XgshModel model,
			ArrayList<String[]> rsArrList, User user);

	// 获得審核信息列表
	public List<HashMap<String, String>> getXgshList(XgshModel model, User user);

	// 获得學生修改信息
	public HashMap<String, String> getXgshInfo(XgshModel model, User user);

	// 保存審核狀態
	public boolean saveShzt(XgshModel model, User user);

	// 提交學生信息修改
	public void submitXxxg(String[] sqid, User user);
}
