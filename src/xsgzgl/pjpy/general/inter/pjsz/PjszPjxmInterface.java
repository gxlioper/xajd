package xsgzgl.pjpy.general.inter.pjsz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.cpxz.PjszCpxzModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖设置_评奖项目_Interface类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public interface PjszPjxmInterface {

	// 获得表头文件(评奖设置_评奖项目)
	public List<HashMap<String, String>> getPjszPjxmTop(PjszPjxmModel model,
			User user);

	// 获得结果集(评奖设置_评奖项目)
	public ArrayList<String[]> getPjszPjxmList(PjpyGeneralForm myForm,
			PjszPjxmModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集(评奖设置_评奖项目)
	public String createPjszPjxmHTML(SearchRsModel rsModel,
			PjszPjxmModel model, ArrayList<String[]> rsArrList, User user);

	// 初始化评奖项目设置
	public void defaultPjxmSetting(PjszPjxmModel model, User user,
			HttpServletResponse response) throws IOException;

	// 初始化评奖项目设置
	public void defaultPjxmUpdate(PjszPjxmModel model, User user,
			HttpServletResponse response) throws Exception;

	
	// 保存评奖项目
	public Boolean savePjxm(PjszPjxmModel model, User user);
	
	// 保存评奖项目
	public Boolean updatePjxm(PjszPjxmModel model, User user);
	
	// 获得评奖项目相关信息(Model)
	public PjszPjxmModel getPjxmModel(String xmdm, User user);
	
	// 获得评奖项目相关信息(Map)
	public HashMap<String,String> getPjxmMap(String xmdm, User user);
	
	// 检测项目名称
	public boolean checkXmmc(PjszPjxmModel model);
	
	// 获得老师评奖项目列表
	public List<HashMap<String, String>> getLssbXmList();
	
	// 删除评奖项目
	public Boolean deletePjxm(PjszPjxmModel model, User user);
	
	
	public String checkDelete(PjszPjxmModel model, User user) throws Exception;
	
	public boolean checkXssq(PjszPjxmModel model, User user) throws Exception;
	
	public boolean checkRssz(PjszPjxmModel model, User user) throws Exception;
}
