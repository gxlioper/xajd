package xgxt.action.zgkd.sxjy;

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
import xgxt.action.Base;
import xgxt.base.DealString;

public class XssyAction extends DispatchAction{
	
	/**
	* <p>Title: ѧ������ϵͳ</p>
	* <p>Description: ѧ����Ϣ����˼�����-�й����-ѧ������action��</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author ³��
	* @version 1.0
	*/
	

	public ActionForward setXssyxgsj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    ʱ��������ʾ
		XssyService service          =    new XssyService();
		
		HashMap<String, String> rs = service.getSjszList();
	
		request.setAttribute("rs", rs);
		return mapping.findForward("setXssyxgsj");		
	}
	
	public ActionForward saveSyxgsj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    ʱ�����ñ���
		XssyService service          =    new XssyService();
		XssyForm myForm              =    (XssyForm) form;
		XssyModel myModel            =    new XssyModel();
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted             =    service.saveSyxgsj(myModel,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return setXssyxgsj(mapping, form,request,response);
	}
	
	//ѧ�����������ҳ
	public ActionForward xssysj(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    ѧ���������
		HttpSession session        = request.getSession();
		String userType            = (String) session.getAttribute("userType");
		String xh                  = (String) session.getAttribute("userName");
		String xn                  = (String) session.getAttribute("LoginXn");
		String pk                  = DealString.toGBK(request.getParameter("pk"));
		XssyService service        =    new XssyService();
	//	  �ж��Ƿ���ѧ��
		
		String view =  DealString.toGBK(request.getParameter("view"));
		
		if(!userType.equalsIgnoreCase("stu")&&pk.equalsIgnoreCase("")) {
			request.setAttribute("errMassage", "��ģ��ֻ����ѧ����д");
			return mapping.findForward("xssysj");		
		}else if (!pk.equalsIgnoreCase("")){
			xh = pk;
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = service.getXssysj(xh, xn,view);
		
		//�鿴ѧ��������Ƶı�����û��ѧ��������
		if(service.isStuAlreadyWriteSj(xh)){
			request.setAttribute("alreadyWrite", "yes");
		}else{
			request.setAttribute("alreadyWrite", "no");
		}
		if(!userType.equalsIgnoreCase("stu")) {
			if (!pk.equalsIgnoreCase("")){
				rs.put("jnjs", "js"+((rs.get("jnjs") == null) ? "" : rs.get("jnjs")));  //����Ա
			}
		}else if (userType.equalsIgnoreCase("stu")){
			rs.put("jnjs", ((rs.get("jnjs") == null) ? "" : rs.get("jnjs")));
		}
		if (!pk.equalsIgnoreCase("")){
			
		}
		if(null!=rs.get("errMassage")) {
			request.setAttribute("errMassage", rs.get("errMassage"));
			return mapping.findForward("xssysj");		
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("xssysj");		
	}
	
	public ActionForward xssysjSave(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    ѧ��������Ʊ���
		HttpSession session        = request.getSession();
		String userName            = (String) session.getAttribute("userNameReal");
		
		XssyForm myForm            =    (XssyForm) form;
		XssyService service        =    new XssyService();
		XssyModel myModel          =    new XssyModel();
		BeanUtils.copyProperties(myModel,myForm);
		String xh = request.getParameter("pk");
		String jnjs = request.getParameter("jnjs");
		boolean inserted = service.saveXssysj(xh,jnjs,myModel,userName,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return xssysj(mapping, form,request,response);		
	}
	
	public ActionForward fdyhf(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //����Ա�ظ�
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_jskd_xssyxxb";
		String realTable     = "sxjy_jskd_xssyxxb";
		//String title     = "˼�����-ѧ������-����Ա�鿴�ظ�";
		String title     = "˼�����-ѧ������-ѧ�����Ĳ�ѯ";
		ArrayList<String[]> rs = null;
		XssyForm myForm              =    (XssyForm) form;
		XssyService service          =    new XssyService();
		XssyModel myModel            =    new XssyModel();
		
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
				//rs = service.getXssyList(myModel);
			rs = service.getFilterList(myModel);
		}
		
		List topTr = service.getXsxyTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		
		
		request.setAttribute("filterList", service.getFilterCondi());
		
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("xssycx");		
}
	private void commonRequestSet(HttpServletRequest request, String tableName, String realTable,
			ArrayList<String[]> rs, List topTr,String title) {
    //Request��ֵ��ͨ�÷���
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
	
	public ActionForward xssysjReqort(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //    ѧ��������Ʊ���
		String page                = DealString.toGBK(request.getParameter("page"));
		String pk                  = DealString.toGBK(request.getParameter("pk"));
		XssyService service        =    new XssyService();
		HashMap<String, String> rs = service.getXssysjAll(pk);
		request.setAttribute("rs", rs);
		return mapping.findForward("xssyReport"+page);		
	}

	//�й����Ա��Ϣ
	public ActionForward dyxx(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    //����Ա�ظ�
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();
		String tableName     = "view_zgkd_dyxx";
		String realTable     = "zgkd_dyxxb";
		//String title     = "˼�����-ѧ������-����Ա�鿴�ظ�";
		String title     = "˼�����-��Ϣά��-��Ա��Ϣ";
		ArrayList<String[]> rs = null;
		XssyForm myForm              =    (XssyForm) form;
		XssyService service          =    new XssyService();
		XssyModel myModel            =    new XssyModel();
		
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
			String isBy = DealString.toGBK(request.getParameter("by"));
				// rs = service.getXssyList(myModel);
			if ("noBy".equals(isBy)) {
				rs = service.getDyxxList(myModel,myForm);
			}
			if ("isBy".equals(isBy)) {
				rs = service.getByDyxxList(myModel,myForm);
			}
		}
		
		List topTr = service.getDyxxTopTr();
		request.setAttribute("xydm", xy);
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("njList", Base.getNjList());
		
		
		request.setAttribute("xzztList", service.getXzztList());
		
		commonRequestSet(request, tableName, realTable, rs, topTr,title);
		return mapping.findForward("dyxx");		
}
	
	public ActionForward dyxxOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		// ��Ա��Ϣ�����鿴
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		String xh                      =    DealString.toGBK(request.getParameter("xh"));
		String db                      =    DealString.toGBK(request.getParameter("db"));
		DAO dao=new DAO();
		if(pk!=null&&!"".equals(pk)){
			request.setAttribute("add", "add");
		}
		if(db!=null&&!"".equals(db)){
			request.setAttribute("db", "db");
		}
		XssyService service          =    new XssyService();
		HashMap<String, String> rs     =    service.getDyxxOne(pk,xh);
		String rdsqsj = dao.getOneRs(
				"select t.djsqsj from zgkd_rdsqb t where t.xh = ?",
				new String[] { xh }, "djsqsj");
		String qdwjjfzsj = dao.getOneRs(
				"select t.rdjjfzsj from zgkd_rdsqb t where t.xh = ?",
				new String[] { xh }, "rdjjfzsj");
		String fzdxsj = dao.getOneRs(
				"select t.fzdxsj from zgkd_rdsqb t where t.xh = ?",
				new String[] { xh }, "fzdxsj");
		rs.put("rdsqsj", rdsqsj);
		rs.put("qdwjjfzsj", qdwjjfzsj);
		rs.put("fzdxsj", fzdxsj);
		request.setAttribute("rs", rs);
		request.setAttribute("xzztList", service.getXzztList());

		return mapping.findForward("dyxxOne");	
	}
	
	public ActionForward saveDyxxOne (ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
	    // ��ѵ�������ά�������鿴����
		XssyForm myForm              =    (XssyForm)  form;
		XssyService service          =    new XssyService();
		XssyModel myModel            =    new XssyModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataDyxx(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("dyxxOne");	
	}
	
	public ActionForward delRtjjfzOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
    // ���Ż������ӵ���ɾ��
		XssyService service          =    new XssyService();	
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		boolean inserted            =    service.deleteDyxxOne(pk,request);
		if(inserted){
			request.setAttribute("delete", "ok");
		}else{
			request.setAttribute("delete", "no");
		}
		return dyxx(mapping, form,request, response);		
	}
	
	public ActionForward ybdyzz(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
			XssyService service          =    new XssyService();
			
			List topTr = service.getYbdyzzTorList();
			ArrayList<String[]> rs = null;
			rs = service.getYbdyzzList();
			if(rs!=null){
				request.setAttribute("rsNum", rs.size());
			}
			request.setAttribute("rs", rs);
			request.setAttribute("topTr", topTr);
			return mapping.findForward("ybdyzz");	
	}
	
	public ActionForward ybdyzzOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
//		��Ա��Ϣ�����鿴
		String pk                      =    DealString.toGBK(request.getParameter("pk"));
		String xh                      =    DealString.toGBK(request.getParameter("xh"));
		XssyService service          =    new XssyService();
		HashMap<String, String> rs     =    service.getDyxxOne(pk,xh);
		request.setAttribute("rs", rs);
		request.setAttribute("xzztList", service.getXzztList());
		return mapping.findForward("ybdyzzOne");	
	}
	
	public ActionForward saveYbdyzzOne(ActionMapping mapping, ActionForm form,HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		XssyForm myForm              =    (XssyForm)  form;
		XssyService service          =    new XssyService();
		XssyModel myModel            =    new XssyModel();
		String pk                    =    DealString.toGBK(request.getParameter("pk"));
		BeanUtils.copyProperties(myModel,myForm);
		boolean inserted            =    service.updataYbdyzz(myModel,pk,request);
		if(inserted){
			request.setAttribute("inserted", "ok");
		}else{
			request.setAttribute("inserted", "no");
		}
		return mapping.findForward("ybdyzzOne");
	}
	
//	�й�����뵳����
	public ActionForward rdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String realTable = "zgkd_rdsqb";
		String tableName = "view_zgkd_rdsq";
		String title = "���Ž���-����ά��-�뵳����";

		ArrayList<String[]> rs = null;
		XssyForm myForm = (XssyForm) form;
		XssyService service = new XssyService();
		XssyModel myModel = new XssyModel();

		// ȡ�ò�ѯ����
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		String pk = "xh";
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// ȡ�ñ�ͷ
			topTr = service.getRdsqTopTr();
			// ȡ�ò�ѯ���
			rs = service.getRdsqList(myModel, myForm);
			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�		
		request.setAttribute("ndList", Base.getXnndList());// ���5��������б�
		commonRequestSet(request, tableName, realTable, rs, topTr, title);
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", "partyApply");// �������ݲ�ѯ����

		return mapping.findForward("rdsq");
	}
	
	public ActionForward delRdsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userDep = session.getAttribute("userDep").toString();

		String realTable = "zgkd_rdsqb";
		String tableName = "view_zgkd_rdsq";
		String title = "���Ž���-����ά��-�뵳����";

		ArrayList<String[]> rs = null;
		XssyForm myForm = (XssyForm) form;
		XssyService service = new XssyService();
		XssyModel myModel = new XssyModel();

		// ȡ�ò�ѯ����
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		String pk = "xh";

		// �������
		String xh = DealString.toGBK(request.getParameter("pk"));

		// ɾ����չ����
		String[] keys = xh.split("!!SplitSignOne!!");
		// ɾ���������
		String del = service.delRdsq(keys);

		// boolean del = service.delSwcl(xh, request);
		// �ж�ɾ���Ƿ�ɹ�
		if (del == null || "".equals(del)) {
			request.setAttribute("del", "ok");
		} else {
			request.setAttribute("del", "no");
			request.setAttribute("delRdsq", del);
		}
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
			xy = userDep;
			myForm.setXydm(xy);
		}
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;

		String bjKey = xy + "!!" + zy + "!!" + nj;
		List topTr = null;

		BeanUtils.copyProperties(myModel, myForm);
		// �����ѯ��ť���в�ѯ
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			// ȡ�ñ�ͷ
			topTr = service.getRdsqTopTr();
			// ȡ�ò�ѯ���
			rs = service.getRdsqList(myModel, myForm);
			// ҳ�淵�ر�֤����������
			myForm.setXm(DealString.toGBK(myForm.getXm()));
		}

		request.setAttribute("xydm", xy);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		request.setAttribute("xnList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xqList", Base.getXqList());// ����ѧ���б�		
		request.setAttribute("ndList", Base.getXnndList());// ���5��������б�
		commonRequestSet(request, tableName, realTable, rs, topTr, title);
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("act", "partyApply");// �������ݲ�ѯ����
		
		return mapping.findForward("rdsq");
	}
}