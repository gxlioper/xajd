package xsgzgl.pjpy.general.inter.xmsz;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.xmsz.pjtj.XmszPjtjModel;
import xsgzgl.pjpy.general.xmsz.xmjd.XmszXmjdModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_评奖条件_Interface类
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

public interface XmszPjtjInterface {

	// 初始化评奖条件设置
	public void defaultPjtjSetting(XmszPjtjModel model, User user,
			HttpServletResponse response) throws IOException;
	
	// 获得可否评奖信息
	public String getPjtjMessage(XmszPjtjModel model, User user);

	// 初始化评奖条件信息
	public HashMap<String, String> getPjtjInfo(XmszPjtjModel model,
			User user);
	
	// 保存评奖条件
	public Boolean savePjtj(XmszPjtjModel model, User user);
	
	// 删除评奖条件
	public Boolean deletePjtj(XmszPjtjModel model, User user);
}
