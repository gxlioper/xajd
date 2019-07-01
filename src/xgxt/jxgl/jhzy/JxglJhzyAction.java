package xgxt.jxgl.jhzy;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.wjcf.wjcfutils.CommonAction;

public class JxglJhzyAction extends CommonAction {

	/**
	 * 整体安排
	 */
	public ActionForward jxrcZtManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JxglJhzyService service = new JxglJhzyService();
		ZtApModel model = new ZtApModel();
		JxglJhzyForm myForm = (JxglJhzyForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String nd = myForm.getNd();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if(xn==null){
			xn=Base.currXn;
			myForm.setXn(xn);
		}
		if(xq==null){
			xq=Base.currXq;
			myForm.setXq(xq);
		}
		if(nd==null){
			nd=Base.currNd;
			myForm.setNd(nd);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model, myForm);		
			topTr  = service.getjxrcZtTitle();
			rs     = service.serv_JxrcZtSearch(model);
		}
		request.setAttribute("rs",rs); 
		request.setAttribute("rsNum",rs.size());
		request.setAttribute("topTr",topTr);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("ndList",Base.getXnndList());
		//读写权判断		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"jxrcZtManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("jxrcZtManage");
	}
	/**
	 *军训时间范围设置
	 */
	public ActionForward jxztrcsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JxglJhzyService service = new JxglJhzyService();
		JsSjSzFwModel model = new JsSjSzFwModel();
		JxglJhzyForm myForm = (JxglJhzyForm) form;
		String doType = request.getParameter("doType");		
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String nd = myForm.getNd();		
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_jxztrcszSave(model);
			request.setAttribute("done",done);
		}
		if(xn==null){
			xn=Base.currXn;
			myForm.setXn(xn);
		}
		if(xq==null){
			xq=Base.currXq;
			myForm.setXq(xq);
		}
		if(nd==null){
			nd=Base.currNd;
			myForm.setNd(nd);
		}
		String pkValue = xn+xq+nd;
		HashMap<String,String> map = service.serv_getSjfwInfo(pkValue);
		request.setAttribute("rs",map);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("ndList",Base.getXnndList());
		return mapping.findForward("jxztrcsz");
	}
	/**
	 * 整体安排日程添加
	 * @throws Exception 
	 */
	public ActionForward jxrcZtAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{	
		JxglJhzyService service = new JxglJhzyService();
		ZtApModel model = new ZtApModel();
		JxglJhzyForm myForm = (JxglJhzyForm) form;
		String doType = request.getParameter("doType");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd= request.getParameter("nd");
		if(xn==null){
			xn =Base.currXn;
		}
		if(xq==null){
			xq =Base.currXq;
		}
		if(nd==null){
			nd =Base.currNd;
		}
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, myForm);			
			boolean result = service.serv_jxrcZtAddSave(model, request);
			if (result) {
				String pk = model.getXn() + model.getXq() + model.getNd();
				request.setAttribute("pk", pk);
			}
			request.setAttribute("result", result);
		}		
		HashMap<String,String> mapSjFw = service.serv_getSjfwInfo(xn+xq+nd);
		request.setAttribute("rsSjFw",mapSjFw);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("xn", xn);
		map.put("xq", service.getXqmc(xq));
		map.put("nd", nd);
		request.setAttribute("rs",map);
		request.setAttribute("xnV", xn);
		request.setAttribute("xqV", xq);
		request.setAttribute("ndV", nd);
		return mapping.findForward("jxrcZtAdd");
	}
	/**
	 * 整体安排日程修改页面 
	 */
	public ActionForward jxrcZtUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("pkValue");
		JxglJhzyService service = new JxglJhzyService();
		String doType = request.getParameter("doType");		
		HashMap<String,String> map = new HashMap<String, String>();
        map = service.serv_getZtInfo(pkValue);
        HashMap<String,String> mapSjFw = new HashMap<String, String>();
        String xn=map.get("xn");
        String xq=map.get("xq");
        String nd=map.get("nd");
        map.put("xq", service.getXqmc(xq));
        mapSjFw = service.serv_getSjfwInfo(pkValue);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("ndList",Base.getXnndList());
		request.setAttribute("rsSjFw",mapSjFw);
		request.setAttribute("doType", doType);
		request.setAttribute("pk", pkValue);
		request.setAttribute("rs",map);
		request.setAttribute("xnV", xn);
		request.setAttribute("xqV", xq);
		request.setAttribute("ndV", nd);
		return mapping.findForward("jxrcZtAdd");
	}
	
	/**
	 * 组织领导管理
	 * 
	 * @throws Exception
	 */
	public ActionForward zzldManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();

		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();

		JxglJhzyService service = new JxglJhzyService();
		JxglJhzyForm myForm = (JxglJhzyForm) form;
		ZzldModel model = new ZzldModel();

		String tableName = "view_zgdd_gfsb";
		String realTable = "zgdd_gfs";
		String title = "军训管理 - 信息维护 - 组织领导管理";
		String pk = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj   = myForm.getNj();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		String go = request.getParameter("go");

		boolean result = false;
		if ("del".equals(doType)) {
			result = service.delZzld(DealString.toGBK(pk), request);
		}

		if ("go".equals(go) || result) {
			BeanUtils.copyProperties(model, myForm);
			rs = service.getZzldList(model);
			topTr = service.getZzTitle();
			myForm.setZzmc(DealString.toGBK(myForm.getZzmc()));
		}

		request.setAttribute("path", "jxglZzld.do");
		appendProperties(request,xydm,zydm,nj);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		
		request.setAttribute("ldList", service.getLdList(nj));
		request.setAttribute("njList", service.getNjList());
		request.setAttribute("userType", userType);
		request.setAttribute("userName", userName);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		request.setAttribute("title", title);

		return mapping.findForward("zzldManage");
	}
	
	/**
	 * 组织领导维护
	 * 
	 * @throws Exception
	 */
	public ActionForward zzldUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JxglJhzyService service = new JxglJhzyService();
		JxglJhzyForm myForm = (JxglJhzyForm) form;
		ZzldModel model = new ZzldModel();

		HashMap<String, String> map = new HashMap<String, String>();

		String title = "军训管理 - 信息维护 - 组织领导维护";

		String xydm = myForm.getXydm();
		String zydm = myForm.getZydm();
		String nj = myForm.getNj();
		String pk = request.getParameter("pkValue");
		String doType = request.getParameter("doType");

		nj = (Base.isNull(nj)) ? Base.currNd : nj;
		map.put("nj", nj);

		BeanUtils.copyProperties(model, myForm);

		if ("save".equals(doType)) {

			boolean result = service.addZzld(model, request);
			if (result) {
				pk = model.getLddm() + model.getZzmc();
			}
			myForm.setZzmc(DealString.toGBK(model.getZzmc()));
			request.setAttribute("result", result);
			map = service.getZzld(DealString.toGBK(pk));
		}
		if ("update".equals(doType)||"view".equalsIgnoreCase(doType)) {
			map = service.getZzld(DealString.toGBK(pk));
			nj = map.get("nj");
		}
		if ("print".equals(doType)) {
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			service.printZzldList(model,response.getOutputStream());
			
			return null;
		}


		appendProperties(request, xydm, zydm, nj);

		request.setAttribute("pk", DealString.toGBK(pk));
		request.setAttribute("njList", service.getNjList());
		request.setAttribute("ldList", service.getLdList(nj));
		request.setAttribute("zwList", service.getLdZwList());
		request.setAttribute("doType", doType);
		request.setAttribute("title", title);
		request.setAttribute("rs", map);
		return mapping.findForward("zzldUpdate");
	}
	
	/**
	 * 整体安排日程信息删除
	 */
	public ActionForward jxrcZtDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String delPk = request.getParameter("delPk");
		JxglJhzyService service = new JxglJhzyService();
		service.serv_jxrcZtDel(delPk);
		return jxrcZtManage(mapping, form, request, response);
	}
	/**
	 * 具体安排日程信息管理
	 */
    public ActionForward jxrcJtManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{
    	JxglJhzyService service = new JxglJhzyService();
		JtApModel model = new JtApModel();
		JxglJhzyForm myForm = (JxglJhzyForm) form;
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String userOnline = session.getAttribute("userOnLine").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String xn = myForm.getXn();
		String xq = myForm.getXq();
		String nd = myForm.getNd();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		ArrayList<HashMap<String, String>> topTr = new ArrayList<HashMap<String, String>>();
		if(xn==null){
			xn=Base.currXn;
			myForm.setXn(xn);
		}
		if(xq==null){
			xq=Base.currXq;
			myForm.setXq(xq);
		}
		if(nd==null){
			nd=Base.currNd;
			myForm.setNd(nd);
		}
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model, myForm);		
			topTr  = service.getjxrcJtTitle();
			rs     = service.serv_JxrcJtSearch(model);
		}
		request.setAttribute("rs",rs); 
		request.setAttribute("rsNum",rs.size());
		request.setAttribute("topTr",topTr);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("ndList",Base.getXnndList());
		request.setAttribute("xyList",Base.getXyList());
		//读写权判断		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"jxrcJtManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");	
    	request.setAttribute("userType",userType);
		return mapping.findForward("jxrcJtManage");
    }
	/**
	 * 具体安排日程添加
	 * @throws Exception 
	 */
	public ActionForward jxrcJtAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{	
		JxglJhzyService service = new JxglJhzyService();
		JtApModel model = new JtApModel();
		JxglJhzyForm myForm = (JxglJhzyForm) form;
		String doType = request.getParameter("doType");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd= request.getParameter("nd");
		String xydm = request.getParameter("xydm");
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		if(xn==null){
			xn =Base.currXn;
		}
		if(xq==null){
			xq =Base.currXq;
		}
		if(nd==null){
			nd =Base.currNd;
		}
		if("xy".equalsIgnoreCase(userType)){
			xydm = userDep;
			myForm.setXydm(xydm);
		}		
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, myForm);			
			boolean result = service.serv_jxrcJtAddSave(model, request);
			if (result) {
				String pk = model.getXydm()+model.getXn() + model.getXq() + model.getNd();
				request.setAttribute("pk", pk);
			}
			request.setAttribute("result", result);
		}		
		HashMap<String,String> mapSjFw = service.serv_getSjfwInfo(xn+xq+nd);
		request.setAttribute("rsSjFw",mapSjFw);
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("xn", xn);
		map.put("xq", service.getXqmc(xq));
		map.put("nd", nd);
		request.setAttribute("rs",map);
		List<HashMap<String,String>>  rsZtApInfo= service.serv_getZtApInfo(xn+xq+nd);
		request.setAttribute("rsZtInfo",rsZtApInfo);
		request.setAttribute("xnV", xn);
		request.setAttribute("xqV", xq);
		request.setAttribute("ndV", nd);
		request.setAttribute("xydmV",xydm);
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("userType",userType);
		return mapping.findForward("jxrcJtAdd");
	}
	/**
	 * 具体安排日程修改页面 
	 */
	public ActionForward jxrcJtUpdate(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("pkValue");
		JxglJhzyService service = new JxglJhzyService();
		String doType = request.getParameter("doType");	
		String userType = request.getSession().getAttribute("userType").toString();
	//	String userDep = request.getSession().getAttribute("userDep").toString();
		JxglJhzyForm myForm = (JxglJhzyForm) form;				
		HashMap<String,String> map = new HashMap<String, String>();
        map = service.serv_getJtInfo(pkValue);
        HashMap<String,String> mapSjFw = new HashMap<String, String>();
        String xn=map.get("xn");
        String xq=map.get("xq");
        String nd=map.get("nd");
        String xydm = map.get("xydm");
        myForm.setXydm(xydm);		
        map.put("xq", service.getXqmc(xq));
        mapSjFw = service.serv_getSjfwInfo(xn+xq+nd);
		List<HashMap<String,String>>  rsZtApInfo= service.serv_getZtApInfo(xn+xq+nd);
		request.setAttribute("rsZtInfo",rsZtApInfo);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("ndList",Base.getXnndList());
		request.setAttribute("rsSjFw",mapSjFw);
		request.setAttribute("doType", doType);
		request.setAttribute("pk", pkValue);
		request.setAttribute("rs",map);
		request.setAttribute("xnV", xn);
		request.setAttribute("xqV", xq);
		request.setAttribute("ndV", nd);
		request.setAttribute("xydmV",xydm);
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("userType",userType);
		return mapping.findForward("jxrcJtAdd");
	}
	/**
	 * 具体安排日程信息删除
	 */
	public ActionForward jxrcJtDel(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String delPk = request.getParameter("delPk");
		JxglJhzyService service = new JxglJhzyService();
		service.serv_jxrcJtDel(delPk);
		return jxrcJtManage(mapping, form, request, response);
	}
	/**
	 * 日程表下载
	 */
	public ActionForward jxrcBbManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response){		
		JxglJhzyForm myForm = (JxglJhzyForm) form;	
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd= request.getParameter("nd");		
		if(xn==null){
			myForm.setXn(Base.currXn);
		}
		if(xq==null){
			myForm.setXq(Base.currXq);
		}
		if(nd==null){
			myForm.setNd(Base.currNd);
		}
		request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("ndList",Base.getXnndList());
		return mapping.findForward("jxrcBbManage");
	}
	/**
	 * 校日程表下载
	 * @throws Exception 
	 */
	public ActionForward xxrcb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{		
		JxglJhzyService service = new JxglJhzyService();
//		JxglJhzyForm myForm = (JxglJhzyForm) form;	
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd= request.getParameter("nd");				
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.excuteXrcbPrint(xn,xq,nd,response.getOutputStream());
		return mapping.findForward("expdata");
	}
	/**
	 * 院系日程表下载
	 * @throws Exception 
	 */
	public ActionForward yxrcb(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception{		
		JxglJhzyService service = new JxglJhzyService();
//		JxglJhzyForm myForm = (JxglJhzyForm) form;	
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String nd= request.getParameter("nd");	
		String xydm= request.getParameter("xydm");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		service.excuteYxrcbPrint(xydm,xn,xq,nd,response.getOutputStream());
		return mapping.findForward("expdata");
	}
}
