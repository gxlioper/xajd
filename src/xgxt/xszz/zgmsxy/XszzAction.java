package xgxt.xszz.zgmsxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.comm.CommService;
import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.SearchUtils;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

public class XszzAction extends BaseAction {

	// private boolean isNull(String str) {
	// return ((str == null) || str.equalsIgnoreCase("") || str
	// .equalsIgnoreCase("all"));
	// }

	// private void generateList(ArrayList<HashMap<String, String>>
	// list,String[] col,String[] colCn){
	// for(int i=0;i<col.length;i++){
	// HashMap<String, String> map = new HashMap<String, String>();
	// map.put("en",col[i]);
	// map.put("cn", colCn[i]);
	// list.add(map);
	// }
	// }

	/**
	 * 困难生认定申请页面 knsrdsq ----- 困难生认定申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzZgmsxyService service = new XszzZgmsxyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrdxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("xn", Base.currXn);// 当前学年
		request.setAttribute("sfksq", service.getKnsrdSqQx(sUserType, userDep,
				xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdsq");
	}

	/**
	 * 保存困难生认定申请信息 jtqkdcsqSave ---- 保存困难生认定申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		KnsrdModel knsrdModel = new KnsrdModel();
		BeanUtils.copyProperties(knsrdModel, xszzZgmsxyActionForm);
		XszzZgmsxyService service = new XszzZgmsxyService();
		boolean bJg = service.saveKnsrdSqxx(knsrdModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = knsrdModel.getXh();
		String xn = knsrdModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsrdxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("xn", Base.currXn);// 当前学年
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdsqSave");
	}

	/**
	 * 家庭情况调查表页面 jtqkdcsqb ----- 家庭情况调查表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtqkdcsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsrdModel knsrdModel = new KnsrdModel();
		BeanUtils.copyProperties(knsrdModel, xszzZgmsxyActionForm);
		stuMap = service.getKnsrdSqb(knsrdModel, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("jtqkdcsqb");
	}

	/**
	 * 困难生认定申请表页面 knsrdsqb ----- 困难生认定申请表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsrdModel knsrdModel = new KnsrdModel();
		BeanUtils.copyProperties(knsrdModel, xszzZgmsxyActionForm);
		stuMap = service.getKnsrdSqb(knsrdModel, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsrdsqb");
	}

	/**
	 * 困难生认定审核页面 knsrdsh ----- 困难生认定审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			xszzZgmsxyActionForm.setXn(Base.currXn);
		}

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsrdxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			xszzZgmsxyActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		List<HashMap<String, String>> topList = service.getKnsrdTit();
		List<String[]> resList = service.getKnsrdRes(queryModel, request);
		String xh = DealString.toGBK(xszzZgmsxyActionForm.getXh());
		xszzZgmsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgmsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表
		HashMap<String, String> hsQuery = new HashMap<String, String>();

		hsQuery.put("mzpyjg", Base.chgNull(queryModel.getMzpyjg(), "", 1));
		hsQuery.put("xysh", Base.chgNull(queryModel.getXysh(), "", 1));
		hsQuery.put("xxsh", Base.chgNull(queryModel.getXxsh(), "", 1));

		request.setAttribute("hs", hsQuery);
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("shList", service.getshList());
		request.setAttribute("hForm", xszzZgmsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zgmsxy_knsxx");
		request.setAttribute("tableName", "view_zgmsxy_knsxx");
		return mapping.findForward("knsrdsh");
	}

	/**
	 * 困难生认定批量审核 knsrdplsh ----- 困难生认定批量审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdplsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		XszzZgmsxyService service = new XszzZgmsxyService();
		HashMap<String, String> rs = new HashMap<String, String>();
		String doType = Base.chgNull(request.getParameter("doType"), "", 1);

		String cbVal = Base.chgNull(request.getParameter("cbVal"),
				"!!splitOne!!", 1);
		String shType = Base.chgNull(request.getParameter("shType"), "", 1);
		String shjg = Base.chgNull(request.getParameter("shjg"), "", 1);

		if ("save".equalsIgnoreCase(doType)) {
			service.modKnsrdxx(cbVal, shType, shjg, request);
			request.setAttribute("over", "over");
		}

		rs.put("cbVal", cbVal);
		rs.put("shType", shType);
		rs.put("shjg", shjg);
		request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		return mapping.findForward("knsrdplsh");
	}

	/**
	 * 困难生认定信息导出 knsrdExp ----- 困难生认定信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		service.getKnsrdExp(queryModel, response, request);
		return mapping.findForward("knsrdExp");
	}

	/**
	 * 困难生认定审核详细页面 knsrdshOne ----- 困难生认定审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrdxx(pkVal);
		xszzZgmsxyActionForm.setMzpyjg(stuMap.get("mzpyjg"));
		xszzZgmsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZgmsxyActionForm.setXxsh(stuMap.get("xxsh"));

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdshOne");
	}

	/**
	 * 保存困难生认定审核信息 knsrdshSave ---- 保存困难生认定审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsrdshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		KnsrdModel knsrdModel = new KnsrdModel();
		BeanUtils.copyProperties(knsrdModel, xszzZgmsxyActionForm);
		boolean bJg = service.saveKnsrdShxx(knsrdModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = knsrdModel.getXh();
		String xn = knsrdModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsrdxx(pkVal);
		xszzZgmsxyActionForm.setMzpyjg(stuMap.get("mzpyjg"));
		xszzZgmsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZgmsxyActionForm.setXxsh(stuMap.get("xxsh"));

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsrdshSave");
	}

	/**
	 * @describe 助学贷款申请时间设定
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_zxdksj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		String pk = "xmmc||xydm";
		String tips = "助学贷款 - 基础数据维护 - 助学贷款申请时间维护";
		String rsNum = "0";// 返回的记录数
		String tableName = "view_zgdzdx_zxdk_sjb";
		String[] colList = new String[] { "主键", "xmmc", "xymc", "xyrs", "kssj",
				"jssj" };
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		String writeAble = "yes";// 写权限

		String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);
		String xydm = Base.chgNull(request.getParameter("xydm"), "", 1);
		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
			xydm = userDep;
		}

		querry.append(SearchUtils.equalSql("xmmc", xmmc));
		querry.append(SearchUtils.equalSql("xydm", xydm));
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("xmmc", xmmc);
		map.put("xydm", xydm);

		String sql = "select xmmc||xydm 主键,a.* from view_zgdzdx_zxdk_sjb a"
				+ querry.toString();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}

		request.setAttribute("zxdkxmList", xszzDao.getZgmsxyZxdkxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs1", map);
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("tips", tips);// 发送位置提示栏信息
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);
		return mapping.findForward("data_zxdksj");
	}

	/**
	 * @describe 根据主键得到助学贷款时间设置详细信息和保存信息
	 * @author zhoumi
	 * @throws Exception
	 */
	public ActionForward zxdksjEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HashMap<String, String> map = new HashMap<String, String>();
		String writeAble = "yes";// 写权限
		String[] outValue = null;
		String userType = dao.getUserType(request.getSession().getAttribute(
				"userDep").toString());
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String sql = "select xmmc,xydm,xymc,xyrs,kssj,jssj from view_zgdzdx_zxdk_sjb where xmmc||xydm=?";
		String[] outString = new String[] { "xmmc", "xydm", "xymc", "xyrs",
				"kssj", "jssj" };

		if ("save".equalsIgnoreCase(act)) {
			String xmmc = Base.chgNull(request.getParameter("xmmc"), "", 1);
			String xydm = Base.chgNull(request.getParameter("xydm"), "", 1);
			String kssj = Base.chgNull(request.getParameter("kssj"), "", 1);
			String jssj = Base.chgNull(request.getParameter("jssj"), "", 1);
			boolean b = false;

			String num = dao
					.getOneRs(
							"select count(*) num from view_zgdzdx_zxdk_sjb where xmmc||xydm=?",
							new String[] { xmmc + xydm }, "num");
			if ("0".equalsIgnoreCase(num)) {
				b = StandardOperation.insert("zgdzdx_zxdk_sjb", new String[] {
						"xmmc", "xydm", "kssj", "jssj" }, new String[] { xmmc,
						xydm, kssj, jssj }, request);
			} else {
				b = StandardOperation.update("zgdzdx_zxdk_sjb", new String[] {
						"kssj", "jssj" }, new String[] { kssj, jssj },
						"xmmc||xydm", xmmc + xydm, request);
			}
			if (b) {
				request.setAttribute("ok", "ok");
				pkVal = xmmc + xydm;
			} else {
				request.setAttribute("ok", "no");
			}
		}

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);
		int len1 = outString.length;
		int len2 = 0;
		if (outValue != null) {
			len2 = outValue.length;
		}
		int max = 0;
		if (len1 >= len2) {
			max = len2;
		} else {
			max = len1;
		}
		for (int i = 0; i < max; i++) {
			if (null != outValue[i]) {
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], "");
			}
		}

		if (userType.equalsIgnoreCase("xy")) {
			writeAble = "no";
		}
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("zxdkxmList", xszzDao.getZgmsxyZxdkxmList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("rs", map);
		request.setAttribute("act", act);
		request.setAttribute("userType", userType);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zxdksjEdit");
	}

	/**
	 * @describe 批量设置助学贷款申请时间
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdksjPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String act = Base.chgNull(request.getParameter("act"), "", 0);
		String pkDel = Base.chgNull(request.getParameter("pkDel"), "", 1);
		String kssj = "1900-01-01";
		String jssj = "1900-01-01";
		HashMap<String, String> map = new HashMap<String, String>();
		if ("save".equalsIgnoreCase(act)) {
			kssj = Base.chgNull(request.getParameter("kssj"), "", 1);
			jssj = Base.chgNull(request.getParameter("jssj"), "", 1);
			String[] pkT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkT.length];
			for (int i = 0; i < pkT.length; i++) {
				sqlT[i] = "update zgdzdx_zxdk_sjb set kssj='" + kssj
						+ "',jssj='" + jssj + "' where xmmc||xydm='" + pkT[i]
						+ "'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("end", "end");
		}
		map.put("kssj", kssj);
		map.put("jssj", jssj);
		map.put("pkDel", pkDel);
		request.setAttribute("rs", map);
		return mapping.findForward("zxdksjPlsz");
	}

	/**
	 * @describe 助学贷款时间初始化
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxdksjcsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		StandardOperation.delete("zgdzdx_zxdk_sjb", new String[] { "1" },
				new String[] { "1" }, request);
		String[] sqlT = new String[5];
		sqlT[0] = "insert into zgdzdx_zxdk_sjb(xmmc,xydm) select '助学贷款申请' xmmc,xydm from view_njxyzybj group by xydm";
		sqlT[1] = "insert into zgdzdx_zxdk_sjb(xmmc,xydm) select '毕业生信息采集' xmmc,xydm from view_njxyzybj group by xydm";
		dao.runBatch(sqlT);
		return new ActionForward("/zgmsxy_xszz.do?method=data_zxdksj&go=go",
				false);
	}

	/**
	 * 国家助学贷款审核页面 gjzxdksh ----- 国家助学贷款审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();

		HttpSession session = request.getSession();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = (String) session.getAttribute("userName");
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxdkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxdkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxdkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}

		if ("xy".equalsIgnoreCase(userType)) {
			xszzZgmsxyActionForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			xszzZgmsxyActionForm.setXh(userName);
		}

		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			xszzZgmsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		List<HashMap<String, String>> topList = service.getGjzxdkTit();
		List<String[]> resList = service.getGjzxdkRes(xszzZgmsxyActionForm,
				queryModel, request);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgmsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||sfzh");
		request.setAttribute("currNd", Base.currNd);
		request.setAttribute("hForm", xszzZgmsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zgmsxy_gjzxdkdkxx");
		request.setAttribute("tableName", "view_zgmsxy_gjzxdk");
		return mapping.findForward("gjzxdksh");
	}

	/**
	 * 国家助学贷款信息导出 gjzxdkExp ----- 国家助学贷款信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		service.getGjzxdkExp(queryModel, response, request);
		return mapping.findForward("gjzxdkExp");
	}

	/**
	 * 国家助学贷款审核详细页面 gjzxdkshOne ----- 国家助学贷款审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);

		xszzZgmsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZgmsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkshOne");
	}

	/**
	 * 保存国家助学贷款审核信息 gjzxdkshSave ---- 保存国家助学贷款审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		GjzxdkModel model = new GjzxdkModel();
		BeanUtils.copyProperties(model, xszzZgmsxyActionForm);
		boolean bJg = service.saveGjzxdkShxx(model, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String sfzh = model.getSfzh();
		String nd = model.getNd();
		String pkVal = nd + sfzh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkshSave");
	}

	/**
	 * 
	 * @describe 毕业生信息审核
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward bysxxsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgmsxyActionForm.setXydm(userDep);
		}
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delBysxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modBysxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modBysxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}
		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		List<HashMap<String, String>> topList = service.getBysxxTit();
		List<String[]> resList = service.getBysxxRes(xszzZgmsxyActionForm,
				queryModel, request);
		String xh = DealString.toGBK(xszzZgmsxyActionForm.getXh());
		xszzZgmsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgmsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		xszzZgmsxyActionForm.setXysh(Base.chgNull(queryModel.getXysh(), "", 1));
		xszzZgmsxyActionForm.setXxsh(Base.chgNull(queryModel.getXxsh(), "", 1));

		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", xszzZgmsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zgmsxy_gjzxdkgrxx");
		request.setAttribute("tableName", "view_zgmsxy_gjzxdkgrxx");
		// 2011.9.6 qlj 查询后标题消失问题解决
		request.setAttribute("path", "zgmsxy_xszz.do?method=gjzxdksqjg");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("bysxxsh");
	}

	/**
	 * 毕业生审核详细页面 gjzxjshOne ----- 毕业生审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bysshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getBysxx(pkVal);
		xszzZgmsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZgmsxyActionForm.setXxsh(stuMap.get("xxsh"));

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bysxxOne");
	}

	/**
	 * 保存毕业生审核信息 gjzxjshSave ---- 保存毕业生审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bysshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		GjzxdkModel model = new GjzxdkModel();
		BeanUtils.copyProperties(model, xszzZgmsxyActionForm);
		boolean bJg = service.savBysShxx(model, request);// 保存审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String pkVal = xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getBysxx(pkVal);
		xszzZgmsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZgmsxyActionForm.setXxsh(stuMap.get("xxsh"));

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bysxxOne");
	}

	/**
	 * 毕业生信息导出 bysxxExp ----- 毕业生信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bysxxExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		service.getBysxxExp(queryModel, response, request);
		return mapping.findForward("bysxxExp");
	}

	/**
	 * 国家助学贷款申请页面 gjzxdksq ----- 国家助学贷款申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzZgmsxyService service = new XszzZgmsxyService();
		DAO dao = DAO.getInstance();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String sfzh = dao.getOneRs("select sfzh from view_xsjbxx where xh=?",
				new String[] { xh }, "sfzh");
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String nd = Base.currNd;
		pkVal = pkVal.equalsIgnoreCase("") ? nd + sfzh : pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getGjzxdkxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}

		if (StringUtils.isNotNull(sfzh)) {
			stuMap.put("sfzh", sfzh);
		}

		String doType = request.getParameter("doType");

		request.setAttribute("doType", doType);
		String sfksq = service.getGjzxdkSqQx(sUserType, userDep, xh);

		if ("-1".equalsIgnoreCase(sfksq)) {
			String msg = "现在不在申请时间内！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("sfksq", sfksq);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("zgmy_gjzxdksq");
	}

	/**
	 * 保存国家助学贷款申请信息 gjzxdksqSave ---- 保存国家助学贷款申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZgmsxyActionForm);
		XszzZgmsxyService service = new XszzZgmsxyService();
		boolean bJg = service.saveGjzxdkSqxx(gjzxdkModel, request);// 保存信息
		String isPass=(String)request.getAttribute("isPASS");
		if (bJg  && !"is".equalsIgnoreCase(isPass)) {
			request.setAttribute("ok", "ok");
		} else if(!"is".equalsIgnoreCase(isPass)){
			request.setAttribute("ok", "no");
		}

		String sfzh = gjzxdkModel.getSfzh();
		String nd = gjzxdkModel.getNd();
		String pkVal = nd + sfzh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);// 得到贷款详细信息
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("zgmy_gjzxdksqSave");
	}

	/**
	 * 毕业生信息采集申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bysxxsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgmsxyActionForm zgmsForm = (XszzZgmsxyActionForm) form;
		HttpSession session = request.getSession();
		XszzZgmsxyService service = new XszzZgmsxyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		// String userDep =
		// session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xh : pkVal;
		xh = pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getBysxxcjMap(pkVal);
			if (stuMap.size() < 3) {
				request.setAttribute("isNull", "is");
			}
			zgmsForm.setFqxm(stuMap.get("fqxm"));
			zgmsForm.setFqdh(stuMap.get("fqdh"));
			zgmsForm.setFqgzdw(stuMap.get("fqgzdw"));
			zgmsForm.setMqdh(stuMap.get("mqdh"));
			zgmsForm.setMqgzdw(stuMap.get("mqgzdw"));
			zgmsForm.setMqxm(stuMap.get("mqxm"));
			zgmsForm.setLxdh(stuMap.get("lxdh"));
			zgmsForm.setDqgzdwdh(stuMap.get("dqgzdwdh"));
			zgmsForm.setDqgzdwjdz(stuMap.get("dqgzdwjdz"));
			zgmsForm.setDqgzdwyb(stuMap.get("dqgzdwyb"));
			zgmsForm.setBrjyqxhdw(stuMap.get("brjyqxhdw"));
			zgmsForm.setBrjyqxhdw(stuMap.get("brjyqxhdw"));
			zgmsForm.setBrdzyxjdzlxfs(stuMap.get("brdzyxjdzlxfs"));
			zgmsForm.setJtzz(stuMap.get("jtzz"));
			zgmsForm.setYzbm(stuMap.get("yzbm"));
			zgmsForm.setLxfsbgqk(stuMap.get("lxfsbgqk"));
			zgmsForm.setJtgddh(stuMap.get("jtgddh"));

		}

		String sfksq = service.getGjzxdkSqQx(sUserType, xh);

		if ("-1".equalsIgnoreCase(sfksq)) {
			String msg = "现在不在申请时间内！ ";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}

		request.setAttribute("doType", request.getParameter("doType"));
		stuMap.put("pkVal", pkVal);
		request.setAttribute("sfksq", sfksq);
		request.setAttribute("rs", stuMap);

		return mapping.findForward("bysxxcjsq");
	}

	/**
	 * 毕业生信息采集申请保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bysxxsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// HttpSession session = request.getSession();
		// String sUserName =
		// session.getAttribute("userName").toString();//SESSION中获取用户名
		// String sUserType =
		// session.getAttribute("userType").toString();//SESSION中获取用户类型
		// String userDep =
		// session.getAttribute("userDep").toString();//SESSION中获取用户部门
		XszzZgmsxyActionForm zgmsForm = (XszzZgmsxyActionForm) form;
		QueryModel queryModel = new QueryModel();
		BeanUtils.copyProperties(queryModel, zgmsForm);
		XszzZgmsxyService service = new XszzZgmsxyService();
		boolean bJg = service.saveByscjxx(queryModel, request);
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String xh = queryModel.getXh();
		String pkVal = xh;
		stuMap = service.getBysxxcjMap(pkVal);
		stuMap.put("pkVal", pkVal);
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		zgmsForm.setJtzz(DealString.toGBK(zgmsForm.getJtzz()));
		zgmsForm.setFqxm(DealString.toGBK(zgmsForm.getFqxm()));
		zgmsForm.setFqgzdw(DealString.toGBK(zgmsForm.getFqgzdw()));
		zgmsForm.setMqxm(DealString.toGBK(zgmsForm.getMqxm()));
		zgmsForm.setMqgzdw(DealString.toGBK(zgmsForm.getMqgzdw()));
		zgmsForm
				.setBrdzyxjdzlxfs(DealString.toGBK(zgmsForm.getBrdzyxjdzlxfs()));
		zgmsForm.setBrjyqxhdw(DealString.toGBK(zgmsForm.getBrjyqxhdw()));
		zgmsForm.setLxfsbgqk(DealString.toGBK(zgmsForm.getLxfsbgqk()));
		return mapping.findForward("bysxxcjsq");
	}

	public ActionForward bysxxsqprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgmsxyService service = new XszzZgmsxyService();
		String xh = request.getParameter("xh");
		xh = StringUtils.isNull(xh) ? request.getParameter("pkVal") : xh.trim();
		HashMap<String, String> rs = service.getBysxxcjMap(xh);
		request.setAttribute("rs", rs);
		return mapping.findForward("bysprint");
	}

	/**
	 * @describe 申请结果查询
	 * @author zhoumi
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		String username = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String sql = "";
		String[] colList = null;
		String[] colListCN = null;
		Vector<Object> rs = new Vector<Object>();
		String rsNum = "";
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String doType = request.getParameter("doType");
		String gnmkbz = DealString.toGBK(request.getParameter("gnmkbz"));
		HashMap<String, String> map = new HashMap<String, String>();
		ActionForward myActFwd = null;

		request.setAttribute("path", "zgmsxy_xszz.do?method=gjzxdksqjg");
		FormModleCommon.commonRequestSet(request);

		if ("query".equals(doType)) {
			map.put("gnmkbz", gnmkbz);
			if ("1".equalsIgnoreCase(gnmkbz)) {
				String tableName = "view_zgmsxy_gjzxdk";
				colList = new String[] { "url", "行号", "nd", "xh", "sfzh", "xm",
						"hth", "dkqx", "htffje", "sjffrq", "xysh", "xxsh" };
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward(
							"/zgmsxy_xszz.do?method=gjzxdksh&isQuery=is", false);
					return myActFwd;
				}
				sql = "select '/xgxt/zgmsxy_xszz.do?method=gjzxdksq'||'&'||'pkVal='||nd||sfzh url,rownum 行号,a.* from "
						+ tableName + " a where xh=? order by nd";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				rs.addAll(dao
						.rsToVator(sql, new String[] { username }, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}

				request.setAttribute("rs", rs);// 发送数据集
				request.setAttribute("topTr", topTr);// 发送表头
				request.setAttribute("rsNum", rsNum);// 发送记录数

				return new ActionForward("/xszz/zgmsxy/gjzxdkforstu.jsp", false);
			}
			if ("2".equalsIgnoreCase(gnmkbz)) {
				String tableName = "view_zgmsxy_gjzxdkgrxx";
				colList = new String[] { "url", "行号", "xh", "sfzh", "xm",
						"xysh", "xxsh", "xyshyj", "xxshyj" };
				if (!userType.equalsIgnoreCase("stu")) {
					myActFwd = new ActionForward(
							"/zgmsxy_xszz.do?method=bysxxsh&isQuery=is", false);
					return myActFwd;
				}
				sql = "select '/xgxt/zgmsxy_xszz.do?method=bysxxsq'||'&'||'pkVal='||xh url,rownum 行号,a.* from "
						+ tableName + " a where xh=?";
				colListCN = dao.getColumnNameCN(colList, tableName);
				topTr = dao.arrayToList(colList, colListCN);
				rs.addAll(dao
						.rsToVator(sql, new String[] { username }, colList));
				if (rs == null) {
					rsNum = "0";
				} else {
					rsNum = String.valueOf(rs.size());
				}
				request.setAttribute("rs", rs);// 发送数据集
				request.setAttribute("topTr", topTr);// 发送表头
				request.setAttribute("rsNum", rsNum);// 发送记录数
				return new ActionForward("/xszz/zgmsxy/bysxxforstu.jsp", false);
			}
		}

		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("rs1", map);// 把查询条件发回去
		return mapping.findForward("gjzxdksqjg");
	}

	/**
	 * 国家助学贷款上报信息 gjzxdksbxx ----- 国家助学贷款上报信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if (userType.equalsIgnoreCase("xy")) {
			xszzZgmsxyActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		List<HashMap<String, String>> topList = service.getGjzxdkSbxxTit();
		List<String[]> resList = service.getGjzxdkSbxxRes(queryModel, request);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgmsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", xszzZgmsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "");
		request.setAttribute("tableName", "view_zgmsxy_gjzxdksbxx");
		return mapping.findForward("gjzxdksbxx");
	}

	/**
	 * 国家助学贷款上报信息导出 gjzxdksbExp ----- 国家助学贷款上报信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksbExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		service.gjzxdksbExp(queryModel, response, request);
		return mapping.findForward("gjzxdksbExp");
	}

	/**
	 * 生源地贷款
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syddkManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		XszzZgmsxyActionForm myForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		
		String message="";
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delSyddk(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
//		 ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);
		
		// 显示列数
		String showNum = String.valueOf(service.getTopTr("syddk",rForm).size());
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		
		boolean fdyQx = Boolean.parseBoolean(session.getAttribute("fdyQx")
				.toString());
		// 班主任权限
		boolean bzrQx = Boolean.parseBoolean(session.getAttribute("bzrQx")
				.toString());
	
		myForm.setFdyQx(fdyQx);
		myForm.setBzrQx(bzrQx);
		myForm.setUser(user);
		rForm.setRsArrList((ArrayList<String[]>)service.getSyddkList(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============通用设置 end ============
		
		
		request.setAttribute("path", "zgmsxy_xszz.do?method=syddkManage");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		//表头
		request.setAttribute("topTr", service.getTopTr("syddk",rForm));
		return mapping.findForward("syddkManage");
	}

	/**
	 * 生源地贷款（增加）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syddkUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		XszzZgmsxyActionForm myForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		XsxxglService stuService = new XsxxglService();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		
		
		// 生源地贷款保存
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.saveSyddk(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}

		
		request.setAttribute("path", "zgmsxy_xszz.do?method=syddkManage");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("rs", stuInfo);
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("doType", "add");
		return mapping.findForward("syddkUpdate");
	}
	
	/**
	 * 生源地贷款(详细信息、修改)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward syddkOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		XszzZgmsxyActionForm myForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		String doType = request.getParameter("doType");
		String pkValue = request.getParameter("pkValue");
		
		myForm.setPkV(new String[]{pkValue});
		
		// 生源地贷款保存
		String message="";
		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.updateSyddk(myForm);
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
			doType="update";
		}

		request.setAttribute("path", "zgmsxy_xszz.do?method=syddkManage");
		FormModleCommon.commonRequestSet(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("rs", service.getOneSyddk(myForm));
		request.setAttribute("tableName", "view_xsjbxx");
		request.setAttribute("doType", doType);
		return mapping.findForward("syddkUpdate");
	}
	
	/**
	 * 国家助学贷款发放
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdkff(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		
		User user = getUser(request);
		RequestForm rForm = new RequestForm();
		XszzZgmsxyActionForm myForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		CommService commService=new CommService();

		String doType=request.getParameter("doType");
		
		String message="";
		
		if("del".equalsIgnoreCase(doType)){
			boolean flag=service.delZxdkFf(myForm);
			message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}
		
		// ===============通用设置=================
		CommSetting commSetting = new CommSetting();

		// 结果集名称
		String rsName = "rsArrList";
		commSetting.setRsName(rsName);

		// 是否需要checkbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// 显示的起始列
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// 显示列数
		String showNum = String.valueOf(service.getTopTr("gjzxdkff",rForm).size());
		commSetting.setShowNum(showNum);

		rForm.setCommSetting(commSetting);
		rForm.setRsArrList((ArrayList<String[]>)service.getZxdkFfList(myForm));
		commService.setRequestValue(rForm, user, request);
		// ===============通用设置 end ============
		
		
		request.setAttribute("path", "zgmsxy_xszz.do?method=gjzxdkff");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		//表头
		request.setAttribute("topTr", service.getTopTr("gjzxdkff",rForm));
		return mapping.findForward("gjzxdkff");
	}
	
	/**
	 * 学生申请-学生申请(国家助学贷款)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-5
	 */
	public ActionForward xssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzZgmsxyService service = new XszzZgmsxyService();
		DAO dao = DAO.getInstance();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String sfzh = dao.getOneRs("select sfzh from view_xsjbxx where xh=?",
				new String[] { xh }, "sfzh");
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String nd = Base.currNd;
		pkVal = pkVal.equalsIgnoreCase("") ? nd + sfzh : pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		if (!"".equalsIgnoreCase(pkVal)) {
			if (stuMap.size() == 0) {
				stuMap = service.getStudent(xh);// 得到学生信息
			}
		}

		if (StringUtils.isNotNull(sfzh)) {
			stuMap.put("sfzh", sfzh);
		}
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		//String sfksq = service.getGjzxdkSqQx(sUserType, userDep, xh);

		//if ("-1".equalsIgnoreCase(sfksq)) {
		//	String msg = "现在不在申请时间内！";
		//	request.setAttribute("yhInfo", msg);
		//	return new ActionForward("/yhInfo.do", false);
		//}
		String sfksq ="1";
		request.setAttribute("rs", stuMap);
		request.setAttribute("sfksq", sfksq);
		request.setAttribute("pkVal", pkVal);
		//菜单读写权限
		//request.setAttribute("path", "zgmsxy_xszz.do?method=xssq");
		//FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xssq_xssq");
	}
	/**
	 * 学生申请-保存学生申请(国家助学贷款)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-5
	 */
	public ActionForward xssqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		String doType = request.getParameter("doType");
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZgmsxyActionForm);
		XszzZgmsxyService service = new XszzZgmsxyService();
		boolean bJg = service.saveXsSqxx(gjzxdkModel, request);// 保存信息
		String isPass=(String)request.getAttribute("isPASS");
		if (bJg  && !"is".equalsIgnoreCase(isPass)) {
			request.setAttribute("ok", "ok");
		} else if(!"is".equalsIgnoreCase(isPass)){
			request.setAttribute("ok", "no");
		}
		
		String sfzh = gjzxdkModel.getSfzh();
		String nd = gjzxdkModel.getNd();
		String pkVal = nd + sfzh;
		
		String xh = gjzxdkModel.getXh();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXssqxx(pkVal);// 得到贷款详细信息(All)
		//stuMap = service.getStudent(xh);// 得到贷款详细信息（仅学生信息）
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xssq_xssqSave");
	}
	/**
	 * 学生申请审核(国家助学贷款)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-5
	 */
	public ActionForward xssqsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();

		HttpSession session = request.getSession();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		
		String userName = (String) session.getAttribute("userName");
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXssqxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXssqxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}
//		if ("qxsh".equalsIgnoreCase(queryModel.getGo())) {
//			service.modXssqxx(Base.chgNull(request.getParameter("cbVal"),
//					"!!splitOne!!", 1), "未审核", request);
//			queryModel.setGo("go");
//		}

		if ("xy".equalsIgnoreCase(userType)) {
			xszzZgmsxyActionForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			xszzZgmsxyActionForm.setXh(userName);
			String msg = "学生用户无权访问此页面！";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
			
		}

		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			xszzZgmsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		List<HashMap<String, String>> topList = service.getXssqTit();
		List<String[]> resList = service.getXssqshRes(xszzZgmsxyActionForm,
				queryModel, request);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgmsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||sfzh");
		request.setAttribute("currNd", Base.currNd);
		request.setAttribute("hForm", xszzZgmsxyActionForm);
		request.setAttribute("realTable", "xg_zxdk_sq");
		request.setAttribute("tableName", "view_xg_zxdk_sq");
		//菜单读写权限
		request.setAttribute("path", "zgmsxy_xszz.do?method=xssqsh");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xssqsh");
	}
	/**
	 * 学生申请-保存学生申请审核(国家助学贷款)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-5
	 */
	public ActionForward xssqshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		String doType = request.getParameter("doType");
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZgmsxyActionForm);
		XszzZgmsxyService service = new XszzZgmsxyService();
		boolean bJg = service.saveXsSqshxx(gjzxdkModel, request);// 保存信息
		String isPass=(String)request.getAttribute("isPASS");
		System.out.println(isPass);
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else{
			request.setAttribute("ok", "no");
		}
		
		String sfzh = gjzxdkModel.getSfzh();
		String nd = gjzxdkModel.getNd();
		String pkVal = nd + sfzh;
		
		String xh = gjzxdkModel.getXh();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		//stuMap = service.getXssqxx(pkVal);// 得到贷款详细信息
		stuMap = service.getStudent(xh);// 得到贷款详细信息
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xssqshOne");
	}
	/**
	 * 学生申请查询(国家助学贷款)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-5
	 */
	public ActionForward xssqcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();

		HttpSession session = request.getSession();
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userName = (String) session.getAttribute("userName");
		String userDep = session.getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXssqxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXssqxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXssqxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}

		if ("xy".equalsIgnoreCase(userType)) {
			xszzZgmsxyActionForm.setXydm(userDep);
		} else if ("stu".equalsIgnoreCase(userType)) {
			xszzZgmsxyActionForm.setXh(userName);
		}
		
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			//xszzZgmsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZgmsxyActionForm);
		List<HashMap<String, String>> topList = service.getXssqTit();
		List<String[]> resList = service.getXssqRes(xszzZgmsxyActionForm,
				queryModel, request);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgmsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||sfzh");
		request.setAttribute("currNd", Base.currNd);
		request.setAttribute("hForm", xszzZgmsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xg_zxdk_sq");
		request.setAttribute("tableName", "view_xg_zxdk_sq");
		//菜单读写权限
		request.setAttribute("path", "zgmsxy_xszz.do?method=xssqcx");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("xssqcx");
	}
	/**
	 * 学生申请-学生申请审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-5
	 */
	public ActionForward xssqshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXssqxx(pkVal);

		xszzZgmsxyActionForm.setYxsh(stuMap.get("yxsh"));
		xszzZgmsxyActionForm.setXxsh(stuMap.get("xxsh"));
		xszzZgmsxyActionForm.setYxshyj(stuMap.get("yxshyj"));
		xszzZgmsxyActionForm.setXxshyj(stuMap.get("xxshyj"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		String userName = request.getSession().getAttribute("userName").toString();
		String fdysql = "select count(*) num from fdybjb where zgh='"+userName+"'";
		int fdy =Integer.parseInt(dao.getOneRs(fdysql, new String[]{}, "num"));
		String fdyQx = fdy >0 ? "yes" : "no";
		String bzrsql = "select count(*) num from bzrbbb where zgh='"+userName+"'";
		int bzr =Integer.parseInt(dao.getOneRs(bzrsql, new String[]{}, "num"));
		String bzrQx = bzr >0 ? "yes" : "no";
		
		request.setAttribute("fdyQx", fdyQx);
		request.setAttribute("bzrQx", bzrQx);
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xssqshOne");
	}
	
	/**
	 * 学生申请-学生申请详细信息页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-5
	 */
	public ActionForward xssqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzZgmsxyActionForm xszzZgmsxyActionForm = (XszzZgmsxyActionForm) form;
		XszzZgmsxyService service = new XszzZgmsxyService();
		HttpSession session = request.getSession();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXssqxx(pkVal);

		xszzZgmsxyActionForm.setYxsh(stuMap.get("yxsh"));
		xszzZgmsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xssqView");
	}
	/**
	 * 学生申请-学生申请(国家助学贷款)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-5
	 */
	public ActionForward xssqMod(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzZgmsxyService service = new XszzZgmsxyService();
		DAO dao = DAO.getInstance();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String sfzh = dao.getOneRs("select sfzh from view_xsjbxx where xh=?",
				new String[] { xh }, "sfzh");
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String nd = Base.currNd;
		pkVal = pkVal.equalsIgnoreCase("") ? nd + sfzh : pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String doType = request.getParameter("doType");
		pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		stuMap = service.getXssqxx(pkVal);
		request.setAttribute("isPASS", "no");
		this.updateOperation(request, "xg_zxdk_sq");
		request.setAttribute("doType", doType);
		String sfksq ="1";
		request.setAttribute("rs", stuMap);
		request.setAttribute("sfksq", sfksq);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("result", true);
		request.setAttribute("doType", "modi");
		return mapping.findForward("xssq_xssqAdd");
	}
	/**
	 * 学生申请-学生申请(国家助学贷款)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * 
	 * @author honglin
	 * @date 2012-6-5
	 */
	public ActionForward xssqAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzZgmsxyService service = new XszzZgmsxyService();
		DAO dao = DAO.getInstance();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String sfzh = dao.getOneRs("select sfzh from view_xsjbxx where xh=?",
				new String[] { xh }, "sfzh");
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		String nd = Base.currNd;
		pkVal = pkVal.equalsIgnoreCase("") ? nd + sfzh : pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		if (!"".equalsIgnoreCase(pkVal)) {
			if (stuMap.size() == 0) {
				stuMap = service.getStudent(xh);// 得到学生信息
			}
		}

		if (StringUtils.isNotNull(sfzh)) {
			stuMap.put("sfzh", sfzh);
		}
		String doType = request.getParameter("doType");
		request.setAttribute("doType", doType);
		String sfksq ="1";
		request.setAttribute("rs", stuMap);
		request.setAttribute("sfksq", sfksq);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("doType", "add");
		//菜单读写权限
		//request.setAttribute("path", "zgmsxy_xszz.do?method=xssq");
		//FormModleCommon.commonRequestSet(request);
		
		return mapping.findForward("xssq_xssqAdd");
	}
}
