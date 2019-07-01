package xsgzgl.customForm.gnmk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommForm;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.studentInfo.service.XsxxglService;
import xgxt.utils.Pages;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �Զ����_�Զ��幦��_Action��
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

public class CustomGnmkAction extends BasicAction {

	/**
	 * �Զ����_����ģ��_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customGnmkManage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		CustomGnmkInit init = new CustomGnmkInit();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initCustomGnmk(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customGnmkManage");
	}

	/**
	 * �Զ����_����ģ��_��ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward customGnmkDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		CustomGnmkInit init = new CustomGnmkInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ��ʼ��
		init.initCustomGnmk(rForm, myForm, user, request);
		// =================== end ===================

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, myForm);
		service.setList(model, rForm, request);
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("customGnmkDetail");
	}

	// =======================���·���==============================

	/**
	 * ��ù���ģ���ѯ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getGnmkManageList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		User user = getUser(request);// �û�����
		CustomGnmkInit init = new CustomGnmkInit();
		SearchRsModel rsModel = new SearchRsModel();
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============	
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// ����ģ�����
		String gnmkdm = otherValue[0];
		myForm.setGnmkdm(gnmkdm);
		
		init.initCustomGnmk(rForm, myForm, user, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = service.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		List<HashMap<String, String>> topTr = rForm.getTopTr();// ����
		ArrayList<String[]> rsArrList = service.getGnmkManageList(myForm, user);
		String spHtml = service.getGnmkManageHtml(rsModel, myForm, rsArrList,
				user);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("yes");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ��ʾ����ģ����ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showGnmkDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// ����ģ�����
		String gnmkdm = request.getParameter("gnmkdm");
		myForm.setGnmkdm(gnmkdm);
		// ============= ��ʼ����������ֵ end============

		// ==================����ǰ̨ҳ��========================
		service.createGnmkDetail(myForm, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ���ѧ����Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String nj = request.getParameter("nj");// �꼶
		myForm.setNj(nj);

		String xydm = request.getParameter("xydm");// ѧԺ����
		myForm.setXydm(xydm);

		String zydm = request.getParameter("zydm");// רҵ����
		myForm.setZydm(zydm);

		String bjdm = request.getParameter("bjdm");// �༶����
		myForm.setBjdm(bjdm);

		String xh = request.getParameter("xh");// ѧ��
		myForm.setXh(xh);

		String xm = request.getParameter("xm");// ����
		if(!Base.isNull(xm)){
			xm = service.unicode2Gbk(xm);
		}
		myForm.setXm(xm);
		
		String ryfw = request.getParameter("ryfw");// ��Ա��Χ
		myForm.setRyfw(ryfw);
		
		String xsnum = request.getParameter("xsnum");// ѧ������
		myForm.getPages().setCurrentPage(Integer.parseInt(xsnum));
		// ============= ��ʼ����������ֵ end============

		// ==================����ǰ̨ҳ��========================
		service.getXsInfo(myForm, user, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ���ѧ����ϸ��Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getXsInfoDetail(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		XsxxglService stuService = new XsxxglService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String xh = request.getParameter("xh");// ѧ��
		myForm.setXh(xh);
		// ============= ��ʼ����������ֵ end============

		// ==================����ǰ̨ҳ��========================
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> stuInfo = stuService.selectStuinfo(xh);
		list.add(stuInfo);
		// ==================����ǰ̨ҳ�� end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(JSONArray.fromObject(list));

		return null;
	}
	
	/**
	 * ���湦��ģ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveGnmk(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CustomGnmkForm myForm = (CustomGnmkForm) form;
		CustomGnmkService service = new CustomGnmkService();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		String xmb = request.getParameter("xmb");// ��Ŀ��;
		myForm.setXmb(xmb);

		String gnmkdm = request.getParameter("gnmkdm");// ����ģ�����;
		myForm.setGnmkdm(gnmkdm);

		String zd = request.getParameter("zd");// �ֶ�;
		if (!Base.isNull(zd)) {
			myForm.setZd(zd.split("!!@@!!"));
		}

		String zdz = request.getParameter("zdz");// �ֶ�ֵ;
		if (!Base.isNull(zdz)) {
			myForm.setZdz(zdz.split("!!@@!!"));
		}
		// ============= ��ʼ����������ֵ end============

		// ==================ִ�б������========================
		boolean flag = service.saveGnmk(myForm, user);
		String message = flag ? "����ɹ�" : "����ʧ�ܣ�����ϵ�����Ա";
		// ==================ִ�б������ end========================

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}
}
