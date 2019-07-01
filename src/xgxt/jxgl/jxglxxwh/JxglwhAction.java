package xgxt.jxgl.jxglxxwh;

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

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.jxgl.JxglDAO;
import xgxt.jxgl.JxglJzService;

public class JxglwhAction extends DispatchAction{
	
	public ActionForward jxglxstjwh(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		//��ѵ�������ά��
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_jxxsttjg";
		String realTable     = "JXXSTJGB";
		String title     = "��ѵ����-��Ϣά��-�������";
		
		ArrayList<String[]> rs = null;
		JxglwhForm myForm              =    (JxglwhForm)  form;
		JxglwhService service          =    new JxglwhService();
		JxglwhModel myModel            =    new JxglwhModel();
		
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
				rs = service.getJxxsTjList(myModel);
		}
		
		setFormForXnNdXqNj(myForm);
		List topTr = service.getJxxsTjTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xqList", Base.getXqList());
		
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("jxglxstjwh");		
	}
	
	public ActionForward jxglxstjwhOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ��ѵ�������ά�������鿴
			String pk                      =    DealString.toGBK(request.getParameter("pk"));
			String xh                      =    DealString.toGBK(request.getParameter("xh"));
			JxglwhService service          =    new JxglwhService();
			HashMap<String, String> rs     =    service.getJxglxsTjOne(pk,xh);
			request.setAttribute("rs", rs);
			request.setAttribute("xnList", Base.getXnndList());
			request.setAttribute("xqList", Base.getXqList());
			return mapping.findForward("jxglxstjwhOne");	
	}
	
	public ActionForward saveJxglxstjwhOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ��ѵ�������ά�������鿴����
		JxglwhForm myForm              =    (JxglwhForm)  form;
		JxglwhService service        =    new JxglwhService();
		JxglwhModel myModel          =    new JxglwhModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataXstj(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("jxglxstjwhOne");	
	}
	
	public ActionForward delJxglxstjOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // ��ѵ����ѧ����쵥��ɾ��
		JxglwhService service        =    new JxglwhService();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteXstjOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return jxglxstjwh(mapping, form,request, response);		
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
	
	private void setFormForXnNdXqNj(JxglwhForm myForm) {
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
	 * ��ʾ��ѵ����������������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward showDrillConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JxglwhService service = new JxglwhService();
//		String xxdm = StandardOperation.getXxdm();
		request.setAttribute("jxmdsfqgx", service.checkJxmdsfqgx());
		return mapping.findForward("success");
	}
	
	/**
	 * �����ѵ������������������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveDrillConf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JxglwhService service = new JxglwhService();
		JxglwhForm model = new JxglwhForm();
		String jxmdsfqgx = request.getParameter("jxmdsfqgx");
		model.setJxmdsfqgx(jxmdsfqgx);
		
		//	================= begin ���ΰ 2009/3/30 ========================
		String xxdm = StandardOperation.getXxdm();
		 if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJCMXY)){
			 String jxll = request.getParameter("jxll");
			 service.saveCjbl(jxll, request);
			 request.setAttribute("xxdm", xxdm);
		}
		//	================= end ���ΰ 2009/3/30 ========================
		request.setAttribute("jxmdsfqgx", jxmdsfqgx);
		request.setAttribute("result", service.saveDrillConfInfo(model, request));
		return mapping.findForward("success");
	}
	
	/**
	 * ��ʦְ���Ų�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jxghSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		request.setAttribute("doType", DealString.toGBK(request.getParameter("doType")));
		request.setAttribute("pkValue", DealString.toGBK(request.getParameter("pkValue")));
		request.setAttribute("xyList", Base.getXyList());
		setList(request);
		return mapping.findForward("success");
	}
	
	/**
	 * ��ѯ��ʦְ������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward infoSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JxglwhService service = new JxglwhService();
		JxglwhForm model = (JxglwhForm)form;
		List list = service.searchJsghInfo(model);
		String doType = DealString.toGBK(request.getParameter("doType"));
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		
		request.setAttribute("topTr", service.getJsghxxTopTr());//��ͷ
		request.setAttribute("rsNum", list.size());
		request.setAttribute("rs", list);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("success");
	}
	
	/**
	 * ҳ����ת
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward forwardPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JxglwhService service = new JxglwhService();
		String jsgh = DealString.toGBK(request.getParameter("jsid"));
		
		request.setAttribute("doType", DealString.toGBK(request.getParameter("doType")));
		request.setAttribute("pkValue", DealString.toGBK(request.getParameter("pkValue")));
		

		// ============ begin ���ΰ 2009/4/1 =================
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			String xb = service.getJsxx(jsgh).get("xb");
			if ("��".equals(xb)) {
				request.setAttribute("xbV", "1");
			} else if ("Ů".equals(xb)) {
				request.setAttribute("xbV", "2");
			} else {
				request.setAttribute("xbV", "");
			}
		}
		// ============ end ���ΰ 2009/4/1 =================
		request.setAttribute("rs", service.getJsxx(jsgh));
		setList(request);
		return mapping.findForward("nblg_ddjsxx");
	}
	
	/**
	 * ��ѵ�Ŷӻ񽱹���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward jxtdhjgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		JxglwhService service = new JxglwhService();
		JxglJzService jxservice = new JxglJzService();
		// ============ begin ���ΰ 2009/5/25 =================
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			List<HashMap<String, String>> ldList = jxservice.getLdList();
			request.setAttribute("ldList", ldList);// �����б�
			request.setAttribute("tableName", "VIEW_NBLG_JXTDHJ");// ��ͼ��
		}else{
			request.setAttribute("ldList", service.getLdList("", ""));//�����б�
			request.setAttribute("tableName","view_jxtdhj");//��ͼ��
		}
		// ============ end ���ΰ 2009/5/25 =================
		request.setAttribute("jxList", service.getJxtdjxList());// �����б�
		request.setAttribute("realTable", "jxtdhjb");// ����

		setList(request);
		return mapping.findForward("jxtdhjgl");
	}
	
	/**
	 * ��ѵ�Ŷӻ񽱹���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward jxtdhjSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		JxglwhService service = new JxglwhService();
		JxglwhForm model = (JxglwhForm) form;

		List list = service.getJxtdhjInfo(model);// ��ѯ�Ŷӻ���Ϣ
		// ���������Ϣ
		JxglJzService jxservice = new JxglJzService();

		// ============ begin ���ΰ 2009/5/25 =================
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			List<HashMap<String, String>> ldList = jxservice.getLdList();
			request.setAttribute("ldList", ldList);
			request.setAttribute("topTr", service.getJxtdhjTopTr());// ��ȡ��ͷ��Ϣ
			request.setAttribute("rs", list);
			request.setAttribute("rsNum", list.size());
			request.setAttribute("jxList", service.getJxtdjxList());// �����б�
			request.setAttribute("realTable", "jxtdhjb");// ����
			request.setAttribute("tableName", "VIEW_NBLG_JXTDHJ");// ��ͼ��
		} else {
			request.setAttribute("topTr", service.getJxtdhjTopTr());// ��ȡ��ͷ��Ϣ
			request.setAttribute("rs", list);
			request.setAttribute("rsNum", list.size());
			request.setAttribute("jxList", service.getJxtdjxList());// �����б�
			request.setAttribute("ldList", service.getLdList("", ""));// �����б�
			request.setAttribute("realTable", "jxtdhjb");// ����
			request.setAttribute("tableName", "view_jxtdhj");// ��ͼ��
		}
		// ============ end ���ΰ 2009/5/25 =================

		setList(request);
		return mapping.findForward("jxtdhjgl");
	}
	
	/**
	 * ��ʾ��ѵ�Ŷӻ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward showJxtdhjAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		JxglwhService service = new JxglwhService();
		// ���������Ϣ
		JxglJzService jxservice = new JxglJzService();
		// ============ begin ���ΰ 2009/5/25 =================
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			List<HashMap<String, String>> ldList = jxservice.getLdList();
			String pkValue = DealString.toGBK(request.getParameter("pkValue"));
			service.getLdList("", "");
			// TODO
			request.setAttribute("rs", service.getJxtdhjByPk(pkValue));
			request.setAttribute("jxList", service.getJxtdjxList());// �����б�
			request.setAttribute("ldList", ldList);// �����б�
			setList(request);// �����б�
			request.setAttribute("doType", "add");
		} else {
			String pkValue = DealString.toGBK(request.getParameter("pkValue"));

			request.setAttribute("rs", service.getJxtdhjByPk(pkValue));
			request.setAttribute("jxList", service.getJxtdjxList());// �����б�
			request.setAttribute("ldList", service.getLdList("", ""));// �����б�
			setList(request);// �����б�
			request.setAttribute("doType", "add");
		}
		// ============ end ���ΰ 2009/5/25 =================
		return mapping.findForward("jxtdhjxx");
	}
	
	/**
	 * ��ʾ��ѵ�Ŷӻ��޸�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * */
	public ActionForward showJxtdhjModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		JxglwhService service = new JxglwhService();
		JxglJzService jxservice = new JxglJzService();
		// ============ begin ���ΰ 2009/5/25 =================
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			List<HashMap<String, String>> ldList = jxservice.getLdList();
			String pkValue = DealString.toGBK(request.getParameter("pkValue"));

			request.setAttribute("rs", service.getJxtdhjByPk(pkValue));
			request.setAttribute("jxList", service.getJxtdjxList());// �����б�
			request.setAttribute("ldList", ldList);// �����б�
			setList(request);// �����б�
			request.setAttribute("doType", "modi");
		} else {
			String pkValue = DealString.toGBK(request.getParameter("pkValue"));

			request.setAttribute("rs", service.getJxtdhjByPk(pkValue));
			request.setAttribute("jxList", service.getJxtdjxList());// �����б�
			request.setAttribute("ldList", service.getLdList("", ""));// �����б�
			setList(request);// �����б�
			request.setAttribute("doType", "modi");
		}
		// ============ end ���ΰ 2009/5/25 =================
		return mapping.findForward("jxtdhjxx");
	}
	
	/**
	 * �����ѵ�Ŷӻ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveJxtdhj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		JxglwhService service = new JxglwhService();
		String doType = DealString.toGBK(request.getParameter("doType"));
		JxglJzService jxservice = new JxglJzService();
		// ============ begin ���ΰ 2009/5/25 =================
		String xxdm = StandardOperation.getXxdm();
		if (xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)) {
			List<HashMap<String, String>> ldList = jxservice.getLdList();
			JxglwhForm model = (JxglwhForm) form;
			model.setDoType(doType);

			request.setAttribute("result", service.saveJxtdhj(model, request));
			request.setAttribute("jxList", service.getJxtdjxList());// �����б�
			request.setAttribute("ldList", ldList);// �����б�
			request.setAttribute("rs", model);
			setList(request);// �����б�
			request.setAttribute("doType", doType);
		} else {
			JxglwhForm model = (JxglwhForm) form;
			model.setDoType(doType);

			request.setAttribute("result", service.saveJxtdhj(model, request));
			request.setAttribute("jxList", service.getJxtdjxList());// �����б�
			request.setAttribute("ldList", service.getLdList("", ""));// �����б�
			request.setAttribute("rs", model);
			setList(request);// �����б�
			request.setAttribute("doType", doType);
		}
		return mapping.findForward("jxtdhjxx");
	}
	
	/**
	 * ɾ����ѵ�Ŷӻ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delJxtdhj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		JxglwhService service = new JxglwhService();
		String pkString = DealString.toGBK(request.getParameter("delPk"));
		
		request.setAttribute("result", service.delJxtdhj(pkString, request));
		return jxtdhjSearch(mapping, form, request, response);
	}
	
	/**
	 * ѧ����ѵ��ѵ�ɲ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward jxxscxgbgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JxglwhService service = new JxglwhService();
		
		request.setAttribute("realTable", "jxxscxgbb");
		request.setAttribute("tableName", "view_jxxscxgb");
		request.setAttribute("ldList", service.getLdList("", ""));
		setList(request);
		return mapping.findForward("xsjxcxgbgl"); 
	}
	
	/**
	 * ѧ����ѵ��ѵ�ɲ���Ϣ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception
	 * */
	public ActionForward jxxscxgbSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JxglwhService service = new JxglwhService();
		JxglwhForm model = (JxglwhForm) form;
		List list = service.getJxxscxgbInfo(model);
		
		request.setAttribute("topTr", service.getJxxscxgbTopTr());
		request.setAttribute("rs", list);
		request.setAttribute("rsNum", list.size());
		request.setAttribute("realTable", "jxxscxgbb");
		request.setAttribute("tableName", "view_jxxscxgb");
		request.setAttribute("ldList", service.getLdList("", ""));
		setList(request);
		return mapping.findForward("xsjxcxgbgl");
	}
	
	/**
	 * ��ʾѧ����ѵ��ѵ�ɲ���Ϣ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward showJxcxgbAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JxglwhService service = new JxglwhService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.getJxcxgbByPk(pkValue));
		request.setAttribute("realTable", "jxxscxgbb");
		request.setAttribute("tableName", "view_jxxscxgb");
		request.setAttribute("doType", "add");
		request.setAttribute("ldList", service.getLdList("", ""));
		setList(request);
		return mapping.findForward("xsjxcxgbxx");
	}
	
	/**
	 * ��ʾѧ����ѵ��ѵ�ɲ���Ϣ�޸�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward showJxcxgbModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		JxglwhService service = new JxglwhService();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		
		request.setAttribute("rs", service.getJxcxgbByPk(pkValue));
		request.setAttribute("realTable", "jxxscxgbb");
		request.setAttribute("tableName", "view_jxxscxgb");
		request.setAttribute("doType", "modi");
		request.setAttribute("ldList", service.getLdList("", ""));
		setList(request);
		return mapping.findForward("xsjxcxgbxx");
	}
	
	/**
	 * ����ѧ����ѵ��ѵ�ɲ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward saveJxcxgbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JxglwhService service = new JxglwhService();
		JxglwhForm model = (JxglwhForm) form;
		String doType = DealString.toGBK(request.getParameter("doType"));
		
		model.setDoType(doType);
		
		request.setAttribute("result", service.saveJxcxgbxx(model, request));
		request.setAttribute("realTable", "jxxscxgbb");
		request.setAttribute("tableName", "view_jxxscxgb");
		request.setAttribute("doType", "modi");
		request.setAttribute("ldList", service.getLdList("", ""));
		setList(request);
		request.setAttribute("rs", service.getJxcxgbByPk(model.getXh()+model.getJxnd()+model.getFzlddm()));
		return mapping.findForward("xsjxcxgbxx");
	}
	
	/**
	 * ɾ��ѧ����ѵ��ѵ�ɲ���Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 * @throws Exception 
	 * */
	public ActionForward delJxcxgb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		JxglwhService service = new JxglwhService();		
		String pkString = DealString.toGBK(request.getParameter("delPk"));
		
		
		request.setAttribute("result", service.deleteJxcxgbxx(pkString, request));
		request.setAttribute("realTable", "jxxscxgbb");
		request.setAttribute("tableName", "view_jxxscxgb");
		request.setAttribute("ldList", service.getLdList("", ""));
		setList(request);
		return jxxscxgbSearch(mapping, form, request, response);
	}
	
	
	/**
	 * @���� ���û����б�
	 * @param request 
	 */
	private void setList(HttpServletRequest request) {
		DAO dao = DAO.getInstance();
		JxglDAO jxglDao = new JxglDAO();
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String userDep = request.getSession().getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
//		String writeAble = Base.getWriteAble(request);
		if ("xy".equalsIgnoreCase(userType)) {
			xydm = userDep;
		}
		request.setAttribute("bzList", jxglDao.getLdList2(Base.currNd));
		request.setAttribute("xnList", dao.getXnndList());
		request.setAttribute("xqList", dao.getXqList());
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());
		request.setAttribute("xbList", dao.getSexList());
		request.setAttribute("mzList", dao.getMzList());
		request.setAttribute("zyList", dao.getZyList(xydm));
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));
		request.setAttribute("writeAble", "yes");//writeAble
	}	
}
