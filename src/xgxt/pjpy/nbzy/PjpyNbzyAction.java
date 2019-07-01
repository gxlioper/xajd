
package xgxt.pjpy.nbzy;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.nbzy.jxj.JxjService;
import xgxt.pjpy.nbzy.xjbj.XjbjService;
import xgxt.pjpy.nbzy.zhszcp.ZhszcpModel;
import xgxt.pjpy.nbzy.zhszcp.ZhszcpService;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyNbzyAction extends CommonAction {

	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	
	/**
	 * 先进班级报表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjbjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xxmc = StandardOperation.getXxmc();
		String pkValue = request.getParameter("pkValue");
		XjbjService service = new XjbjService();
		HashMap<String, String> rs = service.xjbjPrint(pkValue);
		rs.put("title", xxmc + "先进班级申报表");
		request.setAttribute("rs", rs);
		return mapping.findForward("xjbjprint");
	}

	public ActionForward zhszcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		String userType = getUserType(request);//用户类型
		String userDep = getUserDep(request);//用户部门
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		ZhszcpService service = new ZhszcpService();
		nbzyForm.setSzlx("0");//默认为职业素养
		String excelB = "nbzy_pjpy_zysycpb";//默认为职业素养表
		String act = request.getParameter("act");
		realTable = "pjpy_zysycpb";
		tableName = "view_pjpy_zysycpb";
		String tmp = "0";
		if (!StringUtils.isNull(act) && StringUtils.isEqual("szlx", act)) {//页面获取
			nbzyForm.setSzlx(request.getParameter("szlx"));
			realTable = service.getRealTable(request.getParameter("szlx"));//表名
			tableName = "view_" + realTable;//视图名
			excelB = "nbzy_" + realTable + ".xls";
			tmp = request.getParameter("szlx");
		}
		request.setAttribute("tmp", tmp);
		request.setAttribute("excelb", excelB);
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendTableXx(request, tableName, realTable);//加载表名,视图名
		nbzyForm.setXm(DealString.toGBK(nbzyForm.getXm()));
		return mapping.findForward("zhszcppage");
	}
	
	public ActionForward szqry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		String userType = getUserType(request);//用户类型
		String userDep = getUserDep(request);//用户部门
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		ZhszcpService service = new ZhszcpService();
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, nbzyForm);
		String szlx = request.getParameter("szlx");
		List<HashMap<String, String>> topTr = service.title(szlx);
		List<String[]> rsList = service.result(model, szlx);
		
		String excelB = "nbzy_pjpy_zysycpb";//默认为职业素养表
		realTable = "pjpy_zysycpb";
		tableName = "view_pjpy_zysycpb";
		realTable = service.getRealTable(request.getParameter("szlx"));//表名
		tableName = "view_" + realTable;//视图名
		excelB = "nbzy_" + realTable + ".xls";
		request.setAttribute("tmp", request.getParameter("szlx"));
		request.setAttribute("excelb", excelB);
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendQryResult(request, topTr, rsList);
		appendTableXx(request, tableName, realTable);//加载表名,视图名
		nbzyForm.setXm(DealString.toGBK(nbzyForm.getXm()));
		return mapping.findForward("zhszcppage");
	}
	
	public ActionForward zyszfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		ZhszcpService service = new ZhszcpService();
		HashMap<String, String> rs = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		String act = request.getParameter("act");
		String title = service.getTitle(act);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		nbzyForm.setLr(null);
		nbzyForm.setFs(null);
		nbzyForm.setZt(null);
		request.setAttribute("act", act);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("szfadd");
	}
	
	public ActionForward zysyfSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		ZhszcpService service = new ZhszcpService();
		HashMap<String, String> rs = new HashMap<String, String>();
		String xh = request.getParameter("xh");
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuInfo(xh);
		}
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, nbzyForm);
		String act = request.getParameter("act");
		String title = service.getTitle(act);
		String res = service.szfSave(model, act);
		appendSaveResult(request, res);//记录操作结果
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		nbzyForm.setLr(null);
		nbzyForm.setFs(null);
		nbzyForm.setZt(null);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("szfadd");
	}
	
	public ActionForward zysyfModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		ZhszcpService service = new ZhszcpService();
		String title = service.getTitle(act);
		HashMap<String, String> rs = service.viewSzf(pkValue, act);
		if (rs != null) {
			nbzyForm.setXn(rs.get("xn"));
			nbzyForm.setXq(rs.get("xq"));
			nbzyForm.setTfs(rs.get("fs"));
			nbzyForm.setTzt(rs.get("zt"));
			nbzyForm.setTlr(rs.get("lr"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("act", act);
		request.setAttribute("pkValue", pkValue);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zysyfmodipage");
	}
	
	public ActionForward zysyfModisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		nbzyForm.setXh(request.getParameter("xh"));
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		ZhszcpService service = new ZhszcpService();
		String title = service.getTitle(act);
		HashMap<String, String> rs = service.viewSzf(pkValue, act);
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, nbzyForm);
		boolean bFlag = service.szfModisave(pkValue, act, model);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		nbzyForm.setTlr(DealString.toGBK(nbzyForm.getTlr()));
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zysyfmodipage");
	}
	
	public ActionForward szfdel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		String userType = getUserType(request);//用户类型
		String userDep = getUserDep(request);//用户部门
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		ZhszcpService service = new ZhszcpService();
		String excelB = "nbzy_pjpy_zysycpb";//默认为职业素养表
		String act = request.getParameter("act");
		realTable = "pjpy_zysycpb";
		tableName = "view_pjpy_zysycpb";
		String tmp = "0";
		nbzyForm.setSzlx(request.getParameter("szlx"));
		realTable = service.getRealTable(request.getParameter("szlx"));//表名
		tableName = "view_" + realTable;//视图名
		excelB = "nbzy_" + realTable + ".xls";
		tmp = request.getParameter("szlx");
		String res = service.zyfDel(nbzyForm.getCbv(), act, request);
		if (StringUtils.isNull(res)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "操作完成，其中第" + res + "条数据操作失败,该生尚未记录任何分数!");
		}
		request.setAttribute("tmp", tmp);
		request.setAttribute("excelb", excelB);
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendTableXx(request, tableName, realTable);//加载表名,视图名
		nbzyForm.setXm(DealString.toGBK(nbzyForm.getXm()));
		return mapping.findForward("zhszcppage");
	}
	
	public ActionForward zhszcpCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		String userType = getUserType(request);//用户类型
		String userDep = getUserDep(request);//用户部门
		String tableName = "";
		String realTable = "";
		if (StringUtils.isEqual("xy", userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		ZhszcpService service = new ZhszcpService();
		String excelB = "nbzy_pjpy_zysycpb";//默认为职业素养表
//		String act = request.getParameter("act");
		realTable = "pjpy_zysycpb";
		tableName = "view_pjpy_zysycpb";
		String tmp = "0";
		nbzyForm.setSzlx(request.getParameter("szlx"));
		realTable = service.getRealTable(request.getParameter("szlx"));//表名
		tableName = "view_" + realTable;//视图名
		excelB = "nbzy_" + realTable + ".xls";
		tmp = request.getParameter("szlx");
		String xn = request.getParameter("xn");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		String nj = request.getParameter("nj");
		boolean bFlag = service.zhszcpCount(xn, xydm,zydm,bjdm,nj);
		if (bFlag) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "操作失败!");
		}
		request.setAttribute("tmp", tmp);
		request.setAttribute("excelb", excelB);
		appendProperties(request, xydm, zydm, nj);//加载列表
		appendTableXx(request, tableName, realTable);//加载表名,视图名
		nbzyForm.setXm(DealString.toGBK(nbzyForm.getXm()));
		return mapping.findForward("zhszcppage");
	}
	
	public void appendSaveResult(HttpServletRequest request, String res) throws Exception {
		String inserted = "";
		if (StringUtils.isNull(res)) {
			inserted = "yes";
		} else {
			inserted = "no";
			request.setAttribute("failinfo", String.format("操作完成，其中第%1$s条数据操作失败！", res));
		}
		request.setAttribute("inserted", inserted);
	}
	
	/**
	 * 奖学金申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String xh = "";
		if ("student".equalsIgnoreCase(userType) || "stu".equalsIgnoreCase(userType)) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		JxjService service = new JxjService(); 
		if (!StringUtils.isNull(xh)) {
			rs = service.viewJxjInfo(xh); 
		}
		appendJxjList(request);
		request.setAttribute("rs", rs);
		rs.put("xn", Base.getJxjsqxn());
		appendProperties(request, xydm, zydm, nj);
		nbzyForm.setSqly(DealString.toGBK(nbzyForm.getSqly()));
		nbzyForm.setFdyyj(DealString.toGBK(nbzyForm.getFdyyj()));
		nbzyForm.setQsh(DealString.toGBK(nbzyForm.getQsh()));
		return mapping.findForward("jxjsqpage");
	}
	
	/**
	 * 奖学金申请保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSqsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String xh = "";
		if ("student".equalsIgnoreCase(userType) || "stu".equalsIgnoreCase(userType)) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = request.getParameter("xh");
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		JxjService service = new JxjService(); 
		if (!StringUtils.isNull(xh)) {
			rs = service.viewJxjInfo(xh); 
		}
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model ,nbzyForm);
		String res = service.sqSave(model, request);
		if (StringUtils.isNull(res)) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			res = service.getBugInfo(res);
			request.setAttribute("failinfo", res);
		}
		appendJxjList(request);
		request.setAttribute("rs", rs);
		nbzyForm.setSqly(DealString.toGBK(nbzyForm.getSqly()));
		nbzyForm.setFdyyj(DealString.toGBK(nbzyForm.getFdyyj()));
		nbzyForm.setQsh(DealString.toGBK(nbzyForm.getQsh()));
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("jxjsqpage");
	}
	
	/**
	 * 奖学金查询页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjqry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		return mapping.findForward("sqqrypage");
	}
	/**
	 * 奖学金查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sqqryres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		JxjService service = new JxjService();
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, nbzyForm);
		List<HashMap<String, String>> topList = service.getJxjTitle();
		List<String[]> resList = service.getJxjResult(model);
		appendQryResult(request, topList, resList);
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		return mapping.findForward("sqqrypage");
	}
	
	/**
	 * 奖学金修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		String act = request.getParameter("act");
		if (!StringUtils.isNull(act) && "view".equalsIgnoreCase(act)) {
			request.setAttribute("act", act);
		}
		HashMap<String, String> rs = service.viewJxjxx(pkValue);
		if (rs != null) {
			nbzyForm.setSqly(rs.get("sqly"));
			nbzyForm.setFdyyj(rs.get("fdyyj"));
			nbzyForm.setQsh(rs.get("qsh"));
			nbzyForm.setJxjdm(rs.get("jxjdm"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		appendJxjList(request);
		return mapping.findForward("modipage");
	}
	
	/**
	 * 奖学金修改保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjModisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		JxjService service = new JxjService();
		HashMap<String, String> rs = service.viewJxjxx(pkValue);
		ZhszcpModel model = new ZhszcpModel();
		BeanUtils.copyProperties(model, nbzyForm);
		boolean bFlag = service.jxjmodiSave(model, pkValue, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		appendJxjList(request);
		nbzyForm.setSqly(DealString.toGBK(nbzyForm.getSqly()));
		nbzyForm.setFdyyj(DealString.toGBK(nbzyForm.getFdyyj()));
		nbzyForm.setQsh(DealString.toGBK(nbzyForm.getQsh()));
		return mapping.findForward("modipage");
	}
	
	/**
	 * 奖学金批量删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		JxjService service = new JxjService();
		String res = service.jxjDel(nbzyForm.getCbv(), request);
		if (StringUtils.isNull(res)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "操作完成,其中第" + res + "条数据操作失败!");
		}
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		return mapping.findForward("sqqrypage");
	}
	
	/**
	 * 奖学金审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		nbzyForm.setXn(Base.getJxjsqxn());
		String act = request.getParameter("go");
		JxjService service = new JxjService();
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(act) && "go".equalsIgnoreCase(act)) {
			topList = service.shTitle(userType);
			ZhszcpModel model = new ZhszcpModel();
			BeanUtils.copyProperties(model, nbzyForm);
			rs = service.shResult(userType, model);
		}
		appendQryResult(request, topList, rs);
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		return mapping.findForward("jxjshpage");
	}
	
	/**
	 * 奖学金审核结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shRes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			nbzyForm.setXydm(xydm);
		}
		String jg = request.getParameter("param1");
		nbzyForm.setXn(Base.getJxjsqxn());
		JxjService service = new JxjService();
		String res = service.result(userType, nbzyForm.getCbv(), request, jg);
		if (StringUtils.isNull(res)) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", "操作完成,其中第" + res + "条数据操作失败!");
		}
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		return mapping.findForward("jxjshpage");
	}
	
	/**
	 * 奖学金单个审核结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		JxjService service = new JxjService();
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = service.jxjShone(pkValue, userType);
		String act = request.getParameter("act");
		if (!StringUtils.isNull(act) && "save".equalsIgnoreCase(act)) {
			boolean bFlag = service.jxjoneshSave(userType, pkValue, request
					.getParameter("yesNo"), request.getParameter("yj"), request
					.getParameter("xyshyj"), request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			nbzyForm.setYj(DealString.toGBK(nbzyForm.getYj()));
			nbzyForm.setXyshyj(DealString.toGBK(nbzyForm.getXyshyj()));
		}
		nbzyForm.setYesNo(rs.get("yesno"));
		nbzyForm.setYj(rs.get("yj"));
		nbzyForm.setXyshyj(rs.get("xyshyj"));
		nbzyForm.setSqly(rs.get("sqly"));
		nbzyForm.setFdyyj(rs.get("fdyyj"));
		appendChkList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjshonepage");
	}
	
	public ActionForward tjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		JxjService service = new JxjService();
		List<HashMap<String, String>> titList = service.tjTitle();
		String xn = request.getParameter("xn");
		String jxjdm = request.getParameter("jxjdm");
		List<String[]> rsList = service.tjResult(xn, jxjdm);
		appendQryResult(request, titList, rsList);
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("tjszpage");
	}
	
	public ActionForward tjszsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		JxjService service = new JxjService();
		boolean bFlag = service.tjSave(nbzyForm.getXn(), nbzyForm.getJxjdm(), nbzyForm.getTj(), nbzyForm.getBl());
		if (bFlag) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
		}
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("tjszpage");
	}
	
	public ActionForward tjDelres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyNbzyActionForm nbzyForm = (PjpyNbzyActionForm) form;
		JxjService service = new JxjService();
		boolean bFlag = service.tjDel(nbzyForm.getCbv());
		if (bFlag) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
		}
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("tjszpage");
	}
	
	public ActionForward jxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
//		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
//		JxjService service = new JxjService();
		/*if (!StringUtils.isNull(pkValue)) {
			rs = service.jxjPrint(pkValue);
		}*/
		request.setAttribute("rs", rs);
		request.setAttribute("title", "宁波职业技术学院综合奖学金申报表");
		return mapping.findForward("jxjprint");
	}
}
