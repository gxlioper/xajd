package xgxt.pjpy.jhzy.sqsh;

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
import org.apache.struts.actions.DispatchAction;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.pjpy.jhzy.JhzyPjpyForm;
import xgxt.pjpy.jhzy.JhzyPjpyUnit;
import xgxt.pjpy.jhzy.pjsz.PjszService;
import xgxt.utils.Fdypd;

public class JxjsqshAction  extends DispatchAction {
	JxjsqshService myService = new JxjsqshService();
	
	public ActionForward sqManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.setAttribute("jxjList",myService.getJxjList());
		return mapping.findForward("sqManage");
	}
	
	public ActionForward shManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.setAttribute("jxjList",myService.getJxjList());
		return mapping.findForward("shManage");
	}
	
	public ActionForward cxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		request.setAttribute("jxjList",myService.getJxjList());
		return mapping.findForward("cxManage");
	}
	
	
	public ActionForward jxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String jxjdm = request.getParameter("jxjdm");
		String [] jxjxx = myService.getJxjXx(jxjdm);
		String jxjmc = jxjxx[0];
		String jxjlb = jxjxx[1];
		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("jxjmc", jxjmc);
		request.setAttribute("jxjlb", jxjlb);
		JxjsqshService service = new JxjsqshService();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userOnLine = request.getSession().getAttribute("userOnLine")
				.toString();
		String userName = request.getSession().getAttribute("userName")
				.toString();
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String act = DealString.toGBK(request.getParameter("act"));
		JxjsqshModel model = new JxjsqshModel();
		if ("student".equalsIgnoreCase(userOnLine)) {
			xh = userName;
		}
		if (xn == null||xn.equalsIgnoreCase("")) {
			xn = Base.getJxjsqxn();
		}
		myForm.setXn(xn);
		request.setAttribute("xn", xn);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("act", act);
		request.setAttribute("pk", DealString.toGBK(request.getParameter("pk")));
		String xq = request.getParameter("xq");
		if (xq == null) {
			xq = Base.getJxjsqxq();
		}
		myForm.setXq(xq);
		if(jxjmc.equalsIgnoreCase("国家奖学金")){
			if ("save".equalsIgnoreCase(doType)) {
				PjszService szService = new PjszService();
				boolean done = szService.isTjsz(xh,xn,"","jxj",jxjdm);
				if(done){
					BeanUtils.copyProperties(model, myForm);
					done = service.JxjGjjxjSave(model,request);
					request.setAttribute("done", done);
				}else{
					request.setAttribute("done", "tjbfh");
				}
			}
			request.setAttribute("rs", service.serv_getStuInfo(xh));
			request.setAttribute("rsJxj", service.serv_getXsJxjInfo(request.getParameter("pk")));
			request.setAttribute("rsCjList", service.getCjList(xh,xn));
			return mapping.findForward("sqGjJxj");
		}else if(jxjmc.matches("优秀学生一等奖学金")||jxjmc.matches("优秀学生二等奖学金")||jxjmc.matches("优秀学生三等奖学金")){
			if ("save".equalsIgnoreCase(doType)) {
				BeanUtils.copyProperties(model, myForm);
				PjszService szService = new PjszService();
				boolean done = szService.isTjsz(xh,xn,xq,"jxj",jxjdm);
				if(done){
					done = service.JxjCommonSave(model,request);
					request.setAttribute("done", done);
				}else{
					request.setAttribute("done", "tjbfh");
				}
			}
			request.setAttribute("xqmc",Base.getJxjsqxqmc());
			request.setAttribute("xqList", Base.getXqList());
			request.setAttribute("rs", service.serv_getStuInfo(xh));
			request.setAttribute("rsJxj", service.serv_getXsJxjInfo(request.getParameter("pk")));
			return mapping.findForward("sqCommon");
		}else if(jxjmc.matches("康恩贝自强奖学金")){
			if ("save".equalsIgnoreCase(doType)) {
				BeanUtils.copyProperties(model, myForm);
				PjszService szService = new PjszService();
				boolean done = szService.isTjsz(xh,xn,"","jxj",jxjdm);
				if(done){
					done = service.JxjKnbSave(model,request);
					request.setAttribute("done", done);
				}else{
					request.setAttribute("done", "tjbfh");
				}
			}
			request.setAttribute("rs", service.serv_getStuInfo(xh));
			request.setAttribute("rsJxj", service.serv_getXsJxjInfo(request.getParameter("pk")));
			return mapping.findForward("sqKnbJxj");
		}else{
			if ("save".equalsIgnoreCase(doType)) {
				BeanUtils.copyProperties(model, myForm);
				PjszService szService = new PjszService();
				boolean done = szService.isTjsz(xh,xn,"","jxj",jxjdm);
				if(done){
					done = service.JxjCommonSave(model,request);
					request.setAttribute("done", done);
				}else{
					request.setAttribute("done", "tjbfh");
				}
			}
			request.setAttribute("rs", service.serv_getStuInfo(xh));
			request.setAttribute("rsJxj", service.serv_getXsJxjInfo(request.getParameter("pk")));
			return mapping.findForward("sqCommon");
		}
	}
	
	
	public ActionForward jxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
	JhzyPjpyUnit unit = new JhzyPjpyUnit();
	JxjsqshService service = new JxjsqshService();
	HttpSession session = request.getSession();
	JhzyPjpyForm myForm = (JhzyPjpyForm) form;
	String userType = (String) session.getAttribute("userType");
	String userDep = (String) session.getAttribute("userDep");
	String userName = session.getAttribute("userName").toString();
	ArrayList<String[]> rs = new ArrayList<String[]>();
	List<HashMap<String, String>> topTr = null;
	String xn = request.getParameter("xn");
	String jxjdm = request.getParameter("jxjdm");
	String [] jxjxx = myService.getJxjXx(jxjdm);
	String jxjmc = jxjxx[0];
	request.setAttribute("jxjdm", jxjdm);
	request.setAttribute("jxjmc", jxjmc);
	unit.setNjXyZyBjList(request, myForm);
	if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
		// 辅导员登录
		request.setAttribute("bjList", Fdypd.getFdybjList(userName));
		request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
		request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
		userType = "isFdy";
		myForm.setXydm("");
	}
	if (xn == null) {
		xn = Base.getJxjsqxn();
		myForm.setXn(xn);
	}
	if ("xy".equalsIgnoreCase(userType)) {
		myForm.setXydm(userDep);
	}
	if ("go".equalsIgnoreCase(request.getParameter("go"))) {
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		JxjsqshModel model = new JxjsqshModel();
		BeanUtils.copyProperties(model, myForm);
		topTr = service.getJxjshTopTr();
		rs = service.getJxjshList(userType, userName, model,jxjdm);
	}
	request.setAttribute("rs", rs);
	request.setAttribute("topTr", topTr);
	request.setAttribute("rsNum", rs.size());
	request.setAttribute("userType", userType);
	request.setAttribute("userName", userName);
	if(jxjmc.matches("优秀学生一等奖学金")||jxjmc.matches("优秀学生二等奖学金")||jxjmc.matches("优秀学生三等奖学金")){
		request.setAttribute("xqDis", "true");
	}
	return mapping.findForward("jxjsh");
	}
	
	/**
	 * 奖学金单个审核
	 */
	public ActionForward jxjChek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		JxjsqshService service = new JxjsqshService();
		
		String pkValue = request.getParameter("pkValue");
		String jxjdm = request.getParameter("jxjdm");
		HashMap<String, String> map = new HashMap<String, String>();
		String findForward = "";
//		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String userType = (String) session.getAttribute("userType");
		String doType = request.getParameter("doType");
		
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			userType = "isFdy";
		}
        if("save".equalsIgnoreCase(doType)){//保存审核结果
        	boolean done=false;
        	String fdyshyj = DealString.toGBK(request.getParameter("fdyyj"));
        	 String xyshyj = DealString.toGBK(request.getParameter("xyshyj"));
        	 String xxshyj = DealString.toGBK(request.getParameter("xxshyj"));
        	 String yesNo = DealString.toGBK(request.getParameter("yesNo"));      	 
        	 done=service.serv_JxjsqshChk(userType, pkValue, jxjdm, fdyshyj, xyshyj, xxshyj, yesNo);
        	 request.setAttribute("done",done);
        }
        map = service.serv_getJxjInfo(pkValue);
        String [] jxjxx = myService.getJxjXx(jxjdm);
		String jxjmc = jxjxx[0];
		if(jxjmc.equalsIgnoreCase("国家奖学金")){
			findForward = "gjJxjChek";
		}else if(jxjmc.matches("优秀学生一等奖学金")||jxjmc.matches("优秀学生二等奖学金")||jxjmc.matches("优秀学生三等奖学金")){
			request.setAttribute("xqmc",Base.getJxjsqxqmc());
			request.setAttribute("xqList", Base.getXqList());
			findForward = "commonJxjChek";
		}else if(jxjmc.matches("康恩贝自强奖学金")){
			findForward = "knbJxjChek";
		}else{
			findForward = "commonJxjChek";
		}
		if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			map.put("yesNo",map.get("xxsh"));
		}else if("xy".equalsIgnoreCase(userType)){
			map.put("yesNo",map.get("xysh"));
		}else{
			map.put("yesNo",map.get("fdysh"));
		}
		request.setAttribute("shDjList",service.serv_getShDjList(map.get("xh")));
		request.setAttribute("shJxjList",service.serv_getShJxjList(map.get("xh")));
		request.setAttribute("cjList",service.serv_getCjList(map.get("xh")));
		request.setAttribute("rs", service.serv_getStuInfo(map.get("xh")));
		request.setAttribute("rsJxj", map);
		request.setAttribute("chkList",service.serv_getChkList());
		request.setAttribute("userType",userType);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("jxjmc", jxjmc);
		request.setAttribute("jxjdm",jxjdm);
		return mapping.findForward(findForward);
	}
	
	/**
	 * 奖学金批量审核
	 */
	public ActionForward JxjsqshCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JxjsqshService service = new JxjsqshService();
		HttpSession session  = request.getSession();
		String check = request.getParameter("check");
		String userType = session.getAttribute("userType").toString();
		String pkVStr = request.getParameter("pkVStr");
		String jxjdm = request.getParameter("jxjdm");
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			userType = "isFdy";
		}
		service.serv_JxjsqshCk(userType, check, jxjdm, pkVStr);
		return jxjsh(mapping, form, request, response);
	}
	
	/**
	 * 奖学金查询
	 */
	public ActionForward jxjQuery(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JhzyPjpyUnit unit = new JhzyPjpyUnit();
		JxjsqshService service = new JxjsqshService();
		HttpSession session = request.getSession();
		JhzyPjpyForm myForm = (JhzyPjpyForm) form;
		String jxjdm = request.getParameter("jxjdm");
		String [] jxjxx = myService.getJxjXx(jxjdm);
		String jxjmc = jxjxx[0];
		request.setAttribute("jxjdm", jxjdm);
		request.setAttribute("jxjmc", jxjmc);
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		String xn = request.getParameter("xn");
		unit.setNjXyZyBjList(request, myForm);
		
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			// 辅导员登录
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));
			request.setAttribute("xyList", Fdypd.getFdyXyList(userName));
			userType = "isFdy";
			myForm.setXydm("");
		}
		if (xn == null) {
			xn = Base.getJxjsqxn();
			myForm.setXn(xn);
		}
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()));
			myForm.setXm(DealString.toGBK(myForm.getXm()));
			JxjsqshModel model = new JxjsqshModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getJxjshTopTr();
			rs = service.serv_getJxjCxList(userType, userName, model,jxjdm);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("tableName","view_xsjxjb");
		request.setAttribute("realTable","xsjxjb"); 
		// 读写权判断
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "prise_result.do", userOnline
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");
		return mapping.findForward("jxjsqjgcx");
	}
	
	public ActionForward jxjDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JxjsqshService service = new JxjsqshService();
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String pkVStr = request.getParameter("pkVStr");
		String jxjdm = request.getParameter("jxjdm");
		if (session.getAttribute("fdyQx").toString().equalsIgnoreCase("true")) {
			userType = "isFdy";
		}
		boolean result = service.serv_jxjDel(userType, jxjdm, pkVStr);
		request.setAttribute("result", result);
		return jxjQuery(mapping, form, request, response);
	}
}
