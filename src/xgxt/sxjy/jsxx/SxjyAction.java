package xgxt.sxjy.jsxx;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.SxjyForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 江苏信息_思想教育-action类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 骆嘉伟
 * @version 1.0
 */
public class SxjyAction extends DispatchAction {
	/**
	 * @describe 新生问卷调查
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward xswjdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		String tableName = "jsxx_xsdcsjb";
		String realTable = "jsxx_xsdcsjb";
		String title = "思想教育 - 新生问卷调查 - 问卷调查表";

		SxjyForm myForm = (SxjyForm) form;
		SxjyService service = new SxjyService();
		//
		// String nj = myForm.getJxnd();
		// nj = (nj == null) ? Base.currNd : nj;
		// myForm.setJxnd(nj);
		//
		Vector<Object> vector = new Vector<Object>();
		List<HashMap<String, String>> topTr = null;
		//
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			String[] colList = new String[] { "主键", "行号", "sjlsh", "sjm",
					"sjxsbj", "jrsj" };
			topTr = service.getTopTr(tableName, colList);
			// 取得查询结果
			vector = service.getXswjdcList(myForm, colList, userType);
		}
		request.setAttribute("rs", vector);
		if (vector != null && !"".equals(vector)) {
			request.setAttribute("rsNum", vector.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}
		//
		// setList(request);
		request.setAttribute("rs", vector);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("xswjdc");
	}

	/**
	 * @describe 新生问卷调查表维护
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward xswjdcOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "思想教育 - 新生问卷调查 - 问卷调查表维护";
		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");

		SxjyService service = new SxjyService();
		SxjyForm myForm = (SxjyForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			String sjlsh = service.getSjLsh();
			rs.put("sjlsh", sjlsh);
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getXswjbDetail(pkValue);
		}
		if ("del".equalsIgnoreCase(doType)) {
			boolean inserted = service.delSjxx(pkValue, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean inserted = service.saveSjxx(myForm, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		// setList(request);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("xswjdcOne");
	}

	/**
	 * @describe 新生问卷调查表维护
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward xswjdcNr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "思想教育 - 新生问卷调查 - 问卷调查表内容维护";
		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : DealString
				.toGBK(request.getParameter("pk"));

		SxjyService service = new SxjyService();
		SxjyForm myForm = (SxjyForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			String stbh = service.getNrLsh();
			String nowDay = service.getNowDay();
			rs.put("stbh", stbh);
			rs.put("jrsj", nowDay);
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getStDetail(pkValue);
		}
		if ("del".equalsIgnoreCase(doType)) {
			boolean inserted = service.delSt(pkValue);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean inserted = service.saveNrxx(myForm, request);
			if (inserted) {
				if ("edit".equals(request.getParameter("savetype"))) {
					doType = "edit";
					rs = service.getStDetail(myForm.getStbh());
					request.setAttribute("result", "yes");
					request.setAttribute("pkValue", myForm.getStbh());
				} else if ("add".equals(request.getParameter("savetype"))) {
					request.setAttribute("result", "yes");
					return new ActionForward(
							"/jsxx_xswjdc.do?method=xswjdcNr&type=add", false);
				}
			} else {
				request.setAttribute("result", "no");
			}
		}

		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("xswjdcNr");
	}

	/**
	 * @describe 新生问卷调查表维护
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward stzj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String title = "思想教育 - 新生问卷调查 - 试题组卷维护";
		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : DealString
				.toGBK(request.getParameter("pk"));

		SxjyService service = new SxjyService();
		SxjyForm myForm = (SxjyForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> StInfo = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getXswjbDetail(pkValue);
		}
		if ("del".equalsIgnoreCase(doType)) {
			boolean inserted = service.delSjxx(pkValue, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/jsxx_xswjdc.do?method=xswjdc&go=go",
					false);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean inserted = service.saveZj(myForm, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		request.setAttribute("StInfo", StInfo);
		request.setAttribute("title", title);
		request.setAttribute("sjList", service.getWjdcb());
		request.setAttribute("stList", service.getAllStList());
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("stzj");
	}

	/**
	 * @describe 新生问卷调查填写
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward xswjdctx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		String tableName = "jsxx_xsdcsjb";
		String realTable = "jsxx_xsdcsjb";
		String title = "思想教育 - 新生问卷调查 - 问卷调查填写";

		SxjyForm myForm = (SxjyForm) form;
		SxjyService service = new SxjyService();

		Vector<Object> vector = new Vector<Object>();
		List<HashMap<String, String>> topTr = null;
		//
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			String[] colList = new String[] { "主键", "行号", "sjlsh", "sjm",
					"sjxsbj", "jrsj" };
			topTr = service.getTopTr(tableName, colList);
			// 取得查询结果
			vector = service.getXswjdcList(myForm, colList, userType);
		}
		request.setAttribute("rs", vector);
		if (vector != null && !"".equals(vector)) {
			request.setAttribute("rsNum", vector.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		request.setAttribute("rs", vector);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("xswjdctx");
	}

	/**
	 * @describe 新生问卷调查表维护
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward xswjdctxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "思想教育 - 新生问卷调查 - 问卷调查填写";
		String doType = request.getParameter("type");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");

		SxjyService service = new SxjyService();
		SxjyForm myForm = (SxjyForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> StInfo = new HashMap<String, String>();

		String nowSt = "";
		if (!"yes".equals(request.getParameter("perios"))) {
			nowSt = request.getParameter("nowSt") == null ? "0" : String
					.valueOf(Integer.parseInt(String.valueOf(request
							.getParameter("nowSt"))) + 1);
		} else {
			nowSt = String.valueOf(Integer.parseInt(String.valueOf(request
					.getParameter("nowSt"))) - 1);
		}

		if (pkValue == null || "".equals(pkValue)) {
			pkValue = myForm.getSjlsh();
		}
		String num = service.getStNum(pkValue);
		if (nowSt.equalsIgnoreCase(num)) {
			request.setAttribute("lastSt", "yes");
		}
		if (nowSt.equalsIgnoreCase("1")) {
			request.setAttribute("first", "yes");
		}
		if ("stu".equalsIgnoreCase(userType)
				|| "student".equalsIgnoreCase(userType)) {
			myForm.setXh(userName);
		}
		if ("add".equalsIgnoreCase(doType)) {
			HashMap<String, String> sj = service.getWjmc(pkValue);
			rs.put("sjlsh", sj.get("sjlsh"));
			rs.put("sjm", sj.get("sjm"));
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getSt(pkValue, nowSt);
			HashMap<String, String> da = service.getStDa(pkValue, nowSt, myForm
					.getXh());
			rs.put("da", da.get("stda"));
			rs.put("zda", da.get("stzda"));
			if (rs != null) {
				rs.put("nowSt", String.valueOf(nowSt));
				request.setAttribute("userType", userType);
				request.setAttribute("title", title);
				request.setAttribute("rs", rs);
				request.setAttribute("pkValue", pkValue);
				request.setAttribute("doType", doType);
				return mapping.findForward("xswjdctxOne");
			}
		}
		if ("st".equalsIgnoreCase(doType)) {
			title = "思想教育 - 新生问卷调查 - 问卷调查统计";
			request.setAttribute("userType", userType);
			request.setAttribute("title", title);
			request.setAttribute("rs", rs);
			request.setAttribute("pkValue", pkValue);
			request.setAttribute("doType", doType);
			return mapping.findForward("xswjdctxOne");
		}
		if ("save".equalsIgnoreCase(doType)) {
			pkValue = myForm.getSjlsh();
			rs = service.getSt(pkValue, nowSt);
			if (!"yes".equals(request.getParameter("perios"))) {
				service.saveTx(myForm, request);
			}
			HashMap<String, String> da = service.getStDa(pkValue, nowSt, myForm
					.getXh());
			rs.put("da", da.get("stda"));
			rs.put("zda", da.get("stzda"));

			doType = "edit";
		}

		rs.put("nowSt", String.valueOf(nowSt));

		if ("yes".equals(request.getParameter("last"))) {
			request.setAttribute("result", "yes");
		}
		request.setAttribute("StInfo", StInfo);
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		request.setAttribute("sjList", service.getWjdcb());
		request.setAttribute("stList", service.getAllStList());
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("xswjdctxOne");
	}

	/**
	 * @describe 新生问卷调查情况
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward xswjdcqk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "view_jsxx_xswjdcqk";
		String realTable = "view_jsxx_xswjdcqk";
		String title = "思想教育 - 新生问卷调查 - 问卷调查情况";

		SxjyForm myForm = (SxjyForm) form;
		SxjyService service = new SxjyService();

		Vector<Object> vector = new Vector<Object>();
		List<HashMap<String, String>> topTr = null;

		// 取得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		if (userType.equalsIgnoreCase("stu")) {
			HashMap<String, String> map = service.getXsjbxx(userName);
			myForm.setXh(map.get("xh"));
			myForm.setXm(map.get("xm"));
			myForm.setXydm(map.get("xydm"));
			myForm.setZydm(map.get("zydm"));
			myForm.setBjdm(map.get("bjdm"));
			myForm.setNj(map.get("nj"));
		}

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;

		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			String[] colList = new String[] { "主键", "xh", "xb", "xm", "xymc",
					"zymc", "bjmc" };
			topTr = service.getTopTr(tableName, colList);
			// 取得查询结果
			vector = service.getXswjdcQkList(myForm, colList);		
		}
		request.setAttribute("rs", vector);
		if (vector != null && !"".equals(vector)) {
			request.setAttribute("rsNum", vector.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("sjList", service.getWjdcb());
		request.setAttribute("rs", vector);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("topTr", topTr);
		request.setAttribute("sjlshV", myForm.getSjlsh() == null ? "" : myForm
				.getSjlsh());
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("xswjdcqk");
	}

	/**
	 * @describe 新生问卷调查情况查看
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward xswjdcqkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String title = "思想教育 - 新生问卷调查 - 问卷调查情况查看";
		String doType = request.getParameter("type");
		String userType = session.getAttribute("userType").toString();
		// String userName = session.getAttribute("userName").toString();
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");
		String sjlsh = request.getParameter("sjlsh");

		SxjyService service = new SxjyService();
		// SxjyForm myForm = (SxjyForm) form;

		// List<HashMap<String, String>> rsList = new ArrayList<HashMap<String,
		// String>>();
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("view".equalsIgnoreCase(doType)) {
			rs = service.getWjdcQk(pkValue, sjlsh);
			rs.put("sjlsh", sjlsh);
		}

		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		request.setAttribute("sjList", service.getWjdcb());
		request.setAttribute("stList", service.getAllStList());
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("xswjdcqkOne");
	}
}
