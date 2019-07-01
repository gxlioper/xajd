package xsgzgl.pjpy.xzgyzyjsxy.wdpj.xssq;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.comm.BasicService;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.inter.wdpj.WdpjXssqInterface;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmDAO;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmService;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;
import xsgzgl.pjpy.xzgyzyjsxy.zhcp.PjpyZhcpDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_学生申请_通用_Service类
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

public class WdpjXssqService extends xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqService {

	WdpjXssqDAO dao = new WdpjXssqDAO();


	/**
	 * 学生申请by本周期
	 * 
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXssqByZq(WdpjXssqModel model,
			User user) throws Exception {

		List<HashMap<String, String>> xssqList = dao.getXssqByZq(model, user);
		
		return xssqList;
	}

	/**
	 * 获取历年综测信息
	 */
	public List<String[]> getXszcInfo(WdpjXssqModel model, User user) {
		// TODO 自动生成方法存根
		String xh=user.getUserName();
		
		PjpyZhcpDAO dao=new PjpyZhcpDAO();
		
		return dao.getZcList(xh);
	}
	
	/**
	 * 获取学生本次综测信息
	 */
	public List<Object> getZcxxByZq(WdpjXssqModel model, User user) throws Exception {
		
		String xh=user.getUserName();
		
		PjpyZhcpDAO dao=new PjpyZhcpDAO();
		
		return dao.getZcByZqList(xh);
	}
	

}
