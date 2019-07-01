package xgxt.pjpy.comm.zhcp.sjdr;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.FileManage;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.utils.rar.CompressFile;

import com.zfsoft.basic.BasicAction;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �ۺ����ʲ���_���ݵ���_Action��
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

public class ZhcpSjdrAction extends BasicAction {

	/**
	 * �ۺϲ���_���ݵ���_����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjdrManage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();
		User user = getUser(request);// �û�����
		ZhcpJbszForm jbszModel = ZhcpJbszForm.zcjbszModel;

		// ============= ��ʼ����������ֵ ============
		List<HashMap<String, String>> xmList = service.getXmList(myForm);// ��Ŀ�б�
		myForm.setXmList(xmList);

		RequestForm rForm = new RequestForm();
		init.getSjdrRForm(rForm, myForm, request);

		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String message = "";// ��ʾ��Ϣ
		String[] xn = new String[] { jbszModel.getPjxn() };// ѧ��
		// ��ʼ����Ŀ�б�
		List<HashMap<String, String>> cshXmList = service.getCshXmList(myForm);
		request.setAttribute("cshXmList", cshXmList);
		// =================== end ===================

		// ============= ����request��ֵ =============
		myForm.getSearchModel().setSearch_tj_xn(xn);
		request.setAttribute("searchTj", myForm.getSearchModel());
		service.setRequestValue(rForm, request);
		// =================== end ===================

		return mapping.findForward("sjdrManage");
	}

	/**
	 * ����۲����ز�ѯ�б�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward getZhcpInfoList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");
		// ������Ŀ
		String czxm = otherValue[0];
		myForm.setCzxm(czxm);
		// IE�汾
		String ie = otherValue[1];
		rsModel.setIe(ie);
		// ��Դ��
		String lyb = otherValue[2];
		myForm.setLyb(lyb);

		RequestForm rForm = new RequestForm();
		init.getSjdrRForm(rForm, myForm, request);
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
		ArrayList<String[]> rsArrList = service.getZhfwhList(myForm, user);
		String spHtml = service.getSpHtml(rsModel, myForm, rsArrList);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");

		service.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * �����ۺϲ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward saveZhcpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		User user = getUser(request);// �û�����

		String[] pk = request.getParameter("pk").split("!!@@!!");
		String[] fs = request.getParameter("fs").split("!!@@!!");

		myForm.setPk(pk);
		myForm.setFs(fs);

		// ִ�б������
		boolean flag = service.saveZhcpfXgInfo(myForm, user);
		// ��ʾ��Ϣ
		String message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
				: MessageInfo.MESSAGE_SAVE_FALSE;

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ͬ���ۺϲ�����
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward tbZhcpf(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		User user = getUser(request);// �û�����

		// ��Դ��
		String lyb = request.getParameter("lyb");
		// ������Ŀ
		String czxm = request.getParameter("czxm");

		myForm.setCzxm(czxm);
		myForm.setLyb(lyb);

		// ͬ������
		boolean flag = service.tbZhcpfXgInfo(myForm, user);
		// ��ʾ��Ϣ
		String message = flag ? MessageInfo.MESSAGE_DO_SUCCESS
				: MessageInfo.MESSAGE_DO_FALSE + ",����������������Դ������ɣ�����ϵ��ظ����˽��";

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ��ʾ������Ϣ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward showDcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();

		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		init.getSjdrRForm(rForm, myForm, request);

		// ������ʽ
		String dcxs = request.getParameter("dcxs");
		myForm.setDcxs(dcxs);

		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ͬ�����б�
		List<HashMap<String, String>> bmList = service.getExpBmInfo(myForm);
		// ������
		int bmNum = (bmList != null && bmList.size() > 0) ? bmList.size() : 0;

		// ��ʾ��Ϣ
		String message = "";

		if (bmNum > 0) {
			message += "��Ҫ����" + bmList.size() + "����������";
			message += "(";
			for (int i = 0; i < bmList.size(); i++) {
				if (i == 2) {
					break;
				} else if (i != 0) {
					message += ",";
				}
				message += bmList.get(i).get("bmmc");
			}
			if (bmNum != 0) {
				message += "��";
			}
			message += ")";
		} else {
			message = "false";
		}

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(message);

		return null;
	}

	/**
	 * ����excel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward expToExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();

		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();
		init.getSjdrRForm(rForm, myForm, request);

		// ������ʽ
		String dcxs = request.getParameter("dcxs");
		myForm.setDcxs(dcxs);

		// ����·��
		String filePath = request.getParameter("filePath");
		myForm.setFilePath(filePath);

		// ������Ŀ
		String czxm = request.getParameter("czxm");
		myForm.setCzxm(czxm);
		// ==================�߼���ѯ���========================
		SearchModel searchModel = service.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ͬ�����б�
		List<HashMap<String, String>> bmList = service.getExpBmInfo(myForm);
		myForm.setBmList(bmList);
		myForm.setFilePath(FileManage.getRootPath(request, "zhcp"));

		boolean result = service.expZcfmbToExcel(myForm, user);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(result ? "ģ���Ѿ�����,����������Ҫ��ģ��!" : "ģ������ʧ��!");

		return null;
	}

	/**
	 * �ۺϲ���_���ݵ���_�ļ��ϴ�
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward sjdrImp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ZhcpSjdrForm myForm = (ZhcpSjdrForm) form;
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrInit init = new ZhcpSjdrInit();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		RequestForm rForm = new RequestForm();

		// ============= ��ʼ����������ֵ ============
		init.getSjdrRForm(rForm, myForm, request);
		String doType = rForm.getDoType(); // �������ͣ����棬ɾ���ȣ�
		String userType = user.getUserType();// �û�����
		String userStatus = user.getUserStatus();// �û����
		String showMessage = "";// ��ʾ��Ϣ

		init.getSjdrRForm(rForm, myForm, request);
		// =================== end ===================

		// ============= ִ���ϴ����� ============
		if ("imp".equalsIgnoreCase(doType)) {

			String filePath = service.upLoadFile(request, myForm
					.getImpFilePath(), "comm");

			showMessage = (String) request.getAttribute("message");

			if (Base.isNull(showMessage)) {

				showMessage = service.impZcfInfo(myForm, filePath, user);
				showMessage = Base.isNull(showMessage) ? "�������ݳɹ�,���Ե�����һ��"
						: showMessage;
			}
		}
		// ============= ִ���ϴ����� end ============

		request.setAttribute("showMessage", showMessage);

		return mapping.findForward("sjdrImp");
	}

	/**
	 * ģ�����ҳ��
	 */
	public ActionForward sjdrExp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String czxm = request.getParameter("czxm");
		ZhcpSjdrService service = new ZhcpSjdrService();
		ZhcpSjdrForm model = (ZhcpSjdrForm) form;
		User user = getUser(request);// �û�����
		String[] topTr = new String[] { "ģ������", "ģ������ʱ��" };

		if (StringUtils.isNull(model.getBmlx())){
			model.setBmlx("bj");
		}
		
		List<String[]> result = service.getStencilLog(user, model);
		request.setAttribute("rs", result);

		request.setAttribute("topTr", topTr);
		request.setAttribute("czxm", czxm);
		return mapping.findForward("sjdrExp");
	}

	/**
	 * ����ģ��
	 */
	public ActionForward downloadStencil(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String[] fileNames = request.getParameterValues("primarykey_cbv");

		if (null != fileNames && fileNames.length > 0) {

			String filePath = FileManage.getRootPath(request, "zhcp");
			String path = "\\";
			if (System.getProperty("os.name").equalsIgnoreCase("Linux")){
				path = "/";
			}
			for (int i = 0; i < fileNames.length; i++) {
				fileNames[i] = filePath + path + fileNames[i]+".xls";
			}
			
			//����ѹ���ļ�
			CompressFile.getInstance().zip(fileNames, filePath+path+"temp.zip");
			//����
			byte b[] = new byte[500];
			File file = new File(filePath + "/temp.zip");
			if (null != file && file.exists()) {
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment;filename=xgxt_zhcp.zip");
				InputStream in = new FileInputStream(file);
				in = new BufferedInputStream(in);
				while ((in.read(b)) != -1) {
					response.getOutputStream().write(b);
				}
				in.close();
				return  null;
			} else {
				request.setAttribute("message", "�ļ������ڻ�ɾ��������ϵ����Ա");
				return new ActionForward("/prompt.do",false);
			}
		}
		return null;
	}
}
