package xgxt.studentInfo.sjxy.shgz;

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

import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.basic.BasicAction;

/**
 * Title: ѧ����������ϵͳ Description:����ѧԺ��Ṥ������Action Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-7-19
 */
public class ShgzSjxyAction extends BasicAction {
	/**
	 * �����Ṥ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");

		ShgzForm sForm = (ShgzForm) form;
		ShgzModel model = new ShgzModel();
		BeanUtils.copyProperties(model, sForm);

		// �Ƿ��������ʸ�
		boolean isApply = true;

		// �����ɹ��ж���־
		boolean flag = false;
		// 
		ShgzSjxyService service = new ShgzSjxyService();

		String xh = StringUtils.isEqual("stu", userType) ? userName : request
				.getParameter("xh");

		if (service.isAuditing(xh)) {
			isApply = false;
			request.setAttribute("msg", "������ļ�¼�Ѿ�����ˣ��벻Ҫ�ظ�����");
		}

		if ("add".equalsIgnoreCase(doType) && isApply) {

			// �洢���ݣ��ŵ����ű��У�������ݴ���ִ�и��²���
			if (service.isExists(xh)) {
				flag = service.updateRecord(model);
			} else {
				flag = service.insertRecord(model);
			}

			// ���������ʾ��
			if (flag) {
				request.setAttribute("msg", "�����ɹ�");
			} else {
				request.setAttribute("msg", "����ʧ��");
			}
		}

		Map<String, String> map = service.getStuInfo(xh);

		loadInfo(request, "sjxy_shgzwh.do?method=shgzadd");
		request.setAttribute("option", "add");
		request.setAttribute("rs", map);
		request.setAttribute("isApply", isApply);
		return mapping.findForward("shgzadd");
	}

	/**
	 * ��Ṥ���������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = "view_sjxy_shgz";
		String tableName = "sjxy_shgzb";
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		ShgzForm sForm = (ShgzForm) form;

		HttpSession session = request.getSession();
		String user = getUserType(session);
		String userDep = session.getAttribute("userDep").toString();

		if ("xy".equalsIgnoreCase(user)) {
			sForm.setQueryequals_xydm(userDep);
		}

		if ("sh".equalsIgnoreCase(doType)) {
			String shzd = request.getParameter("shzd");
			String shsj = request.getParameter("shsj");
			String shjg = request.getParameter("shjg");

			if (StringUtils.isNotNull(shjg)) {
				shjg = "tg".equalsIgnoreCase(shjg) ? "ͨ��" : "��ͨ��";
			}

			// ��ȡҳ������primarykey_Ϊ��ʼ������
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);
			valueMap.put(shsj, GetTime.getNowTime());

			// ͨ����˷���
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}

		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = getOutputColumn(request, user);
			this.selectPageDataByPagination(request, sForm, tableName,
					viewName, outputColumn);
		}

		loadInfo(request, "sjxy_shgzwh.do?method=shgzsh");
		request.setAttribute("user", user);
		return mapping.findForward("shgzsh");
	}

	/**
	 * �鿴���޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzViewAndModi(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		boolean isApply = true;
		String user = getUserType(session);
		String option = "view";

		ShgzSjxyService service = new ShgzSjxyService();
		Map<String, String> map = service.getShgzInfo(pkValue);

		ShgzForm sForm = (ShgzForm) form;
		ShgzModel model = new ShgzModel();
		BeanUtils.copyProperties(model, sForm);

		if ("view".equalsIgnoreCase(doType)) {
			isApply = false;
			request.setAttribute("title", "��Ṥ������ - ��Ṥ�����������鿴");

		}

		if ("modi".equalsIgnoreCase(doType)) {
			String xtwsh = request.getParameter("xtwsh");
			if ("xy".equalsIgnoreCase(user) && "ͨ��".equals(xtwsh)) {
				isApply = false;
			}
			option = "modi";
			request.setAttribute("title", "��Ṥ������ - ��Ṥ�������޸�");
		}

		if ("save".equalsIgnoreCase(doType)) {
			boolean flag = service.updateRecord(model);

			request.setAttribute("result", "true");
			if (flag) {
				request.setAttribute("msg", "�����ɹ�");
			} else {
				request.setAttribute("msg", "����ʧ��");
			}
		}

		request.setAttribute("option", option);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("isApply", isApply);
		return mapping.findForward("shgzadd");
	}

	/**
	 * ��Ṥ��������ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */

	public ActionForward shgzcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = "view_sjxy_shgz";
		String tableName = "sjxy_shgzb";
		String doType = request.getParameter("doType");
		String go = request.getParameter("go");
		ShgzForm sForm = (ShgzForm) form;
		
		ShgzSjxyService service = new ShgzSjxyService();

		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		
		String user = getUserType(session);
		String userDep = session.getAttribute("userDep").toString();

		loadInfo(request, "sjxy_shgzwh.do?method=shgzcx");

		if ("stu".equalsIgnoreCase(user)) {
			Map<String, String> map = service.getShgzInfo(userName);
			request.setAttribute("rs", map);
			request.setAttribute("pkValue", userName);
			return mapping.findForward("shgzcxforstu");
		}

		if ("xy".equalsIgnoreCase(user)) {
			sForm.setQueryequals_xydm(userDep);
		}

		if ("del".equalsIgnoreCase(doType)) {
			boolean flag = service.delRecord(sForm.getPkValues());

			if (flag) {
				request.setAttribute("msg", "�����ɹ�");
			} else {
				request.setAttribute("msg", "����ʧ��");
			}
		}

		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = getOutputColumn(request, user);
			this.selectPageDataByPagination(request, sForm, tableName,
					viewName, outputColumn);
		}

		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		request.setAttribute("user", user);
		return mapping.findForward("shgzcx");

	}

	/**
	 * �������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String pkValue = request.getParameter("pkValue");
		String xtwsh = request.getParameter("xtwsh");
		String user = getUserType(session);
		String doType = request.getParameter("doType");
		boolean isApply = true;
		boolean flag = true;
		ShgzSjxyService service = new ShgzSjxyService();
		ShgzModel model = new ShgzModel();
		BeanUtils.copyProperties(model, form);

		// �ϼ�ͨ�����¼��㲻�����
		if ("xy".equalsIgnoreCase(user) && "ͨ��".equalsIgnoreCase(xtwsh)) {
			isApply = false;
		}

		if (isApply && "save".equalsIgnoreCase(doType)) {
			flag = service.updateRecord(model);

			request.setAttribute("result", "true");
			// ���������ʾ��
			if (flag) {
				request.setAttribute("msg", "�����ɹ�");
			} else {
				request.setAttribute("msg", "����ʧ��");
			}
		}

		Map<String, String> map = service.getShgzInfo(pkValue);

		request.setAttribute("option", "shone");
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("rs", map);
		request.setAttribute("nowtime", GetTime.getNowTime());
		request.setAttribute("user", user);
		request.setAttribute("isApply", isApply);
		request.setAttribute("title", "��Ṥ������ - ��Ṥ�������������");
		return mapping.findForward("shgzadd");
	}

	/**
	 * ��������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "sjxy_shgzb";
		String viewName = "view_sjxy_shgz";
		// ��Ҫ�������ֶ�
		String[] output = { "xh", "xm", "xb", "xymc", "zymc", "bjmc", "ftwsh",
				"xtwsh", "ftwshyj", "xtwshyj" };

		expPageData(request, response, tableName, viewName, output);
		return mapping.findForward("");
	}

	/**
	 * ��ӡ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward shgzprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		ShgzSjxyService service = new ShgzSjxyService();

		Map<String, String> shgzInfo = service.getShgzInfo(pkValue);
		List<HashMap<String, String>> rzqkInfo = service.getRzqkInfo(pkValue);
		List<HashMap<String, String>> hjqkInfo = service.getHjqkInfo(pkValue);

		int rzRow = 10;
		int hjRow = 10;
		int rzSize = 0;
		int hjSize = 0;

		if (rzqkInfo != null) {
			rzSize = rzqkInfo.size();
		}

		if (hjqkInfo != null) {
			hjSize = hjqkInfo.size();
		}

		if (rzSize > rzRow) {
			rzRow = rzSize;
		}

		if (hjSize > hjRow) {
			hjRow = hjSize;
		}

		request.setAttribute("rzSize", rzSize);
		request.setAttribute("hjSize", hjSize);
		request.setAttribute("rzRow", rzRow);
		request.setAttribute("hjRow", hjRow);
		request.setAttribute("shgz", shgzInfo);
		request.setAttribute("rzqk", rzqkInfo);
		request.setAttribute("hjqk", hjqkInfo);
		request.setAttribute("str", new String[10]);

		return mapping.findForward("shgzprint");
	}

	/**
	 * ���ҳ������Ҫ�ĵ�һЩ��Ϣ
	 * 
	 * @param request
	 */
	private void loadInfo(HttpServletRequest request, String path) {
		request.setAttribute("path", path);

		String[] writeAndTitle = FormModleCommon.getWriteAbleAndTitle(request);

		request.setAttribute("writeAble", writeAndTitle[0]);
		request.setAttribute("title", writeAndTitle[1]);
		FormModleCommon.setNjXyZyBjList(request);
	}

	/**
	 * ��userType �ֳ����࣬ѧ����ѧԺ��ѧУ
	 * 
	 * @param session
	 * @return
	 */
	private String getUserType(HttpSession session) {
		String userType = (String) session.getAttribute("userType");

		if ("xy".equalsIgnoreCase(userType)) {
			userType = "xy";
		} else if ("admin".equalsIgnoreCase(userType)
				|| ("xx".equalsIgnoreCase(userType))) {
			userType = "xx";
		}
		return userType;
	}

	/**
	 * ��ȡ��ѯ��ʾ���ֶ�
	 * 
	 * @param request
	 * @param userType
	 * @return
	 */
	private String[] getOutputColumn(HttpServletRequest request, String userType) {
		String[] outputColumn = new String[] { "pkValue", "disabled", "xh",
				"xm", "xb", "xymc", "zymc","bjmc","ftwsh", "xtwsh" };

		// ��ȡ�ϼ��Ƿ����ͨ����Ϣ
		if ("xy".equalsIgnoreCase(userType)) {
			request
					.setAttribute("clientColumns",
							"(case xtwsh when 'ͨ��' then 'disabled' else '' end) disabled,");
		} else {
			request.setAttribute("clientColumns", "'' disabled,");
		}
		return outputColumn;
	}
}
