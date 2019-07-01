package xgxt.rcsw.zjxy;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.rcsw.RcswForm;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.ExcelMethods;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.basic.BasicService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �㹤��֮��ѧԺ�ճ�����-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ���ΰ
 * @version 1.0
 */

public class RcswZjxyAction extends BasicAction {


	/**
	 * �ճ�����_ʵ�﷢��_��Ŀ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffXmwhManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- ����ֵ ----------------
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_xg_rcsw_swffxmwh";
		// ����
		String realTable = "rcsw_swffxmwhb";
		// ����·��
		String path = "rcsw_swff_xmwh.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// -----------------end-----------------------

		//BeanUtils.copyProperties(model, myForm);
	
		// ----------------ִ��ɾ������ ----------------
		if ("del".equalsIgnoreCase(doType)) {
			String[] pkValue = myForm.getPrimarykey_checkVal();
			if (pkValue != null && pkValue.length > 0) {
				boolean result = service.delSwffXmwh(model, pkValue, realTable);
				//ɾ����Ŀʱ�����Ŀ���Ѿ��������ݾͲ���ɾ����Ŀ
//				if(result){
//					service.delSwffXmwh(pkValue);
//				}
				request.setAttribute("result", result);
			}
		}
		
		
		// -----------------end-----------------------

		// ----------------ִ�в�ѯ���� ----------------
		if (search) {
			StringBuilder query=new StringBuilder();
			
			//���Ž���ʱ������
			if(!"".equalsIgnoreCase(myForm.getKssj())){
				query.append(" and ffjssj>='"+myForm.getKssj()+"' ");
			}
			if(!"".equalsIgnoreCase(myForm.getJssj())){
				query.append(" and ffjssj<='"+myForm.getJssj()+"' ");
			}
			
			request.setAttribute("annexTerm", query);
			String[] outputColumn = { "pk","isDis","xmlxmc" ,"xmmc", "xn", "xqmc","nd", "ffsj","ffjssj",
					"xlqrs"};
			this.selectPageDataByPagination(request, myForm, realTable,
					tableName, outputColumn);
		}else{
			//��ʼ��ҳ������
			myForm.setQueryequals_xn(Base.currXn);
			myForm.setQueryequals_xq(Base.currXq);
			myForm.setQueryequals_nd(Base.currNd);
		}
		// -----------------end-----------------------
		
		// ----------------��ʼ��request��ֵ ----------------
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		

		service.setRequestValue(rForm, request);
		// ----------------end ----------------

		// ----------------��ʼ��request��ֵ ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		return mapping.findForward("swffXmwhManage");
	}
	
	
	/**
	 * �ճ�����_ʵ�﷢��_��Ŀά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffXmwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();
		// ---------------- ����ֵ ----------------
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_rcsw_swffxmwh";
		// ����
		String realTable = "rcsw_swffxmwhb";
		// ����·��
		String path = "rcsw_swff_xmwh.do";
		// ����
		String pk = request.getParameter("pk");
		// -----------------end-----------------------

		HashMap<String, String> rs = new HashMap<String, String>();	

		BeanUtils.copyProperties(model, myForm);
		
		//	-----------------����ʵ�﷢����Ŀά��-----------------------
		if ("save".equalsIgnoreCase(doType)) {
			
			this.insertOperation(request, realTable);
			String xn=myForm.getSave_xn();
			String xq=myForm.getSave_xq();
			String nd=myForm.getSave_nd();
			String xmlx=myForm.getSave_xmlx();
			String ffsj=myForm.getSave_ffsj();
			pk=xn+xq+nd+xmlx+ffsj;
			rs = service.getSwffXmwhInfo(pk);
			rs.put("pk", rs.get("xmlx")+rs.get("xn")+rs.get("xq")+rs.get("nd")+rs.get("ffrq").split("!!@@!!")[0]+rs.get("ffsj"));
		}
		
		if ("modi".equalsIgnoreCase(doType)) {
			
			this.updateOperation(request, realTable);
			
		}
		
		//	-----------------end-----------------------
		
		// ----------------ִ���޸Ļ��߲鿴���� ----------------
		if (("update".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType))
				&& !Base.isNull(pk)) {
			rs = service.getSwffXmwhInfo(pk);
			rs.put("pk", rs.get("xmlx")+rs.get("xn")+rs.get("xq")+rs.get("nd")+rs.get("ffrq").split("!!@@!!")[0]+rs.get("ffsj"));
		}
		// -----------------end-----------------------
		
		// ----------------��ʼ��request��ֵ ----------------
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		myForm.setSave_xn(Base.currXn);
		myForm.setSave_xq(Base.currXq);
		myForm.setSave_nd(Base.currNd);
		
		service.setRequestValue(rForm, request);
		
		request.setAttribute("rs", rs);
		request.setAttribute("xmPkValue", pk);
		// ----------------end ----------------

		// ----------------��ʼ��request��ֵ ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------

		return mapping.findForward("swffXmwhUpdate");
	}

	/**
	 * �ճ�����_ʵ�﷢��_ѧ������
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward swffXsffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ---------------- ����ֵ ----------------
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String doType = request.getParameter("doType");
		String tableName = "view_rcsw_swffxsff";
		String realTable = "rcsw_swffrqwhb";
		String path = "rcsw_swff_xsff.do";
		String mklx = "xs";

		RcswForm myForm = (RcswForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		// ----------------end----------------
		return swffRqwhManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ճ�����_ʵ�﷢��_��ʦ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward swffLsffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ---------------- ����ֵ ----------------
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String doType = request.getParameter("doType");
		String tableName = "view_rcsw_swfflsff";
		String realTable = "rcsw_swffrqwhb";
		String path = "rcsw_swff_lsff.do";
		String mklx = "ls";

		RcswForm myForm = (RcswForm) form;

		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		// ----------------end----------------

		return swffRqwhManage(mapping, myForm, rForm, request, response);
	}

	/**
	 * �ճ�����_ʵ�﷢��_������Ա����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffRqwhManage(ActionMapping mapping, RcswForm myForm,
			RequestForm rForm, 
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- ����ֵ ----------------
		// ��½�û�����
		String userType = rForm.getUserType();
		// ��½�û���
		String userName = rForm.getUserName();
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = rForm.getDoType();
		// ��ͼ��
		String tableName = rForm.getTableName();
		// ����
		String realTable = rForm.getRealTable();
		// ����·��
		String path = rForm.getDoType();
		// ģ������
		String mklx = rForm.getMklx();
		// ����;
		String pk = request.getParameter("pk");
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// -----------------end-----------------------
		
		// ----------------ִ���ύ�������� ----------------
		if ("tj".equalsIgnoreCase(doType)) {
			boolean result = service.setSwffmd(pk, userName);
			request.setAttribute("result", result);
		}
		// -----------------end-----------------------
		
		// ----------------ִ�в�ѯ���� ----------------
		if (search) {
			StringBuilder query=new StringBuilder();
			if(!"".equalsIgnoreCase(myForm.getKssj())){
				query.append(" and ffjssj>='"+myForm.getKssj()+"' ");
			}
			if(!"".equalsIgnoreCase(myForm.getJssj())){
				query.append(" and ffjssj<='"+myForm.getJssj()+"' ");
			}
			String[] outputColumn = { "pk","xmlxmc", "xmmc", "xn", "xqmc", "nd", "ffsj","ffjssj",
					"ffrs" };
			request.setAttribute("annexTerm", query);
			this.selectPageDataByPagination(request, myForm, "",
					tableName, outputColumn);
		}
		// -----------------end-----------------------
		
		// ----------------��ʼ��request��ֵ ----------------
		service.setRequestValue(rForm, request);
		// ----------------end ----------------

		// ----------------��ʼ��request��ֵ ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------

		return mapping.findForward("swffRqwhManage");
	}
	
	/**
	 * �ճ�����_ʵ�﷢��_������Աά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffRqwhUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- ����ֵ ----------------
		String userDep = (String)request.getSession().getAttribute("userDep");
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		doType = Base.isNull(doType) ? "add" : doType;
		// ��ͼ��
		String tableName = "view_xg_rcsw_swffxmwh";
		// ����
		String realTable = "rcsw_swffrqwhb";
		// ����·��
		String path = "rcsw_swff_xmwh.do";
		// ����
		String pk = request.getParameter("pk");
		// ģ������
		String mklx = request.getParameter("mklx");
		// ѧ��ְ����
		String xhzgh = request.getParameter("stuInfo");
		// ��շ�������Ϣ
		if (Base.isNull(xhzgh)) {
			service.delffzxx();
		}
		// -----------------end-----------------------

		HashMap<String, String> rs = new HashMap<String, String>();	

		BeanUtils.copyProperties(model, myForm);
		
		//	-----------------���淢�Ŷ���-----------------------
		if ("save".equalsIgnoreCase(doType) && model.getXhzgh().length>0) {
			
			model.setFfr(userName);
			model.setLx(mklx);
			boolean result = service.saveSwffFfdx(model, realTable);
			request.setAttribute("result", result);
			doType = "ff";
			
		}else if("save".equalsIgnoreCase(doType) && !(model.getXhzgh().length>0)){
			request.setAttribute("result", true);
			doType = "ff";
		}
		//	-----------------end-----------------------
		
		// ----------------ִ���޸Ļ��߲鿴���� ----------------
		List<HashMap<String, String>> topTr = null;
		ArrayList<String[]> rsList = null;
		
		if (("ff".equalsIgnoreCase(doType) || "view".equalsIgnoreCase(doType))
				&& !Base.isNull(pk)) {
			rs = service.getSwffXmwhInfo(pk);
			
			//����
			String zj = "xn||xq||nd||xmlx||ffsj||lx";
			//����ֵ
			String zjValue=pk+mklx;
			//���ű�ע
			String ffbz = service.getOneValue("rcsw_swffrqwhb", "ffbz", zj, zjValue);
			rs.put("ffbz", ffbz);
			
			model.setXn(rs.get("xn"));
			model.setXq(rs.get("xq"));
			model.setNd(rs.get("nd"));
			model.setXmlx(rs.get("xmlx"));
			model.setFfsj(rs.get("ffsj"));
			model.setLx(mklx);
			model.setUserType(userType);
			model.setUserDep(userDep);
			//���Ŷ����б�
			if("xy".equalsIgnoreCase(userType)){
				model.setUserDep(userDep);
			}
			
			if ("xs".equalsIgnoreCase(mklx)) {
				topTr = service.getTopTr("ffdx_xs");
				rsList = service.getXsFfdxList(model);
			} else if ("ls".equalsIgnoreCase(mklx)) {
				topTr = service.getTopTr("ffdx_ls");
				rsList = service.getLsFfdxList(model);
			}	
		}
		// -----------------end-----------------------
		
		// ----------------��ʼ��request��ֵ ----------------
		
		String[] qtzd = new String[] { "pk" };
		String[] qtzdz = new String[] { pk };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setRs(rs);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		rForm.setMklx(mklx);
		
		service.setRequestValue(rForm, request);
		
		request.setAttribute("rs", rs);
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", rsList);
		request.setAttribute("xhzgh", xhzgh);
		// ----------------end ----------------

		// ----------------��ʼ��request��ֵ ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------

		return mapping.findForward("swffRqwhUpdate");
	}
	
	/**
	 * ѧ��������Ϣ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward bffrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- ����ֵ ----------------
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// ��½���Ŵ���
		String userDep = (String) request.getSession().getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_rcsw_swffxmwh";
		// ����
		String realTable = "rcsw_swffxmwhb";
		// ����·��
		String path = "rcsw_swff_xmwh.do";
		// ģ������
		String mklx = request.getParameter("mklx");
		// ������Ⱥ
		String ffrq = request.getParameter("ffrq");
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ---------------- end ----------------
		
		if (userType.equalsIgnoreCase("xy")) {
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		// ------------------ִ�б������---------------------
		if ("ff".equalsIgnoreCase(doType)) {
			String pkValue = request.getParameter("stuInfo");
			String[] xhzgh = pkValue.split("!!@@!!");
			if (xhzgh != null && xhzgh.length > 0) {
				model.setXhzgh(xhzgh);
				model.setLx(mklx);
				service.saveBffzxx(model);
			}
			return swffRqwhUpdate(mapping, form, request, response);
		}
		// ------------------end---------------------
		
		// ------------------ִ�в�ѯ����---------------------
		
		ArrayList<String[]> rsList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		if (search) {
			model.setUserDep(userDep);
			if("xs".equalsIgnoreCase(mklx)){
				topTr = service.getTopTr("ffdx_xs");
				rsList = service.getXsFfdxList(model, ffrq);
			}else if ("ls".equalsIgnoreCase(mklx)){
				topTr = service.getTopTr("ffdx_ls");
				rsList = service.getLsFfdxList(model,ffrq);
			}
		}
		// ------------------end---------------------
		
		// ----------------��ʼ��request��ֵ ----------------

		String[] qtzd = new String[] { "ffrq" };
		String[] qtzdz = new String[] { ffrq };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		request.setAttribute("xn", myForm.getXn());
		request.setAttribute("xq", myForm.getXq());
		request.setAttribute("nd", myForm.getNd());
		request.setAttribute("xmlx", myForm.getXmlx());
		request.setAttribute("ffsj", myForm.getFfsj());
		
		service.setRequestValue(rForm, request);

		if(rsList!=null && rsList.size()>0){
			request.setAttribute("rsNum", rsList.size());
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", rsList);
		
		// ----------------end ----------------

		// ----------------��ʼ��request��ֵ ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		return mapping.findForward("bffrManage");
	}

	/**
	 * �ճ�����_ʵ�﷢��_�����ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffJgcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();
		
		// ---------------- ����ֵ ----------------
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_rcsw_swffxmwh";
		// ����
		String realTable = "rcsw_swffxmwhb";
		// ����·��
		String path = "rcsw_swff_jgcx.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ����Ա
		boolean isAdmin = true;
		
		User user = getUser(request);

		CommService commService = new CommService();
		
		boolean isXy = commService.isXy(user);
		
		List<HashMap<String, String>> topTr = null;
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		BeanUtils.copyProperties(model, myForm);
		
		// -----------------end-----------------------
	
		// ----------------�ж�Ȩ�� ----------------
		if (!"xx".equalsIgnoreCase(userType)&&!"admin".equalsIgnoreCase(userType)) {
			if(!isXy){
				myForm.setZgh(userName);
			}
			isAdmin = false;;
		}
		// -----------------end-----------------------
		
		// ----------------ִ�в�ѯ���� ----------------
		if (search) {
			topTr = service.getTopTr("swff_jg");
			rs = service.getFfjgList(model);
		}
		// -----------------end-----------------------
		
		// ----------------��ʼ��request��ֵ ----------------
		
		String[] qtzd = new String[] { "isAdmin" };
		String[] qtzdz = new String[] { String.valueOf(isAdmin) };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		request.setAttribute("isXy", isXy);
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		
		// ----------------end ----------------

		// ----------------��ʼ��request��ֵ ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		
		return mapping.findForward("swffJgcxManage");
	}
	
	/**
	 * �ճ�����_ʵ�﷢��_��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffFfpjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		
		String tabName="rcsw_swffrqwhb";
		String doType=request.getParameter("doType");
		
		//��������ʵ�﷢��
		if("savePj".equalsIgnoreCase(doType)){
			//����Ŀ�������FORM��
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			//�������
			HashMap<String, String> valueMap = service.setPlpj(myForm);
			this.auditingBatchOperation(request, primaryMap, valueMap, tabName);
			//�������ۺ��ѯ
			doType="query";
		}
		
		//��ѯ
		if("query".equalsIgnoreCase(doType)){
			service.getFfxxByZgh(request, myForm);
		}
		
		service.setList(myForm, request, "swff");
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "rcsw_swff_ffpj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("swffFfpjManage");
	}
	
	public ActionForward swffOnePjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjxyService service = new RcswZjxyService();
		
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");

		String tabName="rcsw_swffrqwhb";
	
		if("pj".equalsIgnoreCase(doType)){		
			this.updateOperation(request, tabName);
		}
		
		
		if(!"".equalsIgnoreCase(doType)){
			HashMap<String,String>hashMap=(HashMap<String,String>)service.getOneFfjl(pkValue);
			hashMap.put("save_mycd", hashMap.get("mycd"));
			hashMap.put("save_pjyj", hashMap.get("pjyj"));
			request.setAttribute("rs", hashMap);
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_ffpj.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("swffOnePjManage");
	}
	
	/**
	 * ���������Ϣͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * author qlj
	 */
	public ActionForward swffPjTjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		RcswZjxyService service = new RcswZjxyService();
		BasicService basicService=new BasicService();
		CommService commService=new CommService();
		
		RcswForm myForm = (RcswForm) form;
		
		String doType=request.getParameter("doType");
		String pkValue=request.getParameter("pkValue");
		String userType=session.getAttribute("userType").toString();
		
		//����Ȩ���ж� ֻ�������Ա��ѧУ�û�����
		if (!"admin".equalsIgnoreCase(userType) && !"xx".equalsIgnoreCase(userType)) {
			request.setAttribute("yhInfo", "�Բ�����û�з��ʱ�ҳ���Ȩ��!");
			return new ActionForward("/yhInfo.do",false);
		}
		
		//��ѯͳ����Ϣ
		if("query".equalsIgnoreCase(doType)){		
			List<String[]> rs = (ArrayList<String[]>) commService
					.getResultList((ArrayList<String[]>) service
							.getPjtjXx(myForm), myForm.getPages());
			request.setAttribute("rs", rs);
			//��ʾ��ͷ
			request.setAttribute("topTr", basicService.getTopTr("mdqr_xmszb",
					new String[] {"��Ŀ����","��Ŀ����","ѧ��","ѧ��","���","����ʼʱ��","�������ʱ��","�ǳ�����",
					"�Ƚ�����","����","������","δ����"}));
		}
		
		//�����������ͳ��
		if("tj".equalsIgnoreCase(doType)){
			String modelPath = servlet.getServletContext().getRealPath("")+"/print/pjpy/hhgxy_pjtj.xls";
			response.reset();
			response.setContentType("application/vnd.ms-excel");
			WritableWorkbook wwb = ExcelMethods.getWritableWorkbook(new File(
					modelPath), response.getOutputStream());
			service.pjxxTj(wwb,myForm);
			
		}
		
		service.setList(myForm, request, "swff");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_pjtj.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffPjTjManage");
	}
	
	/**
	 * ʵ�﷢����Աȷ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffXmffManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		RcswZjxyService service = new RcswZjxyService();
		RcswForm myForm = (RcswForm) form;
		RcswZjxyModel model=new RcswZjxyModel();
		
		String xmPkValue=request.getParameter("xmPkValue");
		String userName=session.getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		String goType=request.getParameter("goType");
		//������ͳ�ƻ��Ƿ���
		String cs=request.getParameter("cs");
		
		if("".equalsIgnoreCase(doType)){
			doType="query";
		}
		
		String pkValue=request.getParameter("pkValue");
		
		String tableName="xsxxb";
		String viewName="view_xg_swff_ffxmtj";
		
		//���淢����Ⱥ��Ϣ
		if("save".equalsIgnoreCase(doType)){
			tableName="xg_swff_ffryb";
			BeanUtils.copyProperties(model, myForm);
			boolean result=service.saveSwffRyxx(model, tableName);
			request.setAttribute("result", result);
			request.setAttribute("dxtzdxList", myForm.getCheckVal());
			doType="view";
		}
		
		//����ɹ��� ���Ͷ���֪ͨ
		if("fsdxtz".equalsIgnoreCase(doType)){
			myForm.setUserName(userName);
			myForm.setDxtzsj(GetTime.getNowTime4());
			request.setAttribute("dxResult",service.saveLqtz(myForm));
			doType="view";
		}
		
		//ֱ��ѡ�����ݷ��Ͷ���֪ͨ
		if("zjfsdxtz".equalsIgnoreCase(doType)){
			
			request.setAttribute("dxResult", service.saveDxtz(myForm));
			doType="view";
		}
		
		//ѡ��Ҫ���ŵ���Ŀ
		if("view".equalsIgnoreCase(doType)){
			tableName="rcsw_swffxmwhb";
			viewName="view_rcsw_swffxmwh";
			this.selectPageDataByOne(request, tableName, viewName, pkValue);
			HashMap<String,String>rsMap=(HashMap<String,String>)request.getAttribute("rs");
			request.setAttribute("xmxxMap", rsMap);
			myForm.setXn(rsMap.get("xn"));
			myForm.setXq(rsMap.get("xq"));
			myForm.setNd(rsMap.get("nd"));
			myForm.setFfsj(rsMap.get("ffsj"));
			myForm.setXmlx(rsMap.get("xmlx"));
			
			//�߼���ѯͳ������
			if("tj".equalsIgnoreCase(cs)){
				//�����ͳ����������,������Ա�ⶨĬ��Ϊ"��"
				myForm.setSfqd("��");
			}else if("ff".equalsIgnoreCase(cs)){
				//�����Ԥ��������������,������Ա�ⶨĬ��Ϊ"��"
				myForm.setSfqd("��");
			}else {
				myForm.setSfqd(null);
			}
			
			request.setAttribute("mrtj", cs);
			request.setAttribute("searchTj", myForm.getSearchModel());
			request.setAttribute("rs", service.getYffry(myForm, request));
		}
		
		//ģ���������
		request.setAttribute("xmPkValue", xmPkValue);
		request.setAttribute("goType", goType);
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		service.setList(myForm, request, "swff");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_xmff.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffFfryManage");
	}
	
	/**
	 * ʵ�﷢����Աȷ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffFfryManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		RcswZjxyService service = new RcswZjxyService();
		RcswForm myForm = (RcswForm) form;
		RcswZjxyModel model=new RcswZjxyModel();
		
		String userName=session.getAttribute("userName").toString();
		String doType=request.getParameter("doType");
		
		String pkValue=request.getParameter("pkValue");
		
		String tableName="xsxxb";
		String viewName="view_xg_swff_ffxmtj";
		
		
		//ͳ�Ʋ�ѯ
		if("query".equalsIgnoreCase(doType)){	
			tableName="xsxxb";
			viewName="view_xg_swff_ffxmtj";
			String[]outputColumn={"pkValue","xh","xm","nj","xymc","zymc","bjmc","ffxm","lqxm"};
			//�ж� ��ѯ��Χ
			if(!"".equalsIgnoreCase(myForm.getCxfw())){
				request.setAttribute("annexTerm", " and lqcs > ffcs ");
			}
			this.selectPageDataByPagination(request, form, tableName, viewName, outputColumn);
		}
		
		
		request.setAttribute("lqcsList", service.getLqcsList());
		request.setAttribute("ffcsList", service.getFfcsList());
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		service.setList(myForm, request, "swff");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_ffry.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffFfrytjManage");
	}
	
	

	/**
	 * ʵ�﷢����Աȷ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffFfrytjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjxyService service = new RcswZjxyService();
		RcswForm myForm = (RcswForm) form;
		BasicService basicService=new BasicService();
		
		String doType=request.getParameter("doType");
		
		String pkValue=request.getParameter("pkValue");
		
		
	
		//ͳ�Ʋ�ѯ
		myForm.setXh(pkValue);
		if("query".equalsIgnoreCase(doType)){	
			request.setAttribute("topTr", basicService.getTopTr("xg_sysz_tpszb",
					new String[] { "ѧ��", "ѧ��", "���","����ʱ��","��Ŀ����","�Ƿ���ȡ","�Ƿ�֪ͨ" }));
			if(service.getFfryTjxx(myForm).size()==0){
					request.setAttribute("yhInfo", "��ѧ��û�з�����Ŀ�ɹ���ѯ��");
					return new ActionForward("/yhInfo.do",false);
			}
			request.setAttribute("rs", service.getFfryTjxx(myForm));
		}
		
		request.setAttribute("doType", doType);
		request.setAttribute("pkValue", pkValue);
	
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("path", "rcsw_swff_ffry.do");
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffFfrytjUpdate");
	}
	
	
	/**
	 * ʵ�﷢�� ������Ա��Ŀѡ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return  ActionForward
	 * @throws Exception
	 * author ��������
	 */
	public ActionForward swffXmxz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswZjxyService service = new RcswZjxyService();
		RcswForm myForm = (RcswForm) form;
		
		String doType=request.getParameter("doType");
		
		String tableName="rcsw_swffxmwhb";
		String viewName="view_xg_rcsw_swffxmwh";
		
		//ʵ�﷢����Ŀ��ѯ
		if ("query".equalsIgnoreCase(doType)){
			StringBuilder query=new StringBuilder();
			if(!"".equalsIgnoreCase(myForm.getKssj())){
				query.append(" and ffjssj>='"+myForm.getKssj()+"' ");
			}
			if(!"".equalsIgnoreCase(myForm.getJssj())){
				query.append(" and ffjssj<='"+myForm.getJssj()+"' ");
			}
			
			request.setAttribute("annexTerm", query);
			String[] outputColumn = { "pkValue","xmlxmc" ,"xmmc", "xn", "xqmc","nd", "ffsj","ffjssj",
					"xlqrs"};//,"lqtz"
			this.selectPageDataByPagination(request, myForm, tableName,
					viewName, outputColumn);
		}else{
			
			myForm.setQueryequals_xn(Base.currXn);
			myForm.setQueryequals_xq(Base.currXq);
			myForm.setQueryequals_nd(Base.currNd);
			
		}
		
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("nd", Base.currNd);
		
	
		service.setList(myForm, request, "swff");
		
		request.setAttribute("path", "rcsw_swff_xmff.do");
		FormModleCommon.commonRequestSet(request);
		
		FormModleCommon.setNdXnXqList(request);
		return mapping.findForward("swffXmxz");
	}
	
	/**
	 * �ճ�����_ʵ�﷢��_���Ž����ѯ
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward swffFfjgManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ---------------- ����ֵ ----------------
		String userType = (String) request.getSession()
				.getAttribute("userType");
		String userName = (String) request.getSession()
				.getAttribute("userName");
		String doType = request.getParameter("doType");
		String tableName = "view_rcsw_swffxsff";
		String realTable = "rcsw_swffrqwhb";
		String path = "rcsw_swff_ffjg.do";
		String mklx = "xs";

		RcswForm myForm = (RcswForm) form;

		RequestForm rForm = new RequestForm();
		rForm.setUserType(userType);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setUserName(userName);
		rForm.setMklx(mklx);
		// ----------------end----------------
		return swffRqwhManage(mapping, myForm, rForm, request, response);
	}
	
	/**
	 * ѧ��������Ϣ����
	 * 
	 * @author luojw
	 * @return ActionForward
	 */
	public ActionForward ffrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		// ---------------- ����ֵ ----------------
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// ��½���Ŵ���
		String userDep = (String) request.getSession().getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_rcsw_swffxmwh";
		// ����
		String realTable = "rcsw_swffxmwhb";
		// ����·��
		String path = "rcsw_swff_xmwh.do";
		// ģ������
		String mklx = request.getParameter("mklx");
		// ������Ⱥ
		String ffrq = request.getParameter("ffrq");
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ---------------- end ----------------
		
		if (userType.equalsIgnoreCase("xy")) {
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		// ------------------ִ�б������---------------------
		if ("ff".equalsIgnoreCase(doType)) {
			String pkValue = request.getParameter("stuInfo");
			String[] xhzgh = pkValue.split("!!@@!!");
			if (xhzgh != null && xhzgh.length > 0) {
				model.setXhzgh(xhzgh);
				model.setLx(mklx);
				service.saveBffzxx(model);
			}
			return swffRqwhUpdate(mapping, form, request, response);
		}
		// ------------------end---------------------
		
		// ------------------ִ�в�ѯ����---------------------
		
		ArrayList<String[]> rsList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		
		if (search) {
			model.setUserDep(userDep);
			topTr = service.getTopTr("ffdx_xs");
			rsList = service.getFfxsList(model);
		}
		// ------------------end---------------------
		
		// ----------------��ʼ��request��ֵ ----------------

		String[] qtzd = new String[] { "ffrq" };
		String[] qtzdz = new String[] { ffrq };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setMklx(mklx);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		request.setAttribute("xn", myForm.getXn());
		request.setAttribute("xq", myForm.getXq());
		request.setAttribute("nd", myForm.getNd());
		request.setAttribute("xmlx", myForm.getXmlx());
		request.setAttribute("ffsj", myForm.getFfsj());
		
		service.setRequestValue(rForm, request);

		if(rsList!=null && rsList.size()>0){
			request.setAttribute("rsNum", rsList.size());
		}
		request.setAttribute("topTr", topTr);
		request.setAttribute("rsList", rsList);
		
		// ----------------end ----------------

		// ----------------��ʼ��request��ֵ ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		return mapping.findForward("bffrManage");
	}


	/**
	 * �ճ�����_ʵ�﷢��_������Աά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffFfjgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();

		String pkValue=request.getParameter("pkValue");
		// ---------------- ����ֵ ----------------
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½���Ŵ���
		String userDep = (String) request.getSession().getAttribute("userDep");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ����·��
		String path = "rcsw_swff_xmwh.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// ---------------- end ----------------
		
		if (userType.equalsIgnoreCase("xy")) {
			model.setXydm(userDep);
			myForm.setXydm(userDep);
		}
		
		BeanUtils.copyProperties(model, myForm);
		
		// ------------------ִ�б������---------------------
		if ("ff".equalsIgnoreCase(doType)) {
			service.saveYffry(request,myForm);
		}
			
		
		HashMap<String,String>xmzj=(HashMap<String,String>)service.getSwffxm(pkValue);
		request.setAttribute("xmzj", xmzj);
		model.setPkValue(pkValue);
		
		// ================��ѯ����================
		ArrayList<String[]> rsList = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = null;
		topTr = service.getTopTr("ffdx_xs");
		rsList = service.getFfxsList(model);
		// ================��ѯ����end================
		
		RequestForm rForm = new RequestForm();
		service.setRequestValue(rForm, request);

		//==================ҳ��������ʾ===================
		//��¼��
		if(rsList!=null && rsList.size()>0){
			request.setAttribute("rsNum", rsList.size());
		}
		//��ͷ
		request.setAttribute("topTr", topTr);
		//�����
		request.setAttribute("rsList", rsList);
		
		// ==================ҳ��������ʾend===================

		// ----------------��ʼ��request��ֵ ----------------
		request.setAttribute("pkValue", pkValue);
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		return mapping.findForward("ffrManage");
	}
	
	/**
	 * �ճ�����_ʵ�﷢��_������Աά��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffDxtzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		RcswForm myForm=(RcswForm)form;
		RcswZjxyService service=new RcswZjxyService();
		String tableName="xg_rcsw_swfflq";
		String viewName="view_xg_rcsw_swfflq";
		String doType=request.getParameter("doType");
		String userName=session.getAttribute("userName").toString();
		
		myForm.setUserName(userName);
		if("dxtzfk".equalsIgnoreCase(doType)){
			service.getDxhz(myForm);
			doType="query";
		}
		
		if("query".equalsIgnoreCase(doType)){
			String[]outputColumn={"pkValue","xhzgh","xm","fsnr","hznr"};
			this.selectPageDataByPagination(request, myForm, tableName, viewName, outputColumn);
		}
		
		request.setAttribute("path", "rcsw_swff_dxtz.do");
		FormModleCommon.commonRequestSet(request);
		//
		service.setList(myForm, request, "dxtz");
		return mapping.findForward("swffDxtzManage");
	}
	
	/**
	 * ��ѧ��Ϊ�������Ŀ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * author qlj
	 */
	public ActionForward swffFfryqrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session=request.getSession();
		BasicService basicService=new BasicService();
		XsxxglService xsxxglService = new XsxxglService();
		RcswZjxyService service=new RcswZjxyService();
		String doType=request.getParameter("doType");
		RcswForm myForm=(RcswForm)form;
		
		RcswZjxyModel model=new RcswZjxyModel();
		BeanUtils.copyProperties(model, myForm);
		
		String userName=session.getAttribute("userName").toString();
		
		myForm.setUserName(userName);
		//��ѯ
		if("query".equalsIgnoreCase(doType)){
			request.setAttribute("topTr", basicService.getTopTr("xg_swff_ffryb",
					new String[] { "��Ŀ����", "��Ŀ����", "ѧ��","ѧ��","���","����ʼʱ��","�������ʱ��"}));
			request.setAttribute("rs",service.getXsFfxm(myForm));
		}
		
		//����
		if("save".equalsIgnoreCase(doType)){
			
			request.setAttribute("result", service.saveFfxm(myForm));
		}
		
		HashMap<String,String>xsxx=new HashMap<String,String>();
		if(!Base.isNull(myForm.getZgh())){
			myForm.setZgh(myForm.getZgh());
			xsxx= xsxxglService.selectStuinfo(myForm.getZgh());
		
			if(xsxx==null || Base.isNull(xsxx.get("xh"))){
				request.setAttribute("message", "�����ѧ�Ų�����,��ʹ�����֤������!");
			}
		}else if(!Base.isNull(myForm.getSfzh())){
			
			xsxx=service.getXhBySfzh(myForm.getSfzh());
			myForm.setZgh(xsxx.get("xh"));
			if(xsxx==null || xsxx.size()==0){
				request.setAttribute("message", "��������֤�Ų�����,��ʹ��ѧ������!");
			}
		}
		request.setAttribute("xsxx", xsxx);
		request.setAttribute("path", "rcsw_swff_ffryqr.do");
		request.setAttribute("xmmcList", service.getXmmcList());
		FormModleCommon.commonRequestSet(request);
		FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("swffFfryqrManage");
	}
	
	
	
	
	/**
	 * ���ݵ���
	 * method expDate
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward expDate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RcswForm myForm = (RcswForm) form;
		String tableName=myForm.getTableName();
		String viewName=myForm.getViewName();
		
		String[]outPut=null;
		if("view_rcsw_swffrqwh".equalsIgnoreCase(viewName)){
			
			String pk=request.getParameter("pk")==null ? "" : request.getParameter("pk");
			if(!"".equalsIgnoreCase(pk) && pk!=null ){
				request.setAttribute("annexTerm", " and xn||xq||nd||xmlx||ffsj='"+pk+"'" );
			}
			outPut=new String[]{"zgh","xm","xn","xqmc","nd","xmmc","ffsj","xymc","zymc","bjmc"};
			
		}else if("view_xg_swff_ffryb".equalsIgnoreCase(viewName)){
				String query=" and sfqd='��' ";
				String xmlx=myForm.getXmlx();
				if(!"".equalsIgnoreCase(xmlx)){
					query+="  and xmlx= '"+xmlx+"' ";
				}
				outPut=new String[]{"xhzgh","xydm","zydm","xymc","zymc","bjdm","bjmc","xm","nj","xmmc","xqmc",
							"xn","xq","nd","ffsj","xmlx"};
				request.setAttribute("annexTerm",query);
		}
		
		
		try {
			this.expPageData(request, response, tableName, viewName, outPut);
		} catch (Exception e) {
			e.printStackTrace();
		}			
		
		return mapping.findForward("success");
	}
	
	/**
	 * �ճ�����_ʵ�﷢��_���ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffJgTjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		RcswForm myForm = (RcswForm) form;
		RcswZjxyService service = new RcswZjxyService();
		RcswZjxyModel model = new RcswZjxyModel();
		
		// ---------------- ����ֵ ----------------
		// ��½�û�����
		String userType = (String) request.getSession().getAttribute("userType");
		// ��½�û���
		String userName = (String) request.getSession().getAttribute("userName");
		// �������ͣ����ӣ��޸ģ�ɾ���ȣ�
		String doType = request.getParameter("doType");
		// ��ͼ��
		String tableName = "view_rcsw_swffxmwh";
		// ����
		String realTable = "rcsw_swffxmwhb";
		// ����·��
		String path = "zjxy_rcsw_swff_jgtj.do";
		// �Ƿ��ѯ����
		boolean search = Base.isNull(request.getParameter("go")) ? false : true;
		// �Ƿ����Ա
		boolean isAdmin = true;
		
		User user = getUser(request);
		
		myForm.setUser(user);
		
		CommService commService = new CommService();
		
		boolean isXy = commService.isXy(user);
		
		List<HashMap<String, String>> topTr = null;
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		BeanUtils.copyProperties(model, myForm);
		
		// -----------------end-----------------------
	
		// ----------------�ж�Ȩ�� ----------------
		if (!"xx".equalsIgnoreCase(userType)&&!"admin".equalsIgnoreCase(userType)) {
			if(!isXy){
				myForm.setZgh(userName);
			}
			isAdmin = false;;
		}
		// -----------------end-----------------------
		
		// ----------------ִ�в�ѯ���� ----------------
		if (search) {
			topTr = service.getTopTr("swff_jgtj");
			rs = service.getFfxxList(myForm);
		}
		// -----------------end-----------------------
		
		// ----------------��ʼ��request��ֵ ----------------
		
		String[] qtzd = new String[] { "isAdmin" };
		String[] qtzdz = new String[] { String.valueOf(isAdmin) };
		
		RequestForm rForm = new RequestForm();

		rForm.setUserType(userType);
		rForm.setUserName(userName);
		rForm.setDoType(doType);
		rForm.setTableName(tableName);
		rForm.setRealTable(realTable);
		rForm.setPath(path);
		rForm.setQtzd(qtzd);
		rForm.setQtzdz(qtzdz);
		
		service.setRequestValue(rForm, request);
		request.setAttribute("isXy", isXy);
		
		FormModleCommon.commonRequestSet(request, tableName, realTable, rs,
				topTr);
		
		// ----------------end ----------------

		// ----------------��ʼ��request��ֵ ----------------
		service.setList(myForm, request, "swff");
		// ----------------end ----------------
		
		
		return mapping.findForward("swffJgTjManage");
	}
	
	/**
	 * �ճ�����_ʵ�﷢��_���ͳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward swffJgTjOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		RcswForm myForm = (RcswForm) form;
		User user = getUser(request);
		myForm.setUser(user);
		DAO dao=DAO.getInstance();
		String cxxx=request.getParameter("cxxx");
		String pk=request.getParameter("pk");
		RcswZjxyService service=new RcswZjxyService();
		String[]colList={"ѧ��","����","�꼶",Base.YXPZXY_KEY,"רҵ","�༶"};
		
		request.setAttribute("cxxx", cxxx);
		request.setAttribute("pk", pk);
		request.setAttribute("topTr", dao.arrayToList(colList, colList));
		request.setAttribute("rsArrList",service.getTjxxOne(myForm));
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		return mapping.findForward("swffJgTjOne");
	}
}
	
