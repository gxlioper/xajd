package xgxt.szdw.action.zjjj;

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
import xgxt.szdw.form.zjjj.XsgzglForm;
import xgxt.szdw.model.zjjj.XsgzglModel;
import xgxt.szdw.server.zjjj.XsgzdwService;

public class XsgzdwAction extends DispatchAction{
	
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ����˼������-�Ϻ�����-��ʦ����action��</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	public ActionForward fgldxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	   �ֹ��쵼��Ϣ
		String tableName     = "view_fgldxx";
		String realTable     = "fgldxx";
		
		ArrayList<String[]> rs = null;
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String xy = myForm.getXydm();
		
		xy = (xy==null) ? "" : xy;
		
		BeanUtils.copyProperties(myModel,myForm);
		rs = service.getXsgzdwList(myModel);
		
		List topTr = service.getXsgzdwTopTr();
		commonRequestSet(request, tableName, realTable, rs, topTr);
		request.setAttribute("zwList", service.getZwList());
		return mapping.findForward("fgldxx");				
	}
	
	public ActionForward fgldxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�ֹ��쵼��Ϣ
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			XsgzdwService service          =    new XsgzdwService();
			HashMap<String, String> rs     =    service.getXsgzdwOne(pk);
			request.setAttribute("zwList", service.getZwList());
			request.setAttribute("rs", rs);
			return mapping.findForward("fgldxxOne");	
	}
	
	public ActionForward saveFgldxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�ֹ��쵼��Ϣ
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataFgldxx(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("fgldxxOne");	
	}
	
	public ActionForward delFgldxxOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�ֹ��쵼��Ϣ
		XsgzdwService service          =    new XsgzdwService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteFgldxxOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return fgldxx(mapping, form,request, response);		
	}

	public ActionForward xgbryxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	   ѧ������Ա��Ϣ
		String tableName     = "view_xgbryxx";
		String realTable     = "xgbryxx";
		
		ArrayList<String[]> rs = null;
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String xy = myForm.getXydm();
		
		xy = (xy==null) ? "" : xy;
		
		BeanUtils.copyProperties(myModel,myForm);
		rs = service.getXgbryxxList(myModel);
		
		List topTr = service.getXgbryxxTopTr();
		commonRequestSet(request, tableName, realTable, rs, topTr);
		request.setAttribute("zwList", service.getZwList());
		return mapping.findForward("xgbryxx");				
	}
	
	public ActionForward xgbryxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//ѧ������Ա��Ϣ
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			XsgzdwService service          =    new XsgzdwService();
			HashMap<String, String> rs     =    service.getXgbryxxOne(pk);
			request.setAttribute("zwList", service.getZwList());
			request.setAttribute("rs", rs);
			return mapping.findForward("xgbryxxOne");	
	}
	
	public ActionForward saveXgbryxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//ѧ������Ա��Ϣ
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataXgbryxx(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("xgbryxxOne");	
	}
	
	public ActionForward delXgbryxxOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//ѧ������Ա��Ϣ
		XsgzdwService service          =    new XsgzdwService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteXgbryxxOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return xgbryxx(mapping, form,request, response);		
	}
	
	public ActionForward zlbzrxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	   �����������Ϣ
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_zlbzrxx";
		String realTable     = "zlbzrxx";
		
		ArrayList<String[]> rs = null;
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		
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
		rs = service.getZlbzrxxList(myModel);
		
		List topTr = service.getZlbzrxxTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("zlbzrxx");				
	}
	
	public ActionForward zlbzrxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�����������Ϣ
			HttpSession session = request.getSession();
			String userType = session.getAttribute("userType").toString();
			String userDep = session.getAttribute("userDep").toString();
			XsgzglForm myForm              =    (XsgzglForm)  form;
			String bj = DealString.toGBK(myForm.getBjdm());
			String zlsf = DealString.toGBK(myForm.getZlsf());
			XsgzdwService service          =    new XsgzdwService();
			String nj = myForm.getNj();
			String xy = myForm.getXydm();
			String zy = myForm.getZydm();
			
			xy = (xy==null) ? "" : xy;
			zy = (zy==null) ? "" : zy;
			bj = (bj==null) ? "" : bj;
			nj = (nj==null) ? "" : nj;
			
			if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
				xy = userDep;
				myForm.setXydm(xy);
			}
			String bjKey = xy+"!!"+zy+"!!"+nj;
			if(zlsf.equalsIgnoreCase("����ѧ��")&&!bj.equalsIgnoreCase("")){
				request.setAttribute("sfbbxs", "yes");
				request.setAttribute("bjxsList", service.getbjxsList(bj));
			}else{
				request.setAttribute("sfbbxs", "no");
			}
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			
			HashMap<String, String> rs     =    service.getZlbzrxxOne(pk);
			if(!bj.equalsIgnoreCase("")){
				rs.put("xydm",xy);
				rs.put("zydm",zy);
				rs.put("bjdm",bj);
				rs.put("zlsf",zlsf);
			}
			request.setAttribute("rs", rs);
			request.setAttribute("xydm", xy);
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("zyList", (Base.getZyMap()).get(xy));
			request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
			request.setAttribute("njList", Base.getNjList());
			return mapping.findForward("zlbzrxxOne");	
	}
	
	public ActionForward saveZlbzrxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�����������Ϣ
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataZlbzrxx(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("zlbzrxxOne");	
	}
	
	public ActionForward delZlbzrxxOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�����������Ϣ
		XsgzdwService service          =    new XsgzdwService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteZlbzrxxOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return xgbryxx(mapping, form,request, response);		
	}
	
	public ActionForward dzzsj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	   ����֧���
		String tableName     = "view_dzzsj";
		String realTable     = "dzzsjb";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		ArrayList<String[]> rs = null;
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String xy = myForm.getXydm();
		
		if (userType.equalsIgnoreCase("xy")&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy==null) ? "" : xy;
		
		BeanUtils.copyProperties(myModel,myForm);
		rs = service.getDzzsjList(myModel);
		
		List topTr = service.getDzzsjTopTr();
		request.setAttribute("xydm", xy);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		request.setAttribute("xyList", Base.getXyList());
		return mapping.findForward("dzzsj");				
	}
	
	public ActionForward dzzsjOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//����֧���
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			XsgzdwService service          =    new XsgzdwService();
			HashMap<String, String> rs     =    service.getDzzsjOne(pk);
			request.setAttribute("xyList", Base.getXyList());
			request.setAttribute("rs", rs);
			return mapping.findForward("dzzsjOne");	
	}
	
	public ActionForward saveDzzsjOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//����֧���
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataDzzsj(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("dzzsjOne");	
	}
	
	public ActionForward delDzzsjOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//����֧���
		XsgzdwService service          =    new XsgzdwService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteDzzsjOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return dzzsj(mapping, form,request, response);		
	}
	
	public ActionForward xshzzxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	    ѧ������֯��Ϣ
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_xshzzxx";
		String realTable     = "xshzzxxb";
		
		ArrayList<String[]> rs = null;
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		
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
				rs = service.getXshzzxxList(myModel);
		}
		
		List topTr = service.getXshzzxxTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xqList", Base.getXqList());
		
		commonRequestSet(request, tableName, realTable, rs, topTr);
		return mapping.findForward("xshzzxx");		
	}
	
	public ActionForward xshzzxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ѧ������֯��Ϣ�����鿴
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			String xh                      =    DealString.toGBK(request.getParameter("xh"));
			XsgzdwService service          =    new XsgzdwService();
			HashMap<String, String> rs     =    service.getXshzzxxOne(pk,xh);
			request.setAttribute("rs", rs);
			request.setAttribute("zwList", service.getXshzw());
			return mapping.findForward("xshzzxxOne");	
	}
	
	public ActionForward saveXshzzxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ѧ������֯��Ϣ�����鿴����
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataXshzzxx(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("xshzzxxOne");	
	}
	
	public ActionForward delXshzzxxOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // ѧ������֯��Ϣ����ɾ��
		XsgzdwService service          =    new XsgzdwService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteXshzzxxOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return xshzzxx(mapping, form,request, response);		
	}
	
	//������˼������
	public ActionForward nblgSzdwxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	  ������˼������
		String tableName     = "view_dzzsj";
		String realTable     = "dzzsjb";
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		
		ArrayList<String[]> rs = null;
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String bm = myForm.getBmdm();
		
		if (userType.equalsIgnoreCase("xy")&& (bm == null || bm.equalsIgnoreCase(""))) {
			bm = userDep;
			myForm.setBmdm(bm);
		}
		bm = (bm==null) ? "" : bm;
		
		BeanUtils.copyProperties(myModel,myForm);
		rs = service.getSzdwxxList(myModel);
		
		List topTr = service.getSzdwxxTopTr();
		request.setAttribute("bmdm", bm);
		commonRequestSet(request, tableName, realTable, rs, topTr);
		request.setAttribute("bmList", service.getBmList());
		request.setAttribute("SzdwzyjsdjList", service.getSzdwzyjsdjb());
		request.setAttribute("SzdwzwdmList", service.getSzdwzwdmb());
		request.setAttribute("SzdwrylbList", service.getSzdwrylb());
		return mapping.findForward("nblgSzdwxx");				
	}
	
	public ActionForward nblgSzdwxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//������˼������
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			XsgzdwService service          =    new XsgzdwService();
			HashMap<String, String> rs     =    service.getNblgSzdwxxOne(pk);
			request.setAttribute("bmList", service.getBmList());
			request.setAttribute("SzdwzyjsdjList", service.getSzdwzyjsdjb());
			request.setAttribute("SzdwzwdmList", service.getSzdwzwdmb());
			request.setAttribute("SzdwrylbList", service.getSzdwrylb());
			request.setAttribute("rs", rs);
			return mapping.findForward("nblgSzdwxxOne");	
	}
	
	public ActionForward saveNblgSzdwxx (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//������˼������
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataNblgSzdwxxOne(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("nblgSzdwxxOne");	
	}
	
	public ActionForward delNblgSzdwxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//����֧���
		XsgzdwService service          =    new XsgzdwService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteNblgSzdwxxOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return dzzsj(mapping, form,request, response);		
	}
	
	public ActionForward bjqkxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		//�༶�����Ϣ
		XsgzdwService service          =    new XsgzdwService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteNblgSzdwxxOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return dzzsj(mapping, form,request, response);		
	}
	
	public ActionForward bjxxqk(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
	//	   �༶��ϸ��� 
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_bjxxxx";
		
		ArrayList<String[]> rs = null;
		XsgzglForm myForm              =    (XsgzglForm)  form;
		XsgzdwService service          =    new XsgzdwService();
		XsgzglModel myModel            =    new XsgzglModel();
		
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
			rs = service.getBjxxqkList(myModel);
		}
		
		List topTr = service.getBjxxqkTopTr();
		myForm.setSffp(DealString.toGBK(myForm.getSffp()));
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("njList", Base.getNjList());
		commonRequestSet(request, tableName, "", rs, topTr);
		return mapping.findForward("bjqkxx");				
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
	private void setFormForXnNdXqNj(XsgzglForm myForm) {
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
