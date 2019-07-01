package xgxt.dtjs.action;

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
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.dtjs.form.DtjsForm;
import xgxt.dtjs.model.DtjsModel;
import xgxt.dtjs.server.DtjsService;
import xgxt.dtjs.utils.GetWriteAbleAndTitle;

public class DtjsAction extends DispatchAction {

	/**
	 * <p>
	 * Title: 学工管理系统
	 * </p>
	 * <p>
	 * Description: 学生信息管理党团建设-action类
	 * </p>
	 * <p>
	 * Copyright: Copyright (c) 2008
	 * </p>
	 * <p>
	 * Company: zfsoft
	 * </p>
	 * 
	 * @author 鲁宁
	 * @version 1.0
	 */

	public ActionForward rtjjfz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 白云工商 入团积极份子
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName = "view_rtjjfz";
		String realTable = "rtjjfzb";
		String title = "党团建设-信息维护-入团积极份子";

		ArrayList<String[]> rs = null;
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();

		String nj = myForm.getNj();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		String bjKey = xy + "!!" + zy + "!!" + nj;

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = service.getRtjjfzList(myModel);
			// =============== begin 骆 2009/3/12 ============
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			// =============== end 骆 2009/3/12 ============
		}

		List topTr = service.getRtjjfzTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xqList", Base.getXqList());

		commonRequestSet(request, tableName, realTable, rs, topTr, title);
		return mapping.findForward("rtjjfz");
	}

	public ActionForward rtjjfzOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 入团积极份子单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		DtjsService service = new DtjsService();
		HashMap<String, String> rs = service.getRtjjfzOne(pk, xh);
		request.setAttribute("rs", rs);
		return mapping.findForward("rtjjfzOne");
	}

	public ActionForward saveRtjjfzOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 军训新生体检维护单个查看保存
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();
		// =============== begin 骆 2009/3/12 ============
		String pk = DealString.toGBK(myForm.getXh());
		boolean isTy = service.isTy(pk);
		if (isTy) {

			BeanUtils.copyProperties(myModel, myForm);
			boolean inserted = service.updataRtjjfz(myModel, pk, request);
			if (inserted) {
				request.setAttribute("inserted", "ok");
			} else {
				request.setAttribute("inserted", "no");
			}
		} else {
			request.setAttribute("isTy", "no");
		}
		// =============== end 骆 2009/3/12 ============
		return mapping.findForward("rtjjfzOne");
	}

	public ActionForward delRtjjfzOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 入团积极分子单个删除
		DtjsService service = new DtjsService();
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean inserted = service.deleteRtjjfzOne(pk, request);
		if (inserted) {
			request.setAttribute("delete", "ok");
		} else {
			request.setAttribute("delete", "no");
		}
		return rtjjfz(mapping, form, request, response);
	}

	private void commonRequestSet(HttpServletRequest request, String tableName,
			String realTable, ArrayList<String[]> rs, List topTr, String title) {
		// Request存值的通用方法
		String writeAble = request.getParameter("writeAble");
		if (writeAble == null) {
			writeAble = Base.getWriteAble(request);
		}

		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}

	private void commonRequestSet2(HttpServletRequest request,
			String tableName, String realTable, ArrayList<String[]> rs,
			List topTr) {
		// Request存值的通用方法2 区别是title从数据库里取
		String writeAble = request.getParameter("writeAble");
		String title = DealString.toGBK(request.getParameter("title"));
		if (Base.isNull(writeAble)) {
			String[] message = GetWriteAbleAndTitle
					.getWriteAbleAndTitle(request);
			writeAble = message[0];
			title = message[1];
		}
		if (rs != null) {
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}

	// 武汉理工 学院素质教育论坛 学院主题教育活动
	public ActionForward szjylt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 武汉理工 学院素质教育论坛
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName = "view_sxjyszktb";
		String realTable = "sxjyszktb";
		String title = "思想教育-思想教育活动-学院素质教育论坛";

		ArrayList<String[]> rs = null;
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();
		String xy = myForm.getXydm();

		xy = (xy == null) ? "" : xy;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = service.getSzktList(myModel);
		}

		List topTr = service.getSzktTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);
		return mapping.findForward("szjylt");
	}

	public ActionForward szjyltOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 武汉理工 学院素质教育单个查看
		String pk = DealString.toGBK(request.getParameter("pk"));
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		DtjsService service = new DtjsService();
		HashMap<String, String> rs = service.getSzjyltOne(pk);

		DtjsForm myForm = (DtjsForm) form;
		String xy = myForm.getXydm();
		if (rs.get("xn") == null || rs.get("xn").equalsIgnoreCase("")) {
			setFormForXnNdXqNj(myForm);
		} else {
			myForm.setXn(rs.get("xn"));
			myForm.setNd(rs.get("nd"));
		}
		xy = (xy == null) ? "" : xy;
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("szjyltOne");
	}

	public ActionForward saveSzjyltOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 武汉理工 学院素质教育单个查看
		// 军训新生体检维护单个查看保存
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();
		String pk = DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel, myForm);
		boolean inserted = service.updataSzjylt(myModel, pk, request);
		if (inserted) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("szjyltOne");
	}

	public ActionForward delSzjyltOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 军训管理学生体检单个删除
		DtjsService service = new DtjsService();
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean inserted = service.deleteSzjyltOne(pk, request);
		if (inserted) {
			request.setAttribute("delete", "ok");
		} else {
			request.setAttribute("delete", "no");
		}
		return szjylt(mapping, form, request, response);
	}

	// 武汉理工 学院素质教育论坛 学院主题教育活动
	public ActionForward ztjyhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 武汉理工 学院主题教育活动
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName = "view_sxjyszktb";
		String realTable = "sxjyztjyhdb";
		String title = "思想教育-思想教育活动-学院主题教育活动";

		ArrayList<String[]> rs = null;
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();
		String xy = myForm.getXydm();

		xy = (xy == null) ? "" : xy;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = service.getZtjyhzList(myModel);
		}

		List topTr = service.getZtjyhzTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);
		return mapping.findForward("ztjyhd");
	}

	public ActionForward ztjyhdOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 武汉理工 学院主题教育活动
		String pk = DealString.toGBK(request.getParameter("pk"));
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		DtjsService service = new DtjsService();
		HashMap<String, String> rs = service.getZtjyhzOne(pk);

		DtjsForm myForm = (DtjsForm) form;
		String xy = myForm.getXydm();
		if (rs.get("xn") == null || rs.get("xn").equalsIgnoreCase("")) {
			setFormForXnNdXqNj(myForm);
		} else {
			myForm.setXn(rs.get("xn"));
			myForm.setNd(rs.get("nd"));
		}
		xy = (xy == null) ? "" : xy;
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("ztjyhdOne");
	}

	public ActionForward saveZtjyhzOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 武汉理工 学院素质教育单个查看
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();
		String pk = DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel, myForm);
		boolean inserted = service.updataZtjyhz(myModel, pk, request);
		if (inserted) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("ztjyhdOne");
	}

	public ActionForward delZtjyhdOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 学院素质教育单个删除
		DtjsService service = new DtjsService();
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean inserted = service.deleteZtjyhzOne(pk, request);
		if (inserted) {
			request.setAttribute("delete", "ok");
		} else {
			request.setAttribute("delete", "no");
		}
		return ztjyhz(mapping, form, request, response);
	}

	// 武汉理工 学生马克思主义理论学习研究会
	public ActionForward mlzyyjhfhxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 武汉理工 马克思主义理论学习研究会分会基本信息
		String tableName = "mlzyyjhfhxxb";
		String realTable = "mlzyyjhfhxxb";
		String title = "思想教育-思想教育活动-马克思主义研究会分会基本信息";

		ArrayList<String[]> rs = null;
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = service.getFhxxList(myModel);
		}
		myForm.setFhmc(DealString.toGBK(myForm.getFhmc()));
		List topTr = service.getFhxxListTopTr();
		commonRequestSet(request, tableName, realTable, rs, topTr, title);
		return mapping.findForward("mlzyyjhfhxx");
	}

	public ActionForward mlzyyjhfhxxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 武汉理工 马克思主义理论学习研究会分会基本信息
		String pk = DealString.toGBK(request.getParameter("pk"));
		DtjsService service = new DtjsService();
		HashMap<String, String> rs = service.getFhxxOne(pk);
		request.setAttribute("rs", rs);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("mlzyyjhfhxxOne");
	}

	public ActionForward saveMlzyyjhfhxxbOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 武汉理工 学院素质教育单个查看
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();
		String pk = DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel, myForm);
		boolean inserted = service.updataMlzyyjhfhxxb(myModel, pk, request);
		if (inserted) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("mlzyyjhfhxxOne");
	}

	public ActionForward delMlzyyjhfhxxbOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 军训管理学生体检单个删除
		DtjsService service = new DtjsService();
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean inserted = service.deleteFhxxOne(pk, request);
		if (inserted) {
			request.setAttribute("delete", "ok");
		} else {
			request.setAttribute("delete", "no");
		}
		return mlzyyjhfhxx(mapping, form, request, response);
	}

	public ActionForward tkqkb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 上海工程 思想教育听课情况基本信息
		String tableName = "view_tkqkjl";
		String realTable = "sxjy_tkqkjlb";
		ArrayList<String[]> rs = null;
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String nj = myForm.getNj();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		String bjKey = xy + "!!" + zy + "!!" + nj;

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs = service.getTkqkList(myModel);
		}
		List topTr = service.getTkqkListTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet2(request, tableName, realTable, rs, topTr);
		return mapping.findForward("tkqkjl");
	}

	public ActionForward tkqkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 上海工程 思想教育听课情况基本信息
		String pk = DealString.toGBK(request.getParameter("pk"));
		DtjsService service = new DtjsService();
		HashMap<String, String> rs = service.getTkqkOne(pk);
		DtjsForm myForm = (DtjsForm) form;

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String nj = myForm.getNj();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		String bjKey = xy + "!!" + zy + "!!" + nj;
		if (pk.equalsIgnoreCase("")) {
			rs.put("xn", Base.currXn);
			rs.put("nd", Base.currNd);
			rs.put("xq", Base.currXq);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("tkqkjlOne");
	}

	public ActionForward saveTkqkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 武汉理工 学院素质教育单个查看
		DtjsForm myForm = (DtjsForm) form;
		DtjsService service = new DtjsService();
		DtjsModel myModel = new DtjsModel();
		String pk = DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel, myForm);
		boolean inserted = service.updataTkqk(myModel, pk, request);
		if (inserted) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("tkqkjlOne");
	}

	public ActionForward delTkqkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 军训管理学生体检单个删除
		DtjsService service = new DtjsService();
		String pk = DealString.toGBK(request.getParameter("pk"));
		boolean inserted = service.deleteTkqkOne(pk, request);
		if (inserted) {
			request.setAttribute("delete", "ok");
		} else {
			request.setAttribute("delete", "no");
		}
		return tkqkb(mapping, form, request, response);
	}

	private void setFormForXnNdXqNj(DtjsForm myForm) {
		// 判断学年，年度，学期,年级后，往form里注入的方法
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;

		if ((DealString.toGBK(myForm.getXn())).equalsIgnoreCase("")) {
			myForm.setXn(xn);
		}

		if ((DealString.toGBK(myForm.getXq())).equalsIgnoreCase("")) {
			myForm.setXq(xq);
		}

		if ((DealString.toGBK(myForm.getNd())).equalsIgnoreCase("")) {
			myForm.setNd(nd);
			myForm.setNj(nd);
		}
	}
}
