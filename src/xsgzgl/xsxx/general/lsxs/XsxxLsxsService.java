package xsgzgl.xsxx.general.lsxs;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.inter.XsxxLsxsInterface;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_历史学生_Service类
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

public class XsxxLsxsService extends CommService implements XsxxLsxsInterface {

	XsxxLsxsDAO dao = new XsxxLsxsDAO();

	public String createXsxxLsxsHTML(SearchRsModel rsModel,
			XsxxLsxsModel model, ArrayList<String[]> rsArrList, User user) {
		// TODO 自动生成方法存根
		return null;
	}

	public HashMap<String, String> getLsxsInfo(XsxxZxxsModel model, User user) {
		// TODO 自动生成方法存根
		return null;
	}


	public ArrayList<String[]> getXsxxLsxsList(XsxxGeneralForm myForm,
			XsxxLsxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		// TODO 自动生成方法存根
		return null;
	}

	public List<HashMap<String, String>> getXsxxLsxsTop(XsxxLsxsModel model,
			User user) {
		// TODO 自动生成方法存根
		return null;
	}

}