/*
 * @className: XljkXlcyjgAction.java
 * 
 * @time: 2010-4-30
 * 
 * @copyRight hz-zf
 * 
 */

package xgxt.xljk.tyb.action;

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

import common.GlobalsVariable;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.UserTypePd;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;
import xgxt.xljk.tyb.model.XljkXlcyjgModel;
import xgxt.xljk.tyb.service.XljkXlcyjgService;

/**
 * 心理健康 - 心理测验结果维护所用的ACTION, 包括对大学生人格因素心理测试结果和卡特尔人格因素测试结果的维护
 * 
 * @author lt
 * @version 1.0 2010-4-30
 */
public class XljkXlcyjgAction extends CommonAction {

	/**
	 * 大学生人格因素测验结果维护
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rgyswjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();

		setXydm(xljkForm, request);// 设置默认学院代码

		/* 查询数据操作 */
		if (QUERY.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			rs = service.queryDxsrgysjgResult(model, isFdyAndGetName(request));
			topTr = service.queryDxsrgysclJgTitle();
			
			/* 查询大学生人格因子不存在的因子名称 */
			request.setAttribute("yzmc", service.queryDxsyzNotExistsDB());
		} else if (DELETE.equalsIgnoreCase(xljkForm.getAct())) {
			appendOperResult(request, service.deletDxsrgyscyXx(xljkForm.getCbv(), "dxsrgyscyb"));
		}

		appendOperQx(request, "xljk_tyb_rgyswjwh.do"); // 设置页面读写权
		appendQryResult(request, topTr, rs); // 设置页面查询结果
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		FormModleCommon.getWriteAbleAndTitle(request);
		
		return mapping.findForward("rgyswjwh");
	}
	
	/**
	 * 增加大学生人格测验结果信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addDxsrgyscy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if (StringUtils.isNotNull(xljkForm.getXh())) {
			rs = CommonQueryDAO.getStuInfo(xljkForm.getXh());
		}
		
		/* 保存数据操作 */
		if (SAVE.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			appendOperResult(request, service.addDxsrgyscyXx(model));
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("addDxsrgyscy");
	}
	
	/**
	 * 显示和修改大学生人格测验结果信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateDxsrgyscy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();
		List<String[]> pyList = new ArrayList<String[]>();
		
		/* 保存数据操作 */
		if (SAVE.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			appendOperResult(request, service.updateDxsrgyscyXx(model, pkValue));
		} else if (VIEW.equalsIgnoreCase(xljkForm.getAct())) {//显示数据读写权
			request.setAttribute("writable", "view");
		}
		
		/* 显示数据 */
		rs = service.viewDxsrgyscyXx(pkValue);
		if (!rs.isEmpty()) {
			BeanUtils.copyProperties(xljkForm, rs);
			
			/* 显示与基础代码维护中对应的详细评语 */
			pyList = service.queryDxsrgpyResult(rs);
			request.setAttribute("yzmc", service.queryDxsyzNotExistsDB());
		}
		
		request.setAttribute("pyList", pyList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("updateDxsrgyscy");
	}
	
	/**
	 * 卡特尔人格因素心里测验结果维护
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ktewjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();

		setXydm(xljkForm, request);// 设置默认学院代码

		/* 查询数据操作 */
		if (QUERY.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			rs = service.queryKtergysResult(model, isFdyAndGetName(request));
			topTr = service.queryKtergysTitle();
			
			/* 未维护的卡特尔因子代码 */
			request.setAttribute("yzmc", service.queryKteyzNotExistsDB());
		} else if (DELETE.equalsIgnoreCase(xljkForm.getAct())) {
			appendOperResult(request, service.deletDxsrgyscyXx(xljkForm.getCbv(), "ktergysjcb"));
		}

		appendOperQx(request, "xljk_tyb_ktewjwh.do"); // 设置页面读写权
		appendQryResult(request, topTr, rs); // 设置页面查询结果
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
		FormModleCommon.getWriteAbleAndTitle(request);
		
		return mapping.findForward("ktewjwh");
	}
	
	/**
	 * 增加卡特尔人格因素心里测验信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addKtewjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();
		HashMap<String, String> rs = new HashMap<String, String>();
		
		if (StringUtils.isNotNull(xljkForm.getXh())) {
			rs = CommonQueryDAO.getStuInfo(xljkForm.getXh());
		}
		
		/* 保存数据操作 */
		if (SAVE.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			appendOperResult(request, service.addKtergysxx(model));
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("addKtewjwh");
	}
	
	/**
	 * 修改卡特尔人格因素心里测验信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward updateKtewjwh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		XljkXlcyjgActionForm xljkForm = (XljkXlcyjgActionForm) form;
		XljkXlcyjgService service = new XljkXlcyjgService();
		List<String[]> pyList = new ArrayList<String[]>();
		
		/* 保存数据操作 */
		if (SAVE.equalsIgnoreCase(xljkForm.getAct())) {
			XljkXlcyjgModel model = new XljkXlcyjgModel();
			BeanUtils.copyProperties(model, xljkForm);
			appendOperResult(request, service.updateKtergysxx(model, pkValue));
		} else if (VIEW.equalsIgnoreCase(xljkForm.getAct())) {//显示数据读写权
			request.setAttribute("writable", "view");
		}
		
		/* 显示数据 */
		rs = service.viewKtergysxx(pkValue);
		if (!rs.isEmpty()) {
			BeanUtils.copyProperties(xljkForm, rs);
			pyList = service.queryKteyzpyResult(rs);
			/* 未维护的卡特尔因子代码 */
			request.setAttribute("yzmc", service.queryKteyzNotExistsDB());
		}
		
		request.setAttribute("pyList", pyList);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("updateKtewjwh");
	}

	/**
	 * 设置学院用户默认的学院代码，但是如果是辅导员用户则不用设置
	 * @param form
	 * @param request
	 */
	private void setXydm(XljkXlcyjgActionForm form, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		String userDep = session.getAttribute("userDep") != null ? session
				.getAttribute("userDep").toString() : "";
		String fdyQx = session.getAttribute("fdyQx") != null ? session
				.getAttribute("fdyQx").toString() : "false";
				
		if (GlobalsVariable.XTDM_XY.equalsIgnoreCase(userType)
				&& !Boolean.parseBoolean(fdyQx)) {
			form.setXydm(userDep);
		}
	}
	
	private String isFdyAndGetName(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String isFdy = session.getAttribute("isFdy") != null ? session
				.getAttribute("isFdy").toString() : "false";
		if (UserTypePd.isFdy(isFdy)) {
			return session.getAttribute("userName").toString();
		} 
		return "";
	}
}
