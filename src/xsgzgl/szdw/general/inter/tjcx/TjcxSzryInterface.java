package xsgzgl.szdw.general.inter.tjcx;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.tjcx.szry.SzryModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_统计查询_思政人员_Interface类
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

public interface TjcxSzryInterface {

	// 创建队伍维护DIV
	public void createSzryStatDiv(SzdwGeneralForm myForm, SzryModel model,
			User user, HttpServletResponse response) throws Exception;
	//导出数据
	public void exportData(ServletOutputStream outputStream,
			SzdwGeneralForm myForm, User user) throws Exception;
}
