 package xgxt.dtjs.zjjj.action;

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
import xgxt.dtjs.zjjj.form.TxgzForm;
import xgxt.dtjs.zjjj.model.TxgzModel;
import xgxt.dtjs.zjjj.server.TxgzService;
import xgxt.pjpy.xbemy.PjpyXbemyDAO;

public class TxgzAction extends DispatchAction{
	
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ������ѧ-action��</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	
	public ActionForward tzzgb(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	    ����֯�ɲ�
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_tzzgb";
		String realTable     = "tzzgbb";
		
		ArrayList<String[]> rs = null;
		TxgzForm myForm              =    (TxgzForm)  form;
		TxgzService service          =    new TxgzService();
		TxgzModel myModel            =    new TxgzModel();
		
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
				rs = service.getTzzgbList(myModel);
		}
		
		List topTr = service.getTzzgbTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("zwList", service.getZwList());
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("tzzgb");		
	}
	
	public ActionForward tzzgbOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ���Ż������ӵ����鿴
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			String xh                      =    DealString.toGBK(request.getParameter("xh"));
			String writeAble               =    DealString.toGBK(request.getParameter("writeAble"));
			TxgzService service          =    new TxgzService();
			HashMap<String, String> rs     =    service.getTzzgbOne(pk,xh);
			request.setAttribute("writeAble", writeAble);
			request.setAttribute("rs", rs);
			request.setAttribute("zwList", service.getZwList());
			return mapping.findForward("tzzgbOne");	
	}
	
	public ActionForward saveTzzgbOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ��ѵ�������ά�������鿴����
		TxgzForm myForm              =    (TxgzForm)  form;
		TxgzService service          =    new TxgzService();
		TxgzModel myModel            =    new TxgzModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataTzzgb(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("tzzgbOne");	
	}
	
	public ActionForward delTzzgbOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // ���Ż������ӵ���ɾ��
		TxgzService service          =    new TxgzService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteTzzgbOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return tzzgb(mapping, form,request, response);		
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
	
	public ActionForward tytysbsh(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception {
		TxgzForm myForm  = (TxgzForm) form;
		String xydm 		   		= "";
		String zydm 		   		= "";
		String nj   		   		= "";
		HttpSession session    		= request.getSession();
		String bmdm            		= session.getAttribute("userDep").toString();//�û����ڲ���
//		String userName             = session.getAttribute("userName").toString();
		String userType        		= session.getAttribute("userType").toString();//�û�����
		TxgzService service   = new TxgzService();
//		�û���ѧԺ�����Ա��û����˵�λ�ķ���
		if("xy".equalsIgnoreCase(userType)){
			xydm = bmdm;
			myForm.setXydm(xydm);
		} 
		appendProperities(request,xydm,zydm,nj);
		request.setAttribute("userType", session.getAttribute("userType"));
		
		TxgzModel myModel = new TxgzModel();
		BeanUtils.copyProperties(myModel,myForm);//��form�е�pjpyModel�ֶ�ֵ���Ƶ�pjpyModel��
		List topTr = service.getSbSearchTitle();//�����û���������ȡ��ͬ��TIP
		ArrayList<String[]> rs = null;
		if ((request.getParameter("go") != null)&& request.getParameter("go").equalsIgnoreCase("go")) {	
			if (userType.equalsIgnoreCase("xy")){//ѧԺ�û���ѯ���
				rs = service.getXySbSearchResult(myModel);
			}else{//ѧ���������񴦣����񴦺͹���Ա��ѯ���
				rs = service.getXxShSearchResult(myModel);
			}
		}
		commonRequestSet(request,"", "", rs,topTr);//����ҳ��title
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("rsNum", rs != null ? rs.size():0);
		return mapping.findForward("tytysbsh");
	}

	
	@SuppressWarnings("unused")
	private void setFormForXnNdXqNj(TxgzForm myForm) {
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
	 * ��request������������Ӧ����Ŀ:xnList,njList,xyList,zyList,bjList
	 * @param request
	 */
	private void appendProperities(HttpServletRequest request,String xydm,String zydm,String nj){
		PjpyXbemyDAO dao = new PjpyXbemyDAO();
		String xy = "";
		String zy = "";
		String njLocal = nj;
		xy = xy==null ? "": xydm; 
		zy = zy==null ? "": zydm; 
		njLocal = nj==null ? "": njLocal;
		
		String zyKey = xy==null ? "":xy; 
		String bjKey = xy+"!!"+zy+"!!"+njLocal;
		
		request.setAttribute("writeAble", "yes");
		request.setAttribute("jxjList", dao.getJxjList());
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", Base.getZyMap().get(zyKey));
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));
	}
	
	
}
