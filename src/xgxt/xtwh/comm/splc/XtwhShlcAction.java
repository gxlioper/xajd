package xgxt.xtwh.comm.splc;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.CommSetting;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;
import xgxt.utils.db.GetSysData;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ͨ�ð汾ѧ������-action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ������
 * @version 1.0
 */

public class XtwhShlcAction extends BasicAction {

	/**
	 * �������̹���ģ��
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		RequestForm rForm = new RequestForm();
		XtwhShlcForm myForm = (XtwhShlcForm) form;
		XtwhShlcService service = new XtwhShlcService();
		User user = getUser(request);

		String doType = request.getParameter("doType");
		String message = "";// ��ʾ��Ϣ
		if ("del".equalsIgnoreCase(doType)) {

			boolean blog = service.delShlc(myForm);
			message = blog ? MessageInfo.MESSAGE_DEL_SUCCESS
					: MessageInfo.MESSAGE_DEL_FALSE;
			request.setAttribute("message", message);
		}

		// ===============����������� begin=================
		List<HashMap<String, String>> topTr = service.getTopTr(myForm);
		CommSetting commSetting = new CommSetting();

		// ���������
		String rsName = "rs";
		commSetting.setRsName(rsName);

		// �Ƿ���Ҫcheckbox
		String isCheckBox = "yes";
		commSetting.setIsCheckBox(isCheckBox);

		// ��ʾ����ʼ��
		String startNum = "0";
		commSetting.setStartNum(startNum);

		// ��ʾ����
		String showNum = String.valueOf(topTr.size() - 1);
		commSetting.setShowNum(showNum);
		rForm.setCommSetting(commSetting);
		service.setRequestValue(rForm, user, request);
		// ===============����������� end=================

		request.setAttribute("rs", service.getSplcList(myForm));
		request.setAttribute("topTr", topTr);
		request.setAttribute("ssmkList", service.getSsmkList());
		request.setAttribute("path", "splcNew.do?method=splcManage");
		request.setAttribute("doType", doType);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("splcManage");
	}

	// -------------------------------���ǽ���Ҫ�и��� �ķ�����ʦд����
	// begin----------------------------------
	/**
	 * ���������û�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcYhsz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhShlcForm myForm = (XtwhShlcForm) form;
		XtwhShlcService service = new XtwhShlcService();

		User user = getUser(request);
		myForm.setUser(user);

		String message = "";// ��ʾ��Ϣ
		String doType = request.getParameter("doType");
		// �������������ҳ������ģ��򷵻ص��������ý���
		String lcid = request.getParameter("lcid");

		if (lcid != null && !lcid.equalsIgnoreCase("")) {
			request.setAttribute("lcid", lcid);
			myForm.setId(lcid);
			myForm.setPkValue(lcid);
		}
		// ========== ���水ť��ɾ�� �˴��������� start ==========
		if ("save".equalsIgnoreCase(doType)) {

			// ========== ���Ի� �û���Ȩ begin ============
			boolean blog = true;
			String yhszlx = myForm.getYhszlx();
			if("rcxwwh".equals(yhszlx)){
				// �ճ�����NEW-�ճ���Ϊά��NEW-�ճ���Ϊ����ά��-�ճ���Ϊ���
				blog = service.saveSpgwRcxwwh(myForm);
			}else{
				// ϵͳά��-��������ά��-��������
				blog = service.saveSpgw(myForm);
			}
			// ========== ���Ի� �û���Ȩ end ============
			message = blog ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
			if (lcid != null && !lcid.equalsIgnoreCase("")) {
				return new ActionForward("/splcNew.do?method=splcZs&lcid="
						+ lcid, false);
			}
		}
		// ========== ���水ť��ɾ�� �˴��������� end ==========
		
		// ������λ
		String gwdm = request.getParameter("spgw");

		// ³����������ȡ��λ�������̼���λ����
		String gwlx = request.getParameter("gwlx");
		String[] gwxx = service.getGwxx(gwlx, gwdm,lcid);
		List<HashMap<String, String>> gwxxList =service.getSpgwByShl(myForm);
		request.setAttribute("gwxxList", gwxxList);
		request.setAttribute("gwxx", gwxx);
		request.setAttribute("gwlx", gwlx);// ��ʦ��������BUG����ǿ�ұ����� 2012.6.29
		request.setAttribute("spgw", gwdm);
		String yhszlx = request.getParameter("yhszlx");
		request.setAttribute("yhszlx", yhszlx);
		return mapping.findForward("splcYhsz");
	}

	// -------------------------------���ǽ���Ҫ�и��� �ķ�����ʦд����
	// end----------------------------------

	// -------------------------------���ǽ���Ҫ�еı�¥�ϸ���ķ���
	// begin----------------------------------

	/**
	 * ������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String title = "������Ϣ";
		request.setAttribute("title", title);
		XtwhShlcService service = new XtwhShlcService();
		request.setAttribute("ssmkList", service.getSsmkList());
		request.setAttribute("gdgwList", service.getGdgwList());
		return mapping.findForward("splcAdd");
	}

	/**
	 * ������������
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		XtwhShlcForm myForm = (XtwhShlcForm) form;
		XtwhShlcModel model = new XtwhShlcModel();
		XtwhShlcService service = new XtwhShlcService();
		BeanUtils.copyProperties(model, myForm);
		String lcid = GetSysData.getGuid();
		model.setId(lcid);
		String lcmc = myForm.getLcmc();

		model.setMc(lcmc);
		Boolean update = service.savelcxx(model, request);

		// ����ɹ��������һ��Ԥ��ҳ��
		if (update){
			String type = request.getParameter("type");//�õ��Ƿ���Ҫͨ�ø�λ�û���ʼ����
			if(null != type){//�����
				//ͨ�ù��ܸ��죨���Ӹ���ѡ������ݷ�Χ�����Ӹ�λ�û���
				//service.insertGwyhBySjfw(lcid);	
				service.gwCsh(lcid);	
			}			
			// ��ȡ������Ϣ
			// �û���Ϣ
			HashMap<String, String> rs = service.getLcxx(lcid);
			// ����������λ��Ϣ
			List<String[]> lcgwrs = service.getLcgwxx(lcid);
			String title = "����չʾ";
			request.setAttribute("title", title);
			request.setAttribute("rs", rs);
			request.setAttribute("lcgwrs", lcgwrs);
			request.setAttribute("ssmkList", service.getSsmkList());
			myForm.setMaxSize(new Integer(lcgwrs.size()).toString()); 
			return mapping.findForward("splcLczs");
		} else {
			request.setAttribute("result", "false");
			return mapping.findForward("splcAdd");
		}

	}

	/**
	 * ��������չʾ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward splcZs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// ��ȡ������Ϣ
		// �û���Ϣ
		XtwhShlcService service = new XtwhShlcService();
		XtwhShlcForm myForm = (XtwhShlcForm) form;
		String lcid = request.getParameter("lcid");
		HashMap<String, String> rs = service.getLcxx(lcid);
		// ����������λ��Ϣ
		List<String[]> lcgwrs = service.getLcgwxx(lcid);
		String title = "����չʾ";
		myForm.setMaxSize(new Integer(lcgwrs.size()).toString());
		request.setAttribute("title", title);
		request.setAttribute("rs", rs);
		request.setAttribute("lcgwrs", lcgwrs);
		request.setAttribute("ssmkList", service.getSsmkList());
		return mapping.findForward("splcLczs");
	}

	// -------------------------------���ǽ���Ҫ�еı�¥�ϸ���ķ���
	// end----------------------------------

	/**
	 * �޸���������
	 */
	public ActionForward splcUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		XtwhShlcService service = new XtwhShlcService();
		String lcid = request.getParameter("lcid");
		XtwhShlcForm myForm = (XtwhShlcForm) form;
		// User user=getUser(request);

		HashMap<String, String> rs = service.getLcxx(lcid);
		// ����������λ��Ϣ
		List<String[]> lcgwrs = service.getLcgwxx(lcid);
		String doType = request.getParameter("doType");
		String message = "";// ��ʾ��Ϣ
		if ("update".equalsIgnoreCase(doType)) {
			myForm.setLcmc(request.getParameter("mc"));
			String type = request.getParameter("type");				
			List<HashMap<String, String>> oldSpgwList = service.getSpgwAndGwz(lcid);//��ȡ�ɵ�������λlist			
			
			boolean blog = service.updateShlc(myForm,request,lcgwrs);			
		 //���Ҫ��ʼ����ͨ�ù��ܸ��죨���Ӹ���ѡ������ݷ�Χ���޸ĸ�λ��Ա��
			if(null != type){				
				if(blog){
					List<HashMap<String, String>> newSpgwList = service.getSpgwAndGwz(lcid);//��ȡ�ɵ�������λlist
					service.updateGwzyh(oldSpgwList, newSpgwList, lcid);
				}
			}
			message = blog ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
			request.setAttribute("message", message);
		}
		String title = "����չʾ";
		request.setAttribute("title", title);

		myForm.setMaxSize(new Integer(lcgwrs.size()).toString());

		request.setAttribute("rs", rs);
		request.setAttribute("lcgwrs", lcgwrs);
		request.setAttribute("ssmkList", service.getSsmkList());

		return mapping.findForward("splcUpdate");

	}

}