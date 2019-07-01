/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-1-4 下午03:55:33</p>
 */
package xgxt.sztz.csmzzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.sztz.dao.SztzDao;

public class SztzCsmzAction extends DispatchAction {
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
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
		return super.execute(mapping,form,request,response);
	}
    public ActionForward xfSb(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
    	SztzDao myDao = new SztzDao();
    	SztzCsmzActionForm myForm = (SztzCsmzActionForm)form;
    	HttpSession session     = request.getSession();
    	SztzCsmzService service = new SztzCsmzService();
    	HashMap<String,String> map = new HashMap<String,String>();	
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String access              = myDao.getBgbM();
		String xn       = myForm.getXn();
		if(xn==null){
			xn = Base.currXn;
			myForm.setXn(xn);
		}			
		ArrayList<String[]> rs = null;
		String bjdm = "";
		if ("stu".equalsIgnoreCase(userType)) {// 有操作权限的学生干部登录
			String userGB = myDao.getBgbPower(userName);
			if (userGB.equalsIgnoreCase("0")) {
				request.setAttribute("errMsg", "只有班干部：\'"+access+"\' 才能进行素质拓展学分上报！");
				return new ActionForward("/errMsg.do", false);
			} else {
		        //topTr = service.getwmQsSbSearchTitle(userType);
		        rs    = service.getxfSbResult(userName,bjdm);			
			}
		}else{
			request.setAttribute("errMsg", "只有学生班干部：\'"+access+"\' 才能进行素质拓展学分上报！");
			return new ActionForward("/errMsg.do", false);
		}
		map  = service.getInfo("view_xsjbxx", "xh",userName);
		request.setAttribute("bjInfo",map);
        request.setAttribute("xnList",Base.getXnndList());
        request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size():0);	       
    	return mapping.findForward("creditHSb");
    }
    public ActionForward xfSbSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException{
    	SztzCsmzActionForm myForm = (SztzCsmzActionForm)form;
    	SztzCsmzService service   = new SztzCsmzService();
    	SztzCsmzXfsbModel model = new SztzCsmzXfsbModel();
    	BeanUtils.copyProperties(model, myForm);   
    	boolean done = service.serv_xfSbSave(model);
    	if(done){    		
			request.setAttribute("done", "ok");
		} else {
			request.setAttribute("done", "no");
		}		
    	return new ActionForward("/csmzzy_sztz.do?method=xfSb",false);
    }
    public ActionForward xfSbManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
    	SztzCsmzActionForm myForm = (SztzCsmzActionForm)form;
    	SztzDao myDao = new SztzDao();
    	DAO   dao     = DAO.getInstance();
    	HttpSession session = request.getSession();
    	String xydm = myForm.getXydm();
    	String zydm = myForm.getZydm();
    	String nj   = myForm.getNj();  
    	String xn   = myForm.getXn();
    	String writeAble = "no";
    	HashMap<String,String> map = new HashMap<String,String>();	
    	if(xn==null){
    		myForm.setXn(Base.currXn);
    	}
		String userTypeO = session.getAttribute("userType").toString();
		String userDep  = session.getAttribute("userDep").toString();
		String userTypeT = dao.getUserType(userDep); 		
		
		String userName = session.getAttribute("userName").toString();
    	SztzCsmzService service = new SztzCsmzService();
    	if ("stu".equalsIgnoreCase(userTypeO)) {// 有操作权限的学生干部登录
    		if ("0".equalsIgnoreCase(myDao.getBgbPower(userName))) {
    			request.setAttribute("errMsg", "只有班干部：\'"+myDao.getBgbM()+"\' 才能进行素质拓展学分上报结果查询！");
    			return new ActionForward("/errMsg.do", false);
    		}
    		writeAble = "yes";
    		map  = service.getInfo("view_xsjbxx", "xh",userName);
    		myForm.setNj(map.get("nj"));
    		myForm.setXydm(map.get("xydm"));
    		myForm.setZydm(map.get("zydm"));
    		myForm.setBjdm(map.get("bjdm"));
    	}else{
    		if("xy".equalsIgnoreCase(userTypeT)){
    			xydm = userDep;
    			myForm.setXydm(xydm);
    		}
    		writeAble = (Base.chkUPower(userName,"jswmhdShManage.do","student".equalsIgnoreCase(session.getAttribute("userOnLine").toString()))==1)?"yes":"no";
    	}
    	if("go".equalsIgnoreCase(request.getParameter("go"))){
        	SztzCsmzXfsbModel model = new SztzCsmzXfsbModel();
        	BeanUtils.copyProperties(model, myForm);   
			ArrayList<HashMap<String, String>> topTr = service.getxfSbMTitle();
			ArrayList<String[]> rs    = service.getCzMResult(model,"",userTypeT);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
    	}
//    	读写权限
    	request.setAttribute("writeAble" ,writeAble); 
    	request.setAttribute("userType", userTypeO);
    	getListxx(request,xydm,zydm,nj);
    	return mapping.findForward("xfSbManage");
    }
	// 获取列表信息
	private static void getListxx(HttpServletRequest request,
			String xydm, String zydm, String nj) {
		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
		String bjKey = xydm + "!!" + zydm + "!!" + nj;
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));// 发送班级列表
		request.setAttribute("njList", Base.getNjList());// 发送年级列表
		request.setAttribute("xnList", Base.getXnndList());// 发送学年列表
		request.setAttribute("xqList", Base.getXqList());// 发送学期列表
		request.setAttribute("xyList", Base.getXyList());// 发送学院列表
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));// 发送专业列表		
	}
	public ActionForward xfsbxxView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		SztzCsmzService service = new SztzCsmzService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		ArrayList<String[]> rs  = service.getxfSbXx(pkValue);
		request.setAttribute("map",service.getInfo("view_xfsbxx", "xn||bjdm",pkValue));
		request.setAttribute("rs",rs);
		return mapping.findForward("xfSbView");
	}
	public ActionForward xfsbxxModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		SztzCsmzService service = new SztzCsmzService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		ArrayList<String[]> rs  = service.getxfSbXx(pkValue);
		request.setAttribute("map",service.getInfo("view_xfsbxx", "xn||bjdm",pkValue));
		request.setAttribute("rs",rs);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("rsNum", rs != null ? rs.size():0);
    	request.setAttribute("pkValue", pkValue);
		return mapping.findForward("xfSbModi");
	}
    public ActionForward xfSbModiSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException{
    	SztzCsmzActionForm myForm = (SztzCsmzActionForm)form;
    	SztzCsmzService service   = new SztzCsmzService();
    	SztzCsmzXfsbModel model = new SztzCsmzXfsbModel();
    	String pkValue = DealString.toGBK(request.getParameter("pkValue"));
    	BeanUtils.copyProperties(model, myForm);   
    	boolean done = service.serv_xfSbSave(model);
    	if(done){    		
			request.setAttribute("done", "ok");
			pkValue = DealString.toGBK(myForm.getXn()+myForm.getBjdm());
		} else {
			request.setAttribute("done", "no");
		}	
    	request.setAttribute("pkValue",pkValue);
    	return new ActionForward("/csmzzy_sztz.do?method=xfsbxxModi",false);
    }
    public ActionForward xfSbxxDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String pkValue = DealString.toGBK(request.getParameter("pkValue"));
    	SztzCsmzService service   = new SztzCsmzService();
    	service.serv_xfSbxxDel(pkValue);
//    	boolean done = service.serv_xfSbxxDel(pkValue);
    	return new ActionForward("/csmzzy_sztz.do?method=xfSbManage",false);
    }
    public ActionForward xfSbShManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
    	SztzCsmzActionForm myForm = (SztzCsmzActionForm)form;
    	SztzCsmzService service = new SztzCsmzService();
    	DAO   dao     = DAO.getInstance();
    	HttpSession session = request.getSession();
    	String xydm = myForm.getXydm();
    	String zydm = myForm.getZydm();
    	String nj   = myForm.getNj();  
    	String yesNo = DealString.toGBK(myForm.getYesNo());
    	myForm.setYesNo(yesNo);
		String userTypeO = session.getAttribute("userType").toString();
		String userDep  = session.getAttribute("userDep").toString();
		String userTypeT = dao.getUserType(userDep); 		
   	    if ("stu".equalsIgnoreCase(userTypeO)) { 		
    			request.setAttribute("errMsg", "学生无权访问此页面！");
    			return new ActionForward("/errMsg.do", false);   		   		
    	}else{
    		if("xy".equalsIgnoreCase(userTypeT)){
    			xydm = userDep;
    			myForm.setXydm(xydm);
    		}
    	}
    	if("go".equalsIgnoreCase(request.getParameter("go"))){
        	SztzCsmzXfsbModel model = new SztzCsmzXfsbModel();
        	BeanUtils.copyProperties(model, myForm);   
			ArrayList<HashMap<String, String>> topTr = service.getxfSbMTitle();
			ArrayList<String[]> rs    = service.getCzMResult(model,yesNo,userTypeT);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
    	}
    	getListxx(request,xydm,zydm,nj);
        request.setAttribute("chkList",dao.getChkList(3));
    	return mapping.findForward("xfsbshM");
    }
    public ActionForward xfSbSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	SztzCsmzService service   = new SztzCsmzService();
    	DAO   dao       = DAO.getInstance();
    	String pkValue  = request.getParameter("pkValue");
		String userDep  = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep); 		
		String shType     = request.getParameter("shType");
    	boolean done   = service.serv_xfsbSh(pkValue, shType, userType);
    	request.setAttribute("done",done);
    	return new ActionForward("/csmzzy_sztz.do?method=xfSbShManage",false);
    }
    public ActionForward plXfCheck(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
       	SztzCsmzService service   = new SztzCsmzService();
    	DAO   dao       = DAO.getInstance();
    	String pkValue = request.getParameter("pkVStr");
    	String userDep  = request.getSession().getAttribute("userDep").toString();		
		String shType     = request.getParameter("check");
		String curDep = dao.getOneRs("select xgbdm from xtszb where rownum=1 ",new String[]{},"xgbdm");
		curDep = Base.isNull(curDep)?"":curDep;
		String userType = request.getSession().getAttribute("userType").toString();
		if (!userDep.equalsIgnoreCase(curDep)) {//不属于学工部用户
			userType = "stu".equalsIgnoreCase(userType)?userType:"xy";//非学生班干部用户设置为"xy"用户				
		}else{
			userType = "xx";
		}
		boolean done = service.serv_plXfSh(pkValue, shType, userType);
		request.setAttribute("done",done);
		return new ActionForward("/csmzzy_sztz.do?method=xfSbShManage",false);
    }
}
