package xgxt.xsxx.comm.ajax;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_Ajax_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxAjaxAction extends BasicAction{

	/**
	 * ���ѧ����ͥ���
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getQtxxInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String xh = request.getParameter("xh");
		String mklx = request.getParameter("mklx");
		
		XsxxAjaxService service = new XsxxAjaxService();

		List<HashMap<String, String>> list = service.getQtxxInfo(mklx,xh);
		
		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));
		
		return null;
	}
	
	/**
	 * ����session��ֵ
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setSessionValue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String path = request.getParameter("path");
		
		HttpSession session = request.getSession();
		session.setAttribute("clickPath", path);
		
		return null;
	}
	
	/**
	 * ���ز��������б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward setBmOption(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String bmjb = request.getParameter("bmjb");
		String bmmc = request.getParameter(bmjb);
			
		String nj = request.getParameter("nj");
		String xydm = request.getParameter("xydm");
		String zydm = request.getParameter("zydm");
		String bjdm = request.getParameter("bjdm");
		
		if (!Base.isNull(bmmc)){
			bmmc = URLDecoder.decode(bmmc, "UTF-8");
		}
		
		XsxxAjaxService service = new XsxxAjaxService();
			
		XsxxAjaxModel model = new XsxxAjaxModel();
		model.setBmjb(bmjb);
		model.setBmmc(bmmc);
		model.setNj(nj);
		model.setXydm(xydm);
		model.setZydm(zydm);
		model.setBjdm(bjdm);
		
		User user = getUser(request);// �û�����
		
		List<HashMap<String, String>> map = service.getBmOption(model,user);
		JSONArray bmmcList = JSONArray.fromObject(map);
		response.setContentType("text/html;charset=gbk"); 
		response.getWriter().print(bmmcList);
		
		return null;
	}
	
	/**
	 * �ж��Ƿ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkIsExists(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxAjaxModel model = new XsxxAjaxModel();
		XsxxAjaxService service = new XsxxAjaxService();
		// ���˴���
		String dm = request.getParameter("dm");
		// ����
		String[] pk = request.getParameter("pk").split("!!@@!!");
		// ����ֵ
		String pkValue = service.unicode2Gbk(request.getParameter("pkValue"));
		// ����
		String tableName = request.getParameter("tableName");
		// ������Ϣ
		String errMsg = service.unicode2Gbk(request.getParameter("errMsg"));

		model.setDm(dm);
		model.setPk(pk);
		model.setPkValue(pkValue);
		model.setTableName(tableName);
		model.setErrMsg(errMsg);

		// �ж��Ƿ����
		boolean flag = service.checkIsExists(model);

		// ��ʾ��Ϣ
		String message = "";

		if (!flag) {
			message = errMsg;
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ����û���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward checkUserName(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxAjaxService service = new XsxxAjaxService();
		// �û���
		String userName = request.getParameter("userName");

		// �û�����
		String userType = service.checkUserName(userName);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(userType);

		return null;
	}
	

	/**
	 * 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jdqkInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxAjaxService service = new XsxxAjaxService();
		// �û���
		String xh = request.getParameter("xh");

		// �û�����
		HashMap<String,String>jdqkMap = service.getJdqkInfo(xh);

		response.setContentType("text/html;charset=gbk"); 
		
		response.getWriter().print(JSONArray.fromObject(jdqkMap));

		return null;
	}

	/**
	 * ����ѧ���ɼ�HTML
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward createXscjHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxAjaxService service = new XsxxAjaxService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin =======
		String xh = request.getParameter("xh");
		// =================== end ===================

		// ==================����ǰ̨ҳ��====================
		service.createXscjHtml(xh, user, response);
		// ==================����ǰ̨ҳ�� end================

		return null;
	}

}
