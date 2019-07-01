package xsgzgl.pjpy.general.inter.xmsz;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.xmjd.XmszXmjdModel;


/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_项目兼得_Interface类
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

public interface XmszXmjdInterface {
	
	// 初始化项目兼得设置
	public void defaultXmjdSetting(XmszXmjdModel model, User user,
			HttpServletResponse response) throws Exception;

	// 保存项目兼得
	public Boolean saveXmjd(XmszXmjdModel model, User user);

	// 删除项目兼得
	public Boolean deleteXmjd(XmszXmjdModel model, User user);
}
