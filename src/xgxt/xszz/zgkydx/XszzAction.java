package xgxt.xszz.zgkydx;

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

import xgxt.DAO.DAO;
import xgxt.DAO.XszzDao;
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * Title: ѧ����������ϵͳ
 * Description: �й���ҵ��ѧѧ������Action
 * Copyright: Copyright (c) 2008
 * Company: zfsoft
 * Author: ����
 * Version: 1.0
 * Time: 2008-08-13
 */
public class XszzAction extends BaseAction {

	private boolean isNull(String str) {
		return ((str == null) || str.equalsIgnoreCase("") || str
				.equalsIgnoreCase("all"));
	}
	
	/**
	 * ����������ҳ�� knssq ----- ����������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzZgkdService service = new XszzZgkdService();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// �õ�������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		request.setAttribute("sfksq", service.getKnsSqQx(sUserType, userDep,
				xh));
		stuMap.put("nd", Base.currNd);// ��ǰ���
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssq");
	}

	/**
	 * ����������������Ϣ knssqSave ---- ����������������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, xszzZgkydxActionForm);
		XszzZgkdService service = new XszzZgkdService();
		boolean bJg = service.saveKnsSqxx(knsModel, request);// ������Ϣ
		if (!"is".equals(request.getAttribute("isPASS"))) {
			if (bJg) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// �õ���������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("nd", Base.currNd);// ��ǰ���
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssqSave");
	}

	/**
	 * �����������ҳ�� knssqb ----- �����������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, xszzZgkydxActionForm);
		stuMap = service.getKnsSqb(knsModel, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knssqb");
	}

	/**
	 * ���������ҳ�� knssh ----- ���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knssh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("ad".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "A��", request);
			queryModel.setGo("go");
		}
		if ("bd".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "B��", request);
			queryModel.setGo("go");
		}
		if ("cd".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "C��", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "������", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			xszzZgkydxActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			xszzZgkydxActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getKnsRes(queryModel, request, form);
		String rnum = service.getKnsResNum(queryModel, request);
		xszzZgkydxActionForm.getPages().setMaxRecord(Integer.parseInt(rnum));
		String xh = DealString.toGBK(xszzZgkydxActionForm.getXh());
		xszzZgkydxActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgkydxActionForm);
		commonAction.appendProperties(request, commenBean, false);// ��REQUEST�д��ҳ����ص��б�
		xszzZgkydxActionForm.setXbshjg(DealString.toGBK(xszzZgkydxActionForm.getXbshjg()));
		xszzZgkydxActionForm.setXxshjg(DealString.toGBK(xszzZgkydxActionForm.getXxshjg()));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZgkydxActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "zgkydx_knsxx");
		request.setAttribute("tableName", "view_zgkydx_knsxx");
		return mapping.findForward("knssh");
	}

	/**
	 * ��������Ϣ���� knsExp ----- ��������Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		service.getKnsExp(queryModel, response, request);
		return mapping.findForward("knsExp");
	}

	/**
	 * �����������ϸҳ�� knsshOne ----- �����������ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		xszzZgkydxActionForm.setBjpyjg(stuMap.get("bjpyjg"));
		xszzZgkydxActionForm.setXbshjg(stuMap.get("xbshjg"));
		xszzZgkydxActionForm.setXxshjg(stuMap.get("xxshjg"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(21));
		request.setAttribute("dcfyqkList", dao.getChkList(22));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshOne");
	}

	/**
	 * ���������������Ϣ knsshSave ---- ���������������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, xszzZgkydxActionForm);
		XszzZgkdService service = new XszzZgkdService();
		boolean bJg = service.saveKnsShxx(knsModel, request);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		xszzZgkydxActionForm.setBjpyjg(stuMap.get("bjpyjg"));
		xszzZgkydxActionForm.setXbshjg(stuMap.get("xbshjg"));
		xszzZgkydxActionForm.setXxshjg(stuMap.get("xxshjg"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "admin");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(21));
		request.setAttribute("dcfyqkList", dao.getChkList(22));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshSave");
	}

	/**
	 * ������Ⱥ������������ѯҳ��
	 * knscx ----- ������Ⱥ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knscx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		DAO dao = DAO.getInstance();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		List<HashMap<String, String>> topList = service.getTsrqszTit();
		List<String[]> resList = service.getTsrqKnsRes(queryModel, request);
		String xh = DealString.toGBK(xszzZgkydxActionForm.getXh());
		xszzZgkydxActionForm.setXh(xh);
		xszzZgkydxActionForm.setXxshjg(queryModel.getXxshjg());
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgkydxActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgkydxActionForm.setXydm(userDep);
		}
		request.setAttribute("knpdList", dao.getChkList(23));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZgkydxActionForm);
		request.setAttribute("realTable", "zgkydx_knsxx");
		request.setAttribute("tableName", "view_zgkydx_knsxx");
		return mapping.findForward("knscx");
	}

	/**
	 * ������Ⱥ����ҳ��
	 * tsrqSet
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqSet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		String pkVal = Base.chgNull(request.getParameter("pkVal"),
				"!!splitOne!!", 1);
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("tsrqSet");
	}

	/**
	 * ������Ⱥ���ñ���
	 * tsrqSave
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String tsrqdm = xszzZgkydxActionForm.getTsrqdm();
		String pkVal = Base.chgNull(request.getParameter("pkVal"),
				"!!splitOne!!", 1);
		service.tsrqSave(Base.chgNull(request.getParameter("pkVal"),
				"!!splitOne!!", 1), tsrqdm, request);
		request.setAttribute("ok", "ok");
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("tsrqSave");
	}

	/**
	 * ������Ⱥ��ѯҳ��
	 * tsrqcx ----- ������Ⱥ��ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		DAO dao = DAO.getInstance();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delTsrqxx(Base.chgNull(request.getParameter("pkVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}

		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		List<HashMap<String, String>> topList = service.getTsrqcxTit();
		List<String[]> resList = service.getTsrqRes(queryModel, request);
		xszzZgkydxActionForm.setKnpd(queryModel.getKnpd());
		xszzZgkydxActionForm.setTsrqdm(queryModel.getTsrqdm());
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgkydxActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgkydxActionForm.setXydm(userDep);
		}
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("knpdList", dao.getChkList(23));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", xszzZgkydxActionForm);
		request.setAttribute("realTable", "xszz_zgkd_tsrq");
		request.setAttribute("tableName", "view_xszz_zgkd_tsrq");
		return mapping.findForward("tsrqcx");
	}

	/**
	 * ������Ⱥ��Ϣ����
	 * tsrqExp ----- ������Ⱥ��Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		service.getTsrqExp(queryModel, response, request);
		return mapping.findForward("tsrqExp");
	}

	/**
	 * ������Ⱥ��������Ŀά��ҳ��
	 * tsrqxmcx ----- ������Ⱥ��������Ŀ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqxmcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzZgkdService service = new XszzZgkdService();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delTsrqxmcxx(Base.chgNull(request.getParameter("pkVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		
		BeanUtils.copyProperties(queryModel, xszzZgkydxActionForm);
		List<HashMap<String, String>> topList = service.getTsrqxmTit();
		List<String[]> resList = service.getTsrqxmRes(queryModel, request);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, xszzZgkydxActionForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		if (userType.equalsIgnoreCase("xy")) {
			xszzZgkydxActionForm.setXydm(userDep);
		}
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("zzxmList", service.getZzxmList());
		request.setAttribute("pk", "tsrqdm||zzxmdm");
		request.setAttribute("hForm", xszzZgkydxActionForm);
		request.setAttribute("realTable", "xszz_zgktdx_tsrqxmwh");
		request.setAttribute("tableName", "view_xszz_zgktdx_tsrqxmwh");
		return mapping.findForward("tsrqxmcx");
	}

	/**
	 * ������Ⱥ��������Ŀ����ҳ��
	 * tsrqxmSet
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqxmSet(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("zzxmList", service.getZzxmList());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("tsrqxmSet");
	}

	/**
	 * ������Ⱥ��������Ŀ���ñ���
	 * tsrqxmSave
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tsrqxmSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzZgkdService service = new XszzZgkdService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		String tsrqdm = xszzZgkydxActionForm.getTsrqdm();
		String zzxmdm = xszzZgkydxActionForm.getZzxmdm();
		boolean b = service.tsrqxmSave(zzxmdm, tsrqdm, request);
		if (b) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		xszzZgkydxActionForm.setTsrqdm(tsrqdm);
		xszzZgkydxActionForm.setZzxmdm(zzxmdm);
		request.setAttribute("tsrqList", service.getTsrqList());
		request.setAttribute("zzxmList", service.getZzxmList());
		request.setAttribute("rs", stuMap);
		return mapping.findForward("tsrqxmSave");
	}
	
	/**
	 * @describe ��������б�
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		DAO dao = DAO.getInstance();
		XszzZgkydxActionForm xszzZgkydxActionForm = (XszzZgkydxActionForm) form;
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		realTable = "xszz_common_new_zzbbxssqb";
		pk = "nd||xmdm||xh";
		tableName = "view_xszz_common_new_zzbbxssqb";
		
		String nd = "";
		if(!isQuery.equalsIgnoreCase("is")){
			nd = Base.currNd;
		} else {
			nd = Base.chgNull(request.getParameter("nd"), "", 1);
		}
		String dqnd = Base.currNd;
		String dqxn = Base.currXn;
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		String sqlt = "";
		querry.append(querry1.toString());
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ������Ŀ���";
		
		if (isQuery.equalsIgnoreCase("is")) {
			tips = "��ǰ����λ�ã�ѧ������ - ���� - ��������ѯ - ������Ŀ";
			colList = new String[] { "bgcolor", "����", "pk2", "r", "nd", "xmmc", "xh", "xm",
					"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "xysh", "xxsh", "sqsj", "sfkns" };
			if (userType.equalsIgnoreCase("xx")) {
				StringBuffer sb = new StringBuffer("select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from(select ");
				sb.append(pk);
				sb.append(" ����,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from ");
				sb.append(tableName);
				sb.append(" a");
				sb.append(querry.toString());
				sb.append(" order by xxsh desc) a) where r<=");
				sb.append(xszzZgkydxActionForm.getPages().getStart() + xszzZgkydxActionForm.getPages().getPageSize());
				sb.append(" and r>");
				sb.append(xszzZgkydxActionForm.getPages().getStart());
				sql = sb.toString();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString(); 
				request.setAttribute("isXX", "is");
			} else {
				StringBuffer sb = new StringBuffer("select * from (select (case when nvl(a.xxsh,'δ���')='ͨ��' then '#FFFFFF' else '#CCCCCC' end) bgcolor,a.* from(select ");
				sb.append(pk);
				sb.append(" ����,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from ");
				sb.append(tableName);
				sb.append(" a");
				sb.append(querry.toString());
				sb.append(" order by xysh desc) a) where r<=");
				sb.append(xszzZgkydxActionForm.getPages().getStart() + xszzZgkydxActionForm.getPages().getPageSize());
				sb.append(" and r>");
				sb.append(xszzZgkydxActionForm.getPages().getStart());
				sql = sb.toString();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString(); 
				request.setAttribute("isXX", "no");
			}
		} else {
			if (userType.equalsIgnoreCase("xx")) {
				colList = new String[] { "bgcolor", "����", "pk2", "r", "nd", "xmmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "sfkns", "xysh", "" };
			}else{
				colList = new String[] { "bgcolor", "����", "pk2", "r", "nd", "xmmc", "xh", "xm",
						"xb", "sfzh", "xymc", "zymc", "bjmc", "nj", "sqsj", "" };
			}
			if (userType.equalsIgnoreCase("xx")) {
				StringBuffer sb = new StringBuffer("select * from (select (case when (select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='ͨ��' and x.xh=a.xh)+(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='ͨ��' and x.xh=a.xh)<='10000' then case when (select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='ͨ��' and x.xh=a.xh)<='8000' then '#000000' else '#FF0000' end else '#FF0000' end) bgcolor, a.* from(select ");
				sb.append(pk);
				sb.append(" ����,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from ");
				sb.append(tableName);
				sb.append(" a");
				sb.append(querry.toString());
				sb.append(" and xysh='ͨ��' order by xxsh desc) a) where r<=");
				sb.append(xszzZgkydxActionForm.getPages().getStart() + xszzZgkydxActionForm.getPages().getPageSize());
				sb.append(" and r>");
				sb.append(xszzZgkydxActionForm.getPages().getStart());
				sql = sb.toString();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString()+" and xysh='ͨ��'"; 
				colList[colList.length - 1] = "xxsh";
				request.setAttribute("isXX", "is");
			} else {
				StringBuffer sb = new StringBuffer("select * from (select (case when (select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='ͨ��' and x.xh=a.xh)+(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='ͨ��' and x.xh=a.xh)<='10000' then case when (select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='ͨ��' and x.xh=a.xh)<='8000' then '#000000' else '#FF0000' end else '#FF0000' end) bgcolor, a.* from(select ");
				sb.append(pk);
				sb.append(" ����,a.xmdm pk2,rownum r,a.nd,a.xmmc,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.xysh,a.xxsh,a.sqsj,a.sfkns from ");
				sb.append(tableName);
				sb.append(" a");
				sb.append(querry.toString());
				sb.append(" and xydm='");
				sb.append(userDep);
				sb.append("' order by xysh desc) a) where r<=");
				sb.append(xszzZgkydxActionForm.getPages().getStart() + xszzZgkydxActionForm.getPages().getPageSize());
				sb.append(" and r>");
				sb.append(xszzZgkydxActionForm.getPages().getStart());
				sql = sb.toString();
				sqlt = "select count(*) num from "+tableName+" a"+ querry.toString()+" and xydm='"+userDep+"'"; 
				colList[colList.length - 1] = "xysh";
				request.setAttribute("isXX", "no");
			}
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("xmdm", xmdm);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		xszzZgkydxActionForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs(sqlt, new String[]{}, "num")));
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("xszzsh");
	}
	
	/**
	 * @describe ������ʢ
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzgs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

//		 ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		String isQuery = request.getParameter("isQuery");
		if ((null == isQuery) || ("".equalsIgnoreCase(isQuery))) {
			isQuery = "no";
		}
		request.setAttribute("isQuery", isQuery);
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		StringBuffer querry1 = new StringBuffer("");
		String tableName = "";// ��ѯ��ϢԴ����Ϊ��ͼ����
		String rsNum = "0";// ���صļ�¼��
		String realTable = "";// ����Դ��
		String pk = "";// ����Դ����������ʽΪ���ֶ���||�ֶ���||�ֶ�������
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		realTable = "xszz_common_new_zzbbxssqb";
		pk = "nd||xmdm||xh";
		tableName = "view_xszz_common_new_zzbbxssqb";
		
		String nd = "";
		nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		querry.append(querry1.toString());
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ������ʢ����";

		colList = new String[] { "����", "pk2", "nd", "xh", "xm", "xb",
				"xymc", "zymc", "bjmc", "nj", "sfkns", "xmmc", "������׼���",
				"��ѧ���ܽ��", "�����ܽ��" };
		String dqnd = Base.currNd;
		String dqxn = Base.currXn;
		if (userType.equalsIgnoreCase("xx")) {
			StringBuffer sb = new StringBuffer("select a.* from (select ");
			sb.append(pk);
			sb.append(" ����,a.xmdm pk2,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sfkns,a.xmmc,a.xxpzje ������׼���,");
			sb.append("(select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='ͨ��' and x.xh=a.xh) ��ѧ���ܽ��,");
			sb.append("(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='ͨ��' and x.xh=a.xh) �����ܽ�� from ");
			sb.append(tableName);
			sb.append(" a");
			sb.append(querry.toString());
			sb.append(" order by xh) a where (NVL(��ѧ���ܽ��,0)+nvl(�����ܽ��,0))>'10000' or nvl(�����ܽ��,0)>'8000'");
			sql = sb.toString();
			request.setAttribute("isXX", "is");
		} else {
			StringBuffer sb = new StringBuffer("select a.* from (select ");
			sb.append(pk);
			sb.append(" ����,a.xmdm pk2,a.nd,a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,a.nj,a.sfkns,a.xmmc,a.xxpzje ������׼���,");
			sb.append("(select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='ͨ��' and x.xh=a.xh) ��ѧ���ܽ��,");
			sb.append("(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='ͨ��' and x.xh=a.xh) �����ܽ�� from ");
			sb.append(tableName);
			sb.append(" a");
			sb.append(querry.toString());
			sb.append(" and xydm='");
			sb.append(userDep);
			sb.append("' order by xh) a where (NVL(��ѧ���ܽ��,0)+nvl(�����ܽ��,0))>'10000' or nvl(�����ܽ��,0)>'8000'");
			sql = sb.toString();
			request.setAttribute("isXX", "no");
		}
		colListCN = dao.getColumnNameCN(colList, tableName);
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
		}
		
		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("xmdm", xmdm);
		xy = xy==null ? "":xy;
		zy = zy==null ? "":zy;
		nj = nj==null ? "":nj;
		String bjKey = xy+"!!"+zy+"!!"+nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("tableName", tableName);// ������ͼ��
		request.setAttribute("realTable", realTable);// ��������Դ����
		request.setAttribute("pk", pk);// ��������Դ������
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("xszzgs");
	}
	
	/**
	 * @describe ������ʢ���ݵ���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzgsExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String nd = "";
		nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		String dqnd = Base.currNd;
		String dqxn = Base.currXn;
		sql = "select a.* from (select a.nd,a.xh,a.xm,a.xb,a.sfzh,a.xymc,a.zymc,a.bjmc,a.nj,a.sfkns,a.xmmc,a.xxpzje,"
			+ "(select nvl(sum(ROUND(b.jlje)),0) jlje from xsjxjb x,jxjdmb b where x.xn='"+dqxn+"' and x.jxjdm=b.jxjdm and x.xxsh='ͨ��' and x.xh=a.xh) jlje,"
			+ "(select nvl(sum(ROUND(x.xxpzje)),0) xxpzje from xszz_common_new_zzbbxssqb x where x.nd='"+dqnd+"' and x.xxsh='ͨ��' and x.xh=a.xh) zzzje"
			+ " from view_xszz_common_new_zzbbxssqb a"
			+ querry.toString()
			+ " order by xh) a where (NVL(jlje,0)+nvl(zzzje,0))>'10000' or nvl(zzzje,0)>'8000'";
		
		String[] colListT = dao.getColumnName("select nd,xh,xm,sfzh,xymc,zymc,bjmc,nj,sfkns,xmmc from view_xszz_common_new_zzbbxssqb where 1=2");// �����������
		String[] colListCNT = dao.getColumnNameCN(colListT, "view_xszz_common_new_zzbbxssqb");
		String[] colList = new String[colListT.length+3];
		String[] colListCN = new String[colListCNT.length+3];
		int i = 0;
		for (; i < colListT.length; i++){
			colList[i] = colListT[i];
			colListCN[i] = colListCNT[i];
		}
		colList[i] = "xxpzje";
		colListCN[i] = "�����������";
		i++;
		colList[i] = "jlje";
		colListCN[i] = "��ѧ���ܽ��";
		i++;
		colList[i] = "zzzje";
		colListCN[i] = "�����ܽ��";
		
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("xszzgsExp");
	}
	
	// ѧ�� �������  hupeng
	public ActionForward hkgl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		String sql = "";
		String pk ="xh";
		XszzZgkdService dao2 = new XszzZgkdService();
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();

		if ("admin".equalsIgnoreCase(userType)
				|| "xx".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xx");
		} else if ("xy".equalsIgnoreCase(userType)) {
			request.setAttribute("who", "xy");
		}

		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String act = DealString.toGBK(request.getParameter("act"));
		String doType = DealString.toGBK(request.getParameter("doType"));
		String xh = DealString.toGBK(request.getParameter("xh"));
		String xm = DealString.toGBK(request.getParameter("xm"));
		String xb = DealString.toGBK(request.getParameter("xb"));
		String hth = DealString.toGBK(request.getParameter("hth"));
		String nj = DealString.toGBK(request.getParameter("nj"));
		String xydm = DealString.toGBK(request.getParameter("xydm"));
		String zydm = DealString.toGBK(request.getParameter("zydm"));
		String bjdm = DealString.toGBK(request.getParameter("bjdm"));

		map.put("xh", xh);
		map.put("xm", xm);
		map.put("xb", xb);
		map.put("hth", hth);
		map.put("nj", nj);
		map.put("xydm", xydm);
		map.put("zydm", zydm);
		map.put("bjdm", bjdm);

		// ����ɾ��
		if (null != pkValue) {
			pkValue = pkValue.replace(" ", "+");
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = dao2.hkxxDel(pk, pkValue, request);
			if (judge) {
				request.setAttribute("delete", "ok");
			} else {
				request.setAttribute("delete", "no");
			}
		}

		//����ɾ��
		if ("delall".equalsIgnoreCase(doType)) {
			String pkstring = request.getParameter("pkstring");
			String[] pkstringI = new String[] {};
			if (pkstring != null) {
				pkstringI = pkstring.split("!!#!!");
			}
			String[] sqlT = new String[pkstringI.length];
			for (int i = 1; i < pkstringI.length; i++) {
				String whichrid = pkstringI[i];
				sqlT[i] = "delete xszz_zgkd_xshkxxb where '"+pk+"'='"+whichrid+"'";
			}
			dao.runBatch(sqlT);
			request.setAttribute("delall", "ok");
		}

		//��պ�ͬ��Ϣ��
		if ("delallhtinfo".equalsIgnoreCase(doType)) {
			boolean judge = false;
			judge = dao2.hkxxDelall("xszz_zgkd_htxxb"); 
			if (judge) {
				request.setAttribute("delallhtinfo", "ok");
			} else {
				request.setAttribute("delallhtinfo", "no");
			}
		}
		//��ջ�����Ϣ��
		if ("delallhkinfo".equalsIgnoreCase(doType)) {

			boolean judge = false;

			judge = dao2.hkxxDelall("xszz_zgkd_xshkxxb");
            
			if (judge) {
				request.setAttribute("delallhkinfo", "ok");
			} else {
				request.setAttribute("delallhkinfo", "no");
			}
		}

		StringBuffer query = new StringBuffer();

		if (!("".equals(xh))) {
			query.append(" and xh like '%");
			query.append(xh);
			query.append("%'");
		}
		if (!("".equals(xm))) {
			query.append(" and xm like '%");
			query.append(xm);
			query.append("%'");
		}
		if (!("".equals(nj))) {
			query.append(" and nj='");
			query.append(nj);
			query.append("'");
		}
		if (!("".equals(xydm))) {
			query.append(" and xydm= '");
			query.append(xydm);
			query.append("'");
		}
		if (!("".equals(zydm))) {
			query.append(" and zydm='");
			query.append(zydm);
			query.append("'");
		}
		if (!("".equals(bjdm))) {
			query.append(" and bjdm='");
			query.append(bjdm);
			query.append("'");
		}
		if (!("".equals(hth))) {
			query.append(" and hth like '%");
			query.append(hth);
			query.append("%'");
		}
		
		String query1 = query.toString();
		XszzZgkdDAO dao1 = new XszzZgkdDAO();
		if ("go".equalsIgnoreCase(act)) {
			rs = dao1.getHkxsxx(query, form, request);
		}
		sql = "select count(*) from view_xszz_zgkd_xshkxxb where 1=1 " + query1;
		int rsNuminfo = dao.getOneRsint(sql);
		String rsNum = String.valueOf(rsNuminfo);
		request.setAttribute("rsNum", rsNum);
		String[] colList = new String[] { "xh", "xm", "xb", "hth", "xymc",
				"zymc", "bjmc" };
		String[] colListCN = dao.getColumnNameCN(colList,
				"view_xszz_zgkd_xshkxxb");
		List topTr = dao.arrayToList(colList, colListCN);
		
		
		

		String querry = query.toString();
		request.setAttribute("querry", querry);
		request.setAttribute("rs1", map);
		request.setAttribute("rs", rs);
		request.setAttribute("realTable", "xszz_zgkd_htxxb");
		request.setAttribute("realTable2", "xszz_zgkd_xshkxxb");
		request.setAttribute("topTr", topTr);
		request.setAttribute("njList", dao.getNjList());
		request.setAttribute("xyList", dao.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", dao.getZyList(xydm));// ����רҵ�б�
		request.setAttribute("bjList", dao.getBjList(xydm, zydm, nj));// ����רҵ�б�
		return mapping.findForward("hkgl");
	}

	//	 ѧ�� �������--��ϸ��Ϣ�鿴 hupeng
	public ActionForward hkglviewmore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
	
		XszzZgkdService dao = new XszzZgkdService();
		map=dao.getHkxxmoreinfo(pkValue, request);
		request.setAttribute("rs", map);	
		return mapping.findForward("hkglviewmore");

	}
	
	 //ѧ�� �������--�޸�
	public ActionForward hkglupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HashMap<String, String> map = new HashMap<String, String>();
		String pkValue = DealString.toGBK(request.getParameter("pkValue"));
		String doType = request.getParameter("doType");
		XszzZgkdService dao = new XszzZgkdService();

		String jtdz = DealString.toGBK(request.getParameter("jtdz")); // ��ͥ��ַ
		String jtyzbm = DealString.toGBK(request.getParameter("jtyzbm")); // ��ͥ��������
		String jtlxdh = DealString.toGBK(request.getParameter("jtlxdh")); // ��ͥ��ϵ�绰
		String gzdw = DealString.toGBK(request.getParameter("gzdw")); // ������λ
		String gzdwdz = DealString.toGBK(request.getParameter("gzdwdz")); // ������λ��ַ
		String gzdwlxdh = DealString.toGBK(request.getParameter("gzdwlxdh")); // ������λ��ϵ�绰
		String sjh = DealString.toGBK(request.getParameter("sjh")); // �ֻ���
		String zjdh = DealString.toGBK(request.getParameter("zjdh")); // �����绰
		String email = DealString.toGBK(request.getParameter("email")); //��������
		String qq = DealString.toGBK(request.getParameter("qq")); // QQ����

		if ("update".equalsIgnoreCase(doType)) {
			boolean judge = false;
			String[] columns = { "jtdz", "jtyzbm", "jtlxdh", "gzdw", "gzdwdz",
					"gzdwlxdh", "sjh", "zjdh", "email", "qq" };
			String[] values = { jtdz, jtyzbm, jtlxdh, gzdw, gzdwdz, gzdwlxdh,
					sjh, zjdh, email, qq };
			judge = dao.hkxxUpdate(columns, values, "xh", pkValue, request);
			if (judge) {
				request.setAttribute("update", "ok");
			} else {
				request.setAttribute("update", "no");
			}
		}
		map = dao.getHkxxmoreinfo(pkValue, request);
		request.setAttribute("rs", map);
		return mapping.findForward("hkglupdate");
	}

	//������Ϣ���ݵ���
	public ActionForward hkglexpData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = request.getParameter("tableName");
		String sql = "";

		response.reset();
		response.setContentType("application/vnd.ms-excel");
		if (tableName == null) {
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		} else {
			Excel2Oracle.exportData(sql, tableName, response.getOutputStream());
		}
		return mapping.findForward("expdata");

	}
	
	/**
	 * @describe �����������б�
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzJehz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// ��ʼ��ҳ�棬���ز�ѯ��Ϣ
		DAO dao = DAO.getInstance();
		XszzDao xszzDao = new XszzDao();
		HttpSession session = request.getSession();
		List<Object> rs = new ArrayList<Object>();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = null;
		String[] colListCN = null;
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		String rsNum = "0";// ���صļ�¼��
		String writeAble = "yes";// дȨ��
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String tips = "";
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);

		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);

		String nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(xmdm)) {
			querry.append(" and a.xmdm='");
			querry.append(xmdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and a.nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and a.nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and a.xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and a.zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and a.bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and a.xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(xysh)) {
			querry.append(" and a.xysh='");
			querry.append(xysh);
			querry.append("' ");
		}
		if (!isNull(xxsh)) {
			querry.append(" and a.xxsh='");
			querry.append(xxsh);
			querry.append("' ");
		}
		String je = "0";
		tips = "��ǰ����λ�ã�ѧ������ - ��� - ��������";
		String sqlT = "";
		if (userType.equalsIgnoreCase("xx")) {
			colList = new String[] { "nd", "xmmc", "xh", "xm", "bjmc",
					"sqsj", "xysh", "xxsh", "xxpzje", "pkdj" };
			colListCN = new String[] { "���", "��������", "ѧ��", "����", "�༶����",
					"����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���", "ѧУ��׼���", "���ѵȼ�" };
		} else {
			colList = new String[] { "nd", "xmmc", "xh", "xm", "bjmc",
					"sqsj", "xysh", "xxsh", "xypzje", "pkdj" };
			colListCN = new String[] { "���", "��������", "ѧ��", "����", "�༶����",
					"����ʱ��", Base.YXPZXY_KEY+"���", "ѧУ���", Base.YXPZXY_KEY+"��׼���", "���ѵȼ�" };
		}
		if (userType.equalsIgnoreCase("xx")) {
			sql = "select nvl(sum(ROUND(xxpzje)),0) num from view_xszz_common_new_zzbbxssqb a "
					+ querry.toString();
			je = dao.getOneRs(sql, new String[] {}, "num");
			sqlT = "select ' ' nd,' ' xmmc,' ' xh,' ' xm,' ' bjmc,' ' sqsj,'�ϼ�' xysh, ' ' xxsh, '"
					+ je + "' xxpzje,' ' pkdj from dual";
			sql = "select a.nd,a.xmmc,a.xh,a.xm,a.bjmc,a.sqsj,a.xysh,a.xxsh,a.xxpzje,NVL((select (case b.xxshjg when 'δ���' then '' when '������' then '' else b.xxshjg end) from zgkydx_knsxx b where a.nd=b.nd and a.xh=b.xh),'') pkdj from view_xszz_common_new_zzbbxssqb a "
					+ querry.toString() + " order by a.nd desc";
		} else {
			sql = "select nvl(sum(ROUND(xypzje)),0) num from view_xszz_common_new_zzbbxssqb a "
					+ querry.toString() + " and xydm='" + userDep;
			je = dao.getOneRs(sql, new String[] {}, "num");
			sqlT = "select ' ' nd,' ' xmmc,' ' xh,' ' xm,' ' bjmc,' ' sqsj,'�ϼ�' xysh, ' ' xxsh, '"
					+ je + "' xypzje,' ' pkdj from dual";
			sql = "select a.nd,a.xmmc,a.xh,a.xm,a.bjmc,a.sqsj,a.xysh,a.xxsh,a.xypzje,NVL((select (case b.xxshjg when 'δ���' then '' when '������' then '' else b.xxshjg end) from zgkydx_knsxx b where a.nd=b.nd and a.xh=b.xh),'') pkdj from view_xszz_common_new_zzbbxssqb a "
					+ querry.toString()
					+ " and a.xydm='"
					+ userDep
					+ "' order by a.nd desc";
		}
		List topTr = dao.arrayToList(colList, colListCN);
		if ((request.getParameter("go") != null)
				&& request.getParameter("go").equalsIgnoreCase("go")) {
			rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
			if (rs == null) {
				rsNum = "0";
			} else {
				rsNum = String.valueOf(rs.size());
			}
			rs.addAll(dao.rsToVator(sqlT, new String[] {}, colList));
		}

		map.put("xydm", xy);
		map.put("zydm", zy);
		map.put("bjdm", bj);
		map.put("nd", nd);
		map.put("nj", nj);
		map.put("xh", xh);
		map.put("xmdm", xmdm);
		map.put("xysh", xysh);
		map.put("xxsh", xxsh);
		xy = xy == null ? "" : xy;
		zy = zy == null ? "" : zy;
		nj = nj == null ? "" : nj;
		String bjKey = xy + "!!" + zy + "!!" + nj;
		request.setAttribute("tips", tips);
		request.setAttribute("rs1", map);// ���Ͷ�дȨ��
		request.setAttribute("writeAble", writeAble);// ���Ͷ�дȨ��
		request.setAttribute("zzxmList", xszzDao.getXszzZzxmList());
		request.setAttribute("njList", Base.getNjList());// �����꼶�б�
		request.setAttribute("ndList", Base.getXnndList());// ����ѧ���б�
		request.setAttribute("xyList", Base.getXyList());// ����ѧԺ�б�
		request.setAttribute("zyList", Base.getZyMap().get(xy));// ����רҵ�б�
		request.setAttribute("bjList", Base.getBjMap().get(bjKey));// ���Ͱ༶�б�
		request.setAttribute("xyshList", xszzDao.xyshList());
		request.setAttribute("xxshList", xszzDao.xxshList());
		request.setAttribute("act", "zzsh");
		request.setAttribute("rs", rs);// �������ݼ�
		request.setAttribute("topTr", topTr);// ���ͱ�ͷ
		request.setAttribute("rsNum", rsNum);// ���ͼ�¼��
		request.setAttribute("userType", userType);// ���ͼ�¼��
		return mapping.findForward("xszzJehz");
	}
	
	/**
	 * @describe ���������ܵ���
	 * @author zhoumi
	 * @throws Exception 
	 */
	public ActionForward xszzJehzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sql = "";// sql���
		StringBuffer querry = new StringBuffer(" where 1=1 ");// sql����
		List<Object> rs = new ArrayList<Object>();
		String userDep = session.getAttribute("userDep").toString();
		String userType = dao.getUserType(userDep);
		String xy = Base.chgNull(request.getParameter("xydm"), "", 1);
		String xh = Base.chgNull(request.getParameter("xh"), "", 1);
		if (userType.equalsIgnoreCase("xy")
				&& (xy == null || xy.trim().equals(""))) {
			xy = userDep;
		}
		String zy = Base.chgNull(request.getParameter("zydm"), "", 1);
		String bj = Base.chgNull(request.getParameter("bjdm"), "", 1);
		String xmdm = Base.chgNull(request.getParameter("xmdm"), "", 1);
		String nj = Base.chgNull(request.getParameter("nj"), "", 1);
		String xysh = Base.chgNull(request.getParameter("xysh"), "", 1);
		String xxsh = Base.chgNull(request.getParameter("xxsh"), "", 1);
		String nd = "";
		nd = Base.chgNull(request.getParameter("nd"), "", 1);
		if (!isNull(xmdm)) {
			querry.append(" and xmdm='");
			querry.append(xmdm);
			querry.append("' ");
		}
		if (!isNull(nj)) {
			querry.append(" and nj='");
			querry.append(nj);
			querry.append("' ");
		}
		if (!isNull(nd)) {
			querry.append(" and nd='");
			querry.append(nd);
			querry.append("' ");
		}
		if (!isNull(xy)) {
			querry.append(" and xydm='");
			querry.append(xy);
			querry.append("' ");
		}
		if (!isNull(zy)) {
			querry.append(" and zydm='");
			querry.append(zy);
			querry.append("' ");
		}
		if (!isNull(bj)) {
			querry.append(" and bjdm='");
			querry.append(bj);
			querry.append("' ");
		}
		if (!isNull(xh)) {
			querry.append(" and xh='");
			querry.append(xh);
			querry.append("' ");
		}
		if (!isNull(xysh)) {
			querry.append(" and xysh='");
			querry.append(xysh);
			querry.append("' ");
		}
		if (!isNull(xxsh)) {
			querry.append(" and xxsh='");
			querry.append(xxsh);
			querry.append("' ");
		}
		
		sql = "select a.*,NVL((select (case b.xxshjg when 'δ���' then '' when '������' then '' else b.xxshjg end) from zgkydx_knsxx b where a.nd=b.nd and a.xh=b.xh),'') pkdj from (select * from view_xszz_common_new_zzbbxssqb " + querry.toString() + ") a";
		
		String[] colListT = dao.getColumnName("select * from view_xszz_common_new_zzbbxssqb where 1=2");// �����������
		String[] colListCNT = dao.getColumnNameCN(colListT, "view_xszz_common_new_zzbbxssqb");
		String[] colList = new String[colListT.length+1];
		String[] colListCN = new String[colListCNT.length+1];
		int i = 0;
		for (; i < colListT.length; i++){
			colList[i] = colListT[i];
			colListCN[i] = colListCNT[i];
		}
		colList[i] = "pkdj";
		colListCN[i] = "ƶ���ȼ�";
		
		
		rs.addAll(dao.rsToVator(sql, new String[] {}, colList));
		
		sql = "select nvl(sum(ROUND(xypzje)),0) xyzje,nvl(sum(ROUND(xxpzje)),0) xxzje from view_xszz_common_new_zzbbxssqb " + querry.toString();
		String[] je = dao.getOneRs(sql, new String[]{}, new String[]{"xyzje","xxzje"});
		
		StringBuffer sqlBf = new StringBuffer("select '�ϼ�' nd");
		for(i = 1; i< colList.length; i++){
			if("xypzje".equalsIgnoreCase(colList[i])){
				sqlBf.append(",'"+je[0]+"' xypzje");
			} else if("xxpzje".equalsIgnoreCase(colList[i])){
				sqlBf.append(",'"+je[1]+"' xxpzje");
			} else {
				sqlBf.append(",' ' "+colList[i].toLowerCase());
			}
		}
		sqlBf.append(" from dual ");
		rs.addAll(dao.rsToVator(sqlBf.toString(), new String[] {}, colList));
		
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		Excel2Oracle.exportDataFor(rs, colListCN, response.getOutputStream());
		return mapping.findForward("xszzJehzExp");
	}

}
