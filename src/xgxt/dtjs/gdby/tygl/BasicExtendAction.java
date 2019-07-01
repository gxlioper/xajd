package xgxt.dtjs.gdby.tygl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.utils.FormModleCommon;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.action.SuperAction;

/**
 * Title: ѧ����������ϵͳ
 * Description:������Action,����һЩ���õķ���
 * Copyright: Copyright (c) 2010
 * Company: zfsoft
 * Author: sjf
 * Version: 1.0
 * Time: 2010-7-30
 */
public class BasicExtendAction extends SuperAction{

	/** 
	 * ��userType �ֳ����࣬����������Ա��ѧԺ��ѧУ
	 * @param session
	 * @return
	 */
	protected String getUserType(HttpSession session){
		String userType = session.getAttribute("userType").toString();
		
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
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
	 * ��userType �ֳ����࣬�����������Σ�ѧԺ��ѧУ
	 * @param session
	 * @return
	 */
	protected String getUserType1(HttpSession session){
		String userType = session.getAttribute("userType").toString();
		
		boolean isBzr = Boolean.valueOf(session.getAttribute("isBzr").toString());
		if(isBzr){
			userType = "bzr";
		}else if("xy".equalsIgnoreCase(userType)){
			userType = "xy";
		}else if("admin".equalsIgnoreCase(userType) || ("xx".equalsIgnoreCase(userType))){
			userType = "xx";
		}
		return userType;
	}
	
	/** 
	 * ��userType ѧԺ��ѧУ
	 * @param session
	 * @return
	 */
	protected String getUserType2(HttpSession session){
		String userType = session.getAttribute("userType").toString();
		if("xy".equalsIgnoreCase(userType)){
			userType = "xy";
		}else if("admin".equalsIgnoreCase(userType) || ("xx".equalsIgnoreCase(userType))){
			userType = "xx";
		}
		return userType;
	}
	
	
	/** 
	 * ��userType �ֳ����࣬������������,����Ա,ѧԺ��ѧУ
	 * @param session
	 * @return
	 */
	protected String getUserType3(HttpSession session){
		String userType = session.getAttribute("userType").toString();
		
		boolean isBzr = Boolean.valueOf(session.getAttribute("isBzr").toString());
		boolean isFdy = Boolean.valueOf(session.getAttribute("isFdy").toString());
		
		if(isBzr){
			userType = "bzr";
		}else if(isFdy){
			userType = "fdy";
		}else if("xy".equalsIgnoreCase(userType)){
			userType = "xy";
		}else if("admin".equalsIgnoreCase(userType) || ("xx".equalsIgnoreCase(userType))){
			userType = "xx";
		}
		return userType;
	}
	
	/**
	 * ����ҳ���title��writeAble
	 * @param request
	 * @param path
	 */
	protected void setWriteAbleAndTitle(HttpServletRequest request, String path){
		request.setAttribute("path", path);
		
		String[] writeAbleAndTitle = FormModleCommon.getWriteAbleAndTitle(request);
		
		request.setAttribute("writeAble", writeAbleAndTitle[0]);
		request.setAttribute("title", writeAbleAndTitle[1]);
	}
	
	/**
	 * ��ʾ��Ҫ��ȡ���ݿ���ֶΰ��������ֶ�,�����������
	 * @param request
	 * @param user
	 * @return
	 */
	protected void setDisabledField(HttpServletRequest request, String user){
		// ��ȡ�ϼ��Ƿ����ͨ����Ϣ�����ظ�ҳ��
		if("fdy".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xysh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else if("xy".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else{
			request.setAttribute("clientColumns", "'' disabled,");
		}
	}
	
	/**
	 * ��ʾ��Ҫ��ȡ���ݿ���ֶΰ��������ֶ�,���ڶ������
	 * @param request
	 * @param user
	 * @return
	 */
	protected void setDisabledField1(HttpServletRequest request, String user){
		// ��ȡ�ϼ��Ƿ����ͨ����Ϣ�����ظ�ҳ��
		if(!"xx".equalsIgnoreCase(user)){
			request.setAttribute("clientColumns", "(case xxsh when 'ͨ��' then 'disabled' else '' end)disabled,");
		}else{
			request.setAttribute("clientColumns", "'' disabled,");
		}
	}
	
	/**
	 * ��ý�ʦ��Ϣ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getTeaInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String tableName = "fdyxxb";
		String viewName = "view_fdyxx";
		
		String isZxs=request.getParameter("isZxs");
		if("yes".equalsIgnoreCase(isZxs)){
			tableName = "xg_xljk_zxsxxb";
			viewName="xg_view_xljk_zxsxx";
		}
		
		
		String go = request.getParameter("go");
		
		if ("go".equalsIgnoreCase(go)) {
			String[] output = new String[] { "zgh", "xm", "xb", "zwmc", "bmmc",
					"xl" };
			this.selectPageDataByPagination(request, form, tableName, viewName,
					output);
		}
		
		request.setAttribute("isZxs", isZxs);
		request.setAttribute("xyList", Base.getXyList());
		
		//path:/szdw/xysf/teainfo.jsp,����stu_info.jsp
		return mapping.findForward("teainfo");
	}
}
