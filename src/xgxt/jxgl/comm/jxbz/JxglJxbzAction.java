package xgxt.jxgl.comm.jxbz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.jxgl.comm.JxglCommForm;
import xgxt.jxgl.comm.jxjg.JxglJxjgService;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrForm;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrInit;
import xgxt.pjpy.comm.zhcp.sjdr.ZhcpSjdrService;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ѵ����_��ѵ����_Action��
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

public class JxglJxbzAction extends BasicAction {

	/**
	 * ��ѵ����_��ѵ����_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbzManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxbzInit init = new JxglJxbzInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);
		// =================== end ===================
		
		// ============= ����request��ֵ =============
		request.setAttribute("searchTj", myForm.getSearchModel());
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("jxbzManage");
	}
	
	/**
	 * ��ѵ����_��ѵ����_ά��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbzUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxbzInit init = new JxglJxbzInit();
		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String message = "";// ��ʾ��Ϣ
		HashMap<String, String> rs = service.getJxbzInfo(myForm, user);
		// =================== end ===================

		// =================== ��ʼ���б�ֵ ===========
		// ��ѵ���Ƶȼ��б�
		List<HashMap<String, String>> jxbzdjList = jxszModel.getJxbzdjList();
		// ��ѵ�����б�
		List<HashMap<String, String>> jxbzList = service.getJxbzList(myForm,
				user);
		request.setAttribute("jxbzList", jxbzList);
		request.setAttribute("jxbzdjList", jxbzdjList);
		// ================= end =====================
		
		// =================== ��ʼ���б�ֵ ===========
		service.setJxglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("jxbzUpdate");
	}	
	
	/**
	 * ��ѵ����_��ѵ����_�༶����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward jxbzBjfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxbzInit init = new JxglJxbzInit();
		JxglCommForm jxszModel = JxglJxbzForm.jxglCommForm;
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String message = "";// ��ʾ��Ϣ
		HashMap<String, String> rs = service.getJxbzInfo(myForm, user);
		// =================== end ===================

		// =================== ��ʼ���б�ֵ ===========
		// ��ѵ���Ƶȼ��б�
		List<HashMap<String, String>> jxbzdjList = jxszModel.getJxbzdjList();
		// ��ѵ�����б�
		List<HashMap<String, String>> jxbzList = service.getJxbzList(myForm,
				user);
		request.setAttribute("jxbzList", jxbzList);
		request.setAttribute("jxbzdjList", jxbzdjList);
		// ================= end =====================
		
		// =================== ��ʼ���б�ֵ ===========
		service.setJxglOptionList(myForm, rForm, request);
		// ================= end =====================
		
		// ============= ����request��ֵ =============
		rForm.setRs(rs);
		service.setRequestValue(rForm, request);
		// =================== end ===================
		
		return mapping.findForward("jxbzBjfp");
	}
	
	/**
	 * ������ѵ����HTML����󼶱�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getMaxbzHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		User user = getUser(request);// �û�����

		// ѡ�еĲ���
		String checkedBzdm = request.getParameter("checkedBzdm");
		myForm.setCheckedBzdm(checkedBzdm);
		// ============= ����Html ============
		service.createMaxbzHtml(myForm,user,response);
		// ============= ����Html end ============

		return null;
	}
	
	/**
	 * ������ѵ����HTML���μ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getNextbzHtml(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		User user = getUser(request);// �û�����

		//ѡ�еĲ���
		String checkedBzdm = request.getParameter("checkedBzdm");
		myForm.setCheckedBzdm(checkedBzdm);
		// ============= ����Html ============
		service.createNextbzHtml(myForm, user, response);
		// ============= ����Html end============

		return null;
	}
	
	/**
	 * ��ð༶�����б�
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getBjfpList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxbzInit init = new JxglJxbzInit();
		User user = getUser(request);// �û�����
		SearchRsModel rsModel = new SearchRsModel();
		
		// ============= ��ʼ����������ֵ ============
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue").split("!!@@!!");
		// ѧ��
		String xn = otherValue[0];
		myForm.setXn(xn);
		// �ϼ�����
		String sjdm = otherValue[1];
		myForm.setSjdm(sjdm);
		// �꼶
		String nj = otherValue[2];
		myForm.setNj(nj);
		// ѧԺ����
		String xydm = otherValue[3];
		myForm.setXydm(xydm);
		// רҵ����
		String zydm = otherValue[4];
		myForm.setZydm(zydm);
		// �༶����
		String bjdm = otherValue[5];
		myForm.setBjdm(bjdm);
		
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("",request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getBjfpList(myForm, user);
		// ==================��ʾ���� end========================
		
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================
		
		return null;
	}
	
	/**
	 * ������ѵ����JS
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getJxbzJs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();

		// ============= ����js ============
		service.createJxbzJs(myForm, response);
		//service.createMinJxbzJs(myForm, response);
		// ============= ����js end ============

		return null;
	}
	
	/**
	 * �����ѵ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveJxbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxjgService jxjgService= new JxglJxjgService();
		User user = getUser(request);// �û�����

		// ============= �����ѵ���� ============
		boolean flag = service.saveJxbz(myForm, user, request);
		// ��ʾ��Ϣ
		String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
				: MessageInfo.MESSAGE_DO_FALSE;
		
		if(flag){
			//jxjgService.jxmdIni(user);
		}
		// ============= �����ѵ���� end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
	
	/**
	 * ɾ����ѵ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward delJxbz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxjgService jxjgService= new JxglJxjgService();
		JxglJxbzInit init = new JxglJxbzInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		RequestForm rForm = new RequestForm();
		init.getJxbzRForm(rForm, myForm, request);
		// =================== end ===================
		
		// ============= ɾ����ѵ���� ============
		boolean flag = service.delJxbz(myForm, user);
		// ��ʾ��Ϣ
		String message = flag ? MessageInfo.MESSAGE_DEL_SUCCESS
				: MessageInfo.MESSAGE_DEL_FALSE;
		
		if(flag){
			jxjgService.jxmdIni(user);
		}
		// ============= ɾ����ѵ���� end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����༶����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveBjfp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		JxglJxbzForm myForm = (JxglJxbzForm) form;
		JxglJxbzService service = new JxglJxbzService();
		JxglJxjgService jxjgService= new JxglJxjgService();
		User user = getUser(request);// �û�����
		
		// ============= ��ʼ����������ֵ ============
		// ����
		String[] pk = request.getParameter("pk").split("!!@@!!");
		myForm.setPk(pk);
		// �ϼ�����
		String sjdm = request.getParameter("sjdm");
		myForm.setSjdm(sjdm);
		// ============= ��ʼ����������ֵ end ============
		
		// ============= �����ѵ���� ============
		boolean flag = service.saveBjfp(myForm, user);
		// ��ʾ��Ϣ
		String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;
		
		if(flag){
			//jxjgService.jxmdIni(user);
		}
		// ============= �����ѵ���� end ============

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
