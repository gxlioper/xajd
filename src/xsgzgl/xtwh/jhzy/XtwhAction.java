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
 * 金华职业系统维护
 * @author yeyipin
 */
public class XtwhAction extends BaseAction {
	/**
	 * @describe 金华职业-辅导员权限分配
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
				myForm.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			// 判断用户读写权
			writeAble = Base.getWriteAble(request);
			XtwhService service = new XtwhService();
			HttpSession session = request.getSession();
			//Session用户名
			String userName = session.getAttribute("userName").toString();
			//学校代码
			String xxdm = StandardOperation.getXxdm();
			//用户ID
			String userID = request.getParameter("userName");
			//加后缀
			if((userID != null) && !"".equalsIgnoreCase(userID)){
				userID+="_fdy";
			}
			//操作类型
			String doType = request.getParameter("doType");
			if("save".equalsIgnoreCase(doType)){
				request.setAttribute("message", service.saveFdyssz(request) ? "保存成功" : "保存失败");
			}else if("qx".equalsIgnoreCase(doType)){
				request.setAttribute("message", service.saveFdyqx(request) ? "保存成功" : "保存失败");
			}
			// 用户组划分时需要用到用户组列表，在此初始化
			String groupList = service.getGroupToSplit();
			//用户组无数据报错
			if ((groupList == null) || groupList.equalsIgnoreCase("")) {
				session.setAttribute("errNo", "Err0013");
				return mapping.findForward("false");
			}
			String powerList = service.getPowerToSplit(userName);
			// 获得所有辅导员信息LIST
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
			myForm.setErrMsg("数据连接中断，请重试！");
			return new ActionForward("/errMsg.do", false);
		}
	}
	/**
	 * @describe 班主任权限分配
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
				myForm.setErrMsg("登陆超时，请重新登陆！");
				return new ActionForward("/login.jsp", false);
			}
			// 判断用户读写权
			writeAble = Base.getWriteAble(request);
			XtwhService service = new XtwhService();
			HttpSession session = request.getSession();
			//Session用户名
			String userName = session.getAttribute("userName").toString();
			//学校代码
			String xxdm = StandardOperation.getXxdm();
			//用户ID
			String userID = request.getParameter("userName");
			//加后缀
			if((userID != null) && !"".equalsIgnoreCase(userID)){
				userID+="_bzr";
			}
			//操作类型
			String doType = request.getParameter("doType");
			if("save".equalsIgnoreCase(doType)){
				request.setAttribute("message", service.saveBzrssz(request) ? "保存成功" : "保存失败");
			}else if("qx".equalsIgnoreCase(doType)){
				request.setAttribute("message", service.saveBzrqx(request) ? "保存成功" : "保存失败");
			}
			// 用户组划分时需要用到用户组列表，在此初始化
			String groupList = service.getGroupToSplit();
			//用户组无数据报错
			if ((groupList == null) || groupList.equalsIgnoreCase("")) {
				session.setAttribute("errNo", "Err0013");
				return mapping.findForward("false");
			}
			String powerList = service.getPowerToSplit(userName);
			// 获得所有辅导员信息LIST
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
			myForm.setErrMsg("数据连接中断，请重试！");
			return new ActionForward("/errMsg.do", false);
		}
	}
	/**
	 * 用户查询
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
