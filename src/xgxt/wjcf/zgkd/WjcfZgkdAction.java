package xgxt.wjcf.zgkd;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

import java.util.*;

public class WjcfZgkdAction extends CommonAction {

	private WjcfZgkdService service = WjcfZgkdService.getInstance();
	
	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO 自动生成方法存根
		try {
			HttpSession session = request.getSession();
			/** 在线检测 */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "系统出现错误，请重新登录！" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
		return super.execute(mapping, form, request, response);
	}
	
	/**
	 * 跟踪教育默认页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZgkdActionForm zgkdForm = (WjcfZgkdActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString(); 
		String userDep = session.getAttribute("userDep") == null ? ""
				: session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		zgkdForm.setXydm(xydm);
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		String act = request.getParameter("act");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		List<String[]> rs = new ArrayList<String[]>();
		if ("qry".equalsIgnoreCase(act)){
			WjcfZgkdModel model = new WjcfZgkdModel();
			BeanUtils.copyProperties(model, zgkdForm);
			topTr = service.getGzjyTitle();
			rs = service.getGzjyResult(model);
		} else if ("del".equalsIgnoreCase(act)) {
			String res = service.gzjyDelete(zgkdForm.getCbv());
			if (StringUtils.isNull(res)) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
				request.setAttribute("failInfo", "操作完成,其中第" + rs + "条数据删除失败!");
			}
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		appendCflbCfyy(request);
		request.setAttribute("tableName", "view_wjcf_zgkd_gzjy");
		request.setAttribute("realTable", "wjcf_zgkd_gzjyb");
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("gzjypage");
	}
	
	/**
	 * 选择违纪处分学生页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward wjstuInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZgkdActionForm zgkdForm = (WjcfZgkdActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") == null ? ""
				: session.getAttribute("userType").toString(); 
		String userDep = session.getAttribute("userDep") == null ? ""
				: session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		} else {
			xydm = request.getParameter("xydm");
		}
		zgkdForm.setXydm(xydm);
		zydm = request.getParameter("zydm");
		nj = request.getParameter("nj");
		String act = request.getParameter("act");
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		List<String[]> rs = new ArrayList<String[]>();
		if ("qry".equalsIgnoreCase(act)){
			WjcfZgkdModel model = new WjcfZgkdModel();
			BeanUtils.copyProperties(model, zgkdForm);
			topTr = service.getStuwjcfInfo();
			rs = service.getStuwjcfResult(model);
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size() : 0);
		appendCflbCfyy(request);
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("stuwjcfinfo");
	}
	
	/**
	 * 跟踪教育增加页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZgkdActionForm zgkdForm = (WjcfZgkdActionForm) form;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.getStuwjcfByPk(pkValue);
		}
		zgkdForm.setPkValue(pkValue);
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			WjcfZgkdModel model = new WjcfZgkdModel();
			BeanUtils.copyProperties(model, zgkdForm);
			boolean bFlag = service.gzjyAdd(model, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		}
		zgkdForm.setDd(DealString.toGBK(zgkdForm.getDd()));
		zgkdForm.setThjg(DealString.toGBK(zgkdForm.getThjg()));
		zgkdForm.setThxj(DealString.toGBK(zgkdForm.getThxj()));
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		return mapping.findForward("addpage");
	}

	/**
	 * 跟踪教育显示修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gzjyView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		WjcfZgkdActionForm zgkdForm = (WjcfZgkdActionForm) form;
		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		
		zgkdForm.setPkValue(pkValue);
		String act = request.getParameter("act");
		if ("save".equalsIgnoreCase(act)) {
			WjcfZgkdModel model = new WjcfZgkdModel();
			BeanUtils.copyProperties(model, zgkdForm);
			boolean bFlag = service.gzjyUpdate(model, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
		} else if ("view".equalsIgnoreCase(act)) {
			request.setAttribute("act", "view");
		}
		if (!StringUtils.isNull(pkValue)) {
			rs = service.gzjyView(pkValue);
			zgkdForm.setSj(rs.get("sj"));
			zgkdForm.setDd(rs.get("dd"));
			zgkdForm.setThjg(rs.get("thjg"));
			zgkdForm.setThxj(rs.get("thxj"));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("viewpage");
	}
	
}
