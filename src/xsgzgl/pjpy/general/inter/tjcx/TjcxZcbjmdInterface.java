package xsgzgl.pjpy.general.inter.tjcx;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.zcbjmd.TjcxZcbjmdModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计查询_综测班级名单_Interface类
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

public interface TjcxZcbjmdInterface {

	// 创建综测班级名单HTMl【有等级考试】
	public void createZcbjmdDjksHTML(PjpyGeneralForm myForm,
			TjcxZcbjmdModel model, User user, HttpServletResponse response)
			throws IOException;

	// 创建综测班级名单HTMl【无等级考试】
	public void createZcbjmdNoDjksHTML(PjpyGeneralForm myForm,
			TjcxZcbjmdModel model, User user, HttpServletResponse response)
			throws IOException;
	
	// 导出综测班级名单
	public void expZcbjmd(TjcxZcbjmdModel model, OutputStream os)
			throws Exception;
}
