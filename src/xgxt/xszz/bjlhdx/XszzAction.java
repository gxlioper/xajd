package xgxt.xszz.bjlhdx;

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
 * <p>Description: 北京联合大学学生资助Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-04-23</p>
 */
public class XszzAction extends BaseAction {

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
		XszzBjlhService service = new XszzBjlhService();
		String sUserName = session.getAttribute("userName").toString();//SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String userDep = session.getAttribute("userDep").toString();//SESSION中获取用户部门
		String xh = "";//学号
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//用户类型是学生则直接获取用户名
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
			}
		}
		stuMap.put("xn", Base.currXn);//当前学年
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
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, xszzBjlhActionForm);
		XszzBjlhService service = new XszzBjlhService();
		boolean bJg = service.saveGjzxjSqxx(gjzxjModel, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxjModel.getXh();
		String xn = gjzxjModel.getXn();
		String pkVal = xn + xh;
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
		
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		XszzBjlhService service = new XszzBjlhService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, xszzBjlhActionForm);
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
		
		XszzBjlhService service = new XszzBjlhService();
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
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
			xszzBjlhActionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, xszzBjlhActionForm);
		List<HashMap<String, String>> topList = service.getGjzxjTit();
		List<String[]> resList = service.getGjzxjRes(queryModel,request);
		String xh = DealString.toGBK(xszzBjlhActionForm.getXh());
		xszzBjlhActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzBjlhActionForm);
		commonAction.appendProperties(request, commenBean, false);//在REQUEST中存放页面加载的列表
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			xszzBjlhActionForm.setXn(Base.currXn);
		}
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", xszzBjlhActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_bjlhdx_gjzxj");
		request.setAttribute("tableName", "view_xszz_bjlhdx_gjzxj");
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
		
		XszzBjlhService service = new XszzBjlhService();
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, xszzBjlhActionForm);
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
		XszzBjlhService service = new XszzBjlhService();
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		String sUserType = session.getAttribute("userType").toString();//SESSION中获取用户类型
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		xszzBjlhActionForm.setXysh(stuMap.get("xysh"));
		xszzBjlhActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("gjzxjList", service.getGjzxjList());
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
		XszzBjlhdxActionForm xszzBjlhActionForm = (XszzBjlhdxActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, xszzBjlhActionForm);
		XszzBjlhService service = new XszzBjlhService();
		boolean bJg = service.saveGjzxjShxx(gjzxjModel, request);// 保存困难生审核信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxjModel.getXh();
		String xn = gjzxjModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		xszzBjlhActionForm.setXysh(stuMap.get("xysh"));
		xszzBjlhActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshSave");
	}
}
