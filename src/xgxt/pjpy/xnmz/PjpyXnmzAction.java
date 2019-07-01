
package xgxt.pjpy.xnmz;

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

public class PjpyXnmzAction extends CommonAction {

	private String xydm = "";
	private String zydm = "";
	private String nj = "";
	/**
	 * 学生成绩绩点数据同步页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjcjjdSjtb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		if (StringUtils.isNull(xnmzForm.getBname())) {
			xnmzForm.setBname("0");
		}
		String bname = xnmzForm.getBname();
		
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_xscjjdb", "xscjjdb");
		if (!StringUtils.isNull(bname) && StringUtils.isEqual("0", bname)) {
			appendTableXx(request, "view_cjb", "view_cjb");
		}
		return mapping.findForward("xscjjdpage");
	}

	/**
	 * 成绩绩点同步
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xjcjjtTb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		PjpyXnmzService service = new PjpyXnmzService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		boolean bFlag = service.stucjjdTbResult();
		if (!bFlag) {
			if ("xy".equalsIgnoreCase(userType)) {
				request.setAttribute("failinfo", "数据同步失败,请与管理员联系!");
			} else {
				request.setAttribute("failinfo", "数据同步失败,请确认WebService-Url配置是否正确!");
			}
		} else {
			request.setAttribute("inserted", "yes");
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_xscjjdb", "xscjjdb");
		String bname = xnmzForm.getBname();
		if (!StringUtils.isNull(bname) && StringUtils.isEqual("0", bname)) {
			appendTableXx(request, "view_cjb", "view_cjb");
		}
		return mapping.findForward("xscjjdpage");
	}
	
	/**
	 * 成绩绩点查询结果
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cjjdQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		String act = request.getParameter("act");
		
		CjjdModel model = new CjjdModel();
		BeanUtils.copyProperties(model, xnmzForm);
		PjpyXnmzService service = new PjpyXnmzService();
		String bname = request.getParameter("bname");
		List<String[]> resList = new ArrayList<String[]>();
		List<HashMap<String, String>> titList = new ArrayList<HashMap<String,String>>();
		if (!StringUtils.isNull(bname) && "0".equalsIgnoreCase(bname)) {
			titList = service.cjTitle();
			resList = service.cjResult(model);
		} else {
			if (!StringUtils.isNull(act) && "shcb".equalsIgnoreCase(act)) {
				titList = service.shcbcjjdTitle();
				resList = service.shcbCjjdResult(model);
			} else {
				resList = service.stucjjdQry(model);
				titList = service.stucjjdTitle();
				String lx = model.getLx();
				if (!StringUtils.isNull(lx) && "1".equalsIgnoreCase(lx)) {
					titList = service.stucjjdTitle1();
				} else if (!StringUtils.isNull(lx) && "3".equalsIgnoreCase(lx)) {
					titList = service.stucjjdTitle2();
				} 
			}
		}
		appendQryResult(request, titList, resList);
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_xscjjdb", "xscjjdb");
	
		if (!StringUtils.isNull(bname) && StringUtils.isEqual("0", bname)) {
			appendTableXx(request, "view_cjb", "view_cjb");
		}
		xnmzForm.setXm(DealString.toGBK(xnmzForm.getXm()));
		return mapping.findForward("xscjjdpage");
	}
	
	/**
	 * 综合素质测评维护页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_sztz_bjjyxx", "sztz_bjxsjyxxb");
		return mapping.findForward("zhszcppage");
	}
	
	public ActionForward zhszcpQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		PjpyXnmzService service = new PjpyXnmzService();
		CjjdModel model = new CjjdModel();
		BeanUtils.copyProperties(model, xnmzForm);
		List<HashMap<String, String>> topList = service.zhszcpTitle(model);
		List<String[]> resList = service.zhszcpResult(model);
		appendResult(request, topList, resList);
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_sztz_bjjyxx", "sztz_bjxsjyxxb");
		xnmzForm.setXm(DealString.toGBK(xnmzForm.getXm()));
		return mapping.findForward("zhszcppage");
	}
	
	public ActionForward tjsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_pjpy_xyjdszb", "pjpy_xyjdszb");
		return mapping.findForward("tjszpage");
	}
	
	public ActionForward tjszQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		PjpyXnmzService service = new PjpyXnmzService();
		CjjdModel model = new CjjdModel();
		BeanUtils.copyProperties(model, xnmzForm);
		List<HashMap<String, String>> topList = service.xfTitle();
		List<String[]> resList = service.xfResult(model);
		appendResult(request, topList, resList);
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_pjpy_xyjdszb", "pjpy_xyjdszb");
		return mapping.findForward("tjszpage");
	}
	
	public ActionForward zyxfAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyxfaddpage");
	}
	
	public ActionForward zyxfsave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		CjjdModel model = new CjjdModel();
		BeanUtils.copyProperties(model, xnmzForm);
		PjpyXnmzService service = new PjpyXnmzService();
		boolean bFlag = service.xfsave(model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		appendProperties(request, xydm, zydm, nj);
		return mapping.findForward("zyxfaddpage");
	}
	
	public ActionForward zyxfmodi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		String pkValue = request.getParameter("pkValue");
		PjpyXnmzService service = new PjpyXnmzService();
		HashMap<String, String> rs = service.xfview(pkValue);
		String act = request.getParameter("act");
		if (!StringUtils.isNull(act) && "view".equalsIgnoreCase(act)) {
			request.setAttribute("act", act);
		}
		xnmzForm.setXn(rs.get("xn"));
		xnmzForm.setNj(rs.get("nj"));
		xnmzForm.setXydm(rs.get("xydm"));
		xnmzForm.setZydm(rs.get("zydm"));
		xnmzForm.setJd(rs.get("jd"));
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("zyxfmodipage");
	}
	
	public ActionForward zyxfmodisave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		CjjdModel model = new CjjdModel();
		BeanUtils.copyProperties(model, xnmzForm);
		String pkValue = request.getParameter("pkValue");
		PjpyXnmzService service = new PjpyXnmzService();
		HashMap<String, String> rs = new HashMap<String, String>();
		boolean bFlag = service.xfUpdate(pkValue, model, request);
		if (bFlag) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
		request.setAttribute("rs", rs);
		appendProperties(request, xydm, zydm, nj);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("zyxfmodipage");
	}
	
	public ActionForward xfdel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		PjpyXnmzService service = new PjpyXnmzService();
		String res = service.xfDel(xnmzForm.getCbv(), request);
		if (StringUtils.isNull(res)) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
			request.setAttribute("failinfo", "操作完成,其中第" + res + "条记录删除失败!");
		}
		appendProperties(request, xydm, zydm, nj);
		appendTableXx(request, "view_pjpy_xyjdszb", "pjpy_xyjdszb");
		return mapping.findForward("tjszpage");
	}
	
	/**
	 * 奖学金审核页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("jxjshpage");
	}
	
	public ActionForward jxjshQry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		JxjModel model = new JxjModel();
		BeanUtils.copyProperties(model, xnmzForm);
		JxjService service = new JxjService();
		List<HashMap<String, String>> topList = service.xyjxjshQryTitle(userType);
		List<String[]> resList = service.xyjxjshQryResult(model, userType);
		appendQryResult(request, topList, resList);
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("jxjshpage");
	}
	
	public ActionForward jxjshres(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXnmzActionForm xnmzForm = (PjpyXnmzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			xnmzForm.setXydm(xydm);
		}
		String res = request.getParameter("param1");
		JxjModel model = new JxjModel();
		model.setXn(request.getParameter("xn"));
		model.setJxjdm(request.getParameter("jxjdm"));
		JxjService service = new JxjService();
		String rs = service.xyjxjshRes(xnmzForm.getCbv(), res, request, model, userType);
		if (StringUtils.isNull(rs)) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
			request.setAttribute("failinfo", rs);
		}
		appendTableXx(request, "view_xsjxjb", "xsjxjb");
		appendProperties(request, xydm, zydm, nj);
		appendJxjList(request);
		return mapping.findForward("jxjshpage");
	}
}
