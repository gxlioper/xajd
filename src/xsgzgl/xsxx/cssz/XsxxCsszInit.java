package xsgzgl.xsxx.cssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.xtwh.comm.splc.XtwhShlcService;
import xsgzgl.rcsw.qjgl.RcswQjglForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_参数设置_Init类
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

public class XsxxCsszInit {

	/**
	 * 构造参数设置Service
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public XsxxCsszInterface getCsszService(String gnmk) throws Exception {

		String className = "xsgzgl.xsxx.cssz." + gnmk + ".XsxxCsszService";

		Class interFaceClass = Class.forName(className);

		XsxxCsszInterface service = (XsxxCsszInterface) interFaceClass
				.getConstructor(null).newInstance(null);

		return service;
	}
	
	/**
	 * 参数设置_个人信息_初始化数据
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void initGrxx(RequestForm rForm, XsxxCsszForm model,
			HttpServletRequest request) {

		// 操作类型（增加，修改，删除等）
		String doType = request.getParameter("doType");
		// 访问路径
		String path = "xsxx_cssz_grxx.do";
		// 表头
		List<HashMap<String, String>> topTr = getDefaultValue(model, path);
		// 其他字段
		String[] qtzd = new String[] {};
		// 其他字段值
		String[] qtzdz = new String[] {};

		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);

		rForm.setDoType(doType);
		rForm.setPath(path);
		rForm.setTopTr(topTr);
		
		//审批流程List
		List<HashMap<String, String>> splcList = XtwhShlcService.getSplcList("xsxx");
		request.setAttribute("splcList", splcList);
		
//		// 月List
//		List<HashMap<String, String>> monthList = getOptionList("month");
//		request.setAttribute("monthList", monthList);
//
//		// 日List
//		List<HashMap<String, String>> dayList = getOptionList("day");
//		request.setAttribute("dayList", dayList);
	}
	
	private List<HashMap<String, String>> getDefaultValue(XsxxCsszForm model,
			String path) {

		DAO dao = DAO.getInstance();
		
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();

		if ("rcsw_qjgl_cssz_qjlc.do".equalsIgnoreCase(path)) {// 请假流程
			
			String[] en = new String[] { "xh" };
			String[] cn = new String[] { "学号" };
			
			topTr=dao.arrayToList(en, cn);
		}

		return topTr;
	}
	
	/**
	 * 获得下拉列表值
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public List<HashMap<String, String>> getOptionList(String lx) {

		DAO dao = DAO.getInstance();

		// 审核结果下拉框
		String[] en = null;
		String[] cn = null;

		if ("month".equalsIgnoreCase(lx)) {
			en = new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10", "11", "12" };
			cn = new String[] { "01月", "02月", "03月", "04月", "05月", "06月",
					"07月", "08月", "09月", "10月", "11月", "12月" };
		} else if ("day".equalsIgnoreCase(lx)) {
			en = new String[] { "01", "02", "03", "04", "05", "06", "07", "08",
					"09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
					"19", "20", "21", "22", "23", "24", "25", "26", "27", "28",
					"29", "30", "31" };
			cn = new String[] { "01日", "02日", "03日", "04日", "05日", "06日",
					"07日", "08日", "09日", "10日", "11日", "12日", "13日", "14日",
					"15日", "16日", "17日", "18日", "19日", "20日", "21日", "22日",
					"23日", "24日", "25日", "26日", "27日", "28日", "29日", "30日",
					"31日" };
		}

		return dao.arrayToList(en, cn);
	}
}
