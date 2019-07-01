package xgxt.xszz.xmlgxy;

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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ������ѧԺѧ������Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-12-10</p>
 */
public class XszzAction extends BaseAction {
	/**
	 * ������־��ѧ������ҳ��
	 * gjlzjxjsq ----- ������־��ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzXmlgxyService service = new XszzXmlgxyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjlzjxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("������־��ѧ��", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjsq");
	}

	/**
	 * ���������־��ѧ��������Ϣ gjlzjxjsqSave ---- ���������־��ѧ��������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, actionForm);
		XszzXmlgxyService service = new XszzXmlgxyService();
		boolean bJg = service.saveGjlzjxjSqxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjlzjxjxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjsqSave");
	}

	/**
	 * ������־��ѧ�������ҳ�� gjlzjxjsqb ----- ������־��ѧ�������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		XszzXmlgxyService service = new XszzXmlgxyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxjxx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjlzjxjsqb");
	}
	
	/**
	 * ������־��ѧ�����ҳ�� gjlzjxjsh ----- ������־��ѧ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmlgxyService service = new XszzXmlgxyService();
		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjlzjxjxx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxjxx(actionForm.getPk(), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjlzjxjxx(actionForm.getPk(), "��ͨ��", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjlzjxjTit();
		List<String[]> resList = service.getGjlzjxjRes(queryModel, request,
				actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjlzjxjResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_xmlg_gjlzjxj");
		request.setAttribute("tableName", "view_xszz_xmlg_gjlzjxj");
		return mapping.findForward("gjlzjxjsh");
	}

	/**
	 * ������־��ѧ����Ϣ���� gjlzjxjExp ----- ������־��ѧ����Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmlgxyService service = new XszzXmlgxyService();
		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getGjlzjxjExp(queryModel, response, request);
		return mapping.findForward("gjlzjxjExp");
	}
	
	/**
	 * ������־��ѧ�������ϸҳ�� gjlzjxjshOne ----- ������־��ѧ�������ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzXmlgxyService service = new XszzXmlgxyService();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxjxx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjshOne");
	}
	
	/**
	 * ���������־��ѧ�������Ϣ gjlzjxjshSave ---- ���������־��ѧ�������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		XszzXmlgxyService service = new XszzXmlgxyService();

		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		GjlzjxjModel model = new GjlzjxjModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveGjlzjxjShxx(model, request);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjlzjxjxx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjshSave");
	}

	/**
	 * ������־��ѧ��ʾ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjlzjxjgsmd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmlgxyService service = new XszzXmlgxyService();
		String xn = request.getParameter("xn");
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		WritableWorkbook wwb = Workbook.createWorkbook(response.getOutputStream());
		//д����
		service.gjlzjxjgsmd(wwb, xn);
		return mapping.findForward("");
	}

	/**
	 * ���ҽ�ѧ������ҳ��
	 * gjjxjsq ----- ���ҽ�ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		XszzXmlgxyService service = new XszzXmlgxyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjjxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);
			}
		}

		request.setAttribute("type", Base.chgNull(request.getParameter("type"), "", 1));
		request.setAttribute("sfksq", service.getSqQx("���ҽ�ѧ��", sUserType, xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjsq");
	}

	/**
	 * ������ҽ�ѧ��������Ϣ gjjxjsqSave ---- ������ҽ�ѧ��������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		GjjxjModel model = new GjjxjModel();
		BeanUtils.copyProperties(model, actionForm);
		XszzXmlgxyService service = new XszzXmlgxyService();
		boolean bJg = service.saveGjjxjSqxx(model, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjjxjxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);// ��ǰѧ��
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjsqSave");
	}

	/**
	 * ���ҽ�ѧ�������ҳ�� gjjxjsqb ----- ���ҽ�ѧ�������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		XszzXmlgxyService service = new XszzXmlgxyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxjxx(actionForm.getXn() + actionForm.getXh());
		
		stuMap.put("xxmc", StandardOperation.getXxmc());
		
		String sfzh = Base.chgNull(stuMap.get("sfzh"), "", 1);
		String[] sF = new String[18];
		
		for (int i = 0; i < sfzh.length(); i++) {
			sF[i] = sfzh.substring(i, i+1);
		}
		for (int i = 1; i < 19; i++) {
			stuMap.put("sfzh"+i, sF[i-1]);
		}
		
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjjxjsqb");
	}
	
	/**
	 * ���ҽ�ѧ�����ҳ�� gjjxjsh ----- ���ҽ�ѧ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmlgxyService service = new XszzXmlgxyService();
		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjjxjxx(actionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxjxx(actionForm.getPk(), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxjxx(actionForm.getPk(), "��ͨ��", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			actionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			actionForm.setXn(Base.currXn);
		}
		BeanUtils.copyProperties(queryModel, actionForm);
		List<HashMap<String, String>> topList = service.getGjjxjTit();
		List<String[]> resList = service.getGjjxjRes(queryModel, request,
				actionForm);
		String xh = DealString.toGBK(actionForm.getXh());
		actionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, actionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		actionForm.setXm(DealString.toGBK(actionForm.getXm()));
		actionForm.setXb(DealString.toGBK(actionForm.getXb()));
		actionForm.setSfzh(DealString.toGBK(actionForm.getSfzh()));

		actionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjjxjResNum(queryModel, request)));
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", actionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_xmlg_gjjxj");
		request.setAttribute("tableName", "view_xszz_xmlg_gjjxj");
		return mapping.findForward("gjjxjsh");
	}

	/**
	 * ���ҽ�ѧ����Ϣ���� gjjxjExp ----- ���ҽ�ѧ����Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzXmlgxyService service = new XszzXmlgxyService();
		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, actionForm);
		service.getGjjxjExp(queryModel, response, request);
		return mapping.findForward("gjjxjExp");
	}
	
	/**
	 * ���ҽ�ѧ�������ϸҳ�� gjjxjshOne ----- ���ҽ�ѧ�������ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzXmlgxyService service = new XszzXmlgxyService();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxjxx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjshOne");
	}
	
	/**
	 * ������ҽ�ѧ�������Ϣ gjjxjshSave ---- ������ҽ�ѧ�������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjjxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		XszzXmlgxyService service = new XszzXmlgxyService();
		
		XszzXmlgxyActionForm actionForm = (XszzXmlgxyActionForm) form;
		GjjxjModel model = new GjjxjModel();
		BeanUtils.copyProperties(model, actionForm);
		boolean bJg = service.saveGjjxjShxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String xn = model.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxjxx(pkVal);

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjshSave");
	}
}
