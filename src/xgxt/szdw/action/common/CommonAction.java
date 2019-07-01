package xgxt.szdw.action.common;

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
import org.jaxen.function.FalseFunction;

import com.zfsoft.basic.BasicAction;

import xgxt.DAO.SxjyDAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.dtjs.utils.GetWriteAbleAndTitle;
import xgxt.form.RequestForm;
import xgxt.jxgl.JxglTyForm;
import xgxt.jxgl.gzdx.JxglGzdxService;
import xgxt.sxjy.action.portallet.SzdwForm;
import xgxt.szdw.form.common.CommonForm;
import xgxt.szdw.model.common.CommonModel;
import xgxt.szdw.server.common.CommonService;
import xgxt.szdw.utils.GetColCommentForTableName;
import xgxt.utils.FormModleCommon;

public class CommonAction extends BasicAction{
	
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ����˼������-˼������-ͨ��action��</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	public ActionForward jyltxt(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	   ��������̳��Ϣ��Ҫ���
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String tableName     = DealString.toGBK(myForm.getTableName());
		String realTable     = "";
		ArrayList<String[]> rs = null; 
		
		String xy = myForm.getXydm();
		   
		xy = (xy==null) ? "" : xy;
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = service.getLtxxList(myModel,tableName,myForm);
		}
		List topTr = service.getLtxxTopTr(tableName);
		if(tableName.equalsIgnoreCase("view_yxdxsxsltb")){
			realTable="fzjy_yxdxsxsltb";
		}else{
			realTable=tableName;
		}
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("jyltxt");				
	}
	
	public ActionForward jyltxtOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//��������̳��Ϣ��Ҫ
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			String tableName               =    DealString.toGBK(request.getParameter("tableName"));
			CommonService service          =    new CommonService();
			HashMap<String, String> rs     =    service.getJyltxtOne(pk,tableName);
			request.setAttribute("rs", rs);
			request.setAttribute("tableName", tableName);
			request.setAttribute("pk", pk);
			if(tableName.equalsIgnoreCase("fzjy_xsrxjyb")){
				request.setAttribute("xyList", Base.getXyList());
				return mapping.findForward("xsrxjyOne");	
			}else if (tableName.equalsIgnoreCase("fzjy_fdyltb")){
				return mapping.findForward("fdyltOne");	
			}else if (tableName.equalsIgnoreCase("view_yxdxsxsltb")){
				request.setAttribute("xyList", Base.getXyList());
				return mapping.findForward("yxdxsxsltOne");	
			}else if (tableName.equalsIgnoreCase("fzjy_hyjtb")){
				return mapping.findForward("hyjtOne");	
			}else{
				return null;
			}
	}
	
	public ActionForward saveJyltxtOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�����������̳��Ϣ��Ҫ
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String tableName = DealString.toGBK(request.getParameter("tableName"));
		myForm.setTableName(tableName);
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataJyltxt(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		if(tableName.equalsIgnoreCase("fzjy_xsrxjyb")){
			return mapping.findForward("xsrxjyOne");	
		}else if (tableName.equalsIgnoreCase("fzjy_fdyltb")){
			return mapping.findForward("fdyltOne");	
		}else if (tableName.equalsIgnoreCase("view_yxdxsxsltb")){
			return mapping.findForward("yxdxsxsltOne");	
		}else if (tableName.equalsIgnoreCase("fzjy_hyjtb")){
			return mapping.findForward("hyjtOne");	
		}else{
			return null;
		}
	}
	
	public ActionForward delJyltxtOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//��������̳��Ϣ��Ҫ
		CommonService service          =    new CommonService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		CommonForm myForm              =    (CommonForm)  form;
		String tableName     = DealString.toGBK(myForm.getTableName());
		boolean inserted            =    service.deleteJyltxtOne(pk,tableName,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return jyltxt(mapping, form,request, response);		
	}
	
	//ʱ���������
	public ActionForward sbsjsz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//	  ʱ��������ʾ
		CommonService service          =    new CommonService();
		
		ArrayList<String[]> rs = service.getSjszsList();
	
		request.setAttribute("rs", rs);
		return mapping.findForward("sbsjsz");		
	}
	
	public ActionForward saveSbsjsz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    ʱ�����ñ���
		CommonService service          =    new CommonService();
		CommonForm myForm              =    (CommonForm) form;
		CommonModel myModel            =    new CommonModel();
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted             =    service.saveSjszs(myModel,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return sbsjsz(mapping, form,request,response);
	}

	public ActionForward xssqYcjy (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "fzjy_ycjyb";
		if(userType.equalsIgnoreCase("stu")){
			String xh                      =    userName;
			CommonService service          =    new CommonService();
			boolean sfksq = service.getSqsj(tableName);
			request.setAttribute("sfksq", sfksq);
			if(sfksq){
				HashMap<String, String> rs     =    service.getXsxxxx(xh);
				request.setAttribute("rs", rs);
			}else{
				request.setAttribute("message", "���ڲ�������ʱ����");
			}
		}else{
			request.setAttribute("message", "ֻ��ѧ�����ܷ��ʱ�ҳ��");
		}
		
		return mapping.findForward("xssqYcjy");
	}
	
	public ActionForward xssqJzfdy (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "fzjy_jzfdyb";
		if(userType.equalsIgnoreCase("stu")){
			String xh                      =    userName;
			CommonService service          =    new CommonService();
			boolean sfksq = service.getSqsj(tableName);
			request.setAttribute("sfksq", sfksq);
			if(sfksq){
				HashMap<String, String> rs     =    service.getXsxxxx(xh);
				request.setAttribute("rs", rs);
			}else{
				request.setAttribute("message", "���ڲ�������ʱ����");
			}
		}else{
			request.setAttribute("message", "ֻ��ѧ�����ܷ��ʱ�ҳ��");
		}
		return mapping.findForward("xssqJzfdy");
	}
	
	public ActionForward xssqWspyxjgr (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "fzjy_wspyxjgrb";
		if(userType.equalsIgnoreCase("stu")){
			String xh                      =    userName;
			CommonService service          =    new CommonService();
			boolean sfksq = service.getSqsj(tableName);
			request.setAttribute("sfksq", sfksq);
			if(sfksq){
				HashMap<String, String> rs     =    service.getXssqxx(xh);
				request.setAttribute("rs", rs);
			}else{
				request.setAttribute("message", "���ڲ�������ʱ����");
			}
		}else{
			request.setAttribute("message", "ֻ��ѧ�����ܷ��ʱ�ҳ��");
		}
		return mapping.findForward("xssqWspyxjgr");
	}
	
	public ActionForward xssqXsjlsbgr (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		CommonService service          =    new CommonService();
		String tableName = "fzjy_xsjlsbgrb";
		if(userType.equalsIgnoreCase("stu")){
			String xh                      =    userName;
			
			boolean sfksq = service.getSqsj(tableName);
			request.setAttribute("sfksq", sfksq);
			if(sfksq){
				HashMap<String, String> rs     =    service.getXssqxx(xh);
				request.setAttribute("rs", rs);
			}else{
				request.setAttribute("message", "���ڲ�������ʱ����");
			}
		}else{
			request.setAttribute("message", "ֻ��ѧ�����ܷ��ʱ�ҳ��");
		}
		request.setAttribute("sbxskjcglxList", service.getSercicesbxskjcglxList());
		request.setAttribute("cgjbList", service.getCgjbList());
		return mapping.findForward("xssqXsjlsbgr");
	}
	
	public ActionForward saveYcjysq (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//����Ӣ�Ź���
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		setFormForXnNdXqNj(myForm);
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateYcjy(myModel,"",request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xssqYcjy(mapping, form,request,response);	
	}
	
	public ActionForward saveJzfdysq (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�����ְ����Ա
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		setFormForXnNdXqNj(myForm);
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateJzfdy(myModel,"",request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xssqJzfdy(mapping, form,request,response);	
	}
	
	public ActionForward saveWspyxjgrsq (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�������������Ƚ�����
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		setFormForXnNdXqNj(myForm);
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateWspyxjgr(myModel,"",request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xssqWspyxjgr(mapping, form,request,response);	
	}
	
	public ActionForward saveXsjlsbgrsq (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//����ѧ�������걨����
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		setFormForXnNdXqNj(myForm);
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateXsjlsbgr(myModel,"",request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xssqXsjlsbgr(mapping, form,request,response);	
	}
	
	public ActionForward xssbsh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	   ѧ�������������
		HttpSession session = request.getSession();
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		String tableName     = DealString.toGBK(myForm.getTableName());
		String realTable     = "";
		ArrayList<String[]> rs = null;
		
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
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = service.getXssqshList(myModel,tableName);
		}
		List topTr = service.getXssqshTopTr(tableName);
		if(tableName.equalsIgnoreCase("view_fzjyycjyb")){
			realTable="fzjy_ycjyb";
		}else if(tableName.equalsIgnoreCase("view_fzjyjzfdyb")){
			realTable="fzjy_jzfdyb";
		}else if(tableName.equalsIgnoreCase("view_fzjyxsjlsbgr")){
			realTable="fzjy_xsjlsbgrb";
		}else if(tableName.equalsIgnoreCase("view_wspyxjgr")){
			realTable="fzjy_wspyxjgrb";
		}
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xnList", Base.getXnndList());
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xssbsh");				
	}
	
	public ActionForward delXssbshOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//˼������ѧ����������ɾ��
		CommonService service          =    new CommonService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		String  realTable    = DealString.toGBK(request.getParameter("realTable"));
		boolean inserted            =    service.deleteXssbshOne(pk,realTable,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return xssbsh(mapping, form,request, response);		
	}
	
	public ActionForward xssbshOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//˼������ѧ�������������
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		String tableName               =    DealString.toGBK(request.getParameter("tableName"));
		String xh                      =    DealString.toGBK(request.getParameter("xh"));
		CommonService service          =    new CommonService();
		HashMap<String, String> rs     =    new HashMap<String, String>();
		if(!xh.equalsIgnoreCase("")){
			rs     =    service.getXsxxxx(xh);
		}else{
			rs     =    service.getXssbshOne(pk,tableName);
		}
		request.setAttribute("rs", rs);
		request.setAttribute("tableName", tableName);
		request.setAttribute("pk", pk);
		request.setAttribute("xnList", Base.getXnndList());
		if(tableName.equalsIgnoreCase("view_fzjyycjyb")){
			return mapping.findForward("ycjySh");	
		}else if (tableName.equalsIgnoreCase("view_fzjyjzfdyb")){
			return mapping.findForward("jzfdySh");	
		}else if (tableName.equalsIgnoreCase("view_fzjyxsjlsbgr")){
			request.setAttribute("sbxskjcglxList", service.getSercicesbxskjcglxList());
			request.setAttribute("cgjbList", service.getCgjbList());
			return mapping.findForward("xsjlsbgrSh");	
		}else if (tableName.equalsIgnoreCase("view_wspyxjgr")){
			return mapping.findForward("wspyxjgrSh");	
		}else{
			return null;
		}
	}
	
	public ActionForward jzfdySh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//	��ְ����Ա���
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateJzfdy(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return  mapping.findForward("jzfdySh");	
	}
	
	public ActionForward xsjlsbgrSh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//	ѧ�������걨�������
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateXsjlsbgr(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("xsjlsbgrSh");	
	}
	
	public ActionForward wspyxjgrSh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//	���������Ƚ��������
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateWspyxjgr(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("wspyxjgrSh");	
	}
	
	public ActionForward ycjySh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//	Ӣ�Ž������
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateYcjy(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("ycjySh");	
	}
	
	public ActionForward wspyjtsb (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "fzjy_wspyxjjtb";
		if(userType.equalsIgnoreCase("stu")){
			CommonService service          =    new CommonService();
			String xh                      =    userName;
			String bjdm                    =    service.getBjdmByXh(xh);
			if(bjdm!=null&&!bjdm.equalsIgnoreCase("")){
				boolean sfksq = service.getSqsj(tableName);
				request.setAttribute("szbjdm", bjdm);
				if(sfksq){ 
					HashMap<String, String> rs     =    service.getBjxx(bjdm);
					request.setAttribute("rs", rs);
				}else{
					request.setAttribute("message", "���ڲ�������ʱ����");
				}
			}else{
					request.setAttribute("message", "��ģ��ֻ�а�ɲ���������");
			}
		}else{
			request.setAttribute("message", "ֻ��ѧ�����ܷ��ʱ�ҳ��");
		}
		return mapping.findForward("wspyjtsb");
	}
	
	public ActionForward xsjljtsb (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "fzjy_xskjjljtsbb";
		if(userType.equalsIgnoreCase("stu")){
			CommonService service          =    new CommonService();
			String xh                      =    userName;
			String bjdm                    =    service.getBjdmByXh(xh);
			if(bjdm!=null&&!bjdm.equalsIgnoreCase("")){
				boolean sfksq = service.getSqsj(tableName);
				request.setAttribute("szbjdm", bjdm);
				if(sfksq){
					HashMap<String, String> rs     =    service.getBjxx(bjdm);
					request.setAttribute("rs", rs);
				}else{
					request.setAttribute("message", "���ڲ�������ʱ����");
				}
			}else{
					request.setAttribute("message", "��ģ��ֻ�а�ɲ���������");
			}
		}else{
			request.setAttribute("message", "ֻ��ѧ�����ܷ��ʱ�ҳ��");
		}
		return mapping.findForward("xsjljtsb");
	}
	
	public ActionForward saveWspyxjjtsq (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�������������Ƚ�����
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		setFormForXnNdXqNj(myForm);
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateWspyxjJt(myModel,"",request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return wspyjtsb(mapping, form,request,response);	
	}
	
	public ActionForward saveXsjlsbjtsq (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//����ѧ�������걨����
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		setFormForXnNdXqNj(myForm);
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateXsjlsbJt(myModel,"",request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xsjljtsb(mapping, form,request,response);	
	}
	
	public ActionForward jtsbsh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	   �����������
		HttpSession session = request.getSession();
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		String tableName     = DealString.toGBK(myForm.getTableName());
		String realTable     = "";
		ArrayList<String[]> rs = null;
		
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
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = service.getJtsqshList(myModel,tableName);
		}
		List topTr = service.getJtsqshTopTr(tableName);
		if(tableName.equalsIgnoreCase("view_wspyxjjt")){
			realTable="fzjy_wspyxjjtb";
		}else if(tableName.equalsIgnoreCase("view_xsjlsbjt")){
			realTable="fzjy_xskjjljtsbb";
		}
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xnList", Base.getXnndList());
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("jtsbsh");				
	}
	
	public ActionForward jtsbshOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//˼������ѧ�������������
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		String tableName               =    DealString.toGBK(request.getParameter("tableName"));
		CommonService service          =    new CommonService();
		HashMap<String, String> rs     =    new HashMap<String, String>();
		rs     =    service.getJtsbshOne(pk,tableName);
		request.setAttribute("rs", rs);
		request.setAttribute("tableName", tableName);
		request.setAttribute("pk", pk);
		request.setAttribute("xnList", Base.getXnndList());
		if(tableName.equalsIgnoreCase("view_wspyxjjt")){
			return mapping.findForward("wspyjtsh");	
		}else if(tableName.equalsIgnoreCase("view_xsjlsbjt")){
			return mapping.findForward("xsjljtsh");	
		}else{
			return null;
		}
	}
	
	public ActionForward xsjljtSh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//	ѧ�������걨�������
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateXsjlsbJt(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("xsjljtsh");	
	}
	
	public ActionForward wspyxjjtsh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//	���������Ƚ��������
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updateWspyxjJt(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("wspyjtsh");	
	}
	
	public ActionForward fdyxgxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	  ����Ա�����Ϣά��
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		
		String tableName     = DealString.toGBK(myForm.getTableName());
		String realTable     = "";
		ArrayList<String[]> rs = null;
		String bmdm          = myForm.getBmdm();
		
		if ((!userType.equalsIgnoreCase("admin"))&& (bmdm == null || bmdm.equalsIgnoreCase(""))) {
			bmdm = userDep;
			myForm.setBmdm(bmdm);
		}
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = service.getFdyxgxxList(myModel,tableName);
		}
		List topTr = service.getFdyxgxxTopTr(tableName);
		if(tableName.equalsIgnoreCase("view_fzjyfdymxfw")){
			realTable="fzjy_yxdxsxsltb";
		}else if(tableName.equalsIgnoreCase("view_fzjyfdyjnds")){
			realTable="fzjy_fdyjnds";
		}
		request.setAttribute("bmList",service.getBmList());
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		myForm.setBmmc(DealString.toGBK(myForm.getBmmc()));
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("fdyxgxx");				
	}
	
	public ActionForward fdyxgxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//		  ��������Ա�����Ϣά��
			HttpSession session = request.getSession();
			String userType = session.getAttribute("userType").toString();
			String userDep = session.getAttribute("userDep").toString();
			String bmdm    = "";
			
			if (!userType.equalsIgnoreCase("admin")) {
				bmdm = userDep;
			}
			request.setAttribute("bmdm", bmdm);
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			String tableName               =    DealString.toGBK(request.getParameter("tableName"));
			CommonService service          =    new CommonService();
			HashMap<String, String> rs     =    service.getFdyxgxxOne(pk,tableName);
			request.setAttribute("rs", rs);
			request.setAttribute("tableName", tableName);
			request.setAttribute("pk", pk);
			request.setAttribute("bmList",service.getBmList());
			request.setAttribute("fdyList",service.getFdyList(bmdm));
			HashMap<String, String> rsLength     =    GetColCommentForTableName.GetColCommentForTableNameT(tableName);
			request.setAttribute("rsLength", rsLength);
			if(tableName.equalsIgnoreCase("view_fzjyfdyjnds")){
				return mapping.findForward("fdyjndsOne");	
			}else if (tableName.equalsIgnoreCase("view_fzjyfdymxfw")){
				return mapping.findForward("fdymxfwOne");	
			}else{
				return null;
			}
	}
	
	public ActionForward saveFdyxgxxOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//	���渨��Ա�����Ϣ
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String tableName = DealString.toGBK(request.getParameter("tableName"));
		myForm.setTableName(tableName);
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataFdyxgxx(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		if(tableName.equalsIgnoreCase("view_fzjyfdyjnds")){
			return mapping.findForward("fdyjndsOne");	
		}else if (tableName.equalsIgnoreCase("view_fzjyfdymxfw")){
			return mapping.findForward("fdymxfwOne");	
		}else{
			return null;
		}
	}
	
	public ActionForward delFdyxgxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//��������̳��Ϣ��Ҫ
		CommonService service          =    new CommonService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		CommonForm myForm              =    (CommonForm)  form;
		String tableName     = DealString.toGBK(myForm.getTableName());
		boolean inserted            =    service.delFdyxgxx(pk,tableName,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return jyltxt(mapping, form,request, response);		
	}
	
	public ActionForward zwpjb (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//		  ��������Ա��������
			String zgh = request.getParameter("zgh");
			String fs  = request.getParameter("fs");
			CommonService service          =    new CommonService();
			HashMap<String, String> rs     =    service.getFdyzpxxOne(zgh);
			if(fs!=null){
				Vector<String[]> rs2 = new Vector<String[]>();
				SxjyDAO sxjyDao = new SxjyDAO();
				rs2.addAll(sxjyDao.getJspjForZp(zgh));
				Double count = 0.00;
				for(int i=0;i<rs2.size();i++){
					count = count+Double.parseDouble(rs2.get(i)[1]);
				}
				rs.put("fz", count.toString());
				request.setAttribute("rs", rs);
				return mapping.findForward("zwpjbRep");	
			}else{
				request.setAttribute("rs", rs);
				return mapping.findForward("zwpjb");	
			}
	}
	
	public ActionForward saveZwpjb(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//	���浥������Ա��������
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataFdyzpxx(myModel,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("zwpjb");	
	}
	
	public ActionForward fdyGzwj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	   ����Ա�����ʾ�
		CommonForm myForm              =    (CommonForm)  form;
		CommonService service          =    new CommonService();
		CommonModel myModel            =    new CommonModel();
		String tableName     = "view_fdygzwjjg";
		String realTable     = "";
		ArrayList<String[]> rs = null; 
		
		String xy = myForm.getXydm();
		   
		xy = (xy==null) ? "" : xy;
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			rs = service.getFdyGzwj(myModel,tableName);
		}
		myForm.setXm(DealString.toGBK(myForm.getXm()));
		List topTr = service.getFdyGzwjTopTr(tableName);
		request.setAttribute("bmList", service.getBmList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("fdygzwjjg");				
	}
	
	public ActionForward fdybjjcsz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//����Ա�༶��������
		CommonService service          =    new CommonService();
    	HashMap<String, String> rs =  service.getFdybjjcsz();
    	request.setAttribute("rs", rs);
		return mapping.findForward("fdybjjcsz");				
	}
	
	public ActionForward fdybjjcszSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//����Ա�༶�������ñ���
		String fdybjsz = request.getParameter("fdybjsz");
		String bzrbjsz = request.getParameter("bzrbjsz");
		CommonService service          =    new CommonService();
		boolean update = service.updataFdybjjcszSave(fdybjsz, bzrbjsz, request);
		if(update){
			request.setAttribute("done", "true");
		}else{
			request.setAttribute("done", "false");
		}
		return fdybjjcsz(mapping,form,request,response);				
	}
	
	public ActionForward fdybjCsh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//����Ա�༶��ʼ��
		CommonService service          =    new CommonService();
		boolean update = service.delAllFpbj("fdy");
		if(update){
			request.setAttribute("done", "qk");
		}else{
			request.setAttribute("done", "wqk");
		}
		return fdybjjcsz(mapping,form,request,response);				
	}
	
	public ActionForward bzrbjCsh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//����Ա�༶��ʼ��
		CommonService service          =    new CommonService();
		boolean update = service.delAllFpbj("bzr");
		if(update){
			request.setAttribute("done", "qk");
		}else{
			request.setAttribute("done", "wqk");
		}
		return fdybjjcsz(mapping,form,request,response);				
	}
	
	private void commonRequestSet(HttpServletRequest request, String tableName, String realTable, ArrayList<String[]> rs, List topTr) {
		// Request��ֵ��ͨ�÷���2 ������title�����ݿ���ȡ
		String writeAble  = request.getParameter("writeAble");
		String title      = DealString.toGBK(request.getParameter("title"));
		if(Base.isNull(writeAble)) {
			String [] message = GetWriteAbleAndTitle.getWriteAbleAndTitle(request);
			writeAble = message[0];
			title     = message[1];
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
	
	@SuppressWarnings("unused")
	private void setFormForXnNdXqNj(CommonForm myForm) {
		//    �ж�ѧ�꣬��ȣ�ѧ��,�꼶����form��ע��ķ���
			String xn           = Base.currXn;
			String xq           = Base.currXq;
			String nd           = Base.currNd;
			
			if((DealString.toGBK(myForm.getXn())).equalsIgnoreCase("")){
				myForm.setXn(xn);
			}
			
			if((DealString.toGBK(myForm.getXq())).equalsIgnoreCase("")){
				myForm.setXq(xq);
			}
			
			if((DealString.toGBK(myForm.getNd())).equalsIgnoreCase("")){
				myForm.setNd(nd);
				myForm.setNj(nd);
			}
			
	}
	
	/**
	 * ˼��������ݷ�����ʷ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward putLsjl(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//����Ա�༶��ʼ��
		CommonService service          =    new CommonService();
		boolean update = service.putLsjl();
		if(update){
			request.setAttribute("done", "yes");
		}else{
			request.setAttribute("done", "no");
		}
		return new ActionForward("/setFdyBj.do");				
	}
	
	/**
	 * ˼�������ʷ��¼��ѯ
	 * 
	 * @author luning
	 * @return ActionForward
	 */
	public ActionForward szdwBbLy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		//================== ����ֵ===================
		HttpSession session = request.getSession();
		String userType = (String)session.getAttribute("userType");
		String userDep = (String)session.getAttribute("userDep");
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		CommonForm myForm = (CommonForm) form;
		//�ж�Ϊ�������Ĭ��ֵ
		if(tableName==null||tableName.equalsIgnoreCase("")){
			tableName = "view_fdybbjls";
			realTable = "fdybjlsb";
			myForm.setTableName(tableName);
			
		}else if(tableName.equalsIgnoreCase("view_bzrbbjls")){
			realTable = "fdybjlsb";
		}
		
		

		//��¼�û�����ΪѧԺ
		if ("xy".equalsIgnoreCase(userType)) {
			myForm.setQueryequals_xydm(userDep);
		}
		
		// ==================ִ�в�ѯ���� ==================
		if (search) {
			String[] outputColumn = { "xn","nj","xymc","zymc","bjmc","zgh","xm","yddh" };
			this.selectPageDataByPagination(request, myForm, "", tableName,
					outputColumn);
		}
		
		// =================end ===================
		
		//===============��ʼ��request��ֵ =====================
		request.setAttribute("tableName", tableName);
		request.setAttribute("realTable", realTable);
		FormModleCommon.commonRequestSet(request);
		// =================end ===================

		// ===================��ʼ��request��ֵ ======================
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);//�꼶ѧԺרҵ�༶
		FormModleCommon.setNdXnXqList(request);//���ѧ��ѧ��
		// =================end ===================
		
		// =================end ===================
		return mapping.findForward("fdybjrLsjl");		
	}
	
	/**
	 * ˼��ģ��ͨ�õ���
	 * 
	 * @author luning
	 * @return ActionForward
	 */
	public ActionForward szdwExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("tableName");
		String realTable = request.getParameter("realTable");
		String[] outputColumn = null;
		expPageData(request, response, realTable, tableName, outputColumn);
		return mapping.findForward("");	
	}
}
