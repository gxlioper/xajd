package xsgzgl.xsxx.general;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.Configuration;
import xgxt.comm.CommForm;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.xsxx.general.dljc.XsxxDljcInit;
import xsgzgl.xsxx.general.dljc.XsxxDljcModel;
import xsgzgl.xsxx.general.jcsz.XsxxJcszService;
import xsgzgl.xsxx.general.xjyd.XsxxXjydInit;
import xsgzgl.xsxx.general.xxxg.xgjg.XgjgInit;
import xsgzgl.xsxx.general.xxxg.xgjg.XgjgModel;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshInit;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsInit;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class XsxxGeneralAction extends BasicAction {

	// б��
	public static String slashes;

	static {
		if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
			slashes = "/";
		} else {
			slashes = "\\";
		}
	}

	// =================ѧ����Ϣ begin ===================

	/**
	 * ѧ����Ϣ_��Уѧ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward zxxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		RequestForm rForm = new RequestForm();
		XsxxZxxsInit init = new XsxxZxxsInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initZxxs(rForm, myForm, user, request);

		System.out
				.println("-----------------------------this way begin--------------------------------------------");
		System.out.println("slashes��" + slashes);
		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		System.out.println("xxpymc��" + xxpymc);
		// ����·��
		String pro_path = Configuration.PRO_PATH.substring(0,
				Configuration.PRO_PATH.lastIndexOf(slashes));
		System.out.println("pro_path��" + pro_path);
		// ��ת·��
		String url = "/xsgzgl/xsxx/" + xxpymc + "/zxxs/zxxsManage.jsp";
		System.out.println("url��" + url);
		// �ļ�·��
		String file_paht = pro_path + url;
		System.out.println("file_paht��" + file_paht);
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================
		System.out
				.println("-----------------------------this way end--------------------------------------------");

		// ============= �ж��Ƿ�ͨ�� begin ============
		// File file = new File(file_paht);
		// if (!file.exists()) {
		// return mapping.findForward("zxxsManage");
		// }
		// ============= �ж��Ƿ�ͨ�� end ============

		return new ActionForward(url, false);
	}

	/**
	 * ѧ����Ϣ_ѧ����Ϣ�޸�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xsxxModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		RequestForm rForm = new RequestForm();
		XsxxZxxsInit init = new XsxxZxxsInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initZxxs(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ����·��
		String pro_path = Configuration.PRO_PATH.substring(0,
				Configuration.PRO_PATH.lastIndexOf(slashes));
		// ��ת·��
		String url = "/xsgzgl/xsxx/" + xxpymc + "/zxxs/xsxxUpdate.jsp";
		// �ļ�·��
		String file_paht = pro_path + url;
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		// ============= �ж��Ƿ�ͨ�� begin ============
		// File file = new File(file_paht);
		// if (!file.exists()) {
		// return mapping.findForward("xsxxUpdate");
		// }
		// ============= �ж��Ƿ�ͨ�� end ============

		return new ActionForward(url, false);
	}

	/**
	 * ѧ����Ϣ_ѧ����Ϣ��ϸ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xsxxDetailed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
//		XsxxGeneralService service = new XsxxGeneralService();
//		RequestForm rForm = new RequestForm();
//		XsxxZxxsInit init = new XsxxZxxsInit();
//		User user = getUser(request);// �û�����
//
//		// ============= ��ʼ����������ֵ begin ============
//		init.initZxxs(rForm, myForm, user, request);
//
//		// ѧУƴ������
//		String xxpymc = myForm.getXxpymc();
//		// ��ת·��
//		String url = "/xsgzgl/xsxx/" + xxpymc + "/zxxs/xsxxDetail.jsp";
//		// ============= ��ʼ����������ֵ end ==============
//
//		// ============= ����request��ֵ =============
//		CommForm model = new CommForm();
//		service.setRequestValue(rForm, request);
//		service.setList(model, rForm, request);
		// =================== end ===================
		
		//2013-1-31 qph ���鿴 ѧ����ϸ��ת����·��
		return new ActionForward("/xsxx_tygl.do?method=ckZxsxx&xh="+myForm.getXh(),false);
		//return new ActionForward(url, false);
	}

	/**
	 * ѧ����Ϣ_��ʷѧ������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward lsxsManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		RequestForm rForm = new RequestForm();
		XsxxZxxsInit init = new XsxxZxxsInit();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initZxxs(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ����·��
		String pro_path = Configuration.PRO_PATH.substring(0,
				Configuration.PRO_PATH.lastIndexOf(slashes));
		// ��ת·��
		String url = "/xsgzgl/xsxx/" + xxpymc + "/lsxs/lsxsManage.jsp";
		// �ļ�·��
		String file_paht = pro_path + url;
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		// ============= �ж��Ƿ�ͨ�� begin ============
		// File file = new File(file_paht);
		// if (!file.exists()) {
		// return mapping.findForward("lsxsManage");
		// }
		// ============= �ж��Ƿ�ͨ�� end ============

		return new ActionForward(url, false);
	}

	// =================ѧ����Ϣ end ===================

	// =================ѧ���춯 begin ===================

	/**
	 * ѧ����Ϣ_ѧ���춯����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xjydManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxXjydInit init = new XsxxXjydInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initXjyd(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/xsxx/" + xxpymc + "/xjyd/xjydManage.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// =================ѧ���춯 end ===================

	// =================��¼��� begin ===================

	/**
	 * ѧ����Ϣ_��¼����ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward dljcSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxDljcInit init = new XsxxDljcInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initDljcSearch(rForm, myForm, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/xsxx/" + xxpymc + "/dljc/dljcSearch.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ѧ����Ϣ_ѧ����¼��Ϣ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward dljcInput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxDljcModel model = new XsxxDljcModel();
		XsxxDljcInit init = new XsxxDljcInit();

		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initDljcInput(rForm, myForm, model, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "";
		// �Ƿ���Ϣ����
		boolean isXxws = model.isXxws();

		if (isXxws) {
			url = "/stuPage.jsp";
		} else {
			url = "/xsgzgl/xsxx/" + xxpymc + "/dljc/dljcInput.jsp";
		}
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	// =================��¼��� end ===================

	// =================������Ϣ�޸� begin ===================

	/**
	 * ������Ϣ�޸�_�޸Č��ˡ���ԃ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgshSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XsxxJcszService jcszService = new XsxxJcszService();
		XgshInit init = new XgshInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initXgshSearch(rForm, myForm, user, request);
		init.initParameter(myForm);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/xsxx/general/xxxg/" + xxpymc + "/xgsh/xgshSearch.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= �Д��Ñ��������P begin ============

		// �������P��Ϣ
		HashMap<String, Object> shxgInfo = jcszService.getShlcByYh(user
				.getUserName());
		// ��������
		String splc = (String) shxgInfo.get("splc");
		// ������λ�б�
		List<HashMap<String, String>> spgwList = (List<HashMap<String, String>>) shxgInfo
				.get("spgwList");

		String msg = "";

		if ("wxsh".equalsIgnoreCase(splc)) {
			msg = "���θ�����Ϣ�޸�������ˣ���ȷ��";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		} else if (spgwList == null || spgwList.size() == 0) {
			msg = "�����ڱ��θ�����Ϣ�޸���˵���Ա�����У��������飬������ϵ����Ա";
			request.setAttribute("yhInfo", msg);
			return new ActionForward("/yhInfo.do", false);
		}
		request.setAttribute("splc", splc);
		request.setAttribute("gwid", spgwList.get(0).get("spgw"));
		request.setAttribute("spgwList", spgwList);
		// ===================== end ======================

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ������Ϣ�޸�_�޸Č��ˡ�Ԕ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XgshModel model = new XgshModel();
		XgshInit init = new XgshInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initXgshDetail(rForm, myForm, model, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/xsxx/general/xxxg/" + xxpymc + "/xgsh/xgshDetail.jsp";
		/*if(Base.xxdm.equals("12036")){
			 url = "/xsgzgl/xsxx/general/xxxg/zjzyjt/xgsh/xgshDetail.jsp";
		}*/
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ������Ϣ�޸�_�޸ĽY������ԃ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgjgSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XgjgInit init = new XgjgInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initXgjgSearch(rForm, myForm, user, request);
		init.initParameter(myForm);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/xsxx/general/xxxg/" + xxpymc + "/xgjg/xgjgSearch.jsp";
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm model = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(model, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}

	/**
	 * ������Ϣ�޸�_�޸ĽY����Ԕ����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward xgjgDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XsxxGeneralForm myForm = (XsxxGeneralForm) form;
		XsxxGeneralService service = new XsxxGeneralService();
		XgjgModel model = new XgjgModel();
		XgjgInit init = new XgjgInit();
		RequestForm rForm = new RequestForm();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ begin ============
		init.initXgjgDetail(rForm, myForm, model, user, request);

		// ѧУƴ������
		String xxpymc = myForm.getXxpymc();
		// ��ת·��
		String url = "/xsgzgl/xsxx/general/xxxg/" + xxpymc + "/xgjg/xgjgDetail.jsp";
		if(Base.xxdm.equals("12036")){
			 url = "/xsgzgl/xsxx/general/xxxg/zjzyjt/xgjg/xgjgDetail.jsp";
		}
		// ============= ��ʼ����������ֵ end ==============

		// ============= ����request��ֵ =============
		CommForm commModel = new CommForm();
		service.setRequestValue(rForm, request);
		service.setList(commModel, rForm, request);
		// =================== end ===================

		return new ActionForward(url, false);
	}
	
	// =================������Ϣ�޸� end ===================
}
