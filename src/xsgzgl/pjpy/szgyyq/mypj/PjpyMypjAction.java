package xsgzgl.pjpy.szgyyq.mypj;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

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
 * Description: ��������_���ݹ�ҵ԰��_�ҵ�����_Action��
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
	 * ��������_�ҵ�����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward mypjManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyMypjForm myForm = (PjpyMypjForm) form;
		PjpyMypjService service = new PjpyMypjService();
		PjpyMypjInit init = new PjpyMypjInit();
		User user = getUser(request);// �û�����
		HttpSession session = request.getSession();
		
		// ============= ��ʼ����������ֵ ============
		// ����
		String yhlx = service.getMypjYhlx(user);
		request.setAttribute("yhlx", yhlx);
		session.setAttribute("yhlx", yhlx);
		// =================== end ===================

		// ============= �����û������ж���ת ============
		String forward = "";

		if ("stu".equalsIgnoreCase(yhlx)) {//��ͨѧ��
			forward = "mypjForStu";
		} else if ("bz".equalsIgnoreCase(yhlx)) {//�೤
			forward = "mypjForBz";
		} else if ("bzr".equalsIgnoreCase(yhlx)) {//������
			List<HashMap<String,String>> rs=service.getBzrMyPjTjList(user);
			request.setAttribute("rs", rs);
			forward = "mypjForBzr";
		} else if ("xy".equalsIgnoreCase(yhlx) || "xx".equalsIgnoreCase(yhlx)) {//ѧУ��Ժϵ
			List<HashMap<String,String>> rs=service.getXxMyPjTjList(user);
			request.setAttribute("rs", rs);
			forward = "mypjForXx";
		}
		
		// ѧ������
		String xqmc = service.getOneValue("xqdzb", "xqmc", "xqdm", Base.currXq);
		request.setAttribute("xn", Base.currXn);
		request.setAttribute("xqmc", xqmc);
		// =================== end ===================

		return mapping.findForward(forward);
	}

	/**
	 * ����ҵ�����ͳ���б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getStuTjList(ActionMapping mapping, ActionForm form,
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

		// �û�����
		String yhlx = otherValue[0];
		myForm.setYhlx(yhlx);

		// ��ʼ��
		init.initMypj(rForm, myForm, request);
		// =================== end ===================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ҳ���============================
		Pages pages = service.setPages("8", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================��ʾ����============================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		String spHtml = "";

		if ("stu".equalsIgnoreCase(yhlx)) {// ��ͨѧ��
			spHtml = service.getMypjStuHtml(rsModel, myForm, user);
		} else if ("bz".equalsIgnoreCase(yhlx)) {// �೤
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
		
}
