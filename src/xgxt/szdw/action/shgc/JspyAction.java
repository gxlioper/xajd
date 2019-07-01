package xgxt.szdw.action.shgc;

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
import xgxt.szdw.form.shgc.JspyForm;
import xgxt.szdw.model.shgc.JspyModel;
import xgxt.szdw.server.shgc.JspyService;

public class JspyAction extends DispatchAction{
	
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ����˼������-�Ϻ�����-��ʦ����action��</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	

	public ActionForward fdypysq(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    ����Ա��������
		
		JspyForm myForm              =    (JspyForm)  form;
		HttpSession session          =    request.getSession();
		JspyService service          =    new JspyService();
		String puber                 =    session.getAttribute("userName").toString();
		JspyModel myModel            =    new JspyModel();
		String title                 =    DealString.toGBK(request.getParameter("title"));
		
		BeanUtils.copyProperties(myModel,myForm);
		HashMap<String, String> rs = service.getJspySqxx(puber,title,myModel);
		
		if(title.equalsIgnoreCase("")){
			title = "����Ա����";
		}
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("bmList",service.getBmList());
		return mapping.findForward("fdypy");		
	}
	
	public ActionForward saveFdypysq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
	//    ���渨��Ա��������
		JspyForm myForm              =    (JspyForm)  form;
		JspyService service          =    new JspyService();
		JspyModel myModel            =    new JspyModel();
		String title                 =    DealString.toGBK(request.getParameter("title"));
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		setFormForXnNdXq(myForm);
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataFdysq(myModel,title,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return fdypysq(mapping, form,request,response);
	}
	
	public ActionForward fdypyshList(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//    ����Ա��������б�
		HttpSession session          =    request.getSession();
		String userType                 =    session.getAttribute("userType").toString();
		String userDep                 =    session.getAttribute("userDep").toString();
		String tableName     = "view_sxjy_fdypyb";
		String realTable     = "sxjy_fdypyb";
		String title     = "˼�����-ѧ���������齨��-��ʦ����";
		ArrayList<String[]> rs = null;
		JspyForm myForm              =    (JspyForm)  form;
		JspyService service          =    new JspyService();
		JspyModel myModel            =    new JspyModel();
		String xy = myForm.getXydm();
		xy = (xy==null) ? "" : xy;
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getJspyshList(myModel,userType);
		}else {
			setFormForXnNdXq(myForm);
		}
		if(myForm.getPyxm()!=null) {
			myForm.setPyxm(DealString.toGBK(myForm.getPyxm()));
		}
		if(myForm.getXxsh()!=null) {
			myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		}
		List topTr = service.getJspyTopTr();
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("shztList", service.getShztList());
		
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("fdypySh");		
	}
	
	public ActionForward fdypyshOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    ����Ա���ŵ������
		JspyService service          =    new JspyService();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		JspyModel myModel            =    new JspyModel();
		
		JspyForm myForm              =    (JspyForm)  form;
		
		BeanUtils.copyProperties(myModel,myForm);
		HashMap<String, String> rs = service.getJspySqxxForSh(pk);
		String title                 =    rs.get("pyxm");
		
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("bmList",service.getBmList());
		request.setAttribute("shztList",service.getShztList());
		return mapping.findForward("fdypyShOne");		
	}
	
	public ActionForward saveFdypySh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
	//    ���渨��Ա������˽��
		JspyForm myForm              =    (JspyForm)  form;
		JspyService service          =    new JspyService();
		JspyModel myModel            =    new JspyModel();
		String title                 =    DealString.toGBK(request.getParameter("title"));
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		setFormForXnNdXq(myForm);
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataFdysq(myModel,title,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("fdypyShOne");
	}
	
	public ActionForward fdypyReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
	//    ����Ա���ű����ӡ
		JspyService service          =    new JspyService();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		HashMap<String, String> rs   =    service.getJspySqxxForSh(pk);
		String title                 =    rs.get("pyxm");
		request.setAttribute("rs", rs);
		if(title.equalsIgnoreCase("����Ա����")) {
			return mapping.findForward("fdypsb");		
		}else {
			return mapping.findForward("bzrpsb");		
		}
	}
	
	public ActionForward fdypyjgcx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//    ����Ա��������б�
		HttpSession session          =    request.getSession();
		String userType                 =    session.getAttribute("userType").toString();
		String userDep                 =    session.getAttribute("userDep").toString();
		String tableName     = "view_sxjy_fdypyb";
		String realTable     = "sxjy_fdypyb";
		String title     = "˼�����-ѧ���������齨��-��ʦ����";
		ArrayList<String[]> rs = null;
		JspyForm myForm              =    (JspyForm)  form;
		JspyService service          =    new JspyService();
		JspyModel myModel            =    new JspyModel();
		String xy = myForm.getXydm();
		xy = (xy==null) ? "" : xy;
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		BeanUtils.copyProperties(myModel,myForm);
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
				rs = service.getJspyshList(myModel,userType);
		}else {
			setFormForXnNdXq(myForm);
		}
		if(myForm.getPyxm()!=null) {
			myForm.setPyxm(DealString.toGBK(myForm.getPyxm()));
		}
		if(myForm.getXxsh()!=null) {
			myForm.setXxsh(DealString.toGBK(myForm.getXxsh()));
		}
		List topTr = service.getJspyTopTr();
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("shztList", service.getShztList());
		
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("fdypyjgcx");		
	}
	
	private void setFormForXnNdXq(JspyForm myForm) {
	//    �ж�ѧ�꣬��ȣ�ѧ�ں���form��ע��ķ���
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
		}
	}

	private void commonRequestSet(HttpServletRequest request, String tableName, String realTable,
			ArrayList<String[]> rs, List topTr,String title) {
    //    Request��ֵ��ͨ�÷���
		String writeAble    = request.getParameter("writeAble");
		if(writeAble==null){
			   writeAble    = Base.getWriteAble(request);
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
}
