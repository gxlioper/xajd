 package xgxt.shgz.action;

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

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.dtjs.utils.GetWriteAbleAndTitle;
import xgxt.shgz.form.XsgbForNblgForm;
import xgxt.shgz.model.XsgbForNblgModel;
import xgxt.shgz.server.XsgbForNblgService;

public class XsgbForNblgAction extends DispatchAction{
	 
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ������ѧ-action��</p>
	* <p>Copyright: Copyright (c) 2009</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	
	public ActionForward xshzzxxsb(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	    ����֯�ɲ�
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_xshzzsb";
		String realTable     = "nblg_xshzzsbb";
		
		ArrayList<String[]> rs = null;
		XsgbForNblgForm myForm              =    (XsgbForNblgForm)  form;
		XsgbForNblgService service          =    new XsgbForNblgService();
		XsgbForNblgModel myModel            =    new XsgbForNblgModel();
		
		String bm = myForm.getBmdm();
		
		if (userType.equalsIgnoreCase("xy")&& (bm == null || bm.equalsIgnoreCase(""))) {
			bm = userDep;
			myForm.setBmdm(bm);
		}
		bm = (bm==null) ? "" : bm;
		BeanUtils.copyProperties(myModel,myForm);
		
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getXshzzList(myModel);
		}
		if(userType.equalsIgnoreCase("xy")){
			request.setAttribute("isXy", "yes");
			boolean sfsjn = service.xszzsbsj();
			if(sfsjn){
				request.setAttribute("ksb", "yes");
			}else{
				request.setAttribute("ksb", "no");
			}
		}else{
			request.setAttribute("isXy", "no");
		}
		List topTr = service.getXshzzTopTr();

		request.setAttribute("bmdm", bm);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("bmList", service.getBmList());
		request.setAttribute("xqList", Base.getXqList());
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xshzzxx");		
	}
	
	public ActionForward xshzzxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ѧ������֯��Ϣ�����鿴
			HttpSession session = request.getSession();
			String userType = session.getAttribute("userType").toString();
			String userDep = session.getAttribute("userDep").toString();
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			String writeAble               =    DealString.toGBK(request.getParameter("writeAble"));
			XsgbForNblgService service          =    new XsgbForNblgService();
			if(userType.equalsIgnoreCase("xy")){
				request.setAttribute("isXy", "yes");
			}else{
				request.setAttribute("isXy", "no");
			}
			HashMap<String, String> rs     =    service.getXshzzxxOne(pk);
			request.setAttribute("bmdm", userDep);
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("rs", rs);
			request.setAttribute("bmList", service.getBmList());
			request.setAttribute("sukmList", service.getSukmList());
			request.setAttribute("xsgbzlList", service.getXsgbzlList(pk));
			return mapping.findForward("xshzzxxSb");	
	}
	
	public ActionForward saveXshzzxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ѧ������֯��Ϣ�����鿴����
		XsgbForNblgForm myForm              =    (XsgbForNblgForm)  form;
		XsgbForNblgService service          =    new XsgbForNblgService();
		XsgbForNblgModel myModel            =    new XsgbForNblgModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataXshzzxxOne(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("xshzzxxSb");	
	}
	
	public ActionForward delXshzzxxOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //ѧ������֯��Ϣ����ɾ��
		XsgbForNblgService service          =    new XsgbForNblgService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteXshzzxxOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return xshzzxxsb(mapping, form,request, response);		
	}
	
	public ActionForward xsgbsq (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ѧ����֯�ɲ�����
			HttpSession session = request.getSession();
			String userType = session.getAttribute("userType").toString();
			String userName = session.getAttribute("userName").toString();
			if(userType.equalsIgnoreCase("stu")){
				String xh                      =    userName;
				XsgbForNblgService service          =    new XsgbForNblgService();	
				boolean sfksq = service.xsgbsqsj();
				if(sfksq){
					HashMap<String, String> rs     =    service.getXsgbsq(xh);
					String []  yrxsgbList     =    service.getYrxsgbList(xh);
					XsgbForNblgForm myForm              =    (XsgbForNblgForm)  form;
					String bmdm = DealString.toGBK(myForm.getBmdm());
					String zzdm = DealString.toGBK(myForm.getZzdm());
					if(!zzdm.equalsIgnoreCase("")){
						bmdm = service.getBmdmForZzdm(zzdm);
						myForm.setBmdm(bmdm);
					}
					request.setAttribute("yrxsgbList", yrxsgbList);
					request.setAttribute("rs", rs);
					request.setAttribute("bmList", service.getBmList());
					request.setAttribute("xszzList", service.getXszzList(bmdm));
					request.setAttribute("xsgbList", service.getXsgbList(zzdm));
				}else{
					request.setAttribute("message", "���ڲ�������ʱ����");
				}
			}else{
				request.setAttribute("message", "ֻ��ѧ�����ܷ��ʱ�ҳ��");
			}
			return mapping.findForward("xsgbsq");	
	}

	public ActionForward saveXsgbsqOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ѧ������ѧ����֯�ɲ�����
		XsgbForNblgForm myForm              =    (XsgbForNblgForm)  form;
		XsgbForNblgService service          =    new XsgbForNblgService();
		XsgbForNblgModel myModel            =    new XsgbForNblgModel();
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.saveXsgbsqOne(myModel,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xsgbsq(mapping, form,request, response);	
	}
	
	public ActionForward delXsgbOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //ѧ������֯��Ϣ����ɾ��
		XsgbForNblgService service          =    new XsgbForNblgService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.delXsgbOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return xsgbsh(mapping, form,request, response);		
	}
	
	public ActionForward xsgbsh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	    ѧ����֯�ɲ��������
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_xszzgbsq";
		String realTable     = "nblg_xszzgbsqb";
		
		ArrayList<String[]> rs = null;
		XsgbForNblgForm myForm              =    (XsgbForNblgForm)  form;
		XsgbForNblgService service          =    new XsgbForNblgService();
		XsgbForNblgModel myModel            =    new XsgbForNblgModel();
		
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
		String bjKey = xy+"!!"+zy+"!!"+nj;
		
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getXsgbList(myModel);
		}
		String bmdm = DealString.toGBK(myForm.getBmdm());
		String zzdm = DealString.toGBK(myForm.getZzdm());
		if(!zzdm.equalsIgnoreCase("")){
			bmdm = service.getBmdmForZzdm(zzdm);
			myForm.setBmdm(bmdm);
		}
		
		List topTr = service.getXsgbTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xszzList", service.getXszzList(bmdm));
		request.setAttribute("xsgbList", service.getXsgbList(zzdm));
		request.setAttribute("bmList", service.getBmList());
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xsgbsqCx");		
	}
	
	public ActionForward xsgbsqOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ѧ����֯�ɲ�����
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		String xh                      =    DealString.toGBK(request.getParameter("xh"));
		String writeAble               =    DealString.toGBK(request.getParameter("writeAble"));
		XsgbForNblgService service          =    new XsgbForNblgService();
		HashMap<String, String> rs     =    service.getXsgbshOne(pk,xh);
		String []  yrxsgbList     =    service.getYrxsgbList(xh);
		XsgbForNblgForm myForm              =    (XsgbForNblgForm)  form;
		String bmdm = DealString.toGBK(myForm.getBmdm());
		String zzdm = DealString.toGBK(myForm.getZzdm());
		if(!zzdm.equalsIgnoreCase("")){
			bmdm = service.getBmdmForZzdm(zzdm);
			myForm.setBmdm(bmdm);
		}
		request.setAttribute("yrxsgbList", yrxsgbList);
		request.setAttribute("rs", rs);
		request.setAttribute("bmList", service.getBmList());
		request.setAttribute("xszzList", service.getXszzList(bmdm));
		request.setAttribute("xsgbList", service.getXsgbList(zzdm));
		request.setAttribute("writeAble", writeAble);
		return mapping.findForward("xsgbsqOne");	
	}
	
	public ActionForward saveXsgbshOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ѧ������ѧ����֯�ɲ�����
		XsgbForNblgForm myForm              =    (XsgbForNblgForm)  form;
		XsgbForNblgService service          =    new XsgbForNblgService();
		XsgbForNblgModel myModel            =    new XsgbForNblgModel();
		BeanUtils.copyProperties(myModel,myForm);
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.saveXsgbshOne(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xsgbsqOne(mapping, form,request, response);	
	}
		
	public ActionForward printXshzz (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ѧ������֯��ӡ
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		XsgbForNblgService service          =    new XsgbForNblgService();
		HashMap<String, String> rs     =    service.getXshzzxxOne(pk);
		request.setAttribute("rs", rs);
		request.setAttribute("xsgbzlList", service.getXsgbzlList(pk));
		return mapping.findForward("printXshzz");		
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
	private void setFormForXnNdXqNj(XsgbForNblgForm myForm) {
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
}
