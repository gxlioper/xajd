package xsgzgl.pjpy.general.inter.xmsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.xmsz.rssz.XmszRsszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_人数设置_Interface类
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

public interface XmszRsszInterface {

	// 获得人数是否超过的信息
	public String getRsszMessage(XmszRsszModel model, User user);

	// 获得表头文件(项目设置_人数设置)
	public List<HashMap<String, String>> getXmszRsszTop(XmszRsszModel model,
			User user);

	// 获得结果集(项目设置_人数设置)
	public ArrayList<String[]> getXmszRsszList(PjpyGeneralForm myForm,
			XmszRsszModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(项目设置_人数设置)
	public String createXmszRsszHTML(SearchRsModel rsModel,
			XmszRsszModel model, ArrayList<String[]> rsArrList, User user);
	
	// 初始化评奖人数设置
	public void initRsszb(String xmdm, String szfw, String tsrq, User user);
	
	// 保存设置比例
	public Boolean saveSzbl(PjpyGeneralForm myForm, XmszRsszModel model,
			User user, String saveLx);
	
	// 保存确定人数
	public Boolean saveQdrs(XmszRsszModel model, User user);
}
