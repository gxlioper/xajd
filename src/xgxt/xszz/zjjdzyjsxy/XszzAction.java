package xgxt.xszz.zjjdzyjsxy;

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

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江机电职业技术学院学生资助Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-22</p>
 */
public class XszzAction extends BaseAction {

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
		XszzZjjdService service = new XszzZjjdService();
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
				stuMap.putAll(service.getStuZzqk(xh));
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
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveKnsSqxx(knsModel, request);// 保存信息
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
		
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XszzZjjdService service = new XszzZjjdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, xszzZjjdzyjsxyActionForm);
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
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
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
		if ("ybkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "一般困难", request);
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
			xszzZjjdzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzZjjdzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getKnsRes(queryModel,request);
		String xh = DealString.toGBK(xszzZjjdzyjsxyActionForm.getXh());
		xszzZjjdzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZjjdzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZjjdzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zjjd_knsxx");
		request.setAttribute("tableName", "view_zjjd_knsxx");
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
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
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
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userName = session.getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		xszzZjjdzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			if(userBj.size() == 0){
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
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
		String userName = session.getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
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
		xszzZjjdzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			if(userBj.size() == 0){
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(12));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshSave");
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
		XszzZjjdService service = new XszzZjjdService();
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
			stuMap = service.getGjzxjxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("sfksq", service.getGjzxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("gjzxjList", service.getGjzxjList());
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
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
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
			stuMap = service.getGjzxjxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);//当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("gjzxjList", service.getGjzxjList());
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
		
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XszzZjjdService service = new XszzZjjdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, xszzZjjdzyjsxyActionForm);
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
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
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
			xszzZjjdzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzZjjdzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getGjzxjTit();
		List<String[]> resList = service.getGjzxjRes(queryModel,request);
		String xh = DealString.toGBK(xszzZjjdzyjsxyActionForm.getXh());
		xszzZjjdzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZjjdzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZjjdzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zjjd_gjzxjsqb");
		request.setAttribute("tableName", "view_zjjd_gjzxjsqb");
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
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
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
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		String xh = pkVal.substring(4); 
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
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
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveGjzxjShxx(gjzxjModel, request);// 保存困难生审核信息
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
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		XszzDao xszzDao = new XszzDao();
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshSave");
	}
	
	/**
	 * 国家励志奖学金申请页面
	 * gjlzjxjsq ----- 国家励志奖学金申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
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
			stuMap = service.getGjlzjxjxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("sfksq", service.getGjlzjxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjsq");
	}
	
	/**
	 * 保存国家励志奖学金申请信息
	 * gjlzjxjsqSave ---- 保存国家励志奖学金申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		GjlzjxjModel gjlzjxjModel = new GjlzjxjModel();
		BeanUtils.copyProperties(gjlzjxjModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveGjlzjxjSqxx(gjlzjxjModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjlzjxjModel.getXh();
		String nd = gjlzjxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjlzjxjxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);//当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjsqSave");
	}
	
	/**
	 * 国家励志奖学金申请表页面
	 * gjlzjxjsqb ----- 国家励志奖学金申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XszzZjjdService service = new XszzZjjdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjlzjxjModel gjlzjxjModel = new GjlzjxjModel();
		BeanUtils.copyProperties(gjlzjxjModel, xszzZjjdzyjsxyActionForm);
		stuMap = service.getGjlzjxjSqb(gjlzjxjModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjlzjxjsqb");
	}
	
	/**
	 * 国家励志奖学金审核页面
	 * gjlzjxjsh ----- 国家励志奖学金审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjlzjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZjjdzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzZjjdzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getGjlzjxjTit();
		List<String[]> resList = service.getGjlzjxjRes(queryModel,request);
		String xh = DealString.toGBK(xszzZjjdzyjsxyActionForm.getXh());
		xszzZjjdzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZjjdzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZjjdzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_zjjd_gjlzjxj");
		request.setAttribute("tableName", "view_xszz_zjjd_gjlzjxj");
		return mapping.findForward("gjlzjxjsh");
	}
	
	/**
	 * 国家励志奖学金信息导出
	 * gjlzjxjExp ----- 国家励志奖学金信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		service.getGjlzjxjExp(queryModel, response,request);
		return mapping.findForward("gjlzjxjExp");
	}
	
	/**
	 * 国家励志奖学金审核详细页面
	 * gjlzjxjshOne ----- 国家励志奖学金审核详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxjxx(pkVal);
		String xh = pkVal.substring(4); 
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjshOne");
	}
	
	/**
	 * 保存国家励志奖学金审核信息
	 * gjlzjxjshSave ---- 保存国家励志奖学金审核信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		GjlzjxjModel gjlzjxjModel = new GjlzjxjModel();
		BeanUtils.copyProperties(gjlzjxjModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveGjlzjxjShxx(gjlzjxjModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjlzjxjModel.getXh();
		String nd = gjlzjxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxjxx(pkVal);
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		XszzDao xszzDao = new XszzDao();
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjshSave");
	}

	/**
	 * 临时困难补助申请页面
	 * lsbzsq ----- 临时困难补助申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbzsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
		DAO dao = new DAO();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";//学号
		String id = "";
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//用户类型是学生则直接获取用户名
		id = Base.chgNull(request.getParameter("id"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? id : pkVal;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh) || !"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getLsbzxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				if (dao.isKns(xh)) {
					stuMap.put("sfkns", "是");
				} else {
					stuMap.put("sfkns", "否");
				}
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("sfksq", service.getLsbzSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("lsbzList", service.getLsbzList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsbzsq");
	}
	
	/**
	 * 保存临时困难补助申请信息
	 * lsbzsqSave ---- 保存临时困难补助申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbzsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		LsbzModel lsbzModel = new LsbzModel();
		DAO dao = new DAO();
		BeanUtils.copyProperties(lsbzModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveLsbzSqxx(lsbzModel, request);// 保存信息
		if (!"is".equals(request.getAttribute("isPASS"))) {
			if (bJg) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		
		String xh = lsbzModel.getXh();
		String id = lsbzModel.getId();
		String pkVal = id;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh) || !"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getLsbzxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				if (dao.isKns(xh)) {
					stuMap.put("sfkns", "是");
				} else {
					stuMap.put("sfkns", "否");
				}
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("lsbzList", service.getLsbzList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsbzsqSave");
	}
	
	/**
	 * 临时困难补助申请表页面
	 * lsbzsqb ----- 临时困难补助申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbzsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XszzZjjdService service = new XszzZjjdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		LsbzModel lsbzModel = new LsbzModel();
		BeanUtils.copyProperties(lsbzModel, xszzZjjdzyjsxyActionForm);
		stuMap = service.getLsbzSqb(lsbzModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("lsbzsqb");
	}
	
	/**
	 * 临时困难补助审核页面
	 * lsbzsh ----- 临时困难补助审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delLsbzxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modLsbzxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modLsbzxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZjjdzyjsxyActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getLsbzTit();
		List<String[]> resList = service.getLsbzRes(queryModel,request);
		String xh = DealString.toGBK(xszzZjjdzyjsxyActionForm.getXh());
		xszzZjjdzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZjjdzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "id");
		request.setAttribute("hForm", xszzZjjdzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_zjjd_lsbz");
		request.setAttribute("tableName", "view_xszz_zjjd_lsbz");
		return mapping.findForward("lsbzsh");
	}
	
	/**
	 * 临时困难补助信息导出
	 * lsbzExp ----- 临时困难补助信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		service.getLsbzExp(queryModel, response,request);
		return mapping.findForward("lsbzExp");
	}
	
	/**
	 * 临时困难补助审核详细页面
	 * lsbzshOne ----- 临时困难补助审核详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbzshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getLsbzxx(pkVal);
		String xh = stuMap.get("xh"); 
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsbzshOne");
	}
	
	/**
	 * 保存临时困难补助审核信息
	 * lsbzshSave ---- 保存临时困难补助审核信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lsbzshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		LsbzModel lsbzModel = new LsbzModel();
		BeanUtils.copyProperties(lsbzModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveLsbzShxx(lsbzModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = lsbzModel.getXh();
		String id = lsbzModel.getId();
		String pkVal = id;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getLsbzxx(pkVal);
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		XszzDao xszzDao = new XszzDao();
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("lsbzshSave");
	}

	/**
	 * 国家助学贷款申请页面
	 * gjzxdksq ----- 国家助学贷款申请页面
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
		XszzZjjdService service = new XszzZjjdService();
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
			stuMap = service.getGjzxdkxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("sfksq", service.getGjzxdkSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdksq");
	}
	
	/**
	 * 保存国家助学贷款申请信息
	 * gjzxdksqSave ---- 保存国家助学贷款申请信息 
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
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveGjzxdkSqxx(gjzxdkModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxdkModel.getXh();
		String nd = gjzxdkModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxdkxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);//当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdksqSave");
	}
	
	/**
	 * 国家助学贷款申请表页面
	 * gjzxdksqb ----- 国家助学贷款申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxdksqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XszzZjjdService service = new XszzZjjdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZjjdzyjsxyActionForm);
		stuMap = service.getGjzxdkSqb(gjzxdkModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxdksqb");
	}
	
	/**
	 * 国家助学贷款审核页面
	 * gjzxdksh ----- 国家助学贷款审核
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
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
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
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZjjdzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzZjjdzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getGjzxdkTit();
		List<String[]> resList = service.getGjzxdkRes(queryModel,request);
		String xh = DealString.toGBK(xszzZjjdzyjsxyActionForm.getXh());
		xszzZjjdzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZjjdzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZjjdzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_zjjd_gjzxdk");
		request.setAttribute("tableName", "view_xszz_zjjd_gjzxdk");
		return mapping.findForward("gjzxdksh");
	}
	
	/**
	 * 国家助学贷款信息导出
	 * gjzxdkExp ----- 国家助学贷款信息导出
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
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		service.getGjzxdkExp(queryModel, response,request);
		return mapping.findForward("gjzxdkExp");
	}
	
	/**
	 * 国家助学贷款审核详细页面
	 * gjzxdkshOne ----- 国家助学贷款审核详细页面
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
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userName = session.getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);
		String xh = pkVal.substring(4); 
		xszzZjjdzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			if(userBj.size() == 0){
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkshOne");
	}
	
	/**
	 * 保存国家助学贷款审核信息
	 * gjzxdkshSave ---- 保存国家助学贷款审核信息 
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
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userName = session.getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		GjzxdkModel gjzxdkModel = new GjzxdkModel();
		BeanUtils.copyProperties(gjzxdkModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveGjzxdkShxx(gjzxdkModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxdkModel.getXh();
		String nd = gjzxdkModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxdkxx(pkVal);
		xszzZjjdzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			if(userBj.size() == 0){
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		XszzDao xszzDao = new XszzDao();
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxdkshSave");
	}

	/**
	 * 学费减免申请页面
	 * xfjmsq ----- 学费减免申请页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
		DAO dao = new DAO();
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
			stuMap = service.getXfjmxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				if (dao.isKns(xh)) {
					stuMap.put("sfkns", "是");
				} else {
					stuMap.put("sfkns", "否");
				}
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("sfksq", service.getXfjmSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("xfjmList", service.getXfjmList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjmsq");
	}
	
	/**
	 * 保存学费减免申请信息
	 * xfjmsqSave ---- 保存学费减免申请信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjmsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XfjmModel xfjmModel = new XfjmModel();
		DAO dao = new DAO();
		BeanUtils.copyProperties(xfjmModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveXfjmSqxx(xfjmModel, request);// 保存信息
		if (!"is".equals(request.getAttribute("isPASS"))) {
			if (bJg) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}
		
		String xh = xfjmModel.getXh();
		String nd = xfjmModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getXfjmxx(pkVal);// 得到困难生详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("nd", Base.currNd);// 当前年度
				if (dao.isKns(xh)) {
					stuMap.put("sfkns", "是");
				} else {
					stuMap.put("sfkns", "否");
				}
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("xfjmList", service.getXfjmList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjmsqSave");
	}
	
	/**
	 * 学费减免申请表页面
	 * xfjmsqb ----- 学费减免申请表页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjmsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XszzZjjdService service = new XszzZjjdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		XfjmModel xfjmModel = new XfjmModel();
		BeanUtils.copyProperties(xfjmModel, xszzZjjdzyjsxyActionForm);
		stuMap = service.getXfjmSqb(xfjmModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("xfjmsqb");
	}
	
	/**
	 * 学费减免审核页面
	 * xfjmsh ----- 学费减免审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjmsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delXfjmxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfjmxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modXfjmxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "不通过", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZjjdzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzZjjdzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getXfjmTit();
		List<String[]> resList = service.getXfjmRes(queryModel,request);
		String xh = DealString.toGBK(xszzZjjdzyjsxyActionForm.getXh());
		xszzZjjdzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZjjdzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZjjdzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_zjjd_xfjm");
		request.setAttribute("tableName", "view_xszz_zjjd_xfjm");
		return mapping.findForward("xfjmsh");
	}
	
	/**
	 * 学费减免信息导出
	 * xfjmExp ----- 学费减免信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjmExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		service.getXfjmExp(queryModel, response,request);
		return mapping.findForward("xfjmExp");
	}
	
	/**
	 * 学费减免审核详细页面
	 * xfjmshOne ----- 学费减免审核详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjmshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfjmxx(pkVal);
		String xh = pkVal.substring(4); 
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjmshOne");
	}
	
	/**
	 * 保存学费减免审核信息
	 * xfjmshSave ---- 保存学费减免审核信息 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xfjmshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XfjmModel xfjmModel = new XfjmModel();
		BeanUtils.copyProperties(xfjmModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveXfjmShxx(xfjmModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = xfjmModel.getXh();
		String nd = xfjmModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfjmxx(pkVal);
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		XszzDao xszzDao = new XszzDao();
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfjmshSave");
	}

	/**
	 * 学费缓缴申请页面
	 * xfhjsq ----- 学费缓缴申请页面
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
		XszzZjjdService service = new XszzZjjdService();
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
			stuMap = service.getXfhjxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("nd", Base.currNd);//当前年度
		request.setAttribute("sfksq", service.getXfhjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhjsq");
	}
	
	/**
	 * 保存学费缓缴申请信息
	 * xfhjsqSave ---- 保存学费缓缴申请信息 
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
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XfhjModel xfhjModel = new XfhjModel();
		BeanUtils.copyProperties(xfhjModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
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
				stuMap.put("nd", Base.currNd);//当前年度
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhjsqSave");
	}

	/**
	 * 学费缓缴申请表页面
	 * xfhjsqb ----- 学费缓缴申请表页面
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
		
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XszzZjjdService service = new XszzZjjdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		XfhjModel xfhjModel = new XfhjModel();
		BeanUtils.copyProperties(xfhjModel, xszzZjjdzyjsxyActionForm);
		stuMap = service.getXfhjSqb(xfhjModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("xfhjsqb");
	}

	/**
	 * 学费缓缴审核页面
	 * xfhjsh ----- 学费缓缴审核
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
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
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
		
		if (userType.equalsIgnoreCase("xy")) {
			xszzZjjdzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzZjjdzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getXfhjTit();
		List<String[]> resList = service.getXfhjRes(queryModel,request);
		String xh = DealString.toGBK(xszzZjjdzyjsxyActionForm.getXh());
		xszzZjjdzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZjjdzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZjjdzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_zjjd_xfhj");
		request.setAttribute("tableName", "view_xszz_zjjd_xfhj");
		return mapping.findForward("xfhjsh");
	}
	
	/**
	 * 学费缓缴信息导出
	 * xfhjExp ----- 学费缓缴信息导出
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
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzZjjdzyjsxyActionForm);
		service.getXfhjExp(queryModel, response,request);
		return mapping.findForward("xfhjExp");
	}

	/**
	 * 学费缓缴审核详细页面
	 * xfhjshOne ----- 学费缓缴审核详细页面
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
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userName = session.getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getXfhjxx(pkVal);
		String xh = pkVal.substring(4); 
		xszzZjjdzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			if(userBj.size() == 0){
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhjshOne");
	}

	/**
	 * 保存学费缓缴审核信息
	 * xfhjshSave ---- 保存学费缓缴审核信息 
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
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userName = session.getAttribute("userName").toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XfhjModel xfhjModel = new XfhjModel();
		BeanUtils.copyProperties(xfhjModel, xszzZjjdzyjsxyActionForm);
		XszzZjjdService service = new XszzZjjdService();
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
		xszzZjjdzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		xszzZjjdzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		xszzZjjdzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			if(userBj.size() == 0){
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		XszzDao xszzDao = new XszzDao();
		request.setAttribute("xsQgzuCjjlList", xszzDao.getXsQgzuCjjlList(xh));
		request.setAttribute("xsJxjjlList", xszzDao.getXsJxjjlList(xh));
		request.setAttribute("zjjdLsknbzList", xszzDao.getZjjdLsknbzList(xh));
		request.setAttribute("zjjdXfhjList", xszzDao.getZjjdXfhjList(xh));
		request.setAttribute("zjjdXfjmList", xszzDao.getZjjdXfjmList(xh));
		request.setAttribute("zjjdGjzxjList", xszzDao.getZjjdGjzxjList(xh));
		request.setAttribute("zjjdGjLzjxjList", xszzDao.getZjjdGjLzjxjList(xh));
		request.setAttribute("zjjdGjzxdkList", xszzDao.getZjjdGjzxdkList(xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("xfhjshSave");
	}

	/**
	 * 放款信息页面
	 * data_fkxx ----- 放款信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_fkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZjjdzyjsxyActionForm xszzZjjdzyjsxyActionForm = (XszzZjjdzyjsxyActionForm)form;
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm zzjdForm = (XszzZjjdzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(request.getParameter("go"));
		String userType = request.getSession().getAttribute("userType").toString();
		String sUserName = request.getSession().getAttribute("userName").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delFkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		
		BeanUtils.copyProperties(queryModel, zzjdForm);
		if ("stu".equalsIgnoreCase(userType) || "student".equalsIgnoreCase(userType)){
			userType = "stu";
			queryModel.setGo("go");
			queryModel.setXh(sUserName);
		}
		List<HashMap<String, String>> topList = service.getFkxxTit();
		List<String[]> resList = service.getFkxxRes(queryModel,request);
		String xh = DealString.toGBK(zzjdForm.getXh());
		String hth = DealString.toGBK(zzjdForm.getHth());
		zzjdForm.setXh(xh);
		zzjdForm.setHth(hth);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZjjdzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		if (userType.equalsIgnoreCase("xy")) {
			zzjdForm.setXydm(userDep);
		}
		request.setAttribute("pk", "xh||hth");
		request.setAttribute("hForm", zzjdForm);
		request.setAttribute("userType", userType);
		request.setAttribute("realTable", "zjjd_zxdk_fkxx");
		request.setAttribute("tableName", "view_zjjd_zxdk_fkxx");
		return mapping.findForward("data_fkxx");
	}
	
	/**
	 * 放款信息详细信息页面
	 * fkxx_queryOne ----- 放款信息详细信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fkxx_queryOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzZjjdService service = new XszzZjjdService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String xh = "";//学号
		String hth = "";
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//用户类型是学生则直接获取用户名
		hth = Base.chgNull(request.getParameter("hth"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xh + hth : pkVal;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh) || !"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getFkxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		if ("stu".equalsIgnoreCase(sUserType) || "student".equalsIgnoreCase(sUserType)){
			sUserType = "stu";
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("userType", sUserType);
		return mapping.findForward("fkxx_queryOne");
	}
	
	/**
	 * 保存放款信息
	 * fkxx_save ---- 保存放款信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fkxx_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZjjdzyjsxyActionForm zzjdForm = (XszzZjjdzyjsxyActionForm) form;
		FkxxModel fkxxModel = new FkxxModel();
		BeanUtils.copyProperties(fkxxModel, zzjdForm);
		XszzZjjdService service = new XszzZjjdService();
		boolean bJg = service.saveFkxx(fkxxModel, request);// 保存贷款信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = fkxxModel.getXh();
		String hth = fkxxModel.getHth();
		String pkVal = xh + hth;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getFkxx(pkVal);// 得到贷款详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("fkxx_save");
	}
	
	/**
	 * 放款信息导出
	 * fkxx_exp ----- 放款信息导出
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fkxx_exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzZjjdService service = new XszzZjjdService();
		XszzZjjdzyjsxyActionForm zzjdForm = (XszzZjjdzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, zzjdForm);
		service.getFkxxExp(queryModel, response,request);
		return mapping.findForward("fkxx_exp");
	}
}
