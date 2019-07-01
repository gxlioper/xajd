package xgxt.xsgygl.ahjzgyxy;

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
import xgxt.xsgygl.dao.GyglShareDAO;
import xgxt.xsgygl.dao.gyglDao;


/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 安徽建筑工业学院公寓管理Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-30</p>
 */
public class GyglAhjgAction extends  DispatchAction {
	/**文明寝室申报默认页面*/
     public ActionForward wmqsSb(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse Response){   	 
    	 if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){//井冈山大学
    		 return new ActionForward("/jgsdx_gygl.do?method=wmqssb",false);
    	 }
    	 HttpSession session        = request.getSession();    	
    	 GyglAhjgServices Service   = new GyglAhjgServices();
    	 HashMap<String,String> map = new HashMap<String,String>();
    	 String canDo               = "false";//能否操作标志 1、"true"表示能操作 2、"false"表示不能操作
//    	 String userType            = session.getAttribute("userType").toString();//用户类型
    	 String userName            = session.getAttribute("userName").toString();//用户登录名
    	 String userRealName        = session.getAttribute("userNameReal").toString();// 用户真实姓名
    	 String isFdy               = session.getAttribute("isFdy").toString();//判断是否是辅导员用户    	 
//    	 String isStu               = userName.equalsIgnoreCase("student")?"true":"false";//判断是否是学生用户   
    	 String pkValue             = request.getParameter("pkValue");//数据保存后返回的主键值
    	 //申报信息保存后 将信息存入MAP中 开始
    	 if(pkValue!=null&&!pkValue.equalsIgnoreCase("")){
    		 map = Service.getWmQsSbXx(pkValue);
    	 }else{//    	申报信息保存后 将信息存入MAP中 结束   		
    		 map.put("xn",Base.currXn);//当前学年
    		 map.put("xq",Base.currXq);//当前学期
    	 }
    	 if(isFdy.equalsIgnoreCase("true")){
			 canDo=isFdy;
			 map.put("yhm",userName);
			 map.put("xm",userRealName);
		 }
    	 if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
    		 request.setAttribute("qslb","qslb");
    		 request.setAttribute("qslbList",GyglShareDAO.getqslbList());
    	 }
    	
         appendPropertitiesTowPart(request);//所需列表
    	 request.setAttribute("rs",map);
    	 request.setAttribute("canDo",canDo);//
    	 return mapping.findForward("wmqssb");
     }
     /**文明寝室申报信息保存
     * @throws InvocationTargetException 
     * @throws Exception */
     public ActionForward wmqsSbSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception, InvocationTargetException{
    	 String xn      = request.getParameter("xn");
    	 String xq      = request.getParameter("xq");
    	 String ssbh    = request.getParameter("lddm")+"-"+request.getParameter("qsh");
    	 String pkValue = xn+xq+ssbh;//主键
    	 
    	 GyglAhjgForm Form         = (GyglAhjgForm)form;//
    	 GyglAhjgServices Service  = new GyglAhjgServices();
    	 GyglAhjgWmqssbModel Model = new GyglAhjgWmqssbModel();//文明寝室申报信息参数MODEL
    	 BeanUtils.copyProperties(Model, Form);
    	 boolean done = Service.sbXxSave(Model);
    	 request.setAttribute("done",done);
    	 return new ActionForward("/ahjg_gygl.do?method=wmqsSb&pkValue="+pkValue,false);
     }
     /**文明寝室申请审核查询初始页面*/
     public ActionForward wmqsSbShDef(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){ 
    	 if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){//井冈山大学
    		 return new ActionForward("/jgsdx_gygl.do?method=wmqsSbShDef",false);
    	 }
    	 if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){//宁波理工学院
    		 return new ActionForward("/nblgxy_gygl.do?method=nblgxy_WmqsSbShDef",false);
    	 }
    	 GyglAhjgForm Form    = (GyglAhjgForm)form;
    	 Form.setXn(Base.currXn);//默认当前学年
    	 Form.setXq(Base.currXq);//默认当前学期
    	 String userType      = request.getSession().getAttribute("userType").toString();
    	 if(userType.equalsIgnoreCase("stu")){//学生用户无权访问
    		 request.setAttribute("errMsg", "无权访问该页面！");
    		 return new ActionForward("/errMsg.do", false);
    	 }    	 
		 appendPropertitiesTowPart(request);//所需列表
    	 return mapping.findForward("wmqsshcx"); 
     }
     /**文明寝室申请审核查询执行
     * @throws InvocationTargetException 
     * @throws IllegalAccessException */
     public ActionForward wmqsSbShQer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
    	 GyglAhjgForm Form         = (GyglAhjgForm)form;
    	 GyglAhjgServices Service  = new GyglAhjgServices();
    	 GyglAhjgWmqssbModel Model = new GyglAhjgWmqssbModel();//文明寝室申报信息参数MODEL
       	 Form.setXn(Base.currXn);//默认当前学年
    	 Form.setXq(Base.currXq);//默认当前学期
         String userType           = request.getSession().getAttribute("userType").toString();
         String userName           =  request.getSession().getAttribute("userName").toString();
         BeanUtils.copyProperties(Model, Form);
         ArrayList<HashMap<String, String>> topTr = Service.getwmQsSbSearchTitle(userType);
         ArrayList<String[]> rs    = Service.getwmQsSbSearchResult(userName, userType, Model);
         appendPropertitiesTowPart(request);//所需列表
         request.setAttribute("topTr",topTr);
         request.setAttribute("rs", rs);
 		 request.setAttribute("rsNum", rs != null ? rs.size():0);
         return mapping.findForward("wmqsshcx");
     }
     /**文明寝室审核信息*/
     public ActionForward wmqsSbSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
    	 HashMap<String,String> map  = new HashMap<String,String>();
    	 HashMap<String,String> map1 = new HashMap<String,String>();
    	 HashMap<String,String> map2 = new HashMap<String,String>();
    	 String userType             = request.getSession().getAttribute("userType").toString();
    	 String pkValue              = request.getParameter("pkValue");
    	 String shType               = "";
    	 GyglAhjgServices Service    = new GyglAhjgServices();
    	 map = Service.getwmSbOneInfo(pkValue,userType);//文明寝室相关信息
    	 map1= Service.getwmQsWjInfo(pkValue);//寝室内学生当前学年违纪处分相关信息
    	 map2= Service.getwmQsBlInf();//当前学年文明寝室数比例及相关信息
    	 if("xy".equalsIgnoreCase(userType)){//学院审核
			 shType = "院系";
		 }else if("xx".equalsIgnoreCase(userType)
				 ||"admin".equalsIgnoreCase(userType)){//学校审核
			 shType = "学校";
		 }
    	 request.setAttribute("shType",shType);
    	 request.setAttribute("rs",map);
    	 request.setAttribute("rs1", map1);
    	 request.setAttribute("rs2",map2);
    	 request.setAttribute("pkValue",pkValue);
    	 appendPropertitiesTowPart(request);
    	 return mapping.findForward("wmqssbsh");
     }
     /**文明寝室审核信息保存
     * @throws Exception */
     public ActionForward wmqsSbShSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	 GyglAhjgForm gaF           = (GyglAhjgForm)form;
    	 String yesNo               = gaF.getYesNo();
    	 String pkValue             = request.getParameter("pkValue");
    	 String userType            = request.getSession().getAttribute("userType").toString();
    	 GyglAhjgServices Service   = new GyglAhjgServices();
      	 boolean done = Service.wmSbShSave(pkValue, userType, yesNo);
    	 request.setAttribute("done",done);
    	 request.setAttribute("pkValue",pkValue);
      	 return mapping.findForward("wmqssbsh");
     }
     /**
 	 * 在request对象中增加相应的项目:njList,xyList,zyList,bjList
 	 * @param request
 	 */
 	@SuppressWarnings("unused")
	private void appendProperitiesOnePart(HttpServletRequest request,String xydm,String zydm,String nj){		
  		String xy      = "";
 		String zy      = "";
 		String njLocal = nj;
 		xy             = xy==null ? "": xydm; 
 		zy             = zy==null ? "": zydm; 
 		njLocal        = nj==null ? "": njLocal;
 		
 		String zyKey   = xy==null ? "":xy; 
 		String bjKey   = xy+"!!"+zy+"!!"+njLocal;

 		request.setAttribute("njList", Base.getNjList());
 		request.setAttribute("xyList", Base.getXyList());
 		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
 		request.setAttribute("bjList", Base.getBjMap().get(bjKey));		
 	}
 	 /**
 	 * 在request对象中增加相应的项目:xnList,xqList,ldList,qshList,cheList
 	 * @param request
 	 */
 	private void appendPropertitiesTowPart(HttpServletRequest request){
		gyglDao gyDao = new gyglDao();
 		DAO dao       = DAO.getInstance();
 		request.setAttribute("xnList",Base.getXnndList());
 		request.setAttribute("xqList",Base.getXqList());
 		request.setAttribute("ldList",gyDao.getGyLdList());//公寓楼栋列表
 		request.setAttribute("qshList",gyDao.getQshList());//公寓寝室号列表
 		request.setAttribute("chkList", dao.getChkList(3));//审核状态列表
 	}
}
