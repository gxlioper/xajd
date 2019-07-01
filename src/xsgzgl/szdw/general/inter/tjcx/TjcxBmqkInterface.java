package xsgzgl.szdw.general.inter.tjcx;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.tjcx.bmqk.BmqkModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_统计查询_部门情况_Interface类
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

public interface TjcxBmqkInterface {

	// 创建部门情况统计DIV
	public void createBmqkStatDiv(SzdwGeneralForm myForm, BmqkModel model,
			User user, HttpServletResponse response) throws Exception;

	public void exportData(ServletOutputStream outputStream,
			SzdwGeneralForm myForm, User user) throws Exception;
}
