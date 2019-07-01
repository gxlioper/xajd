package xsgzgl.pjpy.general.inter.xmsz;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.xmsy.XmszXmsyModel;


/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_项目顺延_Interface类
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

public interface XmszXmsyInterface {
	
	// 获得项目顺延列表
	public List<HashMap<String, String>> getXmsyList(XmszXmsyModel model) ;

	public void showXmsyDiv(XmszXmsyModel model, HttpServletResponse response)throws IOException;
	
	public boolean saveXmsy(XmszXmsyModel model,User user)throws Exception;
}
