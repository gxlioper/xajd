package xsgzgl.pjpy.general.inter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.form.User;
import xsgzgl.pjpy.general.index.PjpyIndexModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖项目_Interface类
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

public interface PjpyIndexInterface {

	// 初始化已定制评奖流程
	public void defaultCustomPjlc(PjpyIndexModel model, User user,
			HttpServletResponse response) throws IOException;

	// 初始化自由流程
	public void defaultFreePjlc(PjpyIndexModel model, User user,
			HttpServletResponse response) throws IOException;

	// 保存评奖流程
	public Boolean savePjlc(PjpyIndexModel model, User user);
	
	// 保存开始新评奖
	public Boolean saveStart(PjpyIndexModel model, User user);
	
	// 提交评奖流程
	public Boolean submitPjlc(String lcdj, User user) ;
	
	// ===========================结束本次评奖=====================================
	// 获取本次评奖统计信息
	public List<HashMap<String, String>> getBcpjtjInfo(User user)throws Exception;
	
	// 数据进入历史库
	public void  theEnd(User user);

}
