package xgxt.pjpy.xmlg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

public class PjpyXmlgAction extends CommonAction {

	private static final String SAVE = "save";
	private static final String QUERY = "query";
//	private static final String DELETE = "delete";
	private static final String VIEW = "view";
	
	/**
	 * �ۺ����ʲ���ά��Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zhszcpwhDefault(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		PjpyXmlgModel userDepModel = getXybmxx(request);
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
		}
		
		PjpyXmlgService service = new PjpyXmlgService();
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryZhszcpTitle();
			rs = (ArrayList<String[]>)service.queryZhszcpResult(model);
		}
		
		request.setAttribute("path", "data_search.do?act=zhsz");
		FormModleCommon.commonRequestSet(request, "view_zhszcp", "zhszcp", rs, topTr);
		try {
			setNjXyZyBjList(request, xmlgForm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("zhszcpwhDefault");
	}
	
	/**
	 * �ۺ����ʲ���ɾ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward zhszcpwhDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		// ɾ���ۺ����ʲ�����Ϣ
		boolean result = service.deleteZhszcpxx(model);
		appendOperResult(request, result);
		zhszcpwhDefault(mapping, form, request, response);
		return mapping.findForward("zhszcpwhDefault");
	}
	
	/**
	 * �����ۺ����ʲ����ɼ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward addZhszcpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		PjpyXmlgService service = new PjpyXmlgService();
		String xh = request.getParameter("xh");
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(xh)) {
			rs = CommonQueryDAO.getStuInfo(xh);
		}
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.addZhszcpxx(model);
			appendOperResult(request, result);
		}
		
		setNjXyZyBjList(request, xmlgForm);
		request.setAttribute("rs", rs);
		return mapping.findForward("addZhszcpxx");
	}
	
	/**
	 * �޸��ۺ����ʲ����ɼ�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiZhszcpxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		String pkValue = request.getParameter("pkValue");
		PjpyXmlgService service = new PjpyXmlgService();
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			model.setPkValue(pkValue);
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.updateZhszcpxx(model);
			appendOperResult(request, result);
		} else if (VIEW.equalsIgnoreCase(xmlgForm.getOperType())) {
			request.setAttribute("writable", "no");
		}
		
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		HashMap<String, String> rs = service.queryZhszcpOnexx(queryModel);
		if (rs != null) {
			xmlgForm.setXn(rs.get("xn"));
			xmlgForm.setXq(rs.get("xq"));
			xmlgForm.setDcj(rs.get("dcj"));
			xmlgForm.setZcj(rs.get("zcj"));
			xmlgForm.setTcj(rs.get("tcj"));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("modiZhszcpxx");
	}
	
	/**
	 * �����۲�������ʽ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward szZhszcppm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userModel = getXybmxx(request);
		
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgService service = new PjpyXmlgService();
		
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXydm(userModel.getXydm());
			boolean result = service.saveZhszcpPmfs(model);
			appendOperResult(request, result);
		} else {
			rs = service.getXyZhszcppmfs(userModel);
		}
		
		if (rs != null) {
			xmlgForm.setPmfs(rs.get("pmfs"));
		}
		return mapping.findForward("szZhszcppm");
	}
	
	/**
	 * ͳһ��ȡ�û������벿����Ϣ
	 * @param request
	 * @return
	 */
	public PjpyXmlgModel getXybmxx(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ?session.getAttribute("userType").toString():null;
		String userDep = session.getAttribute("userDep") != null ?session.getAttribute("userDep").toString():null;
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXydm(userDep);
		model.setUserType(userType);
		return model;
	}
	
	/**
	 * ��ѧ��,�����ƺű�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward csblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXn(Base.getJxjsqxn());
		String userType = request.getSession().getAttribute("userType").toString();
		PjpyXmlgModel userDepModel = getXybmxx(request);
		//����ط���ѧԺ�û����н�ѧ��,�����ƺ���������
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			return new ActionForward("/pjpy_xmlg_xyJxjrstz.do", false);
		}
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String tname = request.getParameter("tname");
		if (StringUtils.isNull(tname)) {
			tname = "jxj";
		}
				
		String act = request.getParameter("act");
	
		if ("rych".equalsIgnoreCase(act)) {
			return new ActionForward("/pjpy_xmlg_rychCsblsz.do", false);
		}
		
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			BeanUtils.copyProperties(queryModel, xmlgForm);
			queryModel.setKey("jxj");
			topTr = service.queryCsszTitle();
			rs = (ArrayList<String[]>)service.queryBlszxx(queryModel);
		}
		
		
		//���������б�
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("tname", tname);
		setNjXyZyBjList(request, xmlgForm);
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setSfqy("��");
		request.setAttribute("jxjList", service.getJxjList(model));
		request.setAttribute("fwList", service.getQueryType());
		request.setAttribute("pageCard", service.getPageCard(userType));
		appendPjxnndxq(request);
		return mapping.findForward("csblsz");
	}
	
	/**
	 * ��ʼ����ѧ�������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXn(Base.getJxjsqxn());
		model.setKey("jxj");
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		boolean result = service.initData(model);
		appendOperResult(request, result);
		
		//��ʼ����ѧ�������������
		csblsz(mapping, form, request, response);
		return mapping.findForward("csblsz");
	}
	
	/**
	 * ��ʼ����ѧ�������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward initDataByRych(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXn(Base.getJxjsqxn());
		model.setKey("rych");
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		boolean result = service.initData(model);
		appendOperResult(request, result);
		
		//��ʼ�������ƺű�����������
		rychCsblsz(mapping, form, request, response);
		return mapping.findForward("rychCsblsz");
	}
	
	/**
	 * ��ѧ�������������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		String xn = Base.getJxjsqxn();
		PjpyXmlgService service = new PjpyXmlgService();
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			String[] xydm = request.getParameterValues("chkonexy");
			String[] njdm = request.getParameterValues("chkonenj");
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXydmList(xydm);
			model.setNjdmList(njdm);
			model.setXn(xn);
			model.setKey("jxj");
			boolean result = service.modiJxjblszxx(model);
			appendOperResult(request, result);
		}
		
		//���������б�
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("��");
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		request.setAttribute("xn", xn);
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("jxjBlsz");
	}
	
	/**
	 * �����ƺű�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychBlsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		String xn = Base.getJxjsqxn();
		PjpyXmlgService service = new PjpyXmlgService();
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			String[] xydm = request.getParameterValues("chkonexy");
			String[] njdm = request.getParameterValues("chkonenj");
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXydmList(xydm);
			model.setNjdmList(njdm);
			model.setXn(xn);
			model.setKey("rych");
			boolean result = service.modiJxjblszxx(model);
			appendOperResult(request, result);
		}
		
		//���������б�
		request.setAttribute("rychList", service.getRychList());
		request.setAttribute("xn", xn);
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("rychBlsz");
	}
	
	/**
	 * �޸ı���������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiBlszxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);

		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgService service = new PjpyXmlgService();

		// �޸����ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.modiBlszxxOne(model);
			appendOperResult(request, result);
		}
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		rs = service.queryBlszxxOne(queryModel);
		if (rs != null) {
			xmlgForm.setBl(rs.get("bl"));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("modiBlszxx");
	}
	
	/**
	 * �޸ı���������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiRychBlszxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);

		String pkValue = request.getParameter("pkValue");
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgService service = new PjpyXmlgService();

		// �޸����ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.modiBlszxxOne(model);
			appendOperResult(request, result);
		}
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		rs = service.queryBlszxxOne(queryModel);
		if (rs != null) {
			xmlgForm.setBl(rs.get("bl"));
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("modiRychBlszxx");
	}
	
	/**
	 * �����ƺ�ѧУ��������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychCsblsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXn(Base.getJxjsqxn());
		String userType = request.getSession().getAttribute("userType").toString();
		PjpyXmlgService service = new PjpyXmlgService();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String tname = request.getParameter("tname");
		if (StringUtils.isNull(tname)) {
			tname = "rych";
		}
		PjpyXmlgModel userDepModel = getXybmxx(request);
//		����ط���ѧԺ�û����������ƺ���������
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			return new ActionForward("/xmlgpjpy.do?method=xyRychrstz", false);
		}
		String act = request.getParameter("act");
		if ("jxj".equalsIgnoreCase(act)) {
			return new ActionForward("/pjpy_xmlg_csblsz.do", false);
		}
		
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			BeanUtils.copyProperties(queryModel, xmlgForm);
			queryModel.setKey("rych");
			topTr = service.queryCsszTitle();
			rs = (ArrayList<String[]>)service.queryBlszxx(queryModel);
		}
		
		//���������б�
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("tname", tname);
		setNjXyZyBjList(request, xmlgForm);
		request.setAttribute("rychList", service.getRychList());
		request.setAttribute("fwList", service.getQueryType());
		request.setAttribute("pageCard", service.getPageCard(userType));
		appendPjxnndxq(request);
		return mapping.findForward("rychCsblsz");
	}
	
	/**
	 * ��ѧ������Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);

		PjpyXmlgService service = new PjpyXmlgService();
				
		String xh = request.getParameter("xh");
		String xn = Base.getJxjsqxn();
		String nd = Base.getJxjsqnd();
		
		//ѧ����ѧ���������
		if ("stu".equalsIgnoreCase(userDepModel.getUserType())
				|| "student".equalsIgnoreCase(userDepModel.getUserType())) {
			xh = request.getSession().getAttribute("userName") != null ? request
					.getSession().getAttribute("userName").toString()
					: "";
			request.setAttribute("showstu", "yes");
		}
		
		//���潱ѧ��������Ϣ
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXh(xh);
			model.setXn(xn);
			model.setNd(nd);
			model.setXq(Base.getJxjsqxq());
			boolean result = service.addJxjsqxx(model);
			//���������������ӡ������
			request.setAttribute("pkValue", model.getXh()+model.getXn()+model.getJxjdm());
			appendOperResult(request, result);
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		List<String[]> zhszcpList = new ArrayList<String[]>();
		if (!StringUtils.isNull(xh)) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			queryModel.setXn(xn);
			queryModel.setXh(xh);
			//��ѯѧ����Ϣ
			rs = CommonQueryDAO.getStuInfo(xh);
			//��ѯѧ���༶����
			String bjrs = CommonQueryDAO.getBjrs(xh);
			rs.put("bjrs", bjrs);
			//��ѯѧ���۲�������Ϣ
			zhszcpList = service.getStuZhszcpList(queryModel);
		}
		rs.put("xn", xn);
		rs.put("nd", nd);
		request.setAttribute("rs", rs);
		request.setAttribute("zhcpList", zhszcpList);
		
		//���������б�
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("��");
		//ѡ��ѧ������ˢ�½�ѧ����Ϣ
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			queryModel.setLbdm(xmlgForm.getLbdm());
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		return mapping.findForward("jxjsqDefault");
	}
	
	/**
	 * ��ѧ�����������ʽ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tzrsFssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.saveJxjrstzrs(model);
			appendOperResult(request, result);
		}
		
		//��������Ʒ�ʽ�����ˣ�
		// 1 �������������Ʒ�ʽ��
		// 2 �ǽ�����Ʒ�ʽ.
		HashMap<String, String> rs = service.getJxjrstzxzrs();
		if (rs != null) {
			xmlgForm.setXzfs(rs.get("xzfs"));
		}
		
		request.setAttribute("xzfsList", service.getXzfsList());
		return mapping.findForward("tzrsFssz");
	}
	
	/**
	 * ��ѧ����������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		PjpyXmlgService service = new PjpyXmlgService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		//�����ѧԺ�û�
		PjpyXmlgModel userDepModel = getXybmxx(request);
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
		}
		
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryJxjsqTitle();
			model.setUserType(userDepModel.getUserType());
			rs = (ArrayList<String[]>)service.queryJxjsqResult(model, false);
		}
		
		//���������б�
		request.setAttribute("path", "prise_result.do");
		FormModleCommon.commonRequestSet(request, "view_xsjxjb", "xsjxjb", rs, topTr);
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("��");
		//ѡ��ѧ������ˢ�½�ѧ����Ϣ
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			queryModel.setLbdm(xmlgForm.getLbdm());
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("jxjsqQuery");
	}
	
	/**
	 * ɾ����ѧ���������ݲ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgService service = new PjpyXmlgService();
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		boolean result = service.deleteJxjsqxx(model);
		appendDeleteResult(request, result);
		
		jxjsqQuery(mapping, form, request, response);
		return mapping.findForward("jxjsqQuery");
	}
	
	/**
	 * �޸Ľ�ѧ���������ݲ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiJxjsqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		String pkValue = request.getParameter("pkValue");
		PjpyXmlgService service = new PjpyXmlgService();
		
		//�����޸���Ϣ
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.updateJxjsqxx(model);
			appendOperResult(request, result);
		} else if (VIEW.equalsIgnoreCase(xmlgForm.getOperType())) {//���������˫��һ�������Ƶ�
			request.setAttribute("writable", "no");
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		List<String[]> zhszcpList = new ArrayList<String[]>();
		
		queryModel.setPkValue(pkValue);
		//ͨ��������ѯ��ѧ��������Ϣ
		rs = service.viewJxjsqxx(queryModel);
		if (rs != null) {
			xmlgForm.setLbdm(rs.get("lbdm"));
			xmlgForm.setJxjdm(rs.get("jxjdm"));
			xmlgForm.setDrzw(rs.get("drzw"));
			xmlgForm.setWysp(rs.get("wysp"));
			xmlgForm.setJsjsp(rs.get("jsjsp"));
			xmlgForm.setJlqk(rs.get("jlqk"));
			xmlgForm.setSqly(rs.get("sqly"));
		}
		
		//��ѯѧ���۲�������Ϣ
		queryModel.setXh(rs.get("xh"));
		queryModel.setXn(rs.get("xn"));
		zhszcpList = service.getStuZhszcpList(queryModel);

		request.setAttribute("zhcpList", zhszcpList);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		
		PjpyXmlgModel dmqueryModel = new PjpyXmlgModel();
		dmqueryModel.setSfqy("��");
		//ѡ��ѧ������ˢ�½�ѧ����Ϣ
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			dmqueryModel.setLbdm(xmlgForm.getLbdm());
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(dmqueryModel));
		return mapping.findForward("modiJxjsqxx");
	}
	
	/**
	 * ѧ����ѯ��ѧ��������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward queryJxjsqxxBystu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xh = request.getSession().getAttribute("userName") != null ? request
				.getSession().getAttribute("userName").toString() : "";
				
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXh(xh);
		List<String[]> rs = service.queryStujxjsqxx(model);
		List<HashMap<String, String>> topTr = service.queryJxjsqByStuTitle();
			
		request.setAttribute("topTr", topTr);
		request.setAttribute("rs", rs);
		request.setAttribute("num", rs != null ? rs.size() : 0);
		return mapping.findForward("queryJxjsqxxBystu");
	}
	
	/**
	 * ɾ����ѧ���������ݲ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward stujxjsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgService service = new PjpyXmlgService();
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		boolean result = service.deleteJxjsqxx(model);
		appendDeleteResult(request, result);
		
		queryJxjsqxxBystu(mapping, form, request, response);
		return mapping.findForward("queryJxjsqxxBystu");
	}
	
	/**
	 * ��ѧ�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXn(Base.getJxjsqxn());
		
		PjpyXmlgService service = new PjpyXmlgService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		//�����ѧԺ�û�
		PjpyXmlgModel userDepModel = getXybmxx(request);
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
			//���ﴦ����Ǽ��ظ�Ժϵ����������ʽ
			if (!StringUtils.isNull(xmlgForm.getXydm())) {
				String fpfs = service.getXyTzFs(Base.getJxjsqxn(), xmlgForm.getXydm());
				//����������ʽ
				request.setAttribute("fpfs", fpfs);
				if (!StringUtils.isNull(fpfs)) {
					request.setAttribute("fpfsxx", "��ѧ����˽��Խ�ѧ��"
							+ ("zy".equalsIgnoreCase(fpfs) ? "רҵ" : ("bj"
									.equalsIgnoreCase(fpfs) ? "�༶" : ""))
							+ "Ϊ��λ�������;");
				}
				String szrs = "";
				PjpyXmlgModel model = new PjpyXmlgModel();
				model.setXn(Base.getJxjsqxn());
				model.setJxjdm(xmlgForm.getJxjdm());
				
				if ("zy".equalsIgnoreCase(fpfs)) {
					model.setBmdm(xmlgForm.getZydm());
					szrs = service.getXyJxtzrs(model, "jxj");
				} else if ("bj".equalsIgnoreCase(fpfs)) {
					model.setBmdm(xmlgForm.getBjdm());
					szrs = service.getXyJxtzrs(model, "jxj");
				}
				//����������
				request.setAttribute("szrs", szrs);
				request.setAttribute("szrsxx", StringUtils.isNull(szrs)
						|| "0".equalsIgnoreCase(szrs) ? "" : "������Ϊ:" + szrs
						+ "��");
			}
		}
		
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryJxjsqTitle();
			model.setUserType(userDepModel.getUserType());
			rs = (ArrayList<String[]>)service.queryJxjsqResult(model, true);
		}
		
		//���������б�
		request.setAttribute("path", "prise_check.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("��");
		//ѡ��ѧ������ˢ�½�ѧ����Ϣ
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			queryModel.setLbdm(xmlgForm.getLbdm());
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		setNjXyZyBjList(request, xmlgForm);
		request.setAttribute("shList", service.getShList());
		
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * ��ѧ�𵥸����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);
		String pkValue = request.getParameter("pkValue");
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		HashMap<String, String> rs = new HashMap<String, String>();
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setKeys(new String[]{pkValue});
			model.setUserType(userDepModel.getUserType());
			//����ط����������������˵��õ���ͬһ������,��������ڶ�������Ϊ��
			boolean result = service.saveJxjshxx(model, "", "jxj", false);
			appendOperResult(request, result);
		}
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		queryModel.setUserType(userDepModel.getUserType());
		rs = service.queryJxjshDgxx(queryModel);
		if (rs != null) {
			xmlgForm.setSh(rs.get("sh"));
			xmlgForm.setYj(rs.get("yj"));
			xmlgForm.setXyshyj(rs.get("xyshyj"));
		}
		
		//������Ƶ���ѧУ���ͨ����,ѧԺ���������Ӵ
		if ("xy".equalsIgnoreCase(userDepModel.getUserType()) && "ͨ��".equalsIgnoreCase(rs.get("xxsh"))) {
			request.setAttribute("writable", "no");
		}
		
		queryModel.setXh(rs.get("xh"));
		queryModel.setXn(rs.get("xn"));
		
		//�����б�
		request.setAttribute("zhcpList", service.getStuZhszcpList(queryModel));//�۲������б�
		request.setAttribute("rs", rs);
		request.setAttribute("shList", service.getShList());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", userDepModel.getUserType());
		return mapping.findForward("jxjDgsh");
	}
	
	/**
	 * ��ѧ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgModel userDepModel = getXybmxx(request);
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		String act = request.getParameter("act");
		if ("tg".equalsIgnoreCase(act)) {
			model.setSh("ͨ��");
		} else if ("btg".equalsIgnoreCase(act)) {
			model.setSh("��ͨ��");
		}
		model.setUserType(userDepModel.getUserType());
		
		//�����������
		boolean result = service.saveJxjshxx(model, act, "jxj", true);
		appendOperResult(request, result);
		jxjsh(mapping, form, request, response);
		return mapping.findForward("jxjsh");
	}
	
	/**
	 * ��REQUEST�д�����ݲ�����Ľ��
	 * @param request
	 * @param result
	 */
	public void appendOperResult(HttpServletRequest request, boolean result) {
		if (result) {
			request.setAttribute("inserted", "yes");
		} else {
			request.setAttribute("inserted", "no");
		}
	}
	
	//ɾ��������ʾ
	public void appendDeleteResult(HttpServletRequest request, boolean result) {
		if (result) {
			request.setAttribute("deleted", "yes");
		} else {
			request.setAttribute("deleted", "no");
		}
	}
	
	public void appendPjxnndxq(HttpServletRequest request) {
		request.setAttribute("xn", Base.getJxjsqxn());
		request.setAttribute("nd", Base.getJxjsqnd());
		request.setAttribute("xq", Base.getJxjsqxq());
	}
	
	/**
	 * ����ѧԺ���꼶��רҵ���༶�����б�ֵ
	 * @param request
	 * @param myForm
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public void setNjXyZyBjList(HttpServletRequest request,
			PjpyXmlgActionForm myForm) throws Exception{
		// ��request�����꼶ѧԺרҵ�༶List�ķ���
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("userType");
		String userDep = (String) session.getAttribute("userDep");
		String userName = session.getAttribute("userName").toString();
		String xy = myForm.getXydm();
		String zy = myForm.getZydm();
		String bj = myForm.getBjdm();
		String nj = myForm.getNj();
		//
		xy = (xy == null) ? "" : xy;
		zy = (zy == null) ? "" : zy;
		bj = (bj == null) ? "" : bj;
		nj = (nj == null) ? "" : nj;
		//
		if ("xy".equalsIgnoreCase(userType)) {
			xy = userDep;
			myForm.setXydm(xy);
		}

		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("ndList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());//ѧ���б�
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xyList", Base.getXyList());
		request.setAttribute("zyList", (Base.getZyMap()).get(xy));
		request.setAttribute("bjList", (Base.getBjMap()).get(bjKey));
		if (session.getAttribute("fdyQx").toString() != null
				&& "true".equalsIgnoreCase(session.getAttribute("fdyQx")
						.toString())) {
			// ����Ա��¼
			request.setAttribute("bjList", Fdypd.getFdybjList(userName));// ���Ͱ༶�б�
			request.setAttribute("zyList", Fdypd.getFdyZyList(userName));// ���Ͱ༶�б�
		}
	}
	public ActionForward xyJxjrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		String userDep = request.getSession().getAttribute("userDep").toString();
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXydm(userDep);
		xmlgForm.setXn(Base.getJxjsqxn());
		PjpyXmlgService service = new PjpyXmlgService();
		String userType = request.getSession().getAttribute("userType").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String tname = request.getParameter("tname");
		if (StringUtils.isNull(tname)) {
			tname = "jxj";
		}				
		String act = request.getParameter("act");
		
		if ("rych".equalsIgnoreCase(act)) {
			return new ActionForward("/xmlgpjpy.do?method=xyRychrstz", false);
		}
		String xydm = xmlgForm.getXydm();
		String xn   = xmlgForm.getXn();
		xmlgForm.setBmlb(service.getXyTzFs(xn, xydm));
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			BeanUtils.copyProperties(queryModel, xmlgForm);
			queryModel.setKey("jxj");
			topTr = service.queryXyJxjRsSzTitle();
			rs = (ArrayList<String[]>)service.queryXyJxjRsSz(queryModel);
		}
		//���������б�
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("tname", tname);
		setNjXyZyBjList(request, xmlgForm);
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setSfqy("��");
		request.setAttribute("jxjList", service.getJxjList(model));
		request.setAttribute("fwList", service.getQueryType());
		request.setAttribute("pageCard", service.getPageCard(userType));
		appendPjxnndxq(request);
		request.setAttribute("xydm",xmlgForm.getXydm());
		request.setAttribute("yxzje",service.getXyZje(xmlgForm.getXydm(),Base.getJxjsqxn()));//��ѧԺ�����ܽ�ѧ���
		request.setAttribute("xzfs",service.getJxjrstzxzrs().get("xzfs"));
		request.setAttribute("bmlbV",xmlgForm.getBmlb());//���õ�����ʽ�ǰ�רҵ,���ǰ༶
		
		//��ѯѧԺ������Ľ��
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setXydm(xmlgForm.getXydm());
		queryModel.setXn(Base.getJxjsqxn());
		request.setAttribute("tzje", service.getXytzje(queryModel, xmlgForm
				.getBmlb()));
		
		return mapping.findForward("xyJxjrstz");
	}
	public ActionForward xyJxjrstzSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;	
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		BeanUtils.copyProperties(queryModel, xmlgForm);
		queryModel.setKey("jxj");
		service.XyJxjRsSzSave(queryModel);
		xmlgForm.setOperType("query");
		return xyJxjrstz(mapping, form, request, response);
	}
	public ActionForward  tzRsFsSz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, SQLException {//xyfpjxjfs
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;	
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();		
		BeanUtils.copyProperties(queryModel, xmlgForm);
		queryModel.setKey("jxj");
		service.tzRsFsSzSave(queryModel);
		return new ActionForward("/xmlgpjpy.do?method=xyJxjrstz",false);
	}
	public ActionForward tzfsUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;	
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();		
		BeanUtils.copyProperties(queryModel, xmlgForm);
	    //queryModel.setKey("jxj");
		service.tzfsUpdateSave(queryModel);
		return xyJxjrstz(mapping, form, request, response);
	}
	public ActionForward xyRychrstz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		String userDep = request.getSession().getAttribute("userDep").toString();
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXydm(userDep);
		xmlgForm.setXn(Base.getJxjsqxn());
		PjpyXmlgService service = new PjpyXmlgService();
		String userType = request.getSession().getAttribute("userType").toString();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String tname = request.getParameter("tname");
		if (StringUtils.isNull(tname)) {
			tname = "rych";
		}				
		
		String act = request.getParameter("act");
		if ("jxj".equalsIgnoreCase(act)) {
			return new ActionForward("/pjpy_xmlg_csblsz.do", false);
		}
		
		String xydm = xmlgForm.getXydm();
		String xn   = xmlgForm.getXn();
		xmlgForm.setBmlb(service.getXyTzFs(xn, xydm));
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			BeanUtils.copyProperties(queryModel, xmlgForm);
			queryModel.setKey("rych");
			topTr = service.queryRychRsszTitle();
			rs = (ArrayList<String[]>)service.queryRychRssz(queryModel);
		}
		//���������б�
		request.setAttribute("path", "prise_conf_rs.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("tname", tname);
		setNjXyZyBjList(request, xmlgForm);
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setSfqy("��");
//		���������б�
		request.setAttribute("rychList", service.getRychList());
		request.setAttribute("fwList", service.getQueryType());
		request.setAttribute("pageCard", service.getPageCard(userType));
		appendPjxnndxq(request);
		request.setAttribute("xydm",xmlgForm.getXydm());
		request.setAttribute("yxzje",service.getXyZje(xmlgForm.getXydm(),Base.getJxjsqxn()));//��ѧԺ�����ܽ�ѧ���
		request.setAttribute("xzfs",service.getJxjrstzxzrs().get("xzfs"));
		request.setAttribute("bmlb",xmlgForm.getBmlb());
		return mapping.findForward("xyRychrstz");
	}
	public ActionForward xyRychrstzSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;	
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		BeanUtils.copyProperties(queryModel, xmlgForm);
		queryModel.setKey("rych");
		service.XyRychRsSzSave(queryModel);
		xmlgForm.setOperType("query");
		return xyRychrstz(mapping, form, request, response);
	}
	/**�����ƺŽ�ѧ������Ĭ��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);

		PjpyXmlgService service = new PjpyXmlgService();
		String xh = request.getParameter("xh");
		String xn = Base.getJxjsqxn();
		String nd = Base.getJxjsqnd();
		
		//ѧ�������ƺ��������
		if ("stu".equalsIgnoreCase(userDepModel.getUserType())
				|| "student".equalsIgnoreCase(userDepModel.getUserType())) {
			xh = request.getSession().getAttribute("userName") != null ? 
					request.getSession().getAttribute("userName").toString(): "";
			request.setAttribute("showstu", "yes");
		}
		
		//���������ƺ�������Ϣ
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXh(xh);
			model.setXn(xn);
			model.setNd(nd);
			model.setXq(Base.getJxjsqxq());
			boolean result = service.serv_addRychsqxx(model);
			appendOperResult(request, result);
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		List<String[]> zhszcpList = new ArrayList<String[]>();
		if (!StringUtils.isNull(xh)) {
			PjpyXmlgModel queryModel = new PjpyXmlgModel();
			queryModel.setXn(xn);
			queryModel.setXh(xh);
			//��ѯѧ����Ϣ
			rs = CommonQueryDAO.getStuInfo(xh);
			//��ѯѧ���༶����
			String bjrs = CommonQueryDAO.getBjrs(xh);
			rs.put("bjrs", bjrs);
			//��ѯѧ���۲�������Ϣ
			zhszcpList = service.getStuZhszcpList(queryModel);
		}
		rs.put("xn", xn);
		rs.put("nd", nd);
		request.setAttribute("rs", rs);
		request.setAttribute("zhcpList", zhszcpList);		
		request.setAttribute("rychList", service.getRychList());
		return mapping.findForward("rychsqDefault");
	}
	/**
	 * �����ƺ���������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);
		PjpyXmlgService service = new PjpyXmlgService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		String userType = userDepModel.getUserType();
		//�����ѧԺ�û�
		
		if ("xy".equalsIgnoreCase(userType)) {
			xmlgForm.setXydm(userDepModel.getXydm());
		}
		
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryRychsqTitle();
			model.setUserType(userDepModel.getUserType());
			rs = (ArrayList<String[]>)service.serv_queryRychsqxx(model,userType);
		}
		//���������б�
		request.setAttribute("path", "credit_result.do");
		FormModleCommon.commonRequestSet(request, "view_xsrychb", "xsrychb", rs, topTr);
		request.setAttribute("rychList", service.getRychList());
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("rychsqQuery");
	}
	/**
	 * �޸������ƺ��������ݲ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiRychsqxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		String pkValue = request.getParameter("pkValue");
		PjpyXmlgService service = new PjpyXmlgService();
		
		//�����޸���Ϣ
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.serv_modiRychsqxx(model,pkValue);
			appendOperResult(request, result);
			pkValue = model.getXh()+model.getXn()+model.getRychdm();
		} else if (VIEW.equalsIgnoreCase(xmlgForm.getOperType())) {//���������˫��һ�������Ƶ�
			request.setAttribute("writable", "no");
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		List<String[]> zhszcpList = new ArrayList<String[]>();
		queryModel.setPkValue(pkValue);
		//ͨ��������ѯ�����ƺ�������Ϣ
		rs = service.viewRychsqxx(queryModel);
		
		//��ѯѧ���۲�������Ϣ
		queryModel.setXh(rs.get("xh"));
		queryModel.setXn(rs.get("xn"));
		rs.put("jlqk",rs.get("jcqk"));
		zhszcpList = service.getStuZhszcpList(queryModel);
		request.setAttribute("zhcpList", zhszcpList);
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);		
		request.setAttribute("rychList", service.getRychList());
		return mapping.findForward("modiRychsqxx");
	}
	/**
	 * ɾ�������ƺ��������ݲ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsqDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgService service = new PjpyXmlgService();
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		boolean result = service.serv_deleteRychsqxx(model);
		appendDeleteResult(request, result);
		return rychsqQuery(mapping, form, request, response);
	}
		
	/**
	 * ��ѧ��������ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		PjpyXmlgService service = new PjpyXmlgService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.jxjPrint(pkValue, "jxj");
			
		} else {
			rs.put("xn", Base.getJxjsqxn());
		}
		
		request.setAttribute("rs", rs);
		return mapping.findForward("jxjPrint");
	}
	
	/**
	 * �����ƺ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String userOnLine = request.getSession().getAttribute("userOnLine").toString();
		if (userOnLine.equalsIgnoreCase("student")) {// ѧ���޲���Ȩ��
			request.setAttribute("errMsg", "ѧ���û���Ȩ���ʸ�ҳ�棡");
			return new ActionForward("/errMsg.do", false);
		}
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		xmlgForm.setXn(Base.getJxjsqxn());
		
		PjpyXmlgService service = new PjpyXmlgService();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		//�����ѧԺ�û�
		PjpyXmlgModel userDepModel = getXybmxx(request);
		
		String userType = userDepModel.getUserType();
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
			//���ﴦ����Ǽ��ظ�Ժϵ����������ʽ
			if (!StringUtils.isNull(xmlgForm.getXydm())) {
				String fpfs = service.getXyTzFs(Base.getJxjsqxn(), xmlgForm.getXydm());
				//����������ʽ
				request.setAttribute("fpfs", fpfs);
				if (!StringUtils.isNull(fpfs)) {
					request.setAttribute("fpfsxx", "�����ƺ���˽��������ƺţ�"
							+ ("zy".equalsIgnoreCase(fpfs) ? "רҵ" : ("bj"
									.equalsIgnoreCase(fpfs) ? "�༶" : ""))
							+ "Ϊ��λ�������;");
				}
				String szrs = "";
				PjpyXmlgModel model = new PjpyXmlgModel();
				model.setXn(Base.getJxjsqxn());
				model.setRychdm(xmlgForm.getRychdm());
				
				if ("zy".equalsIgnoreCase(fpfs)) {
					model.setBmdm(xmlgForm.getZydm());
					szrs = service.getXyJxtzrs(model, "rych");
				} else if ("bj".equalsIgnoreCase(fpfs)) {
					model.setBmdm(xmlgForm.getBjdm());
					szrs = service.getXyJxtzrs(model, "rych");
				}
				//����������
				request.setAttribute("szrs", szrs);
				request.setAttribute("szrsxx", StringUtils.isNull(szrs)
						|| "0".equalsIgnoreCase(szrs) ? "" : "������Ϊ:" + szrs
						+ "��");
			}
		}
		
		//��ѯ���ݲ���
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryRychShTitle();
			model.setUserType(userType);
			rs = (ArrayList<String[]>) service.serv_queryRychShxx(model,
					userType);
		}
		
		//���������б�
		request.setAttribute("path", "credit_check.do");
		FormModleCommon.commonRequestSet(request, "", "", rs, topTr);
		request.setAttribute("rychList", service.getRychList());		
		setNjXyZyBjList(request, xmlgForm);
		request.setAttribute("shList", service.getShList());
		return mapping.findForward("rychsh");
	}
	/**
	 * ��ѧ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychPlsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgModel userDepModel = getXybmxx(request);
		String[] keys = request.getParameterValues("cbv");
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setKeys(keys);
		
		PjpyXmlgService service = new PjpyXmlgService();
		
		String act = request.getParameter("act");
		if ("tg".equalsIgnoreCase(act)) {
			model.setSh("ͨ��");
		} else if ("btg".equalsIgnoreCase(act)) {
			model.setSh("��ͨ��");
		}
		model.setUserType(userDepModel.getUserType());
		
		//�����������
		boolean result = service.saveJxjshxx(model, act, "rych", true);
		appendOperResult(request, result);
		return rychsh(mapping, form, request, response);
	}
	/**
	 * �����ƺŵ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward rychDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);
		String pkValue = request.getParameter("pkValue");
		String userType = userDepModel.getUserType();
		PjpyXmlgService service = new PjpyXmlgService();
		
		HashMap<String, String> rs = new HashMap<String, String>();
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			PjpyXmlgModel model = new PjpyXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setKeys(new String[]{pkValue});
			model.setUserType(userDepModel.getUserType());
			//����ط����������������˵��õ���ͬһ������,��������ڶ�������Ϊ��
			boolean result = service.saveJxjshxx(model, "", "rych", false);
			appendOperResult(request, result);
		}
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setPkValue(pkValue);
		queryModel.setUserType(userDepModel.getUserType());
		rs = service.viewRychsqxx(queryModel);
		if (rs != null) {
			if("xy".equalsIgnoreCase(userType)){
				xmlgForm.setSh(rs.get("xysh"));
				xmlgForm.setYj(rs.get("xyyj"));
				//xmlgForm.setXyshyj(rs.get("xyyj"));			
			}else{
				xmlgForm.setSh(rs.get("xxsh"));
				xmlgForm.setYj(rs.get("xxyj"));
				xmlgForm.setXyshyj(rs.get("xyyj"));
			}
		}
		
		//������Ƶ���ѧУ���ͨ����,ѧԺ���������Ӵ
		if ("xy".equalsIgnoreCase(userType) && "ͨ��".equalsIgnoreCase(rs.get("xxsh"))) {
			request.setAttribute("writable", "no");
		}
		
		queryModel.setXh(rs.get("xh"));
		queryModel.setXn(rs.get("xn"));
		rs.put("jlqk",rs.get("jcqk"));
		//�����б�
		request.setAttribute("zhcpList", service.getStuZhszcpList(queryModel));//�۲������б�
		request.setAttribute("rs", rs);
		request.setAttribute("shList", service.getShList());
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", userDepModel.getUserType());
		return mapping.findForward("rychdgsh");
	}
	
	/**
	 * ��ѧ�������ƺ�Ժϵ�Ƽ�����ʾ���������������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxhzsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		PjpyXmlgModel userDepModel = getXybmxx(request);
		if ("xy".equalsIgnoreCase(userDepModel.getUserType())) {
			xmlgForm.setXydm(userDepModel.getXydm());
		}
		
		if (StringUtils.isNull(xmlgForm.getXn())) {
			xmlgForm.setXn(Base.getJxjsqxn());
		}
		
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("��");
		//ѡ��ѧ������ˢ�½�ѧ����Ϣ
		if (!StringUtils.isNull(xmlgForm.getLbdm())) {
			queryModel.setLbdm(xmlgForm.getLbdm());
			xmlgForm.setJxjmc("");
		}
		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		setNjXyZyBjList(request, xmlgForm);
		return mapping.findForward("jxhzsc");
	}
	
	/**
	 * ��ѧ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjjehzsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String xn = request.getParameter("xn");
		String jxjdm = request.getParameter("jxjdm");
		String[] jxjdmList = StringUtils.isNull(jxjdm) ? null : jxjdm.split("!@");
		String xydm = request.getParameter("xydm");
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXn(xn);
		model.setXydm(xydm);
		model.setKeys(jxjdmList);
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		
		//д������
		service.writeJxjjehz(wwb, model);
		return mapping.findForward("");
	}
	/**
	 * �������
	 */
	public ActionForward mdscDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){		
		return mapping.findForward("mdscDefault");
	}
	/**
	 * ��ʾ�����
	 */
	public ActionForward gsdsc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel queryModel = new PjpyXmlgModel();
		queryModel.setSfqy("��");
		String type = request.getParameter("type"); 
		//FormModleCommon.setNdXnXqList(request);
		FormModleCommon.setNjXyZyBjList(request);
//		request.setAttribute("jxjlbList", service.getJxjlbList());
		request.setAttribute("jxjList", service.getJxjList(queryModel));
		request.setAttribute("rychList", service.getRychList());
		xmlgForm.setXn(Base.getJxjsqxn());
		if(type.equalsIgnoreCase("jxjgsd")){
			return mapping.findForward("jxjgsd");
		}else if(type.equalsIgnoreCase("rychgsd")){
			return mapping.findForward("rychgsd");
		}else{
			return mapping.findForward("pytjb");
		}		
	}
	/**
	 * ��ѧ�������ƺŹ�ʾ������
	 * @throws Exception 
	 */
	public ActionForward jxjRychGsddc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();
		String type = request.getParameter("type");
		xmlgForm.setXn(Base.getJxjsqxn());
		BeanUtils.copyProperties(model, xmlgForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		//д����
		service.serv_jxjRychGsddc(wwb, model,type);
		return mapping.findForward("");
	}
	/**
	 * �����Ƽ�����
	 * @throws Exception 
	 */
	public ActionForward pytjbdc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyXmlgActionForm xmlgForm = (PjpyXmlgActionForm) form;
		PjpyXmlgService service = new PjpyXmlgService();
		PjpyXmlgModel model = new PjpyXmlgModel();		
		xmlgForm.setXn(Base.getJxjsqxn());
		BeanUtils.copyProperties(model, xmlgForm);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		//д����
		service.serv_pytjb(wwb,model);
		return mapping.findForward("");
	}
		
	/**
	 * ��ѧ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxjjehzOutPut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		PjpyXmlgModel model = new PjpyXmlgModel();
		model.setXn(Base.getJxjsqxn());
		
		PjpyXmlgService service = new PjpyXmlgService();
		//��ѯ����δ�����Ľ�ѧ�����������
		List<String[]> rs = service.queryJxjjehzData(model);
		
		//������ѯ�����Ľ�����ͷ
		List<String[]> title = service.queryJxjjehzTitle(rs);
		List<String[]> result = service.parseQueryJxjjeResult(rs);
		
		request.setAttribute("rs", result);
		request.setAttribute("title", title);
		request.setAttribute("num", rs != null ? rs.size() : 1);
		request.setAttribute("xn", Base.getJxjsqxn());
		return mapping.findForward("jxjjehzOutPut");
	}
	
}
