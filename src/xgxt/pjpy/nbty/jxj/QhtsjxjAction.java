package xgxt.pjpy.nbty.jxj;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.zfsoft.basic.BasicAction;
import xgxt.action.Base;
import xgxt.action.base.BaseDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

/**
 * Title: ѧ����������ϵͳ
 * Description:������һ�庮��ʹ��ѧ��Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-7-14
 */
public class QhtsjxjAction extends BasicAction{
	/**
	 * ������ѧ�������룬����������һְҵ����ѧԺ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward qhtsjxjsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		String tableName = "nbty_qhtssqb";
		
		// �Ƿ�������ж����ݣ�������ܱ����룬���ص�ҳ���е����밴ť
		boolean isApply = true;
		
		// ����û�������ѧ����xh��userName,����ѧ��xhΪ��ҳ�����ύ������ѧ�ţ�
		String xh = StringUtils.isEqual(userType, "stu") ? userName : request.getParameter("save_xh");
		String doType = request.getParameter("doType");
		
		// ��ǰѧ��
		String xn = Base.currXn;
		
		Map<String, String> stuInf = new HashMap<String, String>();		
		QhtsNbtyService service = new QhtsNbtyService();
		Map<String, String> jxjxx = service.getJxjxx("�庮��ʹ��ѧ��");
		
		if(!StringUtils.isNull(xh)){
			stuInf = service.getStuInfo(xh);
		}
		
		//�жϸ�ѧ���Ƿ��������
		if(service.isAuditing(xh, xn)){
			isApply = false;
			request.setAttribute("message", "���ڱ���ˣ��������룡");
		}
		
		if("add".equalsIgnoreCase(doType) && isApply){
			// �ж�Ҫ���ӵ������Ƿ����
			BaseDAO baseDao = new BaseDAO();
			String pkVal = xh+xn;
			boolean isExists = baseDao.checkExists(tableName, "xh||xn", pkVal);
			
			// �������ִ��update������������ִ�в������
			if(isExists){
				updateOperation(request, tableName);
			}else{
				insertOperation(request, tableName);
			}
		}
		// ����ʱ��
		request.setAttribute("sqsj", GetTime.getNowTime());
		// �жϱ�־���Ƿ��������
		request.setAttribute("isApply", isApply);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("jxjxx", jxjxx);
		request.setAttribute("rs", stuInf);
		request.setAttribute("userType", userType);
		return mapping.findForward("qhtsjxjsq");
	}
	
	/**
	 * �庮��ʹ��������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward qhtsjxjcx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		String go = request.getParameter("go");
		QhtsjxjForm qForm = (QhtsjxjForm)form;
		
		// ���ָ���Ա��ѧԺ�û�
		String user = getUserType(session);
		String tableName = "nbty_qhtssqb";
		String viewName = "view_nbty_qhtsjxj";
		QhtsNbtyService service = new QhtsNbtyService();
		
		
		if("xy".equalsIgnoreCase(user)){
			qForm.setQueryequals_xydm(userDep);
		}
		
		if("stu".equalsIgnoreCase(user)){
			String xh = session.getAttribute("userName").toString();
			Map<String,String> stuInfo = service.getStuInfo(xh);
			String equals_xh = stuInfo.get("xh");
			String like_xm = stuInfo.get("xm");
			String equals_nj = stuInfo.get("nj");

			qForm.setQuerylike_xh(equals_xh);
			qForm.setQuerylike_xm(like_xm);
			qForm.setQueryequals_nj(equals_nj);
			
			request.setAttribute("queryInfo", stuInfo);
		}

		
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = getOutputList(request, user);		
			selectPageDataByPagination(request, qForm, tableName, 
					viewName, outputColumn);
		}
		
		// ������Ϣ
		FormModleCommon.setNdXnXqList(request);
		loadInfo(request, "nbty_qhtsjxj.do?method=qhtsjxjcx");
		
		// ���뵼�뵼���ı����ͼ
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		request.setAttribute("userType", user);
		request.setAttribute("userDep", userDep);
		return mapping.findForward("qhtsjxjcx");
	}
	
	/**
	 * ������Ϣ�Ĳ鿴�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjone(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����Ϊѧ�ţ�ѧ��
		String pkValue = request.getParameter("pkValue");
		String user = getUserType(request.getSession());
		String doType = request.getParameter("doType");
		
		QhtsNbtyService service = new QhtsNbtyService();

		Map<String, String> map = service.getQhtsxx(pkValue);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", user);
		
		if("view".equalsIgnoreCase(doType)){
			// �����޸ĵ�ҳ��
			request.setAttribute("view","true");
		}
		
		// �Ƿ�����޸Ļ򵥸���˵�Ȩ��
		boolean isModi = isModi(request, user);
		// �޸�
		if("modi".equalsIgnoreCase(doType) && !isModi){
			request.setAttribute("view", "true");
		}
		
		if("shone".equalsIgnoreCase(doType)){
			if(!isModi){
				request.setAttribute("view", "true");
			}else{
				request.setAttribute("nowtime", GetTime.getNowTime());
				return mapping.findForward("qhtsjxjshone");
			}
		}
		
		return mapping.findForward("qhtsjxjmodi");
	}
	
	/**
	 * �����庮��ʹ��ѧ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjupdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����ҳ��
		String destination = request.getParameter("destination");
		this.updateOperation(request, "nbty_qhtssqb");
		return mapping.findForward(destination);
	}
	
	/**
	 * �庮��ʹ��ӡ����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjprint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pkValue = request.getParameter("pkValue");
		
		QhtsNbtyService service = new QhtsNbtyService();
		Map<String, String> map = service.getQhtsxx(pkValue);
		
		request.setAttribute("rs", map);
		return mapping.findForward("qhtsjxjprint");
	}
	
	/**
	 * ������ѧ������ˣ�����������һְҵ����ѧԺ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjsh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		String tableName = "nbty_qhtssqb";
		String viewName = "view_nbty_qhtsjxj";
		// ��Ҫ���еĲ���
		String doType = request.getParameter("doType");
		// ��˽��
		String shjg = request.getParameter("shjg");
		// ����ֶ�
		String shzd = request.getParameter("shzd");
		// �û�����
		String userDep = session.getAttribute("userDep").toString();
		// ���ʱ��
		String shsj = request.getParameter("shsj");
		
		String go = request.getParameter("go");
		
		// ��ȡuserType
		String user = getUserType(session);

		QhtsjxjForm gForm = (QhtsjxjForm)form; 
		
		// �����ѧԺ��ѧԺ���ž�ȷ��
		if("xy".equalsIgnoreCase(user)){
			gForm.setQueryequals_xydm(userDep);
		}
		
		if(!StringUtils.isNull(shjg)){
			shjg = "tg".equalsIgnoreCase(shjg) ? "ͨ��" : "��ͨ��";
		}
		
		// ɾ������
		if("del".equalsIgnoreCase(doType)){
			deleteOperation(request, tableName);
		}
		
		// ��˲���
		if("sh".equalsIgnoreCase(doType)){
			
			// ��ȡҳ������primarykey_Ϊ��ʼ������
			HashMap<String, String[]> primaryMap = getValueArrayMap(request, PRIFIX_PRIMARY_KEY);
			HashMap<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(shzd, shjg);
			valueMap.put(shsj, GetTime.getNowTime());
			
			// ͨ����˷���
			auditingBatchOperation(request, primaryMap, valueMap, tableName);
		}
		//		����ҳ���е�������ȡ����
		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = getOutputList(request, user);
			selectPageDataByPagination(request, gForm, tableName,
					viewName, outputColumn);
		}
		
		loadInfo(request, "nbty_qhtsjxj.do?method=qhtsjxjsh");
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("nd", Base.currNd);
		request.setAttribute("userType", user);
		return mapping.findForward("qhtsjxjsh");
	}
	
	
	/**
	 * ����ҳ���еĲ�ѯ��Ϣ
	 * @param request
	 * @param userType
	 */
	private void loadInfo(HttpServletRequest request, String path){
		// �������ͼ����꼶��ѧԺ��רҵ���༶��Ϣ
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		request.setAttribute("path", path);
		request.setAttribute("writeAble", FormModleCommon.getWriteAbleAndTitle(request)[0]);
	}
	
	/**
	 * ��userType �ֳ����࣬����������Ա��ѧԺ��ѧУ
	 * @param session
	 * @return
	 */
	private String getUserType(HttpSession session){
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		String userType = (String)session.getAttribute("userType");
		
		if(isFdy){
			userType = "fdy";
		}else if("xy".equalsIgnoreCase(userType)){
			userType = "xy";
		}else if("admin".equalsIgnoreCase(userType) || ("xx".equalsIgnoreCase(userType))){
			userType = "xx";
		}
		return userType;
	}
	
	/**
	 * ��ʾ��Ҫ��ȡ���ݿ���ֶΰ��������ֶ�
	 * @param request
	 * @param user
	 * @return
	 */
	private String[] getOutputList(HttpServletRequest request, String user){
		String[] outputColumn = {"pkValue","disabled","xh","nj","xn","xm","bjmc","bjsh","xysh","xxsh"};
		// ��ȡ�ϼ��Ƿ����ͨ����Ϣ�����ظ�ҳ��
		if("fdy".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xysh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else if("xy".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else{
			request.setAttribute("clientColumns", "'' disabled,");
		}
		
		return outputColumn;
	}
	
	
	private boolean isModi(HttpServletRequest request, String user){
		String xysh = request.getParameter("xysh");
		String xxsh = request.getParameter("xxsh");
		
		boolean flag = true;
		
		if("fdy".equalsIgnoreCase(user) && "ͨ��".equalsIgnoreCase(xysh) || 
				"xy".equalsIgnoreCase(user) && "ͨ��".equalsIgnoreCase(xxsh)){
			flag = false;
		}
		
		return flag;
	}
	/**
	 * ��������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qhtsjxjExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tableName = "nbty_qhtssqb";
		String viewName = "view_nbty_qhtsjxj";
		// ��Ҫ�������ֶ�
			String[] output = { "xh", "xn", "nj", "xm", "xb", "csrq", "mzmc","sfzh","rxrq","xymc",
				"zymc", "bjmc", "pddd", "sfcjqgzx", "sfsqzxdk","jxjmc","jlje","bjsh", "xysh",
				"xxsh" };
		
		expPageData(request, response, tableName,viewName, output);
		return mapping.findForward("");
	}
	
	
}

