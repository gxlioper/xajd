
package xgxt.rcsw.zzlgdx;

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

import com.sun.org.apache.bcel.internal.generic.RETURN;
import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.SaveForm;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江理工Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 周觅</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-11-27</p>
 */
public class RcswAction extends BaseAction {
	
	/**
	 * 在校证明信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delZxzmxx(actionForm.getPk());
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getZxzmTit();
		List<String[]> resList = service.getZxzmRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getZxzmResNum(queryModel, request)));
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "rcsw_zzlgdx_zxzmxx");
		request.setAttribute("tableName", "view_rcsw_zzlgdx_zxzmxx");
		request.setAttribute("path", "zzlgdx_rcsw.do?method=zxzmDate");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxzmDate");
	}
	
	/**
	 * 在校证明信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getZxzmExp(queryModel, response, request);
		return mapping.findForward("zxzmExp");
	}
	
	/**
	 * 在校证明编辑页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xh : pkVal;
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getZxzmxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("lrsj", GetTime.getSystemTime());
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("path", "zzlgdx_rcsw.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("zxzmEdit");
	}
	
	/**
	 * 保存在校证明信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmEditSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		ZxzmModel model = new ZxzmModel();
		BeanUtils.copyProperties(model, actionForm);
		RcswZzlgdxService service = new RcswZzlgdxService();
		boolean bJg = service.saveZxzmxx(model, request);// 保存信息
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getZxzmxx(xh);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("lrsj", GetTime.getSystemTime());
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", xh);
		return mapping.findForward("zxzmEditSave");
	}
	
	/**
	 * 在校证明打印页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmDy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		RcswZzlgdxService service = new RcswZzlgdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getZxzmxx(actionForm.getXh());
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("zxzmDy");
	}

	/**
	 * 出国(境)信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();

		if (StringUtils.isEqual(userType, "stu")
				|| StringUtils.isEqual(userType, "student")) {
			cgjStuDate(mapping,form,request,response);
			return mapping.findForward("cgjStuDate");
		}
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delCgjxx(actionForm.getPk());
			request.setAttribute("msg","删除成功！");
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getCgjTit();
		List<String[]> resList = service.getCgjRes(queryModel, request,
				actionForm, false);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getCgjResNum(queryModel, request, false)));
		
		request.setAttribute("path", "zzlgdx_rcsw.do?method=cgjDate");
		String title = FormModleCommon.getWriteAbleAndTitle(request)[1];
		
		request.setAttribute("title", title);
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "rcsw_zzlgdx_cgjzm");
		request.setAttribute("tableName", "view_rcsw_zzlgdx_cgjzm");
		return mapping.findForward("cgjDate");
	}
	
	/**
	 * 出国(境)学生信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void cgjStuDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		RcswZzlgdxService service = new RcswZzlgdxService();
		String xh = session.getAttribute("userName").toString();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delCgjxx(new String[] { actionForm.getGuid() });
		}

		List<HashMap<String, String>> topList = service.getCgjStuTit();
		List<String[]> resList = service.getCgjStuRes(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);

		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", actionForm);
	}
	
	/**
	 * 出国(境)信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getCgjExp(queryModel, response, request);
		return mapping.findForward("cgjExp");
	}
	
	/**
	 * 出国(境)申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		RcswZzlgdxService service = new RcswZzlgdxService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh) || !"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getCgjxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("sqsj", GetTime.getSystemTime());
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("cgjsq");
	}
	
	/**
	 * 保存出国(境)申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		CgjModel model = new CgjModel();
		BeanUtils.copyProperties(model, actionForm);
		String[] bJg = service.saveCgjSqxx(model, request);// 保存信息
		if ("1".equalsIgnoreCase(bJg[0])) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getCgjxx(bJg[1]);
		if (stuMap.size() == 0) {
			stuMap = service.getStu(xh);// 得到学生信息
			stuMap.put("sqsj", GetTime.getSystemTime());
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", bJg[1]);
		return mapping.findForward("cgjsqSave");
	}
	
	/**
	 * 出国(境)查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getCgjxx(pkVal);// 得到详细信息
		}

		request.setAttribute("rs", stuMap);
		return mapping.findForward("cgjQuery");
	}
	
	/**
	 * 出国(境)打印页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjDy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getCgjxx(Base.chgNull(request.getParameter("pkVal"), "", 1));
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("cgjDy");
	}
	
	/**
	 * 出国(境)审核页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		String msg = null;
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
				msg = service.delCgjxx(actionForm.getPk());
				queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			msg = service.modCgjxx(actionForm.getPk(), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			msg = service.modCgjxx(actionForm.getPk(), "不通过", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		
		if(msg != null){
			request.setAttribute("msg",msg);
		}
		
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getCgjTit();
		List<String[]> resList = service.getCgjRes(queryModel, request,
				actionForm, true);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
		actionForm.setXysh(DealString.toGBK(actionForm.getXysh()));
		actionForm.setXxsh(DealString.toGBK(actionForm.getXxsh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getCgjResNum(queryModel, request, true)));
		
		request.setAttribute("path", "zzlgdx_rcsw.do?method=cgjsh");
		String title = FormModleCommon.getWriteAbleAndTitle(request)[1];
		
		request.setAttribute("title", title);
		request.setAttribute("pk", "guid");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "rcsw_zzlgdx_cgjzm");
		request.setAttribute("tableName", "view_rcsw_zzlgdx_cgjzm");
		return mapping.findForward("cgjsh");
	}
	
	/**
	 * 出国(境)信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjShExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		queryModel.setUserType(request.getSession().getAttribute("userType").toString());
		service.getCgjShExp(queryModel, response, request);
		return mapping.findForward("");
	}
	
	/**
	 * 出国(境)审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getCgjxx(pkVal);

		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("cgjshOne");
	}
	
	/**
	 * 保存出国(境)审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cgjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		CgjModel model = new CgjModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveCgjShxx(model, request);// 保存审核信息
		if (bJg) {
			request.setAttribute("ok", "操作成功！");
		} else {
			request.setAttribute("ok", "操作失败！");
		}

		String pkVal = model.getGuid();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getCgjxx(pkVal);

		actionForm.setXysh(stuMap.get("xysh"));
		actionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("cgjshSave");
	}

	/**
	 * 请假信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();

		if (StringUtils.isEqual(userType, "stu")
				|| StringUtils.isEqual(userType, "student")) {
			qjStuDate(mapping,form,request,response);
			return mapping.findForward("qjStuDate");
		}
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delQjxx(actionForm.getPk());
			request.setAttribute("msg","删除成功！");
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getQjTit();
		List<String[]> resList = service.getQjRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
		actionForm.setQjlx(DealString.toGBK(actionForm.getQjlx()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getQjResNum(queryModel, request)));
		
		request.setAttribute("path", "zzlgdx_rcsw.do?method=qjDate");
		String title = FormModleCommon.getWriteAbleAndTitle(request)[1];
		
		request.setAttribute("title", title);
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "rcsw_zzlgdx_qjsqb");
		request.setAttribute("tableName", "view_rcsw_zzlgdx_qjsqb");
		return mapping.findForward("qjDate");
	}
	
	/**
	 * 请假学生信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void qjStuDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		RcswZzlgdxService service = new RcswZzlgdxService();
		String xh = session.getAttribute("userName").toString();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delQjxx(new String[] { actionForm.getGuid() });
		}

		List<HashMap<String, String>> topList = service.getQjStuTit();
		List<String[]> resList = service.getQjStuRes(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);

		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", actionForm);
	}
	
	/**
	 * 请假信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getQjExp(queryModel, response, request);
		return mapping.findForward("qjExp");
	}
	
	/**
	 * 请假申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		RcswZzlgdxService service = new RcswZzlgdxService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh) || !"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getQjxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("sqsj", GetTime.getSystemTime());
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("qjsq");
	}
	
	/**
	 * 保存请假申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		QjModel model = new QjModel();
		BeanUtils.copyProperties(model, actionForm);
		String[] bJg = service.saveQjSqxx(model, request);// 保存信息
		if ("1".equalsIgnoreCase(bJg[0])) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getQjxx(bJg[1]);
		if (stuMap.size() == 0) {
			stuMap = service.getStu(xh);// 得到学生信息
			stuMap.put("sqsj", GetTime.getSystemTime());
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", bJg[1]);
		return mapping.findForward("qjsqSave");
	}
	
	/**
	 * 请假查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getQjxx(pkVal);// 得到详细信息
		}

		request.setAttribute("rs", stuMap);
		return mapping.findForward("qjQuery");
	}
	
	/**
	 * 请假打印页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjDy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getQjxx(Base.chgNull(request.getParameter("pkVal"),
				"", 1));

		String qjksrq = Base.chgNull(stuMap.get("qjksrq"), "--", 1);
		qjksrq = qjksrq.replaceAll("-", "年");
		qjksrq = qjksrq.substring(0, qjksrq.lastIndexOf("年")) + "月"
				+ qjksrq.substring(qjksrq.lastIndexOf("年") + 1) + "日";
		stuMap.put("qjksrq", qjksrq);

		String qjjzrq = Base.chgNull(stuMap.get("qjjzrq"), "--", 1);
		qjjzrq = qjjzrq.replaceAll("-", "年");
		qjjzrq = qjjzrq.substring(0, qjjzrq.lastIndexOf("年")) + "月"
				+ qjjzrq.substring(qjjzrq.lastIndexOf("年") + 1) + "日";
		stuMap.put("qjjzrq", qjjzrq);

		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("qjDy");
	}
	
	/**
	 * 请假审核页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		String msg = null;
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			msg = service.delQjxx(actionForm.getPk());
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			msg = service.modQjxx(actionForm.getPk(), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			msg = service.modQjxx(actionForm.getPk(), "不通过", request);
			queryModel.setGo("go");
		}
		
		if(msg != null){
			request.setAttribute("msg", msg);
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getQjTit();
		List<String[]> resList = service.getQjRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
		actionForm.setQjlx(DealString.toGBK(actionForm.getQjlx()));
		actionForm.setShjg(DealString.toGBK(actionForm.getShjg()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getQjResNum(queryModel, request)));
		request.setAttribute("path", "zzlgdx_rcsw.do?method=qjsh");
		String title = FormModleCommon.getWriteAbleAndTitle(request)[1];
		
		request.setAttribute("title", title);
		request.setAttribute("pk", "guid");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "rcsw_zzlgdx_qjsqb");
		request.setAttribute("tableName", "view_rcsw_zzlgdx_qjsqb");
		return mapping.findForward("qjsh");
	}
	
	/**
	 * 请假审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getQjxx(pkVal);

		actionForm.setShjg(stuMap.get("shjg"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("qjshOne");
	}
	
	/**
	 * 保存请假审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		QjModel model = new QjModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveQjShxx(model, request);// 保存审核信息
		if (bJg) {
			request.setAttribute("ok", "操作成功！");
		} else {
			request.setAttribute("ok", "操作失败！");
		}

		String pkVal = model.getGuid();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getQjxx(pkVal);

		actionForm.setShjg(stuMap.get("shjg"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("qjshSave");
	}
	
	/**
	 * 入伍信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();

		if (StringUtils.isEqual(userType, "stu")
				|| StringUtils.isEqual(userType, "student")) {
			rwStuDate(mapping,form,request,response);
			return mapping.findForward("rwStuDate");
		}
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delRwxx(actionForm.getPk());
			request.setAttribute("msg","删除成功！");
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getRwTit();
		List<String[]> resList = service.getRwRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getRwResNum(queryModel, request)));
		
		request.setAttribute("path", "zzlgdx_rcsw.do?method=rwDate");
		String title = FormModleCommon.getWriteAbleAndTitle(request)[1];
		
		request.setAttribute("title", title);
		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "rcsw_zzlgdx_rwxx");
		request.setAttribute("tableName", "view_rcsw_zzlgdx_rwxx");
		return mapping.findForward("rwDate");
	}
	
	/**
	 * 入伍学生信息
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public void rwStuDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		RcswZzlgdxService service = new RcswZzlgdxService();
		String xh = session.getAttribute("userName").toString();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delRwxx(new String[] { actionForm.getGuid() });
		}

		List<HashMap<String, String>> topList = service.getRwStuTit();
		List<String[]> resList = service.getRwStuRes(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);

		request.setAttribute("pk", "xh");
		request.setAttribute("hForm", actionForm);
	}
	
	/**
	 * 入伍信息导出
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getRwExp(queryModel, response, request);
		return mapping.findForward("rwExp");
	}
	
	/**
	 * 入伍申请页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		RcswZzlgdxService service = new RcswZzlgdxService();
		String sUserName = session.getAttribute("userName").toString();// SESSION中获取用户名
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型
		String xh = "";// 学号
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// 用户类型是学生则直接获取用户名
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh) || !"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getRwxx(pkVal);// 得到详细信息
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// 得到学生信息
				stuMap.put("sqsj", GetTime.getSystemTime());
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("rwsq");
	}
	
	/**
	 * 保存入伍申请信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		RwModel model = new RwModel();
		BeanUtils.copyProperties(model, actionForm);
		String[] bJg = service.saveRwSqxx(model, request);// 保存信息
		if ("1".equalsIgnoreCase(bJg[0])) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getRwxx(bJg[1]);
		if (stuMap.size() == 0) {
			stuMap = service.getStu(xh);// 得到学生信息
			stuMap.put("sqsj", GetTime.getSystemTime());
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", bJg[1]);
		return mapping.findForward("rwsqSave");
	}
	
	/**
	 * 入伍查询页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(pkVal)) {
			stuMap = service.getRwxx(pkVal);// 得到详细信息
		}

		request.setAttribute("rs", stuMap);
		return mapping.findForward("rwQuery");
	}
	
	/**
	 * 入伍打印页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwDy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RcswZzlgdxService service = new RcswZzlgdxService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getRwxx(Base.chgNull(request.getParameter("pkVal"),
				"", 1));

		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("rwDy");
	}
	
	/**
	 * 入伍审核页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		String msg = null;
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			msg = service.delRwxx(actionForm.getPk());
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			msg = service.modRwxx(actionForm.getPk(), "通过", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			msg = service.modRwxx(actionForm.getPk(), "不通过", request);
			queryModel.setGo("go");
		}
		
		if(msg != null){
			request.setAttribute("msg", msg);
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getRwTit();
		List<String[]> resList = service.getRwRes(queryModel, request,
				actionForm);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// 公用ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, false);// 在REQUEST中存放页面加载的列表

		actionForm.setXh(DealString.toGBK(actionForm.getXh()));
		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));
		actionForm.setShjg(DealString.toGBK(actionForm.getShjg()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getRwResNum(queryModel, request)));
		request.setAttribute("path", "zzlgdx_rcsw.do?method=rwsh");
		String title = FormModleCommon.getWriteAbleAndTitle(request)[1];
		
		request.setAttribute("title", title);
		request.setAttribute("pk", "guid");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("realTable", "rcsw_zzlgdx_rwxx");
		request.setAttribute("tableName", "view_rcsw_zzlgdx_rwxx");
		return mapping.findForward("rwsh");
	}
	
	/**
	 * 入伍审核详细页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getRwxx(pkVal);

		actionForm.setShjg(stuMap.get("shjg"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("rwshOne");
	}
	
	/**
	 * 保存入伍审核信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rwshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION中获取用户类型

		RcswZzlgdxService service = new RcswZzlgdxService();
		RcswZzlgdxActionForm actionForm = (RcswZzlgdxActionForm) form;
		RwModel model = new RwModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveRwShxx(model, request);// 保存审核信息
		if (bJg) {
			request.setAttribute("ok", "操作成功！");
		} else {
			request.setAttribute("ok", "操作失败！");
		}

		String pkVal = model.getGuid();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getRwxx(pkVal);

		actionForm.setShjg(stuMap.get("shjg"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("rwshSave");
	}

	
	/**
	 * 在校证明申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		
		String userName = (String) session.getAttribute("userName");
		String userType = (String) session.getAttribute("userType");
		
		ZxzmModel model = new ZxzmModel();
		RcswZzlgdxActionForm myForm = (RcswZzlgdxActionForm) form;
		RcswZzlgdxService service = new RcswZzlgdxService();
		
		String realTable = "rcsw_zxzmb";
		String tableName = "view_rcsw_zxzmb";
		String pk = "xh||'-'||lrsj";
		String xh = request.getParameter("xh");
		String doType = request.getParameter("doType");
		String[] colList = new String[]{"xh","xmmc","shlx","lrsj","zxbx"};
		
		boolean result = false;
		
		if ("stu".equalsIgnoreCase(userType)) {
			xh = userName;
		}
		
		HashMap<String, String> rs = service.getStu(xh);// 得到学生信息
		rs.put("lrsj", GetTime.getNowTime2());
		
		BeanUtils.copyProperties(model, myForm);
		
		//		保存
		if (!Base.isNull(doType) && "save".equalsIgnoreCase(doType)) {
			String pkValue = model.getXh()+"-"+model.getLrsj();
			
			result = service.saveData(realTable, colList, pkValue, model, request);

			request.setAttribute("result", result);
			
			rs.put("xmmc", model.getXmmc());
			rs.put("shlx", model.getShlx());
			rs.put("zxbx", model.getZxbx());
		}
		
		service.getBdZd(realTable, myForm);
		
		service.setList(request, "");
		request.setAttribute("rs", rs);
		return mapping.findForward("zxzmSq");
	}
	
	
	/**
	 * 在校证明管理
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		
		ZxzmModel model = new ZxzmModel();
		RcswZzlgdxActionForm myForm = (RcswZzlgdxActionForm) form;
		RcswZzlgdxService service = new RcswZzlgdxService();
		
		String realTable = "rcsw_zxzmb";
		String tableName = "view_rcsw_zxzmb";
		String pk = "xh||'-'||lrsj";
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "nj", "xymc",
				"zymc", "bjmc", "xmm", "shlx", "xysh", "xxsh", "lrsj" };
		if(Globals.XXDM_WHSYFWXY.equals(Base.xxdm)){
			colList = new String[] { "pk", "xh", "xm", "xb", "nj", "xymc",
					"zymc", "bjmc", "xmm", "fdysh","xysh", "xxsh", "lrsj" };
		}
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List topTr = null;
		
		String doType = request.getParameter("doType");
		boolean result = false;
		
		if ("xy".equals(userType)) {
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		//批量删除所选数据
		if (!Base.isNull(doType) && "del".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("checkVal");

			if (null != checkVal && checkVal.length > 0) {

				result = service.delData(realTable, checkVal, pk);

				request.setAttribute("result", result);
			}
		}
		
		
		//	查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))) {
			
			List<HashMap<String, String>> zdyColList = service.zdyColList(realTable);
			String[] zdyCol = new String[zdyColList.size()];
			String[] zdyColCN = new String[zdyColList.size()];
			
			for (int i = 0; i < zdyColList.size(); i++) {
				zdyCol[i] = zdyColList.get(i).get("zdid");
				zdyColCN[i] = zdyColList.get(i).get("zdmc");
			}
			
			topTr = service.getZdyTopTr(tableName, colList, zdyCol, zdyColCN);
			rs = service.getDataList(tableName, model, colList, zdyCol,
					realTable, new String[] { pk});
			
		}
		
		
		request.setAttribute("path", "zzlgdx_rcsw.do?method=zxzmManage");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);
		service.setList(request, "");
		return mapping.findForward("zxzmManage");
	}
	
	
	/**
	 * 在校证明审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String isFdy = session.getAttribute("isFdy").toString();//判断是否是辅导员
		String xxdm = Base.xxdm;
		
		ZxzmModel model = new ZxzmModel();
		RcswZzlgdxActionForm myForm = (RcswZzlgdxActionForm) form;
		RcswZzlgdxService service = new RcswZzlgdxService();
		
		String realTable = "rcsw_zxzmb";
		String tableName = "view_rcsw_zxzmb";
		String pk = "xh||'-'||lrsj";
		String[] colList = null;
		String[] onezd = null;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List topTr = null;
		
		String doType = request.getParameter("doType");
		String shzt = request.getParameter("shzt");
		boolean result = false;
		
		//武汉商业
		if(Globals.XXDM_WHSYFWXY.equals(xxdm)){
			//权限控制
			if(StringUtils.isEqual(isFdy, "true")){
				onezd = new String[] { "fdysh" };
				colList = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","xmm","fdysh","xysh","xxsh","lrsj"};
				myForm.setXydm(userDep);
				BeanUtils.copyProperties(model, myForm);
				if(!StringUtils.isNull(shzt)){
					model.setFdysh(shzt);
				}
			}else if ("xy".equalsIgnoreCase(userType)) {
				onezd = new String[] { "xysh" };
				colList = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","xmm","xysh","xxsh","lrsj"};
				myForm.setFdysh("已通过");
				myForm.setXydm(userDep);
				BeanUtils.copyProperties(model, myForm);
				if(!StringUtils.isNull(shzt)){
					model.setXysh(shzt);
				}
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				onezd = new String[] { "xxsh" };
				colList = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","xmm","xxsh","lrsj"};
				myForm.setXysh("已通过");
				BeanUtils.copyProperties(model, myForm);
				if(!StringUtils.isNull(shzt)){
					model.setXxsh(shzt);
				}
			} else {
				request.setAttribute("errMsg", "对不起，你无权访问此页！");
				return new ActionForward("/errMsg.do", false);
			}
		}else{
			//权限控制
			if ("xy".equalsIgnoreCase(userType)) {
				onezd = new String[] { "xysh" };
				colList = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","xmm","shlx","xysh","lrsj","xxsh"};
				myForm.setXydm(userDep);
				BeanUtils.copyProperties(model, myForm);
				model.setXysh(shzt);
			} else if ("xx".equalsIgnoreCase(userType)
					|| "admin".equalsIgnoreCase(userType)) {
				onezd = new String[] { "xxsh" };
				colList = new String[]{"pk","xh","xm","xb","nj","xymc","zymc","bjmc","xmm","shlx","xxsh","lrsj"};
				myForm.setShlx("校审");
				myForm.setXysh("已通过");
				BeanUtils.copyProperties(model, myForm);
				model.setXxsh(shzt);
			} else {
				request.setAttribute("errMsg", "对不起，你无权访问此页！");
				return new ActionForward("/errMsg.do", false);
			}
		}
		
		//批量审核
		if (!Base.isNull(doType) && "sh".equalsIgnoreCase(doType)) {
			String[] checkVal = request.getParameterValues("checkVal");

			if (null != checkVal && checkVal.length > 0) {

				SaveForm saveForm = new SaveForm();

				saveForm.setTableName(realTable);
				saveForm.setOnezd(onezd);
				saveForm.setPk(pk);
				saveForm.setPkValue(checkVal);

				result = service.updateZxzm(saveForm, model);

				request.setAttribute("result", result);
			}
		}
		
		//	查询
		if (((request.getParameter("go") != null) && request.getParameter("go")
				.equalsIgnoreCase("go"))) {
			
			List<HashMap<String, String>> zdyColList = service.zdyColList(realTable);
			String[] zdyCol = new String[zdyColList.size()];
			String[] zdyColCN = new String[zdyColList.size()];
			
			for (int i = 0; i < zdyColList.size(); i++) {
				zdyCol[i] = zdyColList.get(i).get("zdid");
				zdyColCN[i] = zdyColList.get(i).get("zdmc");
			}
			
			topTr = service.getZdyTopTr(tableName, colList, zdyCol, zdyColCN);
			rs = service.getDataList(tableName, model, colList, zdyCol,
					realTable, new String[] { pk});
			
		}
		
		service.setList(request, "");
		
		request.setAttribute("path", "zzlgdx_rcsw.do?method=zxzmSh");
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("zxzmSh");
	}
	
	
	/**
	 * 在校证明维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		
		String userType = (String) session.getAttribute("userType");
		String isFdy = session.getAttribute("isFdy").toString();//判断是否是辅导员
		
		ZxzmModel model = new ZxzmModel();
		RcswZzlgdxActionForm myForm = (RcswZzlgdxActionForm) form;
		RcswZzlgdxService service = new RcswZzlgdxService();
		
		String realTable = "rcsw_zxzmb";
		String tableName = "view_rcsw_zxzmb";
		String pk = "xh||'-'||lrsj";
		String pkV = request.getParameter("pk");
		String doType = request.getParameter("doType");
		String[] colList = new String[] { "pk", "xh", "xm", "xb", "zzmm",
				"mzmc", "sfzh", "csrq", "rxrq", "xz", "nj", "xymc", "zymc",
				"bjmc", "xmmc", "shlx", "lrsj","zxbx","xyyj","xxyj" };
		//武汉商业
		if(Globals.XXDM_WHSYFWXY.equals(Base.xxdm)){
			colList = new String[] { "pk", "xh", "xm", "xb", "zzmm",
					"mzmc", "sfzh", "csrq", "rxrq", "xz", "nj", "xymc", "zymc",
					"bjmc", "xmmc", "shlx", "lrsj","zxbx","fdyyj","xyyj","xxyj","fdysh","xysh","xxsh" };
		}
		String[] onezd = null;
		String shzt = request.getParameter("shzt");
		
		HashMap<String , String> rs = new HashMap<String, String>();
		 
		boolean result = false;
		
		BeanUtils.copyProperties(model, myForm);
		
		//加载数据
		if (!Base.isNull(doType) && "view".equalsIgnoreCase(doType)
				|| "sh".equalsIgnoreCase(doType)
				|| "update".equalsIgnoreCase(doType)) {
			
			List<HashMap<String, String>> zdyColList = service.zdyColList(realTable);
			String[] zdyCol = new String[zdyColList.size()];
			
			for (int i = 0; i < zdyColList.size(); i++) {
				zdyCol[i] = zdyColList.get(i).get("zdid");
			}

			rs = service.getOneData(tableName, realTable, colList, zdyCol, pk,
					pkV);
		}
		
		//	修改
		if (!Base.isNull(doType) && "modi".equalsIgnoreCase(doType)) {
			onezd = new String[]{"xh","xmmc","shlx","lrsj","zxbx"};
			
			result = service.updateData(realTable,pk,model, pkV,onezd, request);

			request.setAttribute("result", result);
		}
		
		/* 单个审核 */
		if (!Base.isNull(doType) && "modify".equalsIgnoreCase(doType)) {
			//武汉商业
			if(Globals.XXDM_WHSYFWXY.equals(Base.xxdm)){
				// 权限控制
				if(StringUtils.isEqual(isFdy, "true")){
					onezd = new String[] { "fdyyj", "fdysh" };
					model.setFdysh(shzt);
				}else if ("xy".equalsIgnoreCase(userType)) {
					onezd = new String[] { "xyyj", "xysh" };
					model.setXysh(shzt);
				} else if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					onezd = new String[] { "xxyj", "xxsh" };
					model.setXxsh(shzt);
				} else {
					request.setAttribute("errMsg", "对不起，您无权访问此页！");
					return new ActionForward("/errMsg.do", false);
				}
			}else{
				// 权限控制
				if ("xy".equalsIgnoreCase(userType)) {
					onezd = new String[] { "xyyj", "xysh" };
					model.setXysh(shzt);
				} else if ("xx".equalsIgnoreCase(userType)
						|| "admin".equalsIgnoreCase(userType)) {
					onezd = new String[] { "xxyj", "xxsh" };
					model.setXxsh(shzt);
				} else {
					request.setAttribute("errMsg", "对不起，您无权访问此页！");
					return new ActionForward("/errMsg.do", false);
				}
			}

			SaveForm saveForm = new SaveForm();

			saveForm.setTableName(realTable);
			saveForm.setOnezd(onezd);
			saveForm.setPk(pk);
			saveForm.setPkValue(new String[] { pkV });

			result = service.updateZxzm(saveForm, model);

			request.setAttribute("result", result);
		}
		
		service.getBdZd(realTable, myForm);
		
		service.setList(request, "");
		
		request.setAttribute("pk", pkV);
		request.setAttribute("doType", doType);
		request.setAttribute("rs", rs);
		return mapping.findForward("zxzmSq");
	}

	
	/**
	 * 自定义版在校证明打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zxzmPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZzlgdxActionForm myForm = (RcswZzlgdxActionForm) form;
		RcswZzlgdxService service = new RcswZzlgdxService();
		
		HashMap<String, String> stuMap = service.getStu(myForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("zxzmPrint");
	}
	
	/**
	 * 在校证明打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * author lyl
	 */
	public ActionForward xszxzmPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> rxMap = new HashMap<String, String>();
		HashMap<String, String> blMap = new HashMap<String, String>();
		HashMap<String, String> byMap = new HashMap<String, String>();

		String doType = request.getParameter("doType");
		
		doType = (doType == null || "".equalsIgnoreCase(doType)) ? "" : doType.trim();

		String xh = DealString.toGBK(request.getParameter("xh").trim());
		String rxrq = dao.getOneRs("select rxrq from view_xsxxb where xh=?",new String[] { xh }, "rxrq");
		String blrq = dao.getOneRs("select to_char(sysdate,'yyyy-mm-dd') str from dual",new String[] {}, "str");
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xz = dao.getOneRs("select xz from view_xsjbxx where xh=?",new String[] { xh }, "xz");
		String dqN = dao.getOneRs("select substr(to_char(sysdate,'yyyy-mm-dd'),0,4) dqN from dual",new String[] {}, "dqN");//当前年
		String dqY = dao.getOneRs("select substr(to_char(sysdate,'yyyy-mm-dd'),6,2) dqY from dual",new String[] {}, "dqY");//当前月

		String sql = "select xh,xm,xb,nj,xymc,zymc,bjmc from view_xsjbxx where xh=?";
		String[] colList = { "xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc" };
		rs = dao.getMap(sql, new String[] { xh }, colList);
		String rxny = dao.getOneRs("select rxrq from view_xsxxb where xh=?",new String[] { xh }, "rxrq");

		String rxN = "";
		String rxY = "";
		String rxR = "";
		if (rxny == null || "".equalsIgnoreCase(rxny)) {
			request.setAttribute("message", "入学年月数据错误！");
			request.setAttribute("url", "");
			return mapping.findForward("zxzmFalse");
		}
		if(rxny.length()==8){
			rxN = rxny.substring(0, 4);
			rxY = rxny.substring(4, 6);
			rxR = rxny.substring(6, 8);
		} else if (rxny.length() == 10) {
			rxN = rxny.substring(0, 4);
			rxY = rxny.substring(6, 7);
			rxR = rxny.substring(8,10);
		} else {
			// dataSearchForm.setErrMsg("入学年月数据错误！");
			request.setAttribute("message", "入学年月数据错误,数据格式(yyyymmdd 或yyyy-mm-dd)！");
			request.setAttribute("url", "");
			return mapping.findForward("zxzmFalse");
		}
		
		if(xz == null || "".equalsIgnoreCase(xz)){
			request.setAttribute("message", "无学制信息！");
			request.setAttribute("url", "");
			return mapping.findForward("zxzmFalse");
		}
		rxMap.put("xz", xz);
		rxMap.put("rxN", rxN);
		rxMap.put("rxY", rxY);
		rxMap.put("rxR", rxR);
		byMap.put("byN", rxN.equalsIgnoreCase("0000") ? "" : String.valueOf(Integer.parseInt(rxN) + Integer.parseInt(xz)));
		byMap.put("byN", rxN);
		byMap.put("byY", rxY);
		byMap.put("byR", rxR);

		blMap.put("blN", blrq.substring(0, 4));
		blMap.put("blY", blrq.substring(5, blrq.lastIndexOf("-")));
		blMap.put("blR", blrq.substring(blrq.lastIndexOf("-") + 1, blrq.length()));		

		int n = Integer.parseInt(dqN) - Integer.parseInt(rxN);

		int y = Integer.parseInt(dqY) - Integer.parseInt(rxY);
//		if (y < 0 && n!=1) {			
//			nj = String.valueOf(n - 1);
//		} else if(n==0){
//			nj = String.valueOf(n+1);
//		}else {
//			nj = String.valueOf(n);
//		}
		
//		if (y < 0) {			
//			nj = String.valueOf("0"+n);
//		} else{
//			nj = String.valueOf(n+1);
//		}
		if(rs.get("nj") != null){
			 nj=rs.get("nj").substring(2, 4);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("rxMap", rxMap);
		request.setAttribute("blMap", blMap);
		request.setAttribute("byMap", byMap);
		request.setAttribute("nj", nj);
		request.setAttribute("rxrq", rxrq);
		request.setAttribute("blrq", blrq);
        ActionForward act = mapping.findForward("xszxzmPrint_all");
        return act;
	}
}
