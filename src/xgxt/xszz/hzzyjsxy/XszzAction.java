
package xgxt.xszz.hzzyjsxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 杭州职业技术学院学生资助Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-02</p>
 */
public class XszzAction extends BaseAction {

	/**
	 * 在校学生贷款信息页面
	 * data_dkxx ----- 在校学生贷款数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_dkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel zxxsdkModel = new QueryModel();
		zxxsdkModel.setGo(request.getParameter("go"));
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		
		if ("del".equalsIgnoreCase(zxxsdkModel.getGo())) {
			service.delDkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			zxxsdkModel.setGo("go");
		}
		
		BeanUtils.copyProperties(zxxsdkModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getZxxsDkxxTit();
		List<String[]> resList = service.getShJxjRes(zxxsdkModel,request);
		String xh = DealString.toGBK(hzzyForm.getXh());
		hzzyForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, hzzyForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		if (userType.equalsIgnoreCase("xy")) {
			hzzyForm.setXydm(userDep);
		}
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", hzzyForm);
		request.setAttribute("realTable", "xszz_hzzyjsxy_zxxsdkxx");
		request.setAttribute("tableName", "view_xszz_hzzyjsxy_zxxsdkxx");
		return mapping.findForward("data_dkxx");
	}
	
	/**
	 * 在校学生贷款详细信息页面
	 * dkxx_queryOne ----- 在校学生贷款详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dkxx_queryOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzHzzyService service = new XszzHzzyService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//用户类型是学生则直接获取用户名
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getDkxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("xn", Base.currXn);//当前学年
			}
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("dkxx_queryOne");
	}
	
	/**
	 * 保存在校学生贷款信息
	 * dkxx_save ---- 保存在校学生贷款信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dkxx_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		SaveZxxsdkModel saveZxxsdkModel = new SaveZxxsdkModel();
		BeanUtils.copyProperties(saveZxxsdkModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveDkxx(saveZxxsdkModel, request);// 保存贷款信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = saveZxxsdkModel.getXh();
		String xn = saveZxxsdkModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getDkxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("xn", Base.currXn);//当前学年
			}
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("dkxx_save");
	}
	
	/**
	 * 在校学生贷款信息导出
	 * dkxx_exp ----- 在校学生贷款信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dkxx_exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel zxxsdkModel = new QueryModel();
		
		BeanUtils.copyProperties(zxxsdkModel, hzzyForm);
		service.getZxxsDkxxExp(zxxsdkModel, response,request);
		return mapping.findForward("dkxx_exp");
	}
	
	/**
	 * 家庭经济困难证明打印
	 * jtjjknzm ----- 家庭经济困难证明打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtjjknzm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("jtjjknzm");
	}
	
	/**
	 * 家庭经济困难情况说明打印
	 * jtjjknqksm ----- 家庭经济困难情况说明打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtjjknqksm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("jtjjknqksm");
	}
	
	/**
	 * 困难生申请页面
	 * knssq ----- 困难生申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzHzzyService service = new XszzHzzyService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("sfksq", service.getKnsSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssq");
	}
	
	/**
	 * 保存困难生申请信息
	 * knssqSave ---- 保存困难生申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveKnsSqxx(knsModel, request);// 保存贷款信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);//当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssqSave");
	}

	/**
	 * 困难生申请表页面
	 * knssqb ----- 困难生申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		XszzHzzyService service = new XszzHzzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, hzzyjsxyActionForm);
		stuMap = service.getKnsSqb(knsModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knssqb");
	}

	/**
	 * 困难生审核页面
	 * knssh ----- 困难生审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "困难", request);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "特殊困难", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不困难", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			hzzyForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			hzzyForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getKnsRes(queryModel,request);
		String xh = DealString.toGBK(hzzyForm.getXh());
		hzzyForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, hzzyForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		
		hzzyForm.setXm(DealString.toGBK(hzzyForm.getXm()));
		hzzyForm.setXb(DealString.toGBK(hzzyForm.getXb()));
		hzzyForm.setMzrd(DealString.toGBK(hzzyForm.getMzrd()));
		hzzyForm.setXysh(DealString.toGBK(hzzyForm.getXysh()));
		hzzyForm.setXxsh(DealString.toGBK(hzzyForm.getXxsh()));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", hzzyForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "hzzy_knssqb");
		request.setAttribute("tableName", "view_hzzy_knssqb");
		return mapping.findForward("knssh");
	}
	
	/**
	 * 困难生信息导出
	 * knsExp ----- 困难生信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, hzzyForm);
		service.getKnsExp(queryModel, response,request);
		return mapping.findForward("knsExp");
	}

	/**
	 * 困难生审核详细页面
	 * knsshOne ----- 困难生审核详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		XszzHzzyService service = new XszzHzzyService();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		hzzyForm.setMzrd(stuMap.get("mzrd"));
		hzzyForm.setXysh(stuMap.get("xysh"));
		hzzyForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshOne");
	}

	/**
	 * 保存困难生审核信息
	 * knsshSave ---- 保存困难生审核信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveKnsShxx(knsModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		hzzyjsxyActionForm.setMzrd(stuMap.get("mzrd"));
		hzzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		hzzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshSave");
	}

	/**
	 * 国家奖学金申请页面
	 * gjjxjsq ----- 国家奖学金申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzHzzyService service = new XszzHzzyService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjjxjxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("sfksq", service.getGjjxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjsq");
	}
	
	/**
	 * 保存国家奖学金申请信息
	 * gjjxjsqSave ---- 保存国家奖学金申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		GjjxjModel gjjxjModel = new GjjxjModel();
		BeanUtils.copyProperties(gjjxjModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveGjjxjSqxx(gjjxjModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjjxjModel.getXh();
		String nd = gjjxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjjxjxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);//当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjsqSave");
	}
	
	/**
	 * 国家奖学金申请表页面
	 * gjjxjsqb ----- 国家奖学金申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		XszzHzzyService service = new XszzHzzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjjxjModel gjjxjModel = new GjjxjModel();
		BeanUtils.copyProperties(gjjxjModel, hzzyjsxyActionForm);
		stuMap = service.getGjjxjSqb(gjjxjModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjjxjsqb");
	}
	
	/**
	 * 国家奖学金审核页面
	 * gjjxjsh ----- 国家奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			hzzyForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			hzzyForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getGjjxjTit();
		List<String[]> resList = service.getGjjxjRes(queryModel,request);
		String xh = DealString.toGBK(hzzyForm.getXh());
		hzzyForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, hzzyForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		
		hzzyForm.setXm(DealString.toGBK(hzzyForm.getXm()));
		hzzyForm.setXb(DealString.toGBK(hzzyForm.getXb()));
		hzzyForm.setXysh(DealString.toGBK(hzzyForm.getXysh()));
		hzzyForm.setXxsh(DealString.toGBK(hzzyForm.getXxsh()));
		
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", hzzyForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "hzzy_xszz_gjjxj");
		request.setAttribute("tableName", "view_hzzy_xszz_gjjxj");
		return mapping.findForward("gjjxjsh");
	}
	
	/**
	 * 国家奖学金信息导出
	 * gjjxjExp ----- 国家奖学金信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, hzzyForm);
		service.getGjjxjExp(queryModel, response,request);
		return mapping.findForward("gjjxjExp");
	}
	
	/**
	 * 国家奖学金审核详细页面
	 * gjjxjshOne ----- 国家奖学金审核详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		XszzHzzyService service = new XszzHzzyService();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxjxx(pkVal);
		hzzyForm.setXysh(stuMap.get("xysh"));
		hzzyForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjshOne");
	}
	
	/**
	 * 保存国家奖学金审核信息
	 * gjjxjshSave ---- 保存国家奖学金审核信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		GjjxjModel gjjxjModel = new GjjxjModel();
		BeanUtils.copyProperties(gjjxjModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveGjjxjShxx(gjjxjModel, request);// 保存审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjjxjModel.getXh();
		String nd = gjjxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxjxx(pkVal);
		hzzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		hzzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjshSave");
	}

	/**
	 * 国家助学金申请页面
	 * gjzxjsq ----- 国家助学金申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzHzzyService service = new XszzHzzyService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//用户类型是学生则直接获取用户名
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("sfksq", service.getGjzxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsq");
	}
	
	/**
	 * 保存国家助学金申请信息
	 * gjzxjsqSave ---- 保存国家助学金申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveGjzxjSqxx(gjzxjModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxjModel.getXh();
		String nd = gjzxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);//当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsqSave");
	}
	
	/**
	 * 国家助学金申请表页面
	 * gjzxjsqb ----- 国家助学金申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		XszzHzzyService service = new XszzHzzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, hzzyjsxyActionForm);
		stuMap = service.getGjzxjSqb(gjzxjModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxjsqb");
	}
	
	/**
	 * 国家助学金审核页面
	 * gjzxjsh ----- 国家助学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			hzzyForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			hzzyForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getGjzxjTit();
		List<String[]> resList = service.getGjzxjRes(queryModel,request);
		String xh = DealString.toGBK(hzzyForm.getXh());
		hzzyForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, hzzyForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		
		hzzyForm.setXm(DealString.toGBK(hzzyForm.getXm()));
		hzzyForm.setXb(DealString.toGBK(hzzyForm.getXb()));
		hzzyForm.setJtzz(DealString.toGBK(hzzyForm.getJtzz()));
		hzzyForm.setXysh(DealString.toGBK(hzzyForm.getXysh()));
		hzzyForm.setXxsh(DealString.toGBK(hzzyForm.getXxsh()));
		hzzyForm.setZxjdj(DealString.toGBK(hzzyForm.getZxjdj()));
		
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", hzzyForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "hzzy_xszz_gjzxj");
		request.setAttribute("tableName", "view_hzzy_xszz_gjzxj");
		return mapping.findForward("gjzxjsh");
	}
	
	/**
	 * 国家助学金信息导出
	 * gjzxjExp ----- 国家助学金信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, hzzyForm);
		service.getGjzxjExp(queryModel, response,request);
		return mapping.findForward("gjzxjExp");
	}
	
	/**
	 * 国家助学金审核详细页面
	 * gjzxjshOne ----- 国家助学金审核详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		XszzHzzyService service = new XszzHzzyService();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		hzzyForm.setXysh(stuMap.get("xysh"));
		hzzyForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshOne");
	}
	
	/**
	 * 保存国家助学金审核信息
	 * gjzxjshSave ---- 保存国家助学金审核信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveGjzxjShxx(gjzxjModel, request);// 保存审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxjModel.getXh();
		String nd = gjzxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		hzzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		hzzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshSave");
	}
}
