package xgxt.pjpy.comm.pjpy.mypj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ҵ�����-action��
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

public class PjpyMypjAction extends BasicAction {


	/**
	 * ��������_�ҵ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward mypjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjService service = new PjpyMypjService();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String userType = user.getUserType();// �û�����
		String path = "";
		String forward = "pjpy_mypj.do";
		// =================== end ===================

		if ("stu".equalsIgnoreCase(userType)) {
			forward = "myPjForStu";
		} else {
			forward = "myPjForTea";
		}

		rForm.setPath(path);
		service.setRequestValue(rForm, request);
		
		return mapping.findForward(forward);
	}
	
	/**
	 * ��������_�ҵ�����_��ʦ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward myPjForTea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjForm myForm = (PjpyMypjForm) form;
		PjpyMypjService service = new PjpyMypjService();
		PjpyMypjInit init = new PjpyMypjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		RequestForm rForm = new RequestForm();
		init.getMypjForTeaRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String message = "";// ��ʾ��Ϣ	
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("myPjForTea");
	}	
	
	/**
	 * ��������_�ҵ�����_ѧ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward myPjForStu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjForm myForm = (PjpyMypjForm) form;
		PjpyMypjService service = new PjpyMypjService();
		PjpyMypjInit init = new PjpyMypjInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		RequestForm rForm = new RequestForm();
		init.getMypjForStuRForm(rForm, myForm, request);
		
		String userType = user.getUserType();// �û�����
		// =================== end ===================

		// ============= ��ѧ���û����ɲ��� ============
		if (!"stu".equalsIgnoreCase(userType)) {
			String message = "��ģ��ֻ����ѧ���û����в�������ȷ�ϣ�";
			request.setAttribute("yhInfo", message);
			return new ActionForward("/yhInfo.do", false);
		}
		// =================== end ===================
		
		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("myPjForStu");
	}	
	
	/**
	 * ����ҵ�����ͳ���б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMypjTjList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjForm myForm = (PjpyMypjForm) form;
		PjpyMypjService service = new PjpyMypjService();
		PjpyMypjInit init = new PjpyMypjInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		
		// ============= ��ʼ����������ֵ ============		
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		// �汾����
		String bblx = otherValue[0];
		myForm.setBblx(bblx);
		
		if ("tea".equalsIgnoreCase(bblx)) {//��ʦ��
			init.getMypjForTeaRForm(rForm, myForm, request);
		}else if("stu".equalsIgnoreCase(bblx)){//ѧ����
			init.getMypjForStuRForm(rForm, myForm, request);
		}
		// =================== end ===================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================
		
		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		String spHtml = "";
		if ("tea".equalsIgnoreCase(bblx)) {// ��ʦ��
			spHtml = service.getMypjTeaHtml(rsModel, myForm, user);
		} else if ("stu".equalsIgnoreCase(bblx)) {// ѧ����
			spHtml = service.getMypjStuHtml(rsModel, myForm, user);
		}
		// ==================��ʾ���� end========================
		
		// ==================����ǰ̨ҳ��========================
		int rows = Base.isNull(myForm.getRows()) ? 0 : Integer.parseInt(myForm
				.getRows());
		
		rsModel.setTopTr(topTr);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setLeft("no");
		rsModel.setRows(String.valueOf(rows));
		
		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================
		
		return null;
	}
	
	/**
	 * �洢����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward MD5Transfer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		DAO dao = DAO.getInstance();
		boolean res = false;
		String sql = "{call pro_changeEncryptMode()}";
		res = dao.runProcuder(sql, new String[] {});
		if (res) {
			request.setAttribute("autoChk", "ok");
		} else {
			request.setAttribute("autoChk", "no");
		}
		return mapping.findForward("MD5Transfer");
	}
}
