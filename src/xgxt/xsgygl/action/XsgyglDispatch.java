
package xgxt.xsgygl.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import xgxt.DAO.DAO;
import xgxt.DAO.GetDataInfo;
import xgxt.DAO.GetDropDownList;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.shgz.model.common.CommonModel;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.xsgygl.hhgxy.GyglService;
import xgxt.xsgygl.util.XsgyglUtil;

public class XsgyglDispatch extends DispatchAction {	
	String userType = "";
	boolean isStu = true;
	String sUName = "";
	String power = "";
//	private int p = -1;
		@Override
	public final ActionForward execute(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
//		// TODO �Զ����ɷ������
		HttpSession session = request.getSession();
//		/**�ж��û���дȨ*/
//		writeAble = Base.getWriteAble(request);
//		String dxq = request.getParameter("writeAble");
//		if(!"".equalsIgnoreCase(dxq) && dxq != null){
//			writeAble = dxq;
//		}
		/** ���߼�� */
		int i = Base.chkTimeOut(session);
		if (i <= 2) {
			request.setAttribute("errMsg", "��½��ʱ�������µ�½��");
			return new ActionForward("/login.jsp", false);
		} 
//		request.setAttribute("writeAble", writeAble);			
		userType = request.getSession().getAttribute("userType").toString();   //�õ��û�����
		isStu = (userType.equalsIgnoreCase("stu"));				 //�ж��Ƿ���ѧ���û���¼
		sUName = request.getSession().getAttribute("userName").toString();	//�õ���¼�û���
		return super.execute(mapping, form, request, response);
	}
	/**��Ԣά������*/
	public ActionForward gywxSq(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.utilGywxsq(request, form);		
		return mapping.findForward("gywxSq");
	}
	/**��Ԣά����������Ϣ��ѯ*/
	public ActionForward gywxSqShXx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
		    return new ActionForward("/errMsg.do", false);
		}  
		myUtil.gywxSqShXx(request, form);
		return mapping.findForward("gywxSqShXx");
	}
	/**��Ԣά���������*/
	public ActionForward gywxSqSh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.gywxSqSh(request, form);
		power = "XsgyglDispatch.do?method=gywxSqShXx";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**�ж��û���дȨ*/
		return mapping.findForward("gywxSqSh");
	}
	/**ס���춯����*/
	public ActionForward ssYdSq(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.ssYdSq(request, form);
		return mapping.findForward("ssYdSq");
	}
	/**ס���춯���������Ϣ��ѯ*/
	public ActionForward ssYdSqShXx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "��Ȩ���ʸ�ҳ�棡");
		    return new ActionForward("/errMsg.do", false);
		}else{
		   myUtil.ssYdSqShxx(request, form,userType);		
		}
		return mapping.findForward("ssYdSqShXx");
	}
	/**ס���춯�������*/
	public ActionForward ssYdSqSh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		//Ȩ�޶�ȡ��ʼ
		power = "XsgyglDispatch.do?method=ssYdSqShXx";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**�ж��û���дȨ*/
		//Ȩ�޶�ȡ����
		myUtil.ssYdSqSh(request,form,userType);			
		return mapping.findForward("ssYdSqSh");
	}
	/**������У����*/
	public ActionForward jqLxSq(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//XsgyglForm bean = (XsgyglForm)form;
		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("xxdm", xxdm);
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.jqLxSq(request, form);
		return mapping.findForward("jqLxSq");
	}
	/**������У���������Ϣ��ѯ*/
	public ActionForward jqLxSqShXx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
		    return new ActionForward("/errMsg.do", false);
		} 
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.jqLxSqShXx(request, form);
		return mapping.findForward("jqLxSqShXx");
	}
	public ActionForward jqLxCwFp(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
		    return new ActionForward("/errMsg.do", false);
		} 
		DAO dao = DAO.getInstance();
		//String userOnline = request.getSession().getAttribute("userOnLine").toString();
		boolean res = dao.createCwxx();//��֤��λ��Ϣ�Ƿ���ȷ
		if (res == false){
			request.setAttribute("errINFO","���´�λ����ʱ���ִ���");
		}
		String xn = Base.currXn;
		String xq = Base.getDqxqmc();
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		XsgyglForm dataSearchForm = (XsgyglForm) form;
		GetDataInfo getDataIF   = new GetDataInfo();
		@SuppressWarnings("unused")
		GetDropDownList getData = new GetDropDownList();
		String xxdm =dao.getXxdm();
		String query = "";		
		String newMappingItems = DealString.toGBK(dataSearchForm.getConditionSqlValue());
		String oldMappingItems = DealString.toGBK(dataSearchForm.getOldCondiSqlValue());
		String doType = request.getParameter("doType");
		String xydm = dataSearchForm.getXydm();
		String zydm = dataSearchForm.getZydm();
//		String bjdm = dataSearchForm.getBjdm();
		String xqdm = dataSearchForm.getXqdm();
		String lddm = dataSearchForm.getLddm();		
		String nj = dataSearchForm.getNj();
		String cs = dataSearchForm.getCs();
		String xb = DealString.toGBK(dataSearchForm.getXb());
		dataSearchForm.setXb(xb);
//		String cwfp = DealString.toGBK(request.getParameter("cwfp"));
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
//		String userName = request.getSession().getAttribute("userName").toString();
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
			dataSearchForm.setXydm(xydm);
		}
				
		if (doType != null && doType.equals("save")) {
			boolean doFlag    = false;
			String[] inserSql  = null;
			String[] updateSql = null;
			if(((oldMappingItems != null) && !oldMappingItems.equals(""))){
				//ɾ�����ƴ��
				String delItems1[]     = oldMappingItems.split(",");
				int delNum             =  delItems1.length;
				String delItems2[][]   = new String[delNum][];
				updateSql                 = new String[delNum];
				for (int i = 0; i < delNum; i++) {
					delItems2[i] = delItems1[i].split("/");
					updateSql[i]    = "update gygl_jqlxxxb set ssbh='',cwh='' where xh='" + delItems2[i][0] +"'"; 
					doFlag = dao.runUpdateTab(updateSql[i]);
				}
			}
			if ((newMappingItems != null) && !newMappingItems.equals("")) {	
				//�������ƴ��
				String inserItems1[] = newMappingItems.split(",");
				int num = inserItems1.length;
				String inserItems2[][] = new String[num][];
				inserSql                 = new String[num];
				for(int i=0;i<num;i++){
					inserItems2[i] = inserItems1[i].split("/");
					inserSql[i]  = "update gygl_jqlxxxb set ssbh='" +inserItems2[i][1]+"' ,cwh='" +inserItems2[i][2]+"' where xh='" + inserItems2[i][0] +"'";
					doFlag = dao.runUpdateTab(inserSql[i]);
				}
			}
			if (doFlag == true) {
				request.setAttribute("doFlag", "ok");
				oldMappingItems = newMappingItems;
			} else {
				request.setAttribute("doFlag", "no");
			}
		}
		
		String[] tmp = getDataIF.getWfpcwzsData(xn, xq, nj, xydm);//δ��������(��)
		if(tmp!=null){
			request.setAttribute("total", tmp[0]);
			request.setAttribute("boy", tmp[1]);
			request.setAttribute("girl", tmp[2]);
		}else{
			request.setAttribute("total", "0");
			request.setAttribute("boy", "0");
			request.setAttribute("girl", "0");
		}
		//String[] tmpT = getDataIF.getCwYfpZsData(nj, xydm,bjdm);//�ѷ�������(��)
		String[] tmpT = getDataIF.getYfpcwzsData(xn, xq, nj, xydm);//�ѷ�������(��)
		if(tmpT!=null){
			request.setAttribute("totalBed", tmpT[0]);
			request.setAttribute("boyBed", tmpT[1]);
			request.setAttribute("girlBed", tmpT[2]);					
		}else{
			request.setAttribute("totalBed", "0");
			request.setAttribute("boyBed", "0");
			request.setAttribute("girlBed", "0");			
		}
		request.setAttribute("oldMappingItems", oldMappingItems);
		query=(Base.isNull(xydm))?"%":"%"+xydm+"%";
		query+="!!-!!";
		query+=(Base.isNull(zydm))?"%":"%"+zydm+"%";
		query+="!!-!!";
		query+=(Base.isNull(nj))?"%":"%"+nj+"%";
		query+="!!-!!";		
		//request.setAttribute("bjList", getData.getBjList(query, userName,Fdypd.isFdy(userName)+""));//�༶�б�
		request.setAttribute("njList",Base.getNjList());// �꼶�б�
		request.setAttribute("xyList",Base.getXyList());//ѧԺ�б�       
		request.setAttribute("xiaoquList", dao.getXiaoQuList());//У���б�
		request.setAttribute("ldList", getDataIF.getLdList(xqdm, xb));//¥���б�
		request.setAttribute("csList", getDataIF.getLcList(lddm));//�����б�
		request.setAttribute("ssxxList",getDataIF.getWfpcwxxList(xqdm, lddm, xb, cs));//���ᴲλ�б�
		request.setAttribute("yfpCwList",getDataIF.getYfpqkxxList(xn, xq, nj, xydm, xb));//�ѷ���ѧ����λ�б�
		request.setAttribute("xsList",getDataIF.getWfpxsxxList(xn, xq, nj, xydm, xb));//δ����ѧ���б�
		request.setAttribute("xxdm",xxdm);
		request.setAttribute("userType", userType);	
		return mapping.findForward("jqLxCwFp");
	}
	/**������У�������*/
	public ActionForward jqLxSqSh(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.jqLxSqSh(request, form);
		power = "XsgyglDispatch.do?method=jqLxSqShXx";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**�ж��û���дȨ*/
		return mapping.findForward("jqLxSqSh");
	}
	/**��������ѯ*/
	public ActionForward sqJgCx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.sqJgCx(request, form);
		return mapping.findForward("sqJgCx");
	}
	/**ѧ�����*/
	public ActionForward xsYjx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		if(isStu){//ѧ���û�����
			myUtil.pubYj(request,form,sUName);
			return mapping.findForward("xsYjx_Stu");	
		}else{//��ʦ�û�����
			power = "XsgyglDispatch.do?method=xsYjx";
			request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**�ж��û���дȨ*/
			myUtil.YjXxQur(request,form,sUName);
			return mapping.findForward("xsYjx_Tch");	
		}	       
	}
	/**ѧ��������ݲ鿴*/
	public ActionForward viewYjXx(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.viewYjXx(request, form,sUName);
		return mapping.findForward("viewYjXx");
	}
	/**ѧ������ظ�*/
	public ActionForward yjHf(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.yjHf(request, form,sUName);
		return mapping.findForward("yjHf");
	}
	/**ѧ��ס����ʷ���ݲ�ѯ*/
	public ActionForward xsGyGL_LsSj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
		    return new ActionForward("/errMsg.do", false);
		}else if(userType.equalsIgnoreCase("xy")){
			XsgyglForm xsgyForm = (XsgyglForm)form;
			xsgyForm.setXydm((String)request.getSession().getAttribute("userDep"));
		}
		
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.xsGyGL_LsSj(request, form);
		return mapping.findForward("xsGyGL_LsSjQur");
	}
	/**����ѧ��ס����ʷ���ݲ鿴*/
	public ActionForward viewGyLsSj(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.viewGyLsSj(request, form);
		return mapping.findForward("viewGyLsSj");
	}
	/**��������Ժ��Դ�ⱨ��ͳ��*/
	public ActionForward fykBbtj(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.fykBbtj(request, form,response);
		return mapping.findForward("success");
	}
	/**������У������*/
	public ActionForward jQlXsPb(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.jQlXsPb(request, form);
		return mapping.findForward("jqlxspb");
	}
	/**������У������*/
	public ActionForward sSyDsPb(ActionMapping mapping, ActionForm form, 
	HttpServletRequest request, HttpServletResponse response) throws Exception {
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.sSyDsPb(request, form);
		return mapping.findForward("ssydspb");
	}
	/**ѧ��ס����Ϣ����ɾ��
	 * @throws Exception */
	public ActionForward xsZsXxPlDelete(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.plDelete(request);
		return new ActionForward("/dorm_Using_Search.do?go=go&writeAble=yes", false);
	}
	/**ѧ��ס����Ϣ����ת�Ƶ�ס����ʷ��Ϣ��
	 * @throws Exception */
	public ActionForward xsZsXxToHis(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		myUtil.xsZsXxToHis(request);
		return new ActionForward("/dorm_Using_Search.do?go=go&writeAble=yes", false);
	}
	
	/**��ȡ����ס����Ϣ��ѧ��ס����Ϣ����
	 * @throws Exception */
	public ActionForward xssjTb(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		Boolean res = myUtil.xssjTb();
		if (res) {
			request.setAttribute("initOK", "ok");
		} else {
			request.setAttribute("initOK", "no");
		}
		return new ActionForward("/dorm_Using_Search.do?go=go&writeAble=yes", false);
	}
	
	/**ѧ���߶�����ס������Ĭ��ҳ��*/
	public ActionForward xsZdSq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		XsgyglForm xsgyForm = (XsgyglForm)form;
		String xn = xsgyForm.getXn();
		String xq = xsgyForm.getXq();
		HashMap<String,String> map = new HashMap<String,String>();
		XsgyglUtil myUtil = new XsgyglUtil(request,form);
		map = myUtil.getUserInfo(userType,sUName);//��ȡ�û������Ϣ
		//Ĭ��ѧ��ѧ��
		if(Base.isNull(xn)){			
			xn = Base.currXn;
			xsgyForm.setXn(xn);
			map.put("xn",xn);
		}
		if(Base.isNull(xq)){
			xq = Base.currXq;
			xsgyForm.setXq(xq);
			map.put("xq",xq);
		}		
		
		String wzksrq = request.getParameter("wzksrq");
    	String jhwzsj = DealString.toGBK(request.getParameter("jhwzsj"));
    	String wzlxdm = DealString.toGBK(request.getParameter("wzlxdm"));
    	String wzdz   = DealString.toGBK(request.getParameter("wzdz"));
    	String wzyy   = DealString.toGBK(request.getParameter("wzyy"));
    	String jzsfty = DealString.toGBK(request.getParameter("jzsfty"));
    	String sqxn = DealString.toGBK(request.getParameter("xn"));
    	xn=Base.isNull(sqxn)?xn:sqxn;
    	
    	map.put("wzksrq", wzksrq);
    	map.put("jhwzsj", jhwzsj);
    	map.put("wzlxdm", wzlxdm);
    	map.put("wzksrq", wzksrq);
    	map.put("wzdz", wzdz);
    	map.put("wzyy", wzyy);
    	map.put("jzsfty", jzsfty);
    	map.put("xn", xn);
    	
		request.setAttribute("rs",map);
	    request.setAttribute("xnList",Base.getXnndList());//ѧ���б�
	    request.setAttribute("xqList",Base.getXqList());//ѧ���б�
	    request.setAttribute("userType",userType); //�û�����
	    request.setAttribute("wzlxList", gyglDao.getWzlxList());//��ס�����б�
		return mapping.findForward("xszdsq");
	}
	/**ѧ���߶�����ס��������Ϣ����
	 * @throws Exception */
	public ActionForward xsZdSqSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		XsgyglForm xsgyForm = (XsgyglForm)form;
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
		boolean result     = myUtil.xsZdSqSave(request);
		request.setAttribute("result", result);
		return xsZdSq(mapping, form, request, response);//new ActionForward("/XsgyglDispatch.do?method=xsZdSq",false);
	}
	/**ѧ���߶�(��ס)������˲�ѯ*/
	public ActionForward xsZdSqShXx(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		if(userType.equalsIgnoreCase("stu")){//ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ����Ȩ���ʸ�ҳ�棡");
		    return new ActionForward("/errMsg.do", false);
		} 
		XsgyglForm xsgyForm = (XsgyglForm)form;
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
		myUtil.xsZdSqShXx(request,xsgyForm);
		return mapping.findForward("xszdsqshxx");
	}
	/**ѧ���߶�(��ס)�������
	 * @throws Exception */
	public ActionForward xsZdSqSh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		XsgyglForm xsgyForm = (XsgyglForm)form;
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
	    myUtil.xsZdSqSh(request);	
	    //�ж�Ȩ�޿�ʼ
		power = "XsgyglDispatch.do?method=xsZdSqShXx";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**�ж��û���дȨ*/
		//�ж�Ȩ�޽���
		return mapping.findForward("xszdsqsh");
	}
	
	/**������У��Ϣ��ӡ
	 * @throws Exception */
	public ActionForward jqlxPrint(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		XsgyglForm xsgyForm = (XsgyglForm)form;
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
	    myUtil.jqlxPrint(request);	
	    //�ж�Ȩ�޿�ʼ
		power = "XsgyglDispatch.do?method=jqlxPrint";
		request.setAttribute("writeAble",(Base.chkUPower(sUName, power, isStu) == 1) ? "yes" : "no");///**�ж��û���дȨ*/
		//�ж�Ȩ�޽���
		return mapping.findForward("jqlxPrint");
	}
	/**ά����Ա��ǲ��*/
	public ActionForward toWxRypq(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		XsgyglForm xsgyForm = (XsgyglForm)form;
		String pkValue = request.getParameter("pkValue");
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
		request.setAttribute("rs",myUtil.getWxDxx(pkValue));
		request.setAttribute("xxmc",Base.xxmc);
		return mapping.findForward("toWxRypq");
	}
	/**�꼶��סʱ��*/
	public ActionForward setNjrzsj(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		
		XsgyglForm xsgyForm = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String rzsj = request.getParameter("rzsj");
		XsgyglUtil myUtil   = new XsgyglUtil(request,xsgyForm);
		if("save".equals(doType)){
			request.setAttribute("result",
					myUtil.saveNjrzsj(xsgyForm.getNj(), rzsj));
		}
		if(!Base.isNull(xsgyForm.getNj())){
			ArrayList<String[]> list = myUtil.getNjrzsj(xsgyForm.getNj());
			if(list !=null && list.size() >0){
				request.setAttribute("rzsj",list.get(0)[1]);
			}else{
				request.setAttribute("rzsj","");
			}
		}else{
			request.setAttribute("rzsj","");
		}
		request.setAttribute("rs",myUtil.getNjrzsj(""));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("rsNum",
				myUtil.getNjrzsj("") !=null?myUtil.getNjrzsj("").size():0);
		return mapping.findForward("njrzsj");
	}
	
	/**
	 * ��ʾ������������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * */
	public ActionForward qspjsq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response){
		
		return mapping.findForward("qspjsqDispatch");
	}
	
	/**
	 * �¶����������������
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward ydwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){//����������Ϣ
			model.setSsbh(StringUtils.joinStr(model.getLddm(), "-", model.getQsh()));
			boolean result = service.saveYdwmqssqb(model,request);
			request.setAttribute("result", result);
		}else{
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			model.setSqr(session.getAttribute("userName").toString());
		}
		
		setXnndList(request);
		setGyxxList(request);//��Ԣ��Ϣ�������б�
		request.setAttribute("yfList", service.getYdwmqssqYfList());
		return mapping.findForward("ydwmqssq");
	}
	
	/**
	 * �¶������������������Ϣ�޸�
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward modiYdwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		if("save".equalsIgnoreCase(doType)){
			//����������Ϣ
			model.setSqr(userName);
			boolean result = service.saveYdwmqssqb(model,request);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//��ȡ����
		request.setAttribute("rs", service.queryYdwmqssqb(model, userType, userName));
		return mapping.findForward("modiYdwmqssq");
	}
	
	/**
	 * �¶�����������������ѯҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward ydwmqssqSearch(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		XsxxglService xsxxService = new XsxxglService();
		
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		
		if("stu".equalsIgnoreCase(userType)){//ѧ���û�
			model.setSsbh(xsxxService.selectStuinfo(userName).get("ssbh"));
		}
		if("del".equalsIgnoreCase(doType)){//����ɾ��
			boolean result = service.delYdwmqssqb(model);
			request.setAttribute("result", result);
			go = "go";
		}
		if(!Base.isNull(go)){//��ѯ����
			rs = service.queryYdwmqssqxx(model);
			topTr = service.getYdwmqssqToptr();
		}
		
		setXnndList(request);
		setGyxxList(request);//��Ԣ��Ϣ�������б�
		FormModleCommon.commonRequestSet(request, "view_gygl_ydwmqssqb", 
				                         "gygl_ydwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		return mapping.findForward("ydwmqssqSearch");
	}
	
	/**
	 * �¶�����������������ѯ��������
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expYdwmqssqxx(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		XsgyglForm model = (XsgyglForm) form;
		GyglService service = new GyglService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setYf(request.getParameter("yf"));
		model.setLddm(request.getParameter("lddm"));
		model.setQsh(request.getParameter("qsh"));
		model.setSqsjks(request.getParameter("sqsjks"));
		model.setSqsjjs(request.getParameter("sqsjjs"));
		model.setFdysh(request.getParameter("fdysh"));
		model.setXysh(request.getParameter("xysh"));
		model.setXxsh(request.getParameter("xxsh"));
		
		String[] colList = new String[] {"xn","xq","xqmc","yf","ssbh","sqsj",
				                         "sqr","bz","fdysh","fdyshyj","fdyshsj",
				                         "xysh","xyshyj","xyshsj","xxsh","xxshyj",
				                         "xxshsj","lddm","ldmc","cs","qsh"};
		String[] colListCN = service.getColCNList("view_gygl_ydwmqssqb",colList);
		//��ѯ�¶���������������������ҳ��
		List<String[]> rs = service.queryYdwmqssqxxAll(model,colList);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * �¶����������������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward ydwmqssh(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String shjb = "1";//��˼���
		
		model.setShjb(shjb);
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("audi".equalsIgnoreCase(doType)){//�������
			String shjg = request.getParameter("shjg");
			boolean result = service.audiYdwmqssqbBatch(model,shjg,userType,userName);
			request.setAttribute("result", result);
			go = "go";
		}
		
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		if(!Base.isNull(go)){//��ѯ����			
			rs = service.queryYdwmqssqshxx(model,userType,userName);
			topTr = service.getYdwmqssqshToptr(shjb,userType,userName);
		}
		
		setXnndList(request);
		setGyxxList(request);//��Ԣ��Ϣ�������б�
		FormModleCommon.commonRequestSet(request, "view_gygl_ydwmqssqb", 
				                         "gygl_ydwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("yhType", service.getYhlx(userType, userName, shjb));
		return mapping.findForward("ydwmqssqsh");
	}
	
	/**
	 * �¶�������������������
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward audiYdwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String shjb = service.getYdwmqsshjb();//��˼���
		
		if("save".equalsIgnoreCase(doType)){
			//����������Ϣ
			model.setSqr(userName);
			boolean result = service.audiYdwmqssqb(model, request, userType, userName);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//��ȡ����
		request.setAttribute("yhType", service.getYhlx(userType, userName,shjb));//�û�����
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("rs", service.queryYdwmqssqb(model, userType, userName));
		return mapping.findForward("audiYdwmqssq");
	}
	
	/**
	 * ��ʾѧ��������������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xqwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){//����������Ϣ
			model.setSsbh(StringUtils.joinStr(model.getLddm(), "-", model.getQsh()));
			boolean result = service.saveXqwmqssqb(model,request);
			request.setAttribute("result", result);
		}else{
			model.setXn(Base.currXn);
			model.setXq(Base.currXq);
			model.setSqr(session.getAttribute("userName").toString());
		}
		
		setXnndList(request);
		setGyxxList(request);//��Ԣ��Ϣ�������б�
		return mapping.findForward("xqwmqssq");
	}
	
	/**
	 * ѧ����������������Ϣ�޸�
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward modiXqwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		if("save".equalsIgnoreCase(doType)){
			//����������Ϣ
			model.setSqr(userName);
			boolean result = service.saveXqwmqssqb(model,request);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//��ȡ����
		request.setAttribute("rs", service.queryXqwmqssqb(model,userType,userName));
		return mapping.findForward("modiXqwmqssq");
	}
	
	/**
	 * ѧ������������������ѯҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xqwmqssqSearch(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		XsxxglService xsxxService = new XsxxglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		
		if("stu".equalsIgnoreCase(userType)){//ѧ���û�
			model.setSsbh(xsxxService.selectStuinfo(userName).get("ssbh"));
		}
		if("del".equalsIgnoreCase(doType)){//����ɾ��
			boolean result = service.delXqwmqssqb(model);
			request.setAttribute("result", result);
			go = "go";
		}
		if(!Base.isNull(go)){//��ѯ����
			rs = service.queryXqwmqssqxx(model);
			topTr = service.getXqwmqssqToptr();
		}
		
		setXnndList(request);
		setGyxxList(request);//��Ԣ��Ϣ�������б�
		FormModleCommon.commonRequestSet(request, "view_gygl_xqwmqssqb", 
				                         "gygl_xqwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));		
		return mapping.findForward("xqwmqssqSearch");
	}
	
	/**
	 * ѧ������������������ѯ��������
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expXqwmqssqxx(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		XsgyglForm model = (XsgyglForm) form;
		GyglService service = new GyglService();
		model.setXn(request.getParameter("xn"));
		model.setXq(request.getParameter("xq"));
		model.setLddm(request.getParameter("lddm"));
		model.setQsh(request.getParameter("qsh"));
		model.setSqsjks(request.getParameter("sqsjks"));
		model.setSqsjjs(request.getParameter("sqsjjs"));
		model.setFdysh(request.getParameter("fdysh"));
		model.setXysh(request.getParameter("xysh"));
		model.setXxsh(request.getParameter("xxsh"));
		
		String[] colList = new String[] {"xn","xq","xqmc","ssbh","sqsj",
				                         "sqr","bz","fdysh","fdyshyj","fdyshsj",
				                         "xysh","xyshyj","xyshsj","xxsh","xxshyj",
				                         "xxshsj","lddm","ldmc","cs","qsh"};
		String[] colListCN = service.getColCNList("view_gygl_xqwmqssqb",colList);
		//��ѯ�¶���������������������ҳ��
		List<String[]> rs = service.queryXqwmqssqxxAll(model,colList);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * ѧ�����������������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xqwmqssh(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String shjb = service.getXqwmqsshjb();
		
		model.setShjb(shjb);
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("audi".equalsIgnoreCase(doType)){//�������
			String shjg = request.getParameter("shjg");
			boolean result = service.audiXqwmqssqbBatch(model,shjg,userType,userName);
			request.setAttribute("result", result);
			go = "go";
		}
		
		model.setXn(Base.currXn);
		model.setXq(Base.currXq);
		if(!Base.isNull(go)){//��ѯ����			
			rs = service.queryXqwmqssqshxx(model,userType,userName);
			topTr = service.getXqwmqssqshToptr(shjb,userType,userName);
		}
		
		setXnndList(request);
		setGyxxList(request);//��Ԣ��Ϣ�������б�
		FormModleCommon.commonRequestSet(request, "view_gygl_xqwmqssqb", 
				                         "gygl_xqwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("yhType", service.getYhlx(userType, userName,shjb));//�û�����
		return mapping.findForward("xqwmqssqsh");
	}
	
	/**
	 * ѧ�����������������
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward audiXqwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String shjb = service.getXqwmqsshjb();
		
		model.setShjb(shjb);
		if("save".equalsIgnoreCase(doType)){
			//����������Ϣ
			model.setSqr(userName);
			boolean result = service.audiXqwmqssqb(model,request,userType,userName);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//��ȡ����
		request.setAttribute("yhType", service.getYhlx(userType, userName,shjb));//�û�����
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("rs", service.queryXqwmqssqb(model,userType,userName));
		return mapping.findForward("audiXqwmqssq");
	}
	
	/**
	 * ��ʾѧ�����������������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xnwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		
		if("save".equalsIgnoreCase(doType)){//����������Ϣ
			model.setSsbh(StringUtils.joinStr(model.getLddm(), "-", model.getQsh()));
			boolean result = service.saveXnwmqssqb(model,request);
			request.setAttribute("result", result);
		}else{
			model.setXn(Base.currXn);
			model.setSqr(session.getAttribute("userName").toString());
		}
		
		setXnndList(request);
		setGyxxList(request);//��Ԣ��Ϣ�������б�
		return mapping.findForward("xnwmqssq");
	}
	
	/**
	 * ��ʾ����������������ѯҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * */
	public ActionForward qspjsqjgcx(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return mapping.findForward("qspjsqSearchDispatch");
	}
	
	/**
	 * ѧ�������������������Ϣ�޸�
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward modiXnwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		if("save".equalsIgnoreCase(doType)){
			//����������Ϣ
			model.setSqr(userName);
			boolean result = service.saveXnwmqssqb(model,request);
			request.setAttribute("result", result);
		}
		model.setCbv(new String[]{request.getParameter("pk")});//��ȡ����
		request.setAttribute("rs", service.queryXnwmqssqb(model,userType,userName));
		return mapping.findForward("modiXnwmqssq");
	}
	
	/**
	 * ѧ������������������ѯҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xnwmqssqSearch(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		XsxxglService xsxxService = new XsxxglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		
		if("stu".equalsIgnoreCase(userType)){//ѧ���û�
			model.setSsbh(xsxxService.selectStuinfo(userName).get("ssbh"));
		}
		if("del".equalsIgnoreCase(doType)){//����ɾ��
			boolean result = service.delXnwmqssqb(model);
			request.setAttribute("result", result);
			go = "go";
		}
		if(!Base.isNull(go)){//��ѯ����
			rs = service.queryXnwmqssqxx(model);
			topTr = service.getXnwmqssqToptr();
		}
		
		setXnndList(request);
		setGyxxList(request);//��Ԣ��Ϣ�������б�
		FormModleCommon.commonRequestSet(request, "view_gygl_xnwmqssqb", 
				                         "gygl_xnwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		return mapping.findForward("xnwmqssqSearch");
	}
	
	/**
	 * ѧ������������������ѯ��������
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward expXnwmqssqxx(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		XsgyglForm model = (XsgyglForm) form;
		GyglService service = new GyglService();
		model.setXn(request.getParameter("xn"));
		model.setLddm(request.getParameter("lddm"));
		model.setQsh(request.getParameter("qsh"));
		model.setSqsjks(request.getParameter("sqsjks"));
		model.setSqsjjs(request.getParameter("sqsjjs"));
		model.setFdysh(request.getParameter("fdysh"));
		model.setXysh(request.getParameter("xysh"));
		model.setXxsh(request.getParameter("xxsh"));
		
		String[] colList = new String[] {"xn", "ssbh", "sqsj", "sqr", "bz", 
				                         "fdysh", "fdyshyj", "fdyshsj",
				                         "xysh", "xyshyj", "xyshsj", "xxsh",
				                         "xxshyj", "xxshsj", "lddm", "ldmc",
				                         "cs", "qsh"};
		String[] colListCN = service.getColCNList("view_gygl_xnwmqssqb",colList);
		//��ѯ�¶���������������������ҳ��
		List<String[]> rs = service.queryXnwmqssqxxAll(model,colList);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");		
		Excel2Oracle.exportData(rs,colList,colListCN, response.getOutputStream());
		return mapping.findForward("");
	}
	
	/**
	 * ѧ�����������������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward xnwmqssh(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		HttpSession session = request.getSession();
		XsgyglForm model = (XsgyglForm)form;
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		GyglService service = new GyglService();
		String go = request.getParameter("go");
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String shjb = service.getXnwmqsshjb();
		
		model.setShjb(shjb);
		if("xy".equalsIgnoreCase(userType)){
			model.setXydm(userDep);
		}
		if("audi".equalsIgnoreCase(doType)){//�������
			String shjg = request.getParameter("shjg");
			//�жϱ����Ƿ񳬹�
			String scaleFlag = "";
			boolean result = false;
			if("ͨ��".equalsIgnoreCase(shjg)){
				scaleFlag = service.checkWmqsbl(model);
			}
			if(Base.isNull(scaleFlag)){//����δ�����ɼ������
				result = service.audiXnwmqssqbBatch(model,shjg,userType,userName);
			}else{
				request.setAttribute("mes", scaleFlag);
			}
			request.setAttribute("result", result);
			go = "go";
		}
		
		model.setXn(Base.currXn);//ֻ����˵�ǰѧ������
		if(!Base.isNull(go)){//��ѯ����			
			rs = service.queryXnwmqssqshxx(model,userType,userName);
			topTr = service.getXnwmqssqshToptr(shjb,userType,userName);
		}
		
		setXnndList(request);
		setGyxxList(request);//��Ԣ��Ϣ�������б�
		FormModleCommon.commonRequestSet(request, "view_gygl_xnwmqssqb", 
				                         "gygl_xnwmqssqb", rs, topTr);
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("yhType", service.getYhlx(userType, userName, shjb));
		return mapping.findForward("xnwmqssqsh");
	}
	
	/**
	 * ѧ��������������������
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward audiXnwmqssq(ActionMapping mapping,
			ActionForm form,HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		GyglService service = new GyglService();
		XsgyglForm model = (XsgyglForm)form;
		String doType = request.getParameter("doType");
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		String shjb = service.getXnwmqsshjb();
		String yhlx = service.getYhlx(userType, userName, shjb);//�û�����
		
		model.setShjb(shjb);
		model.setCbv(new String[]{request.getParameter("pk")});//��ȡ����
		if("save".equalsIgnoreCase(doType)){
			//����������Ϣ
			model.setSqr(userName);
			//�жϱ����Ƿ񳬹�
			String scaleFlag = "";
			boolean result = false;
			
			//��˽��
			String shjg = "xy".equalsIgnoreCase(yhlx) ? model.getXysh() 
						  :model.getXxsh();
			if("ͨ��".equalsIgnoreCase(shjg)){
				scaleFlag = service.checkWmqsbl(model);
			}
			if(Base.isNull(scaleFlag)){//����δ�����ɼ������
				result = service.audiXnwmqssqb(model,request,userType,userName);
			}else{
				request.setAttribute("mes", scaleFlag);
			}
			request.setAttribute("result", result);
		}
		
		request.setAttribute("yhType", yhlx);//�û�����
		request.setAttribute("chkList", service.getChkList(3));
		request.setAttribute("rs", service.queryXnwmqssqb(model,userType,userName));
		return mapping.findForward("audiXnwmqssq");
	}
	
	/**
	 * ���������������ҳ��
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward qspjsqsh(ActionMapping mapping,
		    ActionForm form,HttpServletRequest request,HttpServletResponse response) 
		    throws Exception{
		return mapping.findForward("qspjsqshDispatch");
	}
		
	/**
	 * ����ѧ������б�����
	 * @param HttpServletRequest request
	 * */
	private void setXnndList(HttpServletRequest request){		
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("yfList", Base.getYfList());
	}
	
	/**
	 * ���ù�Ԣ��Ϣ�б�����
	 * @param HttpServletRequest request
	 * */
	private void setGyxxList(HttpServletRequest request){
		GyglService service = new GyglService();
		request.setAttribute("ldList", service.getList("ld"));//��Ԣ¥��
		request.setAttribute("qshList", service.getList("qsh"));//��Ԣ�޺�
	}
}
