package xgxt.xszz.ynys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.ToolClass;
import xgxt.utils.String.NullStringException;
import xgxt.utils.String.StringUtils;
import xgxt.utils.form.FormUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;
import xgxt.xszz.XszzService;

public class XszzYnysAction extends DispatchAction {
	/**
	 * 国家励志奖学金单个处理
	 * 
	 * @throws NullStringException
	 */
	public ActionForward gjlzjxjpre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws NullStringException {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		FormUtils.CheckUserAccessPower(request); // Check
		// User
		// Power
		XszzYnysActionForm myForm = (XszzYnysActionForm) form;
		XszzYnysService service = XszzYnysService.getInstance();
		String tableName = "view_xszz_ynys_gjlzjxj";
		String realTable = "xszz_ynys_gjlzjxj";
		myForm.do_Gjlzjxj_GBK();
		if (StringUtils.isEqual(userType, "stu")
				|| StringUtils.isEqual(userType, "student")) {
			String[] stuT = dao
					.getOneRs(
							"select xh,xm,xb,sfzh,zzmmmc,mzmc,xymc,zymc,bjmc from view_stu_details where xh=?",
							new String[] { userName }, new String[] { "xh",
									"xm", "xb", "sfzh", "zzmmmc", "mzmc",
									"xymc", "zymc", "bjmc" });
			myForm.setXh(userName);
			if (stuT != null) {
				myForm.setXm(stuT[1]);
				myForm.setXb(stuT[2]);
				myForm.setSfzh(stuT[3]);
				myForm.setZzmmmc(stuT[4]);
				myForm.setMzmc(stuT[5]);
				myForm.setXymc(stuT[6]);
				myForm.setZymc(stuT[7]);
				myForm.setBjmc(stuT[8]);
			}
		}
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if (!"".equalsIgnoreCase(pkVal)) {
			myForm.setNd(pkVal);
		}
		String pk = "nd||xh";
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName,
				userName, userType, pk);
		this.setXyUserWritAbleDisabled(myForm, userType, userDep, request);
		request.setAttribute("xxmc", service.getXXmc());// set XXmc
		request.setAttribute("checkList", service.getCheckList(userType, 3));
		return mapping.findForward("gjlzjxj_queryOne");
	}

	/** 国家励志奖学金维护 */
	public ActionForward gjlzjxjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();		
		FormUtils.CheckUserAccessPower(request);
		XszzYnysActionForm myForm = (XszzYnysActionForm) form;
		XszzYnysService service = XszzYnysService.getInstance();
		String tableName = "view_xszz_ynys_gjlzjxj";
		String realTable = "xszz_ynys_gjlzjxj";
		String pkColumn = "nd||xh"; // 主键
		String title = "当前所在位置：学生资助 - 申请结果查询 - 国家励志奖学金查询";
		String sqms = request.getParameter("sqms");
		myForm.do_Gjlzjxj_GBK();
		
		if(!"true".equalsIgnoreCase(sqms)){
			title = "当前所在位置：学生资助 - 审核 - 国家励志奖学金审核";
		}
		//学院用户默认学院代码为用户部门代码
		appendDefaultValue(request, myForm);
		
		List<String[]> rs = new ArrayList<String[]>();
		rs = this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName,
				request, service, userType);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType,
				userDep, request); // writAble
		this.setRequestAttribute(request, writeAble, realTable, tableName,
				userType, rs, myForm);
		request.setAttribute("checkList", service.getCheckList(userType, 3));
		request.setAttribute("title", title);
		return mapping.findForward("data_gjlzjxj");
	}

	/**
	 * 省政府励志奖学金单个处理
	 * 
	 * @throws NullStringException
	 */
	public ActionForward szflzjxjpre(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws NullStringException {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		FormUtils.CheckUserAccessPower(request);
		XszzYnysActionForm myForm = (XszzYnysActionForm) form;
		XszzYnysService service = XszzYnysService.getInstance();
		String tableName = "view_xszz_ynys_szflzjxj";
		String realTable = "xszz_ynys_szflzjxj";
		
		myForm.do_Gjlzjxj_GBK();// 字段与国家励志奖学金一样的
		if (StringUtils.isEqual(userType, "stu")
				|| StringUtils.isEqual(userType, "student")) {
			String[] stuT = dao
					.getOneRs(
							"select xh,xm,xb,sfzh,zzmmmc,mzmc,xymc,zymc,bjmc from view_stu_details where xh=?",
							new String[] { userName }, new String[] { "xh",
									"xm", "xb", "sfzh", "zzmmmc", "mzmc",
									"xymc", "zymc", "bjmc" });
			myForm.setXh(userName);
			if (stuT != null) {
				myForm.setXm(stuT[1]);
				myForm.setXb(stuT[2]);
				myForm.setSfzh(stuT[3]);
				myForm.setZzmmmc(stuT[4]);
				myForm.setMzmc(stuT[5]);
				myForm.setXymc(stuT[6]);
				myForm.setZymc(stuT[7]);
				myForm.setBjmc(stuT[8]);
			}
		}
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if (!"".equalsIgnoreCase(pkVal)) {
			myForm.setNd(pkVal);
		}
		String pk = "nd||xh";
		this.doTypeRequest_SQ(myForm, request, service, realTable, tableName,
				userName, userType, pk);
		request.setAttribute("xxmc", service.getXXmc());// set XXmc
		request.setAttribute("checkList", service.getCheckList(userType, 3));
		this.setXyUserWritAbleDisabled(myForm, userType, userDep, request);
		request.setAttribute("sqms",request.getParameter("sqms"));
		return mapping.findForward("szflzjxj_queryOne");
	}

	/** 省政府励志奖学金维护 */
	public ActionForward szflzjxjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userType = session.getAttribute("userType").toString();
		FormUtils.CheckUserAccessPower(request);
		XszzYnysActionForm myForm = (XszzYnysActionForm) form;
		XszzYnysService service = XszzYnysService.getInstance();
		String tableName = "view_xszz_ynys_szflzjxj";
		String realTable = "xszz_ynys_szflzjxj";
		String pkColumn = "nd||xh"; // 主键
		String title = "当前所在位置：学生资助 - 申请结果查询 - 省政府励志奖学金查询";
		String sqms = request.getParameter("sqms");
		myForm.do_Gjlzjxj_GBK();
		
		if(!"true".equalsIgnoreCase(sqms)){
			title = "当前所在位置：学生资助 - 审核 - 省政府励志奖学金审核";
		}
		
		//学院用户默认学院代码为用户部门代码
		appendDefaultValue(request, myForm);
		
		List<String[]> rs = new ArrayList<String[]>();
		rs = this.doTypeRequest_WH(myForm, rs, pkColumn, realTable, tableName,
				request, service, userType);
		String writeAble = this.setXyUserWritAbleDisabled(myForm, userType,
				userDep, request); // writAble
		this.setRequestAttribute(request, writeAble, realTable, tableName,
				userType, rs, myForm);
		request.setAttribute("checkList", service.getCheckList(userType, 3));
		request.setAttribute("title", title);
		return mapping.findForward("data_szflzjxj");
	}
	
	/**
	 * 学院用户默认学院代码为用户部门
	 * @param request
	 * @param model
	 * @author lr
	 * */
	private void appendDefaultValue(HttpServletRequest request, 
			                        XszzYnysActionForm model){
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		String userDep = session.getAttribute("userDep").toString();
		//非辅导员的学院用户
		if("xy".equalsIgnoreCase(userType) && "false".equalsIgnoreCase(isFdy)){
			model.setXydm(userDep);
		}
	}
	/** 数据导出 */
	public ActionForward common_exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String userType = request.getSession().getAttribute("userType")
				.toString();
		XszzYnysService service = XszzYnysService.getInstance();
		XszzYnysActionForm myForm = (XszzYnysActionForm) form;
		service.getCommonExp(myForm, response, request, myForm.getTableName(),
				userType);
		return mapping.findForward("common_exp");
	}

	/**
	 * *******************************************private
	 * method***********************************
	 */
	/** 维护数据 */
	private List<String[]> doTypeRequest_WH(XszzYnysActionForm myForm,
			List<String[]> rs, String pkColumn, String realTable,
			String tableName, HttpServletRequest request,
			XszzYnysService service, String userType) {
		String doType = myForm.getDoType();
		try {
			// 表明不是从stu_result_query.do公共页面跳转过来的 如果是跳转过来的话，那么doType=query
			if (StringUtils.isNotNull(doType) && (!doType.equals("query"))) {
				if ("del".equals(doType)) {
					String pk = request.getParameter("cbVal");
					request.setAttribute("ok", myForm.setResult(service
							.batchDelRecord(pk, realTable, pkColumn)));// 提示信息
				}
				rs = service.getCommonVector(myForm, tableName, userType);
				request.setAttribute("topTr", myForm.getColumnCN()); // 返回表头信息
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}

	/** ###################################################################################################### */
	/** 单个数据 */
	private void doTypeRequest_SQ(XszzYnysActionForm myForm,
			HttpServletRequest request, XszzYnysService service,
			String realTable, String tableName, String userName,
			String userType, String pk) {
		String doType = myForm.getDoType();
		try {
			if (StringUtils.isNull(request.getParameter("getXh"))) { // 得到学生的信息（判断是否从子窗口中跳转）这里表示不是
				if ("add".equals(doType)) {
					request.setAttribute("ok", myForm.setResult(service
							.saveCommonInfo(myForm, realTable, pk)));
					request.setAttribute("sqms", "true");
				} else if ("view".equals(doType) || "modi".equals(doType)) {
					service.getCommonAllInfo(myForm, tableName, pk);
				}
				request.setAttribute("rs", myForm);
			} else {
				// request 中的rs是从 DataManAction stuInfoXX 中得到的
				// 这里要将申请模式设置为 true
				request.setAttribute("sqms", "true");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** request setAttributes */
	private void setRequestAttribute(HttpServletRequest request,
			String writeAble, String realTable, String tableName,
			String userType, List<String[]> rs, XszzYnysActionForm myForm) {
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("realTable", realTable);
		request.setAttribute("tableName", tableName);
		// request.setAttribute("userType", userType);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", (rs == null) == true ? "0" : rs.size());
		request.setAttribute("form", myForm);
	}

	/**
	 * set 学院用户没有操作 增删改查 和导入导出的权限
	 * 
	 * @param myForm
	 * @param userType
	 * @param userDep
	 * @param request
	 * @return
	 */
	private String setXyUserWritAbleDisabled(ActionForm myForm,
			String userType, String userDep, HttpServletRequest request) {
		String writeAble = "";// 写权限 假定学院没有操作权限
		String tempUserType = "";
		if (userType.equalsIgnoreCase("xx") || userType.equals("admin")) { // 学校用户或管理员
			writeAble = "yes";
			tempUserType = "xx";
		} else {
			if (userType.equalsIgnoreCase("xy")) { // 学院用户
				writeAble = "yes";
				try {
					FormUtils.setOneProperty("xydm", userDep, myForm);
				} catch (Exception e) {
					e.printStackTrace();
				}
				tempUserType = request.getSession().getAttribute("isFdy")
						.toString().equals("true") ? "fdy" : "xy";// 辅导员
			} else {
				writeAble = "no";
				tempUserType = "stu"; // 学生
			}
		}
		request.setAttribute("userType", tempUserType); // 将用户的类型保存到request中
		return writeAble;
	}

	/**
	 * 学费缓缴申请页面 xfhjsq ----- 学费缓缴申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzYnysService service = XszzYnysService.getInstance();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();// SESSION中获取用户部门
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getXfhjxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("nd", Base.currNd);// 当前年度
		request.setAttribute("sfksq", service.getXfhjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhjsq");
	}

	/**
	 * 保存学费缓缴申请信息 xfhjsqSave ---- 保存学费缓缴申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzYnysActionForm xszzform = (XszzYnysActionForm) form;
		XfhjModel xfhjModel = new XfhjModel();
		BeanUtils.copyProperties(xfhjModel, xszzform);
		XszzYnysService service = XszzYnysService.getInstance();
		boolean bJg = service.saveXfhjSqxx(xfhjModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = xfhjModel.getXh();
		String nd = xfhjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getXfhjxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);// 当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhjsqSave");
	}

	/**
	 * 学费缓缴申请表页面 xfhjsqb ----- 学费缓缴申请表页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzYnysActionForm xszzform = (XszzYnysActionForm) form;
		XszzYnysService service = XszzYnysService.getInstance();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		XfhjModel xfhjModel = new XfhjModel();
		BeanUtils.copyProperties(xfhjModel, xszzform);
		stuMap = service.getXfhjSqb(xfhjModel, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("xfhjsqb");
	}

	/**
	 * 学费缓缴审核页面 xfhjsh ----- 学费缓缴审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzYnysService service = XszzYnysService.getInstance();
		XszzYnysActionForm xszzform = (XszzYnysActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXfhjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfhjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfhjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}
		if(StringUtils.isNotNull(xszzform.getShlb())){
			if("xy".equalsIgnoreCase(userType)){
				xszzform.setXysh(xszzform.getShlb());
			}else{
				xszzform.setXxsh(xszzform.getShlb());
			}
		}

		if (userType.equalsIgnoreCase("xy")) {
			xszzform.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			xszzform.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzform);
		List<HashMap<String, String>> topList = service.getXfhjTit();
		List<String[]> resList = service.getXfhjRes(queryModel, request);
		String xh = DealString.toGBK(xszzform.getXh());
		xszzform.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzform);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzform);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_ynys_xfhj");
		request.setAttribute("tableName", "view_xszz_ynys_xfhj");
		request.setAttribute("checkList", service.getCheckList(userType, 3));//审核列表
		return mapping.findForward("xfhjsh");
	}

	/**
	 * 学费缓缴信息导出 xfhjExp ----- 学费缓缴信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzYnysService service = XszzYnysService.getInstance();
		XszzYnysActionForm xszzForm = (XszzYnysActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, xszzForm);
		service.getXfhjExp(queryModel, response, request);
		return mapping.findForward("xfhjExp");
	}

	/**
	 * 学费缓缴审核详细页面 xfhjshOne ----- 学费缓缴审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzYnysService service = XszzYnysService.getInstance();
		XszzYnysActionForm xszzForm = (XszzYnysActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String userName = session.getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfhjxx(pkVal);
		xszzForm.setXysh(stuMap.get("xysh"));
		xszzForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "admin");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhjshOne");
	}

	/**
	 * 保存学费缓缴审核信息 xfhjshSave ---- 保存学费缓缴审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfhjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		XszzYnysActionForm xszzForm = (XszzYnysActionForm) form;
		XfhjModel xfhjModel = new XfhjModel();
		BeanUtils.copyProperties(xfhjModel, xszzForm);
		XszzYnysService service = XszzYnysService.getInstance();
		boolean bJg = service.saveXfhjShxx(xfhjModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = xfhjModel.getXh();
		String nd = xfhjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfhjxx(pkVal);
		xszzForm.setXysh(stuMap.get("xysh"));
		xszzForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhjshSave");
	}

	public ActionForward jzxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String userType;

		// String userDep;

		String sUName;

		String logMsg;
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		userType = session.getAttribute("userType").toString();
		sUName = session.getAttribute("userName").toString();
		String title = "学生资助 - 云南艺术学院进步鼓励奖助学金申请";
		XszzYnysActionForm xszzForm = (XszzYnysActionForm) form;

		String xxdm = StandardOperation.getXxdm();
		String doType = request.getParameter("doType");// 操作类型

		String titName = request.getParameter("lx");

		String[] colName1 = null;
		String jzxjmc = "";
		String sfksq = "-1";// 国家奖学金是否可申请参数，-1表示不可以，1表示可以。

		String[] pages = null;
		String[] titNames = null;

		if ("jbgl".equalsIgnoreCase(titName)) {
			pages = new String[] { "云南艺术学院进步鼓励奖助学金" };
			titNames = new String[] { "jbgl" };			
		} else if ("xy".equalsIgnoreCase(titName)) {
			pages = new String[] { "云南艺术学院奖学金" };
			titNames = new String[] { "xy" };
			title = "学生资助 - 云南艺术学院奖学金申请";
		} else if ("jj".equalsIgnoreCase(titName)) {
			pages = new String[] { "云南艺术学院救济助学金" };
			titNames = new String[] { "jj" };
			title = "学生资助 - 云南艺术学院救济助学金申请";
		} else if("gjzxdkjbzx".equalsIgnoreCase(titName)){
			pages = new String[] { "云南艺术学院国家助学贷款奖补专项资金助学金" };
			titNames = new String[] { "gjzxdkjbzx" };
			title = "学生资助 - 云南艺术学院国家助学贷款奖补专项资金助学金申请";
		}

		String rightNow = (dao.getOneRs(
				"select to_char(sysdate,'yyyy-mm-dd') sdate from dual",
				new String[] {}, new String[] { "sdate" }))[0];

		request.setAttribute("xxdm", xxdm);
		List pagesList = dao.arrayToList(titNames, pages);
		request.setAttribute("pages", pagesList);

		String sql = "";
		String sql1 = "";// 用于获取设置的申请时间
		String[] outString = new String[] {};
		sql = "select xh,nd,csny,rxny,byny,lxdh,jzxjzwmc,xm,sfzh,xb,bjmc,xy,xmc,xydm,zydm,bjdm,nj,mzmc,zzmmmc,kh,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdz,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdz,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdz,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz,sfkns,sqsj,jzxjmc,jlxx,hkrs,jtyzsr,jtrjsr,jtsrly,jtzz,sqly,xyshyj,xxshyj,yzbm,radjthk,xxmc,xysh,xxsh,gkfs,bygz,kc1_mc,kc1_cj,kc2_mc,kc2_cj,kc3_mc,kc3_cj,kc4_mc,kc4_cj,kc5_mc,kc5_cj,kc6_mc,kc6_cj,kc7_mc,kc7_cj,kc8_mc,kc8_cj,kc9_mc,kc9_cj,kc10_mc,kc10_cj,kc11_mc,kc11_cj,kc12_mc,kc12_cj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyshrxm,xyzzfzryj,fdysh,fdyshyj,sxncj,xsjbqkjj "
				+ "from view_xsjxjzxjsq where 1=2";
		outString = dao.getColumnName(sql);// 获得view_xsjxjzxjsq的列名数组
		String[] outValue = null;
		HashMap<String, String> map = new HashMap<String, String>();
		String nd = Base.currNd;
		String xh = "";

		if (userType.equalsIgnoreCase("stu")) {
			xh = sUName;
			String[] jxjksjssj = null;
			String gnmkmc = "";
			
			if (titName.equalsIgnoreCase("jbgl")) {
				gnmkmc = "云南艺术学院进步鼓励奖助学金";
			} else if (titName.equalsIgnoreCase("xy")) {
				gnmkmc = "云南艺术学院奖学金";
			} else if (titName.equalsIgnoreCase("jj")) {
				gnmkmc = "云南艺术学院救济助学金";
			}else if("gjzxdkjbzx".equalsIgnoreCase(titName)){
				gnmkmc = "云南艺术学院国家助学贷款奖补专项资金助学金";
			}
			
			sql1 = "select a.kssj,a.jssj from NBLG_SJB a,"
					+ "view_xsjbxx b where a.xydm=b.xydm and a.xmmc=? and b.xh=? ";
			jxjksjssj = dao.getOneRs(sql1, new String[] { gnmkmc, xh },
					new String[] { "kssj", "jssj" });
			if (jxjksjssj != null
					&& jxjksjssj[0].compareToIgnoreCase(rightNow) <= 0
					&& jxjksjssj[1].compareToIgnoreCase(rightNow) >= 0) {// /在申请时间范围内
				sfksq = "1";// /可以进行申请
				request.setAttribute("sfksq", sfksq);
				if (doType != null && doType.equalsIgnoreCase("add")) {// /学生填写申请
					String csny = DealString.toGBK(request.getParameter("csny")
							.toString());
					String rxny = DealString.toGBK(request.getParameter("rxny")
							.toString());
					String jlxx = DealString.toGBK(request.getParameter("jlxx")
							.toString());
					String radJthk = DealString.toGBK(request.getParameter(
							"radJthk").toString());
					String hkrs = DealString.toGBK(request.getParameter("hkrs")
							.toString());
					String jtyzsr = DealString.toGBK(request.getParameter(
							"jtyzsr").toString());
					String jtrjsr = DealString.toGBK(request.getParameter(
							"jtrjsr").toString());
					String jtsrly = DealString.toGBK(request.getParameter(
							"jtsrly").toString());
					String yzbm = DealString.toGBK(request.getParameter("yzbm")
							.toString());
					String jtzz = DealString.toGBK(request.getParameter("jtzz")
							.toString());
					String JTCY1_XM = DealString.toGBK(request.getParameter(
							"JTCY1_XM").toString());
					String JTCY1_nl = DealString.toGBK(request.getParameter(
							"JTCY1_nl").toString());
					String JTCY1_GX = DealString.toGBK(request.getParameter(
							"JTCY1_GX").toString());
					String JTCY1_GZDZ = DealString.toGBK(request.getParameter(
							"JTCY1_GZDZ").toString());
					String JTCY2_XM = DealString.toGBK(request.getParameter(
							"JTCY2_XM").toString());
					String JTCY2_nl = DealString.toGBK(request.getParameter(
							"JTCY2_nl").toString());
					String JTCY2_GX = DealString.toGBK(request.getParameter(
							"JTCY2_GX").toString());
					String JTCY2_GZDZ = DealString.toGBK(request.getParameter(
							"JTCY2_GZDZ").toString());
					String JTCY3_XM = DealString.toGBK(request.getParameter(
							"JTCY3_XM").toString());
					String JTCY3_nl = DealString.toGBK(request.getParameter(
							"JTCY3_nl").toString());
					String JTCY3_GX = DealString.toGBK(request.getParameter(
							"JTCY3_GX").toString());
					String JTCY3_GZDZ = DealString.toGBK(request.getParameter(
							"JTCY3_GZDZ").toString());
					String JTCY4_XM = DealString.toGBK(request.getParameter(
							"JTCY4_XM").toString());
					String JTCY4_nl = DealString.toGBK(request.getParameter(
							"JTCY4_nl").toString());
					String JTCY4_GX = DealString.toGBK(request.getParameter(
							"JTCY4_GX").toString());
					String JTCY4_GZDZ = DealString.toGBK(request.getParameter(
							"JTCY4_GZDZ").toString());
					String JTCY5_XM = DealString.toGBK(request.getParameter(
							"JTCY5_XM").toString());
					String JTCY5_nl = DealString.toGBK(request.getParameter(
							"JTCY5_nl").toString());
					String JTCY5_GX = DealString.toGBK(request.getParameter(
							"JTCY5_GX").toString());
					String JTCY5_GZDZ = DealString.toGBK(request.getParameter(
							"JTCY5_GZDZ").toString());
					String sqly = DealString.toGBK(request.getParameter("sqly")
							.toString());

					if (titName.equalsIgnoreCase("jbgl")) {
						jzxjmc = "云南艺术学院进步鼓励奖助学金";
					} else if (titName.equalsIgnoreCase("xy")) {
						jzxjmc = "云南艺术学院奖学金";
					} else if (titName.equalsIgnoreCase("jj")) {
						jzxjmc = "云南艺术学院救济助学金";
					} else if (titName.equalsIgnoreCase("gjzxdkjbzx")) {
						jzxjmc = "云南艺术学院国家助学贷款奖补专项资金助学金";
					}
					String pkVal = request.getParameter("pkVal");
					if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
						pkVal = DealString.toGBK(pkVal);
					} else {
						pkVal = xh + nd + titName;
					}
					sql = "select xxsh from xsjxjzxjsqb where lower(xh||nd||jzxjmc)=?";
					String[] temp = dao.getOneRs(sql, new String[] { pkVal },
							new String[] { "xxsh" });
					if ((temp != null)
							&& ("通过".equalsIgnoreCase(temp[0]) || "已通过"
									.equalsIgnoreCase(temp[0]))) {
						request.setAttribute("isPASS", "is");
					} else {
						StandardOperation.delete("xsjxjzxjsqb",
								"lower(xh||nd||jzxjmc)", pkVal, request);

						String[] valueForOut;

						String sxncj = DealString.toGBK(request.getParameter(
								"sxncj").toString());
						String lxdh = DealString.toGBK(request.getParameter(
								"lxdh").toString());
						String jb =request.getParameter("jb");
						colName1 = new String[] { "xh", "csny", "rxny", "jlxx",
								"radJthk", "hkrs", "jtyzsr", "jtrjsr",
								"jtsrly", "yzbm", "jtzz", "JTCY1_XM",
								"JTCY1_nl", "JTCY1_GX", "JTCY1_GZDZ",
								"JTCY2_XM", "JTCY2_nl", "JTCY2_GX",
								"JTCY2_GZDZ", "JTCY3_XM", "JTCY3_nl",
								"JTCY3_GX", "JTCY3_GZDZ", "JTCY4_XM",
								"JTCY4_nl", "JTCY4_GX", "JTCY4_GZDZ",
								"JTCY5_XM", "JTCY5_nl", "JTCY5_GX",
								"JTCY5_GZDZ", "sqly", "JZXJMC", "jzxjzwmc",
								"nd", "sxncj", "lxdh", "jb" };

						valueForOut = new String[] { xh, csny, rxny, jlxx,
								radJthk, hkrs, jtyzsr, jtrjsr, jtsrly, yzbm,
								jtzz, JTCY1_XM, JTCY1_nl, JTCY1_GX, JTCY1_GZDZ,
								JTCY2_XM, JTCY2_nl, JTCY2_GX, JTCY2_GZDZ,
								JTCY3_XM, JTCY3_nl, JTCY3_GX, JTCY3_GZDZ,
								JTCY4_XM, JTCY4_nl, JTCY4_GX, JTCY4_GZDZ,
								JTCY5_XM, JTCY5_nl, JTCY5_GX, JTCY5_GZDZ, sqly,
								titName, jzxjmc, nd, sxncj, lxdh, jb };

						boolean ok = false;
						ok = StandardOperation.insert("xsjxjzxjsqb", colName1,
								valueForOut, request);
						if (ok) {
							logMsg = "申请" + titName;
							Base.log(request, logMsg, sUName);
							request.setAttribute("ok", "ok");
						} else {
							request.setAttribute("ok", "no");
						}
					}
				}
			} else {// 不在申请时间范围内
				sfksq = "-1";
				request.setAttribute("sfksq", sfksq);// 不能申请
			}
		} else if (!(userType.equalsIgnoreCase("stu"))) {
			sfksq = "1";// /可以进行申请
			request.setAttribute("sfksq", sfksq);
			xh = xszzForm.getXh();
			if (doType != null && doType.equalsIgnoreCase("add")) {
				xh = DealString.toGBK(request.getParameter("xh").toString());
				String csny = DealString.toGBK(request.getParameter("csny")
						.toString());
				String rxny = DealString.toGBK(request.getParameter("rxny")
						.toString());
				String jlxx = DealString.toGBK(request.getParameter("jlxx")
						.toString());
				String radJthk = DealString.toGBK(request.getParameter(
						"radJthk").toString());
				String hkrs = DealString.toGBK(request.getParameter("hkrs")
						.toString());
				String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr")
						.toString());
				String jtrjsr = DealString.toGBK(request.getParameter("jtrjsr")
						.toString());
				String jtsrly = DealString.toGBK(request.getParameter("jtsrly")
						.toString());
				String yzbm = DealString.toGBK(request.getParameter("yzbm")
						.toString());
				String jtzz = DealString.toGBK(request.getParameter("jtzz")
						.toString());
				String JTCY1_XM = DealString.toGBK(request.getParameter(
						"JTCY1_XM").toString());
				String JTCY1_nl = DealString.toGBK(request.getParameter(
						"JTCY1_nl").toString());
				String JTCY1_GX = DealString.toGBK(request.getParameter(
						"JTCY1_GX").toString());
				String JTCY1_GZDZ = DealString.toGBK(request.getParameter(
						"JTCY1_GZDZ").toString());
				String JTCY2_XM = DealString.toGBK(request.getParameter(
						"JTCY2_XM").toString());
				String JTCY2_nl = DealString.toGBK(request.getParameter(
						"JTCY2_nl").toString());
				String JTCY2_GX = DealString.toGBK(request.getParameter(
						"JTCY2_GX").toString());
				String JTCY2_GZDZ = DealString.toGBK(request.getParameter(
						"JTCY2_GZDZ").toString());
				String JTCY3_XM = DealString.toGBK(request.getParameter(
						"JTCY3_XM").toString());
				String JTCY3_nl = DealString.toGBK(request.getParameter(
						"JTCY3_nl").toString());
				String JTCY3_GX = DealString.toGBK(request.getParameter(
						"JTCY3_GX").toString());
				String JTCY3_GZDZ = DealString.toGBK(request.getParameter(
						"JTCY3_GZDZ").toString());
				String JTCY4_XM = DealString.toGBK(request.getParameter(
						"JTCY4_XM").toString());
				String JTCY4_nl = DealString.toGBK(request.getParameter(
						"JTCY4_nl").toString());
				String JTCY4_GX = DealString.toGBK(request.getParameter(
						"JTCY4_GX").toString());
				String JTCY4_GZDZ = DealString.toGBK(request.getParameter(
						"JTCY4_GZDZ").toString());
				String JTCY5_XM = DealString.toGBK(request.getParameter(
						"JTCY5_XM").toString());
				String JTCY5_nl = DealString.toGBK(request.getParameter(
						"JTCY5_nl").toString());
				String JTCY5_GX = DealString.toGBK(request.getParameter(
						"JTCY5_GX").toString());
				String JTCY5_GZDZ = DealString.toGBK(request.getParameter(
						"JTCY5_GZDZ").toString());
				String sqly = DealString.toGBK(request.getParameter("sqly")
						.toString());

				if (titName.equalsIgnoreCase("jbgl")) {
					jzxjmc = "云南艺术学院进步鼓励奖助学金";
				} else if (titName.equalsIgnoreCase("xy")) {
					jzxjmc = "云南艺术学院奖学金";
				} else if (titName.equalsIgnoreCase("jj")) {
					jzxjmc = "云南艺术学院救济助学金";
				} else if (titName.equalsIgnoreCase("gjzxdkjbzx")) {
					jzxjmc = "云南艺术学院国家助学贷款奖补专项资金助学金";
				} 

				String pkVal = request.getParameter("pkVal");
				if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
					pkVal = DealString.toGBK(pkVal);
				} else {
					pkVal = xh + nd + titName;
				}
				sql = "select xxsh from xsjxjzxjsqb where lower(xh||nd||jzxjmc)=?";
				String[] temp = dao.getOneRs(sql, new String[] { pkVal },
						new String[] { "xxsh" });
				if ((temp != null)
						&& ("通过".equalsIgnoreCase(temp[0]) || "已通过"
								.equalsIgnoreCase(temp[0]))) {
					request.setAttribute("isPASS", "is");
				} else {
					StandardOperation.delete("xsjxjzxjsqb",
							"lower(xh||nd||jzxjmc)", pkVal, request);

					String[] valueForOut;

					String sxncj = DealString.toGBK(request.getParameter(
							"sxncj").toString());
					String lxdh = DealString.toGBK(request.getParameter("lxdh")
							.toString());

					String jb = request.getParameter("jb");
					colName1 = new String[] { "xh", "csny", "rxny", "jlxx",
							"radJthk", "hkrs", "jtyzsr", "jtrjsr", "jtsrly",
							"yzbm", "jtzz", "JTCY1_XM", "JTCY1_nl", "JTCY1_GX",
							"JTCY1_GZDZ", "JTCY2_XM", "JTCY2_nl", "JTCY2_GX",
							"JTCY2_GZDZ", "JTCY3_XM", "JTCY3_nl", "JTCY3_GX",
							"JTCY3_GZDZ", "JTCY4_XM", "JTCY4_nl", "JTCY4_GX",
							"JTCY4_GZDZ", "JTCY5_XM", "JTCY5_nl", "JTCY5_GX",
							"JTCY5_GZDZ", "sqly", "JZXJMC", "jzxjzwmc", "nd",
							"sxncj", "lxdh", "jb" };

					valueForOut = new String[] { xh, csny, rxny, jlxx, radJthk,
							hkrs, jtyzsr, jtrjsr, jtsrly, yzbm, jtzz, JTCY1_XM,
							JTCY1_nl, JTCY1_GX, JTCY1_GZDZ, JTCY2_XM, JTCY2_nl,
							JTCY2_GX, JTCY2_GZDZ, JTCY3_XM, JTCY3_nl, JTCY3_GX,
							JTCY3_GZDZ, JTCY4_XM, JTCY4_nl, JTCY4_GX,
							JTCY4_GZDZ, JTCY5_XM, JTCY5_nl, JTCY5_GX,
							JTCY5_GZDZ, sqly, titName, jzxjmc, nd, sxncj, lxdh,
							jb };

					boolean ok = false;
					ok = StandardOperation.insert("xsjxjzxjsqb", colName1,
							valueForOut, request);
					if (ok) {
						logMsg = "申请" + titName;
						Base.log(request, logMsg, sUName);
						request.setAttribute("ok", "ok");
					} else {
						request.setAttribute("ok", "no");
					}
				}
			}
		}

		String pkVal = request.getParameter("pkVal");
		if ((null != pkVal) && (!"".equalsIgnoreCase(pkVal))) {
			pkVal = DealString.toGBK(pkVal);
		} else {
			pkVal = xh + nd + titName;
		}

		sql = "select xh,nd,csny,rxny,byny,lxdh,jzxjzwmc,xm,sfzh,xb,bjmc,xy,xmc,xydm,"
				+ " zydm,bjdm,nj,mzmc,zzmmmc,jb,kh,JTCY1_XM,JTCY1_NL,JTCY1_GX,JTCY1_GZDZ,JTCY2_XM,"
				+ " JTCY2_NL,JTCY2_GX,JTCY2_GZDZ,JTCY3_XM,JTCY3_NL,JTCY3_GX,JTCY3_GZDZ,JTCY4_XM,JTCY4_NL,"
				+ " JTCY4_GX,JTCY4_GZDZ,JTCY5_XM,JTCY5_NL,JTCY5_GX,JTCY5_GZDZ,sfkns,sqsj,jzxjmc,jlxx,hkrs,"
				+ " jtyzsr,jtrjsr,jtsrly,jtzz,sqly,xyshyj,xxshyj,yzbm,radJthk,xxmc,xysh,xxsh,gkfs,bygz,kc1_mc,"
				+ " kc1_cj,kc2_mc,kc2_cj,kc3_mc,kc3_cj,kc4_mc,kc4_cj,kc5_mc,kc5_cj,kc6_mc,kc6_cj,kc7_mc,kc7_cj,"
				+ " kc8_mc,kc8_cj,kc9_mc,kc9_cj,kc10_mc,kc10_cj,kc11_mc,kc11_cj,kc12_mc,kc12_cj,zzdjdm,zzdjmc,"
				+ " zzdjje,xyshryhm,xyshrxm,xyzzfzryj,fdysh,fdyshyj,sxncj,xsjbqkjj "
				+ "from view_xsjxjzxjsq where lower(xh||nd||jzxjmc)=?";
		
		outString = new String[] { "xh", "nd", "csny", "rxny", "byny", "lxdh",
				"jzxjzwmc", "xm", "sfzh", "xb", "bjmc", "xy", "xmc", "xydm",
				"zydm", "bjdm", "nj", "mzmc", "zzmmmc", "kh", "JTCY1_XM",
				"JTCY1_nl", "JTCY1_GX", "JTCY1_GZDZ", "JTCY2_XM", "JTCY2_nl",
				"JTCY2_GX", "JTCY2_GZDZ", "JTCY3_XM", "JTCY3_nl", "JTCY3_GX",
				"JTCY3_GZDZ", "JTCY4_XM", "JTCY4_nl", "JTCY4_GX", "JTCY4_GZDZ",
				"JTCY5_XM", "JTCY5_nl", "JTCY5_GX", "JTCY5_GZDZ", "sfkns",
				"sqsj", "jzxjmc", "jlxx", "hkrs", "jtyzsr", "jtrjsr", "jtsrly",
				"jtzz", "sqly", "xyshyj", "xxshyj", "yzbm", "radJthk", "xxmc",
				"xysh", "xxsh", "gkfs", "bygz", "kc1_mc", "kc1_cj", "kc2_mc",
				"kc2_cj", "kc3_mc", "kc3_cj", "kc4_mc", "kc4_cj", "kc5_mc",
				"kc5_cj", "kc6_mc", "kc6_cj", "kc7_mc", "kc7_cj", "kc8_mc",
				"kc8_cj", "kc9_mc", "kc9_cj", "kc10_mc", "kc10_cj", "kc11_mc",
				"kc11_cj", "kc12_mc", "kc12_cj", "zzdjdm", "zzdjmc", "zzdjje",
				"xyshryhm", "xyshrxm", "xyzzfzryj", "fdysh", "fdyshyj",
				"sxncj", "xsjbqkjj", "jb" };

		outValue = dao.getOneRs(sql, new String[] { pkVal }, outString);

		if (null == outValue) {
			if (null != xh) {
				sql = "select a.*,(select b.kh from view_xsxxb b "
						+ "where a.xh=b.xh group by b.xh,b.kh) kh from (select v.xh,v.xm,v.xb,v.bjmc,v.xymc,"
						+ "q.MZMC mz,q.zzmmmc,q.sfzh,q.nj,v.zymc xi,x.xxmc,(select j.rxny from bks_xsjbxx j where j.xh=v.xh) rxny,q.csrq csny from "
						+ "view_xsjbxx v,view_stu_details q,xtszb x where "
						+ "v.xh = q.xh and v.xh=?) a";
				String[] colName = new String[] { "xh", "xm", "xb", "bjmc",
						"xymc", "mz", "zzmmmc", "sfzh", "xi", "xxmc", "rxny",
						"csny", "kh", "nj" };
				String[] outVal = dao.getOneRs(sql, new String[] { xh },
						colName);
				if (outVal == null) {

				} else {
					colName = new String[] { "xh", "xm", "xb", "bjmc", "xy",
							"mzmc", "zzmmmc", "sfzh", "xmc", "xxmc", "rxny",
							"csny", "kh", "nj" };
					for (int i = 0; i < colName.length; i++) {
						if (null != outVal[i]) {
							map.put(colName[i], outVal[i]);
						} else {
							map.put(colName[i], "");
						}
					}
				}
			}
		} else {

			int len1 = outString.length;
			int len2 = outValue.length;
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
		}

		request.setAttribute("jbList", dao.getJxjDj());
		request.setAttribute("title", title);
		request.setAttribute("titName", titName);
		request.setAttribute("rs", map);

		return mapping.findForward("jzxjsq");
	}

	public ActionForward jzxjprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String titleName = request.getParameter("titName");
		String sqlb = "";

		String xxmc = ToolClass.getXxmc();

		if ("jbgl".equalsIgnoreCase(titleName)) {
			sqlb = "云南艺术学院进步鼓励奖助学金";
		} else if ("xy".equalsIgnoreCase(titleName)) {
			sqlb = "云南艺术学院奖学金";
		} else if ("jj".equalsIgnoreCase(titleName)) {
			sqlb = "云南艺术学院救济助学金";
		} else if ("gjzxdkjbzx".equalsIgnoreCase(titleName)) {
			sqlb = "云南艺术学院国家助学贷款奖补专项资金助学金";
		}

		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String csny = DealString.toGBK(request.getParameter("csny"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String rxny = DealString.toGBK(request.getParameter("rxny"));
		String mzmc = DealString.toGBK(request.getParameter("mzmc"));
		xxmc = DealString.toGBK(request.getParameter("xxmc"));
		String xy = DealString.toGBK(request.getParameter("xy"));
		String xmc = DealString.toGBK(request.getParameter("xmc"));
		String bjmc = DealString.toGBK(request.getParameter("bjmc"));
		String jlxx = DealString.toGBK(request.getParameter("jlxx"));
		String radjthk = DealString.toGBK(request.getParameter("radJthk"));
		String hkrs = DealString.toGBK(request.getParameter("hkrs"));
		String jtyzsr = DealString.toGBK(request.getParameter("jtyzsr"));
		String jtrjsr = DealString.toGBK(request.getParameter("jtrjsr"));
		String jtsrly = DealString.toGBK(request.getParameter("jtsrly"));
		String yzbm = DealString.toGBK(request.getParameter("yzbm"));
		String jtzz = DealString.toGBK(request.getParameter("jtzz"));
		String jtcy1_xm = DealString.toGBK(request.getParameter("JTCY1_XM"));
		String jtcy1_nl = DealString.toGBK(request.getParameter("JTCY1_nl"));
		String jtcy1_gx = DealString.toGBK(request.getParameter("JTCY1_GX"));
		String jtcy1_gzdz = DealString
				.toGBK(request.getParameter("JTCY1_GZDZ"));
		String jtcy2_xm = DealString.toGBK(request.getParameter("JTCY2_XM"));
		String jtcy2_nl = DealString.toGBK(request.getParameter("JTCY2_nl"));
		String jtcy2_gx = DealString.toGBK(request.getParameter("JTCY2_GX"));
		String jtcy2_gzdz = DealString
				.toGBK(request.getParameter("JTCY2_GZDZ"));
		String jtcy3_xm = DealString.toGBK(request.getParameter("JTCY3_XM"));
		String jtcy3_nl = DealString.toGBK(request.getParameter("JTCY3_nl"));
		String jtcy3_gx = DealString.toGBK(request.getParameter("JTCY3_GX"));
		String jtcy3_gzdz = DealString
				.toGBK(request.getParameter("JTCY3_GZDZ"));
		String jtcy4_xm = DealString.toGBK(request.getParameter("JTCY4_XM"));
		String jtcy4_nl = DealString.toGBK(request.getParameter("JTCY4_nl"));
		String jtcy4_gx = DealString.toGBK(request.getParameter("JTCY4_GX"));
		String jtcy4_gzdz = DealString
				.toGBK(request.getParameter("JTCY4_GZDZ"));
		String jtcy5_xm = DealString.toGBK(request.getParameter("JTCY5_XM"));
		String jtcy5_nl = DealString.toGBK(request.getParameter("JTCY5_nl"));
		String jtcy5_gx = DealString.toGBK(request.getParameter("JTCY5_GX"));
		String jtcy5_gzdz = DealString
				.toGBK(request.getParameter("JTCY5_GZDZ"));
		String sqly = DealString.toGBK(request.getParameter("sqly"));
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));

		HashMap<String, String> map = new HashMap<String, String>();
		String[] outValue;
		String[] outString;

		DAO dao = DAO.getInstance();
		String lxdh = DealString.toGBK(request.getParameter("lxdh"));
		String sxncj = DealString.toGBK(request.getParameter("sxncj"));
		String zzmmmc = DealString.toGBK(request.getParameter("zzmmmc"));
		String sfzh = DealString.toGBK(request.getParameter("sfzh"));
		String jb = DealString.toGBK(request.getParameter("jb"));
		String jbmc = dao.getOneRs(
				"select mc jbmc from ynys_zzdjb where dm =?",
				new String[] { jb }, "jbmc");
		outValue = new String[] { xh, xm, rxny, xb, bjmc, xy, mzmc, xxmc,
				jtcy1_xm, jtcy1_nl, jtcy1_gx, jtcy1_gzdz, jtcy2_xm, jtcy2_nl,
				jtcy2_gx, jtcy2_gzdz, jtcy3_xm, jtcy3_nl, jtcy3_gx, jtcy3_gzdz,
				jtcy4_xm, jtcy4_nl, jtcy4_gx, jtcy4_gzdz, jtcy5_xm, jtcy5_nl,
				jtcy5_gx, jtcy5_gzdz, jlxx, hkrs, jtyzsr, jtrjsr, jtsrly, jtzz,
				sqly, xyshyj, xxshyj, yzbm, radjthk, csny, xmc, lxdh, sxncj,
				zzmmmc, sfzh, jbmc, nj };
		outString = new String[] { "xh", "xm", "rxny", "xb", "bjmc", "xy",
				"mzmc", "xxmc", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx",
				"jtcy1_gzdz", "jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdz",
				"jtcy3_xm", "jtcy3_nl", "jtcy3_gx", "jtcy3_gzdz", "jtcy4_xm",
				"jtcy4_nl", "jtcy4_gx", "jtcy4_gzdz", "jtcy5_xm", "jtcy5_nl",
				"jtcy5_gx", "jtcy5_gzdz", "jlxx", "hkrs", "jtyzsr", "jtrjsr",
				"jtsrly", "jtzz", "sqly", "xyshyj", "xxshyj", "yzbm",
				"radJthk", "csny", "xmc", "lxdh", "sxncj", "zzmmmc", "sfzh",
				"jbmc", "nj" };
		request.setAttribute("isYNYS", "is");

		// System.out.println(outString[35]+outValue[35]);
		for (int i = 0; i < outValue.length; i++) {
			if (outValue[i] != null && !(outValue[i].equals(""))) {
				// System.out.println("i="+i+" "+outString[i]);
				map.put(outString[i], outValue[i]);
			} else {
				map.put(outString[i], " ");
			}
		}
		request.setAttribute("sqlb", sqlb);
		request.setAttribute("rs", map);
		request.setAttribute("titleName", titleName);

		return mapping.findForward("jzxjprint");
	}

	public ActionForward jzxjsh(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 初始化页面，返回查询信息
		XszzYnysActionForm checkForm = (XszzYnysActionForm) form;
		XszzYnysService service = XszzYnysService.getInstance();
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		List<Object> rsExp = new ArrayList<Object>();
		String sqlExp = "";// sql语句
		String isQuery = request.getParameter("isQuery");
		if((null == isQuery) || ("".equalsIgnoreCase(isQuery))){
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql语句
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql条件
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// 查询信息源（多为视图名）
		String rsNum = "0";// 返回的记录数
		String realTable = "";// 数据源表
		String pk = "";// 数据源表主键（格式为“字段名||字段名||字段名”）
		String writeAble = "yes";// 写权限
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String shxm = DealString.toGBK(request.getParameter("shxm"));
		String xy = checkForm.getXydm();
		String xydm = request.getParameter("xydm");
		
		String titName = request.getParameter("lx");

		String dm = "";
		String mc = "";
		String tips = "";
		
		if ("jbgl".equalsIgnoreCase(titName)) {
			mc = "云南艺术学院进步鼓励奖助学金";
			dm = "jbgl";
		} else if ("xy".equalsIgnoreCase(titName)) {
			mc = "云南艺术学院奖学金";
			dm = "xy";
		} else if ("jj".equalsIgnoreCase(titName)) {
			mc = "云南艺术学院救济助学金";
			dm = "jj";
		} else if ("gjzxdkjbzx".equalsIgnoreCase(titName)) {
			mc = "云南艺术学院国家助学贷款奖补专项资金助学金";
			dm = "gjzxdkjbzx";
		}
		
		sql = "select '"+dm+"' shxmdm,'"+mc+"' shxmmc from dual";
		
		List shxmList = dao.getList(sql, new String[] {},
				new String[] { "shxmdm" , "shxmmc" });
		request.setAttribute("shxmList", shxmList);
		querry1.append(" and lower(JZXJMC)='");
		querry1.append(dm);
		querry1.append("' ");

		String xh = DealString.toGBK(checkForm.getXh());
		if (xydm != null && xy == null) {
			xy = xydm;
		}
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
			checkForm.setXydm(xy);
		}
		String zy = checkForm.getZydm();
		String bj = checkForm.getBjdm();
		String nj = checkForm.getNj();
		String xm = checkForm.getXm();
		String jb = checkForm.getJb();
		realTable = "xsjxjzxjsqb";
		pk = "lower(xh||nd||jzxjmc)";
		tableName = "view_xsjxjzxjsq";
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
//			if()
//			nd = Base.currNd;
		} else {
			nd = request.getParameter("nd");
		}
		if (isNull(nd)) {
		} else {
			querry.append(" and nd = '");
			querry.append(nd);
			querry.append("' ");
		}
		if (isNull(xy)) {
		} else {
			querry.append(" and xydm = '");
			querry.append(xy);
			querry.append("' ");
		}
		if (isNull(zy)) {
		} else {
			querry.append(" and zydm = '");
			querry.append(zy);
			querry.append("' ");
		}
		if (isNull(bj)) {
			
		} else {
			querry.append(" and bjdm = '");
			querry.append(bj);
			querry.append("' ");
		}
		if (isNull(nj)) {
			
		} else {
			querry.append(" and nj = '");
			querry.append(nj);
			querry.append("' ");
		}
		if (isNull(xh)) {
		} else {
			querry.append(" and xh like '%");
			querry.append(xh);
			querry.append("%' ");
		}
		if (isNull(xm)) {
		} else {
			querry.append(" and xm like '%");
			querry.append(xm);
			querry.append("%' ");
		}
		if (isNull(jb)) {
		} else {
			querry.append(" and jb = '");
			querry.append(jb);
			querry.append("' ");
		}
		if(StringUtils.isNotNull(checkForm.getShlb())){
			if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
				querry.append(" and xxsh='");
			}else{
				querry.append(" and xysh='");
			}
			querry.append(checkForm.getShlb());
			querry.append("' ");
		}
		
		
		querry.append(querry1.toString());

		if (isQuery.equalsIgnoreCase("is")) {

			if ("jbgl".equalsIgnoreCase(titName)) {
				tips = "当前所在位置：学生资助 - 申请结果查询 - 云南艺术学院进步鼓励奖助学金";
			} else if ("xy".equalsIgnoreCase(titName)) {
				tips = "当前所在位置：学生资助 - 申请结果查询 - 云南艺术学院奖学金";
			} else if ("jj".equalsIgnoreCase(titName)) {
				tips = "当前所在位置：学生资助 - 申请结果查询 - 云南艺术学院救济助学金";
			} else if ("gjzxdkjbzx".equalsIgnoreCase(titName)) {
				tips = "当前所在位置：学生资助 - 申请结果查询 - 云南艺术学院国家助学贷款奖补专项资金助学金";
			}
			
			if ("xy".equalsIgnoreCase(titName)) {
				colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh",
						"xm", "JTYZSR", "JTRJSR", "JZXJzwMC", "jbmc", "xysh",
						"xxsh" };
			}else{
				colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh",
						"xm", "JTYZSR", "JTRJSR", "JZXJzwMC", "xysh",
						"xxsh" };
			}

			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ " a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.JZXJMC pk3,a.* from "
						+ tableName
						+ " a" + querry.toString() + " order by xxsh desc) a";
				sqlExp = "select xh||'##OneSpile##'||xm||'##OneSpile##'||nd||'##OneSpile##'||csny||'##OneSpile##'||rxny||'##OneSpile##'||byny||'##OneSpile##'||lxdh||'##OneSpile##'||jzxjzwmc||'##OneSpile##'||sfzh||'##OneSpile##'||xb||'##OneSpile##'||bjmc||'##OneSpile##'||xy||'##OneSpile##'||mzmc||'##OneSpile##'||jtcy1_xm||'##OneSpile##'||jtcy1_nl||'##OneSpile##'||jtcy1_gx||'##OneSpile##'||jtcy1_gzdz||'##OneSpile##'||jtcy2_xm||'##OneSpile##'||jtcy2_nl||'##OneSpile##'||jtcy2_gx||'##OneSpile##'||jtcy2_gzdz||'##OneSpile##'||jtcy3_xm||'##OneSpile##'||jtcy3_nl||'##OneSpile##'||jtcy3_gx||'##OneSpile##'||jtcy3_gzdz||'##OneSpile##'||jtcy4_xm||'##OneSpile##'||jtcy4_nl||'##OneSpile##'||jtcy4_gx||'##OneSpile##'||jtcy4_gzdz||'##OneSpile##'||jtcy5_xm||'##OneSpile##'||jtcy5_nl||'##OneSpile##'||jtcy5_gx||'##OneSpile##'||jtcy5_gzdz||'##OneSpile##'||sfkns||'##OneSpile##'||sqsj||'##OneSpile##'||jzxjmc||'##OneSpile##'||jlxx||'##OneSpile##'||hkrs||'##OneSpile##'||jtyzsr||'##OneSpile##'||jtrjsr||'##OneSpile##'||jtsrly||'##OneSpile##'||jtzz||'##OneSpile##'||sqly||'##OneSpile##'||xyshyj||'##OneSpile##'||xxshyj||'##OneSpile##'||yzbm||'##OneSpile##'||radjthk||'##OneSpile##'||xmc||'##OneSpile##'||xxmc||'##OneSpile##'||xysh||'##OneSpile##'||xxsh||'##OneSpile##'||xydm||'##OneSpile##'||zydm||'##OneSpile##'||bjdm||'##OneSpile##'||nj||'##OneSpile##'||gkfs||'##OneSpile##'||bygz||'##OneSpile##'||kc1_mc||'##OneSpile##'||kc1_cj||'##OneSpile##'||kc2_mc||'##OneSpile##'||kc2_cj||'##OneSpile##'||kc3_mc||'##OneSpile##'||kc3_cj||'##OneSpile##'||kc4_mc||'##OneSpile##'||kc4_cj||'##OneSpile##'||kc5_mc||'##OneSpile##'||kc5_cj||'##OneSpile##'||kc6_mc||'##OneSpile##'||kc6_cj||'##OneSpile##'||kc7_mc||'##OneSpile##'||kc7_cj||'##OneSpile##'||kc8_mc||'##OneSpile##'||kc8_cj||'##OneSpile##'||kc9_mc||'##OneSpile##'||kc9_cj||'##OneSpile##'||kc10_mc||'##OneSpile##'||kc10_cj||'##OneSpile##'||kc11_mc||'##OneSpile##'||kc11_cj||'##OneSpile##'||kc12_mc||'##OneSpile##'||kc12_cj||'##OneSpile##'||kh||'##OneSpile##'||zzdjdm||'##OneSpile##'||zzdjmc||'##OneSpile##'||zzdjje||'##OneSpile##'||xyshryhm||'##OneSpile##'||xyshrxm||'##OneSpile##'||xyzzfzryj||'##OneSpile##'||fdysh||'##OneSpile##'||fdyshyj col from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " order by xxsh desc";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.JZXJMC pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xxsh desc) a";
				sqlExp = "select xh||'##OneSpile##'||xm||'##OneSpile##'||nd||'##OneSpile##'||csny||'##OneSpile##'||rxny||'##OneSpile##'||byny||'##OneSpile##'||lxdh||'##OneSpile##'||jzxjzwmc||'##OneSpile##'||sfzh||'##OneSpile##'||xb||'##OneSpile##'||bjmc||'##OneSpile##'||xy||'##OneSpile##'||mzmc||'##OneSpile##'||jtcy1_xm||'##OneSpile##'||jtcy1_nl||'##OneSpile##'||jtcy1_gx||'##OneSpile##'||jtcy1_gzdz||'##OneSpile##'||jtcy2_xm||'##OneSpile##'||jtcy2_nl||'##OneSpile##'||jtcy2_gx||'##OneSpile##'||jtcy2_gzdz||'##OneSpile##'||jtcy3_xm||'##OneSpile##'||jtcy3_nl||'##OneSpile##'||jtcy3_gx||'##OneSpile##'||jtcy3_gzdz||'##OneSpile##'||jtcy4_xm||'##OneSpile##'||jtcy4_nl||'##OneSpile##'||jtcy4_gx||'##OneSpile##'||jtcy4_gzdz||'##OneSpile##'||jtcy5_xm||'##OneSpile##'||jtcy5_nl||'##OneSpile##'||jtcy5_gx||'##OneSpile##'||jtcy5_gzdz||'##OneSpile##'||sfkns||'##OneSpile##'||sqsj||'##OneSpile##'||jzxjmc||'##OneSpile##'||jlxx||'##OneSpile##'||hkrs||'##OneSpile##'||jtyzsr||'##OneSpile##'||jtrjsr||'##OneSpile##'||jtsrly||'##OneSpile##'||jtzz||'##OneSpile##'||sqly||'##OneSpile##'||xyshyj||'##OneSpile##'||xxshyj||'##OneSpile##'||yzbm||'##OneSpile##'||radjthk||'##OneSpile##'||xmc||'##OneSpile##'||xxmc||'##OneSpile##'||xysh||'##OneSpile##'||xxsh||'##OneSpile##'||xydm||'##OneSpile##'||zydm||'##OneSpile##'||bjdm||'##OneSpile##'||nj||'##OneSpile##'||gkfs||'##OneSpile##'||bygz||'##OneSpile##'||kc1_mc||'##OneSpile##'||kc1_cj||'##OneSpile##'||kc2_mc||'##OneSpile##'||kc2_cj||'##OneSpile##'||kc3_mc||'##OneSpile##'||kc3_cj||'##OneSpile##'||kc4_mc||'##OneSpile##'||kc4_cj||'##OneSpile##'||kc5_mc||'##OneSpile##'||kc5_cj||'##OneSpile##'||kc6_mc||'##OneSpile##'||kc6_cj||'##OneSpile##'||kc7_mc||'##OneSpile##'||kc7_cj||'##OneSpile##'||kc8_mc||'##OneSpile##'||kc8_cj||'##OneSpile##'||kc9_mc||'##OneSpile##'||kc9_cj||'##OneSpile##'||kc10_mc||'##OneSpile##'||kc10_cj||'##OneSpile##'||kc11_mc||'##OneSpile##'||kc11_cj||'##OneSpile##'||kc12_mc||'##OneSpile##'||kc12_cj||'##OneSpile##'||kh||'##OneSpile##'||zzdjdm||'##OneSpile##'||zzdjmc||'##OneSpile##'||zzdjje||'##OneSpile##'||xyshryhm||'##OneSpile##'||xyshrxm||'##OneSpile##'||xyzzfzryj||'##OneSpile##'||fdysh||'##OneSpile##'||fdyshyj col from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xxsh desc";
				request.setAttribute("isXX", "no");
			}
		} else {
			
			if ("jbgl".equalsIgnoreCase(titName)) {
				tips = "当前所在位置：学生资助 - 审核 - 云南艺术学院进步鼓励奖助学金审核";
			} else if ("xy".equalsIgnoreCase(titName)) {
				tips = "当前所在位置：学生资助 - 审核 - 云南艺术学院奖学金审核";
			} else if ("jj".equalsIgnoreCase(titName)) {
				tips = "当前所在位置：学生资助 - 审核 - 云南艺术学院救济助学金审核";
			} else if ("gjzxdkjbzx".equalsIgnoreCase(titName)) {
				tips = "当前所在位置：学生资助 - 审核 - 云南艺术学院国家助学贷款奖补专项资金助学金审核";
			}
			
			if ("xy".equalsIgnoreCase(titName)) {
				colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh", "xm",
						"xb", "bjmc", "JTYZSR", "JTRJSR", "JZXJzwMC", "jbmc","" };
			}else{
				colList = new String[] { "bgcolor", "主键", "pk2", "pk3", "nd", "xh", "xm",
						"xb", "bjmc", "JTYZSR", "JTRJSR", "JZXJzwMC","" };
			}
			
			if (userType.equalsIgnoreCase("xx")) {
				sql = "select (case when nvl(a.xxsh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
					+ " a.* from(select "
					+ pk
					+ " 主键,a.xh pk2,a.JZXJMC pk3,a.* from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc) a";
				sqlExp = "select xh||'##OneSpile##'||xm||'##OneSpile##'||nd||'##OneSpile##'||csny||'##OneSpile##'||rxny||'##OneSpile##'||byny||'##OneSpile##'||lxdh||'##OneSpile##'||jzxjzwmc||'##OneSpile##'||sfzh||'##OneSpile##'||xb||'##OneSpile##'||bjmc||'##OneSpile##'||xy||'##OneSpile##'||mzmc||'##OneSpile##'||jtcy1_xm||'##OneSpile##'||jtcy1_nl||'##OneSpile##'||jtcy1_gx||'##OneSpile##'||jtcy1_gzdz||'##OneSpile##'||jtcy2_xm||'##OneSpile##'||jtcy2_nl||'##OneSpile##'||jtcy2_gx||'##OneSpile##'||jtcy2_gzdz||'##OneSpile##'||jtcy3_xm||'##OneSpile##'||jtcy3_nl||'##OneSpile##'||jtcy3_gx||'##OneSpile##'||jtcy3_gzdz||'##OneSpile##'||jtcy4_xm||'##OneSpile##'||jtcy4_nl||'##OneSpile##'||jtcy4_gx||'##OneSpile##'||jtcy4_gzdz||'##OneSpile##'||jtcy5_xm||'##OneSpile##'||jtcy5_nl||'##OneSpile##'||jtcy5_gx||'##OneSpile##'||jtcy5_gzdz||'##OneSpile##'||sfkns||'##OneSpile##'||sqsj||'##OneSpile##'||jzxjmc||'##OneSpile##'||jlxx||'##OneSpile##'||hkrs||'##OneSpile##'||jtyzsr||'##OneSpile##'||jtrjsr||'##OneSpile##'||jtsrly||'##OneSpile##'||jtzz||'##OneSpile##'||sqly||'##OneSpile##'||xyshyj||'##OneSpile##'||xxshyj||'##OneSpile##'||yzbm||'##OneSpile##'||radjthk||'##OneSpile##'||xmc||'##OneSpile##'||xxmc||'##OneSpile##'||xysh||'##OneSpile##'||xxsh||'##OneSpile##'||xydm||'##OneSpile##'||zydm||'##OneSpile##'||bjdm||'##OneSpile##'||nj||'##OneSpile##'||gkfs||'##OneSpile##'||bygz||'##OneSpile##'||kc1_mc||'##OneSpile##'||kc1_cj||'##OneSpile##'||kc2_mc||'##OneSpile##'||kc2_cj||'##OneSpile##'||kc3_mc||'##OneSpile##'||kc3_cj||'##OneSpile##'||kc4_mc||'##OneSpile##'||kc4_cj||'##OneSpile##'||kc5_mc||'##OneSpile##'||kc5_cj||'##OneSpile##'||kc6_mc||'##OneSpile##'||kc6_cj||'##OneSpile##'||kc7_mc||'##OneSpile##'||kc7_cj||'##OneSpile##'||kc8_mc||'##OneSpile##'||kc8_cj||'##OneSpile##'||kc9_mc||'##OneSpile##'||kc9_cj||'##OneSpile##'||kc10_mc||'##OneSpile##'||kc10_cj||'##OneSpile##'||kc11_mc||'##OneSpile##'||kc11_cj||'##OneSpile##'||kc12_mc||'##OneSpile##'||kc12_cj||'##OneSpile##'||kh||'##OneSpile##'||zzdjdm||'##OneSpile##'||zzdjmc||'##OneSpile##'||zzdjje||'##OneSpile##'||xyshryhm||'##OneSpile##'||xyshrxm||'##OneSpile##'||xyzzfzryj||'##OneSpile##'||fdysh||'##OneSpile##'||fdyshyj col from "
					+ tableName
					+ " a"
					+ querry.toString()
					+ " and xysh='通过' order by xxsh desc";
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				checkForm.setXydm(userDep);
				sql = "select (case when nvl(a.xysh,'未审核')='通过' then '#FFFFFF' else '#CCCCCC' end) bgcolor,"
						+ "a.* from(select "
						+ pk
						+ " 主键,a.xh pk2,a.JZXJMC pk3,a.* from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep
						+ "' order by xysh desc) a";
				sqlExp = "select xh||'##OneSpile##'||xm||'##OneSpile##'||nd||'##OneSpile##'||csny||'##OneSpile##'||rxny||'##OneSpile##'||byny||'##OneSpile##'||lxdh||'##OneSpile##'||jzxjzwmc||'##OneSpile##'||sfzh||'##OneSpile##'||xb||'##OneSpile##'||bjmc||'##OneSpile##'||xy||'##OneSpile##'||mzmc||'##OneSpile##'||jtcy1_xm||'##OneSpile##'||jtcy1_nl||'##OneSpile##'||jtcy1_gx||'##OneSpile##'||jtcy1_gzdz||'##OneSpile##'||jtcy2_xm||'##OneSpile##'||jtcy2_nl||'##OneSpile##'||jtcy2_gx||'##OneSpile##'||jtcy2_gzdz||'##OneSpile##'||jtcy3_xm||'##OneSpile##'||jtcy3_nl||'##OneSpile##'||jtcy3_gx||'##OneSpile##'||jtcy3_gzdz||'##OneSpile##'||jtcy4_xm||'##OneSpile##'||jtcy4_nl||'##OneSpile##'||jtcy4_gx||'##OneSpile##'||jtcy4_gzdz||'##OneSpile##'||jtcy5_xm||'##OneSpile##'||jtcy5_nl||'##OneSpile##'||jtcy5_gx||'##OneSpile##'||jtcy5_gzdz||'##OneSpile##'||sfkns||'##OneSpile##'||sqsj||'##OneSpile##'||jzxjmc||'##OneSpile##'||jlxx||'##OneSpile##'||hkrs||'##OneSpile##'||jtyzsr||'##OneSpile##'||jtrjsr||'##OneSpile##'||jtsrly||'##OneSpile##'||jtzz||'##OneSpile##'||sqly||'##OneSpile##'||xyshyj||'##OneSpile##'||xxshyj||'##OneSpile##'||yzbm||'##OneSpile##'||radjthk||'##OneSpile##'||xmc||'##OneSpile##'||xxmc||'##OneSpile##'||xysh||'##OneSpile##'||xxsh||'##OneSpile##'||xydm||'##OneSpile##'||zydm||'##OneSpile##'||bjdm||'##OneSpile##'||nj||'##OneSpile##'||gkfs||'##OneSpile##'||bygz||'##OneSpile##'||kc1_mc||'##OneSpile##'||kc1_cj||'##OneSpile##'||kc2_mc||'##OneSpile##'||kc2_cj||'##OneSpile##'||kc3_mc||'##OneSpile##'||kc3_cj||'##OneSpile##'||kc4_mc||'##OneSpile##'||kc4_cj||'##OneSpile##'||kc5_mc||'##OneSpile##'||kc5_cj||'##OneSpile##'||kc6_mc||'##OneSpile##'||kc6_cj||'##OneSpile##'||kc7_mc||'##OneSpile##'||kc7_cj||'##OneSpile##'||kc8_mc||'##OneSpile##'||kc8_cj||'##OneSpile##'||kc9_mc||'##OneSpile##'||kc9_cj||'##OneSpile##'||kc10_mc||'##OneSpile##'||kc10_cj||'##OneSpile##'||kc11_mc||'##OneSpile##'||kc11_cj||'##OneSpile##'||kc12_mc||'##OneSpile##'||kc12_cj||'##OneSpile##'||kh||'##OneSpile##'||zzdjdm||'##OneSpile##'||zzdjmc||'##OneSpile##'||zzdjje||'##OneSpile##'||xyshryhm||'##OneSpile##'||xyshrxm||'##OneSpile##'||xyzzfzryj||'##OneSpile##'||fdysh||'##OneSpile##'||fdyshyj col from "
						+ tableName
						+ " a"
						+ querry.toString()
						+ " and xydm='"
						+ userDep + "' order by xysh desc";
				colList[colList.length - 1] = "xysh";

				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		String colListS = "xh##OneSpile##xm##OneSpile##nd##OneSpile##csny##OneSpile##rxny##OneSpile##byny##OneSpile##lxdh##OneSpile##jzxjzwmc##OneSpile##sfzh##OneSpile##xb##OneSpile##bjmc##OneSpile##xy##OneSpile##mzmc##OneSpile##jtcy1_xm##OneSpile##jtcy1_nl##OneSpile##jtcy1_gx##OneSpile##jtcy1_gzdz##OneSpile##jtcy2_xm##OneSpile##jtcy2_nl##OneSpile##jtcy2_gx##OneSpile##jtcy2_gzdz##OneSpile##jtcy3_xm##OneSpile##jtcy3_nl##OneSpile##jtcy3_gx##OneSpile##jtcy3_gzdz##OneSpile##jtcy4_xm##OneSpile##jtcy4_nl##OneSpile##jtcy4_gx##OneSpile##jtcy4_gzdz##OneSpile##jtcy5_xm##OneSpile##jtcy5_nl##OneSpile##jtcy5_gx##OneSpile##jtcy5_gzdz##OneSpile##sfkns##OneSpile##sqsj##OneSpile##jzxjmc##OneSpile##jlxx##OneSpile##hkrs##OneSpile##jtyzsr##OneSpile##jtrjsr##OneSpile##jtsrly##OneSpile##jtzz##OneSpile##sqly##OneSpile##xyshyj##OneSpile##xxshyj##OneSpile##yzbm##OneSpile##radjthk##OneSpile##xmc##OneSpile##xxmc##OneSpile##xysh##OneSpile##xxsh##OneSpile##xydm##OneSpile##zydm##OneSpile##bjdm##OneSpile##nj##OneSpile##gkfs##OneSpile##bygz##OneSpile##kc1_mc##OneSpile##kc1_cj##OneSpile##kc2_mc##OneSpile##kc2_cj##OneSpile##kc3_mc##OneSpile##kc3_cj##OneSpile##kc4_mc##OneSpile##kc4_cj##OneSpile##kc5_mc##OneSpile##kc5_cj##OneSpile##kc6_mc##OneSpile##kc6_cj##OneSpile##kc7_mc##OneSpile##kc7_cj##OneSpile##kc8_mc##OneSpile##kc8_cj##OneSpile##kc9_mc##OneSpile##kc9_cj##OneSpile##kc10_mc##OneSpile##kc10_cj##OneSpile##kc11_mc##OneSpile##kc11_cj##OneSpile##kc12_mc##OneSpile##kc12_cj##OneSpile##kh##OneSpile##zzdjdm##OneSpile##zzdjmc##OneSpile##zzdjje##OneSpile##xyshryhm##OneSpile##xyshrxm##OneSpile##xyzzfzryj##OneSpile##fdysh##OneSpile##fdyshyj";
		StringBuffer rsExpString = new StringBuffer("");
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rsExp.addAll(dao.rsToVator(sqlExp, new String[] {}, new String[]{"col"}));
			for(Iterator<Object> it = rsExp.iterator(); it.hasNext();){
				String[] isT = (String[])it.next();
				rsExpString.append(isT[0]);
				rsExpString.append("##TwoSpile##");
			}
		}
		request.setAttribute("colListS", colListS);
		request.setAttribute("rsExpString", rsExpString.toString());

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("shxmdm", dm);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		String bjKey = xy+"!!"+zy+"!!";
		
		request.setAttribute("xydm", xydm);// 发送学校代码
		request.setAttribute("jbList", dao.getJxjDj());
		request.setAttribute("titName", titName);
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// 发送读写权限
		request.setAttribute("writeAble", writeAble);// 发送读写权限
		request.setAttribute("tableName", tableName);// 发送视图名
		request.setAttribute("realTable", realTable);// 发送数据源表名
		request.setAttribute("pk", pk);// 发送数据源表主键
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", Base.getZyMap().get(xy));// 发送专业列表
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// 发送班级列表
		request.setAttribute("act", "xsjxjzxjsqb");
		request.setAttribute("rs", rs);// 发送数据集
		request.setAttribute("topTr", topTr);// 发送表头
		request.setAttribute("rsNum", rsNum);// 发送记录数
		request.setAttribute("userType", userType);// 发送记录数
		request.setAttribute("checkList", service.getCheckList(userType, 3));
		request.setAttribute("form", checkForm);
		return mapping.findForward("jzxjsh");
	}
	
	public ActionForward jzxjshOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		String actDo = request.getParameter("actDo");
		String pkVal = request.getParameter("pkVal");
		String realTable = "";
		HashMap<String, String> hs = new HashMap<String, String>();
		HttpSession session = request.getSession();
		String pk = "";
		String sql = "";
		String[] colList = new String[] {};
		String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
		String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
		String zzdjdm = DealString.toGBK(request.getParameter("zzdjdm"));
		String userType = dao.getUserType(session.getAttribute("userDep")
				.toString());
		String sUName;
		String logMsg;
		sUName = session.getAttribute("userName").toString();
		List xszzDjList = dao.getXszzDjList();
		request.setAttribute("xszzDjList", xszzDjList);
		boolean ok = false;
		if ("del".equalsIgnoreCase(actDo)) {
			String pkDel = request.getParameter("pkDel");
			String[] pkDelT = pkDel.split("!!splitOne!!");
			String[] sqlT = new String[pkDelT.length];
			for (int i = 1; i < pkDelT.length; i++) {
				String pkT = pkDelT[i];
				if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
					sqlT[i] = "delete xsjxjzxjsqb where lower(xh||nd||jzxjmc)='"
							+ pkT + "' and xxsh<>'通过'";
				} else {
					sqlT[i] = "delete xsjxjzxjsqb where lower(xh||nd||jzxjmc)='"
							+ pkT + "'";
				}
			}
			dao.runBatch(sqlT);
			String lx= request.getParameter("lx");
			String isQuery=request.getParameter("isQuery");
			ActionForward newFwd = new ActionForward(
					"/xszz_ynys.do?method=jzxjsh&go=go&lx="+lx+"&isQuery="+isQuery, false);
			return newFwd;
		}

		String titName = request.getParameter("lx");
		String title = "";
		if ("jbgl".equalsIgnoreCase(titName)) {
			title = "当前所在位置：学生资助 - 审核 - 云南艺术学院进步鼓励奖助学金审核";
		} else if ("xy".equalsIgnoreCase(titName)) {
			title = "当前所在位置：学生资助 - 审核 - 云南艺术学院奖学金审核";
		} else if ("jj".equalsIgnoreCase(titName)) {
			title = "当前所在位置：学生资助 - 审核 - 云南艺术学院救济助学金审核";
		} else if ("gjzxdkjbzx".equalsIgnoreCase(titName)) {
			title = "当前所在位置：学生资助 - 审核 - 云南艺术学院国家助学贷款奖补专项资金助学金审核";
		}
		
		if (actDo.equalsIgnoreCase("save")) {
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
				ok = StandardOperation.update("xsjxjzxjsqb", new String[] {
						"xysh", "xyshyj" }, new String[] { yesNo, xyshyj },
						"lower(xh||nd||jzxjmc)", pkVal, request);
			} else {
				ok = StandardOperation.update("xsjxjzxjsqb", new String[] {
						"xxsh", "xxshyj" }, new String[] { yesNo, xxshyj },
						"lower(xh||nd||jzxjmc)", pkVal, request);
			}
			if (ok) {
				logMsg = "修改(审核) xsjxjzxjsqb";
				Base.log(request, logMsg, sUName);
			}
		}
		realTable = "xsjxjzxjsqb";
		pk = "lower(xh||nd||jzxjmc)";
		if ((userType == null) || !userType.equalsIgnoreCase("xx")) {
			sql = "select "
					+ pk
					+ " pk,(select a.mc from ynys_zzdjb a where a.dm = view_xsjxjzxjsq.jb) jb,xh,xm,nd,lxdh,sfzh,zzmmmc,sxncj,csny,rxny,sfzh,xb,bjmc,xy,mzmc,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdz,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdz,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdz,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz,sfkns,sqsj,jzxjmc,jlxx,hkrs,jtyzsr,jtrjsr,jtsrly,"
					+ "jtzz,sqly,xyshyj,xxshyj,yzbm,radjthk,xmc,xxmc,gkfs,bygz,kc1_mc,kc1_cj,kc2_mc,kc2_cj,kc3_mc,kc3_cj,kc4_mc,kc4_cj,kc5_mc,kc5_cj,kc6_mc,kc6_cj,kc7_mc,kc7_cj,kc8_mc,kc8_cj,kc9_mc,kc9_cj,kc10_mc,kc10_cj,kc11_mc,kc11_cj,kc12_mc,kc12_cj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyshrxm,xyzzfzryj,xxsh,XYSH yesNo from view_xsjxjzxjsq where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "no");
		} else {
			sql = "select "
					+ pk
					+ " pk,(select a.mc from ynys_zzdjb a where a.dm = view_xsjxjzxjsq.jb) jb,xh,xm,nd,lxdh,sfzh,zzmmmc,sxncj,csny,rxny,sfzh,xb,bjmc,xy,mzmc,jtcy1_xm,jtcy1_nl,jtcy1_gx,jtcy1_gzdz,jtcy2_xm,jtcy2_nl,jtcy2_gx,jtcy2_gzdz,jtcy3_xm,jtcy3_nl,jtcy3_gx,jtcy3_gzdz,jtcy4_xm,jtcy4_nl,jtcy4_gx,jtcy4_gzdz,jtcy5_xm,jtcy5_nl,jtcy5_gx,jtcy5_gzdz,sfkns,sqsj,jzxjmc,jlxx,hkrs,jtyzsr,jtrjsr,jtsrly,"
					+ "jtzz,sqly,xyshyj,xxshyj,yzbm,radjthk,xmc,xxmc,gkfs,bygz,kc1_mc,kc1_cj,kc2_mc,kc2_cj,kc3_mc,kc3_cj,kc4_mc,kc4_cj,kc5_mc,kc5_cj,kc6_mc,kc6_cj,kc7_mc,kc7_cj,kc8_mc,kc8_cj,kc9_mc,kc9_cj,kc10_mc,kc10_cj,kc11_mc,kc11_cj,kc12_mc,kc12_cj,zzdjdm,zzdjmc,zzdjje,xyshryhm,xyshrxm,xyzzfzryj,xxsh,XXSH yesNo from view_xsjxjzxjsq where "
					+ pk + "='" + pkVal + "'";
			request.setAttribute("isXX", "is");
		}
		colList = new String[] { "pk", "jb", "xh", "xm", "nd", "lxdh", "sfzh",
				"zzmmmc", "sxncj", "csny", "rxny", "sfzh", "xb", "bjmc", "xy",
				"mzmc", "jtcy1_xm", "jtcy1_nl", "jtcy1_gx", "jtcy1_gzdz",
				"jtcy2_xm", "jtcy2_nl", "jtcy2_gx", "jtcy2_gzdz", "jtcy3_xm",
				"jtcy3_nl", "jtcy3_gx", "jtcy3_gzdz", "jtcy4_xm", "jtcy4_nl",
				"jtcy4_gx", "jtcy4_gzdz", "jtcy5_xm", "jtcy5_nl", "jtcy5_gx",
				"jtcy5_gzdz", "sfkns", "sqsj", "jzxjmc", "jlxx", "hkrs",
				"jtyzsr", "jtrjsr", "jtsrly", "jtzz", "sqly", "xyshyj",
				"xxshyj", "yzbm", "radjthk", "xmc", "xxmc", "gkfs", "bygz",
				"kc1_mc", "kc1_cj", "kc2_mc", "kc2_cj", "kc3_mc", "kc3_cj",
				"kc4_mc", "kc4_cj", "kc5_mc", "kc5_cj", "kc6_mc", "kc6_cj",
				"kc7_mc", "kc7_cj", "kc8_mc", "kc8_cj", "kc9_mc", "kc9_cj",
				"kc10_mc", "kc10_cj", "kc11_mc", "kc11_cj", "kc12_mc",
				"kc12_cj", "zzdjdm", "zzdjmc", "zzdjje", "xyshryhm", "xyshrxm",
				"xyzzfzryj", "xxsh", "yesNo" };

		String[] rs = dao.getOneRs(sql, new String[] {}, colList);
		if (rs == null) {
			rs = new String[colList.length];
		}
		for (int i = 0; i < colList.length; i++) {
			rs[i] = ((rs[i] == null) || rs[i].equalsIgnoreCase("")) ? " "
					: rs[i];
			hs.put(colList[i], rs[i]);
		}

		hs.put("zzdjdm", zzdjdm);
		request.setAttribute("titName", titName);
		request.setAttribute("actDo", actDo);
		request.setAttribute("rs", hs);
		request.setAttribute("userType", userType);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pk", pkVal);
		request.setAttribute("title", title);
		request.setAttribute("tName", realTable);
		request.setAttribute("act", "xsjxjzxjsqb");
		return mapping.findForward("jzxjshOne");
	}
	
	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}
}
