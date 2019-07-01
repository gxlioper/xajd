/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 井冈山大学公寓管理Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-10 上午11:03:04</p>
 */
package xgxt.xsgygl.jgsdx;

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

import xgxt.DAO.DAO;
import xgxt.action.Base;


import xgxt.xsgygl.dao.gyglDao;

public class GyglJgsdxAction extends DispatchAction {
	/**文明寝室申报页面*/
	public ActionForward wmqssb(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session        = request.getSession();    	
		GyglJgsdxServices Service   = new GyglJgsdxServices();
		HashMap<String,String> map = new HashMap<String,String>();
		String canDo               = "false";//能否操作标志 1、"true"表示能操作 2、"false"表示不能操作
//		String userType            = session.getAttribute("userType").toString();//用户类型
		String userName            = session.getAttribute("userName").toString();//用户登录名
		String isFdy               = session.getAttribute("isFdy").toString();//判断是否是辅导员用户    	 
//		String isStu               = userName.equalsIgnoreCase("student")?"true":"false";//判断是否是学生用户   
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
			map.put("xm",userName);
		}
//		else if(isStu.equalsIgnoreCase("true")){
//		canDo=isStu;
//		map.put("yhm",userName);
//		map.put("xm",userRealName);
//		}
		appendPropertitiesTowPart(request);//所需列表
		request.setAttribute("rs",map);
		request.setAttribute("canDo",canDo);//
		return mapping.findForward("jgsdx_wmqssb");		
	}

	/**文明寝室申报信息保存
	 * @throws InvocationTargetException 
	 * @throws Exception */
	public ActionForward wmqsSbSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception, InvocationTargetException{
		String xn      = request.getParameter("xn");
		String xq      = request.getParameter("xq");
		String ssbh    = request.getParameter("lddm")+"-"+request.getParameter("qsh");
		String pkValue = xn+xq+ssbh;//主键

		GyglJgsdxForm Form         = (GyglJgsdxForm)form;//
		GyglJgsdxServices Service  = new GyglJgsdxServices();
		GyglJgsdxWmqssbModel Model = new GyglJgsdxWmqssbModel();//文明寝室申报信息参数MODEL
		BeanUtils.copyProperties(Model, Form);
		boolean done = Service.sbXxSave(Model);
		request.setAttribute("done",done);
		return new ActionForward("/jgsdx_gygl.do?method=wmqssb&pkValue="+pkValue,false);
	}
   
	/**文明寝室申请审核查询初始页面*/
    public ActionForward wmqsSbShDef(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){ 
    	GyglJgsdxForm Form    = (GyglJgsdxForm)form;
    	Form.setXn(Base.currXn);//默认当前学年
    	Form.setXq(Base.currXq);//默认当前学期
    	String userType      = request.getSession().getAttribute("userType").toString();
    	if(userType.equalsIgnoreCase("stu")){//学生用户无权访问
    		request.setAttribute("errMsg", "无权访问该页面！");
    		return new ActionForward("/errMsg.do", false);
    	}    	 
    	appendPropertitiesTowPart(request);//所需列表
    	return mapping.findForward("jgsdx_wmqsshcx"); 
    }
    
    /**文明寝室申请审核查询执行
     * @throws InvocationTargetException 
     * @throws IllegalAccessException */
     public ActionForward wmqsSbShQer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
    	 GyglJgsdxForm Form         = (GyglJgsdxForm)form;
    	 GyglJgsdxServices Service  = new GyglJgsdxServices();
    	 GyglJgsdxWmqssbModel Model = new GyglJgsdxWmqssbModel();//文明寝室申报信息参数MODEL
       	 Form.setXn(Base.currXn);//默认当前学年
    	 Form.setXq(Base.currXq);//默认当前学期
         String userType           = request.getSession().getAttribute("userType").toString();
         String userName           = request.getSession().getAttribute("userName").toString();
         String isFdy              = request.getSession().getAttribute("isFdy").toString();
         BeanUtils.copyProperties(Model, Form);
         ArrayList<HashMap<String, String>> topTr = Service.getwmQsSbSearchTitle(userType,isFdy);
         ArrayList<String[]> rs    = Service.getwmQsSbSearchResult(userName, userType, Model,isFdy);
         appendPropertitiesTowPart(request);//所需列表
         request.setAttribute("topTr",topTr);
         request.setAttribute("rs", rs);
 		 request.setAttribute("rsNum", rs != null ? rs.size():0);
         return mapping.findForward("jgsdx_wmqsshcx");
     }

     /**文明寝室审核页面信息*/
     public ActionForward wmqsSbSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
    	 HashMap<String,String> map  = new HashMap<String,String>();
    	 String userType             = request.getSession().getAttribute("userType").toString();
    	 String pkValue              = request.getParameter("pkValue");
    	 String isFdy                = request.getSession().getAttribute("isFdy").toString();
    	 GyglJgsdxServices Service   = new GyglJgsdxServices();
    	 String shType               = "";
    	 map = Service.getwmSbOneInfo(pkValue,userType,isFdy);//文明寝室相关信息
    	 //权限判断
    	 boolean isStu = (userType.equalsIgnoreCase("stu"));				 //判断是否是学生用户登录
 		 String sUName = request.getSession().getAttribute("userName").toString();	//得到登录用户名
    	 String power = "wmqs_sh.do";
		 request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**判断用户读写权*/		
		 String isUserReq = Service.isUserReg(pkValue,sUName);		 
		 request.setAttribute("shType", shType);
		 request.setAttribute("isUserReq", isUserReq);//判断该条文明寝室记录是否是当前用户所申报
		 request.setAttribute("rs",map);
     	 request.setAttribute("pkValue",pkValue);
    	 appendPropertitiesTowPart(request);
    	 return mapping.findForward("jgsdx_wmqssbsh");
     }
     /**文明寝室审核信息保存
      * @throws Exception */
      public ActionForward wmqsSbShSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	  GyglJgsdxForm gaF         = (GyglJgsdxForm)form;
     	 String yesNo               = gaF.getYesNo();
     	 String writeAble           = request.getParameter("writeAble");
     	 String pkValue             = request.getParameter("pkValue");
     	 String isUserReq           = request.getParameter("isUserReq");
     	 String userType            = request.getSession().getAttribute("userType").toString();
       	 String isFdy               = request.getSession().getAttribute("isFdy").toString();
     	 GyglJgsdxServices Service  = new GyglJgsdxServices();
       	 boolean done = Service.wmSbShSave(pkValue, userType, yesNo,isFdy);
     	 request.setAttribute("done",done);
     	 request.setAttribute("pkValue",pkValue);
     	 request.setAttribute("writeAble", writeAble);
     	 request.setAttribute("isUserReq", isUserReq);
       	 return mapping.findForward("jgsdx_wmqssbsh");
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
