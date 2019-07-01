package xsgzgl.pjpy.general.inter;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.PjpyXmszModel;
import xsgzgl.pjpy.general.xmsz.pjtj.XmszPjtjModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_Interface类
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

public interface PjpyXmszInterface {

	// 获得项目审核资格信息
	public String getXmshzg(PjpyXmszModel model, User user) throws Exception;
	
}
