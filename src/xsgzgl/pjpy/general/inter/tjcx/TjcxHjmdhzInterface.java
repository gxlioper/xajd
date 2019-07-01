package xsgzgl.pjpy.general.inter.tjcx;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.hjmdhz.TjcxHjmdhzModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计查询_获奖名单汇总_Interface类
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

public interface TjcxHjmdhzInterface {

	// 获得表头文件
	public List<HashMap<String, String>> getHjmdhzTop(TjcxHjmdhzModel model,
			User user);

	// 获得结果集
	public ArrayList<String[]> getHjmdhzList(PjpyGeneralForm myForm,
			TjcxHjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集
	public String createHjmdhzHTML(SearchRsModel rsModel,
			TjcxHjmdhzModel model, ArrayList<String[]> rsArrList, User user);

	// 导出获奖名单汇总
	public void expHjmdhz(TjcxHjmdhzModel model, OutputStream os)
			throws Exception;
}
