package xgxt.pjpy.zjcm.cssz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.GlobalsVariable;

import xgxt.action.Base;
import xgxt.pjpy.zjcm.PjpyZjcmActionForm;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyZjcmCsszAction extends CommonAction {

	
	/**
	 * 人数比例设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward rsblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		
		//用于判断非学校用户不能进入该模块
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(request.getSession()
				.getAttribute("userType").toString())
				|| "true".equalsIgnoreCase(request.getSession().getAttribute(
						"isFdy").toString())
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(request
						.getSession().getAttribute("userType").toString())) {
			request.setAttribute("errMsg", "当前用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		
		setXydm(request, zjcmForm);
		setPjxnndxq(zjcmForm);
		
		if (StringUtils.isNull(zjcmForm.getKey())) {
			zjcmForm.setKey(GlobalsVariable.PJPY_JXJ);
		}
		
		//查询数据操作
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if (QUERY.equalsIgnoreCase(zjcmForm.getAct())) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, zjcmForm);
			topTr = service.queryJxjrsTitle(zjcmForm.getKey());
			rs = (ArrayList<String[]>) service.queryJxjrsResult(model, zjcmForm.getKey());
		}
		
		//设置相关作用域数据
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("dmList", service.getDmList(zjcmForm.getKey(),
				zjcmForm.getJxjlb()));
		request.setAttribute("lbList", service.getJxjlbList(true));
		request.setAttribute("cpfwList", service.getCpfwList(false));
		return mapping.findForward("rsblsz");
	}	
	
	/**
	 * 奖学金比例批量设置功能
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjblPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		HashMap<String, String> rs = new HashMap<String, String>();
		//设置默认的评奖学年，学期，年度
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxq());
		rs.put("nd", Base.getJxjsqnd());
		
		//保存数据操作
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, zjcmForm);
			model.setKey(GlobalsVariable.PJPY_JXJ);
			appendOperResult(request, service.updateJxjrs(model));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_JXJ,
				zjcmForm.getJxjlb()));
		request.setAttribute("lbList", service.getJxjlbList(true));
		return mapping.findForward("jxjblPlsz");
	}
	
	/**
	 * 基础数据初始化
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward baseDataInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String lb = request.getParameter("lb");
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		appendOperResult(request, service.baseDataInit(lb));
		//根据传过来的类别来判断跳转页面
		if (GlobalsVariable.PJPY_JXJ.equalsIgnoreCase(lb)) {
			return rsblsz(mapping, form, request, response);
		} else {
			return rychRsblsz(mapping, form, request, response);
		}
		
	}

	/**
	 * 奖学金单个人数调整
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		String pkValue = zjcmForm.getPkValue();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		//保存数据操作
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			appendOperResult(request, service.updateJxjDgtzrs(pkValue, zjcmForm
					.getJxjrs()));
		} else if (VIEW.equalsIgnoreCase(request.getParameter("act"))) {//设置读写权限
			request.setAttribute("writable", "view");
		}
		
		//查询单个数据操作
		rs = service.queryDgJxjrsxx(pkValue);
		rs.put("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_JXJ,
				zjcmForm.getJxjlb()));
		request.setAttribute("lbList", service.getJxjlbList(true));
		return mapping.findForward("jxjrstz");
	}
	
	/**
	 * 奖学金比例汇总表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjblFpb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
		BeanUtils.copyProperties(model, zjcmForm);
		//设置默认的评奖学年，学期，年度
		model.setXn(Base.getJxjsqxn());
		model.setXq(Base.getJxjsqxq());
		model.setNd(Base.getJxjsqnd());
		model.setXymc(request.getParameter("xymc"));
		
		WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(response); 
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		service.exportJxjblFpb(wwb, model);
		return mapping.findForward("");
	}
	
	/**
	 * 荣誉称号人数比例设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychRsblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		
		//用于判断非学校用户不能进入该模块
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(request.getSession()
				.getAttribute("userType").toString())
				|| "true".equalsIgnoreCase(request.getSession().getAttribute(
						"isFdy").toString())
				|| GlobalsVariable.XTDM_STUDENT.equalsIgnoreCase(request
						.getSession().getAttribute("userType").toString())) {
			request.setAttribute("errMsg", "当前用户无权访问该页面！");
			return new ActionForward("/errMsg.do", false);
		}
		
		setXydm(request, zjcmForm);
		setPjxnndxq(zjcmForm);
		
		if (StringUtils.isNull(zjcmForm.getKey())) {
			zjcmForm.setKey(GlobalsVariable.PJPY_RYCH);
		}
		
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		//查询数据操作
		if (QUERY.equalsIgnoreCase(zjcmForm.getAct())) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, zjcmForm);
			topTr = service.queryJxjrsTitle(zjcmForm.getKey());
			rs = (ArrayList<String[]>) service.queryJxjrsResult(model, zjcmForm.getKey());
		}
		
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("dmList", service.getDmList(zjcmForm.getKey(),
				zjcmForm.getJxjlb()));
		request.setAttribute("cpfwList", service.getCpfwList(false));
		return mapping.findForward("rychRsblsz");
	}
	
	/**
	 * 荣誉称号比例批量设置
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychblPlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		HashMap<String, String> rs = new HashMap<String, String>();
		//设置默认的评奖学年，学期，年度
		rs.put("xn", Base.getJxjsqxn());
		rs.put("xq", Base.getJxjsqxq());
		rs.put("nd", Base.getJxjsqnd());
		
		//保存数据操作
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {
			PjpyZjcmCsszModel model = new PjpyZjcmCsszModel();
			BeanUtils.copyProperties(model, zjcmForm);
			model.setKey(GlobalsVariable.PJPY_RYCH);
			appendOperResult(request, service.updateJxjrs(model));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_RYCH,
				zjcmForm.getJxjlb()));
		
		return mapping.findForward("rychblPlsz");
	}
	
	/**
	 * 荣誉称号单个人数调整
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyZjcmActionForm zjcmForm = (PjpyZjcmActionForm) form;
		String pkValue = zjcmForm.getPkValue();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		PjpyZjcmCsszService service = new PjpyZjcmCsszService();
		if (SAVE.equalsIgnoreCase(request.getParameter("act"))) {//保存数据操作
			appendOperResult(request, service.updateJxjDgtzrs(pkValue, zjcmForm
					.getJxjrs()));
		} else if (VIEW.equalsIgnoreCase(request.getParameter("act"))) {//设置读写权限
			request.setAttribute("writable", "view");
		}
		//查询单个数据
		rs = service.queryDgJxjrsxx(pkValue);
		rs.put("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("cpfwList", service.getCpfwList(false));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("dmList", service.getDmList(GlobalsVariable.PJPY_RYCH,
				zjcmForm.getJxjlb()));
		
		return mapping.findForward("rychrstz");
	}
	
	//如果是学院用户则设置默认部门代码
	public void setXydm(HttpServletRequest request, PjpyZjcmActionForm form) {
		String userType = request.getSession().getAttribute("userType").toString();
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(userType)) {
			form.setXydm(request.getAttribute("userDep").toString());
		}
	}
	
	//设置评奖学年，年度，学期
	public void setPjxnndxq(PjpyZjcmActionForm form) {
		if (StringUtils.isNull(form.getXn())) {
			form.setXn(Base.getJxjsqxn());
		}
		if (StringUtils.isNull(form.getNd())) {
			form.setNd(Base.getJxjsqnd());
		}
		if (StringUtils.isNull(form.getXq())) {
			form.setXq(Base.getJxjsqxq());
		}
	}
}
