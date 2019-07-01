/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-6-25 ����10:14:00 
 */  
package com.zfsoft.xgxt.rcsw.txhd.xmsq.js;

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
import xgxt.utils.String.StringUtils;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.action.SuperAction;
import com.zfsoft.xgxt.base.auth.SystemAuth;
import com.zfsoft.xgxt.base.auth.SystemAuth.ReadWrite;
import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.log.SystemLog;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.message.MessageUtil;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.base.util.FileUtil;
import com.zfsoft.xgxt.comm.bdpz.service.BdpzService;
import com.zfsoft.xgxt.comm.export.model.ExportModel;
import com.zfsoft.xgxt.comm.export.service.IExportService;
import com.zfsoft.xgxt.comm.export.service.impl.ExportExcelImpl;
import com.zfsoft.xgxt.comm.shlc.dao.ShlcDao;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszForm;
import com.zfsoft.xgxt.rcsw.txhd.xmsz.TxhdXmszService;
import com.zfsoft.xgxt.xpjpy.cssz.CsszModel;
import com.zfsoft.xgxt.xpjpy.cssz.CsszService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;


/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ���Ŀ���루��ʦ�� Action
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2014-6-25 ����10:14:00 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TxhdXmsqJsAction extends SuperAction {

	private TxhdXmsqJsService service = new TxhdXmsqJsService();
	private List<HashMap<String,String>> jbxxList = null;
	private static final String RCSWRCXW = "rcswqjgl";
	private BdpzService bdpzService = new BdpzService();
	
	private static final String url = "rcsw_txhd_xmsq_js.do";
	
	
	/**
	 * 
	 * @����:��ѯҳ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-25 ����11:20:12
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
	@SystemAuth(url = url)
	public ActionForward viewJssqList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmsqJsForm model = (TxhdXmsqJsForm) form;
		
		if (QUERY.equals(model.getType())){
			//���ɸ߼���ѯ����
			CommService comService = new CommService();
			SearchModel searchModel = comService.getSearchModel(request);
			model.setSearchModel(searchModel);
			
			User user = getUser(request);
			//��ѯ
			List<HashMap<String,String>> resultList = service.getPageList(model,user);
			JSONArray dataList = JSONArray.fromObject(resultList);
			response.getWriter().print(dataList);
			return null;
		}
		
		//Ĭ�ϸ߼���ѯ����
		SearchModel searchModel=new SearchModel();
		CsszService csszService = new CsszService();
		CsszModel csszModel = csszService.getModel();
		searchModel.setSearch_tj_xn(new String[]{csszModel.getXn()});
		searchModel.setSearch_tj_xq(new String[]{csszModel.getXq()});
		request.setAttribute("searchTj", searchModel);
		
		request.setAttribute("path", "rcsw_txhd_xmsq_js.do");
		FormModleCommon.commonRequestSet(request);
		return mapping.findForward("viewJssqList");
		
	}
	
	
	/**
	 * 
	 * @����:��д��Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-25 ����01:30:02
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward txhdXmsq (ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmsqJsForm model = (TxhdXmsqJsForm) form;
		User user = getUser(request);
		
		String xh = "stu".equals(user.getUserType()) ? user.getUserName() : model.getXh();
		
		if (!StringUtil.isNull(xh)){
			//ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String,String> xsjbxx = xsxxService.getXsjbxxMore(xh);
			request.setAttribute("jbxx", xsjbxx);
		}
		
		//ѧ��������Ϣ
		String path = "rcsw_txhd_xmsq.do?method=txhdXmsq";
		request.setAttribute("path", path);
		
		//ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		
		request.setAttribute("xh", xh);
		request.setAttribute("currXn", Base.currXn);
		request.setAttribute("currXq", Base.getDqxqmc());
		
		return mapping.findForward("txhdXmsq");
	}
	
	
	
	/**
	 * 
	 * @����:������Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-25 ����02:06:35
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ�-����루��ʦ��-�޸�SQID:{sqid}")
	public ActionForward saveXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) StringUtils.formatBean(form);
		User user = getUser(request);

		String[] xmdmArray = request.getParameterValues("xmdmArray");
		
		//�ύ�ж��Ƿ�����루ʣ�����
		boolean  sfksq = true;
		
		for(int i = 0, n = xmdmArray.length; i < n; i++){
			sfksq = service.getSymeForXmdm(xmdmArray[i],myForm.getType());
			if(!sfksq){
				break;
			}
		}
		
		if(sfksq){
			// ����
			boolean result = service.saveXmsq(myForm, xmdmArray,user);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}

		return null;
		
	}
	
	
	/**
	 * 
	 * @����:������Ŀѡ��ҳ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-25 ����02:53:37
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
	@SystemAuth(url = url)
	public ActionForward getXmsqInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm model = (TxhdXmsqJsForm) form;

		// ��Ŀ������Ϣ
		List<HashMap<String, String>> xmsqInfoList = service.getXmsqInfoList(model.getXh());
		request.setAttribute("xmsqInfoList", xmsqInfoList);

		return mapping.findForward("getXmsqInfo");
	}

	
	
	/**
	 * 
	 * @����:�޸���Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����10:25:24
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward updateXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) form;
		TxhdXmsqJsForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}
		//��ȡ������Ŀ��Ϣ
		//��ȡ������Ŀ��Ϣ
		TxhdXmszService txhdXmszService=new TxhdXmszService();
		TxhdXmszForm txhdXmszForm = txhdXmszService.getModel(model.getXmdm());
		request.setAttribute("txhdXmszForm", StringUtils.formatData(txhdXmszForm));
		
		request.setAttribute("xmwhForm", StringUtils.formatData(model));
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("updateXmsq");
	}
	
	@SystemAuth(url = url)
	public ActionForward showView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) form;
		TxhdXmsqJsForm model = service.getModel(myForm);

		if (model != null) {
			BeanUtils.copyProperties(myForm, StringUtils.formatData(model));

			// ����ѧ��������Ϣ
			XsxxService xsxxService = new XsxxService();
			HashMap<String, String> xsjbxx = xsxxService.getXsjbxxMore(myForm
					.getXh());
			request.setAttribute("jbxx", xsjbxx);

		}
		//��ȡ������Ŀ��Ϣ
		//��ȡ������Ŀ��Ϣ
		
		TxhdXmszService txhdXmszService=new TxhdXmszService();
		TxhdXmszForm txhdXmszForm = txhdXmszService.getModel(model.getXmdm());
		request.setAttribute("txhdXmszForm", StringUtils.formatData(txhdXmszForm));
		
		request.setAttribute("xmwhForm", StringUtils.formatData(model));
		// ѧ��������Ϣ��ʾ����
		jbxxList = bdpzService.getJbxxpz(RCSWRCXW);
		request.setAttribute("jbxxList", jbxxList);
		return mapping.findForward("viewXmsq");
	}
	
	
	/**
	 * 
	 * @����:�޸ı���
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����11:24:15
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ�-����루��ʦ��-�޸�SQID:{sqid}")
	public ActionForward saveSqxg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) form;
		
		//�ж��Ƿ�����루ʣ�����
		boolean  sfksq = true;
		
		sfksq = service.getSymeForXmdm(myForm.getXmdm(), myForm.getType());
		
		if(sfksq){
			// ִ���޸Ĳ���
			boolean result = service.bcxgXmsq(myForm);
			String messageKey = result ? MessageKey.SYS_SAVE_SUCCESS
					: MessageKey.SYS_SAVE_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
			
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}
		
		return null;
	}
	
	
	/**
	 * 
	 * @����:ɾ����Ŀ����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����11:53:01
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ�-����루��ʦ��-ɾ��VALUES:{values}")
	public ActionForward delXmsq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String values = request.getParameter("values");

		if (!StringUtil.isNull(values)) {
			// ɾ��
			int num = service.runDelete(values.split(","));
			boolean result = num > 0;
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
	 * @����:�ύ�����¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����11:58:13
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ�-����루��ʦ��-�ύVALUES:{values}")
	public ActionForward subBusi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		String xh = request.getParameter("xh");
		String xmdm = request.getParameter("xmdm");
		
		//�ж��Ƿ�����루ʣ�����
		boolean  sfksq = true;
		sfksq = service.getSymeForXmdm(xmdm,"submit");
		
		if(sfksq){
			boolean result = service.submitRecord(values,lcid,xh);
			if(result){
				//����ҵ��״̬Ϊ'�����'
				TxhdXmsqJsForm model = new TxhdXmsqJsForm();
				model.setSqid(values);
				model.setShzt(Constants.YW_SHZ);
				service.updateModel(model);
			}
			String messageKey = result ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}
		
		return null;
	}
	/**
	 * 
	 * @����:��֤��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����11:58:13
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
	public ActionForward sfksq(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String xmdm = request.getParameter("xmdm");
		
		//�ж��Ƿ�����루ʣ�����
		boolean  sfksq = true;
		sfksq = service.getSymeForXmdm(xmdm,"submit");
		if(sfksq){
			String messageKey = sfksq ? MessageKey.SYS_SUBMIT_SUCCESS
					: MessageKey.SYS_SUBMIT_FAIL;
			response.getWriter().print(getJsonMessageByKey(messageKey));
		}
		else{
			response.getWriter().print(getJsonMessageByKey(MessageKey.RCSW_TXHD_XMMC_FULL));
		}
		
		return null;
	}
	
	
	
	
	/**
	 * 
	 * @����: ���������¼
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����01:26:29
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	@SystemLog(description="�����ճ�����-��ѧ�-����루��ʦ��-����VALUES:{values}")
	public ActionForward cancle(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String values = request.getParameter("values");
		String lcid = request.getParameter("lcid");
		
		boolean result = service.cancleRecord(values,lcid);
		
		if(result){
			
			//����ҵ��״̬Ϊ'δ�ύ'
			TxhdXmsqJsForm model = new TxhdXmsqJsForm();
			model.setSqid(values);
			//�鿴�Ƿ����˻ؼ�¼,�У����״̬��Ϊ�˻�
			ShlcDao shlcdao = new ShlcDao();
			if(Integer.valueOf(shlcdao.getExistTh(values))>0){
				model.setShzt(Constants.YW_YTH);
			}else{
				model.setShzt(Constants.YW_WTJ);
			}
			service.updateModel(model);
			
		}
		String messageKey = result ? MessageKey.SYS_CANCEL_SUCCESS
				: MessageKey.SYS_CANCEL_FAIL;
		response.getWriter().print(getJsonMessageByKey(messageKey));
		return null;
	}
	
	
	/**
	 * 
	 * @����:����
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-27 ����01:36:07
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
	@SystemAuth(url = url,rewritable=ReadWrite.WRITEABLE)
	public ActionForward exportData(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		TxhdXmsqJsForm myForm = (TxhdXmsqJsForm) form;

		// ���ɸ߼���ѯ����
		CommService comService = new CommService();
		SearchModel searchModel = comService.getSearchModel(request);
		myForm.setSearchModel(searchModel);

		User user = getUser(request);
		// ��ѯ
		List<HashMap<String, String>> resultList = service.getAllList(myForm,
				user);// ��ѯ�����м�¼������ҳ

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
