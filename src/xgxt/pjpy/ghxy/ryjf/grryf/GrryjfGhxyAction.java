package xgxt.pjpy.ghxy.ryjf.grryf;

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
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ
 * Description:���ѧԺ���������ӷ�Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-8-05
 */
public class GrryjfGhxyAction extends BasicExtendAction{
	/**
	 * ���ѧԺ���˼ӷ�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userType = session.getAttribute("userType").toString();
		String userName = session.getAttribute("userName").toString();
		String doType = request.getParameter("doType");
		
		PjpyRyjfForm pForm = (PjpyRyjfForm)form;
		RyjfModel model = new RyjfModel();
		BeanUtils.copyProperties(model, pForm);
		
		Map<String, String> stuInfo = null;
		GrryjfGhxyService service = new GrryjfGhxyService();
		
		//���ѧ�꣬ѧ�ţ�ѧ��
		String xh = "stu".equalsIgnoreCase(userType) ? userName : request.getParameter("xh");
		String xn = Base.currXn;
		String xq = Base.currXq;
		
		boolean isApply = true;
		
		if(StringUtils.isNotNull(xh)){
			stuInfo = service.getStuInfo(xh);
			
			// ����Ѿ�����ˣ������޸�
			if(service.isAuditing(xh, xn, xq)){
				isApply = false;
				request.setAttribute("msg", "���������Ѿ����ϼ���ˣ���������");
			}
		}
		
		if("add".equalsIgnoreCase(doType)){
			// ���������ݲ������
			String msg = service.insertRecord(model) ? "����ɹ�" : "����ʧ��"; 
			request.setAttribute("msg", msg);
		}
		
		// ��ȡwriteAble��title
		setWriteAbleAndTitle(request, "pjpyryjfsq.do");
		request.setAttribute("xn", xn);
		request.setAttribute("xqdm", xq);
		request.setAttribute("xq", BasicExtendService.xqMap.get(xq));
		request.setAttribute("isApply", isApply);
		request.setAttribute("rs", stuInfo);
		return mapping.findForward("grjfsq");
	}
	
	/**
	 * ���ѧԺ���˼ӷ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String tableName = "ghxy_grjfb";
		String viewName = "view_ghxy_grjf";
		// ��Ҫ���еĲ���
		String doType = request.getParameter("doType");

		String go = request.getParameter("go");

		// ��ȡuserType
		String user = getUserType(request);

		if(!("fdy".equalsIgnoreCase(user) || "njbzr".equalsIgnoreCase(user)||"xx".equalsIgnoreCase(user))){
			request.setAttribute("errMsg", "�Բ���,��û�и�ģ��Ȩ�ޣ�");
			return mapping.findForward("error");
		}
		
		PjpyRyjfForm gForm = (PjpyRyjfForm) form;

		// ��˲���
		if ("sh".equalsIgnoreCase(doType)) {
			// ����ֶ�
			String shzd = request.getParameter("shzd");
			
			// ��˽��
			String shjg = request.getParameter("shjg");
			
			// ���ʱ��
			String shsj = request.getParameter("shsj");
			
			if (!StringUtils.isNull(shjg)) {
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
		// ����ҳ���е�������ȡ����
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"pkValue","disabled","xh","xm","nj","xymc","zymc","bjmc","fdysh","njbzrsh","xxsh"};
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}

		setWriteAbleAndTitle(request, "pjpyryjfsh.do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("njList", Base.getNjList());
		request.setAttribute("xq", BasicExtendService.xqMap.get(Base.currXq));
		request.setAttribute("userType", user);
		
		return mapping.findForward("grjfsh");
	}
	
	/**
	 * ���ѧԺ���˼ӷֵ������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfshone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String user = getUserType(request);
		String doType = request.getParameter("doType");
		// ��ghxy_grjfb��������xh||xn||xq
		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh");
		String xn = Base.currXn;
		String xq = Base.currXq;
		
		GrryjfGhxyService service = new GrryjfGhxyService();
		Map<String, String> stuInfo = service.getStuInfo(xh); 
		Map<String, String> grjfInfo = service.getGrjfInfo(pkValue);
		
		if("save".equalsIgnoreCase(doType)){
			PjpyRyjfForm pForm = (PjpyRyjfForm)form;
			RyjfModel model = new RyjfModel();
			BeanUtils.copyProperties(model, pForm);
			String msg = service.updateRecord(model) ? "��˳ɹ�" : "���ʧ��"; 
			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("rs", stuInfo);
		request.setAttribute("grjfInfo", grjfInfo);
		request.setAttribute("xn", xn);
		request.setAttribute("xqdm", xq);
		request.setAttribute("xq", BasicExtendService.xqMap.get(xq));
		request.setAttribute("nowtime", GetTime.getNowTime());
		request.setAttribute("userType", user);
		return mapping.findForward("grjfshone");
	}
	
	/**
	 * ���ѧԺ���˼ӷֵ����鿴���޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfViewAndModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String user = getUserType(request);
		String doType = request.getParameter("doType");
		// ��ghxy_grjfb��������xh||xn||xq
		String pkValue = request.getParameter("pkValue");
		String xh = request.getParameter("xh");

		
		GrryjfGhxyService service = new GrryjfGhxyService();
		Map<String, String> stuInfo = service.getStuInfo(xh); 
		Map<String, String> grjfInfo = service.getGrjfInfo(pkValue);
		
		String operation = "modi".equalsIgnoreCase(doType) ? "modi" : "view";
		
		if("save".equalsIgnoreCase(doType)){
			PjpyRyjfForm pForm = (PjpyRyjfForm)form;
			RyjfModel model = new RyjfModel();
			BeanUtils.copyProperties(model, pForm);
			
			String msg = service.insertRecord(model) ? "�޸ĳɹ�" : "�޸�ʧ��"; 
			request.setAttribute("msg", msg);
		}
		
		request.setAttribute("rs", stuInfo);
		request.setAttribute("grjfInfo", grjfInfo);
		request.setAttribute("operation", operation);
		request.setAttribute("userType", user);
		return mapping.findForward("grjfview");
	}
	
	/**
	 * ���ѧԺ���˼ӷֲ�ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String tableName = "ghxy_grjfb";
		String viewName = "view_ghxy_grjf";
		
		// ��Ҫ���еĲ���
		String doType = request.getParameter("doType");

		String go = request.getParameter("go");

		// ��ȡuserType
		String user = getUserType(request);

		PjpyRyjfForm gForm = (PjpyRyjfForm) form;
		
		GrryjfGhxyService service = new GrryjfGhxyService();
		
		setWriteAbleAndTitle(request, "pjpyryjfcx.do");

		if ("stu".equalsIgnoreCase(user)) {
			String[] output = new String[]{"xh","xm","xn","nj","xq","xymc","zymc","bjmc","ryjf","fdysh","njbzrsh","xxsh"};
			
			// ��������ֶ���
			request.setAttribute("topTr", DAO.getInstance().getColumnNameCN(output, viewName));
			// ��¼
			request.setAttribute("rs", service.getGrjfInfoForStu(userName, output));
			
			return mapping.findForward("grjfcxforstu");
		}
		
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			PjpyRyjfForm pForm = (PjpyRyjfForm)form;
			String msg = service.delRecord(pForm.getPkValues()) ? "ɾ���ɹ�" : "ɾ��ʧ��"; 
			request.setAttribute("msg", msg);
		}

		// ����ҳ���е�������ȡ����
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"pkValue","disabled","xh","xm","xn","nj","xq","xymc","zymc","bjmc","ryjf","fdysh","njbzrsh","xxsh"};
			setDisabledField(request, user);
			selectPageDataByPagination(request, gForm, tableName, viewName,
					outputColumn);
		}
		
		if("xy".equalsIgnoreCase(user)){
			gForm.setQueryequals_xydm(userDep);
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		return mapping.findForward("grjfcx");
	}
	
	/**
	 * ���ѧԺ�����ӷֻ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfhz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String userName = session.getAttribute("userName").toString();
		String viewName = "view_ghxy_xxjfhz";
		
		String go = request.getParameter("go");

		// ��ȡuserType
		String user = getUserType(request);

		PjpyRyjfForm gForm = (PjpyRyjfForm) form;

		
		setWriteAbleAndTitle(request, "pjpyjfhz.do");

		if ("stu".equalsIgnoreCase(user)) {
			gForm.setQuerylike_xh(userName);
		}else if("xy".equalsIgnoreCase(user)){
			gForm.setQueryequals_xydm(userDep);
		}


		// ����ҳ���е�������ȡ����
		if ("go".equalsIgnoreCase(go)) {
			String[] outputColumn = new String[]{"xh","xm","xn","xqmc","xymc","zymc","bjmc","grhjf",
					"bjbzf","qsryf","bjryf","ryjf","ryszf","zf"};
			selectPageDataByPagination(request, gForm, "", viewName,
					outputColumn);
		}
		
		setWriteAbleAndTitle(request, "pjpyhzcx." +
				"do");
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("userType", user);
		request.setAttribute("tableName", viewName);
		return mapping.findForward("ryjfhz");
	}
	
	/**
	 * �������ֻ��ܵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward ryjfhzExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String viewName = "view_ghxy_xxjfhz";
		// ��Ҫ�������ֶ�
			String[] output = {"xh","xm","xn","xqmc","xymc","zymc","bjmc","grhjf",
					"bjbzf","qsryf","bjryf","ryjf","ryszf","zf"};
		
		expPageData(request, response, "",viewName, output);
		return mapping.findForward("");
	}
	
	 /** ���ѧԺ���˼ӷֵ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward grjfExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "ghxy_grjfb";
		String viewName = "view_ghxy_grjf";
		// ��Ҫ�������ֶ�
			String[] output = { "xh","xm","xn","nj","xq","xymc","zymc","bjmc","fdysh","njbzrsh","xxsh",
					"fdyshsj","njbzrshsj","xxshsj","ryjf"};
		
		expPageData(request, response, tableName,viewName, output);
		return mapping.findForward("");
		 
	 }
	 
	/**
	 * ��ȡ�û�����
	 * @param request
	 * @return
	 */
	private String getUserType(HttpServletRequest request){
		HttpSession session = request.getSession();
		GhxyNjzrwhService service = new GhxyNjzrwhService();
		
		List<HashMap<String, String>> list = service.getFdyNj(session.getAttribute("userName").toString());
		String userType = session.getAttribute("userType").toString();
		
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		if(isFdy){
			userType = "fdy";
		}else if(list != null && list.size()>0){
			userType = "njbzr";
			// ������꼶�����οɲ�ѯ���꼶
			request.setAttribute("njListForBzr", list);
		}else if("xy".equalsIgnoreCase(userType)){
			userType = "xy";
		}else if("admin".equalsIgnoreCase(userType) || ("xx".equalsIgnoreCase(userType))){
			userType = "xx";
		}
		return userType;
	}
	
	/**
	 * ��ʾ��Ҫ��ȡ���ݿ���ֶΰ��������ֶ�,�����������
	 * @param request
	 * @param user
	 * @return
	 */
	protected void setDisabledField(HttpServletRequest request, String user){
		// ��ȡ�ϼ��Ƿ����ͨ����Ϣ�����ظ�ҳ��
		if("fdy".equalsIgnoreCase(user) || "xy".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case njbzrsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else if("njbzr".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else{
			request.setAttribute("clientColumns", "'' disabled,");
		}
	}
}
