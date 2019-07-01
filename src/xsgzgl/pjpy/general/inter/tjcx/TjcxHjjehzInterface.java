package xsgzgl.pjpy.general.inter.tjcx;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.hjjehz.TjcxHjjehzModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计查询_获奖金额汇总_Interface类
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

public interface TjcxHjjehzInterface {

	// 获得表头文件
	public List<HashMap<String, String>> getHjjehzTop(TjcxHjjehzModel model,
			User user);

	// 获得结果集
	public ArrayList<String[]> getHjjehzList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集
	public String createHjjehzHTML(SearchRsModel rsModel,
			TjcxHjjehzModel model, ArrayList<String[]> rsArrList, User user);

	// 导出获奖金额汇总
	public void expHjjehz(TjcxHjjehzModel model, OutputStream os)
			throws Exception;

	
	// 传媒获奖名单结果集
	public ArrayList<String[]> getCMHJMDHZList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;
	// 获得表头文件
	public List<HashMap<String, String>> getCMHJMDHZTop(TjcxHjjehzModel model,
			User user);

	// 导出传媒获奖名单汇总
	public void expCmhjmchz(TjcxHjjehzModel model, OutputStream os)
			throws Exception;
}
