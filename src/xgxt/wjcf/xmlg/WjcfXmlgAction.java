package xgxt.wjcf.xmlg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.qgzx.zgdzdx.QgzxZgdzdxService;
import xgxt.utils.ClassCopyUtils;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.Fdypd;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;
import xgxt.utils.date.DateUtils;
import xgxt.wjcf.wjcfutils.CommonAction;

import common.Globals;

public class WjcfXmlgAction  extends CommonAction {

	private WjcfXmlgService service = null;
	
	private static final String QUERY = "query";//��ѯ����
	private static final String SAVE = "save";//�������
	private static final String VIEW = "view";//��ʾ����
	
	/**
	 * ���ֱ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cfbPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String xh = request.getParameter("xh");
		String pk = request.getParameter("pk");
		WjcfXmlgModel model = new WjcfXmlgModel();
		model.setXh(xh);
		model.setPkValue(pk);
		service = new WjcfXmlgService();
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pk)) {
			rs = service.getCfbprintDetails(model);
		} else {
			rs = service.getCfbprint(model);
		}
		request.setAttribute("rs", rs);
		return mapping.findForward("cfbPrint");
	}
	
	/**
	 * �����걨��Ϣ�޸�ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward cfsbxxModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		
				if ("xy".equalsIgnoreCase(userType)) {
					xmlgForm.setXydm(session.getAttribute("userDep") != null ? session.getAttribute("userDep").toString() : "");
				}			
				
		String operType = request.getParameter("operType");
		service = new WjcfXmlgService();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		//��ѯ���ݲ���
		//if (QUERY.equalsIgnoreCase(operType)) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryCfsbxxTitle();
			rs = (ArrayList<String[]>)service.queryCfsbxxResult(model, userType, null);
			request.setAttribute("topTr", topTr);
			request.setAttribute("rs", rs);
		//}
		
		request.setAttribute("path", "not_declare_info.do");
		//���ز�ѯ���
		FormModleCommon.commonRequestSet(request, "view_wjcf", "wjcfb", rs, topTr);
		//���ش�����𣬴���ԭ���б�
		appendCflbCfyy(request);
		setNjXyZyBjList(request, xmlgForm);
		FormModleCommon.formToGBK(xmlgForm);
		return mapping.findForward("cfsbxxModi");
	}
	
	/**
	 * ɾ�������걨��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delCfsbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] keys = request.getParameterValues("cbv");
		service = new WjcfXmlgService();
		
		WjcfXmlgModel model = new WjcfXmlgModel();
		model.setKeys(keys);
		boolean result = service.delCfsbxx(model); 
		request.setAttribute("deleted", result);
		cfsbxxModi(mapping, form, request, response);
		return mapping.findForward("cfsbxxModi");
	}
	
	/**
	 * �޸Ĵ����걨��Ϣҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward modiCfsbxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		//FormModleCommon.formToGBK(xmlgForm);
		
		//ͳһ����ȡҳ��Ĳ���

		
		WjcfXmlgModel model = new WjcfXmlgModel();
		//ͳһ��ҳ���ȡ�Ĳ�����ֵ��MODEL��
		model.setPkValue(request.getParameter("pkValue"));
		
		//ClassCopyUtils.setModelValue(map, model);
		//BeanUtils.copyProperties(map, model);
		
		service = new WjcfXmlgService();
		//��ѯҪ�޸ĵĴ����걨��Ϣ
		HashMap<String, String> rs = service.viewCfsbxx(model); 
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel operModel = new WjcfXmlgModel();
			BeanUtils.copyProperties(operModel, xmlgForm);
			operModel.setRealPath(request.getRealPath("/"));
			boolean result = service.modiCfsbxx(operModel);
			appendOperResult(request, result);
			model.setPkValue(operModel.getPkValue());
		} else if (VIEW.equalsIgnoreCase(model.getOperType())) {
			request.setAttribute("xy_writable", "view");
		}
		if ((!"δ���".equalsIgnoreCase(rs.get("xxsh")) || "��ȷ��".equalsIgnoreCase(rs.get("xsqr")))) {
			request.setAttribute("xy_writable", "view");
		}
	
		
		if (rs != null) {
			xmlgForm.setXn(rs.get("xn"));
			xmlgForm.setNd(rs.get("nd"));
			xmlgForm.setJtwjsy(rs.get("bz"));
			xmlgForm.setXyclyj(rs.get("xyclyj"));
			xmlgForm.setCflb(rs.get("cflb"));
			xmlgForm.setCfyy(rs.get("cfyy"));
			xmlgForm.setLswjjl(rs.get("lswjjl"));
			xmlgForm.setWjsj(rs.get("wjsj"));
			if(Base.xxdm.equalsIgnoreCase(Globals.XXDM_NNZYJSXY)){
				xmlgForm.setXyyj(rs.get("xyyj"));
				xmlgForm.setXxyj(rs.get("xxyj"));
				xmlgForm.setBzryj(rs.get("bzryj"));
				xmlgForm.setXgcyj(rs.get("xgcyj"));
				xmlgForm.setBz(rs.get("bz"));
			}
		}
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", model.getPkValue());
		appendCflbCfyy(request);
		setNjXyZyBjList(request, xmlgForm);
		
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("modiCfsbxx");
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
	
	/**
	 * �������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		
		if ("xy".equalsIgnoreCase(userType)) {
			xmlgForm.setXydm(session.getAttribute("userDep") != null ? session.getAttribute("userDep").toString() : "");
		}		
				
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		service = new WjcfXmlgService();
		
		//��ѯ����
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryCfsbshTitle(userType);
			rs = (ArrayList<String[]>)service.queryCfsbshResult(model, userType);
		}
		
		request.setAttribute("path", "stuPunishAudit.do");
		//���ز�ѯ���
		FormModleCommon.commonRequestSet(request, "view_wjcf", "wjcfb", rs, topTr);
		//���ش�����𣬴���ԭ���б�
		appendCflbCfyy(request);
		setNjXyZyBjList(request, xmlgForm);
		FormModleCommon.formToGBK(xmlgForm);
		return mapping.findForward("cfsh");
	}
	
	/**
	 * �����걨��Ϣ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsbxxDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		
		service = new WjcfXmlgService();		
		
		//ͳһ��ȡҳ�����ֵ
		WjcfXmlgModel model = new WjcfXmlgModel();
		Map map = request.getParameterMap();
		ClassCopyUtils.setModelValue(map, model);
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel operModel = new WjcfXmlgModel();
			BeanUtils.copyProperties(operModel, xmlgForm);
			boolean result = service.saveCfsbshxx(operModel, userType);
			model.setPkValue(operModel.getPkValue());
			appendOperResult(request, result);
		} else if (VIEW.equalsIgnoreCase(xmlgForm.getOperType())) {
			request.setAttribute("writable", "view");
		}
		
		//��ѯ������
		HashMap<String, String> rs = service.viewCfsbshxx(model, userType);
		if (rs != null) {
			xmlgForm.setCfsj(rs.get("cfsj"));
			xmlgForm.setCfwh(rs.get("cfwh"));
			xmlgForm.setSh(rs.get("sh"));
			xmlgForm.setYj(rs.get("yj"));
		}
		request.setAttribute("shList", service.loadshList());
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", model.getPkValue());
		return mapping.findForward("cfsbxxDgsh");
	}
	
	/**
	 * �����걨�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfplsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		
		service = new WjcfXmlgService();		
				
		String pkValue = request.getParameter("keys");
		WjcfXmlgModel model = new WjcfXmlgModel();
		//ȡ�����������Ǿ�����װ�ģ�����Ҫ����һ��
		if (!StringUtils.isNull(pkValue)) {
			model.setKeys(pkValue.split("!@"));
		}
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel operModel = new WjcfXmlgModel();
			BeanUtils.copyProperties(operModel, xmlgForm);
			operModel.setKeys(model.getKeys());
			boolean result = service.saveCfsbplxx(operModel, userType);
			appendOperResult(request, result);
		}
		
		request.setAttribute("shList", service.loadshList());
		request.setAttribute("userType", userType);
		request.setAttribute("keys", pkValue);
		return mapping.findForward("cfplsh");
	}
	
	/**
	 * У�촦�����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		ArrayList<String[]> rs = new ArrayList<String[]>();
		
		service = new WjcfXmlgService();
		
		//��ѯ����
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			topTr = service.queryCfsbshxxByxbTitle();
			rs = (ArrayList<String[]>)service.queryCfsbxxResultByxb(model);
		}
		
		request.setAttribute("path", "stuPunishAuditByXz.do");
		//���ز�ѯ���
		FormModleCommon.commonRequestSet(request, "view_wjcf", "wjcfb", rs, topTr);
		//���ش�����𣬴���ԭ���б�
		appendCflbCfyy(request);
		setNjXyZyBjList(request, xmlgForm);
		FormModleCommon.formToGBK(xmlgForm);
		return mapping.findForward("xbsh");
	}
	
	/**
	 * У�쵥����˴�����Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfsbxxDgshByxb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		
//		HttpSession session = request.getSession();
//		String userType = session.getAttribute("userType") != null ? session
//				.getAttribute("userType").toString() : "";
		
		service = new WjcfXmlgService();		
		
		//ͳһ��ȡҳ�����ֵ
		WjcfXmlgModel model = new WjcfXmlgModel();
		Map map = request.getParameterMap();
		ClassCopyUtils.setModelValue(map, model);
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel operModel = new WjcfXmlgModel();
			BeanUtils.copyProperties(operModel, xmlgForm);
			boolean result = service.saveCfsbshxxByxb(operModel);
			model.setPkValue(operModel.getPkValue());
			appendOperResult(request, result);
		} else if (VIEW.equalsIgnoreCase(xmlgForm.getOperType())) {
			request.setAttribute("writable", "view");
		}
		
		//��ѯ������
		HashMap<String, String> rs = service.viewCfsbxxByxb(model);
		if (rs != null) {
			xmlgForm.setCfsj(rs.get("cfsj"));
			xmlgForm.setCfwh(rs.get("cfwh"));
			xmlgForm.setSh(rs.get("sh"));
			xmlgForm.setYj(rs.get("yj"));
		}
		request.setAttribute("shList", service.loadshList());
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", model.getPkValue());
		return mapping.findForward("cfsbxxDgshByxb");
	}
	
	/**
	 * У���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cfplshbyxb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		
		service = new WjcfXmlgService();		
				
		String pkValue = request.getParameter("keys");
		WjcfXmlgModel model = new WjcfXmlgModel();
		//ȡ�����������Ǿ�����װ�ģ�����Ҫ����һ��
		if (!StringUtils.isNull(pkValue)) {
			model.setKeys(pkValue.split("!@"));
		}
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel operModel = new WjcfXmlgModel();
			BeanUtils.copyProperties(operModel, xmlgForm);
			operModel.setKeys(model.getKeys());
			boolean result = service.saveCfsbplxxByxb(operModel);
			appendOperResult(request, result);
		}
		
		request.setAttribute("shList", service.loadshList());
		request.setAttribute("userType", userType);
		request.setAttribute("keys", pkValue);
		return mapping.findForward("cfplshbyxb");
	}
	
	/**
	 * �����У�쿴����ά��ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckDefault(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		
		if ("stu".equalsIgnoreCase(userType) || "student".equalsIgnoreCase(userType)) {
			return new ActionForward("/wjcf_xmlg_lxckStusq.do", false);
		} else if ("xy".equalsIgnoreCase(userType)) {
			xmlgForm.setXydm(session.getAttribute("userDep") != null ? session
					.getAttribute("userDep").toString() : "");
		}
	
		service = new WjcfXmlgService();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			rs = (ArrayList<String[]>)service.queryLxckResult(model, userType);
			topTr = service.queryLxckTitle();
		}
		
		request.setAttribute("path", "wjcf_zjlg_lxcksjwh.do");
		//���ز�ѯ���
		FormModleCommon.commonRequestSet(request, "view_wjcf_zjlg_lxck", "wjcf_zjlg_lxckb", rs, topTr);
		
		setNjXyZyBjList(request, xmlgForm);
		FormModleCommon.formToGBK(xmlgForm);
		return mapping.findForward("lxckDefault");
	}
	
	/**
	 * ɾ����У�쿴��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delLxckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String[] keys = request.getParameterValues("cbv");
		service = new WjcfXmlgService();
		boolean result = false;
		if (keys != null) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			model.setKeys(keys);
			result = service.delLxckxx(model);
			if (result) {
				request.setAttribute("deleted", "yes");
			} else {
				request.setAttribute("deleted", "no");
			}
		}
		
		lxckDefault(mapping, form, request, response);
		return mapping.findForward("lxckDefault");
	}
	
	/**
	 * ���ӽ����У�쿴��¼
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckStusq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		
		service = new WjcfXmlgService();		
		String xh = "";
		String pkValue = "";
		String operType = xmlgForm.getOperType();
		HashMap<String, String> rs = new HashMap<String, String>();
		if ("stu".equalsIgnoreCase(userType) || "student".equalsIgnoreCase(userType)) {
			xh = session.getAttribute("userName") != null ? session
					.getAttribute("userName").toString() : "";
			rs = CommonQueryDAO.getStuInfo(xh);
			pkValue = request.getParameter("pkValue");
			if (!StringUtils.isNull(pkValue)) {
				rs = service.queryStuLxckxx(pkValue);
			}
		} else {
			pkValue = request.getParameter("pkValue");
			if (!StringUtils.isNull(pkValue)) {
				rs = service.queryStuLxckxx(pkValue);
			}
		}
		
		//�������ݲ���
		if (SAVE.equalsIgnoreCase(operType)) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXn(Base.currXn);
			model.setNd(Base.currNd);
			if ("stu".equalsIgnoreCase(userType) || "student".equalsIgnoreCase(userType)) {
				model.setXh(xh);
			}
			boolean result = service.saveLxcksqxx(model);
			appendOperResult(request, result);
		}
		
		//��������ѹ��������������,��֮������
		if (rs != null && !StringUtils.isNull(rs.get("cfsj"))) {
			boolean writable = service.comTime(rs.get("cfsj"));
			request.setAttribute("writable", writable);
		}
		
		rs.put("cuxn", Base.currXn);
		rs.put("cund", Base.currNd);
		request.setAttribute("rs", rs);
		setNjXyZyBjList(request, xmlgForm);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("lxckStusq");
	}
	
	/**
	 * ��ѯ��У�쿴����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckxxquery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		String operType = request.getParameter("operType");
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		
		String xh = "";
		if ("stu".equalsIgnoreCase(userType) || "student".equalsIgnoreCase(userType)) {
			xh = session.getAttribute("userName") != null ? session
					.getAttribute("userName").toString() : "";
			
		}
		service = new WjcfXmlgService();		
		
		if (QUERY.equalsIgnoreCase(operType)) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setXh(xh);
			rs = (ArrayList<String[]>)service.queryWjcfxxByLxck(model);
			topTr = service.queryWjcfxxByLxck();
		}
		//���ز�ѯ���
		FormModleCommon.commonRequestSet(request, "view_wjcf_zjlg_lxck", "wjcf_zjlg_lxckb", rs, topTr);
		
		setNjXyZyBjList(request, xmlgForm);
		FormModleCommon.formToGBK(xmlgForm);
		return mapping.findForward("lxckxxquery");
	}
	
	/**
	 * ��У�쿴��ӡ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("pkValue");
		
		service = new WjcfXmlgService();	
		
		HashMap<String, String> rs = new HashMap<String, String>();
		if (!StringUtils.isNull(pkValue)) {
			rs = service.lxckPrint(pkValue); 
		}
		rs.put("year", DateUtils.getYear());
		rs.put("mon", DateUtils.getMonth());
		rs.put("date", DateUtils.getDayOfMonth());
		request.setAttribute("rs", rs);
		return mapping.findForward("lxckPrint");
	}
	
	/**
	 * �޸Ľ����У�쿴������Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward modiLxckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		String pkValue = request.getParameter("pkValue");
		
		service = new WjcfXmlgService();
		
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setPkValue(pkValue);
			boolean result = service.modiLxcksqxx(model);
			appendOperResult(request, result);
		} else if (VIEW.equalsIgnoreCase(xmlgForm.getOperType())) {
			request.setAttribute("writable", "view");
		}
		HashMap<String, String> rs = new HashMap<String, String>();
		
		rs = service.viewLxck(pkValue);
		xmlgForm.setXsbx(rs.get("xsbx"));
		
		request.setAttribute("rs", rs);
		request.setAttribute("pkValue", pkValue);
		return mapping.findForward("modiLxckxx");
	}
	
	/**
	 * ��У�쿴�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		String pkValue = request.getParameter("pkValue");
		
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
		service = new WjcfXmlgService();
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.saveLxckDgshxx(model, userType);
			appendOperResult(request, result);
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		WjcfXmlgModel queryModel = new WjcfXmlgModel();
		queryModel.setPkValue(pkValue);
		rs = service.queryLxckshxx(queryModel, userType);
		if (rs != null) {
			xmlgForm.setSh(rs.get("sh"));
			xmlgForm.setYj(rs.get("yj"));
			xmlgForm.setBjpyyj(rs.get("bjpyyj"));
			xmlgForm.setFdyjdyj(rs.get("fdyjdyj"));
		}
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("shList", service.loadshList());
		return mapping.findForward("lxckDgsh");
	}
	
	/**
	 * ���������У�쿴��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward plshLxckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		String userType = request.getSession().getAttribute("userType") != null ? request
				.getSession().getAttribute("userType").toString()
				: "";
				
		service = new WjcfXmlgService();
		
		String pkValue = request.getParameter("pkString");
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.savePlshLxckxx(userType, StringUtils
					.isNull(pkValue) ? new String[] {} : pkValue.split("!@"),
					model);
			appendOperResult(request, result);
		}
		request.setAttribute("pkString", pkValue);
		request.setAttribute("shList", service.loadshList());
		return mapping.findForward("plshLxckxx");
	}
	
	/**
	 * ��У�쿴У�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward lxckxbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType") != null ? session
				.getAttribute("userType").toString() : "";
		
		if ("xy".equalsIgnoreCase(userType)) {
			xmlgForm.setXydm(session.getAttribute("userDep") != null ? session
					.getAttribute("userDep").toString() : "");
		}
	
		service = new WjcfXmlgService();
		
		ArrayList<String[]> rs = new ArrayList<String[]>();
		List<HashMap<String, String>> topTr = new ArrayList<HashMap<String,String>>();
		if (QUERY.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			rs = (ArrayList<String[]>) service.queryLxckResultByxnd(model);
			topTr = service.queryLxckTitleByxb();
		}
		
		request.setAttribute("path", "wjcf_xmlg_lxckxbsh.do");
		//���ز�ѯ���
		FormModleCommon.commonRequestSet(request, "view_wjcf_zjlg_lxck", "wjcf_zjlg_lxckb", rs, topTr);
		
		setNjXyZyBjList(request, xmlgForm);
		FormModleCommon.formToGBK(xmlgForm);
		
		return mapping.findForward("lxckxbsh");
	}
	
	/**
	 * У�쵥�������У�쿴��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xbLxckxxDgsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
		String pkValue = request.getParameter("pkValue");
		
		service = new WjcfXmlgService();
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.saveLxckshxxByxb(model);
			appendOperResult(request, result);
		}
		
		HashMap<String, String> rs = new HashMap<String, String>();
		WjcfXmlgModel queryModel = new WjcfXmlgModel();
		queryModel.setPkValue(pkValue);
		rs = service.queryLxckshxxByxb(queryModel);
		if (rs != null) {
			xmlgForm.setSh(rs.get("sh"));
			xmlgForm.setYj(rs.get("yj"));
			xmlgForm.setJcsj(rs.get("jcsj"));
			xmlgForm.setJcwh(rs.get("jcwh"));
			xmlgForm.setJcjg(rs.get("jcjg"));
		}
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", rs);
		request.setAttribute("shList", service.loadshList());
		request.setAttribute("jcList", service.loadJcList());
		return mapping.findForward("xbLxckxxDgsh");
	}
	
	/**
	 * У�����������У�쿴��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xbplshLxckxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
				
		service = new WjcfXmlgService();
		
		String pkValue = request.getParameter("pkString");
		if (SAVE.equalsIgnoreCase(xmlgForm.getOperType())) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			boolean result = service.savePlshLxckxxByxb(StringUtils
					.isNull(pkValue) ? new String[] {} : pkValue.split("!@"),
					model);
			appendOperResult(request, result);
		}
		request.setAttribute("shList", service.loadshList());
		request.setAttribute("jcList", service.loadJcList());
		request.setAttribute("pkString", pkValue);
		return mapping.findForward("xbplshLxckxx");
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
	public ActionForward plsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pkValue = request.getParameter("keys");
		String[] keys = pkValue != null ? pkValue.split("!@") : null;
		
		QgzxZgdzdxService qgzxZgdzdxService=new QgzxZgdzdxService();
		WjcfXmlgActionForm xmlgForm = (WjcfXmlgActionForm) form;
		FormModleCommon.formToGBK(xmlgForm);
				
		service = new WjcfXmlgService();
		String userType = request.getSession().getAttribute("userType").toString();
		String userName = request.getSession().getAttribute("userName").toString();
		String xm=qgzxZgdzdxService.getFdyXm(userName);
		if (SAVE.equalsIgnoreCase(request.getParameter("operType"))) {
			WjcfXmlgModel model = new WjcfXmlgModel();
			BeanUtils.copyProperties(model, xmlgForm);
			model.setKeys(keys);
			model.setFsjname(xm);
			boolean result = service.savePlshCfxx(model, userType);
			appendOperResult(request, result);
		}
		appendChkList(request);
		request.setAttribute("keys", pkValue);
		return mapping.findForward("plsh");
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
			WjcfXmlgActionForm myForm) throws Exception,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
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
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.equalsIgnoreCase(""))) {
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
}
