package xgxt.rcgl.zjjj.qjgl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.dtjs.gdby.tygl.BasicExtendAction;
import xgxt.utils.FormModleCommon;
import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

public class ZjjjQjglAction extends BasicExtendAction{
	
	/**
	 * ѧ���������
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xsqjUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
		// �û���
		String userName = (String)session.getAttribute("userName");
		String userStatus = getUserStatus(request);

		String doType = request.getParameter("doType");
		String xh = "stu".equalsIgnoreCase(userStatus) ? userName : request.getParameter("xh");
		
		ZjjjQjglService service = new ZjjjQjglService();
		
		if("save".equalsIgnoreCase(doType)){
			ZjjjQjglForm myForm = (ZjjjQjglForm)form;
			
			String message = service.saveXsqj(myForm) ? "����ɹ���" : "����ʧ�ܣ�";
			request.setAttribute("message", message);
		}
		
		Map<String, String> stuInfo = new HashMap<String, String>();
		// ѧ�Ų�Ϊ�����ȡѧ����Ϣ
		if(StringUtils.isNotNull(xh)){
			stuInfo = service.getStuInfo(xh);
		}
		
		setWriteAbleAndTitle(request, "rcsw_qjsq.do");
		request.setAttribute("rs", stuInfo);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xq", Base.currXq);
		request.setAttribute("userStatus", userStatus);
		request.setAttribute("xqmc", ZjjjQjglService.xqdzMap.get(Base.currXq));
		return mapping.findForward("xsqjUpdate");
	}
	
	/**
	 * �����˹���ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		// �û���
		String userName = (String)session.getAttribute("userName");
		String realName = (String)session.getAttribute("userNameReal");
		String userDep = (String)session.getAttribute("userDep");
		String userType = (String)session.getAttribute("userType");
		
		String userStatus = getUserStatus(request);
		String doType = request.getParameter("doType");
		
		if("stu".equalsIgnoreCase(userStatus)){
			request.setAttribute("yhInfo", "ѧ���û����ܲ�����ģ�飡");
			return new ActionForward("/yhInfo.do");
		}
		
		ZjjjQjglService service = new ZjjjQjglService();
		ZjjjQjglForm model = (ZjjjQjglForm)form;
		
		String go = request.getParameter("go");
		
		// ������˲���
		if("sh".equalsIgnoreCase(doType)){
			String shjg = "tg".equalsIgnoreCase(request.getParameter("shjg")) ? "ͨ��" : "��ͨ��";
			String[] pkValues = request.getParameterValues("pkValues");
			
			String message = service.batchSh(pkValues, userStatus, realName, GetTime.getNowTime(), shjg) ? "�����ɹ�" : "����ʧ��";
			
			request.setAttribute("message", message);
			
			go="go";
		}
		
		// �����ѯ��ť
		if("go".equalsIgnoreCase(go)){
			String[] outPutList = new String[]{"pkValue","xh", "xn", "xqmc", "xm", "bjmc", "qjkssj", "qjjssj", "qjts","sfzx","sh1", "sh2", "sh3", "sh4"};
			
			request.setAttribute("topTr", service.getTitle("xg_view_rcsw_xssq", outPutList));
			request.setAttribute("rs", service.getShList(model, userStatus, userName, outPutList));
		}
		
		// �Ƿ�̶�ѧԺ
		boolean disabledXy = false;
		
		//Ϊ�˽��һ�˶������
		if("xy".equalsIgnoreCase(userStatus)){// ѧԺ��½
			if("xy".equalsIgnoreCase(userType)){
				model.setXydm(userDep);
				disabledXy = true;
			}
		}else if("zr".equalsIgnoreCase(userStatus)){
			if("xy".equalsIgnoreCase(userType)){
				model.setXydm(userDep);
				disabledXy = true;
			}
		}
		
		if("xy".equalsIgnoreCase(userStatus)){
			FormModleCommon.setNjXyZyBjList(request);
		}else {
			FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		}
		
		String path = "";
		
		if("fdy".equalsIgnoreCase(userStatus)){
			path = "rcsw_xsqjfdysh.do";
		}else if("zr".equalsIgnoreCase(userStatus)){
			path = "rcsw_xsqjdsjsh.do";
		}else {
			path = "rcsw_xsqjsh.do";
		}

		setWriteAbleAndTitle(request, path);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("ldList", service.getLdList());
		request.setAttribute("userStatus", userStatus);
		request.setAttribute("disabledXy", disabledXy);
		
		return mapping.findForward("qjshManage");
	}
	
	/**
	 * �������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjshUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		
		ZjjjQjglService service = new ZjjjQjglService();
		Map<String, String> map = service.getQjInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("xqmc", ZjjjQjglService.xqdzMap.get((map.get("xq"))));
		request.setAttribute("doType", doType);
		return mapping.findForward("qjshUpdate");
	}
	
	/**
	 * ��ٲ�ѯ����ҳ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjcxManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		HttpSession session = request.getSession();
		// �û���
		String userName = (String)session.getAttribute("userName");
		String userDep = (String)session.getAttribute("userDep");
		
		String userStatus = getUserStatus(request);
		String doType = request.getParameter("doType");
		
		ZjjjQjglService service = new ZjjjQjglService();
		ZjjjQjglForm model = (ZjjjQjglForm)form;
		
		String go = request.getParameter("go");
		
		if("del".equalsIgnoreCase(doType)){
			String[] pkValues = request.getParameterValues("pkValues");
			String message = service.batchDel(pkValues) ? "�����ɹ�" : "����ʧ��";
			
			request.setAttribute("message", message);
			go = "go";
		}
	
		// �����ѯ��ť
		if("go".equalsIgnoreCase(go)){
			String[] outPutList = new String[]{"disabled","pkValue","xh", "xn", "xqmc", "xm", "bjmc","qsh", "qjkssj", "qjjssj", "qjts","sfzx","sh1", "sh2", "sh3", "sh4"};
			
			request.setAttribute("topTr", service.getTitle("xg_view_rcsw_xssq", outPutList));
			request.setAttribute("rs", service.getCxList(model, userStatus, userName, outPutList));
		}
		
		if("stu".equalsIgnoreCase(userStatus)){
			model.setXh(userName);
		}else if("xy".equalsIgnoreCase(userStatus)){// ѧԺ��½
			model.setXydm(userDep);	
		}
		
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		
 		setWriteAbleAndTitle(request, "rcsw_xsqjcx.do");
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("xqList", Base.getXqList());
		request.setAttribute("ldList", service.getLdList());
		request.setAttribute("userStatus", userStatus);
		request.setAttribute("realTable", "xg_rcsw_xsqjb");
		request.setAttribute("tableName", "xg_view_rcsw_xssq");
		
		return mapping.findForward("qjcxManage");
	}
	
	/**
	 * �������޸�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward qjxgUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		String pkValue = request.getParameter("pkValue");
		String doType = request.getParameter("doType");
		String opera = request.getParameter("opera");
		
		String forward = "print".equalsIgnoreCase(doType) ? "qjglPrint" : "qjxgUpdate";
		
		ZjjjQjglService service = new ZjjjQjglService();
		
		// �޸Ĳ���
		if("update".equalsIgnoreCase(opera)){
			ZjjjQjglForm model = (ZjjjQjglForm) form;
			String message = service.updateQjgl(model, pkValue) ? "�޸ĳɹ���" : "�޸�ʧ�ܣ�";
			
			request.setAttribute("message", message);
		}
		
		Map<String, String> map = service.getQjInfo(pkValue);
		
		request.setAttribute("rs", map);
		
		request.setAttribute("xqmc", ZjjjQjglService.xqdzMap.get((map.get("xq"))));
		request.setAttribute("doType", doType);
		return mapping.findForward(forward);
	}
	
	/**
	 * ����û�״̬
	 * @param request
	 * @return
	 */
	private String getUserStatus(HttpServletRequest request){
		String userStatus = request.getParameter("userStatus");
		
		HttpSession session = request.getSession();
		
		if(StringUtils.isNull(userStatus)){
			String userType = (String)session.getAttribute("userType");
			
			if("xy".equalsIgnoreCase(userType)){
				userStatus = "xy";
			}else if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){
				userStatus = "xx";
			}else {
				userStatus = userType;
			}
		}
		
		return userStatus;
	}
	
}
