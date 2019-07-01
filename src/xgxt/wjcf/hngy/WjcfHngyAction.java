
package xgxt.wjcf.hngy;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class WjcfHngyAction extends CommonAction {

	String xydm="";
	String zydm="";
	String nj="";
	
	/**
	 * 日常行为记录
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rcxwjl(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfHngyActionForm hngyForm = (WjcfHngyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			hngyForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);
		hngyForm.setXm(DealString.toGBK(hngyForm.getXm()));
		appendTableXx(request, "view_wjcf_rcxwjlb", "wjcf_rcxwjlb");
		return mapping.findForward("rcxwjlpage");
	}
	
	/**
	 * 日常行为记录查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rcxwjlQry(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfHngyActionForm hngyForm = (WjcfHngyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			hngyForm.setXydm(xydm);
		}
		hngyForm.setXh(request.getParameter("xh"));
		RcxwjlModel model = new RcxwjlModel();
		BeanUtils.copyProperties(model, hngyForm);
		RcxwjlService service = new RcxwjlService();
		List<HashMap<String, String>> topList = service.title();
		List<String[]> resList = service.result(model);
		appendResult(request, topList, resList);
		appendProperties(request, xydm, zydm, nj);
		hngyForm.setXm(DealString.toGBK(hngyForm.getXm()));
		appendTableXx(request, "view_wjcf_rcxwjlb", "wjcf_rcxwjlb");
		return mapping.findForward("rcxwjlpage");
	}
	
	public ActionForward rcxwAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		RcxwjlService service = new RcxwjlService();
		if (!StringUtils.isNull(xh)) {
			rs = service.stuinfo(xh);
		}
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		return mapping.findForward("rcxwaddpage");
	}
	
	public ActionForward rcxwSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfHngyActionForm hngyForm = (WjcfHngyActionForm) form;
		hngyForm.setXh(request.getParameter("xh"));
		RcxwjlModel model = new RcxwjlModel();
		RcxwjlService service = new RcxwjlService();
		HashMap<String, String> rs = new HashMap<String, String>();
		BeanUtils.copyProperties(model, hngyForm);
		boolean bFlag = service.save(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			rs = service.stuinfo(model.getXh());
		}
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		hngyForm.setWjqk(DealString.toGBK(hngyForm.getWjqk()));
		hngyForm.setBz(DealString.toGBK(hngyForm.getBz()));
		return mapping.findForward("rcxwaddpage");
	}
	
	public ActionForward rcxwModi(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfHngyActionForm hngyForm = (WjcfHngyActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		HashMap<String, String> rs = new HashMap<String, String>();
		RcxwjlService service = new RcxwjlService();
		rs = service.view(pkValue);
		if (!StringUtils.isNull(act) && StringUtils.isEqual("view", act)) {
			request.setAttribute("act", "view");
		}
		hngyForm.setXn(rs.get("xn"));
		hngyForm.setXq(rs.get("xq"));
		hngyForm.setNd(rs.get("nd"));
		hngyForm.setWjsj(rs.get("wjsj"));
		hngyForm.setBz(rs.get("bz"));
		hngyForm.setWjqk(rs.get("wjqk"));
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("rcxwmodipage");
	}
	
	public ActionForward rcxwmodiSave(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("pkValue");
		WjcfHngyActionForm hngyForm = (WjcfHngyActionForm) form;
		HashMap<String, String> rs = new HashMap<String, String>();
		RcxwjlService service = new RcxwjlService();
		hngyForm.setXh(request.getParameter("xh"));
		RcxwjlModel model = new RcxwjlModel();
		BeanUtils.copyProperties(model, hngyForm);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("rs", rs);
		boolean bFlag = service.update(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			rs = service.view(pkValue);
			request.setAttribute("inserted", "no");
		}
		hngyForm.setWjqk(DealString.toGBK(hngyForm.getWjqk()));
		hngyForm.setBz(DealString.toGBK(hngyForm.getBz()));
		request.setAttribute("rs", rs);
		return mapping.findForward("rcxwmodipage");
	}
	
	public ActionForward rcxwdel(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfHngyActionForm hngyForm = (WjcfHngyActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			hngyForm.setXydm(xydm);
		}
		RcxwjlService service = new RcxwjlService();
		String res = service.delete(hngyForm.getCbv(), request);
		if (StringUtils.isNull(res)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "操作完成,其中第" + res + "条记录删除失败!");
		}
		appendProperties(request, xydm, zydm, nj);
		hngyForm.setXm(DealString.toGBK(hngyForm.getXm()));
		appendTableXx(request, "view_wjcf_rcxwjlb", "wjcf_rcxwjlb");
		return mapping.findForward("rcxwjlpage");
	}
	
	
}
