package xgxt.pjpy.ghxy.ryjf.bjbzf;

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

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.dtjs.gdby.tygl.BasicExtendService;
import xgxt.pjpy.ghxy.ryjf.PjpyRyjfForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;
import xgxt.utils.FormModleCommon;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ Description:���ѧԺ�༶���÷�Action Copyright: Copyright (c) 2010
 * Company: zfsoft Author: sjf Version: 1.0 Time: 2010-08-06
 */
public class BjbzfGhxyAction extends BasicExtendAction {
	/**
	 * ���ѧԺ�༶���÷�¼��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzflr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String user = getUserType(request);
		if(!"fdy".equalsIgnoreCase(user)){
			request.setAttribute("errMsg", "�����Ǹ���Ա�û���û�и�ģ��Ȩ�ޣ�");
			return mapping.findForward("error");
		}
		String userName = session.getAttribute("userName").toString();
		
		BjbzfGhxyService service = new BjbzfGhxyService();
		
		setWriteAbleAndTitle(request, "pjpybjbzflr.do");
		String[] output = new String[] { "bjdm", "bjmc" };

		//		String user = getUserType(request);
		
		// ��ȡ��������
		request.setAttribute("topTr", DAO.getInstance().getColumnNameCN(output,
				"view_fdybbj"));
		request.setAttribute("rs", service.getFdyBjInfo(userName, output));
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", BasicExtendService.xqMap.get(Base.currXq));
		return mapping.findForward("bjbzflr");
	}

	/**
	 * ���ѧԺ�༶���÷ֵ���¼��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzflrone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		BjbzfGhxyService service = new BjbzfGhxyService();

		// �༶��ѧ�꣬ѧ�ڣ��¶�
		String bjdm = request.getParameter("bjdm");
		String xn = Base.currXn;
		String xq = Base.currXq;
		String yd = request.getParameter("yd");

		if ("save".equalsIgnoreCase(doType)) {
			PjpyRyjfForm pForm = (PjpyRyjfForm) form;
			BjbzfModel model = new BjbzfModel();
			BeanUtils.copyProperties(model, pForm);
			
			// ��ʼ���ύ��ѧ�� 
			model.setXh(request.getParameterValues("xhs"));
			String msg = service.insertRecord(model);
			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("rs", service.getLrInfo(bjdm, xn, xq, yd));
		request.setAttribute("djList", service.getBzdj());
		
		request.setAttribute("bjdm", bjdm);
		request.setAttribute("xn", xn);
		request.setAttribute("xq", xq);
		request.setAttribute("xqmc", BasicExtendService.xqMap.get(xq));
		request.setAttribute("yd", yd);
		return mapping.findForward("bjbzflrone");
	}

	/**
	 * ���ѧԺ�༶���÷����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "ghxy_bjbzfb";
		String viewName = "view_ghxy_bjbzf";
		// ��Ҫ���еĲ���
		String doType = request.getParameter("doType");

		String go = request.getParameter("go");

		// ��ȡuserType
		String user = getUserType(request);

		PjpyRyjfForm gForm = (PjpyRyjfForm) form;
		
		if(!("njbzr".equalsIgnoreCase(user) || "xx".equalsIgnoreCase(user))){
			request.setAttribute("errMsg", "�Բ���,��û�и�ģ��Ȩ�ޣ�");
			return mapping.findForward("error");
		}
		
		// ��˲���
		if ("sh".equalsIgnoreCase(doType)) {
			// ����ֶ�
			String shzd = request.getParameter("shzd");

			// ��˽��
			String shjg = request.getParameter("shjg");

			if (!StringUtils.isNull(shjg)) {
				shjg = "tg".equalsIgnoreCase(shjg) ? "ͨ��" : "��ͨ��";
			}

			// ��ȡҳ������primarykey_Ϊ��ʼ������
			HashMap<String, String[]> primaryMap = getValueArrayMap(request,
					PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);

			// ͨ����˷���
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}
		// ����ҳ���е�������ȡ����
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[] { "pkValue", "disabled", "xh",
					"xm", "nj","yd","bjmc", "bzdj", "njbzrsh", "xxsh" };
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "pjpybjbzfsh.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", BasicExtendService.xqMap.get(Base.currXq));
		request.setAttribute("userType", user);

		return mapping.findForward("bjbzfsh");
	}

	/**
	 * ���ѧԺ�༶���÷ֲ�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userName = session.getAttribute("userName").toString();
		String tableName = "ghxy_bjbzfb";
		String viewName = "view_ghxy_bjbzf";

		// ��Ҫ���еĲ���
		String doType = request.getParameter("doType");

		String go = request.getParameter("go");

		// ��ȡuserType
		String user = getUserType(request);

		PjpyRyjfForm gForm = (PjpyRyjfForm) form;

//		BjbzfGhxyService service = new BjbzfGhxyService();

		setWriteAbleAndTitle(request, "pjpybjbzfcx.do");

		if ("stu".equalsIgnoreCase(user)) {
//			String[] output = new String[] { "xh", "xm", "xn", "nj", "xq",
//					"xymc", "zymc", "bjmc", "fdysh", "njbzrsh", "xxsh", "ryjf" };
//
//			// ��������ֶ���
//			request.setAttribute("topTr", DAO.getInstance().getColumnNameCN(
//					output, viewName));
//
//			return mapping.findForward("grjfcxforstu");
			gForm.setQuerylike_xh(userName);
		}

		// ɾ������
		if ("del".equalsIgnoreCase(doType)) {
			this.deleteOperation(request, tableName);
		}

		// ����ҳ���е�������ȡ����
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[] { "pkValue", "disabled", "xh",
					"xm", "nj","yd","bjmc","bzf", "bzdj", "njbzrsh", "xxsh" };
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}

		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("bjbzfcx");
	}

	/**
	 * ��ȡ�û�����
	 * 
	 * @param request
	 * @return
	 */
	private String getUserType(HttpServletRequest request) {
		HttpSession session = request.getSession();
		GhxyNjzrwhService service = new GhxyNjzrwhService();

		List<HashMap<String, String>> list = service.getFdyNj(session
				.getAttribute("userName").toString());
		String userType = session.getAttribute("userType").toString();

		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy")
				.toString());
		if (isFdy) {
			userType = "fdy";
		} else if (list != null && list.size() > 0) {
			userType = "njbzr";
			// ������꼶�����οɲ�ѯ���꼶
			request.setAttribute("njListForBzr", list);
		} else if ("admin".equalsIgnoreCase(userType)
				|| ("xx".equalsIgnoreCase(userType))) {
			userType = "xx";
		}
		
		return userType;
	}
	
	/**
	 * ���ѧԺ�༶���÷ֵ����鿴���޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzfViewAndModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String user = getUserType(request);
		String doType = request.getParameter("doType");
		// ��ghxy_grjfb��������xh||xn||xq
		String pkValue = request.getParameter("pkValue");
		
		BjbzfGhxyService service = new BjbzfGhxyService();
		Map<String, String> bjbzOne = service.getBjbzOne(pkValue);
		
		String operation = "modi".equalsIgnoreCase(doType) ? "modi" : "view";
		
		if("modi".equalsIgnoreCase(doType)){
			List<HashMap<String, String>> djList = service.getBzdj();
			request.setAttribute("djList", djList);
		}
		
		if("save".equalsIgnoreCase(doType)){
			this.updateOperation(request, "ghxy_bjbzfb");
//			PjpyRyjfForm pForm = (PjpyRyjfForm)form;
//			BjbzfModel model = new BjbzfModel();
//			BeanUtils.copyProperties(model, pForm);
//			
//			String msg = service.updateRecord(model) ? "�޸ĳɹ�" : "�޸�ʧ��"; 
//			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("rs", bjbzOne);
		request.setAttribute("operation", operation);
		request.setAttribute("userType", user);
		return mapping.findForward("bjbzfview");
	}

	/**
	 * ��ʾ��Ҫ��ȡ���ݿ���ֶΰ��������ֶ�,
	 * @param request
	 * @param user
	 * @return
	 */
	protected void setDisabledField(HttpServletRequest request, String user){
		// ��ȡ�ϼ��Ƿ����ͨ����Ϣ�����ظ�ҳ��
		
		if("fdy".equalsIgnoreCase(user) || "stu".equalsIgnoreCase(user) || "xy".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case njbzrsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else if("njbzr".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else{
			request.setAttribute("clientColumns", "'' disabled,");
		}
	}

	/**
	 * ���ѧԺ�༶���÷ֵ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzfshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String user = getUserType(request);
		String doType = request.getParameter("doType");
		
		// ��ghxy_bjbzfb��������xh||xn||xq||yd
		String pkValue = request.getParameter("pkValue");

		BjbzfGhxyService service = new BjbzfGhxyService();

		
		if("save".equalsIgnoreCase(doType)){
			PjpyRyjfForm pForm = (PjpyRyjfForm)form;
			BjbzfModel model = new BjbzfModel();
			BeanUtils.copyProperties(model, pForm);
			String msg = service.updateRecord(model) ? "��˳ɹ�" : "���ʧ��"; 
			request.setAttribute("msg", msg);
		}
		
		Map<String, String> bjbzOne = service.getBjbzOne(pkValue);
		
		request.setAttribute("rs", bjbzOne);
		
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", user);
		return mapping.findForward("bjbzfshone");
	}
	
	 /** ���ѧԺ�༶���÷ֵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzfExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "ghxy_bjbzfb";
		String viewName = "view_ghxy_bjbzf";
		// ��Ҫ�������ֶ�
			String[] output = { "xh","xm","nj","xn","xq","yd","bjmc","njbzrsh","xxsh","bzdj","bzf"};
		
		expPageData(request, response, tableName,viewName, output);
		return mapping.findForward("");
		 
	 }
	 
	/**
	 * ���ѧԺ�༶���÷ֲ�������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward bjbzfcssz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String doType = request.getParameter("doType");
		BjbzfGhxyService service = new BjbzfGhxyService();
		
		PjpyRyjfForm myForm = (PjpyRyjfForm) form;
		BjbzfModel model = new BjbzfModel();
		BeanUtils.copyProperties(model, myForm);
		
		// ��������
		if("save".equalsIgnoreCase(doType)){
			String msg = service.saveCs(model) ? "�����ɹ�" : "����ʧ��";
			request.setAttribute("msg", msg);
			
		}
		
		return mapping.findForward("bjbzfcssz");
	}
}
