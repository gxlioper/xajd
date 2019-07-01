/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ɽ��ѧ��Ԣ����Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2008-7-10 ����11:03:04</p>
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
	/**���������걨ҳ��*/
	public ActionForward wmqssb(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		HttpSession session        = request.getSession();    	
		GyglJgsdxServices Service   = new GyglJgsdxServices();
		HashMap<String,String> map = new HashMap<String,String>();
		String canDo               = "false";//�ܷ������־ 1��"true"��ʾ�ܲ��� 2��"false"��ʾ���ܲ���
//		String userType            = session.getAttribute("userType").toString();//�û�����
		String userName            = session.getAttribute("userName").toString();//�û���¼��
		String isFdy               = session.getAttribute("isFdy").toString();//�ж��Ƿ��Ǹ���Ա�û�    	 
//		String isStu               = userName.equalsIgnoreCase("student")?"true":"false";//�ж��Ƿ���ѧ���û�   
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
			map.put("xm",userName);
		}
//		else if(isStu.equalsIgnoreCase("true")){
//		canDo=isStu;
//		map.put("yhm",userName);
//		map.put("xm",userRealName);
//		}
		appendPropertitiesTowPart(request);//�����б�
		request.setAttribute("rs",map);
		request.setAttribute("canDo",canDo);//
		return mapping.findForward("jgsdx_wmqssb");		
	}

	/**���������걨��Ϣ����
	 * @throws InvocationTargetException 
	 * @throws Exception */
	public ActionForward wmqsSbSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception, InvocationTargetException{
		String xn      = request.getParameter("xn");
		String xq      = request.getParameter("xq");
		String ssbh    = request.getParameter("lddm")+"-"+request.getParameter("qsh");
		String pkValue = xn+xq+ssbh;//����

		GyglJgsdxForm Form         = (GyglJgsdxForm)form;//
		GyglJgsdxServices Service  = new GyglJgsdxServices();
		GyglJgsdxWmqssbModel Model = new GyglJgsdxWmqssbModel();//���������걨��Ϣ����MODEL
		BeanUtils.copyProperties(Model, Form);
		boolean done = Service.sbXxSave(Model);
		request.setAttribute("done",done);
		return new ActionForward("/jgsdx_gygl.do?method=wmqssb&pkValue="+pkValue,false);
	}
   
	/**��������������˲�ѯ��ʼҳ��*/
    public ActionForward wmqsSbShDef(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){ 
    	GyglJgsdxForm Form    = (GyglJgsdxForm)form;
    	Form.setXn(Base.currXn);//Ĭ�ϵ�ǰѧ��
    	Form.setXq(Base.currXq);//Ĭ�ϵ�ǰѧ��
    	String userType      = request.getSession().getAttribute("userType").toString();
    	if(userType.equalsIgnoreCase("stu")){//ѧ���û���Ȩ����
    		request.setAttribute("errMsg", "��Ȩ���ʸ�ҳ�棡");
    		return new ActionForward("/errMsg.do", false);
    	}    	 
    	appendPropertitiesTowPart(request);//�����б�
    	return mapping.findForward("jgsdx_wmqsshcx"); 
    }
    
    /**��������������˲�ѯִ��
     * @throws InvocationTargetException 
     * @throws IllegalAccessException */
     public ActionForward wmqsSbShQer(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException{
    	 GyglJgsdxForm Form         = (GyglJgsdxForm)form;
    	 GyglJgsdxServices Service  = new GyglJgsdxServices();
    	 GyglJgsdxWmqssbModel Model = new GyglJgsdxWmqssbModel();//���������걨��Ϣ����MODEL
       	 Form.setXn(Base.currXn);//Ĭ�ϵ�ǰѧ��
    	 Form.setXq(Base.currXq);//Ĭ�ϵ�ǰѧ��
         String userType           = request.getSession().getAttribute("userType").toString();
         String userName           = request.getSession().getAttribute("userName").toString();
         String isFdy              = request.getSession().getAttribute("isFdy").toString();
         BeanUtils.copyProperties(Model, Form);
         ArrayList<HashMap<String, String>> topTr = Service.getwmQsSbSearchTitle(userType,isFdy);
         ArrayList<String[]> rs    = Service.getwmQsSbSearchResult(userName, userType, Model,isFdy);
         appendPropertitiesTowPart(request);//�����б�
         request.setAttribute("topTr",topTr);
         request.setAttribute("rs", rs);
 		 request.setAttribute("rsNum", rs != null ? rs.size():0);
         return mapping.findForward("jgsdx_wmqsshcx");
     }

     /**�����������ҳ����Ϣ*/
     public ActionForward wmqsSbSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) {
    	 HashMap<String,String> map  = new HashMap<String,String>();
    	 String userType             = request.getSession().getAttribute("userType").toString();
    	 String pkValue              = request.getParameter("pkValue");
    	 String isFdy                = request.getSession().getAttribute("isFdy").toString();
    	 GyglJgsdxServices Service   = new GyglJgsdxServices();
    	 String shType               = "";
    	 map = Service.getwmSbOneInfo(pkValue,userType,isFdy);//�������������Ϣ
    	 //Ȩ���ж�
    	 boolean isStu = (userType.equalsIgnoreCase("stu"));				 //�ж��Ƿ���ѧ���û���¼
 		 String sUName = request.getSession().getAttribute("userName").toString();	//�õ���¼�û���
    	 String power = "wmqs_sh.do";
		 request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**�ж��û���дȨ*/		
		 String isUserReq = Service.isUserReg(pkValue,sUName);		 
		 request.setAttribute("shType", shType);
		 request.setAttribute("isUserReq", isUserReq);//�жϸ����������Ҽ�¼�Ƿ��ǵ�ǰ�û����걨
		 request.setAttribute("rs",map);
     	 request.setAttribute("pkValue",pkValue);
    	 appendPropertitiesTowPart(request);
    	 return mapping.findForward("jgsdx_wmqssbsh");
     }
     /**�������������Ϣ����
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
