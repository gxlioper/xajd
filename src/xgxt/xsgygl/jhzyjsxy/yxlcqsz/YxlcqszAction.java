/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-15 ����01:47:06</p>
 */
package xgxt.xsgygl.jhzyjsxy.yxlcqsz;


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

import xgxt.DAO.DAO;
import xgxt.DAO.GetDropDownList;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.xsgygl.dao.gyglDao;
import xgxt.xsgygl.jhzyjsxy.GyglJhzyForm;

public class YxlcqszAction extends DispatchAction {
	/*
	 * ������Ŀ������
	 */
	public List<HashMap<String, String>> getCdList() {
		DAO dao = DAO.getInstance();
		String[] listEn = new String[] { "001", "002", "003"};
		String[] listCn = new String[] { "�ټ����ҳ�", "��ʮ�Ѳ㳤", "���¥��"};
		return dao.arrayToList(listEn, listCn);
	}
	/**
	 *����˵�ѡ��
	 */
	public ActionForward yxlcqszSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xmdmV = request.getParameter("xmdm");
		int xmdm = Integer.parseInt(Base.isNull(xmdmV) ? "0" : xmdmV);
		switch (xmdm) {
		case 1: {
			return new ActionForward("/jhzy_yxlcqsz.do?method=bjqszSq",false);
		}
		case 2: {
			return new ActionForward("/jhzy_yxlcqsz.do?method=esjczSq",false);
		}
		case 3: {
			return new ActionForward("/jhzy_yxlcqsz.do?method=wjlzSq",false);
		}		
		}
		request.setAttribute("list", getCdList());
		return mapping.findForward("yxlcqszSq");
	}
	/**
	 *��ѯ�˵�ѡ��
	 */
	public ActionForward yxlcqszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userOnline = request.getSession().getAttribute("userOnLine").toString();
		if("student".equalsIgnoreCase(userOnline)){
			return new ActionForward("/jhzy_yxlcqsz.do?method=yxlcqszXsSqJg",false);
		}
		String xmdmV = request.getParameter("xmdm");
		int xmdm = Integer.parseInt(Base.isNull(xmdmV) ? "0" : xmdmV);
		switch (xmdm) {
		case 1: {
			return new ActionForward("/jhzy_yxlcqsz.do?method=bjqszManage",false);
		}
		case 2: {
			return new ActionForward("/jhzy_yxlcqsz.do?method=esjczManage",false);
		}
		case 3: {
			return new ActionForward("/jhzy_yxlcqsz.do?method=wjlzManage",false);
		}		
		}
		request.setAttribute("list", getCdList());
		return mapping.findForward("yxlcqszManage");
	}	
	/**
	 *��˲˵�ѡ��
	 */
	public ActionForward yxlcqszSh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userType = request.getSession().getAttribute("userType").toString();
		if (userType.equalsIgnoreCase("stu")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		String xmdmV = request.getParameter("xmdm");
		int xmdm = Integer.parseInt(Base.isNull(xmdmV) ? "0" : xmdmV);
		switch (xmdm) {
		case 1: {
			return new ActionForward("/jhzy_yxlcqsz.do?method=bjqszShManage",false);
		}
		case 2: {
			return new ActionForward("/jhzy_yxlcqsz.do?method=esjczShManage",false);
		}
		case 3: {
			return new ActionForward("/jhzy_yxlcqsz.do?method=wjlzShManage",false);
		}		
		}
		request.setAttribute("list", getCdList());
		return mapping.findForward("yxlcqszSh");
	}

	/**
	 * �ټ����ҳ�����
	 * @throws Exception 
	 */
	public ActionForward bjqszSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GetDropDownList getList = new GetDropDownList();
		YxlcqszService service = new YxlcqszService();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String qsh   = request.getParameter("qsh");
		@SuppressWarnings("unused")
		String ssbh="";
		String ldmc="";
		if ("student".equalsIgnoreCase(userOnLine)) {
			xh = userName;
		}
		if (xn == null) {
			xn = Base.currXn;
		}
		myForm.setXn(xn);

		if ("save".equalsIgnoreCase(doType)) {
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_bjqszSqSave(model);
			request.setAttribute("done",done);			
		}
		//����ѧ������Ƿ��ǰټ����ҳ��������ȡ������¥����¥�㣬�������ݣ������������Ƿ������ҳ�����������������ӡ�
		HashMap<String,String> rsBjQsz = new HashMap<String, String>();//���ҳ���Ϣ
		rsBjQsz = service.serv_getBjqszInfo(xh, xn);//�ټ����ҳ���Ϣ
		HashMap<String,String> rsQsz = CommonQueryDAO.getStuZsInfo(xh);
		boolean rsSfQsz = service.lcqszPd("qsz", xh);//�ж��Ƿ������ҳ�
		if(rsBjQsz.get("lddm")!=null){
			lddm = rsBjQsz.get("lddm");
			ldmc = rsBjQsz.get("ldmc");
		}else{
			lddm = rsQsz.get("lddm");
			ldmc = rsQsz.get("ldmc");
		}
		if(rsBjQsz.get("lc")!=null){
			lc = rsBjQsz.get("lc");
		}else{
			lc = rsQsz.get("lc");
		}
		if(rsBjQsz.get("qsh")!=null){
			qsh = rsBjQsz.get("qsh");
		}else{
			qsh = rsQsz.get("qsh");
		}
		if(rsBjQsz.get("ssbh")!=null){
			ssbh=rsBjQsz.get("ssbh");
		}else{
			ssbh=rsQsz.get("ssbh");
		}
		myForm.setLddm(lddm);
		myForm.setLc(lc);
		myForm.setQsh(qsh);
		if(!rsSfQsz){
			request.setAttribute("noPass","noPass");
		}
		request.setAttribute("ldmc",Base.isNull(ldmc)?"":ldmc);
		request.setAttribute("qsh", Base.isNull(qsh)?"":qsh);
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,userName));
		request.setAttribute("qshList", getList.GetQshList2(Base.isNull(lddm)?" ":lddm, Base.isNull(lc)?" ":lc,userName));		
		request.setAttribute("rs",CommonQueryDAO.getStuInfo(xh));//ѧ�������Ϣ		
		request.setAttribute("rsBjQsz",rsBjQsz);//���ҳ���Ϣ
		request.setAttribute("act",act);//���ҳ���Ϣ
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("bjqszSq");
	}
	/**
	 *�ټ����ҳ���ѯ
	 */
	public ActionForward bjqszManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		YxlcqszService service = new YxlcqszService();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String xn   = myForm.getXn();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
//		��Ԣ����Ա���ж�
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}

		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
			myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
			myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
			String yesNo = myForm.getYesNo();
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getBjqszCxTopTr();
			rs = service.serv_getBjqszCxList(yesNo, userType, userName, model);
		}	

		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(lddm,userName));
		request.setAttribute("qshList", getList.GetQshList2(lddm, lc,userName));		
		request.setAttribute("chkList",dao.getChkList(3));			
		request.setAttribute("tableName", "view_bjqszxx");
		request.setAttribute("realTable", "bjqszxxb");
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
		//��Ԣ����Ա����¥�������б�����
		gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);
		//		��дȨ�ж�
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "yxlcqszManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		return mapping.findForward("bjqszManange");
	}

	/**
	 *�ټ����ҳ���˲�ѯ
	 */
	public ActionForward bjqszShManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		YxlcqszService service = new YxlcqszService();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String xn   = myForm.getXn();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
//		��Ԣ����Ա���ж�
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
			myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
			myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
			String yesNo = myForm.getYesNo();
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getBjqszCxTopTr();
			rs = service.serv_getBjqszShCxList(yesNo, userType, userName, model);
		}	

		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(lddm,userName));
		request.setAttribute("qshList", getList.GetQshList2(lddm, lc,userName));		
		request.setAttribute("chkList",dao.getChkList(3));			
		request.setAttribute("tableName", "view_bjqszxx");
		request.setAttribute("realTable", "bjqszxxb");								
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
		//��Ԣ����Ա����¥�������б�����
		gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);
//		��дȨ�ж�
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "yxlcqszSh.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		return mapping.findForward("bjqszShManage");
	}
	/**
	 * ��ʮ�Ѳ㳤����
	 * @throws Exception 
	 */
	public ActionForward esjczSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GetDropDownList getList = new GetDropDownList();
		YxlcqszService service = new YxlcqszService();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String zz   =request.getParameter("zz");
		String ldmc="";
		String qsh="";
		if ("student".equalsIgnoreCase(userOnLine)) {
			xh = userName;
		}
		if (xn == null) {
			xn = Base.currXn;
		}
		myForm.setXn(xn);

		if ("save".equalsIgnoreCase(doType)) {
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_esjczSqSave(model);
			request.setAttribute("done",done);			
		}
		//����ѧ������Ƿ��ǰټ����ҳ��������ȡ������¥����¥�㣬�������ݣ������������Ƿ������ҳ�����������������ӡ�
		HashMap<String,String> rsEsjCz = new HashMap<String, String>();//���ҳ���Ϣ
		rsEsjCz = service.serv_getEsjczInfo(xh, xn);//��ʮ�ѳ���Ϣ		
		HashMap<String,String> rsZs = CommonQueryDAO.getStuZsInfo(xh);
		boolean rsCz = service.lcqszPd("cz", xh);//�ж��Ƿ����β㳤
		if(rsEsjCz.get("lddm")!=null){
			lddm = rsEsjCz.get("lddm");
			ldmc = rsEsjCz.get("ldmc");
			if(!Base.isNull(rsEsjCz.get("zz"))){
				qsh = rsEsjCz.get("zz").replace(ldmc,"");
			}
		}else{
			lddm = rsZs.get("lddm");
			ldmc = rsZs.get("ldmc");
			qsh  = rsZs.get("qsh");
		}
		if(rsEsjCz.get("lc")!=null){
			lc = rsEsjCz.get("lc");
		}else{
			lc = rsZs.get("lc");
		}	
		if(rsEsjCz.get("zz")!=null){			
		}else{			
			zz = Base.isNull(rsZs.get("ldmc"))?"":rsZs.get("ldmc")+ (Base.isNull(rsZs.get("qsh"))?"":rsZs.get("qsh"));
			rsEsjCz.put("zz",zz);
		}
		if(!rsCz){
			request.setAttribute("noPass","noPass");
		}
		myForm.setLddm(lddm);
		myForm.setLc(lc);
		request.setAttribute("ldmc",Base.isNull(ldmc)?"":ldmc);
		request.setAttribute("qsh",Base.isNull(qsh)?"":qsh);
		request.setAttribute("cs",Base.isNull(lc)?"":lc);		
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(Base.isNull(lddm)?" ":lddm,userName));			
		request.setAttribute("rs",CommonQueryDAO.getStuInfo(xh));//ѧ�������Ϣ		
		request.setAttribute("rsEsjCz",rsEsjCz);//�㳤��Ϣ
		request.setAttribute("rsZs",CommonQueryDAO.getStuZsInfo(xh));
		request.setAttribute("act",act);//���ҳ���Ϣ
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("esjczSq");
	}
	/**
	 *��ʮ�Ѳ㳤��ѯ
	 */
	public ActionForward esjczManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		YxlcqszService service = new YxlcqszService();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String xn   = myForm.getXn();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
//		��Ԣ����Ա���ж�
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
			myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
			myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
			String yesNo = myForm.getYesNo();
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getEsjczCxTopTr();
			rs = service.serv_getEsjczCxList(yesNo, userType, userName, model);
		}	

		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(lddm,userName));
//		request.setAttribute("qshList", getList.GetQshList2(lddm, lc));		
		request.setAttribute("chkList",dao.getChkList(3));			
		request.setAttribute("tableName", "view_esjczxx");
		request.setAttribute("realTable", "esjczxxb");
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
		//��Ԣ����Ա����¥�������б�����
		gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);

		//		��дȨ�ж�
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "yxlcqszManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		return mapping.findForward("esjczManange");
	}
	/**
	 *��ʮ�Ѳ㳤��˲�ѯ
	 */
	public ActionForward esjczShManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		YxlcqszService service = new YxlcqszService();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String xn   = myForm.getXn();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
//		��Ԣ����Ա���ж�
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}				
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
			myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
			myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
			String yesNo = myForm.getYesNo();
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getEsjczCxTopTr();
			rs = service.sersv_getEsjczShCxList(yesNo, userType, userName, model);
		}			
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ldList", getList.GetLdList());
		request.setAttribute("lcList", getList.GetLcList(lddm,userName));
//		request.setAttribute("qshList", getList.GetQshList2(lddm, lc));		
		request.setAttribute("chkList",dao.getChkList(3));			
		request.setAttribute("tableName", "view_esjczxx");
		request.setAttribute("realTable", "esjczxxb");
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
		//��Ԣ����Ա����¥�������б�����
		gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);

		//		��дȨ�ж�
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "yxlcqszSh.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		return mapping.findForward("esjczShManage");
	}
	/**
	 * ���¥������
	 * @throws Exception 
	 */
	public ActionForward wjlzSq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		GetDropDownList getList = new GetDropDownList();
		YxlcqszService service = new YxlcqszService();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		String act = request.getParameter("act");
		String xh = request.getParameter("xh");
		String xn = request.getParameter("xn");
		String lddm = request.getParameter("lddm");
		String zz   =request.getParameter("zz");
		String ldmc="";
		String qsh="";
		if ("student".equalsIgnoreCase(userOnLine)) {
			xh = userName;
		}
		if (xn == null) {
			xn = Base.currXn;
		}
		myForm.setXn(xn);

		if ("save".equalsIgnoreCase(doType)) {
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			boolean done = service.serv_wjlzSqSave(model);
			request.setAttribute("done",done);			
		}
		//����ѧ������Ƿ������¥���������ȡ������¥���������������Ƿ���¥��������������ӡ�
		HashMap<String,String> rsWjlz = new HashMap<String, String>();//���ҳ���Ϣ
		rsWjlz = service.serv_getWjlzInfo(xh, xn);//���¥����Ϣ
		HashMap<String,String> rsZs = CommonQueryDAO.getStuZsInfo(xh);

		boolean rsCz  =  service.lcqszPd("lz", xh);//����¥����Ϣ
		if(rsWjlz.get("lddm")!=null){
			lddm = rsWjlz.get("lddm");
			ldmc = rsWjlz.get("ldmc");
			if(!Base.isNull(rsWjlz.get("zz"))){
				qsh = rsWjlz.get("zz").replace(ldmc,"");
			}
		}else{
			lddm = rsZs.get("lddm");
			ldmc = rsZs.get("ldmc");
			qsh  = rsZs.get("qsh");
		}		
		if(rsWjlz.get("zz")!=null){			
		}else{			
			zz = Base.isNull(rsZs.get("ldmc"))?"":rsZs.get("ldmc")+ (Base.isNull(rsZs.get("qsh"))?"":rsZs.get("qsh"));
			rsWjlz.put("zz",zz);
		}
		if(!rsCz){
			request.setAttribute("noPass","noPass");
		}
		myForm.setLddm(lddm);
		request.setAttribute("lddm",Base.isNull(lddm)?"":lddm);
		request.setAttribute("ldmc",Base.isNull(ldmc)?"":ldmc);
		request.setAttribute("qsh",Base.isNull(qsh)?"":qsh);	
		request.setAttribute("ldList", getList.GetLdList());	
		request.setAttribute("rs",CommonQueryDAO.getStuInfo(xh));//ѧ�������Ϣ		
		request.setAttribute("rsWjlz",rsWjlz);//�㳤��Ϣ
		request.setAttribute("rsZs",CommonQueryDAO.getStuZsInfo(xh));
		request.setAttribute("act",act);//���ҳ���Ϣ
		request.setAttribute("xnList", Base.getXnndList());
		return mapping.findForward("wjlzSq");
	}
	/**
	 *���¥����ѯ
	 */
	public ActionForward wjlzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		YxlcqszService service = new YxlcqszService();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String lddm = request.getParameter("lddm");
		String lc   = request.getParameter("lc");
		String xn   = myForm.getXn();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
//		��Ԣ����Ա���ж�
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}		
		
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
			myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
			myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
			String yesNo = myForm.getYesNo();
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getWjlzCxTopTr();
			rs = service.serv_getWjlzCxList(yesNo, userType, userName, model);
		}					
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ldList", getList.GetLdList());
//		request.setAttribute("lcList", getList.GetLcList(lddm));
//		request.setAttribute("qshList", getList.GetQshList2(lddm, lc));		
		request.setAttribute("chkList",dao.getChkList(3));			
		request.setAttribute("tableName", "view_wjlzxx");
		request.setAttribute("realTable", "wjlzxxb");
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
		//��Ԣ����Ա����¥�������б�����
		gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);
		//		��дȨ�ж�
		request.setAttribute("writeAble",
				(Base.chkUPower(userName, "yxlcqszManage.do", userOnLine
						.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		return mapping.findForward("wjlzManange");
	}	
	/**
	 *���¥����˲�ѯ
	 */
	public ActionForward wjlzShManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		DAO  dao = DAO.getInstance();
		GetDropDownList getList = new GetDropDownList();
		YxlcqszService service = new YxlcqszService();
		GyglJhzyForm myForm = (GyglJhzyForm)form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		String userDep   = request.getSession().getAttribute("userDep").toString();
		String lddm = request.getParameter("lddm");
		String lc = request.getParameter("lc");
		String xn   = myForm.getXn();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		if (xn == null) {
			xn = Base.currXn;
			myForm.setXn(xn);
		}
//		��Ԣ����Ա���ж�
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}				
		if("xy".equalsIgnoreCase(userType)){
			myForm.setXydm(userDep);
		}
		if ("go".equalsIgnoreCase(request.getParameter("go"))) {
			myForm.setXh(DealString.toGBK(myForm.getXh()).trim());
			myForm.setXm(DealString.toGBK(myForm.getXm()).trim());
			myForm.setYesNo(DealString.toGBK(myForm.getYesNo()));
			String yesNo = myForm.getYesNo();
			YxlcqszModel model = new YxlcqszModel();
			BeanUtils.copyProperties(model, myForm);
			topTr = service.getWjlzCxTopTr();
			rs = service.serv_getWjlzShCxList(yesNo, userType, userName, model);
		}					
		FormModleCommon.setNjXyZyBjList(request);
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsNum", rs.size());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("ldList", getList.GetLdList());
//		request.setAttribute("lcList", getList.GetLcList(lddm));
//		request.setAttribute("qshList", getList.GetQshList2(lddm, lc));		
		request.setAttribute("chkList",dao.getChkList(3));			
		request.setAttribute("tableName", "view_wjlzxx");
		request.setAttribute("realTable", "wjlzxxb");
		request.setAttribute("userName",userName);
		request.setAttribute("userType",userType);
		//��Ԣ����Ա����¥�������б�����
		gyglDao.setGyfdy(request, userName, lddm, lc, Base.currXn, Base.currXq);
		//		��дȨ�ж�
		request.setAttribute("writeAble",(Base.chkUPower(userName, "yxlcqszSh.do", userOnLine.equalsIgnoreCase("student")) == 1) ? "yes" : "no");	
		return mapping.findForward("wjlzShManage");
	}				
	/**
	 * ����¥�����ҳ��������
	 */
	public ActionForward yxlcqszCk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		YxlcqszService service = new YxlcqszService();
		HttpSession session  = request.getSession();
		String check = request.getParameter("check");
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String pkVStr = request.getParameter("pkVStr");
		String xmk = request.getParameter("xmk");
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}		
		
		service.serv_yxlcqszCk(userType, check, xmk, pkVStr);
		if ("bjqsz".equalsIgnoreCase(xmk)) {			
			return bjqszShManage(mapping, form, request, response);
		}if("esjcz".equalsIgnoreCase(xmk)){
			return esjczShManage(mapping, form, request, response);
		}if("wjlz".equalsIgnoreCase(xmk)){
			return wjlzShManage(mapping, form, request, response);
		}else {
			return null;
		}
	}
	/**
	 * ����¥�����ҳ�����ɾ��
	 */
	public ActionForward yxlcqszDel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		YxlcqszService service = new YxlcqszService();
		String userType = (String) session.getAttribute("userType");
		String pkVStr = request.getParameter("pkVStr");
		String xmk = request.getParameter("xmk");
		service.serv_yxlcqszDel(userType, xmk, pkVStr);
		if("bjqsz".equalsIgnoreCase(xmk)){
			return bjqszManage(mapping, form, request, response);
		}if("esjcz".equalsIgnoreCase(xmk)){
			return esjczManage(mapping, form, request, response);
		}if("wjlz".equalsIgnoreCase(xmk)){
			return wjlzManage(mapping, form, request, response);
		}else{
			return null;
		}		
	}
	/**
	 * ����¥�����ҳ��������
	 */
	public ActionForward yxlcqszChek(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		YxlcqszService service = new YxlcqszService();

		String pkValue = request.getParameter("pkValue");
		String xmk = request.getParameter("xmk");
		HashMap<String, String> map = new HashMap<String, String>();
		String findForward = "";
		String userType = (String) session.getAttribute("userType");
		String doType = request.getParameter("doType");
		String userName = request.getSession().getAttribute("userName").toString();
//		��Ԣ����Ա���ж�
		if(gyglDao.gyFdyPd(userName, Base.currXn,Base.currXq)){
			userType = "isGyFdy";
		}
		if("save".equalsIgnoreCase(doType)){//������˽��
			String yesNo = DealString.toGBK(request.getParameter("yesNo"));
			boolean done=false;     	 
			done=service.serv_yxlcqszChk(userType, pkValue, xmk, yesNo);
			request.setAttribute("done",done);
		}				
		if ("bjqsz".equalsIgnoreCase(xmk)) {
			map = service.serv_getBjqszInfo(pkValue);
			findForward = "bjqszChek";
		}else if ("esjcz".equalsIgnoreCase(xmk)){
			map = service.serv_getEsjczInfo(pkValue);
			findForward = "esjczChek";
		}else if ("wjlz".equalsIgnoreCase(xmk)){
			map = service.serv_getWjlzInfo(pkValue);
			findForward = "wjlzChek";
		}		
		if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			map.put("yesNo",map.get("xxsh"));
		}else if("xy".equalsIgnoreCase(userType)){
			map.put("yesNo",map.get("xysh"));
		}else{
			map.put("yesNo",map.get("fdysh"));
		}
		request.setAttribute("rs", CommonQueryDAO.getStuInfo(map.get("xh")));
		request.setAttribute("rsYxlcqsz", map);
		request.setAttribute("chkList",dao.getChkList(3));
		request.setAttribute("userType",userType);
		request.setAttribute("pkValue",pkValue);
		request.setAttribute("xmk",xmk);
		return mapping.findForward(findForward);
	}
	/**
	 * ������ӡ
	 */
	public ActionForward sqbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		String findForward="";
		String xmk = request.getParameter("xmk");
		String xh=request.getParameter("xh");
		String xn=request.getParameter("xn");	
		request.setAttribute("lxdh",DealString.toGBK(request.getParameter("lxdh")));
		if ("bjqsz".equalsIgnoreCase(xmk)) {
			String ldmc = DealString.toGBK(request.getParameter("ldmc"));
			String qsh = DealString.toGBK(request.getParameter("qsh"));
			request.setAttribute("ldmc", ldmc);
			request.setAttribute("qsh",qsh);
			request.setAttribute("qsjsqk",DealString.toGBK(request.getParameter("qsjsqk")));			
			findForward = "bjqszSqb";
		}else if("esjcz".equalsIgnoreCase(xmk)){			
			String ldmc = DealString.toGBK(request.getParameter("ldmc"));
			String qsh = DealString.toGBK(request.getParameter("qsh"));   
			request.setAttribute("ldmc", ldmc);
			request.setAttribute("qsh",qsh);
			findForward = "esjczSqb";
			request.setAttribute("szlcqk",DealString.toGBK(request.getParameter("szlcqk")));
		}else if("wjlz".equalsIgnoreCase(xmk)){			
			String ldmc = DealString.toGBK(request.getParameter("ldmc"));
			String qsh = DealString.toGBK(request.getParameter("qsh"));   
			request.setAttribute("ldmc", ldmc);
			request.setAttribute("qsh",qsh);
			findForward = "wjlzSqb";
			request.setAttribute("ldqk",DealString.toGBK(request.getParameter("ldqk")));
		}
		request.setAttribute("rs",CommonQueryDAO.getStuInfo(xh));	
		request.setAttribute("xxmc",Base.xxmc);
		request.setAttribute("xh",xh);
		request.setAttribute("xn",xn);
		return mapping.findForward(findForward);
	}
	/**
	 * ����¥�㳤������ѧ����ѯ
	 */
	public ActionForward  yxlcqszXsSqJg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		YxlcqszService service = new YxlcqszService();
		String userName = request.getSession().getAttribute("userName").toString();
		ArrayList<String[]> rsBjqsz = new ArrayList<String[]>();
		rsBjqsz = service.serv_getBjqszList_stu(userName);
		ArrayList<String[]> rsEsjcz = new ArrayList<String[]>();
		rsEsjcz = service.serv_getEsjczList_stu(userName);
		ArrayList<String[]> rsWjlz = new ArrayList<String[]>();
		rsWjlz = service.getWjlzList_stu(userName);		
		request.setAttribute("rsBjqsz",rsBjqsz);//�ټ����ҳ���������
		request.setAttribute("rsBjqszNum",rsBjqsz.size());
		request.setAttribute("rsEsjcz",rsEsjcz);//��ʮ�Ѳ㳤��������
		request.setAttribute("rsEsjczNum",rsEsjcz.size());
		request.setAttribute("rsWjlz",rsWjlz);// ���¥����������
		request.setAttribute("rsWjlzNum",rsWjlz.size());			
		request.setAttribute("numCout", rsWjlz.size()+rsBjqsz.size()+rsEsjcz.size());
		return mapping.findForward("yxlcqszXsSqJg");
	}
}
