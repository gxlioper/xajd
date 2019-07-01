package xgxt.pjpy.comm.pjpy.pjlc.xmsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.MessageInfo;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyStuModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;
import xgxt.pjpy.comm.pjpy.pjlc.xmsq.PjpyXmsqService;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_��Ŀ���-action��
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

public class PjpyXmshAction extends BasicAction {

	/**
	 * ��������_��������_��Ŀ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmshManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmshForm myForm = (PjpyXmshForm) form;
		PjpyXmshService service = new PjpyXmshService();
		PjpyXmshInit init = new PjpyXmshInit();
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		
		String xmshdm=request.getParameter("xmshdm");
		// �������ʾ�ֶ�
		String editPageSize = request.getParameter("editPageSize");
		if (!"yes".equalsIgnoreCase(editPageSize)) {
			myForm.getPages().setPageSize(16);
			request.setAttribute("autoPageSize", "16");
		}

		//���ʷ�ʽ
		String fwfs=request.getParameter("fwfs");
		String gwxx=request.getParameter("gwxx");
		String jbxx=request.getParameter("jbxx");
		if(!Base.isNull(jbxx)){
			myForm.setShjb(jbxx);
			myForm.setSpgw(gwxx);
		}
		// �����Ŀ
		String shxm =!Base.isNull(xmshdm)? xmshdm :myForm.getShxm();
		myForm.setShxm(shxm);
		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();

		// ��Ŀ����model��ʼ��
		String pkValue = pjxn + pjxq + pjnd + shxm;
		PjpyXmszModel xmszModel = service.getXmszForXmdm(pkValue);
		
		myForm.setJbszModel(jbszModel);
		myForm.setXmszModel(xmszModel);
		
		RequestForm rForm = new RequestForm();
		init.getPjxmshRForm(rForm, myForm, request);

		// �Ƿ��ѯ����
		boolean search = Boolean.parseBoolean(rForm.getSearch());
		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// �������ʾ�ֶ�
		String[] colList = rForm.getColList();
		// ����б�
		ArrayList<String[]> rsArrList = null;

		// ================= end =====================

		// =============== �����Ŀ�б� ===============
		String xmdm = request.getParameter("xmdm");// ��Ŀ����
		String xmmc = request.getParameter("xmmc");// ��Ŀ����
		String ywmc = request.getParameter("ywmc");// Ӣ������
		String xmxz = request.getParameter("xmxz");// ��Ŀ����
		String xmfw = request.getParameter("xmfw");// ��Ŀ��Χ
		String xmlx = request.getParameter("xmlx");// ��Ŀ����
		
		myForm.setXmdm(xmdm);
		myForm.setXmmc(xmmc);
		myForm.setYwmc(ywmc);
		myForm.setXmxz(xmxz);
		myForm.setXmfw(xmfw);
		myForm.setXmlx(xmlx);
		List<HashMap<String, String>> xhxmList = service.getXhxmList(myForm,
				user);
		request.setAttribute("xhxmList", xhxmList);
		request.setAttribute("hid_xmdm", xmdm);
		request.setAttribute("hid_xmmc", xmmc);
		request.setAttribute("hid_ywmc", ywmc);
		request.setAttribute("hid_xmxz", xmxz);
		request.setAttribute("hid_xmfw", xmfw);
		request.setAttribute("hid_xmlx", xmlx);
		// ================= end =====================
		  
		// =============== ִ����˲��� ===========
		if ("sh".equalsIgnoreCase(doType)) {

			// ���״̬
			String shzt = myForm.getShzt();
			// ��˼���
			String shjb = myForm.getShjb();
			// ������˼���
			String zzShjb = service.getXmzzshjb(myForm);
			// ��ʾ��Ϣ
			String message = "";
			
			// ���Ϊ���ռ�
			if (shjb.equalsIgnoreCase(zzShjb) && "ͨ��".equalsIgnoreCase(shzt)) {
				message = service.getXmxzInfo(myForm);
			}
			
			// ����δ��������
			if (Base.isNull(message)) {
				boolean flag = service.saveXmshzt(myForm, user);

				if (flag && "�˻�".equalsIgnoreCase(shzt)) {
					// �˻ز�����Ҫ�����ϼ����״̬
					flag = service.saveXmcszt(myForm, user);
				} else if (flag && "ͨ��".equalsIgnoreCase(shzt)
						&& !shjb.equalsIgnoreCase(zzShjb)) {
					// ����˲�����Ҫ�����¼����״̬
					flag = service.saveXmycszt(myForm, user);
				}
				message = flag ? MessageInfo.MESSAGE_SH_SUCCESS
						: MessageInfo.MESSAGE_SH_FALSE;
			}
			
			rForm.setMessage(message);

			// ������ִ�в�ѯ
			search = true;
		}
		// ================= end =====================
		
		// =============== ִ�в�ѯ���� ===========
		if (search) {
			// �����
			rsArrList = service.getXmshXsList(myForm, user, colList, request);
			request.setAttribute("searchTj", myForm.getSearchModel());
		} else {
			myForm.getPages().setMaxPage(1);
		}

		rForm.setRsArrList(rsArrList);
		// ================= end =====================

		// =================== ��ʼ���б�ֵ ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		request.setAttribute("fwfs", fwfs);
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("xmshManage");
	}

	/**
	 * ��������_��������_��Ŀ���(��ϸ)
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward xmshDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		PjpyXmshForm myForm = (PjpyXmshForm) form;
		PjpyXmshService service = new PjpyXmshService();
		PjpyXmshInit init = new PjpyXmshInit();

		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============

		// ����ѧ��
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// ����ѧ��
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// �������
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// ����
		String pkValue = request.getParameter("pk");
		myForm.setPkValue(pkValue);
		// ��˼���
		String shjb = request.getParameter("shjb");
		myForm.setShjb(shjb);
		// �����Ŀ
		String shxm = request.getParameter("shxm");
		myForm.setShxm(shxm);
		myForm.setXmdm(shxm);
		
		// ��Ŀ����model��ʼ��
		String pk = pjxn + pjxq + pjnd + shxm;
		PjpyXmszModel xmszModel = service.getXmszForXmdm(pk);
		myForm.setXmszModel(xmszModel);
		myForm.setJbszModel(PjxtszModel.pjxtszModel);
		
		RequestForm rForm = new RequestForm();
		init.getPjxmshRForm(rForm, myForm, request);

		// �������ͣ����棬ɾ���ȣ�
		String doType = rForm.getDoType();
		// ��Ŀ����
		String xmmc = xmszModel.getXmmc();
		myForm.setXmmc(xmmc);
		// ================= end =====================

		// =============== ִ����˲��� ===========
		if ("sh".equalsIgnoreCase(doType)) {

			String[] PrimaryKey = new String[] { pkValue };
			myForm.setPrimarykey_checkVal(PrimaryKey);
			// ���״̬
			String shzt = myForm.getShzt();
			// ������˼���
			String zzShjb = service.getXmzzshjb(myForm);
			// ��ʾ��Ϣ
			String message = "";

			// ���Ϊ���ռ�
			if (shjb.equalsIgnoreCase(zzShjb) && "ͨ��".equalsIgnoreCase(shzt)) {
				message = service.getXmxzInfo(myForm);
			}

			// ����δ��������
			if (Base.isNull(message)) {
				boolean flag = service.saveXmshzt(myForm, user);

				if (flag && "�˻�".equalsIgnoreCase(shzt)) {
					// �˻ز�����Ҫ�����ϼ����״̬
					flag = service.saveXmcszt(myForm, user);
				} else if (flag && "ͨ��".equalsIgnoreCase(shzt)
						&& !shjb.equalsIgnoreCase(zzShjb)) {
					// ����˲�����Ҫ�����¼����״̬
					flag = service.saveXmycszt(myForm, user);
				}
				message = flag ? MessageInfo.MESSAGE_SH_SUCCESS
						: MessageInfo.MESSAGE_SH_FALSE;

			} else {
				//˳����Ŀ
				List<HashMap<String, String>> syxmList = service
						.getSyxmList(myForm);
				
				if (syxmList != null && syxmList.size() > 0) {
					request.setAttribute("xmsy", "yes");
					request.setAttribute("syxmList", syxmList);
				}
			}
			
			rForm.setMessage(message);
		}
		// ================= end =====================

		// =============== ִ����˲��� ===========
		if ("xmsy".equalsIgnoreCase(doType)) {
			// �ɷ�˳��
			boolean kfsy = service.getKfsy(myForm);
			String message = "";

			if (kfsy) {
				boolean flag = service.saveXmsy(myForm, user);
				message = flag ? MessageInfo.MESSAGE_SH_SUCCESS
						: MessageInfo.MESSAGE_SH_FALSE;
			} else {
				message = "����Ŀ�Ѿ����룬������ѡ��˳����Ŀ!";
			}
			rForm.setMessage(message);
		}
		// ================= end =====================
		
		// =================== ��������ϸ��� ===========
		
		List<HashMap<String, String>> rsList = service.getXmshInfoList(myForm);
		request.setAttribute("rsList", rsList);
		System.out.println("��Ŀ��������Ϣ��¼��:"+rsList.size());       
		
		String xh = "";
		if (rsList != null && rsList.size() > 0) {
			xh = rsList.get(0).get("xh");
			request.setAttribute("rs", rsList.get(0));
			System.out.println("Action������ѧ��.....��"+xh);
		}
		
		PjpyXmsqService xmsqService = new PjpyXmsqService();
		PjpyStuModel stuModel = xmsqService.getStuForPj(xh, xmszModel);
		
		request.setAttribute("xmxx", xmszModel);
		request.setAttribute("pjxtsz", PjxtszModel.pjxtszModel);		 // ������Ŀ��Ϣ
		request.setAttribute("stuJbxx", stuModel.getJbxx());		 // ѧ��������Ϣ
		request.setAttribute("stuCjxx", stuModel.getCjxx());		 // ѧ���ɼ���Ϣ
		request.setAttribute("stuDjks", stuModel.getDjks());		 // �ȼ�����
		request.setAttribute("stuZcxx", stuModel.getZcxx());         // �۲�ɼ���Ϣ
		request.setAttribute("shlc", xmsqService.formatShlc(xmszModel.getShlc()));
		request.setAttribute("sqxmInfo", xmsqService.getSqxmInfo(xh, xmszModel));
		// ================= end =====================
		
		// =================== ��ʼ���б�ֵ ===========
		service.setPjpyOptionList(myForm, rForm, request);
		// ================= end =====================

		// ============= ����request��ֵ =============
		service.setRequestValue(rForm, user, request);
		// =================== end ===================

		return mapping.findForward("xmshDetail");
	}
}
