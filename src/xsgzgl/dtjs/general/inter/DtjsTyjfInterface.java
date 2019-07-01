package xsgzgl.dtjs.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.dtjs.general.DtjsGeneralForm;
import xsgzgl.dtjs.general.tyjf.DtjsTyjfModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 党团建设_团员缴费_Interface类
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

public interface DtjsTyjfInterface {

	// 获得表头文件【团员缴费】
	public List<HashMap<String, String>> getDtjsTyjfTop(DtjsTyjfModel model,
			User user);

	// 获得结果集【团员缴费】
	public ArrayList<String[]> getDtjsTyjfList(DtjsGeneralForm myForm,
			DtjsTyjfModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集【团员缴费】
	public String createDtjsTyjfHTML(SearchRsModel rsModel,
			DtjsTyjfModel model, ArrayList<String[]> rsArrList, User user);
	
	// 构建结果集【团员缴费】(编辑)
	public String createDtjsTyjfHTMLofEdit(SearchRsModel rsModel,
			DtjsTyjfModel model, ArrayList<String[]> rsArrList, User user);
	
	// 保存页面信息【团员缴费】
	public boolean saveTyjf (DtjsTyjfModel model, User user)throws Exception;
	
	// 编辑保存页面信息【团员缴费】
	public boolean saveBjTyjf (DtjsTyjfModel model, User user)throws Exception;
}
