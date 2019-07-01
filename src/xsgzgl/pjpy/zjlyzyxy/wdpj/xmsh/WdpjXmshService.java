package xsgzgl.pjpy.zjlyzyxy.wdpj.xmsh;


import java.util.HashMap;

import xgxt.form.User;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_项目审核_通用_Service类
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

public class WdpjXmshService extends xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshService {
	
	WdpjXmshDAO dao = new WdpjXmshDAO();
	// ===========================评奖项目审核详细 begin===============================
	public HashMap<String, Object> defaultWdpjXmsh(WdpjXmshModel model, User user) throws Exception {

		HashMap<String,Object>rs=new HashMap<String,Object>();
		
		WdpjLssbService xmsbService=new WdpjLssbService();
		
		PjpyZhcpService zhcpService=new PjpyZhcpService();
		
		String xmdm=model.getXmdm();
		
		String xh=model.getXh()[0];
		
		// 评奖评优学生申请信息（学生基本信息）
		rs.putAll(dao.getXmsqInfo(model, user));
		// 评奖项目审核信息
		rs.put("xmshInfo",dao.getXmshInfo(model, user));
		// 学生课程成绩
		rs.put("kccjInfo",xmsbService.getXscjList(xh));
		// 学生综测成绩
		rs.put("zccjInfo",zhcpService.getBczcInfo(xh));
		return rs;
	}
	// ===========================评奖项目审核详细 end===============================
	
}
