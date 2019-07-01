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
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս�����ҵѧԺ��Ԣ����Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-30</p>
 */
public class GyglAhjgAction extends  DispatchAction {
	/**���������걨Ĭ��ҳ��*/
     public ActionForward wmqsSb(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse Response){   	 
    	 if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){//����ɽ��ѧ
    		 return new ActionForward("/jgsdx_gygl.do?method=wmqssb",false);
    	 }
    	 HttpSession session        = request.getSession();    	
    	 GyglAhjgServices Service   = new GyglAhjgServices();
    	 HashMap<String,String> map = new HashMap<String,String>();
    	 String canDo               = "false";//�ܷ������־ 1��"true"��ʾ�ܲ��� 2��"false"��ʾ���ܲ���
//    	 String userType            = session.getAttribute("userType").toString();//�û�����
    	 String userName            = session.getAttribute("userName").toString();//�û���¼��
    	 String userRealName        = session.getAttribute("userNameReal").toString();// �û���ʵ����
    	 String isFdy               = session.getAttribute("isFdy").toString();//�ж��Ƿ��Ǹ���Ա�û�    	 
//    	 String isStu               = userName.equalsIgnoreCase("student")?"true":"false";//�ж��Ƿ���ѧ���û�   
    	 String pkValue             = request.getParameter("pkValue");//���ݱ���󷵻ص�����ֵ
    	 //�걨��Ϣ����� ����Ϣ����MAP�� ��ʼ
    	 if(pkValue!=null&&!pkValue.equalsIgnoreCase("")){
    		 map = Service.getWmQsSbXx(pkValue);
    	 }else{//    	�걨��Ϣ����� ����Ϣ����MAP�� ����   		
    		 map.put("xn",Base.currXn);//��ǰѧ��
    		 map.put("xq",Base.currXq);//��ǰѧ��
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
    	
         appendPropertitiesTowPart(request);//�����б�
    	 request.setAttribute("rs",map);
    	 request.setAttribute("canDo",canDo);//
    	 return mapping.findForward("wmqssb");
     }
     /**���������걨��Ϣ����
     * @throws InvocationTargetException 
     * @throws Exception */
     public ActionForward wmqsSbSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception, InvocationTargetException{
    	 String xn      = request.getParameter("xn");
    	 String xq      = request.getParameter("xq");
    	 String ssbh    = request.getParameter("lddm")+"-"+request.getParameter("qsh");
    	 String pkValue = xn+xq+ssbh;//����
    	 
    	 GyglAhjgForm Form         = (GyglAhjgForm)form;//
    	 GyglAhjgServices Service  = new GyglAhjgServices();
    	 GyglAhjgWmqssbModel Model = new GyglAhjgWmqssbModel();//���������걨��Ϣ����MODEL
    	 BeanUtils.copyProperties(Model, Form);
    	 boolean done = Service.sbXxSave(Model);
    	 request.setAttribute("done",done);
    	 return new ActionForward("/ahjg_gygl.do?method=wmqsSb&pkValue="+pkValue,false);
     }
     /**��������������˲�ѯ��ʼҳ��*/
     public ActionForward wmqsSbShDef(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){ 
    	 if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_JGSDX)){//����ɽ��ѧ
    		 return new ActionForward("/jgsdx_gygl.do?method=wmqsSbShDef",false);
    	 }
    	 if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){//������ѧԺ
    		 return new ActionForward("/nblgxy_gygl.do?method=nblgxy_WmqsSbShDef",false);
    	 }
    	 GyglAhjgForm Form    = (GyglAhjgForm)form;
    	 Form.setXn(Base.currXn);//Ĭ�ϵ�ǰѧ��
    	 Form.setXq(Base.currXq);//Ĭ�ϵ�ǰѧ��
    	 String userType      = request.getSession().getAttribute("userType").toString();
    	 if(userType.equalsIgnoreCase("stu")){//ѧ���û���Ȩ����
    		 request.setAttribute("errMsg", "��Ȩ���ʸ�ҳ�棡");
    		 return new ActionForward("/errMsg.do", false);
    	 }    	 
		 appendPropertitiesTowPart(request);//�����б�
    	 return mapping.findForward("wmqsshcx"); 
     }
     /**��������������˲�ѯִ��
     * @throws InvocationTargetException 
     * @throws IllegalAccessException */
     public ActionForward wmqsSbShQer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
    	 GyglAhjgForm Form         = (GyglAhjgForm)form;
    	 GyglAhjgServices Service  = new GyglAhjgServices();
    	 GyglAhjgWmqssbModel Model = new GyglAhjgWmqssbModel();//���������걨��Ϣ����MODEL
       	 Form.setXn(Base.currXn);//Ĭ�ϵ�ǰѧ��
    	 Form.setXq(Base.currXq);//Ĭ�ϵ�ǰѧ��
         String userType           = request.getSession().getAttribute("userType").toString();
         String userName           =  request.getSession().getAttribute("userName").toString();
         BeanUtils.copyProperties(Model, Form);
         ArrayList<HashMap<String, String>> topTr = Service.getwmQsSbSearchTitle(userType);
         ArrayList<String[]> rs    = Service.getwmQsSbSearchResult(userName, userType, Model);
         appendPropertitiesTowPart(request);//�����б�
         request.setAttribute("topTr",topTr);
         request.setAttribute("rs", rs);
 		 request.setAttribute("rsNum", rs != null ? rs.size():0);
         return mapping.findForward("wmqsshcx");
     }
     /**�������������Ϣ*/
     public ActionForward wmqsSbSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
    	 HashMap<String,String> map  = new HashMap<String,String>();
    	 HashMap<String,String> map1 = new HashMap<String,String>();
    	 HashMap<String,String> map2 = new HashMap<String,String>();
    	 String userType             = request.getSession().getAttribute("userType").toString();
    	 String pkValue              = request.getParameter("pkValue");
    	 String shType               = "";
    	 GyglAhjgServices Service    = new GyglAhjgServices();
    	 map = Service.getwmSbOneInfo(pkValue,userType);//�������������Ϣ
    	 map1= Service.getwmQsWjInfo(pkValue);//������ѧ����ǰѧ��Υ�ʹ��������Ϣ
    	 map2= Service.getwmQsBlInf();//��ǰѧ�����������������������Ϣ
    	 if("xy".equalsIgnoreCase(userType)){//ѧԺ���
			 shType = "Ժϵ";
		 }else if("xx".equalsIgnoreCase(userType)
				 ||"admin".equalsIgnoreCase(userType)){//ѧУ���
			 shType = "ѧУ";
		 }
    	 request.setAttribute("shType",shType);
    	 request.setAttribute("rs",map);
    	 request.setAttribute("rs1", map1);
    	 request.setAttribute("rs2",map2);
    	 request.setAttribute("pkValue",pkValue);
    	 appendPropertitiesTowPart(request);
    	 return mapping.findForward("wmqssbsh");
     }
     /**�������������Ϣ����
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
 	 * ��request������������Ӧ����Ŀ:njList,xyList,zyList,bjList
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
 	 * ��request������������Ӧ����Ŀ:xnList,xqList,ldList,qshList,cheList
 	 * @param request
 	 */
 	private void appendPropertitiesTowPart(HttpServletRequest request){
		gyglDao gyDao = new gyglDao();
 		DAO dao       = DAO.getInstance();
 		request.setAttribute("xnList",Base.getXnndList());
 		request.setAttribute("xqList",Base.getXqList());
 		request.setAttribute("ldList",gyDao.getGyLdList());//��Ԣ¥���б�
 		request.setAttribute("qshList",gyDao.getQshList());//��Ԣ���Һ��б�
 		request.setAttribute("chkList", dao.getChkList(3));//���״̬�б�
 	}
}
