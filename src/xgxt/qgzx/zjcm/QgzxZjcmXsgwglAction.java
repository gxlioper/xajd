/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package xgxt.qgzx.zjcm;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.base.DealString;
import xgxt.qgzx.form.QgzxForm;
import xgxt.qgzx.service.XsgwglService;

public class QgzxZjcmXsgwglAction extends DispatchAction {
	/** 
	 * 学生岗位申请
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward gwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		XsgwglService service = new XsgwglService();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		String doType = request.getParameter("doType");
		
		if(doType != null && "modi".equalsIgnoreCase(doType)){//申请信息修改
			return gwsqModi(mapping,form,request,response);
		}
		String xh = request.getParameter("xh");
		xh = userOnLine != null && "student".equalsIgnoreCase(userOnLine) ? userName : xh;
		
		service.freeTimeTableZjcm(xh, request);
		
		request.setAttribute("rs1", service.getQgzxConfig());
		request.setAttribute("rs", service.getStuInfo(xh));
		request.setAttribute("gwList", service.getKsqgw());
		request.setAttribute("do", "no");
		return mapping.findForward("gwsq");
	}
	
	/** 
	 * 学生岗位申请信息修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward gwsqModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgwglService service = new XsgwglService();
		QgzxForm model =(QgzxForm) form;		
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
				
		model.setPk("xh||gwdm||sqsj");
		model.setPkValue(pkValue);
		
		HashMap<String, String> rs = service.getStuGwxx(model);//获取学生申请岗位的信息
		
		request.setAttribute("kxList", service.getFreeTimeList(rs.get("xh")));//获取学生可参加勤工助学的时间信息
		request.setAttribute("rs1", rs);
		request.setAttribute("rs", rs);
		request.setAttribute("gwList", service.getKsqgw());
		request.setAttribute("do", "yes");
		return mapping.findForward("gwsq");
	}
	
	/** 
	 * 保存学生岗位申请信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward saveGwsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		QgzxForm model = (QgzxForm)form;
		XsgwglService service = new XsgwglService();
		String userName = session.getAttribute("userName").toString();
		String userOnLine = session.getAttribute("userOnLine").toString();
		
		model.setXh(userOnLine != null && "student".equalsIgnoreCase(userOnLine) ? userName : model.getXh());
		
		request.setAttribute("result", service.saveGwsq(model, request));//信息保存
		
		model.setPk("xh||gwdm||'-'||gwsbsj");
		model.setPkValue(DealString.toGBK(model.getXh())+DealString.toGBK(model.getXmdm()));		
		HashMap<String, String> rs = service.getStuGwxx(model);//获取学生申请岗位详细信息 
		
		request.setAttribute("rs1", rs);
		request.setAttribute("rs", rs);		
		request.setAttribute("kxList", service.getFreeTimeList(model.getXh()));
		request.setAttribute("do", request.getParameter("do"));
		request.setAttribute("gwList", service.getKsqgw());
		return mapping.findForward("gwsq");
	}
	
	/** 
	 * 打印学生岗位申请表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 */
	public ActionForward printGwsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgwglService service = new XsgwglService();
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xmdm = DealString.toGBK(request.getParameter("xmdm"));
		String lxdh = DealString.toGBK(request.getParameter("lxdh"));
		String jsjsp = DealString.toGBK(request.getParameter("jsjsp"));
		String sffcfp = DealString.toGBK(request.getParameter("sffcfp"));
		String yhtc = DealString.toGBK(request.getParameter("yhtc"));
		String mqqgzxqk = DealString.toGBK(request.getParameter("mqqgzxqk"));
		
		HashMap<String, String> map = new HashMap<String, String>();
		map = service.getStuInfo(xh);
		
		map.put("lxdh", lxdh);
		map.put("jsjsp", jsjsp);
		map.put("sffcfp", sffcfp);
		map.put("yhtc", yhtc);
		map.put("mqqgzxqk", mqqgzxqk);
		map.putAll(service.getGwxxByPk(xmdm));
		
		request.setAttribute("kxList", service.getFreeTimeList(xh));
		request.setAttribute("rs", map);
		return mapping.findForward("printGwsqb");
	}
	
	
}