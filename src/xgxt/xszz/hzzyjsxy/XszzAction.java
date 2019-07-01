
package xgxt.xszz.hzzyjsxy;

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
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ����ְҵ����ѧԺѧ������Action</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-02</p>
 */
public class XszzAction extends BaseAction {

	/**
	 * ��Уѧ��������Ϣҳ��
	 * data_dkxx ----- ��Уѧ����������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward data_dkxx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel zxxsdkModel = new QueryModel();
		zxxsdkModel.setGo(request.getParameter("go"));
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		
		if ("del".equalsIgnoreCase(zxxsdkModel.getGo())) {
			service.delDkxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			zxxsdkModel.setGo("go");
		}
		
		BeanUtils.copyProperties(zxxsdkModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getZxxsDkxxTit();
		List<String[]> resList = service.getShJxjRes(zxxsdkModel,request);
		String xh = DealString.toGBK(hzzyForm.getXh());
		hzzyForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, hzzyForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		if (userType.equalsIgnoreCase("xy")) {
			hzzyForm.setXydm(userDep);
		}
		request.setAttribute("pk", "xn||xh");
		request.setAttribute("hForm", hzzyForm);
		request.setAttribute("realTable", "xszz_hzzyjsxy_zxxsdkxx");
		request.setAttribute("tableName", "view_xszz_hzzyjsxy_zxxsdkxx");
		return mapping.findForward("data_dkxx");
	}
	
	/**
	 * ��Уѧ��������ϸ��Ϣҳ��
	 * dkxx_queryOne ----- ��Уѧ��������ϸ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dkxx_queryOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzHzzyService service = new XszzHzzyService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String xn = Base.currXn;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? xn + xh : pkVal;
		xh = pkVal.substring(9);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getDkxx(pkVal);// �õ�������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);//��ǰѧ��
			}
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("dkxx_queryOne");
	}
	
	/**
	 * ������Уѧ��������Ϣ
	 * dkxx_save ---- ������Уѧ��������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dkxx_save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		SaveZxxsdkModel saveZxxsdkModel = new SaveZxxsdkModel();
		BeanUtils.copyProperties(saveZxxsdkModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveDkxx(saveZxxsdkModel, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = saveZxxsdkModel.getXh();
		String xn = saveZxxsdkModel.getXn();
		String pkVal = xn + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getDkxx(pkVal);// �õ�������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("xn", Base.currXn);//��ǰѧ��
			}
		}
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("dkxx_save");
	}
	
	/**
	 * ��Уѧ��������Ϣ����
	 * dkxx_exp ----- ��Уѧ��������Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward dkxx_exp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel zxxsdkModel = new QueryModel();
		
		BeanUtils.copyProperties(zxxsdkModel, hzzyForm);
		service.getZxxsDkxxExp(zxxsdkModel, response,request);
		return mapping.findForward("dkxx_exp");
	}
	
	/**
	 * ��ͥ��������֤����ӡ
	 * jtjjknzm ----- ��ͥ��������֤����ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtjjknzm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("jtjjknzm");
	}
	
	/**
	 * ��ͥ�����������˵����ӡ
	 * jtjjknqksm ----- ��ͥ�����������˵����ӡ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jtjjknqksm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("jtjjknqksm");
	}
	
	/**
	 * ����������ҳ��
	 * knssq ----- ����������ҳ��
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
		XszzHzzyService service = new XszzHzzyService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
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
		stuMap.put("nd", Base.currNd);//��ǰ���
		request.setAttribute("sfksq", service.getKnsSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssq");
	}
	
	/**
	 * ����������������Ϣ
	 * knssqSave ---- ����������������Ϣ 
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
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveKnsSqxx(knsModel, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = knsModel.getXh();
		String nd = knsModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getKnsxx(pkVal);// �õ���������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("nd", Base.currNd);//��ǰ���
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knssqSave");
	}

	/**
	 * �����������ҳ��
	 * knssqb ----- �����������ҳ��
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
		
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		XszzHzzyService service = new XszzHzzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, hzzyjsxyActionForm);
		stuMap = service.getKnsSqb(knsModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knssqb");
	}

	/**
	 * ���������ҳ��
	 * knssh ----- ���������
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
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "����", request);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��������", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "������", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			hzzyForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			hzzyForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getKnsRes(queryModel,request);
		String xh = DealString.toGBK(hzzyForm.getXh());
		hzzyForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, hzzyForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		hzzyForm.setXm(DealString.toGBK(hzzyForm.getXm()));
		hzzyForm.setXb(DealString.toGBK(hzzyForm.getXb()));
		hzzyForm.setMzrd(DealString.toGBK(hzzyForm.getMzrd()));
		hzzyForm.setXysh(DealString.toGBK(hzzyForm.getXysh()));
		hzzyForm.setXxsh(DealString.toGBK(hzzyForm.getXxsh()));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", hzzyForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "hzzy_knssqb");
		request.setAttribute("tableName", "view_hzzy_knssqb");
		return mapping.findForward("knssh");
	}
	
	/**
	 * ��������Ϣ����
	 * knsExp ----- ��������Ϣ����
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
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, hzzyForm);
		service.getKnsExp(queryModel, response,request);
		return mapping.findForward("knsExp");
	}

	/**
	 * �����������ϸҳ��
	 * knsshOne ----- �����������ϸҳ��
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
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		XszzHzzyService service = new XszzHzzyService();
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);
		hzzyForm.setMzrd(stuMap.get("mzrd"));
		hzzyForm.setXysh(stuMap.get("xysh"));
		hzzyForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshOne");
	}

	/**
	 * ���������������Ϣ
	 * knsshSave ---- ���������������Ϣ 
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
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		KnsModel knsModel = new KnsModel();
		BeanUtils.copyProperties(knsModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
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
		hzzyjsxyActionForm.setMzrd(stuMap.get("mzrd"));
		hzzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		hzzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshSave");
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
		XszzHzzyService service = new XszzHzzyService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjjxjxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		stuMap.put("nd", Base.currNd);//��ǰ���
		request.setAttribute("sfksq", service.getGjjxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjsq");
	}
	
	/**
	 * ������ҽ�ѧ��������Ϣ
	 * gjjxjsqSave ---- ������ҽ�ѧ��������Ϣ 
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
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		GjjxjModel gjjxjModel = new GjjxjModel();
		BeanUtils.copyProperties(gjjxjModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveGjjxjSqxx(gjjxjModel, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjjxjModel.getXh();
		String nd = gjjxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjjxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("nd", Base.currNd);//��ǰ���
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjsqSave");
	}
	
	/**
	 * ���ҽ�ѧ�������ҳ��
	 * gjjxjsqb ----- ���ҽ�ѧ�������ҳ��
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
		
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		XszzHzzyService service = new XszzHzzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjjxjModel gjjxjModel = new GjjxjModel();
		BeanUtils.copyProperties(gjjxjModel, hzzyjsxyActionForm);
		stuMap = service.getGjjxjSqb(gjjxjModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjjxjsqb");
	}
	
	/**
	 * ���ҽ�ѧ�����ҳ��
	 * gjjxjsh ----- ���ҽ�ѧ�����
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
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjjxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			hzzyForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			hzzyForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getGjjxjTit();
		List<String[]> resList = service.getGjjxjRes(queryModel,request);
		String xh = DealString.toGBK(hzzyForm.getXh());
		hzzyForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, hzzyForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		hzzyForm.setXm(DealString.toGBK(hzzyForm.getXm()));
		hzzyForm.setXb(DealString.toGBK(hzzyForm.getXb()));
		hzzyForm.setXysh(DealString.toGBK(hzzyForm.getXysh()));
		hzzyForm.setXxsh(DealString.toGBK(hzzyForm.getXxsh()));
		
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", hzzyForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "hzzy_xszz_gjjxj");
		request.setAttribute("tableName", "view_hzzy_xszz_gjjxj");
		return mapping.findForward("gjjxjsh");
	}
	
	/**
	 * ���ҽ�ѧ����Ϣ����
	 * gjjxjExp ----- ���ҽ�ѧ����Ϣ����
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
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, hzzyForm);
		service.getGjjxjExp(queryModel, response,request);
		return mapping.findForward("gjjxjExp");
	}
	
	/**
	 * ���ҽ�ѧ�������ϸҳ��
	 * gjjxjshOne ----- ���ҽ�ѧ�������ϸҳ��
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
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		XszzHzzyService service = new XszzHzzyService();
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxjxx(pkVal);
		hzzyForm.setXysh(stuMap.get("xysh"));
		hzzyForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
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
	 * ������ҽ�ѧ�������Ϣ
	 * gjjxjshSave ---- ������ҽ�ѧ�������Ϣ 
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
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		GjjxjModel gjjxjModel = new GjjxjModel();
		BeanUtils.copyProperties(gjjxjModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveGjjxjShxx(gjjxjModel, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjjxjModel.getXh();
		String nd = gjjxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjjxjxx(pkVal);
		hzzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		hzzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjjxjshSave");
	}

	/**
	 * ������ѧ������ҳ��
	 * gjzxjsq ----- ������ѧ������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HttpSession session = request.getSession();
		XszzHzzyService service = new XszzHzzyService();
		String sUserName = session.getAttribute("userName").toString();//SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();//SESSION�л�ȡ�û�����
		String xh = "";//ѧ��
		xh = StringUtils.isEqual(sUserType, "stu") || StringUtils.isEqual(sUserType, "student")
						? sUserName : Base.chgNull(request.getParameter("xh"), "", 1);//�û�������ѧ����ֱ�ӻ�ȡ�û���
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}
		stuMap.put("nd", Base.currNd);//��ǰ���
		request.setAttribute("sfksq", service.getGjzxjSqQx(sUserType, userDep,
				xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsq");
	}
	
	/**
	 * ���������ѧ��������Ϣ
	 * gjzxjsqSave ---- ���������ѧ��������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveGjzxjSqxx(gjzxjModel, request);// ������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxjModel.getXh();
		String nd = gjzxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("nd", Base.currNd);//��ǰ���
			}
		}
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsqSave");
	}
	
	/**
	 * ������ѧ�������ҳ��
	 * gjzxjsqb ----- ������ѧ�������ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		XszzHzzyService service = new XszzHzzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, hzzyjsxyActionForm);
		stuMap = service.getGjzxjSqb(gjzxjModel,request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxjsqb");
	}
	
	/**
	 * ������ѧ�����ҳ��
	 * gjzxjsh ----- ������ѧ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType").toString();
		String userDep = request.getSession().getAttribute("userDep").toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "", 1));
		
		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxjxx(Base.chgNull(request.getParameter("cbVal"),
					"!!splitOne!!", 1), "��ͨ��", request);
			queryModel.setGo("go");
		}
		
		if (userType.equalsIgnoreCase("xy")) {
			hzzyForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())){
			hzzyForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, hzzyForm);
		List<HashMap<String, String>> topList = service.getGjzxjTit();
		List<String[]> resList = service.getGjzxjRes(queryModel,request);
		String xh = DealString.toGBK(hzzyForm.getXh());
		hzzyForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);
		
		XszzActionUtils commonAction = new XszzActionUtils();//����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, hzzyForm);
		commonAction.appendProperties(request, commenBean, false);//��REQUEST�д��ҳ����ص��б�
		
		hzzyForm.setXm(DealString.toGBK(hzzyForm.getXm()));
		hzzyForm.setXb(DealString.toGBK(hzzyForm.getXb()));
		hzzyForm.setJtzz(DealString.toGBK(hzzyForm.getJtzz()));
		hzzyForm.setXysh(DealString.toGBK(hzzyForm.getXysh()));
		hzzyForm.setXxsh(DealString.toGBK(hzzyForm.getXxsh()));
		hzzyForm.setZxjdj(DealString.toGBK(hzzyForm.getZxjdj()));
		
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", hzzyForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "hzzy_xszz_gjzxj");
		request.setAttribute("tableName", "view_hzzy_xszz_gjzxj");
		return mapping.findForward("gjzxjsh");
	}
	
	/**
	 * ������ѧ����Ϣ����
	 * gjzxjExp ----- ������ѧ����Ϣ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		XszzHzzyService service = new XszzHzzyService();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();
		
		BeanUtils.copyProperties(queryModel, hzzyForm);
		service.getGjzxjExp(queryModel, response,request);
		return mapping.findForward("gjzxjExp");
	}
	
	/**
	 * ������ѧ�������ϸҳ��
	 * gjzxjshOne ----- ������ѧ�������ϸҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzHzzyjsxyActionForm hzzyForm = (XszzHzzyjsxyActionForm) form;
		XszzHzzyService service = new XszzHzzyService();
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		hzzyForm.setXysh(stuMap.get("xysh"));
		hzzyForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshOne");
	}
	
	/**
	 * ���������ѧ�������Ϣ
	 * gjzxjshSave ---- ���������ѧ�������Ϣ 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward gjzxjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();//SESSION�л�ȡ�û�����
		XszzHzzyjsxyActionForm hzzyjsxyActionForm = (XszzHzzyjsxyActionForm)form;
		GjzxjModel gjzxjModel = new GjzxjModel();
		BeanUtils.copyProperties(gjzxjModel, hzzyjsxyActionForm);
		XszzHzzyService service = new XszzHzzyService();
		boolean bJg = service.saveGjzxjShxx(gjzxjModel, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		
		String xh = gjzxjModel.getXh();
		String nd = gjzxjModel.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);
		hzzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		hzzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));
		
		if("admin".equalsIgnoreCase(sUserType) || "xx".equalsIgnoreCase(sUserType)){
			request.setAttribute("userType", "xx");
		} else {
			request.setAttribute("userType", "xy");
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshSave");
	}
}
