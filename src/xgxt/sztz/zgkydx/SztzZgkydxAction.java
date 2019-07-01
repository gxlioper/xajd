/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-3-27 ����11:17:58</p>
 */
package xgxt.sztz.zgkydx;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

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


public class SztzZgkydxAction extends DispatchAction {
	public final ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		// TODO �Զ����ɷ������
		try {
			HttpSession session = request.getSession();
			/** ���߼�� */
			int i = Base.chkTimeOut(session);
			if (i <= 2) {
				request.setAttribute("errMsg", "��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errMsg", "ϵͳ���ִ��������µ�¼��" + e.toString());
			return new ActionForward("/errMsg.do", false);
		}
		return super.execute(mapping,form,request, response);
	} 
    public ActionForward infoInput(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException{    	
    	SztzZgkydxService service  = new SztzZgkydxService();
    	String userName = request.getSession().getAttribute("userName").toString();
    	String userType = request.getSession().getAttribute("userType").toString();
    	HashMap<String,String> mapXnXq = service.getLimXnXq();
    	List<HashMap<String, String>> tzKmList = service.getTzKm();
    	String kmmcStr = "";
    	String kmdmStr = "";
    	String xn = mapXnXq.get("xn");
    	String xq = mapXnXq.get("xq");
    	HashMap<String,String> map = new HashMap<String, String>();    	
    	// ===========begin luojw 2009/6/5==================
    	String kmdm = request.getParameter("kmdm");
		//Vector<Object> rs = new Vector<Object>();
		//HashMap<String, Object> mapRs = new HashMap<String, Object>();
		Vector<Object> kmXmList = service.serv_infoInpQuer1(xn, xq, userName,kmdm);
		String hddm =(String)kmXmList.get(kmXmList.size()-1);
		// ===========end luojw 2009/6/5==================
		// ��ò������ñ�����(�����������ʱ�䷶Χ)
		if ("empty".equalsIgnoreCase(service.getTimeLim())) {			
			request.setAttribute("sqsjTag", "1");
		}  	
		map = service.getXsInfo(userName);
		kmXmList.remove(kmXmList.size()-1);
		request.setAttribute("hddm",hddm);
		request.setAttribute("rs",map);
		request.setAttribute("rs1", service.getLimXnXq());
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());		
    	request.setAttribute("kmXmList",kmXmList);
    	
    	if(kmXmList==null||!"stu".equalsIgnoreCase(userType)){
    		request.setAttribute("stop","stop");
    	}
    	if(tzKmList!=null){
    		for(int i=0;i<tzKmList.size();i++){
    			kmdmStr +=tzKmList.get(i).get("kmdm").toString()+"/";
    			kmmcStr +=tzKmList.get(i).get("kmm").toString()+"/";
    		}
    	}
    	request.setAttribute("kmdmstr", kmdmStr);
    	request.setAttribute("kmmcstr", kmmcStr);
    	request.setAttribute("xn",xn);
    	request.setAttribute("xq",xq);
    	
    	return mapping.findForward("infoInput");
    }
    public  ActionForward infoSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException{    	    	
//    	String userName = request.getSession().getAttribute("userName").toString();
    	SztzZgkydxService service  = new SztzZgkydxService();
    	List<HashMap<String, String>> kmList = service.getTzKm();
    	StringBuffer delSqlV = new StringBuffer();
    	StringBuffer inSqlV  = new StringBuffer();
    	HashMap<String,String> map = service.getLimXnXq();
    	String actType = request.getParameter("actType");
        String xh = request.getParameter("xh");
    	String xn = map.get("xn");
    	String xq = map.get("xq");
    	if(kmList!=null||!Base.isNull(xn)
    			        ||!Base.isNull(xq)){
    		for(int i=0;i<kmList.size();i++){
    			for(int j=1;j>0;j++){
    				String kmdm = kmList.get(i).get("kmdm").toString();
    				String xmmc = DealString.toGBK(request.getParameter("xmmc/"+kmdm+"/"+j));
    				if(!Base.isNull(xmmc)){
    					String cj = DealString.toGBK(request.getParameter("cj/"+kmdm+"/"+j));
    					String js = DealString.toGBK(request.getParameter("js/"+kmdm+"/"+j));
    					String jb = DealString.toGBK(request.getParameter("jb/"+kmdm+"/"+j));
    					String cjsj = DealString.toGBK(request.getParameter("cjsj/"+kmdm+"/"+j));
    					delSqlV.append("delete from zgkd_tzcjb where xn||xq||xh||xmmc||js||jb='"+xn+xq+xh+xmmc+js+jb+"'"); 
    					delSqlV.append("!!");
    					inSqlV.append("insert into zgkd_tzcjb(xn,xq,xh,xmmc,kmdm,js,jb,cj,cjsj,lrsj)values('"+xn+"','"+xq+"','"+xh+"','"+xmmc+"','"+kmdm+"','"+js+"','"+jb+"','"+cj+"','"+cjsj+"','"+DealString.getDateTime()+"')");
    					inSqlV.append("!!");
    				}else{
    					break;
    				}
    			}
    		}
    	}
    	boolean done = service.ser_infoSave(delSqlV, inSqlV);
    	request.setAttribute("done",done);
        if("infoInput".equalsIgnoreCase(actType)){
    		return new ActionForward("/sztzInfoInput.do",false);
    	}else if("infoModi".equalsIgnoreCase(actType)){
    		return new ActionForward("/zgkydx_sztz.do?method=infoModi",false);
    	}else {
    		return new ActionForward("/zgkydx_sztz.do?method=infoAdd",false);
    	}
    }
    public ActionForward infoManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException{
    	SztzZgkydxActionForm myForm  = (SztzZgkydxActionForm)form;
    	SztzZgkydxService service  = new SztzZgkydxService();
    	String userType = request.getSession().getAttribute("userType").toString();
    	String userName = request.getSession().getAttribute("userName").toString();
    	String userDep  =  request.getSession().getAttribute("userDep").toString();
    	String userOnline = request.getSession().getAttribute("userOnLine").toString();    	
    	HashMap<String,String> mapXnXq = service.getLimXnXq();
    	String xn = myForm.getXn();
    	String xq = myForm.getXq();
    	if(xn==null){
    		myForm.setXn(mapXnXq.get("xn"));
    	}
    	if(xq==null){
    		myForm.setXq(mapXnXq.get("xq"));
    	}
    	String xh = DealString.toGBK(myForm.getXh());
    	String xm = DealString.toGBK(myForm.getXm());
    	myForm.setXh(xh);
    	myForm.setXm(xm);
    	String xydm = myForm.getXydm();
    	String zydm = myForm.getZydm();
    	String bjdm = myForm.getBjdm();
        String nj   = myForm.getNj();
    	if("stu".equalsIgnoreCase(userType)){
    		HashMap<String,String> map = service.getXsInfo(userName);
    		xydm = map.get("xydm");
    		zydm = map.get("zydm");
    		bjdm = map.get("bjdm");
    		nj   = map.get("nj");
    		myForm.setXydm(xydm);
    		myForm.setZydm(zydm);
    		myForm.setBjdm(bjdm);
    	}else if("xy".equalsIgnoreCase(userType)){
    		myForm.setXydm(userDep);
    	}
    	
    	if("go".equalsIgnoreCase(request.getParameter("go"))){
        	TzInfoCxModel model = new TzInfoCxModel();
        	BeanUtils.copyProperties(model, myForm);   
			ArrayList<HashMap<String, String>> topTr = service.serv_infoTitle();
			ArrayList<String[]> rs    = service.serv_infoQuerry(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
    	}
		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
		String bjKey = xydm + "!!" + zydm + "!!" + nj;

    	request.setAttribute("xnList",Base.getXnndList());
    	request.setAttribute("xqList", Base.getXqList());
    	request.setAttribute("njList",Base.getNjList());
    	request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		//��дȨ�ж�		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"sztzInfoManage.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
    	return mapping.findForward("infoManage");
    }
    
    public ActionForward infoView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){

	    String pkValue = request.getParameter("pkValue");
	    String xxdm = Base.xxdm;
	    SztzZgkydxService service  = new SztzZgkydxService();
	    HashMap<String,String> xsmap = new HashMap<String, String>();   
	    xsmap = service.getTzXsInfo("view_zgkd_grsztzxx","xn||xq||xh",pkValue);
	    //xsmap = service.getTzXsInfo("view_zgkd_grsztzxx","xn||xq||xh",pkValue);
	    request.setAttribute("rsxs", xsmap);
	    request.setAttribute("zsbh", "32"+xxdm+xsmap.get("xh"));
	    request.setAttribute("xxmc", Base.xxmc);
	    Vector<Object> kmXmList = service.serv_infoInpQuer(xsmap.get("xn"), xsmap.get("xq"), xsmap.get("xh"));
	    HashMap<String,String> map = new HashMap<String, String>();			
				
	    request.setAttribute("pkValue",pkValue);		
		request.setAttribute("rs",map);		
//		request.setAttribute("xnList",Base.getXnndList());
//		request.setAttribute("xqList",Base.getXqList());
    	request.setAttribute("kmXmList",kmXmList);
		request.setAttribute("view",request.getParameter("view"));
		request.setAttribute("select",request.getParameter("select"));
		request.setAttribute("sel",request.getParameter("sel"));
    	return mapping.findForward("infoView");
    }
    public ActionForward infoAdd(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException{    	
    	SztzZgkydxService service  = new SztzZgkydxService();
    	String xh = request.getParameter("xh");
    	String userType = request.getSession().getAttribute("userType").toString();   	
    	String xn = request.getParameter("xn");
    	String xq = request.getParameter("xq");   	
    	HashMap<String,String> map = new HashMap<String, String>();  
    	List<HashMap<String, String>> tzKmList = service.getTzKm();
    	String kmmcStr = "";
    	String kmdmStr = "";
    	// ==============begin luojw 2009/6/9 ===================
		String kmdm = request.getParameter("kmdm");

		// Vector<Object> rs = new Vector<Object>();
		// HashMap<String, Object> mapRs = new HashMap<String, Object>();

		Vector<Object> kmXmList = service.serv_infoInpQuer1(xn, xq, xh, kmdm);

		String hddm = (String) kmXmList.get(kmXmList.size() - 1);
		kmXmList.remove(kmXmList.size()-1);
		request.setAttribute("hddm",hddm);
		// Vector<Object> kmXmList = service.serv_infoInpQuer(xn, xq, xh);
		// ==============end luojw 2009/6/9 ===================
		map = service.getXsInfo(xh);				
		request.setAttribute("rs",map);
	    if(tzKmList!=null){
    		for(int i=0;i<tzKmList.size();i++){
    			kmdmStr +=tzKmList.get(i).get("kmdm").toString()+"/";
    			kmmcStr +=tzKmList.get(i).get("kmm").toString()+"/";
    		}
    	}

    	request.setAttribute("kmdmstr", kmdmStr);
    	request.setAttribute("kmmcstr", kmmcStr);
		//request.setAttribute("rs1", service.getLimXnXq());
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());		
    	request.setAttribute("kmXmList",kmXmList);
    	request.setAttribute("userType", userType);
    	return mapping.findForward("infoAdd");
    }
    public ActionForward infoModi(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException{
    	String pkValue = request.getParameter("pkValue");
    	String pk      = "xn||xq||xh";
    	SztzZgkydxService service  = new SztzZgkydxService();	     
    	HashMap<String,String> xsmap = service.getTzXsInfo("view_zgkd_grsztzxx",pk,pkValue);	    
	    Vector<Object> kmXmList = service.serv_infoInpQuer1(pkValue);	
    	List<HashMap<String, String>> tzKmList = service.getTzKm();
    	String kmmcStr = "";
    	String kmdmStr = "";
	    request.setAttribute("rsxs",xsmap);
	    // ==============begin luojw 2009/6/9 ===================
//		String kmdm = request.getParameter("kmdm");

		String hddm = (String) kmXmList.get(kmXmList.size() - 1);
		kmXmList.remove(kmXmList.size()-1);
		request.setAttribute("hddm",hddm);
		// ==============end luojw 2009/6/9 ===================
	    if(tzKmList!=null){
    		for(int i=0;i<tzKmList.size();i++){
    			kmdmStr +=tzKmList.get(i).get("kmdm").toString()+"/";
    			kmmcStr +=tzKmList.get(i).get("kmm").toString()+"/";
    		}
    	}
	    request.setAttribute("kmXmList",kmXmList);
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());
    	request.setAttribute("kmdmstr", kmdmStr);
    	request.setAttribute("kmmcstr", kmmcStr);
    	request.setAttribute("xn",xsmap.get("xn"));
    	request.setAttribute("xq",xsmap.get("xq"));
    	return mapping.findForward("infoModi");
    }
    public ActionForward infoDel(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
    	String pkValue = request.getParameter("delPk");
    	String pk      = "xn||xq||xh";
    	SztzZgkydxService service  = new SztzZgkydxService();
    	service.serv_infoDel(pk, pkValue);   	    	    	
    	return new ActionForward("/zgkydx_sztz.do?method=infoManage&go=go",false);
    }
    public ActionForward infoCheckManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException, IllegalAccessException, InvocationTargetException{
    	SztzZgkydxActionForm myForm  = (SztzZgkydxActionForm)form;
    	SztzZgkydxService service  = new SztzZgkydxService();
    	String userType = request.getSession().getAttribute("userType").toString();
    	String userName = request.getSession().getAttribute("userName").toString();
    	String userDep  =  request.getSession().getAttribute("userDep").toString();
    	String userOnline = request.getSession().getAttribute("userOnLine").toString();
   	    if ("stu".equalsIgnoreCase(userType)){ 		
			request.setAttribute("errMsg", "ѧ����Ȩ���ʴ�ҳ�棡");
			return new ActionForward("/errMsg.do", false); 
   	    }
    	
    	HashMap<String,String> mapXnXq = service.getLimXnXq();
    	myForm.setXn(mapXnXq.get("xn"));
    	myForm.setXq(mapXnXq.get("xq"));
    	
    	String xh = DealString.toGBK(myForm.getXh());
    	String xm = DealString.toGBK(myForm.getXm());
    	myForm.setXh(xh);
    	myForm.setXm(xm);
    	String xydm = myForm.getXydm();
    	String zydm = myForm.getZydm();
//    	String bjdm = myForm.getBjdm();
        String nj   = myForm.getNj();
        if("xy".equalsIgnoreCase(userType)){
    		myForm.setXydm(userDep);
    	}
        if("go".equalsIgnoreCase(request.getParameter("go"))){
        	TzInfoCxModel model = new TzInfoCxModel();
        	BeanUtils.copyProperties(model, myForm);   
			ArrayList<HashMap<String, String>> topTr = service.serv_infoChkTitle();
			ArrayList<String[]> rs    = service.serv_infoChkQuerry(model, userType);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
    	}              
        xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
		String bjKey = xydm + "!!" + zydm + "!!" + nj;

    	request.setAttribute("xnList",Base.getXnndList());
    	request.setAttribute("xqList", Base.getXqList());
    	request.setAttribute("njList",Base.getNjList());
    	request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		//��дȨ�ж�		 			
		request.setAttribute("writeAble", (Base.chkUPower(userName,"sztzInfoCheck.do", userOnline.equalsIgnoreCase("student"))==1)?"yes":"no");
    	return mapping.findForward("infoCheckManage");
    }
    public ActionForward infoChkView(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
	    String pkValue = request.getParameter("pkValue");
	    String xxdm = Base.xxdm;
	    SztzZgkydxService service  = new SztzZgkydxService();
	    HashMap<String,String> xsmap = new HashMap<String, String>();   
	    xsmap = service.getTzXsInfo("view_zgkd_grsztzxx","xn||xq||xh",pkValue);
	    //xsmap = service.getTzXsInfo("view_zgkd_grsztzxx","xn||xq||xh",pkValue);
	    request.setAttribute("rsxs", xsmap);
	    request.setAttribute("zsbh", "32"+xxdm+xsmap.get("xh"));
	    request.setAttribute("xxmc", Base.xxmc);
	    Vector<Object> kmXmList = service.serv_infoInpQuer(xsmap.get("xn"), xsmap.get("xq"), xsmap.get("xh"));
	    HashMap<String,String> map = new HashMap<String, String>();			
				
	    request.setAttribute("pkValue",pkValue);		
		request.setAttribute("rs",map);		
//		request.setAttribute("xnList",Base.getXnndList());
//		request.setAttribute("xqList",Base.getXqList());
    	request.setAttribute("kmXmList",kmXmList);
		request.setAttribute("view",request.getParameter("view"));
		request.setAttribute("select",request.getParameter("select"));
		request.setAttribute("sel",request.getParameter("sel"));
    	return mapping.findForward("infoChkView");
    }
    public ActionForward infoChk(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException{
		String pkValue = request.getParameter("pkVStr");
		String check   = request.getParameter("check");
		String userType = request.getSession().getAttribute("userType").toString();
		SztzZgkydxService service  = new SztzZgkydxService();
		service.serv_infoChk(pkValue, userType, check);
		return new ActionForward("/zgkydx_sztz.do?method=infoCheckManage&go=go",false);
    }
    public ActionForward infoPrintManage(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws SQLException, IllegalAccessException, InvocationTargetException{
    	SztzZgkydxActionForm myForm  = (SztzZgkydxActionForm)form;
    	SztzZgkydxService service  = new SztzZgkydxService();
    	String userType = request.getSession().getAttribute("userType").toString();
//    	String userName = request.getSession().getAttribute("userName").toString();
    	String userDep  =  request.getSession().getAttribute("userDep").toString();
    	HashMap<String,String> mapXnXq = service.getLimXnXq();
    	String xn = myForm.getXn();
    	String xq = myForm.getXq();
    	if(xn==null){
    		myForm.setXn(mapXnXq.get("xn"));
    	}
    	if(xq==null){
    		myForm.setXq(mapXnXq.get("xq"));
    	}
    	if("xy".equalsIgnoreCase(userType)){
    		myForm.setXydm(userDep);
    	}
    	String xh = DealString.toGBK(myForm.getXh());
    	String xm = DealString.toGBK(myForm.getXm());
    	myForm.setXh(xh);
    	myForm.setXm(xm);
    	String xydm = myForm.getXydm();
    	String zydm = myForm.getZydm();   	
        String nj   = myForm.getNj();
    	if("go".equalsIgnoreCase(request.getParameter("go"))){
        	TzInfoCxModel model = new TzInfoCxModel();
        	BeanUtils.copyProperties(model, myForm);   
			ArrayList<HashMap<String, String>> topTr = service.serv_infoPrintTitle();
			ArrayList<String[]> rs    = service.serv_infoPrintQuerry(model);
			request.setAttribute("topTr",topTr);
			request.setAttribute("rs", rs);
			request.setAttribute("rsNum", rs != null ? rs.size():0);
    	}

		xydm = xydm == null ? "" : xydm;
		zydm = zydm == null ? "" : zydm;
		nj = nj == null ? "" : nj;
		String bjKey = xydm + "!!" + zydm + "!!" + nj;

    	request.setAttribute("xnList",Base.getXnndList());
    	request.setAttribute("userType", userType);
    	request.setAttribute("xqList", Base.getXqList());
    	request.setAttribute("njList",Base.getNjList());
    	request.setAttribute("xyList",Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xydm));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
    	return mapping.findForward("infoPrintManage");
    }
    public ActionForward infoPrint(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
    	 String pkValue = request.getParameter("pkValue");
 	    String xxdm = Base.xxdm;
 	    SztzZgkydxService service  = new SztzZgkydxService();
 	    HashMap<String,String> xsmap = new HashMap<String, String>();   
 	    xsmap = service.getTzXsInfo("view_zgkd_grsztzxx","xn||xq||xh",pkValue);
 	    request.setAttribute("rsxs", xsmap);
 	    request.setAttribute("zsbh", "32"+xxdm+xsmap.get("xh"));
 	    request.setAttribute("xxmc", Base.xxmc);
 	    Vector<Object> kmXmList = service.serv_infoInpQuer(xsmap.get("xn"), xsmap.get("xq"), xsmap.get("xh"));
 	    HashMap<String,String> map = new HashMap<String, String>();			
		
 	    request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("kmXmList", kmXmList);
		request.setAttribute("bmh", xsmap.get("xh") + xsmap.get("xn")+xsmap.get("xq"));
    	return mapping.findForward("infoPrint");
    }
    
	public ActionForward sztzInfoSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SztzZgkydxActionForm myForm =(SztzZgkydxActionForm)form;
		String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String kmdmStr = "";
		String kmmcStr = "";
		
    	String userName = request.getSession().getAttribute("userName").toString();
    	//String userType = request.getSession().getAttribute("userType").toString();
    	String kmdm = request.getParameter("kmdm");
		//String allPk = DealString.toGBK(request.getParameter("allPk"));
    	String allPk = DealString.toGBK(myForm.getPkV());
		String allhdnrdm = DealString.toGBK(request.getParameter("allhdnrdm"));
		
		SztzZgkydxService service = new SztzZgkydxService();
		List<HashMap<String, String>> tzKmList = service.getTzKm();
		
		String done = "no";
		
		if (allPk.length() != 0) {
			String[] pk = allPk.split("!!SplitSignTwo!!");
			String[] hdnrdm = allhdnrdm.split("!!SplitSignTrd!!");
			boolean inserted = service.addSztzInfo(pk, hdnrdm, xh, xn, xq);
			if(inserted){
				done="yes";
			}
		}
			
    	if(tzKmList!=null){
    		for(int i=0;i<tzKmList.size();i++){
    			kmdmStr +=tzKmList.get(i).get("kmdm").toString()+"/";
    			kmmcStr +=tzKmList.get(i).get("kmm").toString()+"/";
    		}
    	}
    	
    	Vector<Object> kmXmList = service.serv_infoInpQuer1(xn, xq, userName,kmdm);
		
		String hddm =(String)kmXmList.get(kmXmList.size()-1);

		kmXmList.remove(kmXmList.size()-1);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map = service.getXsInfo(userName);
		kmXmList.remove(kmXmList.size() - 1);
		
		request.setAttribute("done",done);
		request.setAttribute("hddm",hddm);
		request.setAttribute("rs",map);
		request.setAttribute("rs1", service.getLimXnXq());
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());		
    	request.setAttribute("kmXmList",kmXmList);
		request.setAttribute("hddm",hddm);
    	request.setAttribute("kmdmstr", kmdmStr);
    	request.setAttribute("kmmcstr", kmmcStr);
    	request.setAttribute("xn",xn);
    	request.setAttribute("xq",xq);
    	
    	if("add".equalsIgnoreCase(doType)){
    		return mapping.findForward("sztzInfoAdd");
    	}
		return mapping.findForward("sztzInfoSave");
	}
	
	public ActionForward sztzInfoEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		SztzZgkydxActionForm myForm =(SztzZgkydxActionForm)form;
		
		//String doType = request.getParameter("doType");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String xq = request.getParameter("xq");
		String kmdmStr = "";
		String kmmcStr = "";
    	String userName = request.getSession().getAttribute("userName").toString();
    	//String userType = request.getSession().getAttribute("userType").toString();
    	String kmdm = request.getParameter("kmdm");
//		String allPk = DealString.toGBK(request.getParameter("allPk"));
    	String allPk = DealString.toGBK(myForm.getPkV());
		String allhdnrdm = DealString.toGBK(request.getParameter("allhdnrdm"));
		String alldelHddm = DealString.toGBK(request.getParameter("delhddm"));
		SztzZgkydxService service = new SztzZgkydxService();
		List<HashMap<String, String>> tzKmList = service.getTzKm();
		String done = "no";
		if (allhdnrdm.length() != 0 || alldelHddm.length() != 0) {
			String[] pk = allPk.split("!!SplitSignTwo!!");
			String[] hdnrdm = allhdnrdm.split("!!SplitSignTrd!!");
			String[] delHddm = alldelHddm.split("!!SplitSignTrd!!");
			boolean inserted = service.editSztzInfo(pk, hdnrdm, delHddm, xh,
					xn, xq);
			if (inserted) {
				done = "yes";
			}
		}
    	if(tzKmList!=null){
    		for(int i=0;i<tzKmList.size();i++){
    			kmdmStr +=tzKmList.get(i).get("kmdm").toString()+"/";
    			kmmcStr +=tzKmList.get(i).get("kmm").toString()+"/";
    		}
    	}
    	Vector<Object> kmXmList = service.serv_infoInpQuer1(xn, xq, userName,kmdm);
		String hddm =(String)kmXmList.get(kmXmList.size()-1);
		kmXmList.remove(kmXmList.size()-1);
		HashMap<String, String> map = new HashMap<String, String>();
		map = service.getXsInfo(userName);
		kmXmList.remove(kmXmList.size() - 1);
		String pkValue = request.getParameter("pkValue");
    	String pk      = "xn||xq||xh";     
    	HashMap<String,String> xsmap = service.getTzXsInfo("view_zgkd_grsztzxx",pk,pkValue);	    
	    request.setAttribute("rsxs",xsmap);
		request.setAttribute("done",done);
		request.setAttribute("hddm",hddm);
		request.setAttribute("rs",map);
		request.setAttribute("rs1", service.getLimXnXq());
		request.setAttribute("xnList",Base.getXnndList());
		request.setAttribute("xqList",Base.getXqList());		
    	request.setAttribute("kmXmList",kmXmList);
		request.setAttribute("hddm",hddm);
    	request.setAttribute("kmdmstr", kmdmStr);
    	request.setAttribute("kmmcstr", kmmcStr);
    	request.setAttribute("xn",xn);
    	request.setAttribute("xq",xq);
		return mapping.findForward("sztzInfoModi");
	}
}
