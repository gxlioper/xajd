package xgxt.pjpy.scjz;

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

import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyScjzAction extends CommonAction {

	private PjpyScjzService service = PjpyScjzService.getInstance();
	
	@SuppressWarnings("unused")
	private String xydm;
	@SuppressWarnings("unused")
	private String zydm;
	@SuppressWarnings("unused")
	private String nj;
	
	/**
	 * 奖学金申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyScjzActionForm scjzForm = (PjpyScjzActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String xh = "";
		if ("stu".equalsIgnoreCase(userType) || "student".equalsIgnoreCase(userType)) {
			xh = session.getAttribute("userName").toString();
		} else {
			xh = scjzForm.getXh();
		}
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = session.getAttribute("userDep").toString();
		} else {
			xydm = scjzForm.getXydm();
		}
		zydm = scjzForm.getZydm();
		nj = scjzForm.getNj();
		HashMap<String, String> rs = new HashMap<String, String>();
		List<String[]> cfList = new ArrayList<String[]>();
		String act = request.getParameter("act");
		if (!StringUtils.isNull(xh)) {
			rs = service.getStuDetailsInfo(xh);//获取学生基本信息加成绩信息
			cfList = service.getStuWjcfInfo(xh);//获取学生评奖学年的处分信息
			if (cfList.size() > 0) {
				request.setAttribute("cfflag", "该生在评奖学年有违纪记录，不符合申请条件！");
			}
			String cjflag = service.chkCjtj(rs);//检查在评奖学年是否有成绩不及格
			if (!StringUtils.isNull(cjflag)) {
				request.setAttribute("cjflag", cjflag);
			}
			if (rs != null && !"save".equalsIgnoreCase(act)) {
				scjzForm.setWysp(rs.get("wysp"));
				scjzForm.setJsjsp(rs.get("jsjsp"));
				scjzForm.setSjhm(rs.get("sjhm"));
				scjzForm.setKhss(rs.get("khss"));
				scjzForm.setKh(rs.get("kh"));
				scjzForm.setDrzw(rs.get("drzw"));
			}
		}
		
		if ("save".equalsIgnoreCase(act)) {//保存数据
			ScjzModel model = new ScjzModel();
			BeanUtils.copyProperties(model, scjzForm);
			boolean bFlag = service.saveJxjsqInfo(model, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			scjzForm.setDrzw(DealString.toGBK(scjzForm.getDrzw()));
			scjzForm.setJsjsp(DealString.toGBK(scjzForm.getJsjsp()));
			scjzForm.setWysp(DealString.toGBK(scjzForm.getWysp()));
			scjzForm.setKhss(DealString.toGBK(scjzForm.getKhss()));
			scjzForm.setSqly(DealString.toGBK(scjzForm.getSqly()));
		}
		request.setAttribute("rs", rs);
		request.setAttribute("cfList", cfList);
		appendJxjList(request);
		appendPjxnxqnd(request);
		return mapping.findForward("jxjsqpage");
	}
	
	/**
	 * 奖学金修改页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyScjzActionForm scjzForm = (PjpyScjzActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("view".equalsIgnoreCase(act)){
			rs = service.viewJxjSqInfo(pkValue);
			scjzForm.setJxjdm(rs.get("jxjdm"));
			scjzForm.setDrzw(rs.get("drzw"));
			scjzForm.setJsjsp(rs.get("jsjsp"));
			scjzForm.setWysp(rs.get("wysp"));
			scjzForm.setKhss(rs.get("khss"));
			scjzForm.setKh(rs.get("kh"));
			scjzForm.setSqly(rs.get("sqly"));
			scjzForm.setSjhm(rs.get("sjhm"));
			scjzForm.setJxjlb(rs.get("jxjlb"));
			scjzForm.setJlje(rs.get("jlje"));
		} 
		if ("save".equalsIgnoreCase(act)) {
			scjzForm.setXh(request.getParameter("xh"));
			ScjzModel model = new ScjzModel();
			BeanUtils.copyProperties(model, scjzForm);
			boolean bFlag = service.updateJxjsqInfo(model, pkValue, request);
			if (bFlag) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
			}
			scjzForm.setDrzw(DealString.toGBK(scjzForm.getDrzw()));
			scjzForm.setJsjsp(DealString.toGBK(scjzForm.getJsjsp()));
			scjzForm.setWysp(DealString.toGBK(scjzForm.getWysp()));
			scjzForm.setKhss(DealString.toGBK(scjzForm.getKhss()));
			scjzForm.setSqly(DealString.toGBK(scjzForm.getSqly()));
		}
		HashMap<String, String> rss = new HashMap<String, String>();
		List<String[]> cfList = new ArrayList<String[]>();
		String xh = rs.get("xh");
		if (!StringUtils.isNull(xh)) {
			rss = service.getStuDetailsInfo(xh);//获取学生基本信息加成绩信息
			cfList = service.getStuWjcfInfo(xh);//获取学生评奖学年的处分信息
			if (cfList.size() > 0) {
				request.setAttribute("cfflag", "该生在评奖学年有违纪记录，不符合申请条件！");
			}
			String cjflag = service.chkCjtj(rss);//检查在评奖学年是否有成绩不及格
			if (!StringUtils.isNull(cjflag)) {
				request.setAttribute("cjflag", cjflag);
			}
			if (rss != null && !"save".equalsIgnoreCase(act)) {
//				scjzForm.setWysp(rss.get("wysp"));
//				scjzForm.setJsjsp(rss.get("jsjsp"));
//				scjzForm.setSjhm(rss.get("sjhm"));
//				scjzForm.setKhss(rss.get("khss"));
//				scjzForm.setKh(rss.get("kh"));
//				scjzForm.setDrzw(rss.get("drzw"));
			}
		}
		request.setAttribute("rss", rss);
		request.setAttribute("rs", rs);
		request.setAttribute("cfList", cfList);
		appendJxjList(request);
		appendPjxnxqnd(request);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjmodipage");
	}
	
	/**
	 * 奖学金单个审核（辅导员，学院，学校）
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String isFdy = session.getAttribute("isFdy").toString();
		PjpyScjzActionForm scjzForm = (PjpyScjzActionForm) form;
		String pkValue = request.getParameter("pkValue");
		String act = request.getParameter("act");
		HashMap<String, String> rs = new HashMap<String, String>();
		HashMap<String, String> rss = new HashMap<String, String>();
		List<String[]> cfList = new ArrayList<String[]>();
		if ("view".equalsIgnoreCase(act)) {
			rs = service.getJxjshInfo(pkValue);
			cfList = service.getStuWjcfInfo(rs.get("xh"));//获取学生评奖学年的处分信息
			rss = service.getStuDetailsInfo(rs.get("xh"));//获取学生基本信息加成绩信息
			scjzForm.setCxcj(rs.get("cxcj"));
			String ys = "";
			String yj = "";
			String cj = rs.get("cxcj");
			if ("xy".equalsIgnoreCase(userType)) {
				if ("true".equalsIgnoreCase(isFdy)) {
					scjzForm.setYesNo(rs.get("fdysh"));
					scjzForm.setShyj(rs.get("fdyyj"));
					ys = rs.get("fdysh");
					yj = rs.get("fdyyj");
					
				} else {
					scjzForm.setYesNo(rs.get("xysh"));
					scjzForm.setShyj(rs.get("xyshyj"));
					ys = rs.get("xysh");
					yj = rs.get("xyshyj");
				}
			} else {
				scjzForm.setYesNo(rs.get("xxsh"));
				scjzForm.setShyj(rs.get("xxshyj"));
				ys = rs.get("xxsh");
				yj = rs.get("xxshyj");
			}
			request.setAttribute("ys", ys);
			request.setAttribute("yj", yj);
			request.setAttribute("cj", cj);
		}
		if ("save".equalsIgnoreCase(act)) {
			
			String jxjdm = request.getParameter("jxjdm");
			String jlje = request.getParameter("jlje");
			String userDep = "";
			if ("xy".equalsIgnoreCase(userType)) {
				userDep = session.getAttribute("userDep").toString();
			}
			String flag = service.jxjsh(userType, isFdy, request
					.getParameter("cxcj"), request.getParameter("yesNo"),
					request.getParameter("shyj"), pkValue, jxjdm, jlje,userDep);
			if (StringUtils.isNull(flag)) {
				request.setAttribute("inserted", "yes");
			} else {
				request.setAttribute("inserted", "no");
				String[] je = service.getJe(flag);
				request.setAttribute("failinfo", "审核失败，该奖学金审核通过金额已超学校设置总金额.\n学校设置总金额："+je[1] + "\n当前审核总金额："+je[0]);
			}
			scjzForm.setCxcj(DealString.toGBK(scjzForm.getCxcj()));
			scjzForm.setYesNo(DealString.toGBK(scjzForm.getYesNo()));
			scjzForm.setShyj(DealString.toGBK(scjzForm.getShyj()));
			rs = service.getJxjshInfo(pkValue);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("rss", rss);
		request.setAttribute("cfList", cfList);
		appendPjxnxqnd(request);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("jxjshpage");
	}
}
