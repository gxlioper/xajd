package xgxt.shgz.action;

import java.lang.reflect.InvocationTargetException;
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
import xgxt.shgz.form.StglForm;
import xgxt.shgz.model.StglModel;
import xgxt.shgz.model.XjqnzyzModel;
import xgxt.shgz.server.StglService;
import xgxt.utils.FormModleCommon;

public class StglAction extends DispatchAction{
	
	public ActionForward rssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		String gb = request.getParameter("gb");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		String xh                      =    DealString.toGBK(request.getParameter("xh"));
		if(userType.equalsIgnoreCase("stu")) {
			xh = userName;
		}
		StglService service          =    new StglService();
		HashMap<String, String> rs     =    service.getStsqOne(pk,xh);
		request.setAttribute("gb", gb);
		request.setAttribute("rs", rs);
		request.setAttribute("stList", service.getXtList());
		request.setAttribute("shztList", service.getShztList());
		return mapping.findForward("rtsq");	
	}
	
	public ActionForward saveRssq (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // 军训新生体检维护单个查看保存
		StglForm myForm              =    (StglForm)  form;
		StglService service        =    new StglService();
		StglModel myModel          =    new StglModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataRssq(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return rssq(mapping, form,request, response);	
	}
	
	public ActionForward rssqsh (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
		HttpServletResponse response) throws IllegalAccessException, InvocationTargetException {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_rssq";
		String realTable     = "rtsqdjb";
		String title     = "社会工作-社团管理-入社申请审核";
		
		ArrayList<String[]> rs = null;
		StglForm myForm              =    (StglForm)  form;
		StglService service        =    new StglService();
		StglModel myModel          =    new StglModel();
		
		String nj = myForm.getNj();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		
		xy = (xy==null) ? "" : xy;
		zy = (zy==null) ? "" : zy;
		bj = (bj==null) ? "" : bj;
		nj = (nj==null) ? "" : nj;
		
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		String bjKey = xy+"!!"+zy+"!!"+nj;
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getRssqList(myModel);
		}
		
		List topTr = service.getRssqTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		if (userType.equalsIgnoreCase("xy")) {
			xy = userDep;
		}else {
			xy = "";
		}
		request.setAttribute("stList", service.getXtList2(xy));
		request.setAttribute("shztList", service.getShztList());
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("rtsqsh");		
	}
	
	private void commonRequestSet(HttpServletRequest request, String tableName, String realTable,
			ArrayList<String[]> rs, List topTr,String title) {
    //    Request存值的通用方法
		String writeAble    = request.getParameter("writeAble");
		if(writeAble==null){
			   writeAble    = Base.getWriteAble(request);
		}
		
		if(rs!=null){
			request.setAttribute("rsNum", rs.size());
		}
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("title", title);
		request.setAttribute("writeAble", writeAble);
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
	}
	
	public ActionForward delRssq(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // 删除入社申请
		StglService service        =    new StglService();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteRssqOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return rssqsh(mapping, form,request, response);		
	}
	
	//时间参数设置
	public ActionForward sjcssz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
//	    时间设置显示
		StglService service          =    new StglService();
		
		HashMap<String, String> rs = service.getSjszList();
	
		request.setAttribute("rs", rs);
		return mapping.findForward("sjcssz");		
	}
	
	public ActionForward saveSjcssz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    时间设置保存
		StglService service          =    new StglService();
		StglForm myForm              =    (StglForm) form;
		StglModel myModel            =    new StglModel();
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted             =    service.saveSjcssz(myModel,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return sjcssz(mapping, form,request,response);
	}
	public ActionForward xjqnzyzManage(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		StglService service          =    new StglService();
		StglForm myForm              =    (StglForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep  = request.getSession().getAttribute("userDep").toString();
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			XjqnzyzModel model = new XjqnzyzModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getXjzyzTopTr();
			rs = service.serv_xjzyzQuerry(model);
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		if(rs==null){
			request.setAttribute("rsNum", rs);
		}else {
			request.setAttribute("rsNum", rs.size());
		}
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("userType",userType);
		return mapping.findForward("xjqnzyzManage");
	}
}
