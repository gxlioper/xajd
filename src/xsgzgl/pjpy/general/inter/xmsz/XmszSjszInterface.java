package xsgzgl.pjpy.general.inter.xmsz;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.sjsz.XmszSjszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_时间设置_Interface类
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

public interface XmszSjszInterface {
	
	// 初始化项目时间设置
	public void defaultSjszSetting(XmszSjszModel model, User user,
			HttpServletResponse response) throws IOException;

	// 保存项目时间设置
	public Boolean saveSjsz(XmszSjszModel model, User user);
	
	// 删除项目时间设置
	public Boolean deleteSjsz(XmszSjszModel model, User user);
}
