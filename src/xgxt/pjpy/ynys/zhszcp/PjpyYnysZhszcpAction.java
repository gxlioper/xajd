
package xgxt.pjpy.ynys.zhszcp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.szdw.utils.ModelToStrings;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 云南艺术评奖评优综合素质测评Action
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-10-31</p>
 */
public class PjpyYnysZhszcpAction extends CommonAction {

	private String xydm = "";//学院代码
	private String zydm = "";//专业代码
	private String nj = "";//年级
	
	/**
	 * 素质测评维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szcpWh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);//用户类型
		String userDep = getUserDep(request);//用户部门
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ynysForm.setSzlx("0");//默认为思想素质分
		String excelB = "ynys_sxzzddszb.xls";//默认为思想素质分表
		String act = request.getParameter("act");
		realTable = "sxzzddszb";
		tableName = "view_sxzzddszb";
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(act) && StringUtils.isEqual("szlx", act)) {//页面获取
			ynysForm.setSzlx(request.getParameter("szlx"));
			realTable = service.getRealTable(request.getParameter("szlx"));//表名
			tableName = "view_" + realTable;//视图名
			excelB = "ynys_" + realTable + ".xls";
			
			if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
				request.setAttribute("ty", "1");
			}
		} 
		request.setAttribute("tmp", ynysForm.getSzlx());
		request.setAttribute("excelb", excelB);
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendTableXx(request, tableName, realTable);//加载表名,视图名
//		String xm = DealString.toGBK(ynysForm.getXm());
		ModelToStrings.formToGBK(ynysForm);
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * 素质测评查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szcpQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);// 用户类型
		String userDep = getUserDep(request);// 用户部门
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		//ynysForm.setSzlx("0");// 默认为思想素质分
		
		ynysForm.setSzlx(request.getParameter("szlx"));
		
		realTable = service.getRealTable(request.getParameter("szlx"));// 表名
		tableName = "view_" + realTable;// 视图名
		String excelb = "ynys_" + realTable + ".xls";//模板下载
		request.setAttribute("excelb", excelb);
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		String szlx = request.getParameter("szlx");
		model.setCxfs(request.getParameter("cxfs"));
		List<HashMap<String, String>> topList = service.szcpQryTitle(szlx, model);//查询表头
		List<String[]> resList = service.szfQryRes(model, szlx);//查询结果
		ynysForm.setXm(DealString.toGBK(ynysForm.getXm()));
		appendResult(request, topList, resList);//加载结果集
		appendProperties(request, xydm, zydm, nj);// 加载列表
		appendTableXx(request, tableName, realTable);// 加载表名,视图名
		request.setAttribute("tmp", request.getParameter("szlx"));
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
			request.setAttribute("ty", "1");
		}
		ynysForm.setShzt(DealString.toGBK(ynysForm.getShzt()));
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * 思想政治道德素质分增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		appendProperties(request, xydm, zydm, nj);//加载列表
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// 用户类型
		String userName = (String)session.getAttribute("userName");// 用户名称
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		request.setAttribute("rs", rs);
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		return mapping.findForward("sxzzddszf");
	}
	
	/**
	 * 思想政治道德素质分增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszfModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		appendProperties(request, xydm, zydm, nj);//加载列表
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// 用户类型
		String userName = (String)session.getAttribute("userName");// 用户名称
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		request.setAttribute("rs", rs);
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		return mapping.findForward("sxzzddszf");
	}
	
	/**
	 * 保存思想政治道德素质分
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		String sFlag = service.saveSxzzf(model);//保存记录
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "操作失败,原因是因为第" + sFlag
					+ "条记录中存在错误数据!");
		}
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		appendProperties(request, xydm, zydm, nj);//加载列表
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("sxzzddszf");
	}
	
	/**
	 * 思相素质分的批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszbDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);//用户类型
		String userDep = getUserDep(request);//用户部门
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ynysForm.setSzlx(request.getParameter("szlx"));
		realTable = service.getRealTable(request.getParameter("szlx"));//表名
		tableName = "view_" + realTable;//视图名
		String sFlag = service.sxzzddszfDel(ynysForm.getCbv(), request);
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "其中第" + sFlag + "数据删除失败!");
		}
		String excelb = "ynys_" + realTable + ".xls";//模板下载
		request.setAttribute("excelb", excelb);
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendTableXx(request, tableName, realTable);//加载表名,视图名
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
			request.setAttribute("ty", "1");
		}
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * 思相素质分修改显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SzcpService service = new SzcpService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rsList  = new ArrayList<HashMap<String, String>>();
		rs = service.getSqxsxx(pkValue);
		request.setAttribute("rs", rs);
		rsList=service.getSqxsxxs(pkValue);
		request.setAttribute("rsList", rsList);
		return mapping.findForward("sxzzddfview");
	}
	
	/**
	 * 思相素质分修改保存结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxzzddszbModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		boolean update = service.updataSxzzf(model);//保存记录
		if (update) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "操作失败");
		}
		return mapping.findForward("sxzzddfview");
	}
	
	/**
	 * 实践分的增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjlxcxfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		appendProperties(request, xydm, zydm, nj);//加载列表
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// 用户类型
		String userName = (String)session.getAttribute("userName");// 用户名称
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		request.setAttribute("rs", rs);
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		return mapping.findForward("sjfadd");
	}
	
	/**
	 * 实践分的增加保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjlxcxszbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		String sFlag = service.saveSjlxf(model);//保存记录
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "操作失败,原因是因为第" + sFlag
					+ "条记录中存在错误数据!");
		}
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		appendProperties(request, xydm, zydm, nj);//加载列表
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("sjfadd");
	}
	
	/**
	 * 实践分修改显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjlxcxfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SzcpService service = new SzcpService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rsList  = new ArrayList<HashMap<String, String>>();
		rs = service.getSjlxcxf(pkValue);
		request.setAttribute("rs", rs);
		rsList=service.getSjlxcxfs(pkValue);
		request.setAttribute("rsList", rsList);
		return mapping.findForward("sjlxview");
	}
	
	/**
	 * 实践分修改保存结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjlxcxszbModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		boolean update = service.updataSjlx(model);//保存记录
		if (update) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "操作失败");
		}
		return mapping.findForward("sjlxview");
	}
	
	/**
	 * 实践分批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);//用户类型
		String userDep = getUserDep(request);//用户部门
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ynysForm.setSzlx(request.getParameter("szlx"));
		realTable = service.getRealTable(request.getParameter("szlx"));//表名
		tableName = "view_" + realTable;//视图名
		String sFlag = service.sjlxcxszbDel(ynysForm.getCbv(), request);//批量删除
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "其中第" + sFlag + "数据删除失败!");
		}
		String excelb = "ynys_" + realTable + ".xls";//模板下载
		request.setAttribute("excelb", excelb);
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendTableXx(request, tableName, realTable);//加载表名,视图名
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
			request.setAttribute("ty", "1");
		}
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * 科学文化素质分增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxwhszfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// 用户类型
		String userName = (String)session.getAttribute("userName");// 用户名称
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);//学生信息
			if (StringUtils.isNull(rs.get("xh"))) {
				rs.put("stuExists", "no");//学号不存在
			}
		}
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		ynysForm.setKcmc(null);
		ynysForm.setCj(null);
		ynysForm.setDf(null);
		ynysForm.setSfbxk(null);
		return mapping.findForward("kxwhszfadd");
	}
	
	/**
	 * 科学文化素质分保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxwhszfbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		String sFlag = service.saveKxwhf(model);//保存记录
		if (StringUtils.isNull(sFlag)) {//成功
			request.setAttribute("inserted", "yes");
		} else {//失败
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "操作失败,原因是因为第" + sFlag
					+ "条记录中存在错误数据!");
		}
		ynysForm.setCj(null);
		ynysForm.setKcmc(null);
		ynysForm.setDf(null);
		ynysForm.setSfbxk(null);
		request.setAttribute("rs", new HashMap<String, String>());
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("kxwhszfadd");
	}
	
	/**
	 * 科学文化素质分修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxwhszfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SzcpService service = new SzcpService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rsList  = new ArrayList<HashMap<String, String>>();
		rs = service.getKxwhszf(pkValue);
		request.setAttribute("rs", rs);
		rsList=service.getKxwhszfs(pkValue);
		request.setAttribute("rsList", rsList);
		return mapping.findForward("kxwhszfview");
	}
	
	/**
	 * 科学文化素质分修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward kxwhszfbmodisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		boolean update = service.updataKxwhszf(model);//保存记录
		if (update) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "操作失败");
		}
		return mapping.findForward("sxzzddfview");
	}
	
	/**
	 * 科学文化分批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxszfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		String userType = getUserType(request);//用户类型
		String userDep = getUserDep(request);//用户部门
		SzcpService service = new SzcpService();
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			ynysForm.setXydm(xydm);
		}
		ynysForm.setSzlx(request.getParameter("szlx"));
		realTable = service.getRealTable(request.getParameter("szlx"));//表名
		tableName = "view_" + realTable;//视图名
		String sFlag = service.kxwhfDel(ynysForm.cbv, request);//批量删除
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "其中第" + sFlag + "数据删除失败!");
		}
		String excelb = "ynys_" + realTable + ".xls";//模板下载
		request.setAttribute("excelb", excelb);
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendTableXx(request, tableName, realTable);//加载表名,视图名
		String cxfs = request.getParameter("szlx");
		if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
			request.setAttribute("ty", "1");
		}
		return mapping.findForward("szcpwh");
	}
	
	/**
	 * 身心分增加
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxlxszfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpService service = new SzcpService();
		appendProperties(request, xydm, zydm, nj);//加载列表
		String xh = request.getParameter("xh");
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");// 用户类型
		String userName = (String)session.getAttribute("userName");// 用户名称
		if(userType.equalsIgnoreCase("stu")){
			xh = userName;
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		request.setAttribute("rs", rs);
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		return mapping.findForward("sxlxfadd");
	}
	
	/**
	 * 身心能力分保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxllszbsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		ynysForm.setXh(request.getParameter("xh"));
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		String sFlag = service.saveSxllf(model);//保存记录
		if (StringUtils.isNull(sFlag)) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "操作失败,原因是因为第" + sFlag
					+ "条记录中存在错误数据!");
		}
		ynysForm.setFs(null);
		ynysForm.setLr(null);
		ynysForm.setDfxm(null);
		appendProperties(request, xydm, zydm, nj);//加载列表
		request.setAttribute("rs", new HashMap<String, String>());
		return mapping.findForward("sxlxfadd");
	}
	
	/** 实践分修改显示
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxllfView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		SzcpService service = new SzcpService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rsList  = new ArrayList<HashMap<String, String>>();
		rs = service.getSxllf(pkValue);
		request.setAttribute("rs", rs);
		rsList=service.getSxllfs(pkValue);
		request.setAttribute("rsList", rsList);
		return mapping.findForward("sxllview");
	}
	
	/** 实践分修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxllfszbmodi(ActionMapping mapping, ActionForm form,
		HttpServletRequest request, HttpServletResponse response)
		throws Exception {
		PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
		SzcpModel model = new SzcpModel();
		BeanUtils.copyProperties(model, ynysForm);
		SzcpService service = new SzcpService();
		boolean update = service.updataSxllfsz(model);//保存记录
		if (update) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "操作失败");
		}
		return mapping.findForward("sxllview");
		}
	
	/** 实践分删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sxllfDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	PjpyYnysZhszcpActionForm ynysForm = (PjpyYnysZhszcpActionForm) form;
	String userType = getUserType(request);//用户类型
	String userDep = getUserDep(request);//用户部门
	SzcpService service = new SzcpService();
	String tableName = "";
	String realTable = "";
	if (StringUtils.isEqual("xy", userType)) {
		xydm = userDep;
		ynysForm.setXydm(xydm);
	}
	ynysForm.setSzlx(request.getParameter("szlx"));
	realTable = service.getRealTable(request.getParameter("szlx"));//表名
	tableName = "view_" + realTable;//视图名
	String sFlag = service.sxllfDel(ynysForm.getCbv(), request);
	if (StringUtils.isNull(sFlag)) {
		request.setAttribute("deleted", "yes");
	} else {
		request.setAttribute("deleted", "no");
		request.setAttribute("failinfo", "其中第" + sFlag + "数据删除失败!");
	}
	String excelb = "ynys_" + realTable + ".xls";//模板下载
	request.setAttribute("excelb", excelb);
	appendProperties(request, xydm, zydm, nj);//加载列表
	appendTableXx(request, tableName, realTable);//加载表名,视图名
	String cxfs = request.getParameter("szlx");
	if (!StringUtils.isNull(cxfs) && "1".equalsIgnoreCase(cxfs)) {
		request.setAttribute("ty", "1");
	}
	return mapping.findForward("szcpwh");
	}
}
