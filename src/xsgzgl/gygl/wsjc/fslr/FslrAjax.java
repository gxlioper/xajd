package xsgzgl.gygl.wsjc.fslr;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.comm.MessageInfo;
import xgxt.comm.SearchRsModel;
import xgxt.comm.search.SearchModel;
import xgxt.form.RequestForm;
import xgxt.form.User;

import com.zfsoft.basic.BasicAction;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import common.Globals;

/**
 * <p>
 * Title: ѧ����������ϵͳ
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-6-28 ����11:29:22
 * </p>
 */
public class FslrAjax extends BasicAction {

	/**
	 * ������飬������¼����Ϣ�Ĳ�ѯ
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fslrCx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FslrService service = new FslrService();
		FslrForm myForm = (FslrForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyglnew_wsjc_fslr.do?searchType=fslrCx");
		request.setAttribute("path", "gyglnew_wsjc_fslr.do");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		List<HashMap<String, String>> topTr = service.getTopTr();
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getFslrCx(myForm, request);
		// ���������
		String spHtml = service.createSearchHTML(rsModel, rsArrList, user);
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}

	/**
	 * ������飬������¼��ѡ���ճ̶Ը�������Ϣ�Ĳ���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward fslrCz(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FslrService service = new FslrService();
		FslrForm myForm = (FslrForm) form;
		RequestForm rForm = new RequestForm();
		SearchRsModel rsModel = new SearchRsModel();
		User user = getUser(request);// �û�����
		String pkValue = request.getParameter("pkValue");
		// ============= ��ʼ����������ֵ ============
		service.commInit(rForm, myForm, request, user);
		myForm.getSearchModel().setPath("gyglnew_wsjc_fslr.do?searchType=fslrcz");
		request.setAttribute("path", "gyglnew_wsjc_fslr.do?searchType=fslrcz");
		// IE�汾
		String ie = request.getParameter("otherValue").split("!!@@!!")[0];
		rsModel.setIe(ie);
		// =================== end ===================
		List<HashMap<String, String>> topTr = service.getTopTr2(pkValue);
		// �����
		ArrayList<String[]> rsArrList = (ArrayList<String[]>) service.getFslrCz(myForm, pkValue,request);
		// ���������
		String spHtml;
		if(Base.xxdm.equals(Globals.XXDM_TJJDZYJSXY)||Base.xxdm.equals("33333")){
			spHtml = service.createSearchHTMLForZjsyjs(rsModel,rsArrList,user,pkValue);
		}else if("11647".equals(Base.xxdm)){
			spHtml = service.createSearchHTML2ForZjCm(rsModel, rsArrList, user, pkValue);
		}else if(Globals.XXDM_TJJM.equals(Base.xxdm)){//��򾭼�ó��
			spHtml = service.createSearchHTML2Tjjm(rsModel, rsArrList, user, pkValue);
		}else{
			spHtml = service.createSearchHTML2(rsModel, rsArrList, user,pkValue);
		}
			
		// ==================����ǰ̨ҳ��========================
		rsModel.setTopTr(topTr);
		rsModel.setRsArrList(rsArrList);
		rsModel.setSpHtml(spHtml);
		service.createRs(rsModel, myForm.getPages(), response);
		// ==================����ǰ̨ҳ�� end========================
		return null;
	}

	/**
	 * ������飬������¼��ѡ���ճ̶Ը�������Ϣ��ֵ�ı���
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		FslrService service = new FslrService();
		String message = "";
		boolean flag = false;
		FslrForm myForm = (FslrForm) form;
		User user = getUser(request);

		String pkValue = request.getParameter("pkValue");
		String jcrq = request.getParameter("jcrq");
		String jcbm = service.unicode2Gbk(request.getParameter("jcbm"));
		String jcry = service.unicode2Gbk(request.getParameter("jcry"));
		String bz = service.unicode2Gbk(request.getParameter("bz"));
		String sfsdj = request.getParameter("sfsdj");
		String username = user.getUserName();
		myForm.setJcrq(jcrq);
		myForm.setJcbm(jcbm);
		myForm.setJcry(jcry);
		myForm.setBz(bz);

		String[] valArr = service.unicode2Gbk(request.getParameter("str"))
				.split("!!!@@@");
		
		String[] bzStr = service.unicode2Gbk(request.getParameter("bzStr"))
		.split("!!!@@@");

		flag = service.fslrBc(myForm, pkValue, valArr, bzStr, username, sfsdj);
		if(Base.xxdm.equals("33333")){
			String kfyjStr = service.unicode2Gbk(request.getParameter("kfyjStr"));
			KflrService kfService = new KflrService();
			flag = kfService.kflrUpdateKfyj(pkValue, kfyjStr);
		}
		if(Base.xxdm.equals("11647")){
			String[] byqs = service.unicode2Gbk(request.getParameter("byqs"))
			.split("!!!@@@");
			flag = service.updateByqsForZjCm(pkValue, byqs);
		}
		if("12688".equals(Base.xxdm)){
			String rcmc = service.getRcmcById(pkValue);
			flag = service.saveXsFslr(myForm, pkValue, valArr, bzStr, username, sfsdj,rcmc);
		}
		if (Base.isNull(message)) {
			message = flag ? MessageInfo.MESSAGE_SAVE_SUCCESS
					: MessageInfo.MESSAGE_SAVE_FALSE;
		}
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(message);
		return null;
	}
	
	public ActionForward checkJcrq(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FslrService service = new FslrService();
		FslrForm myForm = (FslrForm) form;
		String mess = "";
		String pkValue = request.getParameter("pkValue");
		String jcrq = request.getParameter("jcrq");
		HashMap<String, String> jcrcbt = service.getFslrCz2(myForm, pkValue);
		String jcrckssj = jcrcbt.get("kssj");
		String jcrcjssj = jcrcbt.get("jssj");
		mess = service.checkJcrq(jcrckssj,jcrcjssj,jcrq);
		response.setContentType("text/html;charset=gbk");
		response.getWriter().print(mess);
		return null;
	}
	
	/**
	 * 
	 * @����: �㽭��ýͳ�Ʋ�ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-3 ����10:13:59
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward searchForZjcmTjCx(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FslrForm myForm = (FslrForm) form;
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = new FslrService().searchForZjcmTjCx(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 *
	 * @����:����ͳ�Ʋ�ѯ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-7-3 ����10:46:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		FslrForm myForm = (FslrForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		List<HashMap<String, String>> resultList = new FslrService().searchForZjcmTjCx(myForm, user);
		

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = myForm.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
}