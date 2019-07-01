package xsgzgl.szdw.general.szbb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xgxt.utils.Pages;

import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.SzdwGeneralService;
import xsgzgl.szdw.general.inter.SzdwSzbbInterface;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.szdw.fdyxx.FdyxxModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_˼�����_ͨ��_Action��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class SzbbAction extends BasicAction {

	/**
	 * ��ѯ˼������Ա���
	 * 
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward searchSzbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzbbModel model = new SzbbModel();
		SzbbInit init = new SzbbInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSzbbSearch(rForm, myForm, user, request);
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getSzbbTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service
				.getSzbbList(myForm, model, user);
		// ���������
		String spHtml = service.createSzbbHTML(rsModel, model, rsArrList, user,true);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}

	/**
	 * ��ѯ˼�������α��
	 *
	 * @date 2018-09-15
	 * @author wn
	 */
	public ActionForward searchSzBzrbb(ActionMapping mapping, ActionForm form,
									HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzbbModel model = new SzbbModel();
		SzbbInit init = new SzbbInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSzbbSearch(rForm, myForm, user, request);
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);

		// �������ʾ�ֶ�
		String[] otherValue = request.getParameter("otherValue")
				.split("!!@@!!");

		// IE�汾
		String ie = otherValue[0];
		rsModel.setIe(ie);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================�߼���ѯ���========================
		SearchModel searchModel = myService.setSearchModel(rForm, request);
		myForm.setSearchModel(searchModel);
		// ==================�߼���ѯ��� end========================

		// ==================��ʾ����========================
		// ����
		List<HashMap<String, String>> topTr = service.getSzBzrbbTop(model, user);
		// �����
		ArrayList<String[]> rsArrList = service
				.getSzBzrbbList(myForm, model, user);
		// ���������
		String spHtml = service.createSzbbHTML(rsModel, model, rsArrList, user,false);
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");

		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ��ѯ�ѷ��丨��Ա��Ϣ
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward loadYfpFdy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= ��ʼ����������ֵ ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// �༶����
		String bjdm=request.getParameter("bjdm");
		
		// �����ѷ��丨��Աǰ̨��ʾTBODY
		String html=service.createYfpFdyHTML(bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(html);

		return null;
		
	}
	
	/**
	 * ��ѯ�ѷ����������Ϣ
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward loadYfpBzr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= ��ʼ����������ֵ ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// �༶����
		String bjdm=request.getParameter("bjdm");
		
		// �����ѷ��������ǰ̨��ʾ��ϢHTML
		String html=service.createYfpBzrHTML(bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(html);

		return null;
	}
	
	/**
	 * δ����ʦ���ݼ��أ�����Ա or �����Σ�
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward searchSetting(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		CommService commService=new CommService();
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
		SzbbModel model = new SzbbModel();
		SzbbInit init = new SzbbInit();

		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����

		// ============= ��ʼ����������ֵ ============
		init.initSzbbSearch(rForm, myForm, user, request);
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// IE�汾
		String ie = request.getParameter("ie");
		rsModel.setIe(ie);
		
		String bjdm=request.getParameter("bjdm");
		myForm.setBjdm(bjdm);
		
		String fplx=request.getParameter("hid_fplx");
		
		commService.getModelValue(myForm, request);
		// =================== end ===================

		// ==================��ҳ���========================
		Pages pages = myService.setPages("", request);
		myForm.setPages(pages);
		// ==================��ҳ��� end========================

		// ==================��ʾ����========================
		// ����
		//����������Ի����޸ĸ���Ա���
		List<HashMap<String, String>> topTr = null;
		if("12303".equals(Base.xxdm)){
			if("fdy".equalsIgnoreCase(fplx)){
				topTr = service.getSzbbSetTop1(model, user);
			}else if("bzr".equalsIgnoreCase(fplx)){
				topTr = service.getSzbbSetTop(model, user);
			}
		}else{
			 topTr = service.getSzbbSetTop(model, user);
		}
		// �����
		ArrayList<String[]> rsArrList =new ArrayList<String[]>();
		
		// ������� Ϊ����Ա���
		model.setFplx(fplx);
		
		String spHtml ="";
		if("fdy".equalsIgnoreCase(fplx)){
			
			rsArrList=(ArrayList<String[]>)service.getWfpFdyList(myForm, model, user);
			// ���������
			spHtml = service.createWfpFdyHTML(rsModel, model, rsArrList, user);
		}else if("bzr".equalsIgnoreCase(fplx)){// ������� Ϊ�����α��
			
			rsArrList=(ArrayList<String[]>)service.getWfpBzrList(myForm, model, user);
			// ���������
			spHtml = service.createWfpBzrHTML(rsModel, model, rsArrList, user);
		}
		
		// ==================��ʾ���� end========================

		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		rsModel.setCheckBox("no");
		rsModel.setShowTitle("yes");
		
		myService.createRs(rsModel, pages, response);
		// ==================����ǰ̨ҳ�� end========================

		return null;
	}
	
	/**
	 * ȡ������Ա���
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward cancelFdybb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= ��ʼ����������ֵ ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// ����Աְ����
		String zgh=request.getParameter("zgh");
		
		// �༶����
		String bjdm=request.getParameter("bjdm");
		
		// �����ѷ��������ǰ̨��ʾ��ϢHTML
		boolean flag=service.cancelFdybb(zgh,bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(String.valueOf(flag));

		return null;
	}
	
	/**
	 * ȡ�������α��
	 * @date 2013-01-09
	 * @author qlj
	 */
	public ActionForward cancelBzrbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= ��ʼ����������ֵ ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// ������ְ����
		String zgh=request.getParameter("zgh");
		
		// �༶����
		String bjdm=request.getParameter("bjdm");
		
		// �����ѷ��������ǰ̨��ʾ��ϢHTML
		boolean flag=service.cancelBzrbb(zgh,bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(String.valueOf(flag));

		return null;
	}
	
	/**
	 * ����Ա���
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward setFdybb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= ��ʼ����������ֵ ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// ����Աְ����
		String zgh=request.getParameter("zgh");
		
		// �༶����
		String bjdm=request.getParameter("bjdm");
		
		// �����ѷ��������ǰ̨��ʾ��ϢHTML
		boolean flag=service.setFdybb(zgh,bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(String.valueOf(flag));

		return null;
	}
	
	/**
	 * �����α��
	 * @date 2013-01-15
	 * @author qlj
	 */
	public ActionForward setBzrbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		SzdwGeneralService myService = new SzdwGeneralService();
	
		// ============= ��ʼ����������ֵ ============
		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		
		// ������ְ����
		String zgh=request.getParameter("zgh");
		
		// �༶����
		String bjdm=request.getParameter("bjdm");
		
		// �����ѷ��������ǰ̨��ʾ��ϢHTML
		boolean flag=service.setBzrbb(zgh,bjdm);

		response.setContentType("text/html;charset=gbk");

		response.getWriter().print(String.valueOf(flag));

		return null;
	}
	
	
	/**
	 * ����Ա��Ϣά���Զ��嵼��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward szdwbbExportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SzdwGeneralForm myForm = (SzdwGeneralForm) form;
		
		SzdwGeneralService myService = new SzdwGeneralService();
//		SzdwSzbbInterface service = myService.getSzdwSzbbService(myForm);
		SzdwGeneralService szdwGeneralService = new SzdwGeneralService();
		
		//���ɸ߼���ѯ����		
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String,String>> resultList;
		String dcclbh = request.getParameter("dcclbh");
		if("general_szdw_bzr.do".equals(dcclbh)){
			resultList = szdwGeneralService.getSzBzrbbList(myForm,user);
		} else {
			resultList = szdwGeneralService.getSzbbList(myForm,user);
		}
		//�������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());//��ǰ����Ա
		exportModel.setDataList(resultList);//��������
		exportModel.setDcclbh(dcclbh);//���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);//���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	public ActionForward jslx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String quantity = request.getParameter("quntity");
		request.setAttribute("quntity", quantity);
		return mapping.findForward("jslx");
	} 
	
	public ActionForward jsbb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String quantity = request.getParameter("quntity");
		request.setAttribute("quntity", quantity);
		return mapping.findForward("jsbb");
	}
}
