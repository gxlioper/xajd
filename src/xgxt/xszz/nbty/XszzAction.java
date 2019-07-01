package xgxt.xszz.nbty;

import java.util.HashMap;
import java.util.List;
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
 * Description:������һ����Action
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-6-25
 */
public class XszzAction extends BasicAction{
	/**
	 * ������ѧ�������룬����������һְҵ����ѧԺ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public ActionForward gjzxdksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		
		// ����
		String tableName = "nbty_gjzxdksqb";
		String userName = session.getAttribute("userName").toString();
		String userType = session.getAttribute("userType").toString();
		
		// �Ƿ�������ж����ݣ�������ܱ����룬���ص�ҳ���е����밴ť
		boolean isApply = true;
		
		// ��õ�ǰѧ��
		String xn = Base.currXn;
		
		// ����û�������ѧ����xh��userName,����ѧ��xhΪ��ҳ�����ύ������ѧ�ţ�
		String xh = StringUtils.isEqual(userType, "stu") ? userName : request.getParameter("save_xh");
		String type = request.getParameter("type");

		
		Map<String, String> stuInf = new HashMap<String, String>();
		XszzNbtyService service = new XszzNbtyService();
		
		if(!StringUtils.isNull(xh)){
			stuInf = service.getStuInfo(xh);
			
			// ����ѧ����Ϣ
			stuInf.put("xn", xn);
			service.changeRelationToParents(stuInf);
		}
		
		//�жϸ�ѧ���Ƿ��������
		if(service.isAuditing(xh, xn)){
			isApply = false;
			request.setAttribute("message", "���ڱ���ˣ��������룡");
		}
		
		if("add".equalsIgnoreCase(type) && isApply){
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
		request.setAttribute("rs", stuInf);
		request.setAttribute("userType", userType);
		return mapping.findForward("gjzxdksq");
	}
	
	/**
	 * ������ѧ������������ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	
	public ActionForward gjzxdksqjg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		String userDep = session.getAttribute("userDep").toString();
		// ����
		String tableName = "nbty_gjzxdksqb";
		// ��ͼ��
		String viewName = "view_nbty_gjzxdksq";
		String go = request.getParameter("go");
		String xh = session.getAttribute("userName").toString();
		GjzxdkForm gForm = (GjzxdkForm)form;
		XszzNbtyService service = new XszzNbtyService();
		String userType = service.getUserType(session);
		
		request.setAttribute("tableName", viewName);
		request.setAttribute("realTable", tableName);
		
		if("stu".equalsIgnoreCase(userType)){
			List<String[]> list = service.getXsgjzxdkxx(xh);
			request.setAttribute("rs", list);
			return mapping.findForward("gjzxdkforstu");
		}
		// ����ҳ������
		if("fdy".equalsIgnoreCase(userType)){
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		}else{
			FormModleCommon.setNjXyZyBjList(request);
		}
		
		if("xy".equalsIgnoreCase(userType)){
			gForm.setQueryequals_xydm(userDep);
		}

		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdksqjg");
		request.setAttribute("writeAble", FormModleCommon.getWriteAbleAndTitle(request)[0]);

		if("go".equalsIgnoreCase(go)){
			String[] outputColumn = {"pkValue","disabled","xh","nj","xn","xm","bjmc","fdysh","xysh","xxsh"};
			// ��ȡ�ϼ��Ƿ����ͨ����Ϣ�����ظ�ҳ��
			if("fdy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xysh when 'ͨ��' then 'disabled' else '' end)disabled,");
			}else if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
			}else{
				request.setAttribute("clientColumns", "'' disabled,");
			}
			selectPageDataByPagination(request, gForm, tableName, viewName, outputColumn);
		}
		

		request.setAttribute("userType", userType);
		request.setAttribute("userDep", userDep);
		return mapping.findForward("gjzxdksqjg");
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
	public ActionForward gjzxdkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ����Ϊѧ�ţ�ѧ��
		String pkValue = request.getParameter("pkValue");
		String tableName = "nbty_gjzxdksqb";
		XszzNbtyService service = new XszzNbtyService();
		String userType = service.getUserType(request.getSession()); 
		String doType = request.getParameter("doType");
		
		// �������
		if("save".equalsIgnoreCase(doType)){
			updateOperation(request, tableName);
		}
		
		// ����������ȡ������ѧ������������Ϣ
		Map<String, String> map = service.getGjzxdkxx(pkValue);
		service.changeRelationToParents(map);
		request.setAttribute("rs", map);
		request.setAttribute("pkValue", pkValue);
		request.setAttribute("userType", userType);
		
		if("view".equalsIgnoreCase(doType)){
			// �����޸ĵ�ҳ��
			return mapping.findForward("gjzxdkshview");
		}

		// ��ӡ��Ϣ
		if("print".equalsIgnoreCase(doType)){
			// ���ÿѧ�����Ϣ
			Map<String,Map<String,String>> mxndkxx = service.getMxndkxx(map.get("xh"));
			request.setAttribute("mxndkxx", mxndkxx);
			return mapping.findForward("gjzxdkprint");
		}
		
		// �޸�
		if("modi".equalsIgnoreCase(doType) || "shone".equalsIgnoreCase(doType)){
			String xysh = request.getParameter("xysh");
			String xxsh = request.getParameter("xxsh");
		
			if("fdy".equalsIgnoreCase(userType) && "ͨ��".equalsIgnoreCase(xysh) || 
				"xy".equalsIgnoreCase(userType) && "ͨ��".equalsIgnoreCase(xxsh)){
				return mapping.findForward("gjzxdkshview");
			}
			
			if("modi".equalsIgnoreCase(doType)){
				return mapping.findForward("gjzxdkmodi");
			}
			
			request.setAttribute("nowtime", GetTime.getNowTime());
		}
		
		return mapping.findForward("gjzxdkshone");
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
	public ActionForward gjzxdksh(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		// ����
		String tableName = "nbty_gjzxdksqb";
		// ��ͼ��
		String viewName = "view_nbty_gjzxdksq";
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
		
		XszzNbtyService service = new XszzNbtyService();
		// ��ȡuserType
		String userType = service.getUserType(session);

		GjzxdkForm gForm = (GjzxdkForm)form; 
		
		// �����ѧԺ��ѧԺ���ž�ȷ��
		if("xy".equalsIgnoreCase(userType)){
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
			String[] outputColumn = {"pkValue","disabled","xh","nj","xn","xm","bjmc","fdysh","xysh","xxsh"};
			// ��ȡ�ϼ��Ƿ����ͨ����Ϣ�����ظ�ҳ��
			if("fdy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xysh when 'ͨ��' then 'disabled' else '' end)disabled,");
			}else if("xy".equalsIgnoreCase(userType)){
				request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
			}else{
				request.setAttribute("clientColumns", "'' disabled,");
			}
			selectPageDataByPagination(request, gForm, tableName, viewName, outputColumn);
		}
		
		loadInfo(request, userType);
		request.setAttribute("userType", userType);
		return mapping.findForward("gjzxdksh");
	}
	
	
	/**
	 * ����ҳ���еĲ�ѯ��Ϣ
	 * @param request
	 * @param userType
	 */
	private void loadInfo(HttpServletRequest request, String userType){
		// �������ͼ����꼶��ѧԺ��רҵ���༶��Ϣ
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		// ������ȣ�ѧ�꣬ѧ����Ϣ
		FormModleCommon.setNdXnXqList(request);
		request.setAttribute("path", "zgdzdx_xszz.do?method=gjzxdksh");
		request.setAttribute("writeAble", FormModleCommon.getWriteAbleAndTitle(request)[0]);
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
	public ActionForward gjzxdkExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String tableName = "nbty_gjzxdksqb";
		String viewName = "view_nbty_gjzxdksq";
		
		// ��Ҫ�������ֶ�
		String[] output = {"xh","xm", "xb", "xn", "bjmc", "xxqkztpj",
				"bjgbxkms", "sxnpddd", "ywblxyjl", "dkje","fdysh", "xysh", "xxsh", "hkfs"};
		expPageData(request, response, tableName, viewName, output);
		return mapping.findForward("");
	}
}
