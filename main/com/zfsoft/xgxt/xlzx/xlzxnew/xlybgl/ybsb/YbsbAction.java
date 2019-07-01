package com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybsb;

import java.io.File;
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
import xgxt.comm.CommService;
import xgxt.comm.search.SearchModel;
import xgxt.form.User;
import xgxt.utils.FormModleCommon;

import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.xlzx.xlzxnew.xlybgl.ybjg.YbjgService;
import com.zfsoft.xgxt.xlzx.xlzxnew.zqsz.jcsz.XlzxSbService;
import common.newp.StringUtil;

public class YbsbAction extends SuperAction<YbsbForm,YbsbService> {
	private final String url = "xg_xlzxnew_ybsb.do";
	private YbsbService service = new YbsbService();
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ȡ�±������ѯList
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����06:37:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward getYbsbCxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		SearchModel searchModel = new SearchModel();
		searchModel.setSearch_tj_xn(new String[] { Base.currXn });
		request.setAttribute("searchTj", searchModel);
		request.setAttribute("cssz", new XlzxSbService().getModel("yb"));
		request.setAttribute("path", url);
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("ybsbcx");
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ѯ�±�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����06:42:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * ActionForward �������� 
	 * @throws
	 */
	public ActionForward seachYbsqCxList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		User user = getUser(request);
		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);
		List<HashMap<String, String>> resultList = service.getPageList(myForm, user);
		JSONArray dataList = JSONArray.fromObject(resultList);
		response.getWriter().print(dataList);
		return null;
	}
	
	/**
	 * 
	 * @����: �����±����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����11:25:22
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
	public ActionForward addYbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("txr",user.getRealName());
		request.setAttribute("xymc",new YbjgService().getXymc(user.getUserDep()));
		myForm.setXn(Base.currXn);
		myForm.setXydm(user.getUserDep());
		request.setAttribute("xnList",Base.getXnndList());
		return mapping.findForward("addybsb");
	}
	
	/**
	 * 
	 * @����: �޸��±����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����11:36:58
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
	public ActionForward editYbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		YbsbForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
			request.setAttribute("yf", Integer.valueOf(model.getYf())+"��");
			request.setAttribute("sfywt", model.getSfywt());
			request.setAttribute("xymc",new YbjgService().getXymc(model.getXydm()));
		}
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("txr",user.getRealName());
		myForm.setTxr(user.getUserName());
		request.setAttribute("wtryInfo", service.getYbWtryInfo(model.getSbid()));
		return mapping.findForward("editybsb");
	}
	
	/**
	 * 
	 * @����: �鿴�±����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-5 ����11:36:58
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
	public ActionForward ckYbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		YbsbForm model = service.getModel(myForm);
		if(model != null){
			BeanUtils.copyProperties(myForm, model);
			request.setAttribute("yf", Integer.valueOf(model.getYf())+"��");
			request.setAttribute("xymc",new YbjgService().getXymc(model.getXydm()));
		}
		User user = getUser(request);
		request.setAttribute("xnList", Base.getXnndList());
		request.setAttribute("txr",user.getRealName());
		request.setAttribute("wtryInfo", service.getYbWtryInfo(model.getSbid()));
		return mapping.findForward("ckybsb");
	}
	
	/**
	 * 
	 * @����: ɾ���ϱ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-2 ����03:01:55
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
	public ActionForward del(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//���id
		String values = request.getParameter("values");
		if (!StringUtil.isNull(values)) {
			String[] ids = values.split(",");
			int num = service.runDelete(ids);
			boolean result = num > 0;
			if(result){
				service.delSbjg(ids);
			}
			String message = result ? MessageUtil.getText(
					MessageKey.SYS_DEL_NUM, num) : MessageUtil
					.getText(MessageKey.SYS_DEL_FAIL);
			response.getWriter().print(getJsonMessage(message));
		} else {
			throw new SystemException(MessageKey.SYS_DEL_NULL);
		}
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-4 ����10:33:20
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
		
		YbsbForm model = (YbsbForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		model.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(model,
				user);// ��ѯ�����м�¼������ҳ
		

		// �������ܴ���
		IExportService exportService = new ExportExcelImpl();
		ExportModel exportModel = model.getExportModel();
		exportModel.setZgh(user.getUserName());// ��ǰ����Ա
		exportModel.setDataList(resultList);// ��������
		exportModel.setDcclbh(request.getParameter("dcclbh"));// ���õ�ǰ�������ܱ��
		File file = exportService.getExportFile(exportModel);// ���ɵ����ļ�
		FileUtil.outputExcel(response, file);
		return null;
	}
	
	/**
	 * 
	 * @����: �����ϱ�����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-29 ����04:33:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws
	 */
	public ActionForward saveYbsb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		String[] xhArray = request.getParameterValues("xh");
		String[] ybwtmsArray = request.getParameterValues("ybwtms");
		String[] ybgycsArray = request.getParameterValues("ybgycs");
		String[] ybgyhjgArray = request.getParameterValues("ybgyhjg");
		String[] wtfsrqArray = request.getParameterValues("wtfsrq");
		myForm.setXhArray(xhArray);
		myForm.setYbwtmsArray(ybwtmsArray);
		myForm.setYbgycsArray(ybgycsArray);
		myForm.setYbgyhjgArray(ybgyhjgArray);
		myForm.setTxr(getUser(request).getUserName());
		myForm.setWtfsrqArray(wtfsrqArray);
		YbsbService tranService = new YbsbService();
		boolean rs = true;
		try {
			if ("10704".equals(Base.xxdm)) {
				if ("��".equals(myForm.getSfywt())&&myForm.getSbid()!=null) {//��� ԭ�������⣬��Ϊû���⣬��������������ԭ�еĸ������
					rs = tranService.update(myForm);
				}else {
					rs = tranService.saveYbsb(myForm);
				}
			}else {
				rs = tranService.saveYbsb(myForm);
			}
		} catch (SystemException e) {
			// TODO �Զ����� catch ��
			response.getWriter().print(getJsonMessage(e.getMessage()));
			return null;
		}
		String messageKey = rs ? MessageKey.SYS_SAVE_SUCCESS:MessageKey.SYS_SAVE_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
	    return null;
	}
	
	
	/**
	 * @����: �ύ
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����10:10:49
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
	public ActionForward submit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		YbsbForm myForm = (YbsbForm)form;
		String value = request.getParameter("sbsqid");
		myForm.setSbid(value);
		YbsbForm model = service.getModel(myForm);
		User user = getUser(request);
		boolean result = service.submitBusi(model, user);
		String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
				: MessageKey.SYS_SUBMIT_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	/**
	 * 
	 * @����: ����
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-8-30 ����10:28:11
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
	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String sqid = request.getParameter("sbsqid");
		String lcid = request.getParameter("splcid");
		// ֻ�и��ύ���ҵ�һ��δ��˵�ǰ���£������˿��Գ���
		boolean result = service.cancelRecord(sqid, lcid);
		if (result) {
			// ����ҵ��״̬Ϊ'δ�ύ'
			YbsbForm model = new YbsbForm();
			model.setSbid(sqid);
			model.setSplcid(lcid);
			// �鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if (Integer.valueOf(shlcdao.getExistTh(sqid)) > 0) {
				model.setShzt(Constants.YW_YTH);
			} else {
				model.setShzt(Constants.YW_WTJ);
			}
			service.runUpdate(model);
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS : MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
}
