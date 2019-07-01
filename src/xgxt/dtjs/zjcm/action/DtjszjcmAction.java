package xgxt.dtjs.zjcm.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import xgxt.dtjs.zjcm.form.DtjszjcmForm;
import xgxt.dtjs.zjcm.model.DtjszjcmModel;
import xgxt.dtjs.zjcm.server.DtjszjcmService;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 浙江传媒学院学生信息管理党团建设-action类
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

public class DtjszjcmAction extends DispatchAction {

	/**
	 * @describe 根据条件查询目前所有的申请者
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward swcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_swcl";
		String realTable = "swcl";
		String title = "党团建设-数据维护-事务处理";

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

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
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			topTr = service.getSwclTopTr();
			// 取得查询结果
			rs = service.getSwclList(myModel, myForm);
			// 页面返回保证姓名不乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("swcl");
	}

	/**
	 * @describe 将事务处理信息从数据库中删除
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward delSwcl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		// 获得主键
		String xh = DealString.toGBK(request.getParameter("pk"));

		// 删除发展对象
		String[] keys = xh.split("!!SplitSignOne!!");
		// 删除相关数据
		String delSwcl = service.delSwcl(keys);

		// boolean del = service.delSwcl(xh, request);
		// 判断删除是否成功
		if (delSwcl == null || "".equals(delSwcl)) {
			request.setAttribute("del", "ok");
		} else {
			request.setAttribute("del", "no");
			request.setAttribute("delSwcl", delSwcl);
		}

		return mapping.findForward("delSwcl");
	}

	/**
	 * @describe 页面跳转到申请页面
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward swclShq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		String title = "党团建设-数据维护-事务处理申请";

		String xy = myForm.getXydm();
		// 获得材料类型
		List<HashMap<String, String>> cl = service.getClList();

		HashMap<String, String> rs = new HashMap<String, String>();

		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
		String time = f.format(new Date());

		xy = (xy == null) ? "" : xy;

		request.setAttribute("xydm", xy);
		request.setAttribute("title", title);
		request.setAttribute("clList", cl);
		request.setAttribute("sssj", time);
		request.setAttribute("rs", rs);

		return mapping.findForward("swclShq");
	}

	/**
	 * @describe 将申请信息保存到数据库
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveSwclshq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String title = "党团建设-数据维护-事务处理申请";
		String xh = myForm.getXh();
		// 所有新申请信息的审核类型统一设置为未审核
		myForm.setShlx("wsh");
		BeanUtils.copyProperties(myModel, myForm);
		// 添加申请信息
		boolean inserted = service.updataSwcl(myModel, xh, request);
		HashMap<String, String> rs = new HashMap<String, String>();
		// 页面返回保证备注不乱码
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		// 判断添加是否成功
		if (inserted) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		String xy = "";
		// 获得材料类型
		List<HashMap<String, String>> cl = service.getClList();

		request.setAttribute("xydm", xy);
		request.setAttribute("clList", cl);
		request.setAttribute("sssj", "");
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("swclShq");
	}

	/**
	 * @describe 页面跳转到审核页面
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward swclShh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		String xh = DealString.toGBK(request.getParameter("pk"));
		String title = "党团建设-数据维护-事务处理审核";
		// 获得欲修改信息
		HashMap<String, String> rs = service.getSwclSh(xh);

		String xy = "";
		String bjKey = "!!" + "!!";
		// 获得材料类型
		List<HashMap<String, String>> cl = service.getClList();
		String shlx = rs.get("shlx");
		String db = DealString.toGBK(request.getParameter("db"));
		// 该条记录是否通过双击鼠标获得
		if (db != null && !"".equals(db)) {
			request.setAttribute("db", db);
		} else {
			// 该条审核记录是否已经通过审核
			if ("tg".equals(shlx)) {
				request.setAttribute("tg", "tg");
			}
		}
		request.setAttribute("xydm", xy);
		request.setAttribute("clList", cl);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("swclShh");
	}

	/**
	 * @describe 将审核信息保存到数据库中
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveSwclshh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String title = "党团建设-数据维护-事务处理审核";
		String xh = myForm.getXh();
		// 获得材料类型
		String cllx = DealString.toGBK(request.getParameter("cllxV"));
		myForm.setCllx(cllx);

		BeanUtils.copyProperties(myModel, myForm);
		// 保存审核信息
		boolean inserted = service.updataSwcl(myModel, xh, request);
		// 判断保存是否成功
		if (inserted) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
		}
		String xy = "";
		String bjKey = "!!" + "!!";

		request.setAttribute("xydm", xy);
		request.setAttribute("title", title);
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("swclShh");
	}

	// /**
	// * @describe 时间设置
	// * @author luo
	// * @throws Exception
	// */
	// public ActionForward setSj(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response)
	// throws Exception {
	//
	// String pk = DealString.toGBK(request.getParameter("pk"));
	// String[] temp = pk.split("split");
	// // String temppk="";
	// String title = "时间设置";
	// //
	// // for(int i = 0;i<temp.length;i++){
	// // temppk
	// // }
	// request.setAttribute("title", title);
	// request.setAttribute("temppk", pk);
	//
	// return mapping.findForward("setSj");
	// }

	/**
	 * @describe 页面跳转到组织关系接转
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zzgx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		String xh = DealString.toGBK(request.getParameter("pk"));
		// 获得入党时间
		String rdsj = DealString.toGBK(request.getParameter("rdsj"));

		// 获得未接转者信息
		HashMap<String, String> rs = service.getZzgxxx(xh);
		// 获得接转者信息
		HashMap<String, String> rs_zzgx = service.getZzgxjz(xh);

		xh = rs_zzgx.get("xh");
		// 判断是否接转者
		if (xh != null && !"".equals(xh)) {
			request.setAttribute("rs_zzgx", rs_zzgx);
			rs_zzgx.put("rdsj", rdsj);
		} else {
			request.setAttribute("rs", rs);
			rs.put("rdsj", rdsj);
		}

		String xy = "";
		String bjKey = "!!" + "!!";

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("zzgx");
	}

	/**
	 * @descri 保存组织关系接转
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveZzgx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String xh = myForm.getXh();
		String xy = "";
		String bjKey = "!!" + "!!";

		BeanUtils.copyProperties(myModel, myForm);
		// 保存组织关系接转信息
		boolean inserted = service.updataZzgx(myModel, xh, request);

		// 判断是否保存失败
		if (inserted) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("zzgx");
	}

	/**
	 * @describe 根据条件查询目前所有的发展对象
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward fzdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_fzdx";
		String realTable = "fzdx";
		String title = "党团建设-数据维护-发展对象";

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		// 获得查询条件
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

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
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// 查询是否点击查询按钮
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 获得表头
			topTr = service.getFzdxTopTr();
			// 获得查询结果列表
			rs = service.getFzdxList(myModel, myForm);
			// 防止返回页面姓名乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("fzdx");
	}

	/**
	 * @describe 页面跳转到发展对象页面
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward fzdxOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();
		HashMap<String, String> rs = new HashMap<String, String>();

		String pk = DealString.toGBK(request.getParameter("pk"));
		String xy = "";
		HashMap<String, String> rsZjcm = new HashMap<String, String>();
		String bjKey = "!!" + "!!";

		// 判断是否按增加按钮
		if (pk != null && !"".equals(pk)) {
			// 获得发展对象信息
			rs = service.getFzdxOne(pk);
			String db = DealString.toGBK(request.getParameter("db"));
			// 判断是否鼠标双击
			if (db != null && !"".equals(db)) {
				request.setAttribute("db", db);
			}
			request.setAttribute("doType", "modi");
			request.setAttribute("type", "update");
		} else {
			request.setAttribute("doType", "add");
			rs.put("xh", "");
			request.setAttribute("rsZjcm", rsZjcm);
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("rs", rs);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("xsccList", service.getXxccList());

		return mapping.findForward("fzdxOne");
	}

	/**
	 * @describe 将发展对象信息保存到数据库
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveFzdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String nd = DealString.toGBK(request.getParameter("ndV"));
		String xm = DealString.toGBK(request.getParameter("xmV"));
		String xq = DealString.toGBK(request.getParameter("xqV"));
		String xn = DealString.toGBK(request.getParameter("xnV"));
		String kssj = DealString.toGBK(request.getParameter("kssjV"));
		String xh = myForm.getXh();

		// 预备党员的预备期为一年
		int jssj = 0;
		if (myForm.getJssj() != null || !"".equals(myForm.getJssj())) {
			jssj = Integer.parseInt(myForm.getJssj().substring(0, 4)) + 1;
		}

		myForm.setNd(nd);
		myForm.setXm(xm);
		myForm.setXq(xq);
		myForm.setXn(xn);
		myForm.setKssj(kssj);

		HashMap<String, String> rs = new HashMap<String, String>();

		BeanUtils.copyProperties(myModel, myForm);
		// 保存发展对象信息
		boolean inserted = service.updataFzdx(myModel, xh, request);

		// 判断保存是否成功
		if (inserted) {
			request.setAttribute("inserted", "ok");
			rs.put("xh", "");

			// 如果结束时间非空,如果非空将预备党员的结束时间设置为一年后
			// 例:发展对象结束时间:20081010 ---> 预备党员开始时间:20081010 预备党员结束时间:20091010
			if (jssj != 0) {
				myModel.setKssj(String.valueOf(myForm.getJssj()));
				myModel.setJssj(String.valueOf(jssj)
						+ myForm.getJssj().substring(4, 8));
			} else {
				myModel.setKssj(null);
				myModel.setJssj(null);
			}
			// 保存预备党员信息
			service.updataYbdy(myModel, xh, request);
		} else {
			request.setAttribute("inserted", "no");
			rs.put("xh", "");
		}
		String xy = "";

		request.setAttribute("xydm", xy);
		request.setAttribute("doType", "modi");
		request.setAttribute("rs", rs);
		request.setAttribute("rsZjcm", rs);

		return mapping.findForward("fzdxOne");
	}

	/**
	 * @describe 将发展对象信息从数据库中删除
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward delFzdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		String pk = DealString.toGBK(request.getParameter("pk"));

		// 删除发展对象
		// boolean del = service.delFzdx(pk, request);
		String[] keys = pk.split("!!SplitSignOne!!");
		String delFzdx = service.delFzdx(keys);
		if (delFzdx == null || "".equals(delFzdx)) {
			request.setAttribute("del", "ok");
		} else {
			request.setAttribute("delFzdx", delFzdx);
			request.setAttribute("del", "no");
		}

		return mapping.findForward("delFzdx");
	}

	/**
	 * @describe 显示全部发展对象
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward fzdxAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_rdjjfzxx";
		String realTable = "rdjjfzxxb";
		String title = "党团建设-数据维护-入党积极分子-发展对象设置";
		String fzdx = request.getParameter("fz");

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		fzdx = (fzdx == null) ? "" : fzdx;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 从发展对象页面进入
			if ("fz".equals(fzdx)) {
				topTr = service.getFzdxTopTr();
				rs = service.getFzdxList(myModel, myForm);
			}
			// 从入党积极分子页面进入
			else {
				topTr = service.getRdjjfzTopTr();
				rs = service.getRdjjfzList(myModel, myForm);
			}
		}

		request.setAttribute("fz", fzdx);
		request.setAttribute("xydm", xy);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("fzdxAll");
	}

	/**
	 * @describe 添加新党员信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward addDyxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();

		String[] checkVal = myForm.getCheckVal();
		String zzsj = myForm.getJssj();

		// 判断有否选中
		if (checkVal != null) {
			// 添加新党员信息
			String addDy = service.addDy(checkVal, zzsj);
			// 判断有否添加失败
			if ((addDy != null && !"".equals(addDy))) {
				request.setAttribute("inserted", "no");
				request.setAttribute("addDy", addDy);
				return mapping.findForward("addDyxx");
			}
		}

		request.setAttribute("inserted", "ok");

		return mapping.findForward("addDyxx");
	}

	/**
	 * @describe 批量添加发展对象信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward addFzdx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();

		String tableName = "view_rdjjfzxx";
		String realTable = "rdjjfzxxb";
		String title = "党团建设-数据维护-入党积极分子-发展对象设置";
		String fzdx = request.getParameter("fz");

		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;

		String[] checkVal = myForm.getCheckVal();
		String kssj = myForm.getKssj();
		String jssj = myForm.getJssj();

		request.setAttribute("title", title);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("xydm", xy);
		request.setAttribute("fz", fzdx);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());

		// 判断有否选中
		if (checkVal != null) {
			String[] keys = new String[checkVal.length];
			String[] isYb = new String[checkVal.length];
			String[] noYb = new String[checkVal.length];
			int j = 0;
			int k = 0;

			for (int i = 0; i < checkVal.length; i++) {
				String[] pk = checkVal[i].split("&");
				String xn = pk[1];
				String xq = pk[2];
				String xh = pk[3];

				keys[i] = xn + xq + xh;
				boolean isYbdy = service.isYbdy(keys[i]);
				// 判断是否党员
				if (isYbdy) {
					isYb[j] = keys[i];
					j++;
				} else {
					noYb[k] = checkVal[i];
					k++;
				}
			}
			// 判断是否从发展对象页面进入
			if ("fz".equals(fzdx)) {
				// 更新发展对象
				String numFzdx = service.updateFzdx(keys, jssj);
				String numIsYb = null;
				String numNoYb = null;

				if (numFzdx == null || "".equals(numFzdx)) {
					// 是预备党员的话,修改其预备党员信息
					if (isYb[0] != null) {
						numIsYb = service.updateYbdy(isYb, jssj, j);
					}
					// 不是预备党员的话,添加其预备党员信息
					if (noYb[0] != null) {
						numNoYb = service.addYbdy(noYb, jssj, k);
					}
				}
				// 操作出现错误
				if ((numFzdx != null && !"".equals(numFzdx))
						|| (numIsYb != null && !"".equals(numIsYb))
						|| (numNoYb != null && !"".equals(numNoYb))) {
					request.setAttribute("inserted", "no");
					request.setAttribute("numIsYb", numIsYb);
					request.setAttribute("numNoYb", numNoYb);
					request.setAttribute("numFzdx", numFzdx);
					return mapping.findForward("addFzdx");
				}

			} else {
				// 删除发展对象信息
				String numFzdx = service.delFzdx(keys);
				// 添加发展对象信息
				String addFzdx = service.addFzdx(checkVal, kssj);
				// 操作出现错误
				if ((numFzdx != null && !"".equals(numFzdx))
						|| (addFzdx != null && !"".equals(addFzdx))) {
					request.setAttribute("inserted", "no");
					request.setAttribute("addFzdx", addFzdx);
					request.setAttribute("numFzdx", numFzdx);
					return mapping.findForward("addFzdx");
				}
			}
		}

		request.setAttribute("inserted", "ok");

		return mapping.findForward("addFzdx");
	}

	/**
	 * @describe 显示符合转正条件的预备党员
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward zzYb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_ybdyxx";
		String realTable = "ybdyxxb";
		String title = "党团建设-数据维护-预备党员-转正一览";

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得党员信息表头
			topTr = service.getZzybTopTr();
			// 取得符合转正条件的预备党员信息
			rs = service.getZzybList(myModel, myForm);
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("writeAble", "yes");
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("zzYb");
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

	// TODO
	/**
	 * @describe 根据条件查询目前所有的党内奖惩信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dnjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String tableName = "view_dnjc";
		String realTable = "dnjc";
		String title = "党团建设-数据维护-党内奖惩";

		ArrayList<String[]> rs = null;
		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

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
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// 点击查询按钮进行查询
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// 取得表头
			topTr = service.getDnjcTopTr();
			// 取得查询结果
			rs = service.getDnjcList(myModel, myForm);
			// 页面返回保证姓名不乱码
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		commonRequestSet(request, tableName, realTable, rs, topTr, title);

		return mapping.findForward("dnjc");
	}

	/**
	 * @describe 将党内奖惩信息从数据库中删除
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward delDnjc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		// 获得主键
		String xh = DealString.toGBK(request.getParameter("pk"));

		// 删除发展对象
		String[] keys = xh.split("!!SplitSignOne!!");
		// 删除相关数据
		String delDnjc = service.delDnjc(keys);

		// boolean del = service.delSwcl(xh, request);
		// 判断删除是否成功
		if (delDnjc == null || "".equals(delDnjc)) {
			request.setAttribute("del", "ok");
		} else {
			request.setAttribute("del", "no");
			request.setAttribute("delDnjc", delDnjc);
		}

		return mapping.findForward("delDnjc");
	}

	/**
	 * @describe 页面跳转到党内奖惩申请页面
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dnjcShq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		String title = "党团建设-数据维护-党内奖惩申请";
		String xy = myForm.getXydm();
		// 获得奖惩类型
		List<HashMap<String, String>> lx = service.getJclxList();
		// 获得奖惩理由
		List<HashMap<String, String>> ly = service.getJclyList();

		HashMap<String, String> rs = new HashMap<String, String>();

		xy = (xy == null) ? "" : xy;

		request.setAttribute("xydm", xy);
		request.setAttribute("title", title);
		request.setAttribute("lxList", lx);
		request.setAttribute("lyList", ly);
		request.setAttribute("rs", rs);

		return mapping.findForward("dnjcShq");
	}

	/**
	 * @describe 将党内奖惩申请信息保存到数据库
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveDnjcshq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String title = "党团建设-数据维护-党内奖惩申请";
		String xh = myForm.getXh();
		// 所有新申请信息的审核类型统一设置为未审核
		myForm.setShlx("wsh");
		// 获得奖惩类型
		List<HashMap<String, String>> lx = service.getJclxList();
		// 获得奖惩理由
		List<HashMap<String, String>> ly = service.getJclyList();

		BeanUtils.copyProperties(myModel, myForm);
		// 添加申请信息
		boolean inserted = service.updataDnjc(myModel, xh, request);
		HashMap<String, String> rs = new HashMap<String, String>();
		// 页面返回保证姓名不乱码
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		// 判断添加是否成功
		if (inserted) {
			request.setAttribute("inserted", "ok");
		} else {
			request.setAttribute("inserted", "no");
		}
		String xy = "";

		request.setAttribute("xydm", xy);
		request.setAttribute("lxList", lx);
		request.setAttribute("lyList", ly);
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);

		return mapping.findForward("dnjcShq");
	}

	/**
	 * @describe 页面跳转到党内奖惩审核页面
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dnjcShh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		String xh = DealString.toGBK(request.getParameter("pk"));
		// 获得欲修改信息
		HashMap<String, String> rs = service.getDnjc(xh);
		String title = "党团建设-数据维护-党内奖惩审核";
		String xy = "";
		String bjKey = "!!" + "!!";
		// 获得奖惩类型
		List<HashMap<String, String>> lx = service.getJclxList();
		// 获得奖惩理由
		List<HashMap<String, String>> ly = service.getJclyList();
		String shlx = rs.get("shlx");
		String db = DealString.toGBK(request.getParameter("db"));
		// 该条记录是否通过双击鼠标获得
		if (db != null && !"".equals(db)) {
			request.setAttribute("db", db);
		} else {
			// 该条审核记录是否已经通过审核
			if ("tg".equals(shlx)) {
				request.setAttribute("tg", "tg");
			}
		}
		request.setAttribute("xydm", xy);
		request.setAttribute("lxList", lx);
		request.setAttribute("lyList", ly);
		request.setAttribute("rs", rs);
		request.setAttribute("title", title);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("dnjcShh");
	}

	/**
	 * @describe 将党内奖惩审核信息保存到数据库中
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward saveDnjcshh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmForm myForm = (DtjszjcmForm) form;
		DtjszjcmService service = new DtjszjcmService();
		DtjszjcmModel myModel = new DtjszjcmModel();

		String title = "党团建设-数据维护-党内奖惩审核";
		String xh = myForm.getXh();
		// 获得材料类型
		String shlx = DealString.toGBK(request.getParameter("shlxV"));
		myForm.setCllx(shlx);

		BeanUtils.copyProperties(myModel, myForm);
		// 保存审核信息
		boolean inserted = service.updataDnjc(myModel, xh, request);
		// 判断保存是否成功
		if (inserted) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
		}
		String xy = "";
		String bjKey = "!!" + "!!";

		request.setAttribute("xydm", xy);
		request.setAttribute("title", title);
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));

		return mapping.findForward("dnjcShh");
	}

	/**
	 * @describe 批量审核党内奖惩信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward dnjcShhAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		// 获得主键
		String xh = DealString.toGBK(request.getParameter("pk"));
		String shlx = DealString.toGBK(request.getParameter("zht"));
		// 删除发展对象
		String[] keys = xh.split("!!SplitSignOne!!");
		// 删除相关数据
		String updateShh = service.dnjcShhAll(keys,shlx);

		// boolean del = service.delSwcl(xh, request);
		// 判断删除是否成功
		if (updateShh == null || "".equals(updateShh)) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
			request.setAttribute("updateShh", updateShh);
		}

		return mapping.findForward("delDnjc");
	}
	
	/**
	 * @describe 批量审核事务处理信息
	 * @author luo
	 * @throws Exception
	 */
	public ActionForward swclShhAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DtjszjcmService service = new DtjszjcmService();

		// 获得主键
		String xh = DealString.toGBK(request.getParameter("pk"));
		String shlx = DealString.toGBK(request.getParameter("zht"));
		// 删除发展对象
		String[] keys = xh.split("!!SplitSignOne!!");
		// 删除相关数据
		String updateShh = service.swclShhAll(keys,shlx);

		// boolean del = service.delSwcl(xh, request);
		// 判断删除是否成功
		if (updateShh == null || "".equals(updateShh)) {
			request.setAttribute("update", "ok");
		} else {
			request.setAttribute("update", "no");
			request.setAttribute("updateShh", updateShh);
		}

		return mapping.findForward("delSwcl");
	}
}
