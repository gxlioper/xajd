package xsgzgl.pjpy.general.inter.tjcx;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.tjmdhz.TjcxTjmdhzModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计查询_推荐名单汇总_Interface类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public interface TjcxTjmdhzInterface {

	// 获得表头文件
	public List<HashMap<String, String>> getTjmdhzTop(TjcxTjmdhzModel model,
			User user);

	// 获得结果集
	public ArrayList<String[]> getTjmdhzList(PjpyGeneralForm myForm,
			TjcxTjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// 构建结果集
	public String createTjmdhzHTML(SearchRsModel rsModel,
			TjcxTjmdhzModel model, ArrayList<String[]> rsArrList, User user);

	// 导出获奖金额汇总
	public void expTjmdhz(PjpyGeneralForm myForm,TjcxTjmdhzModel model,User user, OutputStream os)
			throws Exception;
}
