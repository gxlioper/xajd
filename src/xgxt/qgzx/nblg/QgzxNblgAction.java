package xgxt.qgzx.nblg;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.DealString;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 宁波理工勤工助学模块Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-14</p>
 */
public class QgzxNblgAction extends DispatchAction {
	
	/**
	 * 宁波理工学生岗位申请表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printXsgwsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		QgzxNblgForm model = new QgzxNblgForm();
		QgzxNblgService service = new QgzxNblgService();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xmdm = DealString.toGBK(request.getParameter("xmdm"));
		String kcjqgzxsj = DealString.toGBK(request.getParameter("kcjqgzxsj"));
		String xn = DealString.toGBK(request.getParameter("xn"));
		String nd = DealString.toGBK(request.getParameter("nd"));
		String xq = DealString.toGBK(request.getParameter("xq"));
		String bz = DealString.toGBK(request.getParameter("bz"));
		
		model.setXh(xh);
		model.setXmdm(xmdm);
		model.setKcjqgzxsj(kcjqgzxsj);
		model.setXn(xn);
		model.setNd(nd);
		model.setXq(xq);
		model.setBz(bz);
		
		request.setAttribute("rs", service.getXsgwsqInfo(model));
		return mapping.findForward("xsgwsqb");
	}
	
	/**
	 * 宁波理工岗位申请表打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printGwsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		QgzxNblgForm model = new QgzxNblgForm();
		QgzxNblgService service = new QgzxNblgService();
		HttpSession session = request.getSession(false);
		String bmdm = session.getAttribute("userDep") == null ? "" : session.getAttribute("userDep").toString();;
		String sqdw = DealString.toGBK(request.getParameter("sqdw"));
		String lxdh = DealString.toGBK(request.getParameter("lxdh"));
		String gznr = DealString.toGBK(request.getParameter("gznr"));
		String xyrs = DealString.toGBK(request.getParameter("xyrs"));
		String gzsj = DealString.toGBK(request.getParameter("gzsj"));
		String bz = DealString.toGBK(request.getParameter("bz"));
		String gwdm = DealString.toGBK(request.getParameter("gwdm"));
		String myqgzxsj = DealString.toGBK(request.getParameter("myqgzxsj"));
		String mssj = DealString.toGBK(request.getParameter("mssj"));
		String tsyq = DealString.toGBK(request.getParameter("tsyq"));
		String dwzp = DealString.toGBK(request.getParameter("dwzp"));
		String rylsqk = DealString.toGBK(request.getParameter("rylsqk"));
		String gwxz = DealString.toGBK(request.getParameter("gwxz"));
		String fzr = DealString.toGBK(request.getParameter("lxr"));
		
		model.setSqdw(sqdw);
		model.setLxdh(lxdh);
		model.setGznr(gznr);
		model.setGzsj(gzsj);
		model.setXyrs(xyrs);
		model.setBz(bz);
		model.setGwdm(gwdm);
		model.setMyqgzxsj(myqgzxsj);
		model.setMssj(mssj);
		model.setTsyq(tsyq);
		model.setDwzp(dwzp);
		model.setRylsqk(rylsqk);
		model.setBmdm(bmdm);
		model.setFzr(fzr);
		
		request.setAttribute("rs", service.getGwsqInfo(model));
		request.setAttribute("gwxz", gwxz);
		return mapping.findForward("gwsqb");
	}
	

	/**
	 * 宁波理工学生考勤表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printYkqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		QgzxNblgForm model = new QgzxNblgForm();
		QgzxNblgService service = new QgzxNblgService();
		HttpSession session = request.getSession(false);
		String xh = request.getParameter("xh");
		String gwmc = DealString.toGBK(request.getParameter("gwmc"));
		String gwsbsj = DealString.toGBK(request.getParameter("gwsbsj"));
		String bmdm = session.getAttribute("userDep") == null ? "" : session.getAttribute("userDep").toString();
		model.setXh(xh);
		model.setGwdm(gwmc);
		model.setBmdm(bmdm);
		model.setGwsbsj(gwsbsj);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		try {
			model.setPath(servlet.getServletContext().getRealPath(""));
			model.setOs(response.getOutputStream());
			service.printYkqb_ser(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("blank");
	}
	
	/**
	 * 宁波理工学生人员汇总表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printYyhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		QgzxNblgForm model = new QgzxNblgForm();
		QgzxNblgService service = new QgzxNblgService();
		HttpSession session = request.getSession();
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String userName = session.getAttribute("userName").toString();
		model.setXn(xn);
		model.setXq(xq);
		model.setUserName(userName);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		try {
			model.setPath(servlet.getServletContext().getRealPath(""));
			model.setOs(response.getOutputStream());
			service.printYyhz_ser(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("blank");
	}
	
	/**
	 * 宁波理工勤工助学月报表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward printYbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		QgzxNblgForm model = new QgzxNblgForm();
		QgzxNblgService service = new QgzxNblgService();	
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		try {
			model.setPath(servlet.getServletContext().getRealPath(""));
			model.setOs(response.getOutputStream());
			model.setUserName(userName);
			service.printYbb_ser(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("blank");
	}
}
