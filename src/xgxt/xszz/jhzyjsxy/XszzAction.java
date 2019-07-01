
package xgxt.xszz.jhzyjsxy;

import java.lang.reflect.InvocationTargetException;
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
import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;
import xgxt.xszz.XszzActionUtils;
import xgxt.xszz.XszzCommenBean;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ��ְҵ����ѧԺѧ������Action</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-09-25</p>
 */
public class XszzAction extends BaseAction {

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
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();// SESSION�л�ȡ�û�����
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
			stuMap = service.getKnsxx(pkVal);// �õ���ϸ��Ϣ

			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}

		String[] knlxJt = { "A���������ٱ�ɽ�������", "B��������������Ϊ�屣������������ͥ��",
				"C����ͥ��Ա����18��55����׳���Ͷ�����", "D�����������������ϵĳ����ͥ��", "E����ʿ��Ů��",
				"F���¶��򾭼����ѵ��׼�ͥ��", "G����ͥ�������������ϳ�Ա�����ܷ����������",
				"H����ͥ��Ա��м��򼲲���ɥʧ�Ͷ�������", "I����ͥ��Ա���ش󼲲���֧�����ҽ�Ʒ��ã�",
				"J����ͥ����Ա������ͻ�����ֱ䣨����Ȼ�ֺ��ȣ�����������Ʋ��ش���ʧ��",
				"K����ĸ���쵼�¼�ͥ�������������½��� ", "L���м�ѧ����", "M�����¼�ͥ�������ѵ��������;" };
		String[] knlxAll = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M" };
		String[] knlxList = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0" };
		ArrayList<String[]> list = new ArrayList<String[]>();
		char[] knlxChar = stuMap.get("knlx") != null ? stuMap.get("knlx")
				.toCharArray() : "".toCharArray();

		for (int j = 0; j < knlxAll.length; j++) {
			for (int i = 0; i < knlxChar.length; i++) {
				if (String.valueOf(knlxChar[i]).equalsIgnoreCase(knlxAll[j])) {
					knlxList[j] = "1";
					break;
				}
			}
			String[] sT = { knlxList[j], knlxJt[j] };
			list.add(sT);
		}
		request.setAttribute("knAll", list);
		request.setAttribute("type", Base.chgNull(request.getParameter("type"),
				"", 1));
		stuMap.put("nd", Base.currNd);// ��ǰ���
		request.setAttribute("sfksq", service.getKnsSqQx("������", sUserType,
				userDep, xh, Base.currNd));
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
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		KnssqbModel knsModel = new KnssqbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
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
				stuMap.put("nd", Base.currNd);// ��ǰ���
			}
		}

		String[] knlxJt = { "A���������ٱ�ɽ�������", "B��������������Ϊ�屣������������ͥ��",
				"C����ͥ��Ա����18��55����׳���Ͷ�����", "D�����������������ϵĳ����ͥ��", "E����ʿ��Ů��",
				"F���¶��򾭼����ѵ��׼�ͥ��", "G����ͥ�������������ϳ�Ա�����ܷ����������",
				"H����ͥ��Ա��м��򼲲���ɥʧ�Ͷ�������", "I����ͥ��Ա���ش󼲲���֧�����ҽ�Ʒ��ã�",
				"J����ͥ����Ա������ͻ�����ֱ䣨����Ȼ�ֺ��ȣ�����������Ʋ��ش���ʧ��",
				"K����ĸ���쵼�¼�ͥ�������������½��� ", "L���м�ѧ����", "M�����¼�ͥ�������ѵ��������;" };
		String[] knlxAll = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M" };
		String[] knlxList = { "0", "0", "0", "0", "0", "0", "0", "0", "0", "0",
				"0", "0", "0" };
		ArrayList<String[]> list = new ArrayList<String[]>();
		char[] knlxChar = stuMap.get("knlx") != null ? stuMap.get("knlx")
				.toCharArray() : "".toCharArray();

		for (int j = 0; j < knlxAll.length; j++) {
			for (int i = 0; i < knlxChar.length; i++) {
				if (String.valueOf(knlxChar[i]).equalsIgnoreCase(knlxAll[j])) {
					knlxList[j] = "1";
					break;
				}
			}
			String[] sT = { knlxList[j], knlxJt[j] };
			list.add(sT);
		}
		request.setAttribute("knAll", list);

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

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnssqbModel knsModel = new KnssqbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
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

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsxx(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("kn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(jhzyjsxyActionForm.getPk(), "����", request);
			queryModel.setGo("go");
		}
		if ("tskn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(jhzyjsxyActionForm.getPk(), "��������", request);
			queryModel.setGo("go");
		}
		if ("bkn".equalsIgnoreCase(queryModel.getGo())) {
			service.modKnsxx(jhzyjsxyActionForm.getPk(), "������", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getKnsRes(queryModel, request,
				jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));
		jhzyjsxyActionForm.setFdysh(DealString.toGBK(jhzyjsxyActionForm
				.getFdysh()));
		jhzyjsxyActionForm.setXysh(DealString.toGBK(jhzyjsxyActionForm
				.getXysh()));
		jhzyjsxyActionForm.setXxsh(DealString.toGBK(jhzyjsxyActionForm
				.getXxsh()));

		jhzyjsxyActionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsResNum(queryModel, request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_jhzy_kns");
		request.setAttribute("tableName", "view_xszz_jhzy_kns");
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

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
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
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);

		String[] knlxJt = { "A���������ٱ�ɽ�������", "B��������������Ϊ�屣������������ͥ��",
				"C����ͥ��Ա����18��55����׳���Ͷ�����", "D�����������������ϵĳ����ͥ��", "E����ʿ��Ů��",
				"F���¶��򾭼����ѵ��׼�ͥ��", "G����ͥ�������������ϳ�Ա�����ܷ����������",
				"H����ͥ��Ա��м��򼲲���ɥʧ�Ͷ�������", "I����ͥ��Ա���ش󼲲���֧�����ҽ�Ʒ��ã�",
				"J����ͥ����Ա������ͻ�����ֱ䣨����Ȼ�ֺ��ȣ�����������Ʋ��ش���ʧ��",
				"K����ĸ���쵼�¼�ͥ�������������½��� ", "L���м�ѧ����", "M�����¼�ͥ�������ѵ��������;" };
		String[] knlxAll = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M" };
		String[] knlxList = { "", "", "", "", "", "", "", "", "", "", "", "",
				"" };
		ArrayList<String[]> list = new ArrayList<String[]>();
		char[] knlxChar = stuMap.get("knlx") != null ? stuMap.get("knlx")
				.toCharArray() : "".toCharArray();

		for (int j = 0; j < knlxAll.length; j++) {
			for (int i = 0; i < knlxChar.length; i++) {
				if (String.valueOf(knlxChar[i]).equalsIgnoreCase(knlxAll[j])) {
					knlxList[j] = "��";
					break;
				}
			}
			String[] sT = { knlxList[j], knlxJt[j] };
			list.add(sT);
		}
		request.setAttribute("knAll", list);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
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
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		KnssqbModel knsModel = new KnssqbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
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

		String[] knlxJt = { "A���������ٱ�ɽ�������", "B��������������Ϊ�屣������������ͥ��",
				"C����ͥ��Ա����18��55����׳���Ͷ�����", "D�����������������ϵĳ����ͥ��", "E����ʿ��Ů��",
				"F���¶��򾭼����ѵ��׼�ͥ��", "G����ͥ�������������ϳ�Ա�����ܷ����������",
				"H����ͥ��Ա��м��򼲲���ɥʧ�Ͷ�������", "I����ͥ��Ա���ش󼲲���֧�����ҽ�Ʒ��ã�",
				"J����ͥ����Ա������ͻ�����ֱ䣨����Ȼ�ֺ��ȣ�����������Ʋ��ش���ʧ��",
				"K����ĸ���쵼�¼�ͥ�������������½��� ", "L���м�ѧ����", "M�����¼�ͥ�������ѵ��������;" };
		String[] knlxAll = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
				"K", "L", "M" };
		String[] knlxList = { "", "", "", "", "", "", "", "", "", "", "", "",
				"" };
		ArrayList<String[]> list = new ArrayList<String[]>();
		char[] knlxChar = stuMap.get("knlx") != null ? stuMap.get("knlx")
				.toCharArray() : "".toCharArray();

		for (int j = 0; j < knlxAll.length; j++) {
			for (int i = 0; i < knlxChar.length; i++) {
				if (String.valueOf(knlxChar[i]).equalsIgnoreCase(knlxAll[j])) {
					knlxList[j] = "��";
					break;
				}
			}
			String[] sT = { knlxList[j], knlxJt[j] };
			list.add(sT);
		}
		request.setAttribute("knAll", list);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(19));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsshSave");
	}

	/**
	 * �������ǼǱ�ҳ�� knsdjb ----- �������ǼǱ�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();// SESSION�л�ȡ�û�����
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
			request.setAttribute("isKns", dao.isKns(xh));
			stuMap = service.getKnsdjbxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}

		stuMap.put("nd", Base.currNd);// ��ǰ���
		request.setAttribute("type", Base.chgNull(request.getParameter("type"),
				"", 1));
		request.setAttribute("sfksq", service.getKnsSqQx("�������ǼǱ�", sUserType,
				userDep, xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsdjb");
	}

	/**
	 * �����������ǼǱ���Ϣ knsdjbSave ---- �����������ǼǱ���Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjbSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		KnsdjbModel knsModel = new KnsdjbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveKnsdjbxx(knsModel, request);// ���������Ϣ
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
			stuMap = service.getKnsdjbxx(pkVal);// �õ���������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("nd", Base.currNd);// ��ǰ���
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("knsdjbSave");
	}

	/**
	 * �������ǼǱ�ҳ�� knsdjbdy ----- �������ǼǱ�ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjbdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		KnsdjbModel knsModel = new KnsdjbModel();
		BeanUtils.copyProperties(knsModel, jhzyjsxyActionForm);
		stuMap = service.getKnsdjbdy(knsModel, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("knsdjbdy");
	}

	/**
	 * �������ǼǱ�����ά��ҳ�� knsdjbsh ----- �������ǼǱ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjbsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delKnsdjb(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getKnsdjbTit();
		List<String[]> resList = service.getKnsdjbRes(queryModel, request,
				jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));

		jhzyjsxyActionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getKnsdjbResNum(queryModel, request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xg_zz_knsdjb");
		request.setAttribute("tableName", "view_xg_zz_knsdjb");
		return mapping.findForward("knsdjbsh");
	}

	/**
	 * �������ǼǱ��� knsdjbExp ----- �������ǼǱ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward knsdjbExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		service.getKnsdjbExp(queryModel, response, request);
		return mapping.findForward("knsdjbExp");
	}

	/**
	 * ������ѧ������ҳ�� gjzxjsq -----������ѧ������ҳ��
	 * 
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
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();// SESSION�л�ȡ�û�����
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
			request.setAttribute("isKns", dao.isKns(xh));
			stuMap = service.getGjzxjxx(pkVal);// �õ���ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
			}
		}

		stuMap.put("nd", Base.currNd);// ��ǰ���
		request.setAttribute("type", Base.chgNull(request.getParameter("type"),
				"", 1));
		request.setAttribute("sfksq", service.getKnsSqQx("������ѧ��", sUserType,
				userDep, xh, Base.currNd));
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsq");
	}

	/**
	 * ���������ѧ��������Ϣ gjzxjsqSave ---- ���������ѧ��������Ϣ
	 * 
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
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		GjzxjModel model = new GjzxjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveGjzxjSqxx(model, request);// ���������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String nd = model.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getGjzxjxx(pkVal);// �õ�������ѧ����ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("nd", Base.currNd);// ��ǰ���
			}
		}

		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjsqSave");
	}

	/**
	 * ������ѧ�������ҳ�� gjzxjsqb ----- ������ѧ�������ҳ��
	 * 
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

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		GjzxjModel model = new GjzxjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		stuMap = service.getGjzxjSqb(model, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjzxjsqb");
	}

	/**
	 * ������ѧ�����ҳ�� gjzxjsh ----- ������ѧ�����
	 * 
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

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delGjzxj(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj(jhzyjsxyActionForm.getPk(), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modGjzxj(jhzyjsxyActionForm.getPk(), "��ͨ��", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getGjzxjTit();
		List<String[]> resList = service.getGjzxjRes(queryModel, request,
				jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));

		jhzyjsxyActionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getGjzxjResNum(queryModel, request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_jhzy_gjzxj");
		request.setAttribute("tableName", "view_xszz_jhzy_gjzxj");
		return mapping.findForward("gjzxjsh");
	}

	/**
	 * ������ѧ����Ϣ���� gjzxjExp ----- ������ѧ����Ϣ����
	 * 
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

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		QueryModel queryModel = new QueryModel();

		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		service.getGjzxjExp(queryModel, response, request);
		return mapping.findForward("gjzxjExp");
	}

	/**
	 * ������ѧ�������ϸҳ�� gjzxjshOne ----- ������ѧ�������ϸҳ��
	 * 
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
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshOne");
	}

	/**
	 * ���������ѧ�������Ϣ gjzxjshSave ---- ���������ѧ�������Ϣ
	 * 
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
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		GjzxjModel model = new GjzxjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveGjzxjShxx(model, request);// ���������ѧ�������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String nd = model.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getGjzxjxx(pkVal);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("gjzxjList", service.getGjzxjList());
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjzxjshSave");
	}

	/**
	 * ������־��ѧ������
	 */
	public ActionForward gjlzjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		HttpSession session = request.getSession();
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();// SESSION�л�ȡ�û�����
		String doType = request.getParameter("doType");
		String type = request.getParameter("type");
		XszzGjlzjxjModel model = new XszzGjlzjxjModel();
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		xh = pkVal.substring(4);

		jhzyjsxyActionForm.setSqly(DealString.toGBK(jhzyjsxyActionForm
				.getSqly()));
		jhzyjsxyActionForm.setChhzjl(DealString.toGBK(jhzyjsxyActionForm
				.getChhzjl()));
		jhzyjsxyActionForm.setJthk(DealString.toGBK(jhzyjsxyActionForm
				.getJthk()));
		jhzyjsxyActionForm.setJtyzsr(DealString.toGBK(jhzyjsxyActionForm
				.getJtyzsr()));
		jhzyjsxyActionForm.setJtzrks(DealString.toGBK(jhzyjsxyActionForm
				.getJtzrks()));
		jhzyjsxyActionForm.setRjysr(DealString.toGBK(jhzyjsxyActionForm
				.getRjysr()));
		jhzyjsxyActionForm.setSrly(DealString.toGBK(jhzyjsxyActionForm
				.getSrly()));
		jhzyjsxyActionForm.setJtzz(DealString.toGBK(jhzyjsxyActionForm
				.getJtzz()));
		boolean isKns = true;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			isKns = service.isKns(xh);
			stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
		}
		if ("save".equalsIgnoreCase(doType)) {// ���ݱ���
			BeanUtils.copyProperties(model, jhzyjsxyActionForm);
			service.serv_saveGjlzjxjSq(model, request);
		}
		stuMap.put("nd", Base.currNd);// ��ǰ���
		request.setAttribute("sfksq", service.getKnsSqQx("������־��ѧ��", sUserType,
				userDep, xh, Base.currNd));
		request.setAttribute("rsJxj", service.serv_getGjlzJxjInfo(Base.currNd
				+ xh));
		request.setAttribute("rs", stuMap);
		request.setAttribute("isKns", isKns);
		request.setAttribute("pkVal", pkVal);
		request.setAttribute("type", type);
		return mapping.findForward("gjlzjxjsq");
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

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();
		XszzGjlzjxjModel model = new XszzGjlzjxjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		stuMap = service.getGjlzjxjSqb(model, request);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("gjlzjxjsqb");
	}

	/**
	 * ������־��ѧ�����
	 * 
	 * @throws InvocationTargetException
	 * @throws Exception
	 */
	public ActionForward gjlzjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception, InvocationTargetException {
		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.serv_delGjlzjxjxx(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.serv_modGjlzJxjsxx(jhzyjsxyActionForm.getPk(), "ͨ��",
					request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.serv_modGjlzJxjsxx(jhzyjsxyActionForm.getPk(), "��ͨ��",
					request);
			queryModel.setGo("go");
		}
		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.ser_getGjlzJxjTit();
		List<String[]> resList = service.serv_getGjlzJxjRes(queryModel,
				request, jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));
		jhzyjsxyActionForm.setFdysh(DealString.toGBK(jhzyjsxyActionForm
				.getFdysh()));
		jhzyjsxyActionForm.setXysh(DealString.toGBK(jhzyjsxyActionForm
				.getXysh()));
		jhzyjsxyActionForm.setXxsh(DealString.toGBK(jhzyjsxyActionForm
				.getXxsh()));

		jhzyjsxyActionForm.getPages()
				.setMaxRecord(
						Integer.parseInt(service.getGjlzJxjResNum(queryModel,
								request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_jhzy_gjlzjxj");
		request.setAttribute("tableName", "view_xszz_jhzy_gjlzjxj");
		return mapping.findForward("gjlzjxjsh");
	}

	/**
	 * ������־��ѧ�������ϸҳ��
	 */
	public ActionForward gjlzjxjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		XszzGjlzjxjModel model = new XszzGjlzjxjModel();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);
		String doType = request.getParameter("doType");
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		if ("save".equalsIgnoreCase(doType)) {
			BeanUtils.copyProperties(model, jhzyjsxyActionForm);
			boolean bJg = service.serv_saveGjlzjxjSh(model, request);
			if (bJg) {
				request.setAttribute("ok", "ok");
			} else {
				request.setAttribute("ok", "no");
			}
		}

		HashMap<String, String> stuMap = new HashMap<String, String>();
		HashMap<String, String> stuGjlzjxjMap = new HashMap<String, String>();
		stuGjlzjxjMap = service.serv_getGjlzJxjInfo(pkVal);
		;
		stuMap = service.getStu(stuGjlzjxjMap.get("xh"));
		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}
		request.setAttribute("rs", stuMap);
		request.setAttribute("rsJxj", stuGjlzjxjMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("gjlzjxjshOne");
	}

	/**
	 * ������ѧ������ҳ�� bkzxjjsq -----������ѧ������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		XszzJhzyService service = new XszzJhzyService();
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userDep = session.getAttribute("userDep").toString();// SESSION�л�ȡ�û�����
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
			if (service.isKns(xh)) {
				stuMap = service.getBkzxjjXx(pkVal);// �õ���ϸ��Ϣ

				if (stuMap.size() == 0) {
					stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				}
				if (service.isBjgkm(nd, xh)) {
					String msg = "�����п�Ŀ�����񣬲��ܽ�������!";
					request.setAttribute("msg", msg);
				}
			} else {
				String msg = "����Ŀǰ���������������ܽ�������!";
				request.setAttribute("msg", msg);
			}
		}

		stuMap.put("nd", Base.currNd);// ��ǰ���
		request.setAttribute("sfksq", service.getKnsSqQx("������ѧ����", sUserType,
				userDep, xh, Base.currNd));
		request.setAttribute("type", request.getParameter("type"));
		request.setAttribute("rs", stuMap);
		request.setAttribute("zzdjList", service.getZzdjList());
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bkzxjjsq");
	}

	/**
	 * ���������ѧ����������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjsqSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		BkzxjjModel model = new BkzxjjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveBkzxjjSqxx(model, request);// ������ѧ����������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}

		String xh = model.getXh();
		String nd = model.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		if (!"".equalsIgnoreCase(xh)) {
			stuMap = service.getBkzxjjXx(pkVal);// �õ���������ϸ��Ϣ
			if (stuMap.size() == 0) {
				stuMap = service.getStu(xh);// �õ�ѧ����Ϣ
				stuMap.put("nd", Base.currNd);// ��ǰ���
			}
		}

		request.setAttribute("zzdjList", service.getZzdjList());
		request.setAttribute("sfksq", "1");
		request.setAttribute("rs", stuMap);
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bkzxjjsqSave");
	}

	/**
	 * ������ѧ�������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyService service = new XszzJhzyService();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		String userType = request.getSession().getAttribute("userType")
				.toString();
		String userDep = request.getSession().getAttribute("userDep")
				.toString();
		QueryModel queryModel = new QueryModel();
		queryModel.setGo(Base.chgNull(request.getParameter("go"), "", 1));
		queryModel.setIsQuery(Base.chgNull(request.getParameter("isQuery"), "",
				1));

		if ("del".equalsIgnoreCase(queryModel.getGo())) {
			service.delBkzxjj(jhzyjsxyActionForm.getPk(), request);
			queryModel.setGo("go");
		}
		if ("tg".equalsIgnoreCase(queryModel.getGo())) {
			service.modBkzxjjxx(jhzyjsxyActionForm.getPk(), "ͨ��", request);
			queryModel.setGo("go");
		}
		if ("btg".equalsIgnoreCase(queryModel.getGo())) {
			service.modBkzxjjxx(jhzyjsxyActionForm.getPk(), "��ͨ��", request);
			queryModel.setGo("go");
		}

		if (userType.equalsIgnoreCase("xy")) {
			jhzyjsxyActionForm.setXydm(userDep);
		}
		if (!"is".equalsIgnoreCase(queryModel.getIsQuery())) {
			jhzyjsxyActionForm.setNd(Base.currNd);
		}
		BeanUtils.copyProperties(queryModel, jhzyjsxyActionForm);
		List<HashMap<String, String>> topList = service.getKnsTit();
		List<String[]> resList = service.getBkzzjjRes(queryModel, request,
				jhzyjsxyActionForm);
		String xh = DealString.toGBK(jhzyjsxyActionForm.getXh());
		jhzyjsxyActionForm.setXh(xh);
		request.setAttribute("topTr", topList);
		request.setAttribute("rs", resList);
		request.setAttribute("rsNum", resList != null ? resList.size() : 0);

		XszzActionUtils commonAction = new XszzActionUtils();// ����ACTION
		XszzCommenBean commenBean = new XszzCommenBean();
		BeanUtils.copyProperties(commenBean, jhzyjsxyActionForm);
		commonAction.appendProperties(request, commenBean, true);// ��REQUEST�д��ҳ����ص��б�

		jhzyjsxyActionForm.setXm(DealString.toGBK(jhzyjsxyActionForm.getXm()));
		jhzyjsxyActionForm.setXb(DealString.toGBK(jhzyjsxyActionForm.getXb()));
		jhzyjsxyActionForm.setFdysh(DealString.toGBK(jhzyjsxyActionForm
				.getFdysh()));
		jhzyjsxyActionForm.setXysh(DealString.toGBK(jhzyjsxyActionForm
				.getXysh()));
		jhzyjsxyActionForm.setXxsh(DealString.toGBK(jhzyjsxyActionForm
				.getXxsh()));

		jhzyjsxyActionForm.getPages().setMaxRecord(
				Integer.parseInt(service.getBkzzjjResNum(queryModel, request)));
		request.setAttribute("pk", "nd||xh");
		request.setAttribute("hForm", jhzyjsxyActionForm);
		request.setAttribute("isQuery", queryModel.getIsQuery());
		request.setAttribute("realTable", "xszz_jhzy_bkzxjj");
		request.setAttribute("tableName", "view_xszz_jhzy_bkzxjj");
		return mapping.findForward("bkzxjjsh");
	}

	/**
	 * ������ѧ�����ϸҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjshOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DAO dao = DAO.getInstance();
		HttpSession session = request.getSession();
		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);

		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getBkzxjjXx(pkVal);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("zzdjList", service.getZzdjList());
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bkzxjjshOne");
	}

	/**
	 * ���������ѧ���������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjshSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		DAO dao = DAO.getInstance();
		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String userName = request.getSession().getAttribute("userName")
				.toString();
		ArrayList<String> userBj = new ArrayList<String>();
		userBj = dao.getUserBj(userName);

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		BkzxjjModel model = new BkzxjjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		XszzJhzyService service = new XszzJhzyService();
		boolean bJg = service.saveBkzxjjShxx(model, request);// ���������������Ϣ
		if (bJg) {
			request.setAttribute("ok", "ok");
		} else {
			request.setAttribute("ok", "no");
		}
		String xh = model.getXh();
		String nd = model.getNd();
		String pkVal = nd + xh;
		HashMap<String, String> stuMap = new HashMap<String, String>();
		stuMap = service.getKnsxx(pkVal);

		jhzyjsxyActionForm.setFdysh(stuMap.get("fdysh"));
		jhzyjsxyActionForm.setXysh(stuMap.get("xysh"));
		jhzyjsxyActionForm.setXxsh(stuMap.get("xxsh"));

		if ("admin".equalsIgnoreCase(sUserType)
				|| "xx".equalsIgnoreCase(sUserType)) {
			request.setAttribute("userType", "xx");
		} else {
			if (userBj.size() == 0) {
				request.setAttribute("userType", "xy");
			} else {
				request.setAttribute("userType", "fdy");
			}
		}

		request.setAttribute("zzdjList", service.getZzdjList());
		request.setAttribute("rs", stuMap);
		request.setAttribute("chkList", dao.getChkList(3));
		request.setAttribute("pkVal", pkVal);
		return mapping.findForward("bkzxjjshSave");
	}

	/**
	 * ������ѧ���������ҳ�� knssqb ----- �����������ҳ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bkzxjjsqb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XszzJhzyjsxyActionForm jhzyjsxyActionForm = (XszzJhzyjsxyActionForm) form;
		XszzJhzyService service = new XszzJhzyService();
		HashMap<String, String> stuMap = new HashMap<String, String>();

		HttpSession session = request.getSession();

		String sUserType = session.getAttribute("userType").toString();// SESSION�л�ȡ�û�����
		String sUserName = session.getAttribute("userName").toString();// SESSION�л�ȡ�û���

		BkzxjjModel model = new BkzxjjModel();
		BeanUtils.copyProperties(model, jhzyjsxyActionForm);
		String xh = "";// ѧ��
		xh = StringUtils.isEqual(sUserType, "stu")
				|| StringUtils.isEqual(sUserType, "student") ? sUserName : Base
				.chgNull(request.getParameter("xh"), "", 1);// �û�������ѧ����ֱ�ӻ�ȡ�û���
		String nd = Base.currNd;
		String pkVal = Base.chgNull(request.getParameter("pkVal"), "", 1);
		pkVal = pkVal.equalsIgnoreCase("") ? nd + xh : pkVal;
		stuMap = service.getBkzxjjXx(pkVal);// �õ���ϸ��Ϣ
		if (stuMap.size() == 0) {
			String msg = "������δ�ύ���룡��";
			request.setAttribute("msg", msg);
		}
		if (Base.isNull(stuMap.get("nd"))) {
			stuMap.put("nd", "    ");
		}
		String zxjjdm = stuMap.get("zxjjdm");
		String zxjjxx = service.getZxjjXx(zxjjdm);
		stuMap.put("zxjjxx", zxjjxx);
		request.setAttribute("rs", stuMap);
		return mapping.findForward("bkzxjjsqb");
	}
}
