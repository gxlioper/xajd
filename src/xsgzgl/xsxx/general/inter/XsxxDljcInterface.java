package xsgzgl.xsxx.general.inter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlModel;
import xsgzgl.xsxx.cssz.XsxxCsszForm;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.dljc.XsxxDljcModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_登录检测_Interface类
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

public interface XsxxDljcInterface {

	// ===================查询页面 begin=============================

	// 获得表头文件【登录检测】
	public List<HashMap<String, String>> getXsxxDljclTop(XsxxDljcModel model,
			User user);

	// 获得结果集【登录检测】
	public ArrayList<String[]> getXsxxDljcList(XsxxGeneralForm myForm,
			XsxxDljcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【登录检测】
	public String createXsxxDljcHTML(SearchRsModel rsModel,
			XsxxDljcModel model, ArrayList<String[]> rsArrList, User user);

	// 重置登录检测
	public boolean resetDljc(XsxxGeneralForm myForm, XsxxDljcModel model,
			User user);

	// ===================查询页面 end=============================

	// ===================设置页面 begin=============================

	// 创建字段设置Div
	public void createZdszDiv(XsxxDljcModel model, User user,
			HttpServletResponse response) throws IOException;

	// 保存字段设置
	public boolean saveZdsz(XsxxDljcModel model, User user);

	// ===================设置页面 end=============================

	// ===================信息完善 begin=============================

	// 信息是否完善
	public boolean isXxws(String xh);

	// 创建信息完善Div
	public void createXxwsDiv(XsxxDljcModel model, User user,
			HttpServletResponse response) throws IOException;

	// 保存完善信息
	public boolean saveXxws(User user, HttpServletRequest request);

	// ===================信息完善 end=============================
}
