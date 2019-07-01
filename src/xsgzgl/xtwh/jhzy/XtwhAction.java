package xsgzgl.xtwh.jhzy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.action.BaseAction;
import xgxt.daoActionLogic.StandardOperation;
/**
 * ��ְҵϵͳά��
 * @author yeyipin
 */
public class XtwhAction extends BaseAction {
	/**
	 * @describe ��ְҵ-����ԱȨ�޷���
	 * @author yeyipin
	 * @throws Exception
	 */
	
	public ActionForward fdyqxfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String writeAble;
		XtwhForm myForm = (XtwhForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				myForm.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}
			// �ж��û���дȨ
			writeAble = Base.getWriteAble(request);
			XtwhService service = new XtwhService();
			HttpSession session = request.getSession();
			//Session�û���
			String userName = session.getAttribute("userName").toString();
			//ѧУ����
			String xxdm = StandardOperation.getXxdm();
			//�û�ID
			String userID = request.getParameter("userName");
			//�Ӻ�׺
			if((userID != null) && !"".equalsIgnoreCase(userID)){
				userID+="_fdy";
			}
			//��������
			String doType = request.getParameter("doType");
			if("save".equalsIgnoreCase(doType)){
				request.setAttribute("message", service.saveFdyssz(request) ? "����ɹ�" : "����ʧ��");
			}else if("qx".equalsIgnoreCase(doType)){
				request.setAttribute("message", service.saveFdyqx(request) ? "����ɹ�" : "����ʧ��");
			}
			// �û��黮��ʱ��Ҫ�õ��û����б��ڴ˳�ʼ��
			String groupList = service.getGroupToSplit();
			//�û��������ݱ���
			if ((groupList == null) || groupList.equalsIgnoreCase("")) {
				session.setAttribute("errNo", "Err0013");
				return mapping.findForward("false");
			}
			String powerList = service.getPowerToSplit(userName);
			// ������и���Ա��ϢLIST
			List<HashMap<String, String>> userList = service.getUserxxList("fdy");
			List<HashMap<String, String>> powerListGroup = service.getPowerList(userID,userName,"fdy");
			request.setAttribute("powerListG", powerListGroup);
			if ((userList == null) || "Error".equalsIgnoreCase(powerList)) {
				myForm.setErrMsg("Err0002");
				return mapping.findForward("false");
			}
			myForm.setGroupList(groupList);
			myForm.setUserPower(powerList);
			request.setAttribute("sUserName", userName);
			request.setAttribute("xxdm", xxdm);
			request.setAttribute("listGroup", userList);
			request.setAttribute("writeAble", writeAble);
			return mapping.findForward("fdyqxfp");

		} catch (Exception e) {
			e.printStackTrace();
			myForm.setErrMsg("���������жϣ������ԣ�");
			return new ActionForward("/errMsg.do", false);
		}
	}
	/**
	 * @describe ������Ȩ�޷���
	 * @author yeyipin
	 * @throws Exception
	 */
	public ActionForward bzrqxfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String writeAble;
		XtwhForm myForm = (XtwhForm) form;
		try {
			int i = Base.chkTimeOut(request.getSession());
			if (i <= 2) {
				myForm.setErrMsg("��½��ʱ�������µ�½��");
				return new ActionForward("/login.jsp", false);
			}
			// �ж��û���дȨ
			writeAble = Base.getWriteAble(request);
			XtwhService service = new XtwhService();
			HttpSession session = request.getSession();
			//Session�û���
			String userName = session.getAttribute("userName").toString();
			//ѧУ����
			String xxdm = StandardOperation.getXxdm();
			//�û�ID
			String userID = request.getParameter("userName");
			//�Ӻ�׺
			if((userID != null) && !"".equalsIgnoreCase(userID)){
				userID+="_bzr";
			}
			//��������
			String doType = request.getParameter("doType");
			if("save".equalsIgnoreCase(doType)){
				request.setAttribute("message", service.saveBzrssz(request) ? "����ɹ�" : "����ʧ��");
			}else if("qx".equalsIgnoreCase(doType)){
				request.setAttribute("message", service.saveBzrqx(request) ? "����ɹ�" : "����ʧ��");
			}
			// �û��黮��ʱ��Ҫ�õ��û����б��ڴ˳�ʼ��
			String groupList = service.getGroupToSplit();
			//�û��������ݱ���
			if ((groupList == null) || groupList.equalsIgnoreCase("")) {
				session.setAttribute("errNo", "Err0013");
				return mapping.findForward("false");
			}
			String powerList = service.getPowerToSplit(userName);
			// ������и���Ա��ϢLIST
			List<HashMap<String, String>> userList = service.getUserxxList("bzr");
			List<HashMap<String, String>> powerListGroup = service.getPowerList(userID,userName,"bzr");
			request.setAttribute("powerListG", powerListGroup);
			if ((userList == null) || "Error".equalsIgnoreCase(powerList)) {
				myForm.setErrMsg("Err0002");
				return mapping.findForward("false");
			}
			myForm.setGroupList(groupList);
			myForm.setUserPower(powerList);
			request.setAttribute("sUserName", userName);
			request.setAttribute("xxdm", xxdm);
			request.setAttribute("listGroup", userList);
			request.setAttribute("writeAble", writeAble);
			return mapping.findForward("bzrqxfp");

		} catch (Exception e) {
			e.printStackTrace();
			myForm.setErrMsg("���������жϣ������ԣ�");
			return new ActionForward("/errMsg.do", false);
		}
	}
	/**
	 * �û���ѯ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward userQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		XtwhService service = new XtwhService();
		request.setAttribute("zList", service.getGroupList());
		String doType=request.getParameter("doType");
		if("fdy".equalsIgnoreCase(doType)){
			return mapping.findForward("fdyQuery");
		}else if("bzr".equalsIgnoreCase(doType)){
			return mapping.findForward("bzrQuery");
		}
		return null;
	}
}
