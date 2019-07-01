package xsgzgl.dtjs.general.tyjf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.comm.globals.GlobalsValue;
import xsgzgl.dtjs.general.DtjsGeneralForm;
import xsgzgl.dtjs.general.DtjsGeneralService;
import xsgzgl.dtjs.general.inter.DtjsTyjfInterface;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 党团建设_团员缴费_通用_Init类
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

public class DtjsTyjfInit {

	/**
	 * 初始化数据【团员缴费】
	 * 
	 * @param request
	 * @author 伟大的骆
	 * @throws Exception
	 * 
	 */
	public void initTyjf(RequestForm rForm, DtjsGeneralForm myForm, User user,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();

		DtjsGeneralService myService = new DtjsGeneralService();
		DtjsTyjfInterface service = myService.getDtjsTyjfService(myForm);

		// 访问路径
		String path = "dtjs_general_tyjf.do";
		// 学校代码
		String xxdm = (String) session.getAttribute("xxdm");
		myForm.setXxdm(xxdm);
		// 学校代码
		String tableName = "xg_xsxx_xjydb";
		String realTable = "xg_dtjs_tyjfb";
		// 学校拼音名称
		String xxpymc = (Base.isNull(myForm.getXxpymc()))?GlobalsValue.getXxpymc(xxdm):myForm.getXxpymc();
		myForm.setXxpymc(xxpymc);
		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		
		//====================初始化页面相关数据=====================
		String xn = Base.currXn;
		// 高级查询
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { xn });

		request.setAttribute("searchTj", searchModel);
	}
}
