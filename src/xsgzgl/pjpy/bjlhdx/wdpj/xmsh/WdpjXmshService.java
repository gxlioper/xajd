package xsgzgl.pjpy.bjlhdx.wdpj.xmsh;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.PjpyGeneralService;
import xsgzgl.pjpy.general.inter.wdpj.WdpjPjtjInterface;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXmshInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbService;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshDAO;
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
 * @author qlj
 * @version 1.0
 */

public class WdpjXmshService extends xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshService{
	
	WdpjXmshDAO dao = new WdpjXmshDAO();
	
	// ===========================审核状态修改 begin===============================
	public boolean updateShzt(WdpjXmshModel model, HttpServletRequest request, User user) throws Exception {

		getModelValue(model, request);
		
		String shzt=model.getShzt();
		
		dao.resultShzt(model, user);
		
		boolean flag=dao.updateShzt(model, user);
		
		if("th".equalsIgnoreCase(shzt) && flag){
			
			 flag=dao.updateThzt(model, user);
		}
		
		flag=dao.updateSqjg(model, user);
		
		return  flag;
	}
	// ===========================审核状态修改 end===============================	
}
