package xgxt.jxgl.gt;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.jxgl.JxglDAO;
import xgxt.jxgl.JxglForm;
import xgxt.utils.ExcelMethods;

import common.Globals;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理-action类
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
public class JxglAction extends DispatchAction {

	/**
	 * @describe 教官信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward jgxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		String tableName = "view_jxgl_jgxx";
		String realTable = "jxgl_jgxxb";
		String title = "军训管理 - 信息维护 - 教官信息维护";

		JxglForm myForm = (JxglForm) form;
		JxglService service = new JxglService();

		Vector<Object> vector = new Vector<Object>();
		List<HashMap<String, String>> topTr = null;

		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			String[] colList = new String[] { "主键", "行号", "jgbh", "xm", "xb",
					"mzmc", "lxdh", "jxnd", "bzdm" };
			topTr = service.getTopTr(tableName, colList);
			HashMap<String, String> map = new HashMap<String, String>();
			topTr.remove(topTr.size() - 1);
			map.put("en", "bzmc");
			map.put("cn", "编制名称");
			topTr.add(map);
			// 取得查询结果
			vector = service.getJgxxList(myForm, colList);
			// 页面返回保证姓名不乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setJgbh(DealString.toGBK(myForm.getJgbh()));
		}
		request.setAttribute("rs", vector);
		if (vector != null && !"".equals(vector)) {
			request.setAttribute("rsNum", vector.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		setList(request);
		request.setAttribute("ldList", service.getLdListB(""));
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("jgxx");
	}

	/**
	 * @describe 单个教官信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward jgxxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");

		JxglService service = new JxglService();
		JxglForm myForm = (JxglForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			rs.put("bz", "");
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getJgxx(pkValue);
			if (rs.get("sfzld") != null
					&& !"".equalsIgnoreCase(rs.get("sfzld"))) {
				request.setAttribute("tp", "tp");
			}
		}
		if ("del".equalsIgnoreCase(doType)) {
			boolean inserted = service.delJgxx(pkValue, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/jxglgt.do?method=jgxx", false);
		}
		if ("save".equalsIgnoreCase(doType)) {
			String type = request.getParameter("doType");
			if ("add".equalsIgnoreCase(type) && service.isJg(myForm)) {
				request.setAttribute("result", "no");
			} else {
				boolean inserted = service.saveJgxx(myForm, request);
				if (inserted) {
					request.setAttribute("result", "yes");
				}
			}
		}
		setList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("jgxxOne");
	}

	/**
	 * @describe 教师信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward jsxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		String tableName = "view_jxgl_jsxx";
		String realTable = "jxgl_ddjsxxb";
		String title = "军训管理 - 信息维护 - 带队教师信息维护";

		JxglForm myForm = (JxglForm) form;
		JxglService service = new JxglService();
		
		Vector<Object> vector = new Vector<Object>();
		List<HashMap<String, String>> topTr = null;

		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			String[] colList = new String[] { "主键", "行号", "jsdm", "xm", "xb",
					"mzmc", "lxdh", "jxnd", "sfzld" };
			topTr = service.getTopTr(tableName, colList);
			// 取得查询结果
			vector = service.getJsxxList(myForm, colList);
			// 页面返回保证姓名不乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setJsdm(DealString.toGBK(myForm.getJsdm()));
		}
		request.setAttribute("rs", vector);
		if (vector != null && !"".equals(vector)) {
			request.setAttribute("rsNum", vector.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}
		setList(request);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("ldList", service.getLdListB(""));
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("jsxx");
	}

	/**
	 * @describe 单个带队教师信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward jsxxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");

		JxglService service = new JxglService();
		JxglForm myForm = (JxglForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			rs.put("bz", "");
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getJsxxOne(pkValue);
			if (rs.get("sfzld") != null
					&& !"".equalsIgnoreCase(rs.get("sfzld"))) {
				request.setAttribute("tp", "tp");
			}
		}
		if ("del".equalsIgnoreCase(doType)) {
			boolean inserted = service.delJsxx(pkValue, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/jxglgt.do?method=jsxx", false);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean inserted = service.saveJsxx(myForm, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		setList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("jsxxOne");
	}

	/**
	 * @describe 军训编制
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward jxbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "军训管理-军训编制－建制维护";

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rsT = new HashMap<String, String>();

		JxglForm myForm = (JxglForm) form;
		JxglService service = new JxglService();
		HashMap<String, String> map = service.getMaxJz();
		String xxdm = StandardOperation.getXxdm();
		String nj = myForm.getNj();
		nj = (nj == null) ? Base.currNd : nj;
		myForm.setNj(nj);
		rsT.put("nj", nj);

		if (Integer.parseInt(service.getJzNum()) < 2) {
			request.setAttribute("jz", "min");
			request.setAttribute("title", title);
			return mapping.findForward("jxbz");
		}
		String act = Base.chgNull(request.getParameter("act"), "", 1);

		if ("del".equalsIgnoreCase(act)) {
			boolean b = service.delJxbz(Base.chgNull(request
					.getParameter("cbVal"), "", 1), nj);
			if (b) {
				request.setAttribute("isDel", "is");
			} else {
				request.setAttribute("isDel", "no");
			}
		}

		String menuListTop = service.getJxjz(nj);

		String[] sT = null;
		List<String> jz = service.getAllJz();
		String allJz = "";
		if (jz != null && jz.size() > 0) {
			for (int i = 0; i < jz.size(); i++) {
				allJz += jz.get(i) + "-";
			}
		}
		setList(request);
		
		request.setAttribute("njList", Base.getXnndList());
		request.setAttribute("bzdm", sT != null ? sT[0] : "");
		request.setAttribute("bzmc", sT != null ? sT[1] : "");
		request.setAttribute("bzdj", sT != null ? sT[2] : "");
		request.setAttribute("bzdjmc", sT != null ? sT[3] : "");
		request.setAttribute("zdy", sT != null ? sT[4] : "");
		request.setAttribute("jgmc", sT != null ? sT[5] : "");
		request.setAttribute("ssjz", sT != null ? sT[6] : "");
		request.setAttribute("bz", sT != null ? sT[7] : "");
		request.setAttribute("xb", sT != null ? sT[8] : "");
		request.setAttribute("xn", sT != null ? sT[9] : "");
		request.setAttribute("allJz", allJz);
		request.setAttribute("rsT", rsT);
		request.setAttribute("xxdm", xxdm);
		if(xxdm.equalsIgnoreCase(Globals.XXDM_JHZYJSXY)||xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
		request.setAttribute("printdm", xxdm);
		}
		request.setAttribute("userType", userType);
		request.setAttribute("maxJz", map.get("jzmc"));
		request.setAttribute("menuListTop", menuListTop);
		request.setAttribute("title", title);
		return mapping.findForward("jxbz");
	}

	/**
	 * @describe 最大单位编制维护
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward addMaxjz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglService service = new JxglService();
		HashMap<String, String> map = service.getMaxJz();
		String xxdm = StandardOperation.getXxdm();

		String title = "军训管理-军训编制－增加" + map.get("jzmc") + "级建制";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		JxglForm myForm = (JxglForm) form;
		String act = request.getParameter("act");

		String jgbh = myForm.getJgbh();
		String jsdm = myForm.getJsdm();
		String sfzld = myForm.getBzdm();
		String nj = myForm.getNj();
		String bzdm = "";

		if ("save".equalsIgnoreCase(act)) {
			boolean bJg = service.saveJxjz(myForm, "add", request);
			boolean jg = true;
			boolean js = true;
			if (jgbh != null && !"".equalsIgnoreCase(jgbh)) {
				jg = service.addJgxx(jgbh, sfzld, request);
			}
			if (jsdm != null && !"".equalsIgnoreCase(jsdm)) {
				js = service.addJsxx(jsdm, sfzld, request);
			}
			if (bJg && jg && js) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		rs = service.getJxjzxx(myForm.getBzdm(), myForm.getNj());

		if (rs.size() == 0) {
			bzdm = service.getJxbzdm();
			rs.put("nj", myForm.getNj());
			rs.put("bzdj", map.get("jzdm"));
			rs.put("bzdjmc", map.get("jzmc"));
			rs.put("bzdm", bzdm);
		}
		setList(request);
		// 获得带队老师信息
		List<HashMap<String, String>> jsList = service.getJsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			List<HashMap<String, String>> fzList = service.getFzList();
			request.setAttribute("fzList", fzList);
		}
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("jsList", jsList);
		request.setAttribute("jgList", jgList);
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		return mapping.findForward("addMaxjz");
	}

	/**
	 * @describe 其他编制维护
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward addQtjz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "军训管理-军训编制－增加其它建制";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();
		JxglForm myForm = (JxglForm) form;
		JxglService service = new JxglService();

		String xxdm = StandardOperation.getXxdm();
		String act = request.getParameter("act");
		String sjdm = Base.chgNull(request.getParameter("sjdm"), "", 1);
		String ssjz = Base.chgNull(request.getParameter("ssjz"), "", 1);
		String sjdj = Base.chgNull(request.getParameter("sjdj"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xb = service.getSjxb(sjdm);

		myForm.setSjdm(sjdm);
		myForm.setNj(nj);

		String jgbh = myForm.getJgbh();
		String sfzld = myForm.getBzdm();
		String zdy = myForm.getJsdm();

		if ("save".equalsIgnoreCase(act)) {
			boolean bJg = service.saveJxjz(myForm, "add", request);
			boolean jg = true;
			boolean js = true;
			if (jgbh != null && !"".equalsIgnoreCase(jgbh)) {
				jg = service.addJgxx(jgbh, sfzld, request);
			}
			if (zdy != null && !"".equalsIgnoreCase(zdy)) {
				js = service.addJsxx(zdy, sfzld, request);
			}
			if (bJg && jg && js) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		rs = service.getJxjzxx(myForm.getBzdm(), myForm.getNj());

		if (rs.size() == 0) {

			HashMap<String, String> map = new HashMap<String, String>();
			map = service.getBzdj(sjdj);
			zdy = service.getJsOne(sjdm);
			String jgmc = service.getJgOne(sjdm);
			String xn = service.getSjXn(sjdm);
			String bzdm = "";
			bzdm = service.getJxbzdm();

			rs.put("nj", myForm.getNj());
			rs.put("xn", xn);
			rs.put("jsdm", zdy);
			rs.put("jgbh", jgmc);
			rs.put("ssjz", ssjz);
			rs.put("sjdm", sjdm);
			rs.put("bzdm", bzdm);
			rs.put("bzdj", map.get("jzdm"));
			rs.put("bzdjmc", map.get("jzmc"));
			rs.put("xb", xb);
		}

		List<String> jz = service.getAllJz();
		String allJz = "";
		if (jz != null && jz.size() > 0) {
			for (int i = 0; i < jz.size(); i++) {
				allJz += jz.get(i) + "-";
			}
		}

		if ("-".equals(xb)) {
			request.setAttribute("noxb", "yes");
		}
		setList(request);
		// 获得带队老师信息
		List<HashMap<String, String>> jsList = service.getJsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("jsList", jsList);
		request.setAttribute("jgList", jgList);
		request.setAttribute("rs", rs);
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		request.setAttribute("allJz", allJz);
		return mapping.findForward("addQtjz");
	}

	/**
	 * @describe 编制修改
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward modJxjz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "军训管理-军训编制－修改建制信息";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		HashMap<String, String> rs = new HashMap<String, String>();

		JxglForm myForm = (JxglForm) form;
		JxglService service = new JxglService();

		String act = request.getParameter("act");
		String bzdm = Base.chgNull(request.getParameter("bzdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xxdm = StandardOperation.getXxdm();
		String xn = service.getJxxn(bzdm);
		
		myForm.setNj(nj);
		myForm.setBzdm(bzdm);
		myForm.setXn(xn);

		String jgbh = myForm.getJgbh();
		String sfzld = myForm.getBzdm();
		String zdy = myForm.getJsdm();

		if ("save".equalsIgnoreCase(act)) {
			String ldxb = service.getSjxb(myForm.getBzdm());
			String sjxb = service.getSjxb(myForm.getSjdm());
			
			String ldxn =  service.getJxxn(bzdm);
			String sjxn =  service.getJxxn(myForm.getSjdm());
			if ("".equalsIgnoreCase(sjxb) || "-".equalsIgnoreCase(sjxb)
					|| ldxb.equalsIgnoreCase(sjxb)) {
				if (!ldxn.equalsIgnoreCase(sjxn)) {
					String msg = "上级建制连队学年与本连队不同，无法修改，请确认！！";
					request.setAttribute("msg", msg);
				} else {
					boolean bJg = service.saveJxjz(myForm, "mod", request);
					boolean jg = true;
					boolean js = true;
					if (jgbh != null && !"".equalsIgnoreCase(jgbh)) {
						jg = service.addJgxx(jgbh, sfzld, request);
					}
					if (zdy != null && !"".equalsIgnoreCase(zdy)) {
						js = service.addJsxx(zdy, sfzld, request);
					}
					if (bJg && jg && js) {
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				}
			} else {
				String msg = "上级建制连队性别与本连队不同，无法修改，请确认！！";
				request.setAttribute("msg", msg);
			}
		}

		rs = service.getJxjzxx(myForm.getBzdm(), myForm.getNj());
		String sjdm = rs.get("sjdm");
		if (sjdm == null || "".equals(sjdm)) {
			request.setAttribute("nosjdm", "yes");
		}
		setList(request);
		request.setAttribute("sjList", service.getSjlJz(sjdm, nj));
		// 获得带队老师信息
		List<HashMap<String, String>> jsList = service.getJsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);
		request.setAttribute("jsList", jsList);
		request.setAttribute("jgList", jgList);
		request.setAttribute("rs", rs);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("userType", userType);
		request.setAttribute("title", title);
		return mapping.findForward("modJxjz");
	}

	/**
	 * @describe 最小编制维护
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward addBjjz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglForm myForm = (JxglForm) form;
		JxglService service = new JxglService();

		String nj = request.getParameter("nj");

		String xxdm = StandardOperation.getXxdm();
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String sjdm = Base.chgNull(request.getParameter("sjdm"), "", 1);
		String ssjz = Base.chgNull(request.getParameter("ssjz"), "", 1);
		String xb = service.getSjxb(sjdm);
		String act = request.getParameter("act");
		String jgbh = myForm.getJgbh();
		String jsdm = myForm.getJsdm();
		String xn = service.getSjXn(sjdm);
		
		xydm = Base.chgNull(xydm, "%", 1);
		nj = Base.chgNull(nj, "", 1);
		zydm = Base.chgNull(zydm, "%", 1);
		xb = "-".equals(xb) ? "%" : xb;

		/** 保存 */
		if (!Base.isNull(act) && act.equalsIgnoreCase("save")) {

			boolean ok = false;
			String[] bjdm = myForm.getBjdmList();
			if (bjdm == null || Base.isNull(sjdm)) {
				ok = service.delAllBz(nj, sjdm);
				if (ok) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			} else {

				boolean bzflg = service.addJxbz(bjdm, nj, sjdm, xb, jsdm, jgbh,xn);

				if (bzflg) {
					request.setAttribute("ok", "ok");
				} else {
					request.setAttribute("ok", "no");
				}
			}
		}
		// 未分配班级列表
		List bjList = service.getNoFpList(nj, xydm, zydm, xb,xn);
		// 已分配班级列表
		List ldBjList = service.getHadFpList(sjdm, nj);
		HashMap<String, String> LdInfo = new HashMap<String, String>();
		// 获得带队老师信息
		List<HashMap<String, String>> jsList = service.getJsList(nj);
		// 获得带队教官信息
		List<HashMap<String, String>> jgList = service.getJgList(nj);

		String xy = myForm.getXydm();
		xy = (xy == null) ? "" : xy;

		setList(request);
		
		HashMap<String, String> rs = new HashMap<String, String>();
		rs.put("jsdm", service.getJsOne(sjdm));
		rs.put("jgbh", service.getJgOne(sjdm));
		rs.put("xn", xn);

		request.setAttribute("jsList", jsList);
		request.setAttribute("jgList", jgList);
		request.setAttribute("rs", rs);
		request.setAttribute("xb", xb);
		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("LdInfo", LdInfo);
		request.setAttribute("nj", nj);
		request.setAttribute("sjdm", sjdm);
		request.setAttribute("ssjz", ssjz);
		request.setAttribute("bjList", bjList);
		request.setAttribute("ldBjList", ldBjList);
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		if (xydm.equalsIgnoreCase("%")) {
			xydm = "";
		}
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// 发送专业列表
		request.setAttribute("xsxyList", service.getXsXyList(nj));
		request.setAttribute("xszyList", service.getXsZyList(nj));
		return mapping.findForward("addBjjz");
	}

	/**
	 * @describe 军训学生名单
	 * @author luo
	 * @throws Exception
	 */
	public static ActionForward ArmyStuInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		JxglService service = new JxglService();
		JxglForm myForm = (JxglForm) form;

		String xxdm = StandardOperation.getXxdm();
		String isFdy = request.getSession().getAttribute("isFdy").toString();// 辅导员
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String tableName = "view_jxmdxx";
		String realTable = "jxgl_jxmdb";
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		String userType = dao.getUserType(userDep);
		String xm = DealString.toGBK(myForm.getXm());

		// 取得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;

		myForm.setXm(xm);

		setList(request);

		String go = request.getParameter("go");
		List<HashMap<String, String>> topTr = null;
		ArrayList<String[]> rs = new ArrayList<String[]> ();
		String rsNum = "";
		String[] colList = new String[] {};

		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			colList = new String[] { "主键", "行号", "xh", "xm", "xb", "nj",
					"xymc", "bjmc", "nd", "ldbh", "xs" };
		} else {
			colList = new String[] { "主键", "行号", "xh", "xm", "xb", "nj",
					"xymc", "bjmc", "ldbh" };
		}

		if ("go".equalsIgnoreCase(go)) {
			topTr = service.getTopTr(tableName, colList);
			rs = service.getArmyStuList(myForm, colList, isFdy, userName);
			if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
				HashMap<String, String> map = new HashMap<String, String>();
				topTr.remove(topTr.size() - 1);
				map.put("en", "xs");
				map.put("cn", "是否新生");
				topTr.add(map);
			}
			myForm.setXh(DealString.toGBK(myForm.getXh()));

			rsNum = rs == null ? "0" : String.valueOf(rs.size());
			request.setAttribute("rs", service.getResultList(rs, myForm));
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		request.setAttribute("writeAble", writeAble);
		request.setAttribute("userType", userType);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			if ("true".equalsIgnoreCase(isFdy)) {
				request.setAttribute("fdy", "yes");
				request.setAttribute("bjList", service.getFdyBjList(userName));
				request.setAttribute("xyList", service.getFdyXyList(userName));
				request.setAttribute("zyList", service.getFdyZyList(userName));
			}
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("isFdy", isFdy);
		request.setAttribute("userName", userName);
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("ldList", service.getLdList(""));

		return mapping.findForward("ArmyStuInfo");

	}

	/**
	 * @describe 军训学生名单维护
	 * @author luo
	 * @throws Exception
	 */
	public static ActionForward ArmyStuInfoOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JxglService service = new JxglService();
		JxglForm myForm = (JxglForm) form;

		String pk = "xh||nd";
		String tableName = "jxgl_jxmdb";
		String realTable = "view_jxmdxx";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("type");

		HashMap<String, String> map = new HashMap<String, String>();

		String[] colList = { "xh", "xb", "xm", "nd", "nj", "xymc", "zymc",
				"bjmc", "ldbh", "sfbx", "jxbx", "jxbz", "jxcf" };

		if ("modi".equals(doType)) {
			if ("0".equals(pkValue.substring(pkValue.length() - 1, pkValue
					.length()))) {
				pkValue = pkValue.substring(0, pkValue.length() - 1);
				map = service.getArmyStuDetail(doType, pk, pkValue, colList,
						xh, realTable);
				request.setAttribute("isXs", "no");
			} else if ("1".equals(pkValue.substring(pkValue.length() - 1,
					pkValue.length()))) {
				pkValue = pkValue.substring(0, pkValue.length() - 1);
				realTable = "view_jxgl_xsjxmd";
				pk = "ksh||nd";
				colList = new String[] { "ksh", "xb", "xm", "nd", "nj", "xymc",
						"zymc", "bjmc", "ldbh", "sfbx", "jxbx", "jxbz", "jxcf" };
				map = service.getArmyStuDetail(doType, pk, pkValue, colList,
						xh, realTable);
				request.setAttribute("isXs", "yes");
			}
			map.put("lddm", map.get("ldbh"));
		}

		if ("add".equals(doType)) {
			map.put("stuExists", "yes");
			request.setAttribute("type", "add");
		}
		request.setAttribute("rs", map);

		if ("save".equals(doType)) {
			String nd = myForm.getNj();
			String ldbh = myForm.getLddm();
			String sfbx = DealString.toGBK(request.getParameter("sfbx"));
			String jxbx = DealString.toGBK(request.getParameter("jxbx"));
			String jxbz = DealString.toGBK(request.getParameter("jxbz"));
			String jxcf = DealString.toGBK(request.getParameter("jxcf"));
			boolean del = true;
			boolean insert = false;
			if ("".equalsIgnoreCase(pkValue)) {
				del = StandardOperation.delete(tableName, "xh||nd", xh + nd,
						request);
			} else {
				del = StandardOperation.delete(tableName, pk, pkValue, request);
			}
			if (del) {
				insert = StandardOperation.insert(tableName, new String[] {
						"xh", "nd", "ldbh", "sfbx", "jxbx", "jxbz", "jxcf" },
						new String[] { xh, nd, ldbh, sfbx, jxbx, jxbz, jxcf },
						request);
			}
			if (insert) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}
		boolean del = false;
		if ("del".equalsIgnoreCase(doType)) {
			pk = "xh||nd||isxs";
			del = StandardOperation.delete(tableName, pk, pkValue, request);
			if (del) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/jxglgt.do?method=ArmyStuInfo&go=go",
					false);
		}

		setList(request);
		request.setAttribute("ldList", service.getNextMinJz(map.get("nd")));
		request.setAttribute("pkValue", pkValue == null ? "" : pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("ArmyStuInfoOne");

	}

	/**
	 * @describe 军训成绩录入
	 * @author luo
	 * @throws Exception
	 */
	public static ActionForward ArmyIntoAchievement(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		JxglService service = new JxglService();
		JxglForm jxglForm = (JxglForm) form;

		String go = request.getParameter("go");
		String tableName = "view_jxcjxx";
		String realTable = "jxgl_cjb";

		String isFdy = request.getSession().getAttribute("isFdy").toString();// 辅导员
		
		List rs = null;
		String xxdm = StandardOperation.getXxdm();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		setList(request);

		if (xxdm.equalsIgnoreCase(Globals.XXDM_JSXX)) {
			if ("true".equalsIgnoreCase(isFdy)) {
				request.setAttribute("fdy", "yes");
				request.setAttribute("bjList", service.getFdyBjList(userName));
				request.setAttribute("xyList", service.getFdyXyList(userName));
				request.setAttribute("zyList", service.getFdyZyList(userName));
			}
		}

		String[] colList = new String[] { "xhArray", "xmArray", "ndArray",
				"xb", "bjmc", "xs", "cjArray","nj" };
		if(xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
			colList = new String[] { "xhArray", "xmArray", "ndArray",
					"sfzh","xb","zymc", "bjmc", "xs", "cjArray","nj" };
			request.setAttribute("showNnzy", "showNnzy");
		}
		int rsNum = 0;
		if (go != null && go.equalsIgnoreCase("go")) {
			rs = service.getCjlrList(jxglForm, colList, isFdy, userName);
			rsNum = (rs != null ? rs.size() : 0);
			jxglForm.setXm(DealString.toGBK(jxglForm.getXm()));
			jxglForm.setXh(DealString.toGBK(jxglForm.getXh()));
		}

		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}
		request.setAttribute("userName", userName);
		request.setAttribute("isFdy", isFdy);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("rs", rs);
		request.setAttribute("ldList", service.getXsLdList());
		request.setAttribute("realTable", realTable); // 表
		request.setAttribute("tableName", tableName); // 视图
		request.setAttribute("rsNum", rsNum);
		request.setAttribute("xxdm", xxdm);
		return mapping.findForward("ArmyIntoAchievement");
	}

	/**
	 * @describe 军训成绩保存
	 * @author luo
	 * @throws Exception
	 */
	public static ActionForward SaveArmyAchievement(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		JxglService service = new JxglService();
		JxglForm myform = (JxglForm) form;
		String[] xhArray = myform.getXhArray();
		String[] ndArray = myform.getNdArray();
		String[] cjArray = myform.getCjArray();
		String lrr = request.getSession().getAttribute("userName").toString();
		boolean flg = service.saveJxcj(xhArray, ndArray, cjArray, lrr, request);
		if (flg) {
			request.setAttribute("result", "ok");
		}
		return new ActionForward("/jxglgt.do?method=ArmyIntoAchievement&go=go",
				false);
	}

	/**
	 * @describe 军训参训干部
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward jxcxgb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xxdm = StandardOperation.getXxdm();

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		String tableName = "view_nblg_jxgbxx";
		String realTable = "nblg_jxgl_jxcxgb";
		String title = "军训管理 - 信息维护 - 参训干部信息维护";

		JxglForm myForm = (JxglForm) form;
		JxglService service = new JxglService();

		Vector<Object> vector = new Vector<Object>();
		List<HashMap<String, String>> topTr = null;

		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			String[] colList = new String[] {};
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				colList = new String[] { "主键", "行号", "zgh", "xm", "xb", "dwmc",
						"zwmc", "jxnd", "lxmc" };
			} else {
				colList = new String[] { "主键", "行号", "zgh", "xm", "xb", "dwmc",
						"zwmc", "jxnd" };
			}
			topTr = service.getTopTr(tableName, colList);
			// 取得查询结果
			vector = service.getGbxxList(myForm, colList);
			// 页面返回保证姓名不乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			myForm.setZgh(DealString.toGBK(myForm.getZgh()));
		}
		request.setAttribute("rs", vector);
		if (vector != null && !"".equals(vector)) {
			request.setAttribute("rsNum", vector.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		setList(request);
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			if (!"cx".equalsIgnoreCase(myForm.getGblx())) {
				request.setAttribute("dwList", service.getdwList());
				request.setAttribute("zwList", service.getzwList(myForm
						.getLddm()));
			} else {
				request.setAttribute("dwList", service.getLdList(myForm
						.getJxnd()));
				request.setAttribute("zwList", service.getBzzwList(myForm
						.getLddm()));
			}
		} else {
			request.setAttribute("dwList", service.getLdList(myForm.getJxnd()));
			request.setAttribute("zwList", service
					.getBzzwList(myForm.getLddm()));
		}

		request.setAttribute("xxdm", xxdm);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("jxcxgb");
	}

	/**
	 * @describe 单个干部信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward jxcxgbOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");
		String xxdm = StandardOperation.getXxdm();
		String gblx = request.getParameter("lx");

		JxglService service = new JxglService();
		JxglForm myForm = (JxglForm) form;

		HashMap<String, String> rs = new HashMap<String, String>();

		if ("add".equalsIgnoreCase(doType)) {
			rs.put("bz", "");
			request.setAttribute("zwList", service.getzwList(myForm.getLddm()));
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
				rs = service.getGbxx(pkValue, gblx);
				if ("cx".equals(rs.get("dwlx"))) {
					rs.put("lddm", rs.get("jxdw"));
					rs.put("cxgb", rs.get("jxzw"));
					request.setAttribute("dw", "cx");
					request.setAttribute("zwList", service.getBzzwList(rs
							.get("jxdw")));
				} else if ("jg".equals(rs.get("dwlx"))) {
					request.setAttribute("dw", "jg");
					request.setAttribute("zwList", service.getzwList(rs
							.get("jxdw")));
				}
			} else {
				gblx = "cx";
				rs = service.getGbxx(pkValue, gblx);
				rs.put("lddm", rs.get("jxdw"));
				rs.put("cxgb", rs.get("jxzw"));
				request.setAttribute("dw", "cx");
				request.setAttribute("zwList", service.getBzzwList(rs
						.get("jxdw")));
			}
		}
		if ("del".equalsIgnoreCase(doType)) {
			boolean inserted = service.delGbxx(pkValue, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/jxglgt.do?method=jxcxgb", false);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean inserted = service.saveGbxx(myForm, request);
			if (inserted) {
				request.setAttribute("result", "yes");
				if ("cx".equals(myForm.getDwlx())) {
					request.setAttribute("dw", "cx");
					request.setAttribute("zwList", service.getBzzwList(myForm
							.getJxdw()));
				} else if ("jg".equals(myForm.getDwlx())) {
					request.setAttribute("dw", "jg");
					request.setAttribute("zwList", service.getzwList(myForm
							.getJxdw()));
				}
			} else {
				request.setAttribute("result", "no");
			}
		}
		setList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("xxdm", xxdm);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("dwList", service.getdwList());
		request.setAttribute("ldList", service.getLdList(""));
		request.setAttribute("doType", doType);

		return mapping.findForward("jxcxgbOne");
	}

	/**
	 * @param request
	 * @作用 设置基础列表
	 */
	private static void setList(HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		JxglDAO jxglDao = new JxglDAO();
		JxglService service = new JxglService();

		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		List xnList = dao.getXnndList();
		xnList.remove(0);

		String userType = dao.getUserType(userDep);
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		}
		request.setAttribute("bzList", jxglDao.getLdList2(Base.currNd));
		request.setAttribute("xnList", xnList);
		request.setAttribute("ldList", service.getLdList(Base.currNd));
		request.setAttribute("njList", service.getNjList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xbList", dao.getSexList());
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));
	}

	/**
	 * 教师职工号查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward jxghSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("doType", DealString.toGBK(request
				.getParameter("doType")));
		request.setAttribute("pkValue", DealString.toGBK(request
				.getParameter("pkValue")));
		request
				.setAttribute("gb", DealString
						.toGBK(request.getParameter("gb")));
		request.setAttribute("xyList", Base.getXyList());
		setList(request);
		return mapping.findForward("jxghSearch");
	}

	/**
	 * 查询教师职工号信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward infoSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		JxglService service = new JxglService();
		JxglForm model = (JxglForm) form;
		List list = service.searchJsghInfo(model);
		String doType = DealString.toGBK(request.getParameter("doType"));
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));

		request
				.setAttribute("gb", DealString
						.toGBK(request.getParameter("gb")));
		request.setAttribute("topTr", service.getJsghxxTopTr());// 表头
		request.setAttribute("rsNum", list.size());
		request.setAttribute("rs", list);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxghSearch");
	}

	/**
	 * 页面跳转
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws SQLException
	 * @throws Exception
	 */
	public ActionForward forwardPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws SQLException {
		JxglService service = new JxglService();
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		String jsgh = DealString.toGBK(request.getParameter("jsid"));
		String xb = service.getJsxx(jsgh).get("xb");
		String gb = DealString.toGBK(request.getParameter("gb"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		if ("xh".equals(gb)) {
			HashMap<String, String> rs = service.getXsxx(xh);
			request.setAttribute("doType", DealString.toGBK(request
					.getParameter("doType")));
			request.setAttribute("pkValue", DealString.toGBK(request
					.getParameter("pkValue")));
			rs.put("isXh", "yes");
			setList(request);
			request.setAttribute("rs", rs);
			request.setAttribute("zwList", service.getzwList(rs.get("jxdw")));
			request.setAttribute("dwList", service.getdwList());
			return mapping.findForward("jxcxgbOne");
		} else if ("yes".equals(gb)) {
			HashMap<String, String> rs = service.getGbxx(jsgh);
			request.setAttribute("doType", DealString.toGBK(request
					.getParameter("doType")));
			request.setAttribute("pkValue", DealString.toGBK(request
					.getParameter("pkValue")));
			request.setAttribute("rs", rs);
			setList(request);
			request.setAttribute("zwList", service.getzwList(rs.get("jxdw")));
			request.setAttribute("dwList", service.getdwList());
			return mapping.findForward("jxcxgbOne");

		} else {

			HashMap<String, String> rs = service.getJsxx(jsgh);
			if ("男".equals(xb)) {
				rs.put("xb", "1");
			} else if ("女".equals(xb)) {
				rs.put("xb", "2");
			} else {
				rs.put("xb", "");
			}
			request.setAttribute("doType", DealString.toGBK(request
					.getParameter("doType")));
			request.setAttribute("pkValue", DealString.toGBK(request
					.getParameter("pkValue")));
			request.setAttribute("rs", rs);
			setList(request);
			return mapping.findForward("jsxxOne");

		}
	}

	/**
	 * 军训干部通讯录打印
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward jxgbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String nd = request.getParameter("nd");
		JxglService service = new JxglService();
		String modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/nblg_jxgb.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.printJxgb(wwb, nd);

		return mapping.findForward("");
	}

	/**
	 * 军训编制分配表打印(宁波理工学院)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward jxbzPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String nd = request.getParameter("nd");
		JxglService service = new JxglService();
		String modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/nblg_jxbz.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.printJxbz(wwb, nd);

		return mapping.findForward("");
	}

	/**
	 * 军训编制分配表打印(金华职业)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward jxbzJhPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xn = request.getParameter("xn");
		JxglService service = new JxglService();
		String modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/jxgl_jxbz.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.printJhJxbz(wwb, xn);

		return mapping.findForward("");
	}
	
	/**
	 * 军训编制分配表打印
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward jxbzGtPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xn = request.getParameter("xn");
		JxglService service = new JxglService();
		String modelPath = servlet.getServletContext().getRealPath("")
				+ "/print/jxgl_jxbz.xls";
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
				modelPath), response.getOutputStream());
		service.printGtJxbz(wwb, xn);

		return mapping.findForward("");
	}

	/**
	 * 军训团队获奖管理
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */

	public ActionForward jxtdhj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		String tableName = "view_jxgl_jxtdhj";
		String realTable = "jxtdhjb";
		String title = "军训管理 - 信息维护 - 团队获奖信息";

		JxglForm myForm = (JxglForm) form;
		JxglService service = new JxglService();

		Vector<Object> vector = new Vector<Object>();
		List<HashMap<String, String>> topTr = null;

		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			String[] colList = new String[] { "主键", "xn", "nd", "xqmc", "ldmc",
					"zdy", "jxmc", "hjsj" };
			topTr = service.getTopTr(tableName, colList);
			// 取得查询结果
			vector = service.getTdhjList(myForm, colList);
			// 页面返回保证姓名不乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}
		
		
		// TODO 分页
		int count =service.getTdhjListCount(myForm,"count");
		myForm.getPages().setMaxRecord(count);
		
		
		request.setAttribute("rs", vector);
		if (vector != null && !"".equals(vector)) {
			request.setAttribute("rsNum", vector.size());
		}
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		setList(request);

		request.setAttribute("ldList", service.getTdList(""));
		request.setAttribute("jxList", service.getJxtdjxList());// 奖项列表
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("userType", userType);

		return mapping.findForward("jxtdhj");
	}

	/**
	 * @describe 单个团队获奖
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward jxtdhjOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String doType = request.getParameter("type");
		String pkValue = request.getParameter("pk") == null ? "" : request
				.getParameter("pk");
		String title = "军训管理 - 信息维护 - 团队获奖信息维护";

		JxglService service = new JxglService();
		JxglForm myForm = (JxglForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();

		setList(request);

		List<HashMap<String, String>> tdList = service.getTdList("");

		if ("add".equalsIgnoreCase(doType)) {
			rs.put("bz", " ");
		}
		if ("edit".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType)) {
			rs = service.getTdhjxx(pkValue);
			tdList = service.getTdList(rs.get("nd"));
		}
		if ("del".equalsIgnoreCase(doType)) {
			boolean inserted = service.delTdxx(pkValue, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
			return new ActionForward("/jxglgt.do?method=jxtdhj&go=go", false);
		}
		if ("save".equalsIgnoreCase(doType)) {
			boolean inserted = service.saveTdhjxx(myForm, request);
			if (inserted) {
				request.setAttribute("result", "yes");
			} else {
				request.setAttribute("result", "no");
			}
		}

		request.setAttribute("ldList", tdList);
		request.setAttribute("jxList", service.getJxtdjxList());// 奖项列表
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("doType", doType);

		return mapping.findForward("jxtdhjOne");
	}
}
