/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-10-27 下午02:41:20</p>
 */
package xgxt.xsgygl.nblgxy;

import java.lang.reflect.InvocationTargetException;
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

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;


public class GyglNblgxyAction extends DispatchAction {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
	/**文明寝室申请审核查询初始页面*/
	public ActionForward nblgxy_WmqsSbShDef(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){ 
		DAO dao       = DAO.getInstance();
		gyglDao gyDao = new gyglDao();   	
		String userType      = request.getSession().getAttribute("userType").toString();
		GyglNblgxyForm Form    = (GyglNblgxyForm)form;
		Form.setXn(Base.currXn);//默认当前学年
		Form.setXq(Base.currXq);//默认当前学期

		if("stu".equalsIgnoreCase(userType)||"xy".equalsIgnoreCase(userType)){//学生、院系用户无权访问
			if("stu".equalsIgnoreCase(userType)){
				request.setAttribute("errMsg", "学生用户,无权访问该页面！");
			} else {
				request.setAttribute("errMsg", "院系用户,无权访问该页面！");
			}
			return new ActionForward("/errMsg.do", false);
		}
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
		request.setAttribute("qshList",gyDao.getQshList());//公寓寝室号列表 
		request.setAttribute("chkList", dao.getChkList(3));//审核状态列表
		return mapping.findForward("nblgxy_wmqsshcx"); 
	}
	/**文明寝室申请审核查询执行*/
	public ActionForward nblgxy_WmqsSbShQer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		DAO dao       = DAO.getInstance();
		gyglDao gyDao = new gyglDao();   	
		GyglNblgxyForm Form         = (GyglNblgxyForm)form;
		GyglNblgxyServices Service  = new GyglNblgxyServices();
		GyglNblgxyWmqsModel Model = new GyglNblgxyWmqsModel();//文明寝室申报信息参数MODEL
		Form.setXn(Base.currXn);//默认当前学年
		Form.setXq(Base.currXq);//默认当前学期
		String userType           = request.getSession().getAttribute("userType").toString();
		BeanUtils.copyProperties(Model, Form);
		ArrayList<HashMap<String, String>> topTr = Service.getwmQsSbSearchTitle(userType);
		ArrayList<String[]> rs    = Service.getwmQsSbSearchResult(userType, Model);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
		request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
		request.setAttribute("qshList",gyDao.getQshList());//公寓寝室号列表  
		request.setAttribute("chkList", dao.getChkList(3));//审核状态列表
		request.setAttribute("topTr",topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		return mapping.findForward("nblgxy_wmqsshcx");
	}
	/**文明寝室审核信息*/
	public ActionForward nblgxy_wmqsSbSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
		DAO dao       = DAO.getInstance();
		HashMap<String,String> map  = new HashMap<String,String>();
		HashMap<String,String> map1 = new HashMap<String,String>();
		HashMap<String,String> map2 = new HashMap<String,String>();
		String userType             = request.getSession().getAttribute("userType").toString();
		String pkValue              = request.getParameter("pkValue");
		String shtype               = "";
		GyglNblgxyServices Service    = new GyglNblgxyServices();
		map = Service.getwmSbOneInfo(pkValue,userType);//文明寝室相关信息
		map1= Service.getwmQsWjInfo(pkValue);//寝室内学生当前学年违纪处分相关信息
		map2= Service.getwmQsBlInf();//当前学年文明寝室数比例及相关信息
		if("xx".equalsIgnoreCase(userType)
				||"admin".equalsIgnoreCase(userType)){
			shtype ="学校";
		}    	

		request.setAttribute("qslbList",GyglShareDAO.getqslbList());	
		request.setAttribute("shtype",shtype);
		request.setAttribute("rs",map);
		request.setAttribute("rs1", map1);
		request.setAttribute("rs2",map2);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("chkList", dao.getChkList(3));//审核状态列表
		return mapping.findForward("nblgxy_wmqssbsh");
	}
	/**文明寝室审核信息保存*/
	public ActionForward wmqsSbShSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglNblgxyForm gaF         = (GyglNblgxyForm)form;
		String yesNo               = DealString.toGBK(gaF.getYesNo());
		String pkValue             = request.getParameter("pkValue");
		String userType            = request.getSession().getAttribute("userType").toString();
		GyglNblgxyServices Service   = new GyglNblgxyServices();
		boolean done = Service.wmSbShSave(pkValue, userType, yesNo);
		request.setAttribute("done",done);
		request.setAttribute("pkValue",pkValue);
		return mapping.findForward("nblgxy_wmqssbsh");
	}
	/**辅导员月报填写*/
	public ActionForward ybAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglNblgxyForm myForm        = (GyglNblgxyForm)form;
		String userNameReal          = request.getSession().getAttribute("userNameReal").toString();
		String userName              = request.getSession().getAttribute("userName").toString();
		String doType                = request.getParameter("doType");
		GyglNblgxyServices Service   = new GyglNblgxyServices();
		GyglNblgxyYbModel model      = new GyglNblgxyYbModel();
		if(Globals.XXDM_NBLGXY.equalsIgnoreCase(Base.xxdm)){
			if(Service.serv_getsjxzInfo()){
				request.setAttribute("agree","yes");
			}else{
				request.setAttribute("agree","no");
			}
		}else{
			request.setAttribute("agree","yes");
		}
		
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, myForm);
			boolean done  = Service.serv_ybAdd(model, userName);
			request.setAttribute("done",done);
		}

		myForm.setNf(Base.currNd);
		myForm.setYf(DealString.getDateTime().substring(5,7)) ;
		myForm.setXm(userNameReal);
		myForm.setFzld(DealString.toGBK(myForm.getFzld()));
		myForm.setRzqsh(DealString.toGBK(myForm.getRzqsh()));
		myForm.setGznr_jhynr(DealString.toGBK(myForm.getGznr_jhynr()));
		myForm.setGznr_jqtnr(DealString.toGBK(myForm.getGznr_jqtnr()));
		myForm.setGznr_jxsdt(DealString.toGBK(myForm.getGznr_jxsdt()));
		myForm.setGznr_jyfk(DealString.toGBK(myForm.getGznr_jyfk()));
		myForm.setJgznr_jyjl(DealString.toGBK(myForm.getJgznr_jyjl()));
		myForm.setFkyj_fwzxfk(DealString.toGBK(myForm.getFkyj_fwzxfk()));
		myForm.setFkyj_xgbfk(DealString.toGBK(myForm.getFkyj_xgbfk()));
		myForm.setFkyj_xyfk(DealString.toGBK(myForm.getFkyj_xyfk()));
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		request.setAttribute("nfList",Base.getXnndList());
		request.setAttribute("dlm", userName);
		return mapping.findForward("yhAdd");
	}
	/**公寓辅导员月报查询初始页面*/
	public ActionForward ybManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){ 
		String userName              = request.getSession().getAttribute("userName").toString();		 	
		String userType              = request.getSession().getAttribute("userType").toString();
		String userOnline            = request.getSession().getAttribute("userOnLine").toString();
		GyglNblgxyForm myForm    = (GyglNblgxyForm)form;
		if(myForm.getNf()==null){
			myForm.setNf(Base.currNd);//默认当前学年
		}
		if(myForm.getYf()==null){
			myForm.setYf(DealString.getDateTime().substring(5,7));
		}

		if("stu".equalsIgnoreCase(userType)){//学生、院系用户无权访问
			if("stu".equalsIgnoreCase(userType)){
				request.setAttribute("errMsg", "学生用户,无权访问该页面！");
			} 
			return new ActionForward("/errMsg.do", false);
		}
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			GyglNblgxyServices  service    = new GyglNblgxyServices();
			ArrayList<HashMap<String, String>> topTr = service.serv_getybMTitle();
			ArrayList<String[]> rs    =service.serv_getybMResult(myForm.getNf(),myForm.getYf(), userName, userType);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);			
		}
		request.setAttribute("nfList",Base.getXnndList());
		//读写权判断		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"lzManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");

		return mapping.findForward("ybM"); 
	}
	public ActionForward ybView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		String pkValue               = DealString.toGBK(request.getParameter("pkValue"));
		GyglNblgxyServices service   = new GyglNblgxyServices();
		request.setAttribute("rs",service.serv_getYbInfo(pkValue));
		return mapping.findForward("ybPView");
	}
	public ActionForward ybModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglNblgxyForm myForm        = (GyglNblgxyForm)form;
		String pkValue               = DealString.toGBK(request.getParameter("pkValue"));
		String doType                = request.getParameter("doType");
		GyglNblgxyYbModel     model  = new GyglNblgxyYbModel();
		GyglNblgxyServices service   = new GyglNblgxyServices();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, myForm);
			boolean done  = service.serv_ybModi(model,pkValue);
			request.setAttribute("done",done);
		}
		request.setAttribute("nfList",Base.getXnndList());   	
		request.setAttribute("rs",service.serv_getYbInfo(pkValue));
		request.setAttribute("pkValue",pkValue);
		return mapping.findForward("ybModi");
	}
	public ActionForward ybDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglNblgxyServices service   = new GyglNblgxyServices();
		String pkValue               = DealString.toGBK(request.getParameter("pkValue"));
		service.serv_ybDel(pkValue);
		return new ActionForward("/nblgxy_gygl.do?method=ybManage",false);
	}
	public ActionForward zrrManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
		GyglNblgxyForm myForm        = (GyglNblgxyForm)form;   	
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		String lddm   = myForm.getLddm();  
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		myForm.setSfzz(DealString.toGBK(myForm.getSfzz()));

		GyglNblgxyServices service   = new GyglNblgxyServices();
		GyglFzqLxrModel model = new GyglFzqLxrModel();
		if("go".equalsIgnoreCase(request.getParameter("go"))){
			BeanUtils.copyProperties(model,myForm);			
			ArrayList<HashMap<String, String>> topTr = service.serv_getZrrTitle();
			ArrayList<String[]> rs    = service.serv_getZrrResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
		}

		request.setAttribute("ldList",GyglShareDAO.getLdList());
		request.setAttribute("ssbhList",GyglShareDAO.GetSsbhList(lddm));
		//读写权判断		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"zrrManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
		return mapping.findForward("zrrM");
	}
	public ActionForward zrrAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		gyglDao myDao  = new gyglDao();
		GyglNblgxyForm myForm        = (GyglNblgxyForm)form;
		myForm.setLxfs(DealString.toGBK(myForm.getLxfs()));
		myForm.setDzyx(DealString.toGBK(myForm.getDzyx()));
		myForm.setFzssbh(DealString.toGBK(myForm.getFzssbh()));
		myForm.setSsbhcn(DealString.toGBK(myForm.getSsbhcn()));
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		
		GyglNblgxyServices service   = new GyglNblgxyServices();
		GyglFzqLxrModel model = new GyglFzqLxrModel();
		String xh  = myForm.getXh();
		String doType = request.getParameter("doType");
		String lddm   = myForm.getLddm();   
		HashMap<String,String> map = new HashMap<String,String>();
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_zrrAddSave(model);
			request.setAttribute("done",done);
		}
		map = myDao.getStuInfo(xh);
		request.setAttribute("rs",map);
		request.setAttribute("ldList",GyglShareDAO.getLdList());
		request.setAttribute("ssbhList",GyglShareDAO.GetSsbhList(lddm));
		return mapping.findForward("zrrAdd");
	}
	public ActionForward zrrModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglNblgxyForm myForm        = (GyglNblgxyForm)form;
		GyglFzqLxrModel model = new GyglFzqLxrModel();
		myForm.setLxfs(DealString.toGBK(myForm.getLxfs()));
		myForm.setDzyx(DealString.toGBK(myForm.getDzyx()));
		myForm.setFzssbh(DealString.toGBK(myForm.getFzssbh()));
		myForm.setSsbhcn(DealString.toGBK(myForm.getSsbhcn()));
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		myForm.setSfzz(DealString.toGBK(myForm.getSfzz()));

		HashMap<String,String> map = new HashMap<String,String>();
		GyglNblgxyServices service   = new GyglNblgxyServices();
		String pkValue            = DealString.toGBK(request.getParameter("pkValue"));
		String isview             = DealString.toGBK(request.getParameter("view"));
		String doType             = request.getParameter("doType");
		if("save".equalsIgnoreCase(doType)){
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_zrrModiSave(model,pkValue);
			request.setAttribute("done",done);
		}
		map = service.serv_zrrInfo(pkValue);
		String lddm   = myForm.getLddm(); 
		request.setAttribute("rs",map);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("isview", isview);
		request.setAttribute("ldList",GyglShareDAO.getLdList());
		request.setAttribute("ssbhList",GyglShareDAO.GetSsbhList(lddm));
		return mapping.findForward("zrrModi");
	}
	public ActionForward zrrDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pkValue  = request.getParameter("pkValue");
		GyglNblgxyServices service   = new GyglNblgxyServices();
		service.ser_zrrDel(pkValue);
		return new ActionForward("/nblgxy_gygl.do?method=zrrManage",false);
	}
	public ActionForward tsxsManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		GyglNblgxyForm myForm        = (GyglNblgxyForm)form;
		String userName              = request.getSession().getAttribute("userName").toString();		 	
//		String userType              = request.getSession().getAttribute("userType").toString();
		String userOnline            = request.getSession().getAttribute("userOnLine").toString();
		String lddm  = myForm.getLddm();
		myForm.setXh(DealString.toGBK(myForm.getXh()));
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		request.setAttribute("ldList",GyglShareDAO.getLdList());
		request.setAttribute("ssbhList",GyglShareDAO.GetSsbhList(lddm));
        gyglDao.getXyZyBjxx(request);
        GyglNblgTsxsModel  model     = new GyglNblgTsxsModel();
        GyglNblgxyServices service   = new GyglNblgxyServices();
     
        if("go".equalsIgnoreCase(request.getParameter("go"))){
        	BeanUtils.copyProperties(model, myForm);
			ArrayList<HashMap<String, String>> topTr = service.serv_getTsxsTitle();
			ArrayList<String[]> rs    = service.serv_getTsxsResult(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
        }
		//读写权判断		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"tsxsManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
        return mapping.findForward("tsxsM");
	}
	public ActionForward tsxsAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{		
		gyglDao myDao  = new gyglDao();
		GyglNblgxyForm myForm        = (GyglNblgxyForm)form;
		GyglNblgTsxsModel  model     = new GyglNblgTsxsModel();
		myForm.setLxfs_ch(DealString.toGBK(myForm.getLxfs_ch()));
		myForm.setLxfs_dh(DealString.toGBK(myForm.getLxfs_dh()));
		myForm.setAhtc(DealString.toGBK(myForm.getAhtc()));
		myForm.setJtzk(DealString.toGBK(myForm.getJtzk()));
		myForm.setXfcj(DealString.toGBK(myForm.getXfcj()));
		myForm.setHjry(DealString.toGBK(myForm.getHjry()));
		myForm.setWjqk(DealString.toGBK(myForm.getWjqk()));
		myForm.setXgtzxg(DealString.toGBK(myForm.getXgtzxg()));
		myForm.setRjjwshhd(DealString.toGBK(myForm.getRjjwshhd()));
		myForm.setGzqk(DealString.toGBK(myForm.getGzqk()));
		myForm.setSsbh(DealString.toGBK(myForm.getSsbh()));
		myForm.setBz(DealString.toGBK(myForm.getBz()));
		String doType  = request.getParameter("doType");
		GyglNblgxyServices service   = new GyglNblgxyServices();
		HashMap<String,String> map = new HashMap<String,String>();
		String xh  = myForm.getXh();
        if("save".equalsIgnoreCase(doType)){
        	BeanUtils.copyProperties(model, myForm);
        	boolean done = service.serv_tsxsAddSave(model);
        	request.setAttribute("done",done);
        }else{
    		if(Base.isNull(map.get("ssbh"))&&!Base.isNull(xh)){
    			request.setAttribute("notIn","notIn");
    		}
        }
		map = myDao.getStuInfo(xh);
		request.setAttribute("rs",map);
		return mapping.findForward("tsxsAdd");
	}
    public ActionForward tsxsModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{		
		String pkValue       = request.getParameter("pkValue");
		String doType        = request.getParameter("doType");
		HashMap<String,String> map = new HashMap<String,String>();
		GyglNblgxyServices service   = new GyglNblgxyServices();
		GyglNblgxyForm myForm        = (GyglNblgxyForm)form;
		GyglNblgTsxsModel  model     = new GyglNblgTsxsModel();
		myForm.setLxfs_ch(DealString.toGBK(myForm.getLxfs_ch()));
		myForm.setLxfs_dh(DealString.toGBK(myForm.getLxfs_dh()));
		myForm.setAhtc(DealString.toGBK(myForm.getAhtc()));
		myForm.setJtzk(DealString.toGBK(myForm.getJtzk()));
		myForm.setXfcj(DealString.toGBK(myForm.getXfcj()));
		myForm.setHjry(DealString.toGBK(myForm.getHjry()));
		myForm.setWjqk(DealString.toGBK(myForm.getWjqk()));
		myForm.setXgtzxg(DealString.toGBK(myForm.getXgtzxg()));
		myForm.setRjjwshhd(DealString.toGBK(myForm.getRjjwshhd()));
		myForm.setGzqk(DealString.toGBK(myForm.getGzqk()));
		myForm.setSsbh(DealString.toGBK(myForm.getSsbh()));
		myForm.setBz(DealString.toGBK(myForm.getBz()));		
		if("save".equalsIgnoreCase(doType)){
	       	BeanUtils.copyProperties(model, myForm);
        	boolean done = service.serv_tsxsModiSave(model,pkValue);
        	request.setAttribute("done",done);
		}
		map = service.serv_tsxsInfo(pkValue);
        request.setAttribute("rs",map);
        request.setAttribute("pkValue",pkValue);
        return mapping.findForward("tsxsModi");
    }
    public ActionForward tsxsView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{		
		String pkValue       = request.getParameter("pkValue");
		HashMap<String,String> map = new HashMap<String,String>();
		GyglNblgxyServices service   = new GyglNblgxyServices();
		map = service.serv_tsxsInfo(pkValue);
        request.setAttribute("rs",map);
    	return mapping.findForward("tsxsView");
    }
	public ActionForward tsxsDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		String pkValue  = request.getParameter("pkValue");
		GyglNblgxyServices service   = new GyglNblgxyServices();
		service.ser_tsxsDel(pkValue);
		return new ActionForward("/nblgxy_gygl.do?method=tsxsManage",false);
	}
}
